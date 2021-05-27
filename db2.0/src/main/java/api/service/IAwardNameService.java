package api.service;

import model.AwardName;

public interface IAwardNameService extends IModelService<AwardName> {

    public AwardName getByIdWithType(long id);
}
