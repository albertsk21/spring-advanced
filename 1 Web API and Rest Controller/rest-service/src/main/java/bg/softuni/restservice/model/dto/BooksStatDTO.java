package bg.softuni.restservice.model.dto;

public class BooksStatDTO {


    public Long count;

    public BooksStatDTO() {
    }

    public Long getCount() {
        return count;
    }

    public BooksStatDTO setBooksCnt(Long count) {
        this.count = count;
        return this;
    }
}
