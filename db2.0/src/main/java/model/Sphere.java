package model;

public final class Sphere extends Model {

    private String sphereName;

    public Sphere() {
    }

    public Sphere(String sphereName) {
        this.sphereName = sphereName;
    }

    public String getSphere() {
        return sphereName;
    }
    public void setSphere(String sphereName) {
        this.sphereName = sphereName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nСфера: ");
        builder.append(sphereName);
        return builder.toString();
    }
}

