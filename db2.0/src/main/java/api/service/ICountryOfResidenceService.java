package api.service;

import model.CountryOfResidence;

public interface ICountryOfResidenceService extends IModelService<CountryOfResidence>{

    public CountryOfResidence getByIdWithPersonAndCountry(long id);
}
