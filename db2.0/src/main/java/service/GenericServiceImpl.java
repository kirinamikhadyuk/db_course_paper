package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import api.dao.IModelDao;
import api.service.IModelService;
import model.Model;
import util.ConnectorDB;

abstract class GenericServiceImpl<T extends Model> implements IModelService<T> {

	Connection connection = ConnectorDB.getConnectorDBInstance().getConnection();

	@Override
	public void create(T model) {

		try {
			
			getDAO().create(model, connection);
			connection.commit();

		} catch (SQLException e) {

			System.out.println(e);

			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println(e1);
			}
		}
	}

	@Override
	public T getById(long id) {

		T model = null;

		try {

			model = getDAO().getById(id, connection);
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
	public List<T> getAll() {

		List<T> modelList = null;

		try {

			modelList = getDAO().getAll(connection);
			connection.commit();

		} catch (SQLException e) {

			System.out.println(e);

			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println(e1);
			}
		}
		return modelList;
	}

	@Override
	public void delete(T model) {

		try {

			getDAO().delete(model, connection);
			connection.commit();

		} catch (SQLException e) {

			System.out.println(e);

			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println(e1);
			}
		}
	}

	abstract IModelDao<T> getDAO();
}
