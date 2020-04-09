package co.edu.javeriana.eas.patterns.products.dtos;

public class SubCategoryCreateDto extends SubCategoryBaseDto {

    private int categoryId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "SubCategoryCreateDto{" +
                "categoryId=" + categoryId +
                '}';
    }

}
