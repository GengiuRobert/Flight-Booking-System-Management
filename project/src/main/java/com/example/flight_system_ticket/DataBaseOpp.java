package com.example.flight_system_ticket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBaseOpp {

    public Connection connect_to_db(String db_name, String user, String password) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + db_name, user, password);
            if (connection != null) {
                System.out.println("Connection established");
            } else {
                System.out.println("Connection failed");
            }
        } catch (Exception e) {
            System.out.println("Error at connecting to db " + e);
        }
        return connection;
    }

    public void insert_row_in_clients_table(Connection connection, String table_name, String email, String client_name, String password) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO " + table_name + " (email,password,name,balance_money) VALUES (?,?,?,0);";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, client_name);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error at creating an account " + e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    System.out.println(e + "Error at closing statement");
                }
            }
        }
    }

    public boolean search_for_account_in_database(Connection connection, String table_name, String email, String password) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM " + table_name + " WHERE email = ? AND password = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            System.out.println(e + "Error at selecting from table " + table_name);
            return false;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error at closing result set or statement: " + e);
            }
        }
    }

    public int retrieve_money_for_client(Connection connection, String table_name, String email) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT balance_money FROM " + table_name + " WHERE email = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("balance_money");
            } else {
                System.out.println("Error at query ");
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Error at retrieving money " + e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error at closing result set or statement: " + e);
            }
        }
        return -1;
    }

    public String retrieve_name_of_client(Connection connection, String table_name, String email) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT name FROM " + table_name + " WHERE email = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            String var7;
            if (resultSet.next()) {
                var7 = resultSet.getString("name");
                return var7;
            }

            System.out.println("Error at query ");
            var7 = "error at query";
            return var7;
        } catch (Exception var18) {
            System.out.println("Error at retrieving name of client " + var18);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException var17) {
                System.out.println("Error at closing result set or statement: " + var17);
            }

        }

        return "no name";
    }

    public List<String> retrieve_card_numbers_for_given_client(Connection connection, int clientId) {
        List<String> cardNumbers = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT card_number FROM credit_cards WHERE client_id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, clientId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cardNumbers.add(resultSet.getString("card_number"));
            }
        } catch (Exception e) {
            System.out.println("Error at retrieving card number " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                System.out.println("Error at closing result set or statement: " + e);
            }

        }
        return cardNumbers;
    }

    public List<String> retrieve_cvv_numbers_for_given_client(Connection connection, int clientId) {
        List<String> cvvNumbers = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT cvv FROM credit_cards WHERE client_id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, clientId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cvvNumbers.add(resultSet.getString("cvv"));
            }
        } catch (Exception e) {
            System.out.println("Error at retrieving cvv number " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                System.out.println("Error at closing result set or statement: " + e);
            }

        }
        return cvvNumbers;
    }

    public List<String> retrieve_expiration_dates_for_given_client(Connection connection, int clientId) {
        List<String> expirationDates = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT expiration_date FROM credit_cards WHERE client_id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, clientId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                expirationDates.add(resultSet.getString("expiration_date"));
            }
        } catch (Exception e) {
            System.out.println("Error at retrieving expiration date " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                System.out.println("Error at closing result set or statement: " + e);
            }

        }
        return expirationDates;
    }

    public List<String> retrieve_card_types_for_given_client(Connection connection, int clientId) {
        List<String> cardTypes = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT card_type FROM credit_cards WHERE client_id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, clientId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cardTypes.add(resultSet.getString("card_type"));
            }
        } catch (Exception e) {
            System.out.println("Error at retrieving card type " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                System.out.println("Error at closing result set or statement: " + e);
            }

        }
        return cardTypes;
    }

    public int retrieve_client_id(Connection connection, String table_name, String email) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT id FROM " + table_name + " WHERE email = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Error at retrieving client id " + e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error at closing result set or statement: " + e);
            }
        }
        return -1;
    }

    public List<String> retrieve_card_IDs_for_given_client(Connection connection, int clientId) {
        List<String> cardIDs = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT id FROM credit_cards WHERE client_id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, clientId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cardIDs.add(resultSet.getString("id"));
            }
        } catch (Exception e) {
            System.out.println("Error at retrieving card ID " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                System.out.println("Error at closing result set or statement: " + e);
            }

        }
        return cardIDs;
    }

    public int total_credit_cards_number_for_given_client(Connection connection, int clientID) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT clients.id AS client_id, clients.name AS client_name, COUNT(credit_cards.id) AS total_credit_cards " +
                    "FROM clients" +
                    " LEFT JOIN credit_cards ON clients.id = credit_cards.client_id" +
                    " WHERE clients.id = ? GROUP BY clients.id, clients.name;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, clientID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("total_credit_cards");
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Error at retrieving total number of credit cards for the client " + e);
        }

        return -1;
    }

    public void register_new_credit_card(Connection connection, int clientID, String card_number, String cvv, String expiration_date, String card_type) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO credit_cards (client_id, cvv, card_number, expiration_date, card_type) " +
                    "VALUES (?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, clientID);
            preparedStatement.setString(2, cvv);
            preparedStatement.setString(3, card_number);
            preparedStatement.setString(4, expiration_date);
            preparedStatement.setString(5, card_type);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error at registering a new card " + e);

        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    System.out.println(e + "Error at closing statement");
                }
            }
        }
    }

    public void delete_a_credit_card(Connection connection, String card_number, int clientId) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "DELETE FROM credit_cards WHERE card_number = ? AND client_id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, card_number);
            preparedStatement.setInt(2, clientId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Delete was successful");
                ;
            } else {
                System.out.println("No credit card found with that number");
            }
        } catch (Exception e) {
            System.out.println("Error at deleting a credit card " + e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    System.out.println(e + "Error at closing statement");
                }
            }
        }
    }

    public void insert_money_to_client(Connection connection, String card_number, String cvv, String expiration_date, int clientId, int money) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "UPDATE clients SET balance_money = balance_money + ? FROM credit_cards " +
                    "WHERE clients.id = ? AND clients.id = credit_cards.client_id " +
                    "AND credit_cards.card_number = ? AND credit_cards.cvv = ? " +
                    "AND credit_cards.expiration_date = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, money);
            preparedStatement.setInt(2, clientId);
            preparedStatement.setString(3, card_number);
            preparedStatement.setString(4, cvv);
            preparedStatement.setString(5, expiration_date);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insert successful");
            } else {
                System.out.println("Insert went wrong");
            }
        } catch (Exception e) {
            System.out.println("Error at inserting money " + e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error at closing statement: " + e);
            }
        }
    }

    public boolean isCreditCardRegistered(Connection connection, int clientId, String card_number) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT COUNT(*) FROM credit_cards WHERE card_number= ? AND client_id= ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, card_number);
            preparedStatement.setInt(2, clientId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) > 0;
        } catch (Exception e) {
            System.out.println("Error at searching the credit card " + e);
        }
        return false;
    }

    public ObservableList<TicketsController.Ticket> getTickets(Connection connection) {
        ObservableList<TicketsController.Ticket> tickets = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT\n" +
                    "    t.id AS ticket_id,\n" +
                    "    a.code_of_name AS airline_code,\n" +
                    "    a.name AS airline_name,\n" +
                    "    leave_city.leave_city AS leave_city,\n" +
                    "    arrival_city.arrival_city AS arrival_city,\n" +
                    "    leave_date.leave_date AS leave_date,\n" +
                    "    arrival_date.arrival_date AS arrival_date,\n" +
                    "    first_class.first_class AS first_class_price,\n" +
                    "    second_class.second_class AS second_class_price,\n" +
                    "    leave_hour.leave_hour AS leave_hour,\n" +
                    "    arrival_hour.arrival_hour AS arrival_hour,\n" +
                    "    leave_airport.leave_airport AS leave_airport,\n" +
                    "    arrival_airport.arrival_airport AS arrival_airport\n" +
                    "FROM\n" +
                    "    tickets t\n" +
                    "JOIN airlines a ON t.company_id = a.id\n" +
                    "JOIN city_info leave_city ON t.leave_city_id = leave_city.id\n" +
                    "JOIN city_info arrival_city ON t.arrival_city_id = arrival_city.id\n" +
                    "JOIN date_info leave_date ON t.leave_date_id = leave_date.id\n" +
                    "JOIN date_info arrival_date ON t.arrival_date_id = arrival_date.id\n" +
                    "JOIN price_info first_class ON t.first_class_id = first_class.id\n" +
                    "JOIN price_info second_class ON t.second_class_id = second_class.id\n" +
                    "JOIN hour_info leave_hour ON t.leave_hour_id = leave_hour.id\n" +
                    "JOIN hour_info arrival_hour ON t.arrival_hour_id = arrival_hour.id\n" +
                    "JOIN airport_info leave_airport ON t.leave_airport_id = leave_airport.id\n" +
                    "JOIN airport_info arrival_airport ON t.arrival_airport_id = arrival_airport.id;";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TicketsController.Ticket ticket = new TicketsController.Ticket(
                        resultSet.getInt("ticket_id"),
                        resultSet.getString("airline_name"),
                        resultSet.getString("airline_code"),
                        resultSet.getString("leave_hour"),
                        resultSet.getString("arrival_hour"),
                        resultSet.getString("leave_airport"),
                        resultSet.getString("arrival_airport"),
                        resultSet.getString("leave_city"),
                        resultSet.getString("arrival_city"),
                        resultSet.getString("leave_date"),
                        resultSet.getString("arrival_date"),
                        resultSet.getInt("first_class_price"),
                        resultSet.getInt("second_class_price")
                );
                tickets.add(ticket);
            }
        } catch (Exception e) {
            System.out.println("Error at getting the tickets " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error at closing result set or statement: " + e);
            }
        }
        return tickets;
    }

    public ObservableList<AllTicketsPageController.Ticket> getTickets2(Connection connection) {
        ObservableList<AllTicketsPageController.Ticket> tickets = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT\n" +
                    "    t.id AS ticket_id,\n" +
                    "    a.code_of_name AS airline_code,\n" +
                    "    a.name AS airline_name,\n" +
                    "    leave_city.leave_city AS leave_city,\n" +
                    "    arrival_city.arrival_city AS arrival_city,\n" +
                    "    leave_date.leave_date AS leave_date,\n" +
                    "    arrival_date.arrival_date AS arrival_date,\n" +
                    "    first_class.first_class AS first_class_price,\n" +
                    "    second_class.second_class AS second_class_price,\n" +
                    "    leave_hour.leave_hour AS leave_hour,\n" +
                    "    arrival_hour.arrival_hour AS arrival_hour,\n" +
                    "    leave_airport.leave_airport AS leave_airport,\n" +
                    "    arrival_airport.arrival_airport AS arrival_airport\n" +
                    "FROM\n" +
                    "    tickets t\n" +
                    "JOIN airlines a ON t.company_id = a.id\n" +
                    "JOIN city_info leave_city ON t.leave_city_id = leave_city.id\n" +
                    "JOIN city_info arrival_city ON t.arrival_city_id = arrival_city.id\n" +
                    "JOIN date_info leave_date ON t.leave_date_id = leave_date.id\n" +
                    "JOIN date_info arrival_date ON t.arrival_date_id = arrival_date.id\n" +
                    "JOIN price_info first_class ON t.first_class_id = first_class.id\n" +
                    "JOIN price_info second_class ON t.second_class_id = second_class.id\n" +
                    "JOIN hour_info leave_hour ON t.leave_hour_id = leave_hour.id\n" +
                    "JOIN hour_info arrival_hour ON t.arrival_hour_id = arrival_hour.id\n" +
                    "JOIN airport_info leave_airport ON t.leave_airport_id = leave_airport.id\n" +
                    "JOIN airport_info arrival_airport ON t.arrival_airport_id = arrival_airport.id;";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AllTicketsPageController.Ticket ticket = new AllTicketsPageController.Ticket(
                        resultSet.getInt("ticket_id"),
                        resultSet.getString("airline_name"),
                        resultSet.getString("airline_code"),
                        resultSet.getString("leave_hour"),
                        resultSet.getString("arrival_hour"),
                        resultSet.getString("leave_airport"),
                        resultSet.getString("arrival_airport"),
                        resultSet.getString("leave_city"),
                        resultSet.getString("arrival_city"),
                        resultSet.getString("leave_date"),
                        resultSet.getString("arrival_date"),
                        resultSet.getInt("first_class_price"),
                        resultSet.getInt("second_class_price")
                );
                tickets.add(ticket);
            }
        } catch (Exception e) {
            System.out.println("Error at getting the tickets " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error at closing result set or statement: " + e);
            }
        }
        return tickets;
    }

    public int getPrice1stClassOfTicketByID(Connection connection, int ticketID) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT " +
                    "pi.first_class " +
                    "FROM " +
                    "tickets t " +
                    "JOIN " +
                    "price_info pi ON t.first_class_id = pi.id " +
                    "WHERE " +
                    "t.id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ticketID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("first_class");
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Error at extracting price 1st Class " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error at closing result set or statement: " + e);
            }
        }
        return -1;
    }

    public int getPrice2ndClassOfTicketByID(Connection connection, int ticketID) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT " +
                    "pi.second_class " +
                    "FROM " +
                    "tickets t " +
                    "JOIN " +
                    "price_info pi ON t.second_class_id = pi.id " +
                    "WHERE " +
                    "t.id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ticketID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("second_class");
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Error at extracting price 2nd Class " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error at closing result set or statement: " + e);
            }
        }
        return -1;
    }

    public void decreaseClientBalanceByID(Connection connection, int clientID, int ticketPrice) {
        PreparedStatement preparedStatement = null;
        try {
            String query = " UPDATE clients SET balance_money = balance_money -? WHERE clients.id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ticketPrice);
            preparedStatement.setInt(2, clientID);
            int rowsAffect = preparedStatement.executeUpdate();
            if (rowsAffect > 0) {
                System.out.println("Decrease was succesful");
            }
        } catch (Exception e) {
            System.out.println("Error at decreasing balance " + e);
        }
    }

    public void insertTicketToTransactions(Connection connection, int clientID, int ticketID, int price, String type,
                                           String leave_date, String arrival_date, String leave_city, String arrival_city, String leave_time, String arrival_time, int seatNumber) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO transactions (client_id, id_of_ticket,price,type,leave_date,arrival_date," +
                    "leave_city,arrival_city,leave_time,arrival_time,seat_number,is_seat_reserved) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,true);";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, clientID);
            preparedStatement.setInt(2, ticketID);
            preparedStatement.setInt(3, price);
            preparedStatement.setString(4, type);
            preparedStatement.setString(5, leave_date);
            preparedStatement.setString(6, arrival_date);
            preparedStatement.setString(7, leave_city);
            preparedStatement.setString(8, arrival_city);
            preparedStatement.setString(9, leave_time);
            preparedStatement.setString(10, arrival_time);
            preparedStatement.setInt(11, seatNumber);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insert into tickets succesful");
            }
        } catch (Exception e) {
            System.out.println("Error at insert into transactions");
        }
    }


    public String getLeaveDateOfTicketByID(Connection connection, int ticketID) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT di.leave_date AS leave_date " +
                    "FROM date_info di " +
                    "JOIN tickets t ON di.id = t.leave_date_id " +
                    "WHERE t.id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ticketID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("leave_date");
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error at getLeaveDateOfTicketByID " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error at closing result set or statement: " + e);
            }
        }
        return null;
    }


    public String getArrivalDateOfTicketByID(Connection connection, int ticketID) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT di.arrival_date AS arrival_date " +
                    "FROM date_info di " +
                    "JOIN tickets t ON di.id = t.arrival_date_id " +
                    "WHERE t.id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ticketID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("arrival_date");
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error at getArrivalDateOfTicketByID " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error at closing result set or statement: " + e);
            }
        }
        return null;
    }


    public String getArrivalCityOfTicketByID(Connection connection, int ticketID) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT ci.arrival_city AS arrival_city " +
                    "FROM city_info ci " +
                    "JOIN tickets t ON ci.id = t.arrival_city_id " +
                    "WHERE t.id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ticketID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("arrival_city");
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error at getArrivalCityOfTicketByID " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error at closing result set or statement: " + e);
            }
        }
        return null;
    }


    public String getLeaveCityOfTicketByID(Connection connection, int ticketID) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT ci.leave_city AS leave_city " +
                    "FROM city_info ci " +
                    "JOIN tickets t ON ci.id = t.leave_city_id " +
                    "WHERE t.id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ticketID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("leave_city");
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error at getLeaveCityOfTicketByID " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error at closing result set or statement: " + e);
            }
        }
        return null;
    }

    public String getLeaveHourOfTicketByID(Connection connection, int ticketID) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT hi.leave_hour AS leave_hour " +
                    "FROM hour_info hi " +
                    "JOIN tickets t ON hi.id = t.leave_hour_id " +
                    "WHERE t.id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ticketID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("leave_hour");
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error at getLeaveHourOfTicketByID " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error at closing result set or statement: " + e);
            }
        }
        return null;
    }


    public String getArrivalHourOfTicketByID(Connection connection, int ticketID) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT hi.arrival_hour AS arrival_hour " +
                    "FROM hour_info hi " +
                    "JOIN tickets t ON hi.id = t.arrival_hour_id " +
                    "WHERE t.id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ticketID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("arrival_hour");
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error at getArrivalHourOfTicketByID " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error at closing result set or statement: " + e);
            }
        }
        return null;
    }

    public void giveRatingToFlight(Connection connection, float ratingFlight, int ticketID, int clientID) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "UPDATE review_info SET rating = ? WHERE client_id = ? AND ticket_id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setFloat(1, ratingFlight);
            preparedStatement.setInt(2, clientID);
            preparedStatement.setInt(3, ticketID);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update succesful into reviews");
            } else {
                System.out.println("Nothing has changed");
            }
        } catch (Exception e) {
            System.out.println("Error at giveRatingToFlight " + e);
        }
    }

    public void insertTicketToReview(Connection connection, int clientID, int ticketID, float ratingFlight) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO review_info (ticket_id, rating, client_id) VALUES (?,?,?);";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ticketID);
            preparedStatement.setFloat(2, ratingFlight);
            preparedStatement.setInt(3, clientID);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("insertTicketToReview succesful");
            } else {
                System.out.println("Nothing happened");
            }
        } catch (Exception e) {
            System.out.println("Error at insertTicketToReview " + e);
        }
    }

    public void giveReviewToTicket(Connection connection, int clientID, int ticketID, String review) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "UPDATE review_info SET review = ? WHERE ticket_id =? AND client_id=?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, review);
            preparedStatement.setInt(2, ticketID);
            preparedStatement.setInt(3, clientID);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("giveReviewToTicket succesful");
            } else {
                System.out.println("Nothing happened");
            }
        } catch (Exception e) {
            System.out.println("giveReviewToTicket " + e);
        }
    }

    public void setReviewBackToDefault(Connection connection, int clientID, int ticketID) {
        PreparedStatement preparedStatement = null;
        try {
            String query = " UPDATE review_info " +
                    "SET review = DEFAULT " +
                    "WHERE ticket_id = ? AND client_id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ticketID);
            preparedStatement.setInt(2, clientID);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("setReviewBackToDefault succesful");
            } else {
                System.out.println("Nothing happened");
            }
        } catch (Exception e) {
            System.out.println("setReviewBackToDefault went wrong " + e);
        }
    }

    public ObservableList<TransactionHistoryController.HistoryTicket> getTicketsHistory(Connection connection, int clientID) {
        ObservableList<TransactionHistoryController.HistoryTicket> tickets = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT " +
                    "t.id AS id_of_ticket, " +
                    "CASE\n" +
                    "        WHEN tr.type = '1st Class' THEN pi.first_class\n" +
                    "        WHEN tr.type = '2nd Class' THEN pi.second_class\n" +
                    "        END AS price,\n" +
                    "    tr.type AS ticket_type," +
                    "di.leave_date," +
                    "di.arrival_date," +
                    "ci_leave.leave_city AS leave_city," +
                    "ci_arrival.arrival_city AS arrival_city," +
                    "hi_leave.leave_hour AS leave_time," +
                    "hi_arrival.arrival_hour AS arrival_time " +
                    "FROM " +
                    "transactions tr " +
                    "JOIN tickets t ON tr.id_of_ticket = t.id " +
                    "JOIN price_info pi ON t.first_class_id = pi.id OR t.second_class_id = pi.id " +
                    "JOIN date_info di ON t.leave_date_id = di.id " +
                    "JOIN city_info ci_leave ON t.leave_city_id = ci_leave.id " +
                    "JOIN city_info ci_arrival ON t.arrival_city_id = ci_arrival.id " +
                    "JOIN hour_info hi_leave ON t.leave_hour_id = hi_leave.id " +
                    "JOIN hour_info hi_arrival ON t.arrival_hour_id = hi_arrival.id " +
                    "WHERE " +
                    "tr.client_id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, clientID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TransactionHistoryController.HistoryTicket ticket = new TransactionHistoryController.HistoryTicket(
                        resultSet.getInt("price"),
                        resultSet.getInt("id_of_ticket"),
                        resultSet.getString("leave_time"),
                        resultSet.getString("arrival_time"),
                        resultSet.getString("leave_city"),
                        resultSet.getString("arrival_city"),
                        resultSet.getString("leave_date"),
                        resultSet.getString("arrival_date"),
                        resultSet.getString("ticket_type")
                );
                tickets.add(ticket);
            }
        } catch (Exception e) {
            System.out.println("Error at getTicketsHistory " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error at closing result set or statement: " + e);
            }
        }
        return tickets;
    }

    public ObservableList<TransactionHistoryController.ReviewThing> getReview(Connection connection, int clientID) {
        ObservableList<TransactionHistoryController.ReviewThing> tickets = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT " +
                    "t.id AS id_of_ticket," +
                    "ri.rating," +
                    "ri.review " +
                    "FROM " +
                    "transactions tr " +
                    "JOIN tickets t ON tr.id_of_ticket = t.id " +
                    "LEFT JOIN review_info ri ON t.id = ri.ticket_id AND tr.client_id = ri.client_id " +
                    "WHERE " +
                    "tr.client_id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, clientID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TransactionHistoryController.ReviewThing reviewThing = new TransactionHistoryController.ReviewThing(
                        resultSet.getInt("id_of_ticket"),
                        resultSet.getFloat("rating"),
                        resultSet.getString("review")
                );
                tickets.add(reviewThing);
            }
        } catch (Exception e) {
            System.out.println("Error at getReview " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error at closing result set or statement: " + e);
            }
        }
        return tickets;
    }

    public int clearTransactionHistory(Connection connection, int clientID) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "DELETE FROM transactions WHERE transactions.client_id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, clientID);
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error at clearTransactionHistory " + e);
        }
        return -1;
    }

    public int clearReviewHistory(Connection connection, int clientID) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "DELETE FROM review_info WHERE review_info.client_id =?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, clientID);
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("clearReviewHistory " + e);
        }
        return -1;
    }

    public ObservableList<TicketsController.PlaneThing> getPlaneInfo(Connection connection) {
        ObservableList<TicketsController.PlaneThing> planes = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT t.id AS ticket_id,pi.plane_name,pi.total_seats,pi.available_seats,pi.first_class_seats,pi.second_class_seats FROM plane_info pi JOIN tickets t ON pi.id = t.plane_id;";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TicketsController.PlaneThing plane = new TicketsController.PlaneThing(
                        resultSet.getInt("ticket_id"),
                        resultSet.getString("plane_name"),
                        resultSet.getInt("total_seats"),
                        resultSet.getInt("available_seats"),
                        resultSet.getInt("first_class_seats"),
                        resultSet.getInt("second_class_seats")
                );
                planes.add(plane);
            }
        } catch (Exception e) {
            System.out.println("Error at getPlaneInfo " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error at closing result set or statement: " + e);
            }
        }
        return planes;
    }

    public int retrieveTotalNumberOfSeats(Connection connection, int ticketId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT pi.total_seats FROM plane_info pi JOIN tickets t ON pi.id = t.plane_id WHERE t.id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ticketId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("total_seats");
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println("retrieveTotalNumberOfSeats " + e);
        }
        return -1;
    }

    public boolean isReservedSeat(Connection connection, int ticketId, int seatNumber) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT si.is_seat_reserved FROM seat_info si WHERE si.seat_number = ? AND si.ticket_id=?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, seatNumber);
            preparedStatement.setInt(2, ticketId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean("is_seat_reserved");
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error at isReservedSeat " + e);
        }
        return false;
    }

    public void insertSeatInfo(Connection connection, int ticketId, int seatNumber) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT into seat_info (seat_number,is_seat_reserved,ticket_id) VALUES (?,true,?);";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, seatNumber);
            preparedStatement.setInt(2, ticketId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("insertSeatInfo succesful");
            } else {
                System.out.println("Nothing happened");
            }
        } catch (Exception e) {
            System.out.println("Error at insertSeatInfo " + e);
        }
    }

    public void decreaseAvailableSeatsNumber(Connection connection, int ticketID, Controller2.SeatClass seatClass) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "UPDATE plane_info\n" +
                    "SET\n" +
                    "  first_class_seats = CASE WHEN ? = 'FIRST_CLASS' THEN first_class_seats - 1 ELSE first_class_seats END,\n" +
                    "  second_class_seats = CASE WHEN ? = 'SECOND_CLASS' THEN second_class_seats - 1 ELSE second_class_seats END,\n" +
                    "  available_seats = available_seats - 1\n" +
                    "FROM tickets t\n" +
                    "WHERE\n" +
                    "  plane_info.id = t.plane_id AND\n" +
                    "  t.id = ? AND\n" +
                    "  (first_class_seats IS NOT NULL OR second_class_seats IS NOT NULL);";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, seatClass.name());
            preparedStatement.setString(2, seatClass.name());
            preparedStatement.setInt(3, ticketID);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("decreaseAvailableSeatsNumber succesful");
            } else {
                System.out.println("nothing happened");
            }
        } catch (Exception e) {
            System.out.println("error at decreaseAvailableSeatsNumber " + e);
        }
    }

    public int getRemainingSeatsNumber(Connection connection, int ticketID) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT pi.available_seats FROM plane_info pi JOIN tickets t ON pi.id = t.plane_id WHERE t.id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ticketID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("available_seats");
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println("error at getRemainingSeatsNumber " + e);
        }
        return -1;
    }

    public ObservableList<AllClientsPageController.Client> getClientsDetails(Connection connection) {
        ObservableList<AllClientsPageController.Client> clients = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT c.id,c.email,c.password,c.name from clients c;";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AllClientsPageController.Client client = new AllClientsPageController.Client(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("name")
                );
                clients.add(client);
            }
        } catch (Exception e) {
            System.out.println("Error at getClientsDetails " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error at closing result set or statement: " + e);
            }
        }
        return clients;
    }

    public int getIDCityOfLeavingFromTicket(Connection connection, String leavingCity) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT ci.id FROM city_info ci WHERE ci.leave_city= ?; ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, leavingCity);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("getIDCityOfLeavingFromTicket succesful " + String.valueOf(resultSet.getInt("id")));
                return resultSet.getInt("id");
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println("error at getCityOfLeavingFromTicket " + e);
        }
        return -1;
    }

    public int getIDCityOfArrivalFromTicket(Connection connection, String arrivalCity) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT ci.id FROM city_info ci WHERE ci.arrival_city= ?; ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, arrivalCity);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("getIDCityOfArrivalFromTicket succesful " + String.valueOf(resultSet.getInt("id")));
                return resultSet.getInt("id");
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println("error at getCityOfLeavingFromTicket " + e);
        }
        return -1;
    }

    public int getIDofAirlineCodeFromTicket(Connection connection, String airlineCode) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT a.id FROM airlines a WHERE a.code_of_name = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, airlineCode);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("getIDofAirlineCode succesful " + String.valueOf(resultSet.getInt("id")));
                return resultSet.getInt("id");
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println("getIDofAirlineCode succesful " + e);
        }
        return -1;
    }

    public int getIdLeavingAirportFromTicket(Connection connection, String leavingAirport) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT ai.id FROM airport_info ai WHERE TRIM(ai.leave_airport) =?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, leavingAirport);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println("error at getIdLeavingAirportFromTicket " + e);
        }
        return -1;
    }

    public int getIdArrivalAirportFromTicket(Connection connection, String arrivalAirport) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT ai.id FROM airport_info ai WHERE TRIM(ai.arrival_airport) =?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, arrivalAirport);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println("error at getIdArrivalAirportFromTicket " + e);
        }
        return -1;
    }

    public int getIdLeavingTimeFromTicket(Connection connection, String leavingTime) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT hi.id FROM hour_info hi WHERE TRIM(hi.leave_hour) = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, leavingTime);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println("error at getIdLeavingTimeFromTicket " + e);
        }
        return -1;
    }

    public int getIdArrivalTimeFromTicket(Connection connection, String arrivalTime) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT hi.id FROM hour_info hi WHERE TRIM(hi.arrival_hour) = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, arrivalTime);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println("error at getIdArrivalTimeFromTicket " + e);
        }
        return -1;
    }

    public int getIdLeavingDateFromTicket(Connection connection, String leavingDate) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT di.id FROM date_info di WHERE TRIM(di.leave_date) = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, leavingDate);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                return -1;

            }
        } catch (Exception e) {
            System.out.println("error at getIdLeavingDateFromTicket " + e);
        }
        return -1;
    }

    public int getIdArrivalDateFromTicket(Connection connection, String arrivalDate) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT di.id FROM date_info di WHERE TRIM(di.arrival_date) = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, arrivalDate);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println("getIdArrivalDateFromTicket error " + e);
        }
        return -1;
    }

    public int getIdPrice1stClassFromTicket(Connection connection, int price1stClass) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT pi.id FROM price_info pi WHERE pi.first_class = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, price1stClass);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println("error getIdPrice1stClassFromTicket " + e);
        }
        return -1;
    }

    public int getIdPrice2ndClassFromTicket(Connection connection, int price2ndClass) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT pi.id FROM price_info pi WHERE pi.second_class = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, price2ndClass);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println("error getIdPrice2ndClassFromTicket " + e);
        }
        return -1;
    }

    public void updateTicketInformations(Connection connection, int leavingCityId, int arrivalCityId, int companyId, int leavingAirportId,
                                         int arrivalAirportId, int leavingTimeId, int arrivalTimeId, int leavingDateId, int arrivalDateId,
                                         int price1stClassId, int price2ndClassId, int ticketId) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "UPDATE tickets " +
                    "SET leave_city_id = ?, arrival_city_id = ?, company_id = ?, " +
                    "leave_airport_id = ?, arrival_airport_id = ?, leave_hour_id = ?, " +
                    "arrival_hour_id = ?, leave_date_id = ?, arrival_date_id = ?, " +
                    "first_class_id = ?, second_class_id = ? " +
                    "WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, leavingCityId);
            preparedStatement.setInt(2, arrivalCityId);
            preparedStatement.setInt(3, companyId);
            preparedStatement.setInt(4, leavingAirportId);
            preparedStatement.setInt(5, arrivalAirportId);
            preparedStatement.setInt(6, leavingTimeId);
            preparedStatement.setInt(7, arrivalTimeId);
            preparedStatement.setInt(8, leavingDateId);
            preparedStatement.setInt(9, arrivalDateId);
            preparedStatement.setInt(10, price1stClassId);
            preparedStatement.setInt(11, price2ndClassId);
            preparedStatement.setInt(12, ticketId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("updateTicketInformations succesful");
            } else {
                System.out.println("nothing happened");
            }
        } catch (Exception e) {
            System.out.println("error at updateTicketInformations " + e);
        }
    }

    public boolean isLeavingCityInDB(Connection connection, String leavingCity) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT ci.leave_city FROM city_info ci WHERE leave_city=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, leavingCity);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            System.out.println("error at isLeavingCityInDB " + e);
        }
        return false;
    }

    public boolean isArrivalCityInDB(Connection connection, String arrivalCity) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT ci.arrival_city FROM city_info ci WHERE arrival_city=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, arrivalCity);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            System.out.println("error at isArrivalCityInDB " + e);
        }
        return false;
    }

    public boolean isCompanyInDB(Connection connection, String companyCodeOfName) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT a.code_of_name FROM airlines a WHERE code_of_name = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, companyCodeOfName);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            System.out.println("Error at isCompanyInDB " + e);
        }
        return false;
    }

    public boolean isArrivalAirportInDb(Connection connection, String arrivalAirport) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT  ai.arrival_airport FROM airport_info ai WHERE TRIM(arrival_airport) = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, arrivalAirport);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (Exception e) {
            System.out.println("Error at isArrivalAirportInDb " + e);
        }
        return false;
    }

    public boolean isLeavingAirportInDb(Connection connection, String leavingAirport) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT  ai.leave_airport FROM airport_info ai WHERE TRIM(leave_airport) = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, leavingAirport);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (Exception e) {
            System.out.println("Error at isLeavingAirportInDb " + e);
        }
        return false;
    }

    public boolean isLeaveDateInDb(Connection connection, String leaveDate) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT di.leave_date FROM date_info di WHERE leave_date = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, leaveDate);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            System.out.println("Error at isLeaveDateInDb " + e);
        }
        return false;
    }

    public boolean isArrivalDateInDb(Connection connection, String arrivalDate) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT di.arrival_date FROM date_info di WHERE arrival_date = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, arrivalDate);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            System.out.println("Error at isArrivalDateInDb " + e);
        }
        return false;
    }

    public boolean isLeavingTimeInDb(Connection connection, String leavingTime) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT hi.leave_hour FROM hour_info hi WHERE leave_hour =?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, leavingTime);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            System.out.println("Error at isLeavingTimeInDb " + e);
        }
        return false;
    }

    public boolean isArrivalTimeInDb(Connection connection, String arrivalTime) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT hi.arrival_hour FROM hour_info hi WHERE arrival_hour =?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, arrivalTime);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            System.out.println("Error at isArrivalTimeInDb " + e);
        }
        return false;
    }

    public boolean isFirstPriceClassInDb(Connection connection, int firstPrice) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "Select pi.first_class FROM price_info pi WHERE first_class = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, firstPrice);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            System.out.println("Error at isFirstPriceClassInDb " + e);
        }
        return false;
    }

    public boolean isSecondPriceClassInDb(Connection connection, int secondPrice) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "Select pi.second_class FROM price_info pi WHERE second_class = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, secondPrice);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            System.out.println("Error at isSecondPriceClassInDb " + e);
        }
        return false;
    }

    public void introduceCitiesInDb(Connection connection, String leavingCity, String arrivalCity) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO city_info (leave_city,arrival_city) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, leavingCity);
            preparedStatement.setString(2, arrivalCity);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("introduceCitiesInDb succesful");
            } else {
                System.out.println("Nothing happened");
            }
        } catch (Exception e) {
            System.out.println("Error at introduceCitiesInDb " + e);
        }
    }

    public void introduceAirlineInDb(Connection connection, String airlineName, String airlineCode) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO airlines (code_of_name, name) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, airlineCode);
            preparedStatement.setString(2, airlineName);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("introduceAirlineInDb succesful");
            } else {
                System.out.println("nothing happened");
            }
        } catch (Exception e) {
            System.out.println("error at introduceAirlineInDb " + e);
        }
    }

    public void introducePricesInDb(Connection connection, int firstClass, int secondClass) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO price_info(first_class, second_class) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, firstClass);
            preparedStatement.setInt(2, secondClass);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("introducePricesInDb succesful");
            } else {
                System.out.println("nothing happened");
            }
        } catch (Exception e) {
            System.out.println("error at introducePricesInDb " + e);
        }
    }

    public void introduceAirportsInDb(Connection connection, String leavingAirport, String arrivalAirport) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO airport_info(leave_airport, arrival_airport) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, leavingAirport);
            preparedStatement.setString(2, arrivalAirport);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("introduceAirportsInDb succesful");
            } else {
                System.out.println("nothing happened");
            }
        } catch (Exception e) {
            System.out.println("error at introduceAirportsInDb " + e);
        }

    }

    public void introduceDatesInDb(Connection connection, String arrivalDate, String leavingDate) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO date_info(leave_date, arrival_date) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, leavingDate);
            preparedStatement.setString(2, arrivalDate);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("introduceDatesInDb succesful");
            } else {
                System.out.println("nothing happened");
            }
        } catch (Exception e) {
            System.out.println("error at introduceDatesInDb " + e);
        }
    }

    public void introduceTimeInDb(Connection connection, String leavingTime, String arrivalTime) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO hour_info(leave_hour, arrival_hour) VALUES (?,?);";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, leavingTime);
            preparedStatement.setString(2, arrivalTime);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("introduceTimeInDb succesful");
            }
        } catch (Exception e) {
            System.out.println("Error at introduceTimeInDb " + e);
        }
    }

    public boolean isPlaneInDb(Connection connection, String planeName) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT pi.plane_name FROM plane_info pi WHERE TRIM(pi.plane_name) = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, planeName);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            System.out.println("error at isPlaneInDb " + e);
        }
        return false;
    }

    public void introducePlaneInDb(Connection connection, String planeName, int totalSeats, int firstClass, int secondClass) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO plane_info(plane_name, total_seats, available_seats,second_class_seats,first_class_seats) VALUES(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, planeName);
            preparedStatement.setInt(2, totalSeats);
            preparedStatement.setInt(3, totalSeats);
            preparedStatement.setInt(4, secondClass);
            preparedStatement.setInt(5, firstClass);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("introducePlaneInDb succesful");
            } else {
                System.out.println("nothing happened");
            }
        } catch (Exception e) {
            System.out.println("Error at introducePlaneInDb " + e);
        }
    }

    public ObservableList<CreateNewTicketController.Airline> getAirlineDetails(Connection connection) {
        ObservableList<CreateNewTicketController.Airline> airlines = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT a.id,a.name,a.code_of_name FROM airlines a;";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CreateNewTicketController.Airline airline = new CreateNewTicketController.Airline(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code_of_name")
                );
                airlines.add(airline);
            }
        } catch (Exception e) {
            System.out.println("Error at getAirlineDetails " + e);
        }

        return airlines;
    }

    public ObservableList<CreateNewTicketController.Airport> getAirportDetails(Connection connection) {
        ObservableList<CreateNewTicketController.Airport> airports = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT ai.id,ai.leave_airport,ai.arrival_airport FROM airport_info ai;";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CreateNewTicketController.Airport airport = new CreateNewTicketController.Airport(
                        resultSet.getInt("id"),
                        resultSet.getString("leave_airport"),
                        resultSet.getString("arrival_airport")
                );
                airports.add(airport);
            }
        } catch (Exception e) {
            System.out.println("Error at getAirportDetails " + e);
        }
        return airports;
    }

    public ObservableList<CreateNewTicketController.Plane> getPlanesDetails(Connection connection) {
        ObservableList<CreateNewTicketController.Plane> planes = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT pi.id,pi.plane_name,pi.total_seats,pi.available_seats,pi.first_class_seats,pi.second_class_seats  FROM plane_info pi;";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CreateNewTicketController.Plane plane = new CreateNewTicketController.Plane(
                        resultSet.getInt("id"),
                        resultSet.getString("plane_name"),
                        resultSet.getInt("total_seats"),
                        resultSet.getInt("available_seats"),
                        resultSet.getInt("second_class_seats"),
                        resultSet.getInt("first_class_seats")
                );
                planes.add(plane);
            }
        } catch (Exception e) {
            System.out.println("Error at getPlanesDetails " + e);
        }
        return planes;
    }

    public ObservableList<CreateNewTicketController.Date> getDatesDetails(Connection connection) {
        ObservableList<CreateNewTicketController.Date> dates = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT di.id,di.leave_date,di.arrival_date FROM date_info di";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CreateNewTicketController.Date date = new CreateNewTicketController.Date(
                        resultSet.getInt("id"),
                        resultSet.getString("leave_date"),
                        resultSet.getString("arrival_date")
                );
                dates.add(date);
            }
        } catch (Exception e) {
            System.out.println("Error at getDatesDetails " + e);
        }
        return dates;
    }

    public ObservableList<CreateNewTicketController.City> getCityDetails(Connection connection) {
        ObservableList<CreateNewTicketController.City> cities = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT ci.id,ci.leave_city,ci.arrival_city FROM city_info ci";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CreateNewTicketController.City city = new CreateNewTicketController.City(
                        resultSet.getInt("id"),
                        resultSet.getString("leave_city"),
                        resultSet.getString("arrival_city")
                );
                cities.add(city);
            }

        } catch (Exception e) {
            System.out.println("Error at getCityDetails " + e);
        }

        return cities;
    }

    public ObservableList<CreateNewTicketController.Price> getPriceDetails(Connection connection){
        ObservableList<CreateNewTicketController.Price> prices = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query="SELECT pi.id,pi.first_class,pi.second_class FROM price_info pi;";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                CreateNewTicketController.Price price = new CreateNewTicketController.Price(
                        resultSet.getInt("id"),
                        resultSet.getInt("first_class"),
                        resultSet.getInt("second_class")
                );
                prices.add(price);
            }
        }catch (Exception e){
            System.out.println("Error at getPriceDetails "+e);
        }
        return prices;
    }

    public ObservableList<CreateNewTicketController.Hour> getHoursDetails(Connection connection){
        ObservableList<CreateNewTicketController.Hour> hours =FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
          String query= "SELECT hi.id,hi.leave_hour,hi.arrival_hour FROM hour_info hi";
          preparedStatement = connection.prepareStatement(query);
          resultSet = preparedStatement.executeQuery();
          while (resultSet.next()){
              CreateNewTicketController.Hour hour = new CreateNewTicketController.Hour(
                      resultSet.getInt("id"),
                      resultSet.getString("leave_hour"),
                      resultSet.getString("arrival_hour")
              );
              hours.add(hour);
          }
        }catch (Exception e){
            System.out.println("error at getHoursDetails "+e);
        }
        return hours;
    }

    public void createNewTicket(Connection connection,String companyName,String leaveCity,String arrivalCity,String leaveDate,String arrivalDate,int firstClass,int secondClass,
                                String leaveHour,String arrivalHour,String leaveAirport,String arrivalAirport,String planeName){
        PreparedStatement preparedStatement = null;
        try {
            String query="INSERT INTO tickets (\n" +
                    "    company_id,\n" +
                    "    leave_city_id,\n" +
                    "    arrival_city_id,\n" +
                    "    leave_date_id,\n" +
                    "    arrival_date_id,\n" +
                    "    first_class_id,\n" +
                    "    second_class_id,\n" +
                    "    leave_hour_id,\n" +
                    "    arrival_hour_id,\n" +
                    "    leave_airport_id,\n" +
                    "    arrival_airport_id,\n" +
                    "    plane_id\n" +
                    ") VALUES (\n" +
                    "    (SELECT id FROM airlines WHERE code_of_name = ?),\n" +
                    "    (SELECT id FROM city_info WHERE leave_city = ?),\n" +
                    "    (SELECT id FROM city_info WHERE arrival_city = ?),\n" +
                    "    (SELECT id FROM date_info WHERE leave_date = ?),\n" +
                    "    (SELECT id FROM date_info WHERE arrival_date = ?),\n" +
                    "    (SELECT id FROM price_info WHERE first_class = ?),\n" +
                    "    (SELECT id FROM price_info WHERE second_class = ?),\n" +
                    "    (SELECT id FROM hour_info WHERE leave_hour = ?),\n" +
                    "    (SELECT id FROM hour_info WHERE arrival_hour = ?),\n" +
                    "    (SELECT id FROM airport_info WHERE leave_airport = ?),\n" +
                    "    (SELECT id FROM airport_info WHERE arrival_airport = ?),\n" +
                    "    (SELECT id FROM plane_info WHERE plane_name=?)\n" +
                    ");";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,companyName);
            preparedStatement.setString(2,leaveCity);
            preparedStatement.setString(3,arrivalCity);
            preparedStatement.setString(4,leaveDate);
            preparedStatement.setString(5,arrivalDate);
            preparedStatement.setInt(6,firstClass);
            preparedStatement.setInt(7,secondClass);
            preparedStatement.setString(8,leaveHour);
            preparedStatement.setString(9,arrivalHour);
            preparedStatement.setString(10,leaveAirport);
            preparedStatement.setString(11,arrivalAirport);
            preparedStatement.setString(12,planeName);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected>0){
                System.out.println("createNewTicket succesful");
            }else{
                System.out.println("nothing happened");
            }
        }catch (Exception e){
            System.out.println("Error at createNewTicket "+e);
        }
    }

}

