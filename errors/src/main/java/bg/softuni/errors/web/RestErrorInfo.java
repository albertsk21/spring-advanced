package bg.softuni.errors.web;

public class RestErrorInfo  {
    private String id;
    private String message;

    public RestErrorInfo(String id,String message) {
        this.message = message;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public RestErrorInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public RestErrorInfo setMessage(String message) {
        this.message = message;
        return this;
    }
}
