import java.util.ArrayList;
import java.util.List;

public class ControlMenu {
    private MedicijnWijziger medicijnWijziger;
    private WijzigingBevestigd wijzigingBevestigd;
    private VeranderingNotificatie veranderingNotificatie;
    private List<Medicijn> gemeldeMedicijnen;
    private MedicijnController medicijnController;
    private LoginService loginService;
    private MedicijnLijst medicijnLijst;

    public ControlMenu(MedicijnWijziger medicijnWijziger, WijzigingBevestigd wijzigingBevestigd, MedicijnLijst medicijnLijst, MedicijnController medicijnController, LoginService loginService) {
        this.medicijnWijziger = medicijnWijziger;
        this.wijzigingBevestigd = wijzigingBevestigd;
        this.veranderingNotificatie = new VeranderingNotificatie();
        this.gemeldeMedicijnen = new ArrayList<>();
        this.medicijnController = medicijnController;
        this.medicijnLijst = medicijnLijst;
        this.loginService = loginService;
        medicijnLijst.addObserver(this::update);
    }

    public boolean login() {
        if (loginService.login()) {
            if (medicijnWijziger.wijzigMedicijn(medicijnLijst)) {
                wijzigingBevestigd.setStatus(true);
                System.out.println(wijzigingBevestigd.getMessage());
            } else {
                System.out.println(wijzigingBevestigd.getMessage());
            }

            medicijnLijst.removeObserver(this::update);
        }
        return false;
    }

    public void update(List<Medicijn> medicijnen) {
        System.out.println("Medicijnlijst is gewijzigd: " + medicijnen);
    }
}