public class Main {
    private static MedicijnLijst medicijnLijst;
    private static MedicijnController medicijnController;
    private static GebruikerBeheer gebruikerBeheer;
    private static MedicijnBeheer medicijnBeheer;
    private static LoginService loginService;
    private static MedicijnWijziger medicijnWijziger;
    private static WijzigingBevestigd wijzigingBevestigd;
    private static ControlMenu controlMenu;

    public static void main(String[] args) {
        initialiseerObjecten();
        voerLoginUit();
    }

    private static void initialiseerObjecten() {
        medicijnLijst = new MedicijnLijst();
        medicijnController = new MedicijnController();
        gebruikerBeheer = new GebruikerBeheer();
        medicijnBeheer = new MedicijnBeheer(medicijnLijst, medicijnController);
        loginService = new LoginService(gebruikerBeheer, medicijnBeheer);
        medicijnWijziger = new MedicijnWijziger();
        wijzigingBevestigd = new WijzigingBevestigd();
        controlMenu = new ControlMenu(medicijnWijziger, wijzigingBevestigd, medicijnLijst, medicijnController, loginService);
    }

    private static void voerLoginUit() {
        boolean isLoggedIn = controlMenu.login();
        if (!isLoggedIn) {
            System.out.println("Login mislukt.");
        }
    }
}
//test