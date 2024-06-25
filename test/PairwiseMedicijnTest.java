import Model.Medicijn;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PairwiseMedicijnTest {

    @Test
    void testPairwise1() {
        Medicijn medicijn = new Medicijn("MedicijnA");
        assertDoesNotThrow(() -> medicijn.wijzigInnameTijd("10:00"));
        assertDoesNotThrow(() -> medicijn.setNaam("MedicijnB"));
        assertDoesNotThrow(() -> medicijn.wijzigInnameTijd("12:00"));
    }

    @Test
    void testPairwise2() {
        Medicijn medicijn = new Medicijn("MedicijnA");
        assertDoesNotThrow(() -> medicijn.wijzigInnameTijd("10:00"));
        assertDoesNotThrow(() -> medicijn.setNaam("MedicijnB"));
        assertThrows(IllegalArgumentException.class, () -> medicijn.wijzigInnameTijd("25:00"));
    }

    @Test
    void testPairwise3() {
        Medicijn medicijn = new Medicijn("MedicijnA");
        assertDoesNotThrow(() -> medicijn.wijzigInnameTijd("10:00"));
        assertThrows(IllegalArgumentException.class, () -> medicijn.setNaam(""));
        assertDoesNotThrow(() -> medicijn.wijzigInnameTijd("12:00"));
    }

    @Test
    void testPairwise4() {
        Medicijn medicijn = new Medicijn("MedicijnA");
        assertThrows(IllegalArgumentException.class, () -> medicijn.wijzigInnameTijd("24:00"));
        assertDoesNotThrow(() -> medicijn.setNaam("MedicijnB"));
        assertDoesNotThrow(() -> medicijn.wijzigInnameTijd("12:00"));
    }

    @Test
    void testPairwise5() {
        Medicijn medicijn = new Medicijn("MedicijnA");
        assertThrows(IllegalArgumentException.class, () -> medicijn.wijzigInnameTijd("24:00"));
        assertThrows(IllegalArgumentException.class, () -> medicijn.setNaam(""));
        assertThrows(IllegalArgumentException.class, () -> medicijn.wijzigInnameTijd("25:00"));
    }

    @Test
    void testPairwise6() {
        assertThrows(IllegalArgumentException.class, () -> new Medicijn(""), "Naam mag niet leeg zijn.");

        Medicijn medicijn = new Medicijn("ValidName");

        assertDoesNotThrow(() -> medicijn.wijzigInnameTijd("10:00"));

        assertDoesNotThrow(() -> medicijn.setNaam("MedicijnB"));

        assertDoesNotThrow(() -> medicijn.wijzigInnameTijd("12:00"));
    }

    @Test
    void testPairwise7() {
        assertThrows(IllegalArgumentException.class, () -> new Medicijn(""), "Naam mag niet leeg zijn.");

        Medicijn medicijn = new Medicijn("ValidName");

        assertThrows(IllegalArgumentException.class, () -> medicijn.wijzigInnameTijd("24:00"));

        assertDoesNotThrow(() -> medicijn.setNaam("MedicijnB"));

        assertThrows(IllegalArgumentException.class, () -> medicijn.wijzigInnameTijd("25:00"));
    }

    @Test
    void testPairwise8() {
        assertThrows(NullPointerException.class, () -> new Medicijn(null), "Naam mag niet null zijn.");

        Medicijn medicijn = new Medicijn("ValidName");

        assertDoesNotThrow(() -> medicijn.wijzigInnameTijd("10:00"));

        assertDoesNotThrow(() -> medicijn.setNaam("MedicijnB"));

        assertDoesNotThrow(() -> medicijn.wijzigInnameTijd("12:00"));
    }

    @Test
    void testPairwise9() {
        assertThrows(NullPointerException.class, () -> new Medicijn(null), "Naam mag niet null zijn.");
        Medicijn medicijn = new Medicijn("ValidName");
        assertThrows(IllegalArgumentException.class, () -> medicijn.wijzigInnameTijd("24:00"));
        assertThrows(IllegalArgumentException.class, () -> medicijn.setNaam(""));
        assertThrows(IllegalArgumentException.class, () -> medicijn.wijzigInnameTijd("25:00"));
    }
}
