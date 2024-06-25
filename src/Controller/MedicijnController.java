package Controller;
import Model.*;
import Utils.HerinneringWekelijks;
import Utils.ReminderDagelijks;
import Utils.ReminderSpecifiekeDagen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MedicijnController {
    private final List<Medicijn> medicijnen;
    private MedicijnLijst medicijnLijst;

    public MedicijnController(MedicijnLijst medicijnLijst) {
        this.medicijnen = new ArrayList<>();
        this.medicijnLijst = medicijnLijst;
    }

    public Medicijn maakMedicijn(String naam) {
        Medicijn medicijn = new Medicijn(naam);
        this.medicijnen.add(medicijn);
        return medicijn;
    }

    public void voegMedicijnenToe() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hoeveel medicijnen wil je registreren?");
        int aantalMedicijnen = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < aantalMedicijnen; i++) {
            System.out.println("Voer de naam van het medicijn in:");
            String medicijnNaam = scanner.nextLine();
            Medicijn nieuwMedicijn = maakMedicijn(medicijnNaam);

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

    private void verschillendeDagenInstellen(Scanner scanner, Medicijn nieuwMedicijn, String innameTijd) {
        System.out.println("Over hoeveel dagen wil je dat de herinnering wordt ingesteld?");
        if (scanner.hasNextInt()) {
            int dagen = scanner.nextInt();
            scanner.nextLine();
            HerinneringWekelijks herinnering = new ReminderSpecifiekeDagen(nieuwMedicijn, innameTijd, dagen);
            herinnering.zetHerinneringMelding();
        } else {
            System.out.println("Ongeldig. Voer een geldig cijfer in");
            scanner.nextLine();
        }
    }

    public void reminderInstellen(int keuze, Medicijn nieuwMedicijn, String innameTijd, Scanner scanner) {
        HerinneringWekelijks herinnering = null;

        switch (keuze) {
            case 1:
                verschillendeDagenInstellen(scanner, nieuwMedicijn, innameTijd);
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