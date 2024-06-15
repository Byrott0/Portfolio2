import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ControlMenu implements Observer {
    private MedicijnWijziger medicijnWijziger;
    private WijzigingBevestigd wijzigingBevestigd;
    private VeranderingNotificatie veranderingNotificatie;
    private Set<Integer> gemeldeMedicijnen; // Set om bij te houden welke medicijnen al zijn gemeld

    public ControlMenu() {
        this.medicijnWijziger = new MedicijnWijziger();
        this.wijzigingBevestigd = new WijzigingBevestigd();
        this.veranderingNotificatie = new VeranderingNotificatie();
        this.gemeldeMedicijnen = new HashSet<>();
    }

    public boolean login() {
        Gebruiker gebruiker = new Gebruiker();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wil je een account aanmaken? (ja/nee)");
        if (scanner.nextLine().equalsIgnoreCase("ja")) {
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
            medicijnLijst.addObserver(veranderingNotificatie);

            int geregistreerdeMedicijnen = 0;
            while (geregistreerdeMedicijnen < aantalMedicijnen) {
                System.out.println("Voer de naam van het medicijn in:");
                String medicijnNaam = scanner.nextLine();

                System.out.println("Kies een type herinnering:");
                System.out.println("1. ReminderSpecifiekeDagen");
                System.out.println("2. ReminderDagelijks");
                int keuze = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                System.out.println("Voer de inname tijd in (HH:mm formaat)");
                String innameTijd = scanner.nextLine();

                Medicijn nieuwMedicijn = new Medicijn(medicijnNaam);
                nieuwMedicijn.wijzigInnameTijd(innameTijd);
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

                // Controleer of het aantal geregistreerde medicijnen gelijk is aan het aantal medicijnen dat de gebruiker wil registreren
                if (geregistreerdeMedicijnen == aantalMedicijnen) {
                    break;
                }
            }

            // Voeg observer toe voor wijzigingen na de initiële toevoeging
            medicijnLijst.addObserver(this);

            // Wijzig medicijnen en notify observers als er wijzigingen zijn aangebracht
            if (medicijnWijziger.wijzigMedicijn(medicijnLijst)) {
                wijzigingBevestigd.setStatus(true);
                System.out.println(wijzigingBevestigd.getMessage());
            } else {
                // Toon wijzigingsbevestiging als er geen wijzigingen zijn aangebracht
                System.out.println(wijzigingBevestigd.getMessage());
            }

            medicijnLijst.removeObserver(this);
            return true;
        }
        return false;
    }

    @Override
    public void update(List<Medicijn> medicijnen) {
        System.out.println("Medicijnlijst is gewijzigd: " + medicijnen);
    }

    // Methode om te controleren of een medicijn al gemeld is
    private boolean isMedicijnGemeld(Medicijn medicijn) {
        return gemeldeMedicijnen.contains(medicijn.getId());
    }

    // Methode om een medicijn als gemeld te markeren
    private void markeerMedicijnAlsGemeld(Medicijn medicijn) {
        gemeldeMedicijnen.add(medicijn.getId());
    }
}
