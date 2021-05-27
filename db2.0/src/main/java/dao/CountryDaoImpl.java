package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.dao.IModelDao;
import model.Country;

public class CountryDaoImpl extends GenericDaoImpl<Country> implements IModelDao<Country> {

    private final static String CREATE = "INSERT INTO dbo.Countries (country) VALUES ((?))";
    private final static String GET_BY_ID = "SELECT * FROM dbo.Countries WHERE dbo.Countries.primary_key = (?)";
    private final static String GET_ALL = "SELECT * FROM dbo.Countries";
    private final static String DELETE = "DELETE FROM dbo.Countries WHERE dbo.Countries.primary_key = (?)";

    public CountryDaoImpl() {
        super(CREATE, GET_BY_ID, GET_ALL, DELETE);
    }

    @Override
    Country toModel(ResultSet rs) throws SQLException {

        Country country = new Country();

        country.setId(rs.getLong("primary_key"));
        country.setCountry(rs.getString("country"));

        return country;
    }

    @Override
    PreparedStatement fillPStatement(PreparedStatement ps, Country model, boolean isUpdate) throws SQLException {

        ps.setString(1, model.getCountry());

        if (isUpdate) {
            ps.setLong(7, model.getId());
        }

        return ps;
    }

    @Override
    List<Country> toList(ResultSet rs) throws SQLException {

        List<Country> countriesList = new ArrayList<Country>();
        while (rs.next()) {
            Country country = toModel(rs);
            countriesList.add(country);
        }

        return countriesList;
    }
}
