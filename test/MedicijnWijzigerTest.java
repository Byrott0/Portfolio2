import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

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

        // Adding a default Medicijn object to the list for testing
        Medicijn medicijn = new Medicijn("Test Medicijn");
        medicijnLijst.addMedicijn(medicijn);
    }

    @Test
    void testWijzigMedicijn_AllTrue() {
        String input = "ja\n1\nja\nnee\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertTrue(wijziger.WijzigMedicijn());
    }

    @Test
    void testWijzigMedicijn_AFalse() {
        String input = "nee\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertFalse(wijziger.WijzigMedicijn());
    }

    @Test
    void testWijzigMedicijn_BFalse() {
        String input = "ja\n2\nnee\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertFalse(wijziger.WijzigMedicijn());
    }

    @Test
    void testWijzigMedicijn_CFalse() {
        String input = "ja\n1\nnee\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertFalse(wijziger.WijzigMedicijn());
    }
}
