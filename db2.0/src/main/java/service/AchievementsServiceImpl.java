package service;

import java.sql.SQLException;

import api.dao.IAchievementsDao;
import api.service.IAchievementsService;
import dao.AchievementsDaoImpl;
import model.Achievements;

import api.dao.IModelDao;

public class AchievementsServiceImpl extends GenericServiceImpl<Achievements> implements IAchievementsService {

    private IAchievementsDao dao = new AchievementsDaoImpl();

    @Override
    public Achievements getByIdWithPersonAndAward(long id) {

        Achievements model = null;

        try {

            model = dao.getByIdWithPersonAndAward(id, connection);
            connection.commit();

        } catch (SQLException e) {

            System.out.println(e);

            try {
                connection.rollback();
            } catch (SQLException e1) {
                System.out.println(e1);
            }
        }
        return model;
    }

    @Override
    IModelDao<Achievements> getDAO() {
        return dao;
    }

}
