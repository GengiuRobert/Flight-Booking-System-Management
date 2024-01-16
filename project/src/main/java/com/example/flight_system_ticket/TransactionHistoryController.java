package com.example.flight_system_ticket;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class TransactionHistoryController {

    @FXML
    TableView<HistoryTicket> transactionHistoryTableView;
    @FXML
    TableColumn<HistoryTicket, Integer> ticketIDTransactionColumn, priceTransactionColumn;
    @FXML
    TableColumn<HistoryTicket, String> typeTransactionColumn, leaveDateTransactionColumn, arrivalDateTransactionColumn, leaveCityTransactionColumn, arrivalTransactionCityColumn, leaveTimeTransactionColumn, arrivalTransactionTimeColumn;
    @FXML
    TableView<ReviewThing> ReviewTableView;
    @FXML
    TableColumn<ReviewThing, Float> ratingReviewColumn;
    @FXML
    TableColumn<ReviewThing,Integer> ticketIDReviewColumn;
    @FXML
    TableColumn<ReviewThing,String> reviewColumn;
    String email;

    public ObservableList<HistoryTicket> historyTicketsList = FXCollections.observableArrayList();
    public ObservableList<ReviewThing> reviewThingsList = FXCollections.observableArrayList();

    public void setEmail(String email) {
        this.email = email;
    }

    public void show() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("transactions_history_view.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Transaction History Page");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error at showing transaction history view " + e);
        }
        initializeHistoryTicket();
        initializeReview();
    }

    public void initializeHistoryTicket() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            int clientID = dataBaseOpp.retrieve_client_id(connection, "clients", email);
            historyTicketsList = dataBaseOpp.getTicketsHistory(connection, clientID);
            transactionHistoryTableView.setItems(historyTicketsList);

            ticketIDTransactionColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            priceTransactionColumn.setCellValueFactory(new PropertyValueFactory<>("ticketId"));
            typeTransactionColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
            leaveDateTransactionColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            arrivalDateTransactionColumn.setCellValueFactory(new PropertyValueFactory<>("leaveDate"));
            leaveCityTransactionColumn.setCellValueFactory(new PropertyValueFactory<>("leaveCity"));
            arrivalTransactionCityColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalCity"));
            leaveTimeTransactionColumn.setCellValueFactory(new PropertyValueFactory<>("leaveHour"));
            arrivalTransactionTimeColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalHour"));
        } catch (Exception e) {
            System.out.println("error at connecting to the database " + e);
        }
    }

    public void initializeReview()
    {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            int clientId = dataBaseOpp.retrieve_client_id(connection,"clients",email);
            reviewThingsList = dataBaseOpp.getReview(connection,clientId);
            ReviewTableView.setItems(reviewThingsList);
            ticketIDReviewColumn.setCellValueFactory(new PropertyValueFactory<>("ticketId"));
            ratingReviewColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
            reviewColumn.setCellValueFactory(new PropertyValueFactory<>("review"));

        }catch (Exception e)
        {
            System.out.println("Error at connecting to database "+e);
        }
    }

    public static class HistoryTicket {
        private int ticketId;
        private int price;
        private String leaveHour;
        private String arrivalHour;
        private String leaveCity;
        private String arrivalCity;
        private String leaveDate;
        private String arrivalDate;
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getTicketId() {
            return ticketId;
        }

        public void setTicketId(int ticketId) {
            this.ticketId = ticketId;
        }

        public String getLeaveHour() {
            return leaveHour;
        }

        public void setLeaveHour(String leaveHour) {
            this.leaveHour = leaveHour;
        }

        public String getArrivalHour() {
            return arrivalHour;
        }

        public void setArrivalHour(String arrivalHour) {
            this.arrivalHour = arrivalHour;
        }

        public String getLeaveCity() {
            return leaveCity;
        }

        public void setLeaveCity(String leaveCity) {
            this.leaveCity = leaveCity;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getArrivalCity() {
            return arrivalCity;
        }

        public void setArrivalCity(String arrivalCity) {
            this.arrivalCity = arrivalCity;
        }

        public String getLeaveDate() {
            return leaveDate;
        }

        public void setLeaveDate(String leaveDate) {
            this.leaveDate = leaveDate;
        }

        public String getArrivalDate() {
            return arrivalDate;
        }

        public void setArrivalDate(String arrivalDate) {
            this.arrivalDate = arrivalDate;
        }


        public HistoryTicket(int ticketId, int price, String leaveHour, String arrivalHour, String type,
                             String leaveCity, String arrivalCity, String leaveDate, String arrivalDate) {
            this.ticketId = ticketId;
            this.leaveHour = leaveHour;
            this.arrivalHour = arrivalHour;
            this.leaveCity = leaveCity;
            this.arrivalCity = arrivalCity;
            this.leaveDate = leaveDate;
            this.arrivalDate = arrivalDate;
            this.type = type;
            this.price = price;
        }
    }

    public static class ReviewThing{
        private int ticketId;
        private Float rating;
        private String review;

        public int getTicketId() {
            return ticketId;
        }

        public void setTicketId(int ticketId) {
            this.ticketId = ticketId;
        }

        public Float getRating() {
            return rating;
        }

        public void setRating(Float rating) {
            this.rating = rating;
        }

        public String getReview() {
            return review;
        }

        public void setReview(String review) {
            this.review = review;
        }
        public ReviewThing(int ticketId,Float rating,String review)
        {
            this.ticketId =ticketId;
            this.rating = rating;
            this.review = review;
        }
    }
}
