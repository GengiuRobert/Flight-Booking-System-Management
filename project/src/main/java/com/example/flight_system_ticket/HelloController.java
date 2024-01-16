package com.example.flight_system_ticket;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Handler;
import java.util.regex.*;
import java.io.IOException;
import java.sql.Connection;

public class HelloController {

    private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    TextField emailField;
    @FXML
    TextField createNameField, createEmailField;
    @FXML
    PasswordField createPasswordField, createRePasswordField;
    @FXML
    PasswordField passwordField;
    @FXML
    Label errorField, account_exists_or_not,userTypeLabel,labelTest;

    @FXML
    private RadioButton client_radio, admin_radio;
    private Controller2 controller2;
    public Stage primary_stage;
    public String help;
    public CreditCardsController creditCardsController = new CreditCardsController();

    public void set_primary_stage(Stage stage) {
        this.primary_stage = stage;
    }

    @FXML
    public void switch_to_admin_page() throws IOException {
       AdminPageController adminPageController = new AdminPageController();
       adminPageController.setPrimaryStage(primary_stage);
       adminPageController.switch_to_admin_page();
       primary_stage.close();
    }

    @FXML
    private void submitCredentials(ActionEvent event) throws IOException, SQLException {
        if (admin_radio.isSelected()) {
            if (emailField.getText().equals("admin@admin.com") && passwordField.getText().equals("admin123")) {
                switch_to_admin_page();
            } else {
                account_exists_or_not.setText("Invalid credentials for admin");
            }
        } else if (client_radio.isSelected()) {
            if (connect_as_client()) {
                client_page_view(event);
                creditCardsController.set_email_of_client(emailField.getText());
            } else {
                account_exists_or_not.setText("Account does not exist");
            }
        }
        userTypeLabel.setText("Invalid user type or credentials");
    }

    public void client_page_view(ActionEvent event) throws IOException, SQLException {

        Controller2 controller = new Controller2();
        controller.setNameOfClientValue(show_name_for_client());
        controller.setBalanceValue(show_balance_for_client());
        controller.set_email(help);
        controller.show();
        primary_stage.close();
    }

    @FXML
    private void switch_to_login_page(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("welcome_login_page.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switch_to_create_an_account_page(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("create_an_account_page.fxml"));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Sig Up Page");
        stage.setScene(scene);
        stage.show();
    }

    public static class EmailValidator {
        private static final String EMAIL_REGEX =
                "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

        public static boolean isValidEmail(String email) {
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
    }

    public static class PasswordValidator {
        private static final String PASSWORD_REGEX =
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";

        private static final Pattern pattern = Pattern.compile(PASSWORD_REGEX);

        public static boolean isValidPassword(String password) {
            Matcher matcher = pattern.matcher(password);
            return matcher.matches();
        }
    }

    public void create_an_account(ActionEvent event) {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            String email = createEmailField.getText();
            String name = createNameField.getText();
            String password = createPasswordField.getText();
            String re_password = createRePasswordField.getText();
            if (!email.isEmpty() && !name.isEmpty() && !password.isEmpty() && !re_password.isEmpty()) {
                if (EmailValidator.isValidEmail(email)) {
                    if (PasswordValidator.isValidPassword(password)) {
                        if (password.equals(re_password)) {
                            if (!dataBaseOpp.search_for_account_in_database(connection, "clients", email, password)) {
                                dataBaseOpp.insert_row_in_clients_table(connection, "clients", email, name, password);
                                errorField.setText("Account created");
                            } else {
                                errorField.setText("Email is taken");
                            }
                        } else {
                            errorField.setText("Password dont match");
                        }
                    } else {
                        errorField.setText("Invalid password");
                    }
                } else {
                    errorField.setText("Invalid email");
                }
            } else {
                errorField.setText("You must complete all the fields");
            }
        } catch (Exception e) {
            System.out.println("Error at closing the connection");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public boolean connect_as_client() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            String email = emailField.getText();
            help =emailField.getText();
            String password = passwordField.getText();
            return dataBaseOpp.search_for_account_in_database(connection, "clients", email, password);
        } catch (Exception e) {
            System.out.println(e + "Error at connect as a client");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @FXML
    public int show_balance_for_client() throws  SQLException{
            Connection connection = null;
            try {
                DataBaseOpp dataBaseOpp = new DataBaseOpp();
                connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
                String email = emailField.getText();
                help =emailField.getText();
                return dataBaseOpp.retrieve_money_for_client(connection, "clients", email);
            } catch (Exception e) {
                System.out.println("Error at retrieving money " + e);
            } finally {
                if (connection != null) connection.close();
            }
            System.out.println("Something bad happened");
     return  -1;
    }

    @FXML
    public String show_name_for_client() throws SQLException {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            String email = emailField.getText();
            help =emailField.getText();
            return dataBaseOpp.retrieve_name_of_client(connection, "clients", email);
        } catch (Exception e) {
            System.out.println("Error at retrieving money " + e);
        } finally {
            if (connection != null) connection.close();
        }
        System.out.println("Something bad happened");
        return "no name";
    }
}