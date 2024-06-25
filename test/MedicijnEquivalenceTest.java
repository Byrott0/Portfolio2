import Model.Medicijn;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MedicijnEquivalenceTest {

    @Test
    void testWijzigInnameTijd() {
        // Geldige tijd
        Medicijn medicijn = new Medicijn("TestMedicijn");
        medicijn.wijzigInnameTijd("10:00");
        assertEquals("10:00", medicijn.getInnameTijd());
    }

    @Test
    void testWijzigInnameTijdOngeldigWaarde() {
        // Ongeldige tijd: String niet in het formaat "HH:mm"
        Medicijn medicijn = new Medicijn("TestMedicijn");
        assertThrows(IllegalArgumentException.class, () -> medicijn.wijzigInnameTijd("invalid"));
    }

    @Test
    void testWijzigInnameTijdNull() {
        // Ongeldige tijd: Null
        Medicijn medicijn = new Medicijn("TestMedicijn");
        System.out.println(assertThrows(NullPointerException.class, () -> medicijn.wijzigInnameTijd(null)));
    }

    // Randwaardetests voor wijzigInnameTijd
    @Test
    void testWijzigInnameTijdLimitBeginDag() {
        Medicijn medicijn = new Medicijn("TestMedicijn");
        medicijn.wijzigInnameTijd("00:00");
        assertEquals("00:00", medicijn.getInnameTijd());
    }

    @Test
    void testWijzigInnameTijdLimitEindeDag() {
        Medicijn medicijn = new Medicijn("TestMedicijn");
        medicijn.wijzigInnameTijd("23:59");
        assertEquals("23:59", medicijn.getInnameTijd());
    }

    @Test
    void testWijzigInnameTijdOngeldigTijdBovenLimit() {
        Medicijn medicijn = new Medicijn("TestMedicijn");
        assertThrows(IllegalArgumentException.class, () -> medicijn.wijzigInnameTijd("24:00")); //net boven de grens
        assertThrows(IllegalArgumentException.class, () -> medicijn.wijzigInnameTijd("23:60")); //net boven de grens
    }

    @Test
    void testWijzigInnameTijdgeldigTijdOnderLimit() {
        Medicijn medicijn = new Medicijn("TestMedicijn");
        medicijn.wijzigInnameTijd("00:01");
        assertEquals("00:01", medicijn.getInnameTijd());
        medicijn.wijzigInnameTijd("23:58");
        assertEquals("23:58", medicijn.getInnameTijd());
    }
}
