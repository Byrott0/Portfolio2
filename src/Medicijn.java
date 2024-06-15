import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Medicijn {
    private String naam;
    private final int id;

    public Medicijn(String naam) {
        this.naam = naam;
        this.id = UUID.randomUUID().hashCode();
    }

    public String getNaam() {
        return naam;
    }

    public int getId() {
        return id;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getInnameTijd() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public void wijzigInnameTijd(String innameTijd) {
        System.out.println("Innametijd gewijzigd naar: " + innameTijd);
    }
}