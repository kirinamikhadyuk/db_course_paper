package service;

import java.sql.SQLException;

import api.dao.IPersonDao;
import api.service.IPersonService;
import dao.PersonDaoImpl;
import model.Person;

import api.dao.IModelDao;

public class PersonServiceImpl extends GenericServiceImpl<Person> implements IPersonService {

    private IPersonDao dao = new PersonDaoImpl();

    @Override
    public Person getByIdWithCountry(long id) {

        Person model = null;

        try {

            model = dao.getByIdWithCountry(id, connection);
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
    IModelDao<Person> getDAO() {
        return dao;
    }

}
