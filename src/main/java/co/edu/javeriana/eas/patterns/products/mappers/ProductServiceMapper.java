package co.edu.javeriana.eas.patterns.products.mappers;

import co.edu.javeriana.eas.patterns.persistence.entities.ProductServiceEntity;
import co.edu.javeriana.eas.patterns.persistence.entities.SubCategoryEntity;
import co.edu.javeriana.eas.patterns.products.dtos.ProductServiceBaseDto;
import co.edu.javeriana.eas.patterns.products.dtos.ProductServiceDto;

public class ProductServiceMapper {

    private ProductServiceMapper() {
    }

    public static ProductServiceDto productServiceEntityMapperInProductServiceDto(ProductServiceEntity productServiceEntity) {
        ProductServiceDto productServiceDto = new ProductServiceDto();
        productServiceDto.setProductServiceId(productServiceEntity.getId());
        productServiceDto.setProductServiceDescription(productServiceEntity.getDescription());
        productServiceDto.setSubCategoryId(productServiceEntity.getSubCategory().getId());
        productServiceDto.setSubCategoryDescription(productServiceEntity.getSubCategory().getDescription());
        return productServiceDto;
    }

    public static ProductServiceEntity productServiceBaseDtoMapperInProductServiceEntity(ProductServiceBaseDto productServiceBase, SubCategoryEntity subCategoryEntity) {
        ProductServiceEntity productServiceEntity = new ProductServiceEntity();
        productServiceEntity.setDescription(productServiceBase.getProductServiceDescription());
        productServiceEntity.setSubCategory(subCategoryEntity);
        return productServiceEntity;
    }

}