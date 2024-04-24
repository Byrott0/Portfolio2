import java.util.Comparator;
import java.util.Scanner;

public class Main implements Wijziginginterface{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MedicijnLijst medicijnLijst = new MedicijnLijst();

        Main main = new Main();

        boolean isLoggedIn = main.login();
        if (!isLoggedIn) {
            return;
        }

        System.out.println("Hoeveel medicijnen wil je registreren?");
        int aantalMedicijnen = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < aantalMedicijnen; i++) {
            System.out.println("Voer de naam van het medicijn in");
            String naam = scanner.nextLine();
            System.out.println("Voer de inname tijd van het medicijn in (HH:mm)");
            String innameTijd = scanner.nextLine();
            Medicijn medicijn = new Medicijn(naam, innameTijd);
            medicijnLijst.addMedicijn(medicijn);
        }
         {


        //Medicijnen editen
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
                medicijn.setInnameTijd(innameTijd);
                medicijnLijst.removeMedicijn(id);
                medicijnLijst.addMedicijn(medicijn);


                System.out.println("Wilt u nog een medicijn wijzigen? (ja/nee)");
                if (scanner.nextLine().equals("nee")) {
                    break;
                }
                }


            }

        }

        // Dit sorteert onze medicijnen op basis van de inname tijd.
        medicijnLijst.getMedicijnen().sort(Comparator.comparingLong(Medicijn::getInnameTijdVerschilMS));

        Bevestiging bevestiging = new Bevestiging();

        bevestiging.wijziging(medicijnLijst);
        bevestiging.bevestigRegistratie();

        System.out.println(bevestiging.getMessage());
        System.out.println(bevestiging.isStatus());

        // Geef overzicht van alle medicijnen
        for (Medicijn medicijn : medicijnLijst.getMedicijnen()) {
            System.out.println("ID: " + medicijn.getId() + " Naam: " + medicijn.getNaam() + " Innametijd: " + medicijn.getInnameTijd());
        }

        for (Medicijn i : medicijnLijst.getMedicijnen()) {
            Reminder reminder = new Reminder(i);
            reminder.remind();
        }


    }

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
                return true;
            }
            return false;


    }

    @Override
    public void wijzigMedicijn(MedicijnLijst medicijnLijst) {

    }
}


