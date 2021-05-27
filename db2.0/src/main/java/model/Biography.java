package model;

public final class Biography extends Model {

    private Person person;

    private String biography;

    public Biography() {
    }

    public Biography(Person person, String biography) {
        this.person = person;
        this.biography = biography;
    }

    public  Person setPerson() { return person; }
    public void setPerson(Person person) { this.person = person; }

    public String getBiography() {
        return biography;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nБиография");
        builder.append(biography);
        return builder.toString();
    }
}

