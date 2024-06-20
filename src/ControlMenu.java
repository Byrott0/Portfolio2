import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class ControlMenu {
    private static MedicijnLijst medicijnLijst;
    private static MedicijnController medicijnController;
    private static GebruikerBeheer gebruikerBeheer;
    private static MedicijnBeheer medicijnBeheer;
    private static LoginService loginService;
    private static MedicijnWijziger medicijnWijziger;
    private static WijzigingBevestigd wijzigingBevestigd;
    private static ControlMenu controlMenu;
    private final VeranderingNotificatie veranderingNotificatie;

    public ControlMenu(MedicijnWijziger medicijnWijziger, WijzigingBevestigd wijzigingBevestigd, MedicijnLijst medicijnLijst, MedicijnController medicijnController, LoginService loginService) {
        this.medicijnWijziger = medicijnWijziger;
        this.wijzigingBevestigd = wijzigingBevestigd;
        this.veranderingNotificatie = new VeranderingNotificatie();
        this.medicijnLijst = medicijnLijst;
        this.loginService = loginService;
        medicijnLijst.addObserver(this::update);
    }

    public boolean login() {
        if (loginService.login()) {
            medicijnWijziger.setMedicijnLijst(medicijnLijst);
            if (medicijnWijziger.WijzigMedicijn()) {
                wijzigingBevestigd.setStatus(true);
                System.out.println(wijzigingBevestigd.getMessage());
                veranderingNotificatie.notifyChange("Login geslaagd en medicaties gewijzigd.");
            }
            medicijnLijst.removeObserver(this::update);
            return true;
        }
        return false;
    }

    public void update(List<Medicijn> medicijnen) {
        System.out.println("Medicijnlijst is gewijzigd: " + medicijnen);
    }

    public static void initialiseerObjecten() {
        medicijnLijst = new MedicijnLijst();
        controlMenu = new ControlMenu(medicijnWijziger, wijzigingBevestigd, medicijnLijst, medicijnController, loginService);
        medicijnController = new MedicijnController();
        gebruikerBeheer = new GebruikerBeheer();
        medicijnBeheer = new MedicijnBeheer(medicijnLijst, medicijnController);
        loginService = new LoginService(gebruikerBeheer, medicijnBeheer, controlMenu);
        medicijnWijziger = new MedicijnWijziger();
        wijzigingBevestigd = new WijzigingBevestigd();
    }

    public static ControlMenu getControlMenuInstance() {
        return controlMenu;
    }

    public static LoginService getLoginServiceInstance() {
        return loginService;
    }
}