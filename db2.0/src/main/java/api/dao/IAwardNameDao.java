package api.dao;

import java.sql.Connection;
import java.sql.SQLException;

import model.AwardName;

public interface IAwardNameDao extends IModelDao<AwardName> {

    public AwardName getByIdWithType(long id, Connection connection) throws SQLException;

}