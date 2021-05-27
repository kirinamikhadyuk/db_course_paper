package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.dao.IBiographyDao;
import model.*;

public class BiographyDaoImpl extends GenericDaoImpl<Biography> implements IBiographyDao {

    private final static String CREATE = "INSERT INTO dbo.Biography (person_key, biography) VALUES ((?), (?))";
    private final static String GET_BY_ID = "SELECT * FROM dbo.Biography WHERE dbo.Biography.person_key = (?)";
    private final static String GET_ALL = "SELECT * FROM dbo.Biography";
    private final static String DELETE = "DELETE FROM dbo.Biography WHERE dbo.Biography.person_key = (?)";

    private final String BIOGRAPHY_GET_BY_ID_WITH_PERSON = "SELECT * FROM dbo.Biography LEFT OUTER JOIN dbo.Person on dbo.Biography.person_key =  dbo.Person.primary_key WHERE dbo.Biography.person_key = (?)";


    public BiographyDaoImpl() {
        super(CREATE, GET_BY_ID, GET_ALL, DELETE);
    }

    @Override
    public Biography getByIdWithPerson(long id, Connection connection) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(BIOGRAPHY_GET_BY_ID_WITH_PERSON)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            Biography biography = toBiographyWithPerson(rs);

            return biography;
        } catch (SQLException e) {
            System.out.println("ERR " + e.getMessage());
            return null;
        }
    }

    private Biography toBiographyWithPerson(ResultSet rs) throws SQLException {

        rs.next();
        Biography biography = toModel(rs);
        Person person = new PersonDaoImpl().toModel(rs);
        biography.setPerson(person);
        System.out.println("PERSON" + person);

        return biography;

    }

    @Override
    Biography toModel(ResultSet rs) throws SQLException {

        Biography biography = new Biography();

        biography.setBiography(rs.getString("biography"));

        return biography;
    }

    @Override
    PreparedStatement fillPStatement(PreparedStatement ps, Biography model, boolean isUpdate) throws SQLException {

        ps.setLong(1, model.getId());
        ps.setString(2, model.getBiography());

        if (isUpdate) {
            ps.setLong(7, model.getId());
        }

        return ps;
    }

    @Override
    List<Biography> toList(ResultSet rs) throws SQLException {

        List<Biography> biographiesList = new ArrayList<Biography>();
        while (rs.next()) {
            Biography biography = toModel(rs);
            biographiesList.add(biography);
        }

        return biographiesList;
    }
}
