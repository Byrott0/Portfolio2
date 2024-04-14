import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Medicijn {
    private String naam;
    private String innameTijd;
    private final long innameTijdVerschilMS;
    private final int id;

    public Medicijn(String naam, String innameTijd) {
        this.naam = naam;
        this.innameTijd = innameTijd;
        this.id = UUID.randomUUID().hashCode();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime innameTijdLocal = LocalTime.parse(innameTijd, formatter);
        LocalTime now = LocalTime.now();

        int innameTijdSeconds = innameTijdLocal.toSecondOfDay();
        int nowSeconds = now.toSecondOfDay();
        long tijdVerschil = innameTijdSeconds - nowSeconds;

        // Als de tijd negatief is, voeg dan 24 uur (in seconden) toe om de tijd correct in te stellen voor de volgende dag.
        if (tijdVerschil < 0) {
            tijdVerschil += 24 * 3600;
        }

        this.innameTijdVerschilMS = tijdVerschil * 1000L;
        System.out.println("Ik ga wachten in seconden: " + innameTijdVerschilMS / 1000L);
    }


    public String getNaam() {
        return naam;
    }

    public String getInnameTijd() {
        return innameTijd;
    }

    public long getInnameTijdVerschilMS() {
        return innameTijdVerschilMS;
    }

    public int getId() {
        return id;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setInnameTijd(String innameTijd) {
        this.innameTijd = innameTijd;
    }

}