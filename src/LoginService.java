import java.util.Scanner;

public class LoginService {
    private final GebruikerBeheer gebruikerBeheer;
    private final MedicijnBeheer medicijnBeheer;
    private ControlMenu controlMenu;

    public LoginService(GebruikerBeheer gebruikerBeheer, MedicijnBeheer medicijnBeheer, ControlMenu controlMenu) {
        this.gebruikerBeheer = gebruikerBeheer;
        this.medicijnBeheer = medicijnBeheer;
        this.controlMenu = controlMenu;
    }
    public void voerLoginUit() {
        boolean isLoggedIn = controlMenu.login();
        if (!isLoggedIn) {
            System.out.println("Login mislukt.");
        }
    }

    public boolean login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wil je een account aanmaken? (ja/nee)");
        if (scanner.nextLine().equalsIgnoreCase("ja")) {
            gebruikerBeheer.registreerGebruiker();
            medicijnBeheer.voegMedicijnenToe();
            return true;
        }
        return false;
    }
}