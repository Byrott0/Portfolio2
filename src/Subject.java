public interface Subject {
        void addObserver(Observer observer);
        void removeObserver(Observer observer);
        void notifyObservers();

        // Voeg een methode toe om te testen met een string of integer
//        void test(String testString);
//        void test(int testInt);
}
