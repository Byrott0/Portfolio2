import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ReminderDagelijks extends HerinneringWekelijks {
    private String innameTijd;

    public ReminderDagelijks(Medicijn medicijn, String innameTijd) {
        super(medicijn);
        this.innameTijd = innameTijd;
    }

    @Override
    protected LocalDateTime bepaalVolgendeInnameTijd(LocalDateTime nu) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime innameTijdLocal = LocalTime.parse(innameTijd, formatter);
        LocalDateTime volgendeInname = nu.with(innameTijdLocal);
        if (nu.isAfter(volgendeInname)) {
            volgendeInname = volgendeInname.plusDays(1);
        }
        return volgendeInname;
    }

    @Override
    protected void registreerMelding(LocalDateTime volgendeInname) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 'Tijd =' HH:mm");
        String formatDateTime = volgendeInname.format(formatter);
        System.out.println("Het is tijd om " + medicijn.getNaam() + " in te nemen op " + formatDateTime);
    }

    @Override
    public void stelHerinneringSpecifiekeDagen(LocalDateTime volgendeInname) {
        // Deze methode is niet relevant voor deze klasse
    }
}
