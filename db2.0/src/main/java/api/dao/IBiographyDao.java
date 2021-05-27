package api.dao;

import java.sql.Connection;
import java.sql.SQLException;

import model.Biography;

public interface IBiographyDao extends IModelDao<Biography> {

    public Biography getByIdWithPerson(long id, Connection connection) throws SQLException;

}