import java.util.ArrayList;

public class MedicijnLijst { // dit staat voor de class medicijn

    private final ArrayList<Medicijn> medicijnen = new ArrayList<>(); //final variabel doet dat je de variabel niet kan veranderen
    // en in een arraylist zorgt het ervoor dat je niet kan verwijzen naar een andere lijst maar juist specifiek naar deze

    public void addMedicijn(Medicijn medicijn) {
        medicijnen.add(medicijn);
    }

    public void removeMedicijn(int id) {
        for (Medicijn medicijn : medicijnen) {
            if (medicijn.getId() == id) {
                medicijnen.remove(medicijn);
                return;
            }
        }
    }

    public ArrayList<Medicijn> getMedicijnen() {
        return medicijnen;
    }
    //dit is een getter die de medicijnen teruggeeft

//een specifiek Medicijn object te vinden en terug te geven op basis van zijn id.
    public Medicijn getMedicijnById(int id) {
        for (Medicijn medicijn : medicijnen) {
            if (medicijn.getId() == id) {
                return medicijn;
            }
        }
        return null;
    }
}
