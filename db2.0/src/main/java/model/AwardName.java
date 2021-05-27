package model;

public final class AwardName extends Model {

    private String name;

    private AwardType type;

    public AwardName() {
    }

    public AwardName(String name, AwardType type) {
        this.name = name;
        this.type = type;
    }

    public String getAwardName() {
        return name;
    }
    public void setAwardName(String name) {
        this.name = name;
    }

    public AwardType getAwardType() { return type; }
    public void setAwardType(AwardType type) { this.type = type; }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nТип: ");
        builder.append(type);
        builder.append("\nНазвание");
        builder.append(name);
        return builder.toString();
    }
}

