package Utils;

import Model.Medicijn;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ReminderSpecifiekeDagen extends HerinneringWekelijks {
    private final int dagen;

    public ReminderSpecifiekeDagen(Medicijn medicijn, String innameTijd, int dagen) {
        super(medicijn);
        this.innameTijd = innameTijd;
        this.dagen = dagen;
    }

    @Override
    protected void keuzeBevestiging() {
        System.out.println("ReminderSpecifiekeDagen ingesteld");
    }

    @Override
    protected LocalDateTime bepaalVolgendeInnameTijd(LocalDateTime nu) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime innameTijdLocal = LocalTime.parse(innameTijd, formatter);
        return nu.with(innameTijdLocal).plusDays(dagen);
    }

    @Override
    protected void registreerMelding(LocalDateTime volgendeInname) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 'Tijd =' HH:mm");
        String formatDateTime = volgendeInname.format(formatter);
        System.out.println("Het is tijd om " + medicijn.getNaam() + " in te nemen op " + formatDateTime);
    }

}

