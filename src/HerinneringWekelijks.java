import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class HerinneringWekelijks {
    protected Medicijn medicijn;

    public HerinneringWekelijks(Medicijn medicijn) {
        this.medicijn = medicijn;
    }

    public long zetHerinneringMelding() {
        LocalDateTime nu = LocalDateTime.now();
        LocalDateTime volgendeInname = bepaalVolgendeInnameTijd(nu);
        registreerMelding(volgendeInname);
        return ChronoUnit.MILLIS.between(nu, volgendeInname);
    }

    protected abstract LocalDateTime bepaalVolgendeInnameTijd(LocalDateTime nu);

    protected abstract void registreerMelding(LocalDateTime volgendeInname);

    public abstract void stelHerinneringSpecifiekeDagen(LocalDateTime volgendeInname);
}
