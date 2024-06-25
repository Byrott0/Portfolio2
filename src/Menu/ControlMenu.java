package Menu;

import Controller.GebruikerController;
import Controller.MedicijnController;
import Utils.*;
import Model.*;
import java.util.List;
import java.util.Scanner;

public class ControlMenu {
    private static MedicijnLijst medicijnLijst;
    private static GebruikerController gebruikerController;
    private static MedicijnController medicijnController;
    private static LoginService loginService;
    private static MedicijnWijziger medicijnWijziger;
    private static WijzigingBevestigd wijzigingBevestigd;
    private static ControlMenu controlMenu;
    private final VeranderingNotificatie veranderingNotificatie;

    public ControlMenu(MedicijnWijziger medicijnWijziger, WijzigingBevestigd wijzigingBevestigd, MedicijnLijst medicijnLijst, MedicijnController medicijnController, LoginService loginService) {
        this.medicijnWijziger = medicijnWijziger;
        this.wijzigingBevestigd = wijzigingBevestigd;
        this.veranderingNotificatie = new VeranderingNotificatie();
        this.medicijnController = medicijnController;
        this.medicijnLijst = medicijnLijst;
        this.loginService = loginService;
        medicijnLijst.addObserver(this::update);
    }

    public boolean login() {
        if (loginService.login()) {
            medicijnWijziger.setMedicijnLijst(medicijnLijst);
            if (medicijnWijziger.WijzigMedicijn(new Scanner(System.in))) {
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
    }

    public static void initialiseerObjecten() {
        medicijnLijst = new MedicijnLijst();
        controlMenu = new ControlMenu(medicijnWijziger, wijzigingBevestigd, medicijnLijst, medicijnController, loginService);
        gebruikerController = new GebruikerController();
        medicijnController = new MedicijnController(medicijnLijst);
        loginService = new LoginService(gebruikerController, medicijnController, controlMenu);
        medicijnWijziger = new MedicijnWijziger();
        wijzigingBevestigd = new WijzigingBevestigd();
    }

    public static LoginService getLoginServiceInstance() {
        return loginService;
    }
}