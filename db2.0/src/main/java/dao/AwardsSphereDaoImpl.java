package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.dao.IAwardsSphereDao;
import model.*;

public class AwardsSphereDaoImpl extends GenericDaoImpl<AwardsSphere> implements IAwardsSphereDao {

    private final static String CREATE = "INSERT INTO dbo.[Awards sphere] (person_key, field_key) VALUES ((?), (?))";
    private final static String GET_BY_ID = "SELECT * FROM dbo.[Awards sphere] WHERE dbo.[Awards sphere].person_key = (?)";
    private final static String GET_ALL = "SELECT * FROM dbo.[Awards sphere]";
    private final static String DELETE = "DELETE FROM dbo.[Awards sphere] WHERE dbo.[Awards sphere].person_key = (?)";

    private final String AWARD_SPHERE_GET_BY_ID_WITH_TYPE = "SELECT * FROM dbo.[Awards sphere] INNER JOIN dbo.Sphere ON dbo.Sphere.primary_key = dbo.[Awards sphere].field_key INNER JOIN dbo.Person on dbo.[Awards sphere].person_key =  dbo.Person.primary_key WHERE dbo.[Awards sphere].person_key = (?)";


    public AwardsSphereDaoImpl() {
        super(CREATE, GET_BY_ID, GET_ALL, DELETE);
    }

    @Override
    public AwardsSphere getByIdWithPersonAndSphere(long id, Connection connection) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(AWARD_SPHERE_GET_BY_ID_WITH_TYPE)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            AwardsSphere sphere = toAwardsSphereWithPersonAndSphere(rs);

            return sphere;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    private AwardsSphere toAwardsSphereWithPersonAndSphere(ResultSet rs) throws SQLException {

        rs.next();
        AwardsSphere awardsSphere = toModel(rs);
        Sphere sphere = new SphereDaoImpl().toModel(rs);
        Person person = new PersonDaoImpl().toModel(rs);
        awardsSphere.setSphere(sphere);
        awardsSphere.setPerson(person);

        return awardsSphere;

    }

    @Override
    AwardsSphere toModel(ResultSet rs) throws SQLException {

        AwardsSphere awardsSphere = new AwardsSphere();

        return awardsSphere;
    }

    @Override
    PreparedStatement fillPStatement(PreparedStatement ps, AwardsSphere model, boolean isUpdate) throws SQLException {

        if (isUpdate) {
            ps.setLong(7, model.getId());
        }

        return ps;
    }

    @Override
    List<AwardsSphere> toList(ResultSet rs) throws SQLException {

        List<AwardsSphere> awardsSpheresList = new ArrayList<AwardsSphere>();
        while (rs.next()) {
            AwardsSphere awardsSphere = toModel(rs);
            awardsSpheresList.add(awardsSphere);
        }

        return awardsSpheresList;
    }
}
