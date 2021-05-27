package api.dao;

import java.sql.Connection;
import java.sql.SQLException;

import model.AwardsSphere;

public interface IAwardsSphereDao extends IModelDao<AwardsSphere> {

    public AwardsSphere getByIdWithPersonAndSphere(long id, Connection connection) throws SQLException;

}