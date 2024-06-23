package Utils;

import Model.Medicijn;
import Model.Observer;
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

    public void notifyChange(String text) {
        System.out.println("Melding: " + text);
    }
}
