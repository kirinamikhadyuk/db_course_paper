package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.dao.IModelDao;
import model.Sphere;

public class SphereDaoImpl extends GenericDaoImpl<Sphere> implements IModelDao<Sphere> {

    private final static String CREATE = "INSERT INTO dbo.Sphere (field) VALUES ((?))";
    private final static String GET_BY_ID = "SELECT * FROM dbo.Sphere WHERE dbo.Sphere.primary_key = (?)";
    private final static String GET_ALL = "SELECT * FROM dbo.Sphere";
    private final static String DELETE = "DELETE FROM dbo.Sphere WHERE dbo.Sphere.primary_key = (?)";

    public SphereDaoImpl() {
        super(CREATE, GET_BY_ID, GET_ALL, DELETE);
    }

    @Override
    Sphere toModel(ResultSet rs) throws SQLException {

        Sphere sphere = new Sphere();

        sphere.setId(rs.getLong("primary_key"));
        sphere.setSphere(rs.getString("field"));

        return sphere;
    }

    @Override
    PreparedStatement fillPStatement(PreparedStatement ps, Sphere model, boolean isUpdate) throws SQLException {

        ps.setString(1, model.getSphere());

        if (isUpdate) {
            ps.setLong(7, model.getId());
        }

        return ps;
    }

    @Override
    List<Sphere> toList(ResultSet rs) throws SQLException {

        List<Sphere> spheresList = new ArrayList<Sphere>();
        while (rs.next()) {
            Sphere sphere = toModel(rs);
            spheresList.add(sphere);
        }

        return spheresList;
    }
}
