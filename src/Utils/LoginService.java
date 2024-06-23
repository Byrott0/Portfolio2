package Utils;

import Menu.ControlMenu;
import Controller.*;
import java.util.Scanner;

public class LoginService {

    private final GebruikerController gebruikerController;
    private final MedicijnController medicijnController;
    private ControlMenu controlMenu;

    public LoginService(GebruikerController gebruikerController, MedicijnController medicijnBeheer, ControlMenu controlMenu) {
        this.gebruikerController = gebruikerController;
        this.medicijnController = medicijnBeheer;
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
            gebruikerController.registreerGebruiker();
            medicijnController.voegMedicijnenToe();
            return true;
        }
        return false;
    }
}