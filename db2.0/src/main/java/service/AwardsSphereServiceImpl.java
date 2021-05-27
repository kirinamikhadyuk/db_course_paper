package service;

import java.sql.SQLException;

import api.dao.IAwardsSphereDao;
import api.service.IAwardsSphereService;
import dao.AwardsSphereDaoImpl;
import model.AwardsSphere;

import api.dao.IModelDao;

public class AwardsSphereServiceImpl extends GenericServiceImpl<AwardsSphere> implements IAwardsSphereService {

    private IAwardsSphereDao dao = new AwardsSphereDaoImpl();

    @Override
    public AwardsSphere getByIdWithPersonAndSphere(long id) {

        AwardsSphere model = null;

        try {

            model = dao.getByIdWithPersonAndSphere(id, connection);
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
    IModelDao<AwardsSphere> getDAO() {
        return dao;
    }

}
