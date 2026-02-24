package controllers;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import models.User;
import models.UserDAO;
import views.ForgotPasswordPanel;
import views.LoginPanel;
import views.MainFrame;
import views.SignUpPanel;

public class AuthController {
    private MainFrame mainFrame;
    private UserDAO userDAO;
    private AppController appController;

    public AuthController(MainFrame mainFrame, UserDAO userDAO, AppController appController) {
        this.mainFrame = mainFrame;
        this.userDAO = userDAO;
        this.appController = appController;

        initListeners();
    }

    private void initListeners() {
        LoginPanel loginPanel = mainFrame.getLoginPanel();
        loginPanel.setLoginListener(e -> handleLogin());
        loginPanel.setSignUpListener(e -> {
            loginPanel.clearFields();
            mainFrame.showView("SignUp");
        });
        loginPanel.setForgotPasswordListener(e -> {
            loginPanel.clearFields();
            mainFrame.showView("ForgotPassword");
        });

        SignUpPanel signUpPanel = mainFrame.getSignUpPanel();
        signUpPanel.setSignUpListener(e -> handleSignUp());
        signUpPanel.setToLoginListener(e -> {
            signUpPanel.clearFields();
            mainFrame.showView("Login");
        });

        ForgotPasswordPanel forgotPanel = mainFrame.getForgotPasswordPanel();
        forgotPanel.setSearchListener(e -> handleSearchUser());
        forgotPanel.setVerifyListener(e -> handleVerifySecurityAnswer());
        forgotPanel.setToLoginListener(e -> {
            forgotPanel.clearFields();
            mainFrame.showView("Login");
        });
    }

    private void handleLogin() {
        LoginPanel loginPanel = mainFrame.getLoginPanel();
        String mobile = loginPanel.getMobile();
        String password = loginPanel.getPassword();

        if (mobile.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Please enter both Mobile No and Password.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            User user = userDAO.loginUser(mobile, password);
            if (user != null) {
                // Login successful
                loginPanel.clearFields();
                JOptionPane.showMessageDialog(mainFrame,
                        "<html><b style=\"color:green\">Login successfully...</b></html>");
                appController.initializeForUser(user);
                mainFrame.showView("App");
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Invalid Mobile No or Password.", "Login Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Database error: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void handleSignUp() {
        SignUpPanel signUpPanel = mainFrame.getSignUpPanel();
        String name = signUpPanel.getNameInput();
        String mobile = signUpPanel.getMobileInput();
        String password = signUpPanel.getPasswordInput();
        String answer = signUpPanel.getAnswerInput();
        int age = signUpPanel.getAgeInput();
        String gender = signUpPanel.getGenderInput();
        String securityQuestion = signUpPanel.getSecurityQuestionInput();

        if (name.isEmpty() || mobile.isEmpty() || password.isEmpty() || answer.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Please fill all fields.", "Sign Up Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (mobile.length() != 10) {
            JOptionPane.showMessageDialog(mainFrame, "Mobile number must be 10 digits.", "Sign Up Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (userDAO.checkUserExists(mobile)) {
                JOptionPane.showMessageDialog(mainFrame, "This mobile number is already registered.",
                        "Registration Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            User newUser = new User(mobile, name, age, gender, password, securityQuestion, answer);
            if (userDAO.registerUser(newUser)) {
                JOptionPane.showMessageDialog(mainFrame,
                        "<html><b style=\"color:green\">Registration completed successfully...</b></html>", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                signUpPanel.clearFields();
                mainFrame.showView("Login");
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Registration failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Database error: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void handleSearchUser() {
        ForgotPasswordPanel forgotPanel = mainFrame.getForgotPasswordPanel();
        String mobile = forgotPanel.getMobileInput();

        if (mobile.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Please enter your registered mobile number.", "Input Required",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            User user = userDAO.getSecurityDetails(mobile);
            if (user != null) {
                forgotPanel.setNameField(user.getName());
                forgotPanel.setQuestionField(user.getSecurityQuestion());
                forgotPanel.clearAnswer();
                forgotPanel.clearPassword();
            } else {
                forgotPanel.clearFields();
                JOptionPane.showMessageDialog(mainFrame, "Incorrect Mobile number.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Database error: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void handleVerifySecurityAnswer() {
        ForgotPasswordPanel forgotPanel = mainFrame.getForgotPasswordPanel();
        String mobile = forgotPanel.getMobileInput();
        String answer = forgotPanel.getAnswerInput();
        String expectedQuestion = forgotPanel.getQuestionFieldText();

        if (expectedQuestion == null || expectedQuestion.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Please search for your mobile number first.", "Action Required",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (answer.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Please enter the answer to the security question.",
                    "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String password = userDAO.getPasswordBySecurityAnswer(mobile, answer);
            if (password != null) {
                forgotPanel.setPasswordField(password);
            } else {
                forgotPanel.clearPassword();
                JOptionPane.showMessageDialog(mainFrame, "Incorrect Answer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Database error: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
