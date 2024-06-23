package Controller;

import Model.Gebruiker;

import java.util.Scanner;


public class GebruikerController {
    private Gebruiker gebruiker;

    public Gebruiker registreerGebruiker() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Voer je naam in");
        String naam = scanner.nextLine();
        System.out.println("Voer je wachtwoord in");
        String wachtwoord = scanner.nextLine();

        gebruiker = new Gebruiker(naam, wachtwoord);
        return gebruiker;
    }
}
