package Lab_7;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JsonDatabaseManager dbManager = new JsonDatabaseManager();
            new LoginFrame(dbManager).setVisible(true);
        });
    }
}