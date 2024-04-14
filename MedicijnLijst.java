import java.util.ArrayList;

public class MedicijnLijst {
    private final ArrayList<Medicijn> medicijnen = new ArrayList<>();

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

    public Medicijn getMedicijnById(int id) {
        for (Medicijn medicijn : medicijnen) {
            if (medicijn.getId() == id) {
                return medicijn;
            }
        }
        return null;
    }
}
