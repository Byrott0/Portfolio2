
public class WijzigingBevestigd {
    private String message;
    private boolean status;

    public WijzigingBevestigd() {
        this.message = "Medicijnen zijn bevestigd";
        this.status = false;
    }


    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}