import controller.MainController;
import view.MainMenu;

/**
 * Main entry point for the Workshop Management System.
 */
public class Main {
    public static void main(String[] args) {
        try {
            MainController controller = MainController.getInstance();
            MainMenu menu = new MainMenu(controller);
            menu.show();
        } catch (Exception e) {
            System.err.println("Fatal error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
