import java.util.ArrayList;
import java.util.List;


public class MedicijnController {
    private final List<Medicijn> medicijnen;

    public MedicijnController() {
        this.medicijnen = new ArrayList<>();
    }

    public Medicijn maakMedicijn(String naam) {
        Medicijn medicijn = new Medicijn(naam + (this.medicijnen.size() + 1));
        this.medicijnen.add(medicijn);
        return medicijn;
    }

    public List<Medicijn> getMedicijnen() {
        return this.medicijnen;
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
