package com.example.flight_system_ticket;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class Controller2 implements Initializable {

    @FXML
    Label balanceClientLabel, welcomeNameLabel, buyTicketLabel, countWordsLabel, yourRatingLabel, deleteReviewLabel, reviewSentSuccesfulLabel, clearHistoryLabel;
    @FXML
    TextField ticketIDTextField, ticketIDRateTextField, ticketIDGiveReviewTextField, ticketIDDeleteReview, seatNumberTextField;
    @FXML
    RadioButton firstClassRadioButton, secondClassRadioButton;
    @FXML
    Slider ratingSlider;
    @FXML
    TextArea reviewTextArea;
    @FXML
    Button ratingButton;
    int value;
    String name_of_client, email;
    public Stage primary_stage, client_view_stage = new Stage(), balanceView;

    public enum ReservedTicketEnum {RESERVED, NOT_RESERVED}

    public enum SeatClass {FIRST_CLASS, SECOND_CLASS}

    public void set_email(String email) {
        this.email = email;
    }

    public void set_primary_stage(Stage stage) {
        this.primary_stage = stage;
    }

    public void setBalanceValue(int value) {
        this.value = value;
    }

    public void setNameOfClientValue(String name) {
        this.name_of_client = name;
    }
    protected void show() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("client_page_view.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            client_view_stage.setScene(new Scene(root));
            client_view_stage.setOnCloseRequest(windowEvent -> {
                Platform.exit();
                System.exit(0);
            });
            client_view_stage.setTitle("Client Page");
            client_view_stage.show();
        } catch (IOException e) {
            System.out.println("Error at showing loading client view " + e);
        }
        ToggleGroup toggleGroup = new ToggleGroup();
        firstClassRadioButton.setToggleGroup(toggleGroup);
        secondClassRadioButton.setToggleGroup(toggleGroup);
        balanceClientLabel.setText("Your balance is: " + String.valueOf(value) + "€");
        welcomeNameLabel.setText("Welcome " + name_of_client);

    }

    public void go_to_balance_update() {
        balance_update_view();
    }

    public void balance_update_view() {
        BalanceUpdateController balanceUpdateController = new BalanceUpdateController();
        balanceUpdateController.set_primary_stage(client_view_stage);
        balanceUpdateController.set_email(email);
        balanceUpdateController.setController2(this);
        balanceUpdateController.setNameOfClientValue(name_of_client);
        balanceUpdateController.go_to_update_balance_view();
    }

    public void go_to_show_tickets() {
        tickets_view();
    }

    public void tickets_view() {
        TicketsController ticketsController = new TicketsController();
        ticketsController.set_primary_stage(client_view_stage);
        ticketsController.show();
    }

    public void buyTicketAndInsertInTransactionsAndReview() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            int ticketId = 0;
            int clientId = dataBaseOpp.retrieve_client_id(connection, "clients", email);
            if (!ticketIDTextField.getText().isEmpty() && Integer.parseInt(ticketIDTextField.getText()) >= 1) {
                ticketId = Integer.parseInt(ticketIDTextField.getText());
            } else {
                buyTicketLabel.setText("Invalid seat number or ticket id");
            }
            int price1stClass = dataBaseOpp.getPrice1stClassOfTicketByID(connection, ticketId);
            int price2ndClass = dataBaseOpp.getPrice2ndClassOfTicketByID(connection, ticketId);

            int seatNumber = 0;
            if (!seatNumberTextField.getText().isEmpty() && Integer.parseInt(seatNumberTextField.getText()) >= 1) {
                seatNumber = Integer.parseInt(seatNumberTextField.getText());
            } else {
                buyTicketLabel.setText("Invalid seat number or ticket id");
            }
            boolean isSeatReserved = dataBaseOpp.isReservedSeat(connection, ticketId, seatNumber);
            ReservedTicketEnum reservedTicketEnum;
            if (!isSeatReserved) {
                reservedTicketEnum = ReservedTicketEnum.RESERVED;
            } else {
                reservedTicketEnum = ReservedTicketEnum.NOT_RESERVED;
            }
            String leave_date = dataBaseOpp.getLeaveDateOfTicketByID(connection, ticketId);
            String arrival_date = dataBaseOpp.getArrivalDateOfTicketByID(connection, ticketId);
            String leave_city = dataBaseOpp.getLeaveCityOfTicketByID(connection, ticketId);
            String arrival_city = dataBaseOpp.getArrivalCityOfTicketByID(connection, ticketId);
            String leave_hour = dataBaseOpp.getLeaveHourOfTicketByID(connection, ticketId);
            String arrival_hour = dataBaseOpp.getArrivalHourOfTicketByID(connection, ticketId);
            int totalSeat = dataBaseOpp.retrieveTotalNumberOfSeats(connection, ticketId);
            int remainigSeats = dataBaseOpp.getRemainingSeatsNumber(connection, ticketId);

            if (firstClassRadioButton.isSelected()) {
                if (remainigSeats > 0) {
                    if (seatNumber >= 1 && seatNumber <= totalSeat) {
                        if (reservedTicketEnum == ReservedTicketEnum.RESERVED) {
                            if (dataBaseOpp.retrieve_money_for_client(connection, "clients", email) > price1stClass) {
                                dataBaseOpp.decreaseClientBalanceByID(connection, clientId, price1stClass);
                                buyTicketLabel.setText("Transaction successful");
                                dataBaseOpp.insertTicketToTransactions(connection, clientId, ticketId, price1stClass, "1st Class", leave_date,
                                        arrival_date, leave_city, arrival_city, leave_hour, arrival_hour, seatNumber);
                                dataBaseOpp.insertTicketToReview(connection, clientId, ticketId, 0);
                                dataBaseOpp.insertSeatInfo(connection, ticketId, seatNumber);
                                dataBaseOpp.decreaseAvailableSeatsNumber(connection, ticketId, SeatClass.FIRST_CLASS);
                                int balance = dataBaseOpp.retrieve_money_for_client(connection, "clients", email);
                                balanceClientLabel.setText("Your balance is: " + String.valueOf(balance) + "€");
                            } else {
                                buyTicketLabel.setText("Not enough funds");
                            }
                        } else {
                            buyTicketLabel.setText("Seat is reserved");
                        }
                    } else {
                        buyTicketLabel.setText("Invalid seat number");
                    }
                } else {
                    buyTicketLabel.setText("No more available seats");
                }
            } else if (secondClassRadioButton.isSelected()) {
                if (remainigSeats > 0) {
                    if (seatNumber >= 1 && seatNumber <= totalSeat) {
                        if (reservedTicketEnum == ReservedTicketEnum.RESERVED) {
                            if (dataBaseOpp.retrieve_money_for_client(connection, "clients", email) > price2ndClass) {
                                dataBaseOpp.decreaseClientBalanceByID(connection, clientId, price2ndClass);
                                buyTicketLabel.setText("Transaction successful");
                                dataBaseOpp.insertTicketToTransactions(connection, clientId, ticketId, price2ndClass, "2nd Class", leave_date,
                                        arrival_date, leave_city, arrival_city, leave_hour, arrival_hour, seatNumber);
                                dataBaseOpp.insertTicketToReview(connection, clientId, ticketId, 0);
                                dataBaseOpp.insertSeatInfo(connection, ticketId, seatNumber);
                                dataBaseOpp.decreaseAvailableSeatsNumber(connection, ticketId, SeatClass.SECOND_CLASS);
                                int balance = dataBaseOpp.retrieve_money_for_client(connection, "clients", email);
                                balanceClientLabel.setText("Your balance is: " + String.valueOf(balance) + "€");
                            } else {
                                buyTicketLabel.setText("Not enough funds");
                            }
                        } else {
                            buyTicketLabel.setText("Seat is reserved");
                        }
                    } else {
                        buyTicketLabel.setText("Invalid seat number");
                    }
                } else {
                    buyTicketLabel.setText("No more available seats");
                }
            } else {
                buyTicketLabel.setText("Invalid ticket type");
            }
        } catch (Exception e) {
            System.out.println("Error at connecting to db " + e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ratingSlider.valueProperty().addListener((ObservableValue<? extends Number> num, Number oldVal, Number newVal) -> {
            float ratingValue = Float.parseFloat(String.format("%.2f", newVal));
            yourRatingLabel.setText("Your rating " + ratingValue);
        });

        reviewTextArea.setWrapText(true);
        reviewTextArea.addEventFilter(KeyEvent.KEY_TYPED, this::limitCharacters);
        ratingButton.setOnAction(this::giveRatingToTicket);
    }

    public void giveRatingToTicket(ActionEvent event) {
        float ratingValue = Float.parseFloat(String.format("%.2f", ratingSlider.getValue()));
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            int ticketID = Integer.parseInt(ticketIDRateTextField.getText());
            int clientID = dataBaseOpp.retrieve_client_id(connection, "clients", email);
            dataBaseOpp.giveRatingToFlight(connection, ratingValue, ticketID, clientID);
        } catch (Exception e) {
            System.out.println("Error at connecting to db " + e);
        }

    }

    @FXML
    void textAreaTyped(KeyEvent event) {
        ObservableList<CharSequence> list = reviewTextArea.getParagraphs();
        int paragraph = list.size();
        String[] words = reviewTextArea.getText().split("\\s+");
        countWordsLabel.setText("Paragraph: " + paragraph + " | Words: " + words.length + " | Characters: " + reviewTextArea.getLength());
    }

    public void limitCharacters(KeyEvent event) {
        int maxLength = 255;
        if (reviewTextArea.getText().length() >= maxLength) {
            event.consume();
        }
    }

    public void giveReviewToTicket() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            int clientID = dataBaseOpp.retrieve_client_id(connection, "clients", email);
            int ticketID = Integer.parseInt(ticketIDGiveReviewTextField.getText());
            String review = reviewTextArea.getText();
            dataBaseOpp.giveReviewToTicket(connection, clientID, ticketID, review);
        } catch (Exception e) {
            System.out.println("Error at connecting to db " + e);
        }
        reviewSentSuccesfulLabel.setText("Review Sent");
    }

    public void deleteReview() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            int clientID = dataBaseOpp.retrieve_client_id(connection, "clients", email);
            int ticketID = Integer.parseInt(ticketIDDeleteReview.getText());
            dataBaseOpp.setReviewBackToDefault(connection, clientID, ticketID);
            deleteReviewLabel.setText("Review Deleted");
        } catch (Exception e) {
            System.out.println("Error at connecting to db " + e);
        }
    }

    public void showTransactionHistory() {
        TransactionHistoryController transactionHistoryController = new TransactionHistoryController();
        transactionHistoryController.setEmail(email);
        transactionHistoryController.show();
    }

    public void clearTheTransactionHistory() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            int clientID = dataBaseOpp.retrieve_client_id(connection, "clients", email);
            int transactionHistory = dataBaseOpp.clearTransactionHistory(connection, clientID);
            int reviewHistory = dataBaseOpp.clearReviewHistory(connection, clientID);
            if (transactionHistory > 0 && reviewHistory > 0) {
                clearHistoryLabel.setText("Transaction history cleared");
            } else {
                clearHistoryLabel.setText("Nothing to clear");
            }
        } catch (Exception e) {
            System.out.println("Error at connecting to db " + e);
        }
    }
}
