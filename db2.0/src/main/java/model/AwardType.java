package model;

public final class AwardType extends Model {

    private String type;

    public AwardType() {
    }

    public AwardType(String type) {
        this.type = type;
    }

    public String getAwardType() {
        return type;
    }
    public void setAwardType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nСфера");
        builder.append(type);
        return builder.toString();
    }
}

