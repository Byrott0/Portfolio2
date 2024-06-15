import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class HerinneringWekelijks {
    protected Medicijn medicijn;

    public HerinneringWekelijks(Medicijn medicijn) {
        this.medicijn = medicijn;
    }

    public long zetHerinneringMelding() {
        LocalDateTime nu = LocalDateTime.now();
        // Voor herinneringen op specifieke dagen, moet je de volgende inname tijd berekenen op basis van de specifieke dagen
        LocalDateTime volgendeInname = nu.plusDays(1); // Bereken de volgende inname tijd
        return ChronoUnit.MILLIS.between(nu, volgendeInname);
    }

    abstract boolean bepaalinnameTijd();

    abstract void registreerMelding(LocalDateTime volgendeInname);

    abstract void stelHerinneringSpecifiekeDagen(LocalDateTime volgendeInname);
}