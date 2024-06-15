import java.util.List;

public class VeranderingNotificatie implements Observer {

    private String bericht;

    @Override
    public void update(List<Medicijn> medicijnen) {
        if (!medicijnen.isEmpty()) {
            bericht = "Medicijnlijst is gewijzigd: " + medicijnen;
            toonBericht();
        }
    }

    private void toonBericht() {
        System.out.println("Melding: " + bericht);
    }
}
