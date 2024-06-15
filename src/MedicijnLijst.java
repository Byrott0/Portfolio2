import java.util.ArrayList;
import java.util.List;

public class MedicijnLijst implements Subject { // dit staat voor de class medicijn

    private final ArrayList<Medicijn> medicijnen = new ArrayList<>(); //final variabel doet dat je de variabel constant blijft
    // en in een arraylist zorgt het ervoor dat je niet kan verwijzen naar een andere lijst maar juist specifiek naar deze

    private final ArrayList<Observer> observers = new ArrayList<>(); //dit is een lijst van observers

    private final MedicijnController medicijnController = new MedicijnController();

    public void addMedicijn(Medicijn medicijn) {
        medicijnen.add(medicijn);
        notifyObservers();
    }

    public void removeMedicijn(int id) {
        for (Medicijn medicijn : medicijnen) {
            if (medicijn.getId() == id) {
                medicijnen.remove(medicijn);
                notifyObservers();
                return;
            }
        }
    }

    public ArrayList<Medicijn> getMedicijnen() {
        return medicijnen;
    }
    //dit is een getter die de medicijnen teruggeeft

    //een specifiek Medicijn object te vinden en terug te geven op basis van zijn id.
    public Medicijn getMedicijnById(int id) {
        return medicijnController.getMedicijnById(id);
    }

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
            observer.update(new ArrayList<>(medicijnen));
        }
    }
}
