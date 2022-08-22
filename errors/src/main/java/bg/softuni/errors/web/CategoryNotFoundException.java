package bg.softuni.errors.web;

public class CategoryNotFoundException extends RuntimeException{


    private String categoryId;
    public CategoryNotFoundException(String id) {
        super("Category with this id: " + id + " not found");
         this.categoryId = id;
    }

    public CategoryNotFoundException setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }
}
