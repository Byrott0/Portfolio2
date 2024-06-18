import java.util.ArrayList;
import java.util.List;

public class MedicijnController {
    private final List<Medicijn> medicijnen;
    String medicijnNaam;

    public MedicijnController() {
        this.medicijnen = new ArrayList<>();
    }

    public Medicijn maakMedicijn(String naam) {
        Medicijn medicijn = new Medicijn(naam);
        this.medicijnNaam = medicijn.getNaam();
        this.medicijnen.add(medicijn);
        return medicijn;
    }
}