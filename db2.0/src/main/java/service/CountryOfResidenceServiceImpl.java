package service;

import java.sql.SQLException;

import api.dao.ICountryOfResidenceDao;
import api.service.ICountryOfResidenceService;
import dao.CountryOfResidenceDaoImpl;
import model.CountryOfResidence;

import api.dao.IModelDao;

public class CountryOfResidenceServiceImpl extends GenericServiceImpl<CountryOfResidence> implements ICountryOfResidenceService {

    private ICountryOfResidenceDao dao = new CountryOfResidenceDaoImpl();

    @Override
    public CountryOfResidence getByIdWithPersonAndCountry(long id) {

        CountryOfResidence model = null;

        try {

            model = dao.getByIdWithPersonAndCountry(id, connection);
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
    IModelDao<CountryOfResidence> getDAO() {
        return dao;
    }

}
