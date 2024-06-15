
public class Main implements Wijzigmedicijn {
    public static void main(String[] args) {
        ControlMenu controlMenu = new ControlMenu();
        boolean isLoggedIn = controlMenu.login();
        if (!isLoggedIn) {
            System.out.println("Login mislukt.");
        }
    }

    @Override
    public void wijzigMedicijn(MedicijnLijst medicijnLijst) {
        System.out.println("Medicijnlijst gewijzigd: " + medicijnLijst);
    }
}