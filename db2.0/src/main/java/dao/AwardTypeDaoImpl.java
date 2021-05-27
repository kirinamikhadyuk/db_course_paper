package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.dao.IModelDao;
import model.AwardType;

public class AwardTypeDaoImpl extends GenericDaoImpl<AwardType> implements IModelDao<AwardType> {

    private final static String CREATE = "INSERT INTO dbo.[Awards type] (type) VALUES ((?))";
    private final static String GET_BY_ID = "SELECT * FROM dbo.[Awards type] WHERE dbo.[Awards type].primary_key = (?)";
    private final static String GET_ALL = "SELECT * FROM dbo.[Awards type]";
    private final static String DELETE = "DELETE FROM dbo.[Awards type] WHERE dbo.[Awards type].primary_key = (?)";

    public AwardTypeDaoImpl() {
        super(CREATE, GET_BY_ID, GET_ALL, DELETE);
    }

    @Override
    AwardType toModel(ResultSet rs) throws SQLException {

        AwardType type = new AwardType();

        type.setId(rs.getLong("primary_key"));
        type.setAwardType(rs.getString("type"));

        return type;
    }

    @Override
    PreparedStatement fillPStatement(PreparedStatement ps, AwardType model, boolean isUpdate) throws SQLException {

        ps.setString(1, model.getAwardType());

        if (isUpdate) {
            ps.setLong(7, model.getId());
        }

        return ps;
    }

    @Override
    List<AwardType> toList(ResultSet rs) throws SQLException {

        List<AwardType> awardTypeList = new ArrayList<AwardType>();
        while (rs.next()) {
            AwardType type = toModel(rs);
            awardTypeList.add(type);
        }

        return awardTypeList;
    }
}
