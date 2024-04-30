import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Medicijn {
    private String naam;
    private String innameTijd;
    private final long innameTijdVerschil;
    private final int id;

    public Medicijn(String naam, String innameTijd) {
        this.naam = naam;
        this.innameTijd = innameTijd;
        this.id = UUID.randomUUID().hashCode();
        //dit genereert een unieke id voor elk medicijn
        //en hashcode is een unieke waarde voor elk object wat in int teruggegeven wordt

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime innameTijdLocal = LocalTime.parse(innameTijd, formatter);
        LocalTime now = LocalTime.now();
        //datetimeformatter zorgt ervoor dat de tijd in het juiste formaat wordt geprint
        //localtime.parse zorgt ervoor dat de tijd in het juiste formaat wordt geprint
        //localtime.now zorgt ervoor dat de tijd van nu wordt geprint

        int innameTijdSeconds = innameTijdLocal.toSecondOfDay() / 60;
        int nowMinutes = now.toSecondOfDay()/ 60;
        long tijdVerschil = innameTijdSeconds - nowMinutes;
        //verschil in seconden tussen de inname tijd en de huidige tijd

        // Als de tijd negatief is, voeg dan 24 uur (in seconden) toe om de tijd correct in te stellen voor de volgende dag.
        if (tijdVerschil < 0) {
            tijdVerschil += 24 * 60;
        }

        this.innameTijdVerschil = tijdVerschil * 1000L; //L staat voor long type
        System.out.println("Ik ga wachten in minuten: " + innameTijdVerschil /1000L);
    }


    public String getNaam() {
        return naam;
    }

    public String getInnameTijd() {
        return innameTijd;
    }

    public long getInnameTijdVerschilMS() {
        return innameTijdVerschil;
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
