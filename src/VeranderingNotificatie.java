import java.util.List;

public class VeranderingNotificatie implements Observer {

    private String bericht;


    private void toonBericht() {
        System.out.println("VeranderingNotificatie: " + bericht);
    }

    @Override
    public void update(List<Medicijn> medicijnen) {

    }

    // Eventueel meer methodes om de notificatie te verwerken, bijvoorbeeld opslaan in een logboek of een GUI-update.
}
