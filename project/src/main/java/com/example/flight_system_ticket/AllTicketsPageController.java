package com.example.flight_system_ticket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AllTicketsPageController {

    @FXML
    TableView<Ticket> ticketsTableView;
    @FXML
    TableColumn<Ticket, String> airlineCodeColumn, airlineFullNameColumn, cityLeavingColumn, cityArrivalColumn, leavingAirportColumn, arrivalAirportColumn;
    @FXML
    TableColumn<Ticket, String> arrivalTimeColumn, leavingTimeColumn, leavingDateColumn, arrivalDateColumn;
    @FXML
    TableColumn<Ticket, Integer> price1stClassColumn, price2ndClassColumn, ticketIDColumn;
    @FXML
    TextField ticketIDTextField, airlineCodeTextField, airlineTextField, cityOfLeavingTextField, cityOfArrivalTextField, leavingAirportTextField, arrivalAirportTextField;
    @FXML
    TextField leavingTimeTextField, arrivalTimeTextField, leavingDateTextField, arrivalDateTextFIeld, price1stClassTextField, price2ndClassTextField;
    @FXML
    Label modifyTicketLabel;
    public Stage allTicketsPage = new Stage();
    public ObservableList<Ticket> ticketList = FXCollections.observableArrayList();

    public void show() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("allTicketsPage.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        allTicketsPage.setTitle("All Tickets Page");
        allTicketsPage.setScene(scene);
        allTicketsPage.show();
        allTicketsPage.setResizable(false);
        initializeTickets();
    }

    boolean isValidDateArrival, isValidDateLeaving, isValidTimeArrival, isValidTimeLeaving;

    @FXML
    public void initialize() {
        ticketsTableView.setRowFactory(tv -> {
            TableRow<Ticket> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    Ticket selectedTicket = row.getItem();
                    displayTicketDetails(selectedTicket);
                }
            });
            return row;
        });
    }

    private void displayTicketDetails(Ticket ticket) {
        ticketIDTextField.setEditable(false);
        ticketIDTextField.setText(String.valueOf(ticket.getTicketId()));
        airlineCodeTextField.setText(ticket.getCodeOfName());
        airlineTextField.setEditable(false);
        airlineTextField.setText(ticket.getAirlineName());
        cityOfLeavingTextField.setText(ticket.getLeaveCity());
        cityOfArrivalTextField.setText(ticket.getArrivalCity());
        leavingAirportTextField.setText(ticket.getLeaveAirport());
        arrivalAirportTextField.setText(ticket.getArrivalAirport());
        leavingTimeTextField.setText(ticket.getLeaveHour());
        arrivalTimeTextField.setText(ticket.getArrivalHour());
        price1stClassTextField.setText(String.valueOf(ticket.getFirstClass()));
        price2ndClassTextField.setText(String.valueOf(ticket.getSecondClass()));
        leavingDateTextField.setText(ticket.getLeaveDate());
        arrivalDateTextFIeld.setText(ticket.getArrivalDate());
    }

    public void initializeTickets() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            ticketList = dataBaseOpp.getTickets2(connection);
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

    public void modifyTicket() {
        Connection connection = null;
        try {
            isValidDateArrival = false;
            isValidDateLeaving = false;
            isValidTimeArrival = false;
            isValidTimeLeaving = false;

            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            int ticketId = Integer.parseInt(ticketIDTextField.getText());
            int leavingTimeId = 0;
            int leavingDateId = 0;
            int leavingCityId = 0;
            int arrivalCityId = 0;
            int companyId = 0;
            int leavingAirportId = 0;
            int arrivalDateId = 0;
            int arrivalAirportId = 0;
            int arrivalTimeId = 0;
            int price1stClassId = 0;
            int price2ndClassId = 0;

            boolean isLeaveCityInDb = dataBaseOpp.isLeavingCityInDB(connection, cityOfLeavingTextField.getText());
            boolean isArrivalCityInDb = dataBaseOpp.isArrivalCityInDB(connection, cityOfArrivalTextField.getText());
            boolean isCompanyInDb = dataBaseOpp.isCompanyInDB(connection, airlineCodeTextField.getText());
            boolean isLeavingAirportInDb = dataBaseOpp.isLeavingAirportInDb(connection, leavingAirportTextField.getText());
            boolean isArrivalAirportInDb = dataBaseOpp.isArrivalAirportInDb(connection, arrivalAirportTextField.getText());
            boolean isLeavingDateInDb = dataBaseOpp.isLeaveDateInDb(connection, leavingDateTextField.getText());
            boolean isArrivalDateInDb = dataBaseOpp.isArrivalDateInDb(connection, arrivalDateTextFIeld.getText());
            boolean isLeavingTimeInDb = dataBaseOpp.isLeavingTimeInDb(connection, leavingTimeTextField.getText());
            boolean isArrivalTimeInDb = dataBaseOpp.isArrivalTimeInDb(connection, arrivalTimeTextField.getText());
            boolean isFirstPriceInDb = dataBaseOpp.isFirstPriceClassInDb(connection, Integer.parseInt(price1stClassTextField.getText()));
            boolean isSecondPriceInDb = dataBaseOpp.isSecondPriceClassInDb(connection, Integer.parseInt(price2ndClassTextField.getText()));

            if (isLeaveCityInDb & isArrivalCityInDb & isCompanyInDb & isLeavingAirportInDb
                    & isArrivalAirportInDb & isLeavingDateInDb & isArrivalDateInDb & isLeavingTimeInDb & isArrivalTimeInDb
                    & isFirstPriceInDb & isSecondPriceInDb) {

                leavingCityId = dataBaseOpp.getIDCityOfLeavingFromTicket(connection, cityOfLeavingTextField.getText());
                arrivalCityId = dataBaseOpp.getIDCityOfArrivalFromTicket(connection, cityOfArrivalTextField.getText());
                companyId = dataBaseOpp.getIDofAirlineCodeFromTicket(connection, airlineCodeTextField.getText());
                leavingAirportId = dataBaseOpp.getIdLeavingAirportFromTicket(connection, leavingAirportTextField.getText());
                arrivalAirportId = dataBaseOpp.getIdArrivalAirportFromTicket(connection, arrivalAirportTextField.getText());

                if (TimeValidator.isValidTime(leavingTimeTextField.getText())) {
                    leavingTimeId = dataBaseOpp.getIdLeavingTimeFromTicket(connection, leavingTimeTextField.getText());
                    isValidTimeLeaving = true;
                } else {
                    modifyTicketLabel.setText("Invalid leaving time");
                }

                if (TimeValidator.isValidTime(arrivalTimeTextField.getText())) {
                    arrivalTimeId = dataBaseOpp.getIdArrivalTimeFromTicket(connection, arrivalTimeTextField.getText());
                    isValidTimeArrival = true;
                } else {
                    modifyTicketLabel.setText("Invalid arrival time");
                }
                if (DateValidator.isValidDate(leavingDateTextField.getText())) {
                    leavingDateId = dataBaseOpp.getIdLeavingDateFromTicket(connection, leavingDateTextField.getText());
                    isValidDateLeaving = true;
                } else {
                    modifyTicketLabel.setText("Invalid leaving date");
                }
                if (DateValidator.isValidDate(arrivalDateTextFIeld.getText())) {
                    arrivalDateId = dataBaseOpp.getIdArrivalDateFromTicket(connection, arrivalDateTextFIeld.getText());
                    isValidDateArrival = true;
                } else {
                    modifyTicketLabel.setText("Invalid arrival date");
                }
                price1stClassId = dataBaseOpp.getIdPrice1stClassFromTicket(connection, Integer.parseInt(price1stClassTextField.getText()));
                price2ndClassId = dataBaseOpp.getIdPrice2ndClassFromTicket(connection, Integer.parseInt(price2ndClassTextField.getText()));


                if (isValidDateArrival & isValidDateLeaving & isValidTimeLeaving & isValidTimeArrival) {
                    dataBaseOpp.updateTicketInformations(connection, leavingCityId, arrivalCityId, companyId, leavingAirportId,
                            arrivalAirportId, leavingTimeId, arrivalTimeId, leavingDateId, arrivalDateId, price1stClassId, price2ndClassId, ticketId);
                    initializeTickets();
                    modifyTicketLabel.setText("Ticket modified successfully");
                }
            } else {
                modifyTicketLabel.setText("Some data are not in the database");
            }
        } catch (Exception e) {
            System.out.println("error at connecting to db " + e);
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

    public static class DateValidator {
        private static final String DATE_PATTERN = "^(?:20[0-4]\\d|2050)-(?:0[1-9]|1[0-2])-(?:0[1-9]|[12]\\d|3[01])$";

        public DateValidator() {
        }

        public static boolean isValidDate(String date) {
            Pattern pattern = Pattern.compile("^(?:20[0-4]\\d|2050)-(?:0[1-9]|1[0-2])-(?:0[1-9]|[12]\\d|3[01])$");
            Matcher matcher = pattern.matcher(date);
            if (!matcher.matches()) {
                System.out.println("Format de data incorect: " + date);
                return false;
            } else {
                int year = Integer.parseInt(date.substring(0, 4));
                int month = Integer.parseInt(date.substring(5, 7));
                int day = Integer.parseInt(date.substring(8, 10));
                if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
                    System.out.println("Zi incorecta pentru luna: " + date);
                    return false;
                } else {
                    if (month == 2) {
                        if (isLeapYear(year) && day > 29) {
                            System.out.println("Zi incorecta pentru luna februarie (an bisect): " + date);
                            return false;
                        }

                        if (!isLeapYear(year) && day > 28) {
                            System.out.println("Zi incorecta pentru luna februarie (an nebisect): " + date);
                            return false;
                        }

                        return true;
                    }

                    return true;
                }
            }
        }


        private static boolean isLeapYear(int year) {
            return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
        }
    }

    public static class TimeValidator {
        private static final String TIME_PATTERN = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$";

        public TimeValidator() {
        }

        public static boolean isValidTime(String time) {
            Pattern pattern = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
            Matcher matcher = pattern.matcher(time);
            return matcher.matches();
        }
    }

}
