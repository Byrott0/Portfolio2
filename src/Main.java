public class Main {
    public static void main(String[] args) {
        MedicijnLijst medicijnLijst = new MedicijnLijst();
        MedicijnController medicijnController = new MedicijnController();
        GebruikerBeheer gebruikerBeheer = new GebruikerBeheer();
        MedicijnBeheer medicijnBeheer = new MedicijnBeheer(medicijnLijst, medicijnController);
        LoginService loginService = new LoginService(gebruikerBeheer, medicijnBeheer);

        MedicijnWijziger medicijnWijziger = new MedicijnWijziger();
        WijzigingBevestigd wijzigingBevestigd = new WijzigingBevestigd();

        ControlMenu controlMenu = new ControlMenu(medicijnWijziger, wijzigingBevestigd, medicijnLijst, medicijnController, loginService);

        boolean isLoggedIn = controlMenu.login();
        if (!isLoggedIn) {
            System.out.println("Login mislukt.");
        }
    }
}