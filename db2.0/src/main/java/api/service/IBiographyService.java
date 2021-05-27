package api.service;

import model.Biography;

public interface IBiographyService extends IModelService<Biography>{

    public Biography getByIdWithPerson(long id);
}
