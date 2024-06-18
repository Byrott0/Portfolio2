import java.util.UUID;

public class Gebruiker {
    private String naam;
    private String wachtwoord;
    private int id;

    public Gebruiker(String naam, String wachtwoord) {
        this.naam = naam;
        this.wachtwoord = wachtwoord;
        this.id = UUID.randomUUID().hashCode();
    }
}