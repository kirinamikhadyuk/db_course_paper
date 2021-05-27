package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.dao.IPersonDao;
import model.Country;
import model.Person;

public class PersonDaoImpl extends GenericDaoImpl<Person> implements IPersonDao {

    private final static String CREATE = "INSERT INTO dbo.Person (primary_key, last_name, first_name, patronymic, date_of_birth, date_of_death, key_country_birth) VALUES ((?), (?), (?), (?), (?), (?), (?))";
    private final static String GET_BY_ID = "SELECT * FROM dbo.Person WHERE primary_key = (?)";
    private final static String GET_ALL = "SELECT * FROM dbo.Person";
    private final static String DELETE = "DELETE FROM dbo.Person WHERE primary_key = (?)";

    private final String PERSON_GET_BY_ID_WITH_COUNTRY = "SELECT * FROM dbo.Person INNER JOIN dbo.Countries ON dbo.Person.key_country_birth = dbo.Countries.primary_key WHERE dbo.Person.primary_key = (?)";

    public PersonDaoImpl() {
        super(CREATE, GET_BY_ID, GET_ALL, DELETE);
    }

    @Override
    public Person getByIdWithCountry(long id, Connection connection) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(PERSON_GET_BY_ID_WITH_COUNTRY)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            Person person = toPersonWithCountry(rs);

            return person;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    private Person toPersonWithCountry(ResultSet rs) throws SQLException {

        rs.next();
        Person person = toModel(rs);
        Country country = new CountryDaoImpl().toModel(rs);
        person.setCountryBirth(country);

        return person;

    }

    @Override
    Person toModel(ResultSet rs) throws SQLException {

        Person person = new Person();

        person.setId(rs.getLong("primary_key"));
        person.setLastName(rs.getString("last_name"));
        person.setFirstName(rs.getString("first_name"));
        person.setPatronymic(rs.getString("patronymic"));
        person.setDateOfBirth(rs.getDate("date_of_birth"));
        person.setDateOfDeath(rs.getDate("date_of_death"));

        return person;
    }

    @Override
    PreparedStatement fillPStatement(PreparedStatement ps, Person model, boolean isUpdate) throws SQLException {

        ps.setLong(1, model.getId());
        ps.setString(2, model.getLastName());
        ps.setString(3, model.getFirstName());
        ps.setString(4, model.getPatronymic());
        ps.setDate(5, model.getDateOfBirth());
        ps.setDate(6, model.getDateOfDeath());
        ps.setLong(7, model.getCountryBirth().getId());

        if (isUpdate) {
            ps.setLong(7, model.getId());
        }

        return ps;
    }

    @Override
    List<Person> toList(ResultSet rs) throws SQLException {

        List<Person> personList = new ArrayList<Person>();
        while (rs.next()) {
            Person person = toModel(rs);
            personList.add(person);
        }

        return personList;
    }
}
