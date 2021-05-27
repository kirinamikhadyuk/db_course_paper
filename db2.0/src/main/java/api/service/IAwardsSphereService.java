package api.service;

import model.AwardsSphere;

public interface IAwardsSphereService extends IModelService<AwardsSphere> {

    public AwardsSphere getByIdWithPersonAndSphere(long id);
}
