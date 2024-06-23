package Utils;

import Model.Medicijn;
import Model.MedicijnLijst;
import java.util.Scanner;

public class MedicijnWijziger {

    private MedicijnLijst medicijnLijst;
    private WijzigingBevestigd wijzigingBevestigd = new WijzigingBevestigd();

    public boolean WijzigMedicijn(Scanner scanner) {
        System.out.println("Wil je nog iets wijzigen voordat ik de reminders aanmaak? (ja/nee)");
        if (scanner.nextLine().equalsIgnoreCase("ja")) {
            while (true) {
                printAlleMedicijnen();
                System.out.println("Voer het ID in van de medicijn waarvan je de naam en tijd wilt wijzigen.");
                int id = scanner.nextInt();
                scanner.nextLine();

                Medicijn medicijn = medicijnLijst.getMedicijnById(id);
                if (medicijn == null) {
                    System.out.println("Medicijn niet gevonden");
                } else {
                    System.out.println("Weet u zeker dat u een medicijn wilt wijzigen?");
                    if (scanner.nextLine().equalsIgnoreCase("ja")) {
                        wijzigAttributenMedicijn(medicijn, scanner);
                        System.out.println("Wilt u nog een medicijn wijzigen? (ja/nee)");
                    }
                    if (scanner.nextLine().equalsIgnoreCase("nee")) {
                        break;
                    }
                }
            }
            return true;
        } else {
            System.out.println(wijzigingBevestigd.getMessage());
        }
        return false;
    }

    public void printAlleMedicijnen() {
        for (Medicijn medicijn : medicijnLijst.getMedicijnen()) {
            System.out.println("ID: " + medicijn.getId() + " Naam: " + medicijn.getNaam() + " Innametijd: " + medicijn.getInnameTijd());
        }
    }

    public void wijzigAttributenMedicijn(Medicijn medicijn, Scanner scanner) {
        System.out.println("Medicijn gevonden: " + medicijn.getNaam());
        System.out.println("Voer de nieuwe naam in");
        String naam = scanner.nextLine();
        System.out.println("Voer de nieuwe inname tijd in (HH:mm)");
        String innameTijd = scanner.nextLine();
        medicijn.setNaam(naam);
        medicijn.wijzigInnameTijd(innameTijd);
        medicijnLijst.notifyObservers();
    }

    public void setMedicijnLijst(MedicijnLijst medicijnLijst) {
        this.medicijnLijst = medicijnLijst;
    }
}