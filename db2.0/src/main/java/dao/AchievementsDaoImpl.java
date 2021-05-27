package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.dao.IAchievementsDao;
import model.Achievements;
import model.AwardName;
import model.Person;

public class AchievementsDaoImpl extends GenericDaoImpl<Achievements> implements IAchievementsDao {



    private final static String CREATE = "INSERT INTO dbo.Achivements (person_key, award_name_key) VALUES ((?), (?))";
    private final static String GET_BY_ID = "SELECT * FROM dbo.Achivements WHERE dbo.Achivements.person_key = (?)";
    private final static String GET_ALL = "SELECT * FROM dbo.Achivements";
    private final static String DELETE = "DELETE FROM dbo.Achivements WHERE dbo.Achivements.person_key = (?)";

    private final String ACHIEVEMENT_GET_BY_ID_WITH_TYPE = "SELECT * FROM dbo.Achivements LEFT OUTER JOIN dbo.[Awards name] ON dbo.[Awards name].primary_key = dbo.Achivements.award_name_key LEFT OUTER JOIN dbo.Person on dbo.Achivements.person_key =  dbo.Person.primary_key WHERE dbo.Achivements.person_key = (?)";


    public AchievementsDaoImpl() {
        super(CREATE, GET_BY_ID, GET_ALL, DELETE);
    }

    @Override
    public Achievements getByIdWithPersonAndAward(long id, Connection connection) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(ACHIEVEMENT_GET_BY_ID_WITH_TYPE)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            Achievements achievement = toAchievementWithPersonAndAward(rs);

            return achievement;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    private Achievements toAchievementWithPersonAndAward(ResultSet rs) throws SQLException {

        rs.next();
        Achievements achievement = toModel(rs);
        AwardName name = new AwardNameDaoImpl().toModel(rs);
        Person person = new PersonDaoImpl().toModel(rs);
        achievement.setAward(name);
        achievement.setPerson(person);

        return achievement;

    }

    @Override
    Achievements toModel(ResultSet rs) throws SQLException {

        Achievements achievement = new Achievements();

        return achievement;
    }

    @Override
    PreparedStatement fillPStatement(PreparedStatement ps, Achievements model, boolean isUpdate) throws SQLException {

        if (isUpdate) {
            ps.setLong(7, model.getId());
        }

        return ps;
    }

    @Override
    List<Achievements> toList(ResultSet rs) throws SQLException {

        List<Achievements> achievementsList = new ArrayList<Achievements>();
        while (rs.next()) {
            Achievements achievement = toModel(rs);
            achievementsList.add(achievement);
        }

        return achievementsList;
    }
}
