package api.dao;

import java.sql.Connection;
import java.sql.SQLException;

import model.Achievements;

public interface IAchievementsDao extends IModelDao<Achievements> {

    public Achievements getByIdWithPersonAndAward(long id, Connection connection) throws SQLException;

}