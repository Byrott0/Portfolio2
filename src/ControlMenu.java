import java.time.LocalDateTime;
import java.util.Scanner;

public class ControlMenu {
    public boolean login() {
        Gebruiker gebruiker = new Gebruiker();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wil je een account aanmaken? (ja/nee)");
        if (scanner.nextLine().equals("ja")) {
            System.out.println("Voer je naam in");
            String naam = scanner.nextLine();
            System.out.println("Voer je wachtwoord in");
            String wachtwoord = scanner.nextLine();
            gebruiker.register(naam, wachtwoord);

            System.out.println("Hoeveel medicijnen wil je registreren?");
            int aantalMedicijnen = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            MedicijnController medicijnController = new MedicijnController();
            MedicijnLijst medicijnLijst = new MedicijnLijst();
            VeranderingNotificatie notificatie = new VeranderingNotificatie();
            medicijnLijst.addObserver(notificatie);

            int geregistreerdeMedicijnen = 0;
            while (geregistreerdeMedicijnen < aantalMedicijnen) {
                System.out.println("Kies een type herinnering:");
                System.out.println("1. ReminderSpecifiekeDagen");
                System.out.println("2. ReminderDagelijks");
                int keuze = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                System.out.println("Voer de inname tijd in (HH:mm formaat)");
                String innameTijd = scanner.nextLine();

                Medicijn nieuwMedicijn = medicijnController.maakMedicijn();
                medicijnLijst.addMedicijn(nieuwMedicijn);
                geregistreerdeMedicijnen++;

                HerinneringWekelijks herinnering = null;

                int dagen = 0;
                switch (keuze) {
                    case 1:
                        System.out.println("Over hoeveel dagen wil je dat de herinnering wordt ingesteld?");
                        if (scanner.hasNextInt()) {
                            dagen = scanner.nextInt();
                            scanner.nextLine();  // Consume newline
                            herinnering = new ReminderSpecifiekeDagen(nieuwMedicijn, innameTijd, dagen);
                            herinnering.stelHerinneringSpecifiekeDagen(LocalDateTime.now().plusDays(dagen));
                        } else {
                            System.out.println("Invalid input. Please enter an integer.");
                            scanner.nextLine();  // Consume newline
                        }
                        break;
                    case 2:
                        herinnering = new ReminderDagelijks(nieuwMedicijn, innameTijd);
                        long wachttijd = herinnering.zetHerinneringMelding();
                        System.out.println("De wachttijd voor de volgende inname is: " + wachttijd / 60000 + " minuten");
                        break;
                    default:
                        System.out.println("Ongeldige keuze. Standaard ingesteld op ReminderSpecifiekeDagen.");
                        herinnering = new ReminderSpecifiekeDagen(nieuwMedicijn, innameTijd, 1);
                        break;
                }

                if (herinnering != null) {
                    herinnering.zetHerinneringMelding();
                }

                //Controleer of het aantal geregistreerde medicijnen gelijk is aan het aantal medicijnen dat de gebruiker wil registreren
                if (geregistreerdeMedicijnen == aantalMedicijnen) {
                    break;
                }
            }

            // Implementeer de MedicijnWijziger klasse
            MedicijnWijziger medicijnWijziger = new MedicijnWijziger();
            medicijnWijziger.bewerkMedicijnen(medicijnLijst);
            medicijnWijziger.wijziging(medicijnLijst);

            medicijnLijst.removeObserver(notificatie);

            return true;
        }
        return false;
    }
}