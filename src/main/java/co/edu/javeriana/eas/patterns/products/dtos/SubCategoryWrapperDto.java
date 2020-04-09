package co.edu.javeriana.eas.patterns.products.dtos;

import java.util.List;

public class SubCategoryWrapperDto {

    private int categoryId;
    private String categoryDescription;
    private List<SubCategoryDto> subCategories;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public List<SubCategoryDto> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategoryDto> subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public String toString() {
        return "SubCategoryWrapperDto{" +
                "categoryId=" + categoryId +
                ", categoryDescription='" + categoryDescription + '\'' +
                ", subCategories=" + subCategories +
                '}';
    }

}