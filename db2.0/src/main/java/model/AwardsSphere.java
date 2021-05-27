package model;

public final class AwardsSphere extends Model {

    private Person person;

    private Sphere sphere;

    public AwardsSphere() {
    }

    public AwardsSphere(Person person, Sphere sphere) {
        this.person = person;
        this.sphere = sphere;
    }

    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    public Sphere getDateFrom() { return sphere; }
    public void setSphere(Sphere sphere) { this.sphere = sphere; }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nСфера: ");
        builder.append(sphere);
        return builder.toString();
    }
}

