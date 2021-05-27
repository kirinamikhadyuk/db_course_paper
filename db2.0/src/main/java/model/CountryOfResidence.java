package model;

import java.sql.Date;

public final class CountryOfResidence extends Model {

    private Person person;

    private Date dateFrom;

    private Date dateTo;

    private Country country;

    public CountryOfResidence() {
    }

    public CountryOfResidence(Person person, Date dateFrom, Date dateTo, Country country) {
        this.person = person;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.country = country;
    }

    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    public java.sql.Date getDateFrom() { return dateFrom; }
    public void setDateFrom(Date dateFrom) { this.dateFrom = dateFrom; }

    public java.sql.Date getDateTo() { return dateTo; }
    public void setDateTo(Date dateTo) { this.dateTo = dateTo; }

    public Country getCountry() { return country; }
    public void setCountry(Country country) { this.country = country; }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Страна проживания: ");
        builder.append(country);
        builder.append("\nс: ");
        builder.append(dateTo);
        builder.append("\nпо: ");
        builder.append(dateFrom);
        return builder.toString();
    }
}

