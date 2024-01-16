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

public class TicketsController {

    Stage tickets_stage = new Stage();
    Stage primaryStage;
    @FXML
    TableView<Ticket> ticketsTableView;
    @FXML
    TableColumn<Ticket, String> airlineCodeColumn, airlineFullNameColumn, cityLeavingColumn, cityArrivalColumn, leavingAirportColumn, arrivalAirportColumn;
    @FXML
    TableColumn<Ticket, String> arrivalTimeColumn, leavingTimeColumn, leavingDateColumn, arrivalDateColumn;
    @FXML
    TableColumn<Ticket, Integer> price1stClassColumn, price2ndClassColumn,ticketIDColumn;
    @FXML
    TableView<PlaneThing> planeInfoTable;
    @FXML
    TableColumn<PlaneThing,Integer> ticketIdPlaneColumn,totalSeatsColumn,availableSeatsColumn,firstClassColumn,secondClassColumn;
    @FXML
    TableColumn<PlaneThing,String> planeCodeColumn;

    public ObservableList<Ticket> ticketList = FXCollections.observableArrayList();
    public ObservableList<PlaneThing> planeThingsList = FXCollections.observableArrayList();

    public void set_primary_stage(Stage stage) {
        this.primaryStage = stage;
    }

    protected void show() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tickets_info.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            tickets_stage.setScene(new Scene(root));
            tickets_stage.show();
            tickets_stage.setResizable(false);
        } catch (IOException e) {
            System.out.println("Error at showing loading tickets view " + e);
        }
        initializeTickets();
        initializePlaneInfo();
    }

    public void initializeTickets() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            ticketList = dataBaseOpp.getTickets(connection);
            ticketsTableView.setItems(ticketList);
            ticketIDColumn.setCellValueFactory(new PropertyValueFactory<>("ticketId"));
            airlineCodeColumn.setCellValueFactory(new PropertyValueFactory<>("codeOfName"));
            airlineFullNameColumn.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
            cityLeavingColumn.setCellValueFactory(new PropertyValueFactory<>("leaveCity"));
            cityArrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalCity"));
            leavingAirportColumn.setCellValueFactory(new PropertyValueFactory<>("leaveAirport"));
            arrivalAirportColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalAirport"));
            leavingTimeColumn.setCellValueFactory(new PropertyValueFactory<>("leaveHour"));
            arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalHour"));
            price1stClassColumn.setCellValueFactory(new PropertyValueFactory<>("firstClass"));
            price2ndClassColumn.setCellValueFactory(new PropertyValueFactory<>("secondClass"));
            leavingDateColumn.setCellValueFactory(new PropertyValueFactory<>("leaveDate"));
            arrivalDateColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
        } catch (Exception e) {
            System.out.println("error at connecting to the database " + e);
        }


    }

    public void initializePlaneInfo() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            planeThingsList = dataBaseOpp.getPlaneInfo(connection);
            planeInfoTable.setItems(planeThingsList);
            ticketIdPlaneColumn.setCellValueFactory(new PropertyValueFactory<>("ticketId"));
            planeCodeColumn.setCellValueFactory(new PropertyValueFactory<>("planeName"));
            totalSeatsColumn.setCellValueFactory(new PropertyValueFactory<>("totalSeats"));
            availableSeatsColumn.setCellValueFactory(new PropertyValueFactory<>("availableSeats"));
            firstClassColumn.setCellValueFactory(new PropertyValueFactory<>("firstClass"));
            secondClassColumn.setCellValueFactory(new PropertyValueFactory<>("secondClass"));
        }catch (Exception e)
        {
            System.out.println("error at connecting to db "+e);
        }
    }

    public static class Ticket {
        private int ticketId;
        private String airlineName;
        private String codeOfName;
        private String leaveHour;
        private String arrivalHour;
        private String leaveAirport;
        private String arrivalAirport;
        private String leaveCity;
        private String arrivalCity;
        private String leaveDate;
        private String arrivalDate;
        private int firstClass;
        private int secondClass;

        public int getTicketId() {
            return ticketId;
        }

        public void setTicketId(int ticketId) {
            this.ticketId = ticketId;
        }

        public String getAirlineName() {
            return airlineName;
        }

        public void setAirlineName(String airlineName) {
            this.airlineName = airlineName;
        }

        public String getCodeOfName() {
            return codeOfName;
        }

        public void setCodeOfName(String codeOfName) {
            this.codeOfName = codeOfName;
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

        public String getLeaveAirport() {
            return leaveAirport;
        }

        public void setLeaveAirport(String leaveAirport) {
            this.leaveAirport = leaveAirport;
        }

        public String getArrivalAirport() {
            return arrivalAirport;
        }

        public void setArrivalAirport(String arrivalAirport) {
            this.arrivalAirport = arrivalAirport;
        }

        public String getLeaveCity() {
            return leaveCity;
        }

        public void setLeaveCity(String leaveCity) {
            this.leaveCity = leaveCity;
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

        public int getFirstClass() {
            return firstClass;
        }

        public void setFirstClass(int firstClass) {
            this.firstClass = firstClass;
        }

        public int getSecondClass() {
            return secondClass;
        }

        public void setSecondClass(int secondClass) {
            this.secondClass = secondClass;
        }

        public Ticket(int ticketId, String airlineName, String codeOfName, String leaveHour,
                      String arrivalHour, String leaveAirport, String arrivalAirport,
                      String leaveCity, String arrivalCity, String leaveDate, String arrivalDate,
                      int firstClass, int secondClass) {
            this.ticketId = ticketId;
            this.airlineName = airlineName;
            this.codeOfName = codeOfName;
            this.leaveHour = leaveHour;
            this.arrivalHour = arrivalHour;
            this.leaveAirport = leaveAirport;
            this.arrivalAirport = arrivalAirport;
            this.leaveCity = leaveCity;
            this.arrivalCity = arrivalCity;
            this.leaveDate = leaveDate;
            this.arrivalDate = arrivalDate;
            this.firstClass = firstClass;
            this.secondClass = secondClass;
        }
    }

    public static class PlaneThing{
        int ticketId;
        String planeName;
        int totalSeats;
        int availableSeats;

        int firstClass;

        public int getFirstClass() {
            return firstClass;
        }

        public void setFirstClass(int firstClass) {
            this.firstClass = firstClass;
        }

        public int getSecondClass() {
            return secondClass;
        }

        public void setSecondClass(int secondClass) {
            this.secondClass = secondClass;
        }

        int secondClass;

        public int getTicketId() {
            return ticketId;
        }

        public void setTicketId(int ticketId) {
            this.ticketId = ticketId;
        }

        public String getPlaneName() {
            return planeName;
        }

        public void setPlaneName(String planeName) {
            this.planeName = planeName;
        }

        public int getTotalSeats() {
            return totalSeats;
        }

        public void setTotalSeats(int totalSeats) {
            this.totalSeats = totalSeats;
        }

        public int getAvailableSeats() {
            return availableSeats;
        }

        public void setAvailableSeats(int availableSeats) {
            this.availableSeats = availableSeats;
        }

        public PlaneThing(int ticketId,String planeName,int totalSeats,int availableSeats,int firstClass,int secondClass)
        {
            this.ticketId=ticketId;
            this.planeName = planeName;
            this.totalSeats = totalSeats;
            this.availableSeats = availableSeats;
            this.firstClass = firstClass;
            this.secondClass =secondClass;
        }

    }
}
