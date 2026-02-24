package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // the views
    private LoginPanel loginPanel;
    private SignUpPanel signUpPanel;
    private ForgotPasswordPanel forgotPasswordPanel;
    private AppTabbedPane appTabbedPane;

    public MainFrame() {
        setTitle("Digital Wedding Invitation App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1020, 600);
        setLocationRelativeTo(null);

        // App Icon
        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("icons/frameicon.jpg")));
        } catch (Exception e) {
            System.err.println("Icon image not found: " + e.getMessage());
        }

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize views
        loginPanel = new LoginPanel();
        signUpPanel = new SignUpPanel();
        forgotPasswordPanel = new ForgotPasswordPanel();
        appTabbedPane = new AppTabbedPane();

        // Add views to CardLayout
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(signUpPanel, "SignUp");
        mainPanel.add(forgotPasswordPanel, "ForgotPassword");
        mainPanel.add(appTabbedPane, "App");

        setContentPane(mainPanel);
    }

    public void showWelcome() {
        JWindow welcome = new JWindow(this);
        welcome.setSize(1020, 600);
        welcome.setLocationRelativeTo(null);
        ImagePanel w = new ImagePanel("icons/welcome.jpg");
        JProgressBar load = new JProgressBar(0, 100);
        load.setStringPainted(true);
        welcome.getContentPane().add(BorderLayout.CENTER, w);
        welcome.getContentPane().add(BorderLayout.PAGE_END, load);
        welcome.setVisible(true);

        Timer timer = new Timer(100, null);
        timer.addActionListener(new ActionListener() {
            int progress = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                progress += 5;
                load.setValue(progress);
                if (progress >= 100) {
                    timer.stop();
                    welcome.dispose();
                    cardLayout.show(mainPanel, "Login"); 
                }
            }
        });
        timer.start();
    }

    public void showView(String viewName) {
        cardLayout.show(mainPanel, viewName);
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public SignUpPanel getSignUpPanel() {
        return signUpPanel;
    }

    public ForgotPasswordPanel getForgotPasswordPanel() {
        return forgotPasswordPanel;
    }

    public AppTabbedPane getAppTabbedPane() {
        return appTabbedPane;
    }
}
