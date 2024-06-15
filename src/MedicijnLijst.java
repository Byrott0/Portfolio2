import java.util.ArrayList;
import java.util.List;

public class MedicijnLijst {
    private List<Medicijn> medicijnen = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    public void addMedicijn(Medicijn medicijn) {
        medicijnen.add(medicijn);
        notifyObservers();
    }

    public void removeMedicijn(int id) {
        medicijnen.removeIf(medicijn -> medicijn.getId() == id);
        notifyObservers();
    }

    public Medicijn getMedicijnById(int id) {
        for (Medicijn medicijn : medicijnen) {
            if (medicijn.getId() == id) {
                return medicijn;
            }
        }
        return null;
    }

    public List<Medicijn> getMedicijnen() {
        return medicijnen;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(medicijnen);
        }
    }
}
