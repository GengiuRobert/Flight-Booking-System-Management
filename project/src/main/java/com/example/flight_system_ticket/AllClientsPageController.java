package com.example.flight_system_ticket;

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

public class AllClientsPageController {

    @FXML
    TableView<Client> clientsTableView;
    @FXML
    TableColumn<Client,Integer> clientIDColumn;
    @FXML
    TableColumn<Client,String> nameColumn,emailColumn,passwordColumn;

    public Stage allClientsStage = new Stage();

    public ObservableList<Client> clientsList = FXCollections.observableArrayList();

    public void show() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("showAllClientsPage.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            allClientsStage.setTitle("All Clients Page");
            allClientsStage.setScene(scene);
            allClientsStage.show();
            allClientsStage.setResizable(false);
            initializeClients();
    }

    public void initializeClients() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            clientsList = dataBaseOpp.getClientsDetails(connection);
            clientsTableView.setItems(clientsList);
            clientIDColumn.setCellValueFactory(new PropertyValueFactory<>("clientID"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        }catch (Exception e)
        {
            System.out.println("error at connecting to db "+e);
        }
    }

    public static class Client{
        int clientID;
        String name;
        String password;
        String email;

        public int getClientID() {
            return clientID;
        }

        public void setClientID(int clientID) {
            this.clientID = clientID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Client(int clientID,String name,String password,String email){
            this.clientID =clientID;
            this.name = name;
            this.password = password;
            this.email = email;
        }
    }

}

