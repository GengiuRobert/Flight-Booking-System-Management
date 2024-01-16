package com.example.flight_system_ticket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;

public class CreateNewTicketController {

    @FXML
    TableView<Airline> airlineTableView;
    @FXML
    TableView<Airport> airportTableView;
    @FXML
    TableView<Plane> planeTableView;
    @FXML
    TableView<Date> dateTableView;
    @FXML
    TableView<City> cityTableView;
    @FXML
    TableView<Price> priceTableView;
    @FXML
    TableView<Hour> timeTableView;
    @FXML
    TableColumn<Integer, Hour> timeIdTableColumn;
    @FXML
    TableColumn<String, Hour> timeLeaveTableColumn, timeArrivalTableColumn;
    @FXML
    TableColumn<Integer, Price> priceIdTableColumn, priceFirstTableColumn, priceSecondTableColumn;
    @FXML
    TableColumn<City, Integer> cityIdTableColumn;
    @FXML
    TableColumn<City, String> cityLeavingTableColumn, cityArrivalTableColumn;
    @FXML
    TableColumn<Integer, Date> dateIdTableColumn;
    @FXML
    TableColumn<String, Date> dateLeavingTableColumn, dateArrivalTableColumn;
    @FXML
    TableColumn<Plane, Integer> planeIdTableColumn, planeTotalSeatsTableColumn, planeAvailableSeatsTableColumn, planeSecondClassTableColumn, planeFirstClassTableColumn;
    @FXML
    TableColumn<Plane, String> planeNameTableColumn;
    @FXML
    TableColumn<String, Airport> airportLeaveTableColumn, airportArrivalTableColumn;
    @FXML
    TableColumn<Integer, Airport> airportIdTableColumn;
    @FXML
    TableColumn<String, Airline> nameAirlineTableColumn, codeOfNameAirlineTableColumn;
    @FXML
    TableColumn<Integer, Airline> idAirlineTableColumn;
    @FXML
    TextField airlineCodeTextField, planeNameTextField, cityLeaveTextField, cityArriveTextField, dateLeaveTextField, dateArriveTextField, priceFirstTextField, priceSecondTextField;
    @FXML
    TextField timeLeaveTextField, timeArriveTextField, airportLeaveTextField, airportArriveTextField;
    @FXML
    Label createTicketLabel;

    public Stage newTicketStage = new Stage();

    public ObservableList<Airline> airlineObservableList = FXCollections.observableArrayList();
    public ObservableList<Airport> airportObservableList = FXCollections.observableArrayList();
    public ObservableList<Plane> planeObservableList = FXCollections.observableArrayList();
    public ObservableList<City> cityObservableList = FXCollections.observableArrayList();
    public ObservableList<Date> dateObservableList = FXCollections.observableArrayList();
    public ObservableList<Price> priceObservableList = FXCollections.observableArrayList();
    public ObservableList<Hour> hourObservableList = FXCollections.observableArrayList();

    public void show() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("createnewticketpage.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        newTicketStage.setTitle("Create New Ticket Page");
        newTicketStage.setScene(scene);
        newTicketStage.show();
        newTicketStage.setResizable(false);
        initializeAirlines();
        initializeAirport();
        initializePlanes();
        initializeDates();
        initializeCities();
        initializePrices();
        initializeHours();
    }

