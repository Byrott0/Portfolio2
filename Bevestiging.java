
public class Bevestiging {
    private String message;
    private boolean status;

    public Bevestiging() {
        this.message = "Medicijnen zijn bevestigd";
        this.status = false;
    }

    public void bevestigRegistratie() {
        this.status = true;
        System.out.println("Het programma is klaar");
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}