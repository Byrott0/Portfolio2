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
            Medicijn nieuwMedicijn = medicijnController.maakMedicijn(medicijnNaam);

            System.out.println("Kies een type herinnering:");
            System.out.println("1. ReminderSpecifiekeDagen");
            System.out.println("2. ReminderDagelijks");
            int keuze = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Voer de inname tijd in (HH:mm formaat)");
            String innameTijd = scanner.nextLine();

            nieuwMedicijn.wijzigInnameTijd(innameTijd);
            medicijnLijst.addMedicijn(nieuwMedicijn);

            reminderInstellen(keuze, nieuwMedicijn, innameTijd, scanner);
        }
    }

    public void reminderInstellen(int keuze, Medicijn nieuwMedicijn, String innameTijd, Scanner scanner){
        HerinneringWekelijks herinnering = null;

        switch (keuze) {
            case 1:
                System.out.println("Over hoeveel dagen wil je dat de herinnering wordt ingesteld?");
                if (scanner.hasNextInt()) {
                    int dagen = scanner.nextInt();
                    scanner.nextLine();
                    herinnering = new ReminderSpecifiekeDagen(nieuwMedicijn, innameTijd, dagen);
                } else {
                    System.out.println("Ongeldig. Voer een geldig cijfer in");
                    scanner.nextLine();
                }
                break;
            case 2:
                herinnering = new ReminderDagelijks(nieuwMedicijn, innameTijd);
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