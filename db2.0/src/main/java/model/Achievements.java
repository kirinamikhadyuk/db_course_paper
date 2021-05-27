package model;

public final class Achievements extends Model {

    private Person person;

    private AwardName award;

    public Achievements() {
    }

    public Achievements(Person person, AwardName award) {
        this.person = person;
        this.award = award;
    }

    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    public AwardName getAward() { return award; }
    public void setAward(AwardName name) { this.award = award; }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nНаграда: ");
        builder.append(award);
        return builder.toString();
    }
}

