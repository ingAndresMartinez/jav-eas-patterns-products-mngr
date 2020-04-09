package co.edu.javeriana.eas.patterns.products.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SubCategoryBaseDto {

    @NotNull(message = "Please provide a subCategoryDescription")
    @NotEmpty(message = "Please provide a subCategoryDescription")
    private String subCategoryDescription;

    public String getSubCategoryDescription() {
        return subCategoryDescription;
    }

    public void setSubCategoryDescription(String subCategoryDescription) {
        this.subCategoryDescription = subCategoryDescription;
    }

}