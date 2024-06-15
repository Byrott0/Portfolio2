import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ReminderDagelijks extends HerinneringWekelijks {
    private String innameTijd;
    private long innameTijdVerschil;

    public ReminderDagelijks(Medicijn medicijn, String innameTijd) {
        super(medicijn);
        this.innameTijd = innameTijd;
        berekenInnameTijdVerschil();
    }

    private void berekenInnameTijdVerschil() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime innameTijdLocal = LocalTime.parse(innameTijd, formatter);
        LocalTime now = LocalTime.now();

        int innameTijdMinutes = innameTijdLocal.toSecondOfDay() / 60;
        int nowMinutes = now.toSecondOfDay() / 60;
        long tijdVerschil = innameTijdMinutes - nowMinutes;

        if (tijdVerschil < 0) {
            tijdVerschil += 24 * 60;
        }

        this.innameTijdVerschil = tijdVerschil * 1000L;
    }

    @Override
    boolean bepaalinnameTijd() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime innameTijdLocal = LocalTime.parse(innameTijd, formatter);
        LocalTime now = LocalTime.now();

        // Compare the current time with the intake time
        if (now.isAfter(innameTijdLocal)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    void registreerMelding(LocalDateTime volgendeInname) {
        System.out.println("Het is tijd om " + medicijn.getNaam() + " in te nemen");
    } // fix medicijn.getnaam om de juiste naam te weergeven

    @Override
    public long zetHerinneringMelding() {
        return innameTijdVerschil;
    }

    @Override
    void stelHerinneringSpecifiekeDagen(LocalDateTime volgendeInname) {
        // Deze methode is niet relevant voor deze klasse
    }
}