package service;

import java.sql.SQLException;

import api.dao.IAwardNameDao;
import api.service.IAwardNameService;
import dao.AwardNameDaoImpl;
import model.AwardName;

import api.dao.IModelDao;

public class AwardNameServiceImpl extends GenericServiceImpl<AwardName> implements IAwardNameService {

    private IAwardNameDao dao = new AwardNameDaoImpl();

    @Override
    public AwardName getByIdWithType(long id) {

        AwardName model = null;

        try {

            model = dao.getByIdWithType(id, connection);
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
    IModelDao<AwardName> getDAO() {
        return dao;
    }

}
