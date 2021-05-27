package api.service;

import model.Person;

public interface IPersonService extends IModelService<Person>{

    public Person getByIdWithCountry(long id);
}
