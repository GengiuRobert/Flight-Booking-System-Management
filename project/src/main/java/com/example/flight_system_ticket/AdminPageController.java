package com.example.flight_system_ticket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.EventObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminPageController {

    @FXML
    TextField introduceArrivalCityTextField, introduceLeavingCityTextField, introduceAirlineNameTextField, introduceAirlineCodeTextField;
    @FXML
    TextField introduceFirstPriceTextField, introduceSecondClassTextField, introduceLeaveAirportTextField, introduceArriveAirportTextField;
    @FXML
    TextField introduceLeavingDateTextField, introduceArrivalDateTextField, introduceLeavingHourTextField, introduceArrivalHourTextField;
    @FXML
    TextField introducePlaneNameTextField, introduceTotalSeatsTextField, introduceFirstClassSeatsTextField, introduceSecondClassSeatsTextField;
    @FXML
    Label introduceCityLabel, introduceAirlineLabel, introducePriceLabel, introduceAirportLabel, introduceDateLabel, introduceHourLabel, introduceSeatsLabel;
    public Stage stage = new Stage(), primaryStage;

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void switch_to_admin_page() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin_page_view.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Admin Page");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public void showClientsDetails() throws IOException {
        AllClientsPageController allClientsPageController = new AllClientsPageController();
        allClientsPageController.show();
    }

    public void showTicketDetails() throws IOException {
        AllTicketsPageController allTicketsPageController = new AllTicketsPageController();
        allTicketsPageController.show();
    }

    public void introduceNewCitiesInDb() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            String leavingCity;
            String arrivalCity;
            if (!introduceArrivalCityTextField.getText().isEmpty() & !introduceLeavingCityTextField.getText().isEmpty()) {
                if (!dataBaseOpp.isArrivalCityInDB(connection, introduceArrivalCityTextField.getText()) &
                        !dataBaseOpp.isLeavingCityInDB(connection, introduceLeavingCityTextField.getText())) {
                    leavingCity = introduceLeavingCityTextField.getText();
                    arrivalCity = introduceArrivalCityTextField.getText();
                    dataBaseOpp.introduceCitiesInDb(connection, leavingCity, arrivalCity);
                    introduceCityLabel.setText("Cities introduced succesful");
                } else {
                    introduceCityLabel.setText("Some data are already in the database");
                }
            } else {
                introduceCityLabel.setText("Some fields are empty");
            }
        } catch (Exception e) {
            System.out.println("Error at connecting to db " + e);
        }
    }

    public void introduceNewAirlineInDb() {
        Connection connection = null;
        try {
            String airlineName;
            String airlineCode;
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            if (!introduceAirlineCodeTextField.getText().isEmpty() & !introduceAirlineNameTextField.getText().isEmpty()) {
                if (!dataBaseOpp.isCompanyInDB(connection, introduceAirlineCodeTextField.getText())) {
                    airlineCode = introduceAirlineCodeTextField.getText();
                    airlineName = introduceAirlineNameTextField.getText();
                    dataBaseOpp.introduceAirlineInDb(connection, airlineName, airlineCode);
                    introduceAirlineLabel.setText("Airline introduced succesful");
                } else {
                    introduceAirlineLabel.setText("Airline already in database");
                }
            } else {
                introduceAirlineLabel.setText("Some fields are empty");
            }
        } catch (Exception e) {
            System.out.println("error at connecting to db " + e);
        }
    }

    public void introduceNewPricesInDb() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");

            if (!introduceFirstPriceTextField.getText().isEmpty() & !introduceSecondClassTextField.getText().isEmpty()) {
                if (!dataBaseOpp.isFirstPriceClassInDb(connection, Integer.parseInt(introduceFirstPriceTextField.getText())) &
                        !dataBaseOpp.isSecondPriceClassInDb(connection, Integer.parseInt(introduceFirstPriceTextField.getText())
                        )) {
                    int firstClass = Integer.parseInt(introduceFirstPriceTextField.getText());
                    int secondClass = Integer.parseInt(introduceSecondClassTextField.getText());
                    dataBaseOpp.introducePricesInDb(connection, firstClass, secondClass);
                    introducePriceLabel.setText("Prices introduced succesful");
                } else {
                    introducePriceLabel.setText("Some data already in database");
                }
            } else {
                introducePriceLabel.setText("Some fields are empty");
            }
        } catch (Exception e) {
            System.out.println("error at connecting to db " + e);
        }
    }

    public void introduceNewAirportInDb() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            if (!introduceArriveAirportTextField.getText().isEmpty() & !introduceLeaveAirportTextField.getText().isEmpty()) {
                if (!dataBaseOpp.isArrivalAirportInDb(connection, introduceArriveAirportTextField.getText()) &
                        !dataBaseOpp.isLeavingAirportInDb(connection, introduceLeaveAirportTextField.getText())) {
                    String leaveAirport = introduceLeaveAirportTextField.getText();
                    String arriveAirport = introduceArriveAirportTextField.getText();
                    dataBaseOpp.introduceAirportsInDb(connection, leaveAirport, arriveAirport);
                    introduceAirportLabel.setText("Airports introduced succesful");
                }
            } else {
                introduceAirportLabel.setText("Some fields are empty");
            }
        } catch (Exception e) {
            System.out.println("error at connecting to db " + e);
        }
    }

    public void introduceNewDatesInDb() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            if (!introduceArrivalDateTextField.getText().isEmpty() & !introduceLeavingDateTextField.getText().isEmpty()) {
                if (DateValidator.isValidDate(introduceArrivalDateTextField.getText()) &
                        DateValidator.isValidDate(introduceLeavingDateTextField.getText())) {
                    if (!dataBaseOpp.isArrivalDateInDb(connection, introduceArrivalDateTextField.getText())
                            & !dataBaseOpp.isLeaveDateInDb(connection, introduceLeavingDateTextField.getText())) {
                        String leavingDate = introduceArrivalDateTextField.getText();
                        String arrivalDate = introduceLeavingDateTextField.getText();
                        dataBaseOpp.introduceDatesInDb(connection, arrivalDate, leavingDate);
                        introduceDateLabel.setText("Dates introduced succesful");
                    }
                } else {
                    introduceDateLabel.setText("Invalid date format");
                }
            } else {
                introduceDateLabel.setText("Some fields are empty");
            }

        } catch (Exception e) {
            System.out.println("Error at connecting to db " + e);
        }
    }

    public void introduceNewHoursInDb() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            if (!introduceArrivalHourTextField.getText().isEmpty() & !introduceLeavingHourTextField.getText().isEmpty()) {
                if (TimeValidator.isValidTime(introduceArrivalHourTextField.getText()) &
                        TimeValidator.isValidTime(introduceLeavingHourTextField.getText())) {
                    if (!dataBaseOpp.isArrivalTimeInDb(connection, introduceArrivalHourTextField.getText()) &
                            !dataBaseOpp.isLeavingTimeInDb(connection, introduceLeavingHourTextField.getText())) {
                        String leavingTime = introduceLeavingHourTextField.getText();
                        String arrivalTime = introduceArrivalHourTextField.getText();
                        dataBaseOpp.introduceTimeInDb(connection, leavingTime, arrivalTime);
                        introduceHourLabel.setText("Hours introduced succesful");
                    } else {
                        introduceHourLabel.setText("Some data are already in database");
                    }
                } else {
                    introduceHourLabel.setText("Invalid time format");
                }
            } else {
                introduceHourLabel.setText("Some fields are empty");
            }
        } catch (Exception e) {
            System.out.println("Error at connecting to db " + e);
        }
    }

    public void introduceNewPlaneInDb() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            if (!introducePlaneNameTextField.getText().isEmpty() & !introduceTotalSeatsTextField.getText().isEmpty() &
                    !introduceFirstClassSeatsTextField.getText().isEmpty() & !introduceSecondClassSeatsTextField.getText().isEmpty()) {
                if (!dataBaseOpp.isPlaneInDb(connection, introducePlaneNameTextField.getText())) {
                    int firstClassSeatsNumber = Integer.parseInt(introduceFirstClassSeatsTextField.getText());
                    int secondClassSeatsNumber = Integer.parseInt(introduceSecondClassSeatsTextField.getText());
                    int totalSeatsNumber = Integer.parseInt(introduceTotalSeatsTextField.getText());
                    if (firstClassSeatsNumber + secondClassSeatsNumber != totalSeatsNumber) {
                        String planeName = introducePlaneNameTextField.getText();
                        dataBaseOpp.introducePlaneInDb(connection, planeName, totalSeatsNumber, firstClassSeatsNumber, secondClassSeatsNumber);
                    } else {
                        introduceSeatsLabel.setText("FirstClass + SecondClass != TotalSeats");
                    }
                } else {
                    introduceSeatsLabel.setText("Some data already in database");
                }
            } else {
                introduceSeatsLabel.setText("Some fields are empty");
            }
        } catch (Exception e) {
            System.out.println("Error at connecting to db " + e);
        }
    }

    public void goToIntroduceNewTicket() throws IOException {
        CreateNewTicketController createNewTicketController = new CreateNewTicketController();
        createNewTicketController.show();
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
