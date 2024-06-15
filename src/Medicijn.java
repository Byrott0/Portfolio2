import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Medicijn {
    private String naam;
    private final int id;
    private String innameTijd;

    public Medicijn(String naam) {
        this.naam = naam;
        this.id = UUID.randomUUID().hashCode();
        this.innameTijd = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
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
        return innameTijd;
    }

    public void wijzigInnameTijd(String innameTijd) {
        this.innameTijd = innameTijd;
        System.out.println("Innametijd gewijzigd naar: " + innameTijd);
    }

    @Override
    public String toString() {
        return " Medicijn " +
                "naam='" + naam + '\'' +
                ", id=" + id +
                ", innameTijd='" + innameTijd + '\'' +
                ' ';
    }
}
