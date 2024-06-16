import java.util.Scanner;

public class LoginService {
    private final GebruikerBeheer gebruikerBeheer;
    private final MedicijnBeheer medicijnBeheer;

    public LoginService(GebruikerBeheer gebruikerBeheer, MedicijnBeheer medicijnBeheer) {
        this.gebruikerBeheer = gebruikerBeheer;
        this.medicijnBeheer = medicijnBeheer;
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