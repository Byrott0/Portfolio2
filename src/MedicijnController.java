import java.util.ArrayList;
import java.util.List;

public class MedicijnController {
    private final List<Medicijn> medicijnen;

    public MedicijnController() {
        this.medicijnen = new ArrayList<>();
    }

    public Medicijn maakMedicijn(String naam) {
        Medicijn medicijn = new Medicijn(naam);
        this.medicijnen.add(medicijn);
        return medicijn;
    }

}