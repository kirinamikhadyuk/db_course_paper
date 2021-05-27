package api.service;

import model.Achievements;

public interface IAchievementsService extends IModelService<Achievements> {

    public Achievements getByIdWithPersonAndAward(long id);
}
