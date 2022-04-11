package invoiceProject.controllers;

import dataFromJSON.utils.DatabaseUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoadCustomersController {

    private static final String INSERT_CUSTOMERS = "INSERT INTO customer (address, country, email, fullName, phoneNumber) VALUES (?,?,?,?,?);";

    public static void loadCustomersFromJSON() throws SQLException, IOException {
        PreparedStatement preparedStatement = DatabaseUtils.databaseConnection.prepareStatement(INSERT_CUSTOMERS);

        String data = new String(Files.readAllBytes(Paths.get("src/main/resources/customers.json")));
        JSONArray jsonArray = new JSONArray(data);

        for (int i = 0; i < jsonArray.length(); i++) {
            String row = jsonArray.get(i).toString();
            JSONObject object = new JSONObject(row);

            preparedStatement.setString(1, object.getString("address"));
            preparedStatement.setString(2, object.getString("country"));
            preparedStatement.setString(3, object.getString("email"));
            preparedStatement.setString(4, object.getString("fullName"));
            preparedStatement.setString(5, object.getString("phoneNumber"));

            preparedStatement.executeUpdate();
        }
    }

}