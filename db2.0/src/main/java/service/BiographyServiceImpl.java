package service;

import java.sql.SQLException;

import api.dao.IBiographyDao;
import api.service.IBiographyService;
import dao.BiographyDaoImpl;
import model.Biography;

import api.dao.IModelDao;

public class BiographyServiceImpl extends GenericServiceImpl<Biography> implements IBiographyService {

    private IBiographyDao dao = new BiographyDaoImpl();

    @Override
    public Biography getByIdWithPerson(long id) {

        Biography model = null;

        try {

            model = dao.getByIdWithPerson(id, connection);
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
    IModelDao<Biography> getDAO() {
        return dao;
    }

}
