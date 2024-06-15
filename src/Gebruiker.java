import java.util.UUID;

public class Gebruiker {
    private String naam;
    private String wachtwoord;
    private int id;

    public Gebruiker() {
        this.naam = naam;
        this.wachtwoord = wachtwoord;
        this.id = UUID.randomUUID().hashCode();
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public int getId() {
        return id;
    }

    public void register(String naam, String wachtwoord) {
        this.naam = naam;
        this.wachtwoord = wachtwoord;
        this.id = UUID.randomUUID().hashCode();
    }
}