// Interface voor bevestigingsacties
interface Bevestigbaar {
    void afgerond();

}

// class weergeeft dat account is aangemaakt
public class Account implements Bevestigbaar {
    private Gebruiker gebruiker;
    private Bevestiging bevestiging;


    public Account(Gebruiker gebruiker, Bevestiging bevestiging) {
        this.gebruiker = gebruiker;

    }

    @Override
    public void afgerond() {
        System.out.println("Account aangemaakt voor " + gebruiker.getNaam() + "!");
    }
}
