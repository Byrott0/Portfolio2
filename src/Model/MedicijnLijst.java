package Model;

import java.util.ArrayList;
import java.util.List;

public class MedicijnLijst implements Subject {

    private final List<Medicijn> medicijnen = new ArrayList<>();
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(medicijnen);
        }
    }

    public void addMedicijn(Medicijn medicijn) {
        medicijnen.add(medicijn);
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
}
