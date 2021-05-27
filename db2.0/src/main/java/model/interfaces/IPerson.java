package model.interfaces;

import model.Country;
import model.Model;

import java.util.Date;

public interface IPerson {

    public String getLastName();
    public void setLastName(String lastName);
    public String getFirstName();
    public void setFirstName(String firstName);
    public String getPatronymic();
    public void setPatronymic(String patronymic);
    public java.sql.Date getDateOfBirth();
    public void setDateOfBirth(Date dateOfBirth);
    public java.sql.Date getDateOfDeath();
    public void setDateOfDeath(Date dateOfDeath);
    public Country getCountryBirth();
    public void setCountryBirth(Country countryBirth);

    long getId();
}
