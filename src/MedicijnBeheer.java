import java.time.LocalDateTime;
import java.util.Scanner;

public class MedicijnBeheer {
    private MedicijnLijst medicijnLijst;
    private MedicijnController medicijnController;

    public MedicijnBeheer(MedicijnLijst medicijnLijst, MedicijnController medicijnController) {
        this.medicijnLijst = medicijnLijst;
        this.medicijnController = medicijnController;
    }

    public void voegMedicijnenToe() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hoeveel medicijnen wil je registreren?");
        int aantalMedicijnen = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < aantalMedicijnen; i++) {
            System.out.println("Voer de naam van het medicijn in:");
            String medicijnNaam = scanner.nextLine();

            System.out.println("Kies een type herinnering:");
            System.out.println("1. ReminderSpecifiekeDagen");
            System.out.println("2. ReminderDagelijks");
            int keuze = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Voer de inname tijd in (HH:mm formaat)");
            String innameTijd = scanner.nextLine();

            Medicijn nieuwMedicijn = medicijnController.maakMedicijn(medicijnNaam);
            nieuwMedicijn.wijzigInnameTijd(innameTijd);
            medicijnLijst.addMedicijn(nieuwMedicijn);

            HerinneringWekelijks herinnering = null;

            switch (keuze) {
                case 1:
                    System.out.println("Over hoeveel dagen wil je dat de herinnering wordt ingesteld?");
                    if (scanner.hasNextInt()) {
                        int dagen = scanner.nextInt();
                        scanner.nextLine();
                        herinnering = new ReminderSpecifiekeDagen(nieuwMedicijn, innameTijd, dagen);
                        herinnering.stelHerinneringSpecifiekeDagen(LocalDateTime.now().plusDays(dagen));
                    } else {
                        System.out.println("Ongeldig. Voer een geldig cijfer in");
                        scanner.nextLine();
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
        }
    }
}