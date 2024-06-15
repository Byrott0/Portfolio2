import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MedicijnController {
    private final List<Medicijn> medicijnen;

    public MedicijnController() {
        this.medicijnen = new ArrayList<>();
    }

    public Medicijn maakMedicijn() {
        Scanner scanner = new Scanner(System.in);
        String naam = scanner.nextLine() + (this.medicijnen.size() + 1);
        Medicijn medicijn = new Medicijn(naam);
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