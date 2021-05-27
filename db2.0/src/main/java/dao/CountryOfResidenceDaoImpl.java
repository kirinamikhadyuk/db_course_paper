package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.dao.ICountryOfResidenceDao;
import model.*;

public class CountryOfResidenceDaoImpl extends GenericDaoImpl<CountryOfResidence> implements ICountryOfResidenceDao {

    private final static String CREATE = "INSERT INTO dbo.[Countries of residence] ( person_key, date_from, date_to, country_key ) VALUES ((?), (?), (?))";
    private final static String GET_BY_ID = "SELECT * FROM dbo.[Countries of residence] WHERE dbo.[Countries of residence].person_key = (?)";
    private final static String GET_ALL = "SELECT * FROM dbo.[Countries of residence] ";
    private final static String DELETE = "DELETE FROM dbo.[Countries of residence]  dbo.[Countries of residence].WHERE person_key = (?)";

    private final String PERSON_GET_BY_ID_WITH_COUNTRY = "SELECT * FROM dbo.[Countries of residence] INNER JOIN dbo.Countries ON dbo.[Countries of residence].country_key = dbo.Countries.primary_key INNER JOIN dbo.Person on dbo.[Countries of residence].person_key =  dbo.Person.primary_key WHERE dbo.[Countries of residence].person_key = (?)";

    public CountryOfResidenceDaoImpl() {
        super(CREATE, GET_BY_ID, GET_ALL, DELETE);
    }

    @Override
    public CountryOfResidence getByIdWithPersonAndCountry(long id, Connection connection) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(PERSON_GET_BY_ID_WITH_COUNTRY)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            CountryOfResidence country = toCountryOfResidenceWithPersonAndCountry(rs);

            return country;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    private CountryOfResidence toCountryOfResidenceWithPersonAndCountry(ResultSet rs) throws SQLException {

        rs.next();
        CountryOfResidence countryOfResidence = toModel(rs);
        Person person = new PersonDaoImpl().toModel(rs);
        Country country = new CountryDaoImpl().toModel(rs);
        countryOfResidence.setPerson(person);
        countryOfResidence.setCountry(country);

        return countryOfResidence;

    }

    @Override
    CountryOfResidence toModel(ResultSet rs) throws SQLException {

        CountryOfResidence country = new CountryOfResidence();

        country.setDateFrom(rs.getDate("data_from"));
        country.setDateTo(rs.getDate("data_to"));

        return country;
    }

    @Override
    PreparedStatement fillPStatement(PreparedStatement ps, CountryOfResidence model, boolean isUpdate) throws SQLException {

        ps.setDate(1, model.getDateFrom());
        ps.setDate(1, model.getDateTo());

        if (isUpdate) {
            ps.setLong(7, model.getId());
        }

        return ps;
    }

    @Override
    List<CountryOfResidence> toList(ResultSet rs) throws SQLException {

        List<CountryOfResidence> countriesList = new ArrayList<CountryOfResidence>();
        while (rs.next()) {
            CountryOfResidence country = toModel(rs);
            countriesList.add(country);
        }

        return countriesList;
    }
}
