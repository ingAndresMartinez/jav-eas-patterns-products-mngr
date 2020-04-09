package co.edu.javeriana.eas.patterns.products.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductServiceBaseDto {

    private int subCategoryId;
    @NotNull(message = "Please provide a productServiceDescription")
    @NotEmpty(message = "Please provide a productServiceDescription")
    private String productServiceDescription;

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getProductServiceDescription() {
        return productServiceDescription;
    }

    public void setProductServiceDescription(String productServiceDescription) {
        this.productServiceDescription = productServiceDescription;
    }

    @Override
    public String toString() {
        return "ProductServiceBaseDto{" +
                "subCategoryId=" + subCategoryId +
                ", productServiceDescription='" + productServiceDescription + '\'' +
                '}';
    }

}