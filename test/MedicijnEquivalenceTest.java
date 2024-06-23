import Model.Medicijn;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MedicijnEquivalenceTest {

//    @Test
//    void testSetNaam() {
//        // Geldige naam: Niet-lege string
//        Medicijn medicijn = new Medicijn("TestMedicijn");
//        medicijn.setNaam("NieuweNaam");
//        assertEquals("NieuweNaam", medicijn.getNaam());
//    }
//
//    @Test
//    void testSetNaamLegeString() {
//        // Ongeldige naam: Lege string
//        Medicijn medicijn = new Medicijn("TestMedicijn");
//        assertThrows(IllegalArgumentException.class, () -> medicijn.setNaam(""));
//    }
//
//    @Test
//    void testSetNaamNull() {
//        // Ongeldige naam: Null
//        Medicijn medicijn = new Medicijn("TestMedicijn");
//        assertThrows(NullPointerException.class, () -> medicijn.setNaam(null));
//    }
//
//    // Randwaardetests voor setNaam
//    @Test
//    void testSetNaamMaxLength() {
//        Medicijn medicijn = new Medicijn("TestMedicijn");
//        String longName = "a".repeat(255);  // Op de grens
//        medicijn.setNaam(longName);
//        assertEquals(longName, medicijn.getNaam());
//    }
//
//    @Test
//    void testSetNaamAboveMaxLength() {
//        Medicijn medicijn = new Medicijn("TestMedicijn");
//        String longName = "a".repeat(256);  // Net boven de grens
//        assertThrows(IllegalArgumentException.class, () -> medicijn.setNaam(longName));
//    }
//
//    @Test
//    void testSetNaamBelowMaxLength() {
//        Medicijn medicijn = new Medicijn("TestMedicijn");
//        String longName = "a".repeat(254);  // Net onder de grens
//        medicijn.setNaam(longName);
//        assertEquals(longName, medicijn.getNaam());
//    }

    @Test
    void testWijzigInnameTijd() {
        // Geldige tijd
        Medicijn medicijn = new Medicijn("TestMedicijn");
        medicijn.wijzigInnameTijd("10:00");
        assertEquals("10:00", medicijn.getInnameTijd());
    }

    @Test
    void testWijzigInnameTijdInvalidFormat() {
        // Ongeldige tijd: String niet in het formaat "HH:mm"
        Medicijn medicijn = new Medicijn("TestMedicijn");
        assertThrows(IllegalArgumentException.class, () -> medicijn.wijzigInnameTijd("invalid"));
    }

    @Test
    void testWijzigInnameTijdNull() {
        // Ongeldige tijd: Null
        Medicijn medicijn = new Medicijn("TestMedicijn");
        assertThrows(NullPointerException.class, () -> medicijn.wijzigInnameTijd(null));
    }

    // Randwaardetests voor wijzigInnameTijd
    @Test
    void testWijzigInnameTijdEdgeCaseStartOfDay() {
        Medicijn medicijn = new Medicijn("TestMedicijn");
        medicijn.wijzigInnameTijd("00:00");
        assertEquals("00:00", medicijn.getInnameTijd());
    }

    @Test
    void testWijzigInnameTijdEdgeCaseEndOfDay() {
        Medicijn medicijn = new Medicijn("TestMedicijn");
        medicijn.wijzigInnameTijd("23:59");
        assertEquals("23:59", medicijn.getInnameTijd());
    }

    @Test
    void testWijzigInnameTijdInvalidTimeAboveEdge() {
        Medicijn medicijn = new Medicijn("TestMedicijn");
        assertThrows(IllegalArgumentException.class, () -> medicijn.wijzigInnameTijd("24:00")); //net boven de grens
        assertThrows(IllegalArgumentException.class, () -> medicijn.wijzigInnameTijd("23:60")); //net boven de grens
    }

    @Test
    void testWijzigInnameTijdValidTimeBelowEdge() {
        Medicijn medicijn = new Medicijn("TestMedicijn");
        medicijn.wijzigInnameTijd("00:01");
        assertEquals("00:01", medicijn.getInnameTijd());
        medicijn.wijzigInnameTijd("23:58");
        assertEquals("23:58", medicijn.getInnameTijd());
    }
}