    public void initializeAirlines() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            airlineObservableList = dataBaseOpp.getAirlineDetails(connection);
            airlineTableView.setItems(airlineObservableList);
            idAirlineTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameAirlineTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            codeOfNameAirlineTableColumn.setCellValueFactory(new PropertyValueFactory<>("codeOfName"));
        } catch (Exception e) {
            System.out.println("error at connecting to db " + e);
        }

    }

    public void initializeAirport() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            airportObservableList = dataBaseOpp.getAirportDetails(connection);
            airportTableView.setItems(airportObservableList);
            airportIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            airportLeaveTableColumn.setCellValueFactory(new PropertyValueFactory<>("leaveAirport"));
            airportArrivalTableColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalAirport"));
        } catch (Exception e) {
            System.out.println("error at connecting to db " + e);
        }
    }

    public void initializePlanes() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            planeObservableList = dataBaseOpp.getPlanesDetails(connection);
            planeTableView.setItems(planeObservableList);
            planeIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            planeNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("planeName"));
            planeTotalSeatsTableColumn.setCellValueFactory(new PropertyValueFactory<>("totalSeats"));
            planeAvailableSeatsTableColumn.setCellValueFactory(new PropertyValueFactory<>("availableSeats"));
            planeSecondClassTableColumn.setCellValueFactory(new PropertyValueFactory<>("secondSeats"));
            planeFirstClassTableColumn.setCellValueFactory(new PropertyValueFactory<>("firstSeats"));
        } catch (Exception e) {
            System.out.println("error at connecting to db " + e);
        }
    }

    public void initializeDates() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            dateObservableList = dataBaseOpp.getDatesDetails(connection);
            dateTableView.setItems(dateObservableList);
            dateIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            dateLeavingTableColumn.setCellValueFactory(new PropertyValueFactory<>("leaveDate"));
            dateArrivalTableColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
        } catch (Exception e) {
            System.out.println("error at connecting to db " + e);
        }
    }

    public void initializeCities() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            cityObservableList = dataBaseOpp.getCityDetails(connection);
            cityTableView.setItems(cityObservableList);
            cityIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            cityLeavingTableColumn.setCellValueFactory(new PropertyValueFactory<>("leaveCity"));
            cityArrivalTableColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalCity"));
        } catch (Exception e) {
            System.out.println("Error at connecting to db " + e);
        }
    }

    public void initializePrices() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            priceObservableList = dataBaseOpp.getPriceDetails(connection);
            priceTableView.setItems(priceObservableList);
            priceIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            priceFirstTableColumn.setCellValueFactory(new PropertyValueFactory<>("firstClass"));
            priceSecondTableColumn.setCellValueFactory(new PropertyValueFactory<>("secondClass"));
        } catch (Exception e) {
            System.out.println("error at connecting to db " + e);
        }
    }

    public void initializeHours() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            hourObservableList = dataBaseOpp.getHoursDetails(connection);
            timeTableView.setItems(hourObservableList);
            timeIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            timeLeaveTableColumn.setCellValueFactory(new PropertyValueFactory<>("leaveHour"));
            timeArrivalTableColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalHour"));
        } catch (Exception e) {
            System.out.println("error at connecting to db " + e);
        }
    }

    public void createNewTicket() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            if (!airlineCodeTextField.getText().isEmpty() & !airportArriveTextField.getText().isEmpty() & !airportLeaveTextField.getText().isEmpty() &
                    !cityArriveTextField.getText().isEmpty() & !cityLeaveTextField.getText().isEmpty() & !dateArriveTextField.getText().isEmpty() &
                    !dateLeaveTextField.getText().isEmpty() & !planeNameTextField.getText().isEmpty() & !priceFirstTextField.getText().isEmpty() &
                    !priceSecondTextField.getText().isEmpty() & !timeArriveTextField.getText().isEmpty() & !timeLeaveTextField.getText().isEmpty()) {
                String airline = airlineCodeTextField.getText();
                String airportArrival = airportArriveTextField.getText();
                String airportLeave = airportLeaveTextField.getText();
                String cityArrival = cityArriveTextField.getText();
                String cityLeave = cityLeaveTextField.getText();
                String dateArrival = dateArriveTextField.getText();
                String dateLeave = dateLeaveTextField.getText();
                String planeName = planeNameTextField.getText();
                int firstPrice = Integer.parseInt(priceFirstTextField.getText());
                int secondPrice = Integer.parseInt(priceSecondTextField.getText());
                String timeArrival = timeArriveTextField.getText();
                String timeLeave = timeLeaveTextField.getText();
                if (dataBaseOpp.isPlaneInDb(connection, planeName) & dataBaseOpp.isLeavingTimeInDb(connection, timeLeave) & dataBaseOpp.isArrivalTimeInDb(connection, timeArrival) &
                        dataBaseOpp.isLeaveDateInDb(connection, dateLeave) & dataBaseOpp.isArrivalDateInDb(connection, dateArrival) & dataBaseOpp.isLeavingCityInDB(connection, cityLeave) &
                        dataBaseOpp.isArrivalCityInDB(connection, cityArrival) & dataBaseOpp.isSecondPriceClassInDb(connection, secondPrice) & dataBaseOpp.isFirstPriceClassInDb(connection, firstPrice) &
                        dataBaseOpp.isArrivalAirportInDb(connection, airportArrival) & dataBaseOpp.isLeavingAirportInDb(connection, airportLeave) & dataBaseOpp.isCompanyInDB(connection, airline)) {
                    dataBaseOpp.createNewTicket(connection, airline, cityLeave, cityArrival, dateLeave, dateArrival, firstPrice, secondPrice, timeLeave, timeArrival,
                            airportLeave, airportArrival, planeName);
                    createTicketLabel.setText("Ticket created successfully");
                } else {
                    createTicketLabel.setText("Some data are not in database");
                }
            } else {
                createTicketLabel.setText("Some fields are empty");
            }
        } catch (Exception e) {
            System.out.println("error at connecting to db " + e);
        }
    }

    public static class Airline {
        int id;
        String name;
        String codeOfName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCodeOfName() {
            return codeOfName;
        }

        public void setCodeOfName(String codeOfName) {
            this.codeOfName = codeOfName;
        }

        public Airline(int id, String name, String codeOfName) {
            this.id = id;
            this.name = name;
            this.codeOfName = codeOfName;
        }

    }

    public static class Airport {
        int id;
        String leaveAirport;
        String arrivalAirport;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public Airport(int id, String leaveAirport, String arrivalAirport) {
            this.id = id;
            this.leaveAirport = leaveAirport;
            this.arrivalAirport = arrivalAirport;
        }
    }

    public static class Date {
        int id;
        String leaveDate;
        String arrivalDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public Date(int id, String leaveDate, String arrivalDate) {
            this.id = id;
            this.leaveDate = leaveDate;
            this.arrivalDate = arrivalDate;
        }
    }

    public static class Plane {
        int id;
        String planeName;
        int totalSeats;
        int availableSeats;
        int secondSeats;
        int firstSeats;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getSecondSeats() {
            return secondSeats;
        }

        public void setSecondSeats(int secondSeats) {
            this.secondSeats = secondSeats;
        }

        public int getFirstSeats() {
            return firstSeats;
        }

        public void setFirstSeats(int firstSeats) {
            this.firstSeats = firstSeats;
        }

        public Plane(int id, String planeName, int totalSeats, int availableSeats, int secondSeats, int firstSeats) {
            this.id = id;
            this.planeName = planeName;
            this.availableSeats = availableSeats;
            this.totalSeats = totalSeats;
            this.secondSeats = secondSeats;
            this.firstSeats = firstSeats;
        }
    }

    public static class City {
        int id;
        String leaveCity;
        String arrivalCity;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public City(int id, String leaveCity, String arrivalCity) {
            this.id = id;
            this.leaveCity = leaveCity;
            this.arrivalCity = arrivalCity;
        }
    }

    public static class Price {
        int id;
        int firstClass;
        int secondClass;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public Price(int id, int firstClass, int secondClass) {
            this.id = id;
            this.firstClass = firstClass;
            this.secondClass = secondClass;
        }
    }

    public static class Hour {
        int id;
        String leaveHour;
        String arrivalHour;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public Hour(int id, String leaveHour, String arrivalHour) {
            this.id = id;
            this.leaveHour = leaveHour;
            this.arrivalHour = arrivalHour;
        }

    }
}
