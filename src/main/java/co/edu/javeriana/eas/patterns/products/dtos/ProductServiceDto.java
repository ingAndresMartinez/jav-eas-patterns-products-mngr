package co.edu.javeriana.eas.patterns.products.dtos;

public class ProductServiceDto extends ProductServiceBaseDto {

    private String subCategoryDescription;
    private int productServiceId;

    public String getSubCategoryDescription() {
        return subCategoryDescription;
    }

    public void setSubCategoryDescription(String subCategoryDescription) {
        this.subCategoryDescription = subCategoryDescription;
    }

    public int getProductServiceId() {
        return productServiceId;
    }

    public void setProductServiceId(int productServiceId) {
        this.productServiceId = productServiceId;
    }

    @Override
    public String toString() {
        return "ProductServiceDto{" +
                "subCategoryDescription='" + subCategoryDescription + '\'' +
                ", productServiceId=" + productServiceId +
                '}';
    }

}