package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.dao.IAwardNameDao;
import model.AwardName;
import model.AwardType;

public class AwardNameDaoImpl extends GenericDaoImpl<AwardName> implements IAwardNameDao {

    private final static String CREATE = "INSERT INTO dbo.[Awards name] (award_name) VALUES ((?))";
    private final static String GET_BY_ID = "SELECT * FROM dbo.[Awards name] WHERE dbo.[Awards name].primary_key = (?)";
    private final static String GET_ALL = "SELECT * FROM dbo.[Awards name]";
    private final static String DELETE = "DELETE FROM dbo.[Awards name] WHERE dbo.[Awards name].primary_key = (?)";

    private final String AWARD_NAME_GET_BY_ID_WITH_TYPE = "SELECT * FROM dbo.[Awards name] LEFT OUTER JOIN dbo.[Awards type] ON dbo.[Awards type].primary_key = dbo.[Awards name].type_key WHERE dbo.[Awards name].type_key = (?)";


    public AwardNameDaoImpl() {
        super(CREATE, GET_BY_ID, GET_ALL, DELETE);
    }

    @Override
    public AwardName getByIdWithType(long id, Connection connection) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(AWARD_NAME_GET_BY_ID_WITH_TYPE)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            AwardName awardName = toAwardNameWithType(rs);

            return awardName;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    private AwardName toAwardNameWithType(ResultSet rs) throws SQLException {

        rs.next();
        AwardName awardName = toModel(rs);
        AwardType type = new AwardTypeDaoImpl().toModel(rs);
        awardName.setAwardType(type);

        return awardName;

    }

    @Override
    AwardName toModel(ResultSet rs) throws SQLException {

        AwardName name = new AwardName();

        name.setId(rs.getLong("primary_key"));
        name.setAwardName(rs.getString("award_name"));

        return name;
    }

    @Override
    PreparedStatement fillPStatement(PreparedStatement ps, AwardName model, boolean isUpdate) throws SQLException {

        ps.setString(1, model.getAwardName());

        if (isUpdate) {
            ps.setLong(7, model.getId());
        }

        return ps;
    }

    @Override
    List<AwardName> toList(ResultSet rs) throws SQLException {

        List<AwardName> awardNameList = new ArrayList<AwardName>();
        while (rs.next()) {
            AwardName name = toModel(rs);
            awardNameList.add(name);
        }

        return awardNameList;
    }
}
