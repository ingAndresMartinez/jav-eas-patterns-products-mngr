package co.edu.javeriana.eas.patterns.products.dtos;

import java.util.List;

public class ProductServiceWrapperDto {

    private int categoryId;
    private String categoryDescription;
    private List<ProductServiceDto> productsServices;

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

    public List<ProductServiceDto> getProductsServices() {
        return productsServices;
    }

    public void setProductsServices(List<ProductServiceDto> productsServices) {
        this.productsServices = productsServices;
    }

    @Override
    public String toString() {
        return "ProductServiceWrapperDto{" +
                "categoryId=" + categoryId +
                ", categoryDescription='" + categoryDescription + '\'' +
                ", productsServices=" + productsServices +
                '}';
    }

}