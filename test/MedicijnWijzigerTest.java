import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MedicijnWijzigerTest {

    private MedicijnWijziger wijziger;
    private MedicijnLijst medicijnLijst;

    @BeforeEach
    void setUp() {
        wijziger = new MedicijnWijziger();
        medicijnLijst = new MedicijnLijst();
        wijziger.setMedicijnLijst(medicijnLijst);

        Medicijn medicijn = new Medicijn("Test Medicijn");
        medicijnLijst.addMedicijn(medicijn);
    }

    @Test
    void testWijzigMedicijn_AllTrue() {
        String input = "ja\n"
                + medicijnLijst.getMedicijnen().get(0).getId() + "\n"
                + "ja\n"
                + "NieuweNaam\n"
                + "10:00\n"
                + "nee\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        assertTrue(wijziger.WijzigMedicijn(scanner));
    }


    @Test
    void testWijzigMedicijn_AFalse() {
        String input = "nee\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        assertFalse(wijziger.WijzigMedicijn(scanner));
    }

    @Test
    void testWijzigMedicijn_BFalse() {
        String input = "ja\0\nee\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        assertFalse(wijziger.WijzigMedicijn(scanner));
    }

    @Test
    void testWijzigMedicijn_CFalse() {
        String input = "ja" + medicijnLijst.getMedicijnen().get(0).getId() + "\nnee\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        assertFalse(wijziger.WijzigMedicijn(scanner));
    }
}