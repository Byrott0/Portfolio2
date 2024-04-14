
public class Reminder {
    private Medicijn medicijn;

    public Reminder(Medicijn medicijn) {
        this.medicijn = medicijn;
    }

    public Medicijn getMedicijn() {
        return this.medicijn;
    }

    public void setMedicijn(Medicijn medicijn) {
        this.medicijn = medicijn;
    }

    public void remind() {
        try {
            Thread.sleep(medicijn.getInnameTijdVerschilMS());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Het is tijd om " + medicijn.getNaam() + " in te nemen");
    }
}