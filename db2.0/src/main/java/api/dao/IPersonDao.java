package api.dao;

import java.sql.Connection;
import java.sql.SQLException;

import model.Person;

public interface IPersonDao extends IModelDao<Person> {

    public Person getByIdWithCountry(long id, Connection connection) throws SQLException;

}
