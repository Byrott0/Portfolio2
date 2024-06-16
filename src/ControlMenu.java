import java.util.ArrayList;
import java.util.List;

public class ControlMenu {
    private final MedicijnWijziger medicijnWijziger;
    private final WijzigingBevestigd wijzigingBevestigd;
    private final VeranderingNotificatie veranderingNotificatie;
    private final List<Medicijn> gemeldeMedicijnen;
    private final MedicijnController medicijnController;
    private final LoginService loginService;
    private final MedicijnLijst medicijnLijst;

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