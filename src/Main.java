import Menu.ControlMenu;
import Utils.LoginService;

public class Main {
    private static ControlMenu controlMenu;
    private static LoginService loginService;

    public static void main(String[] args) {
        ControlMenu.initialiseerObjecten();
        controlMenu = ControlMenu.getControlMenuInstance();
        loginService = ControlMenu.getLoginServiceInstance();
        loginService.voerLoginUit();
    }
}