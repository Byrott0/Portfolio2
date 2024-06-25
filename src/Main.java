import Menu.ControlMenu;
import Utils.LoginService;

public class Main {
    private static LoginService loginService;

    public static void main(String[] args) {
        ControlMenu.initialiseerObjecten();
        loginService = ControlMenu.getLoginServiceInstance();
        loginService.voerLoginUit();
    }
}