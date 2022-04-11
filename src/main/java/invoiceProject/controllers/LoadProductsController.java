package invoiceProject.controllers;

import dataFromJSON.utils.DatabaseUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoadProductsController {

    private static final String INSERT_PRODUCTS = "INSERT INTO product (category, name, quantity, unitPrice, weight) VALUES (?,?,?,?,?);";

    public static void LoadProductsFromJson() throws SQLException, IOException {
        PreparedStatement preparedStatement = DatabaseUtils.databaseConnection.prepareStatement(INSERT_PRODUCTS);

        String data = new String(Files.readAllBytes(Paths.get("src/main/resources/products.json")));
        JSONArray jsonArray = new JSONArray(data);

        for (int i = 0; i < jsonArray.length(); i++) {
            String row = jsonArray.get(i).toString();
            JSONObject object = new JSONObject(row);

            preparedStatement.setString(1, object.getString("category"));
            preparedStatement.setString(2, object.getString("name"));
            preparedStatement.setInt(3, object.getInt("quantity"));
            preparedStatement.setDouble(4, object.getDouble("unitPrice"));
            preparedStatement.setDouble(5, object.getDouble("weight"));

            preparedStatement.executeUpdate();
        }
    }
}
