package api.dao;

import java.sql.Connection;
import java.sql.SQLException;

import model.CountryOfResidence;

public interface ICountryOfResidenceDao extends IModelDao<CountryOfResidence> {

    public CountryOfResidence getByIdWithPersonAndCountry(long id, Connection connection) throws SQLException;

}
