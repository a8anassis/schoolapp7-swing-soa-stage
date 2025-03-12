package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.model.City;
import gr.aueb.cf.schoolapp.util.DBUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAOImpl implements ICityDAO {
    @Override
    public List<City> getAll() throws SQLException {

        String sql = "SELECT * FROM cities order by name asc";
        List<City> cities = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id"); // Get the id column
                String name = rs.getString("name"); // Get the name column

                // Create a City object and add it to the list
                City city = new City(id, name);
                cities.add(city);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null,  "Select error", "Error", JOptionPane.ERROR_MESSAGE);
            throw e;
        }
        return cities;
    }
}
