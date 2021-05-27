package model;

public final class Country extends Model {

    private String countryName;

    public Country() {
    }

    public Country(String countryName) {
        this.countryName = countryName;
    }

    public String getCountry() {
        return countryName;
    }
    public void setCountry(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nСтрана: ");
        builder.append(countryName);
        return builder.toString();
    }
}

