import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ReminderSpecifiekeDagen extends HerinneringWekelijks {
    private String innameTijd;
    private int dagen;

    public ReminderSpecifiekeDagen(Medicijn medicijn, String innameTijd, int dagen) {
        super(medicijn);
        this.innameTijd = innameTijd;
        this.dagen = dagen;
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
    }

    @Override
    public long zetHerinneringMelding() {
        return dagen * 24 * 60 * 60 * 1000L;
    }

    @Override
    void stelHerinneringSpecifiekeDagen(LocalDateTime volgendeInname) {

        System.out.println("Ik ga wachten in dagen: " + dagen);
    }
}