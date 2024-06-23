package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Medicijn {
    private String naam;
    private final int id;
    private String innameTijd;

    public Medicijn(String naam) {
        setNaam(naam);
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
        if (naam == null) {
            throw new NullPointerException("naam mag niet null zijn.");
        }
        if (naam.isEmpty()) {
            throw new IllegalArgumentException("naam mag niet leeg zijn.");
        }
        if (naam.length() > 255) {
            throw new IllegalArgumentException("naam mag niet langer zijn dan 255 tekens.");
        }
        this.naam = naam;
    }

    public String getInnameTijd() {
        return innameTijd;
    }

    public void wijzigInnameTijd(String innameTijd) {
        if (innameTijd == null) {
            throw new NullPointerException("Innametijd mag niet null zijn.");
        }
        if (!innameTijd.matches("([01]\\d|2[0-3]):([0-5]\\d)")) {
            throw new IllegalArgumentException("Ongeldig tijdsformaat. Gebruik HH:mm.");
        }
        this.innameTijd = innameTijd;
        System.out.println("Innametijd gewijzigd naar: " + innameTijd);
    }


    @Override
    public String toString() {
        return "Model.Medicijn " +
                "naam='" + naam + '\'' +
                ", id=" + id +
                ", innameTijd='" + innameTijd + '\'' +
                ' ';
    }
}
