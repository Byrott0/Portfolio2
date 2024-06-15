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
    protected LocalDateTime bepaalVolgendeInnameTijd(LocalDateTime nu) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime innameTijdLocal = LocalTime.parse(innameTijd, formatter);
        return nu.with(innameTijdLocal).plusDays(dagen);
    }

    @Override
    protected void registreerMelding(LocalDateTime volgendeInname) {
        System.out.println("Het is tijd om " + medicijn.getNaam() + " in te nemen op " + volgendeInname);
    }

    @Override
    public void stelHerinneringSpecifiekeDagen(LocalDateTime volgendeInname) {
        System.out.println("Ik ga wachten in dagen: " + dagen);
    }
}
