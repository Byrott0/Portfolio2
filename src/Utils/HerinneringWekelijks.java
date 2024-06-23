package Utils;

import Model.Medicijn;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class HerinneringWekelijks {
    protected Medicijn medicijn;
    protected String innameTijd;

    public HerinneringWekelijks(Medicijn medicijn) {
        this.medicijn = medicijn;
    }

    public final void zetHerinneringMelding() {
        keuzeBevestiging();
        LocalDateTime nu = LocalDateTime.now();
        LocalDateTime volgendeInname = bepaalVolgendeInnameTijd(nu);
        registreerMelding(volgendeInname);
        System.out.println("De wachttijd voor de volgende inname is: " + (ChronoUnit.MILLIS.between(nu, volgendeInname)) / 60000 + " minuten");
    }

    protected abstract void keuzeBevestiging();

    protected abstract LocalDateTime bepaalVolgendeInnameTijd(LocalDateTime nu);

    protected abstract void registreerMelding(LocalDateTime volgendeInname);

}
