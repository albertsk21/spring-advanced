package bg.softuni.errors.web;

public class StudentNotFoundException extends RuntimeException {
    private String id;

    public StudentNotFoundException( String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public StudentNotFoundException setId(String id) {
        this.id = id;
        return this;
    }
}
