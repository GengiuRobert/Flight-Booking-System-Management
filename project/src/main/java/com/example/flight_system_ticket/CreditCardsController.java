package com.example.flight_system_ticket;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CreditCardsController {
    public Stage primary_stage;
    public String email;
    @FXML
    Label cardNumberLabel1, cvvNumberLabel1, expirationDateLabel1, cardTypeLabel1;
    @FXML
    Label cardNumberLabel2, cvvNumberLabel2, expirationDateLabel2, cardTypeLabel2;
    @FXML
    Label cardNumberLabel3, cvvNumberLabel3, expirationDateLabel3, cardTypeLabel3;
    @FXML
    Label cardIDLabel1,cardIDLabel2,cardIDLabel3;

    public void set_email_of_client(String email)
    {
        this.email = email;
    }
    public void set_primary_stage(javafx.stage.Stage stage) {
        this.primary_stage = stage;
    }

    public void go_to_credit_cards_view() throws SQLException {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("credit_cards_page.fxml"));
                fxmlLoader.setController(this);
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Credit Cards Page");
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                System.out.println("Error at showing credit cards  view " + e);
            }
        initialize_card_data();
    }

    public void initialize_card_data() {
        Connection connection = null;
        try {
            DataBaseOpp dataBaseOpp = new DataBaseOpp();
            connection = dataBaseOpp.connect_to_db("tickets_system", "postgres", "12345678");
            int client_id = dataBaseOpp.retrieve_client_id(connection,"clients",email);
            List<String> card_numbers = dataBaseOpp.retrieve_card_numbers_for_given_client(connection, client_id);
            List<String> cvv_numbers = dataBaseOpp.retrieve_cvv_numbers_for_given_client(connection, client_id);
            List<String> expiration_dates = dataBaseOpp.retrieve_expiration_dates_for_given_client(connection, client_id);
            List<String> card_types = dataBaseOpp.retrieve_card_types_for_given_client(connection, client_id);
            List<String> cardIDs = dataBaseOpp.retrieve_card_IDs_for_given_client(connection,client_id);

            if (!card_numbers.isEmpty()) {
                cardNumberLabel1.setText(card_numbers.get(0));
                cvvNumberLabel1.setText(cvv_numbers.get(0));
                expirationDateLabel1.setText(expiration_dates.get(0));
                cardTypeLabel1.setText(card_types.get(0));
                cardIDLabel1.setText(cardIDs.get(0));
            }
            if (1 < card_numbers.size()) {
                cardNumberLabel2.setText(card_numbers.get(1));
                cvvNumberLabel2.setText(cvv_numbers.get(1));
                expirationDateLabel2.setText(expiration_dates.get(1));
                cardTypeLabel2.setText(card_types.get(1));
                cardIDLabel2.setText(cardIDs.get(1));
            }
            if (2 < card_numbers.size()) {
                cardNumberLabel3.setText(card_numbers.get(2));
                cvvNumberLabel3.setText(cvv_numbers.get(2));
                expirationDateLabel3.setText(expiration_dates.get(2));
                cardTypeLabel3.setText(card_types.get(2));
                cardIDLabel3.setText(cardIDs.get(2));
            }
        } catch (Exception e) {
            System.out.println("Error at connecting to the database " + e);
        }
    }

}
