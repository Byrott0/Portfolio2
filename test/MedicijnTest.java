import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MedicijnTest {
    @Test
    void testSetNaam() {
        // Arrange
        Medicijn medicijn = new Medicijn("TestMedicijn");

        // Act
        medicijn.setNaam("NieuweNaam");

        // Assert
        assertEquals("NieuweNaam", medicijn.getNaam());
    }

    @Test
    void testWijzigInnameTijd() {
        // Arrange
        Medicijn medicijn = new Medicijn("TestMedicijn");

        // Act
        medicijn.wijzigInnameTijd("10:00");

        // Assert
        assertEquals("10:00", medicijn.getInnameTijd());
    }
}