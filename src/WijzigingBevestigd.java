public class WijzigingBevestigd {
    private final String message;
    private boolean status;

    public WijzigingBevestigd() {
        this.message = "Medicijnen zijn bevestigd";
        this.status = false;
    }

    public String getMessage() {
        return message;
    }


    public void setStatus(boolean status) {
        this.status = status;
    }
}