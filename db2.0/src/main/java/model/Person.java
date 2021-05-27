package model;

import model.interfaces.IPerson;

import java.util.Date;

public final class Person extends Model implements IPerson {

    private long id;

    private String lastName;

    private String firstName;

    private String patronymic;

    private Date dateOfBirth;

    private Date dateOfDeath;

    private Country countryBirth;

    public Person() {
    }

    public Person(long id, String lastName, String firstName, String patronymic, Date dateOfBirth, Date dateOfDeath,
                  Country countryBirth) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.countryBirth = countryBirth;
    }
    @Override
    public long getId(){return id;}
    @Override
    public void setId(long id){this.id = id;}
    @Override
    public String getLastName() {
        return lastName;
    }
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Override
    public String getFirstName() {
        return firstName;
    }
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Override
    public String getPatronymic() { return patronymic; }
    @Override
    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }
    @Override
    public java.sql.Date getDateOfBirth() { return (java.sql.Date) dateOfBirth; }
    @Override
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    @Override
    public java.sql.Date getDateOfDeath() {
        return (java.sql.Date) dateOfDeath;
    }
    @Override
    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }
    @Override
    public Country getCountryBirth() {
        return countryBirth;
    }
    @Override
    public void setCountryBirth(Country countryBirth) {
        this.countryBirth = countryBirth;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Фамилия: ");
        builder.append(lastName);
        builder.append("\nИмя: ");
        builder.append(firstName);
        builder.append("\nОтчество: ");
        builder.append(patronymic);
        builder.append("\nДата рождения: ");
        builder.append(dateOfBirth);
        builder.append("\nДата смерти: ");
        builder.append(dateOfDeath);
        return builder.toString();
    }
}
