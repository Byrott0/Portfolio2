
public interface Subject {

        void addObserver(Observer observer);
        void removeObserver(Observer observer);
        void notifyObservers();
        //voeg hierin string of int test

}