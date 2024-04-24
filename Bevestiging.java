import java.util.Scanner;

interface Wijziginginterface {
    void wijzigMedicijn(MedicijnLijst medicijnLijst);
}

public class Bevestiging {
    private String message;
    private boolean status;


    private Wijziginginterface medicijnWijziger;

    public Bevestiging(Wijziginginterface medicijnWijziger) {
        this.medicijnWijziger = medicijnWijziger;
        this.message = "Medicijnen zijn bevestigd";
        this.status = false;
    }

    public void wijziging(MedicijnLijst medicijnLijst) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wilt u nog een medicijn wijzigen? (ja/nee)");
        if (scanner.nextLine().equals("ja")) {
            medicijnWijziger.wijzigMedicijn(medicijnLijst);
        }
    }

    public Bevestiging() {
        this.message = "Medicijnen zijn bevestigd";
        this.status = false;
    }

    public void bevestigRegistratie() {
        this.status = true;
        System.out.println("Het programma is klaar");
    }

    public String getMessage() { 
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}
