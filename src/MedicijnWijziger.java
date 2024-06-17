import java.util.Scanner;

public class MedicijnWijziger {

    public boolean wijzigMedicijn(MedicijnLijst medicijnLijst) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wil je nog iets wijzigen voordat ik de reminders aanmaak? (ja/nee)");
        if (scanner.nextLine().equalsIgnoreCase("ja")) {
            while (true) {
                // Geef overzicht van alle medicijnen
                for (Medicijn medicijn : medicijnLijst.getMedicijnen()) {
                    System.out.println("ID: " + medicijn.getId() + " Naam: " + medicijn.getNaam() + " Innametijd: " + medicijn.getInnameTijd());
                }

                System.out.println("Voer het ID in van de medicijn waarvan je de naam en tijd wilt wijzigen," +
                        " of voer -1 in om een medicijn te verwijderen");
                int id = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                if (id == -1) {
                    System.out.println("Voer het ID in van het medicijn dat je wilt verwijderen");
                    id = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    medicijnLijst.removeMedicijn(id);
                    System.out.println("Medicijn verwijderd");
                    continue;
                }

                Medicijn medicijn = medicijnLijst.getMedicijnById(id);
                if (medicijn == null) {
                    System.out.println("Medicijn niet gevonden");
                } else {
                    System.out.println("Medicijn gevonden: " + medicijn.getNaam());

                    System.out.println("Voer de nieuwe naam in");
                    String naam = scanner.nextLine();
                    System.out.println("Voer de nieuwe inname tijd in (HH:mm)");
                    String innameTijd = scanner.nextLine();
                    medicijn.setNaam(naam);
                    medicijn.wijzigInnameTijd(innameTijd);

                    // vermeld de observers na wijzigingen
                    medicijnLijst.notifyObservers();

                    System.out.println("Wilt u nog een medicijn wijzigen? (ja/nee)");
                    if (scanner.nextLine().equalsIgnoreCase("nee")) {
                        break;
                    }
                }
            }
            return true;
        }
        return false;
    }
}