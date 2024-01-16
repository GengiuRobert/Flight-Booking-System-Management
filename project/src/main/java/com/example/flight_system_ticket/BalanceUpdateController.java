package com.example.flight_system_ticket;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BalanceUpdateController {

    @FXML
    Label totalNumberOfCardLabel, showNewBalanceLabel;
    @FXML
    TextField cardRegisterNumberTextField, cvvRegisterNumberTextField, expirationRegisterDateTextField, cardRegistrationType;
    @FXML
    TextField cardDeleteNumberTextField, cardUpdateNumberTextField, cvvUpdateNumberTextField, expirationUpdateDateTextField, moneyAmountTextField;
    String email;
    int value;

    String name_of_client;
    private Controller2 controller2;


    public void setController2(Controller2 controller2) {
        this.controller2 = controller2;
    }

    public void setNameOfClientValue(String name) {
        this.name_of_client = name;
    }

    public Stage primary_stage, balance_update_view_stage = new Stage();

    public void set_email(String email) {
        this.email = email;
    }

    public void set_primary_stage(Stage stage) {
        this.primary_stage = stage;
    }

    public void go_to_update_balance_view() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("balance_update_page.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            balance_update_view_stage.setScene(new Scene(root));
            balance_update_view_stage.setTitle("Update My Balance Page");
            balance_update_view_stage.setResizable(false);
            balance_update_view_stage.setOnCloseRequest(windowEvent -> {
                Platform.exit();
                System.exit(0);
            });
            balance_update_view_stage.show();
        } catch (IOException e) {
            System.out.println("Error at showing loading balance update view " + e);
        }
        totalNumberOfCardLabel.setText("Total number of credit cards: " + number_of_credit_cards());
        primary_stage.close();
    }

    public void go_to_credit_cards() throws SQLException {
        credit_cards_view();
    }

    public void credit_cards_view() throws SQLException {
        CreditCardsController creditCardsController = new CreditCardsController();
        creditCardsController.set_primary_stage(balance_update_view_stage);
        creditCardsController.set_email_of_client(email);
        creditCardsController.go_to_credit_cards_view();
    }

    public int number_of_credit_cards() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            int clientID = dataBaseOpp.retrieve_client_id(connection, "clients", email);
            return dataBaseOpp.total_credit_cards_number_for_given_client(connection, clientID);
        } catch (Exception e) {
            System.out.println("Error at connecting to the database " + e);
        }
        return -1;
    }

    public void insert_credit_card() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            String card_number = cardRegisterNumberTextField.getText();
            String cvv = cvvRegisterNumberTextField.getText();
            String expiration_date = expirationRegisterDateTextField.getText();
            String card_type = cardRegistrationType.getText();
            int clientId = dataBaseOpp.retrieve_client_id(connection, "clients", email);
            if (cvv.length() >= 3 && DateValidator.isValidDate(expiration_date) && card_number.length() >= 12) {
                if (!dataBaseOpp.isCreditCardRegistered(connection, clientId, card_number)) {
                    if (number_of_credit_cards() < 3) {
                        dataBaseOpp.register_new_credit_card(connection, clientId, card_number, cvv, expiration_date, card_type);
                        totalNumberOfCardLabel.setText("Total number of credit cards: " + number_of_credit_cards());
                    } else {
                        totalNumberOfCardLabel.setText("A client can have a total of 3 credit cards");
                    }
                } else {
                    totalNumberOfCardLabel.setText("Card already registered");
                }
            } else {
                totalNumberOfCardLabel.setText("Invalid credit card credentials");
            }
        } catch (Exception e) {
            System.out.println("Error at connecting to the database " + e);
        }
    }

    public void delete_card_by_card_number() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            String card_number_to_delete = cardDeleteNumberTextField.getText();
            int clientId = dataBaseOpp.retrieve_client_id(connection, "clients", email);
            dataBaseOpp.delete_a_credit_card(connection, card_number_to_delete, clientId);
            totalNumberOfCardLabel.setText("Total number of credit cards: " + number_of_credit_cards());
        } catch (Exception e) {
            System.out.println("Error at connecting to the database " + e);
        }
    }

    public void introduce_money() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            String card_number = cardUpdateNumberTextField.getText();
            String cvv = cvvUpdateNumberTextField.getText();
            String expiration_date = expirationUpdateDateTextField.getText();
            int money = Integer.parseInt(moneyAmountTextField.getText());
            int clientId = dataBaseOpp.retrieve_client_id(connection, "clients", email);
            if (dataBaseOpp.isCreditCardRegistered(connection, clientId, card_number)) {
                dataBaseOpp.insert_money_to_client(connection, card_number, cvv, expiration_date, clientId, money);
                showNewBalanceLabel.setText("Your new balance is " + dataBaseOpp.retrieve_money_for_client(connection, "clients", email) + "€");
            } else {
                showNewBalanceLabel.setText("Credit card not found");
            }
        } catch (Exception e) {
            System.out.println("Error at connecting to the database " + e);
        }
    }

    public static class DateValidator {
        private static final String DATE_PATTERN = "^(?:20[0-4]\\d|2050)-(?:0[1-9]|1[0-2])-(?:0[1-9]|[12]\\d|3[01])$";

        public static boolean isValidDate(String date) {
            Pattern pattern = Pattern.compile(DATE_PATTERN);
            Matcher matcher = pattern.matcher(date);

            if (matcher.matches()) {
                int year = Integer.parseInt(date.substring(0, 4));
                int month = Integer.parseInt(date.substring(5, 7));
                int day = Integer.parseInt(date.substring(8, 10));

                if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
                    return false;
                }

                if (month == 2) {
                    if (isLeapYear(year) && day > 29) {
                        return false;
                    }
                    if (!isLeapYear(year) && day > 28) {
                        return false;
                    }
                }

                return true;
            }

            return false;
        }

        private static boolean isLeapYear(int year) {
            return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        }
    }

    public void go_back_to_client_view() throws IOException {
        DataBaseOpp dataBaseOpp = new DataBaseOpp();
        Connection connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
        controller2.set_email(email);
        controller2.setBalanceValue(dataBaseOpp.retrieve_money_for_client(connection,"clients",email));
        controller2.setNameOfClientValue(name_of_client);
        totalNumberOfCardLabel.setText("Total number of credit cards: " + number_of_credit_cards());
        controller2.show();
        primary_stage.show();
        controller2.set_primary_stage(primary_stage);
        balance_update_view_stage.close();
    }

}
