package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.model.City;

import java.sql.SQLException;
import java.util.List;

public interface ICityDAO {
    List<City> getAll() throws SQLException;
}
