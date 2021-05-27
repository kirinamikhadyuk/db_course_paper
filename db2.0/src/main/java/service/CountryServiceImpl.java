package service;

import api.dao.IModelDao;
import api.service.IModelService;
import dao.CountryDaoImpl;
import dao.PersonDaoImpl;
import model.Country;

public class CountryServiceImpl extends GenericServiceImpl<Country> implements IModelService<Country> {

    private IModelDao dao = new CountryDaoImpl();

    @Override
    IModelDao<Country> getDAO() {
        return dao;
    }
}
