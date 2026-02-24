package controllers;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import models.UserDAO;
import views.MainFrame;

public class MainController {
    private MainFrame mainFrame;
    private AuthController authController;
    private AppController appController;

    public MainController() {
        setSystemLookAndFeel();

        mainFrame = new MainFrame();
        UserDAO userDAO = new UserDAO();

        appController = new AppController(mainFrame);
        authController = new AuthController(mainFrame, userDAO, appController);

        mainFrame.setVisible(true);
        mainFrame.showWelcome();
    }

    private void setSystemLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
