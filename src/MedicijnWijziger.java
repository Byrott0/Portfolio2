import java.util.Scanner;

public class MedicijnWijziger {

    private Wijzigmedicijn medicijnWijziger;

    public void wijziging(MedicijnLijst medicijnLijst) {
        // De rest van je code...
    }

    public void bewerkMedicijnen(MedicijnLijst medicijnLijst) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wil je nog iets editen voordat ik de reminders aanmaak? (ja/nee)");
        if (scanner.nextLine().equals("ja")) {
            while (true) {

                // Geef overzicht van alle medicijnen
                for (Medicijn medicijn : medicijnLijst.getMedicijnen()) {
                    System.out.println("ID: " + medicijn.getId() + " Naam: " + medicijn.getNaam() + " Innametijd: " + medicijn.getInnameTijd());
                }

                System.out.println("Voer het ID in van de medicijn waarvan je de naam en tijd wilt wijzigen");
                int id = scanner.nextInt();
                scanner.nextLine();
                Medicijn medicijn = medicijnLijst.getMedicijnById(id);
                if (medicijn == null) {
                    System.out.println("Medicijn niet gevonden");
                    return;
                }
                System.out.println("Voer de nieuwe naam in");
                String naam = scanner.nextLine();
                System.out.println("Voer de nieuwe inname tijd in (HH:mm)");
                String innameTijd = scanner.nextLine();
                medicijn.setNaam(naam);
                medicijn.wijzigInnameTijd(innameTijd); // Hernoem de methode hier
                medicijnLijst.removeMedicijn(id);
                medicijnLijst.addMedicijn(medicijn);

                System.out.println("Wilt u nog een medicijn wijzigen? (ja/nee)");
                if (scanner.nextLine().equals("nee")) {
                    break;
                }
            }
        }
    }
}