package co.edu.javeriana.eas.patterns.products.mappers;

import co.edu.javeriana.eas.patterns.persistence.entities.CategoryEntity;
import co.edu.javeriana.eas.patterns.persistence.entities.SubCategoryEntity;
import co.edu.javeriana.eas.patterns.products.dtos.SubCategoryCreateDto;
import co.edu.javeriana.eas.patterns.products.dtos.SubCategoryDto;

public class SubCategoryMapper {

    private SubCategoryMapper() {
    }

    public static SubCategoryDto subCategoryEntityMapperInSubCategoryDto(SubCategoryEntity subCategoryEntity) {
        SubCategoryDto subCategoryDto = new SubCategoryDto();
        subCategoryDto.setSubCategoryId(subCategoryEntity.getId());
        subCategoryDto.setSubCategoryDescription(subCategoryEntity.getDescription());
        return subCategoryDto;
    }

    public static SubCategoryEntity subCategoryDtoMapperInSubCategoryEntity(SubCategoryCreateDto subCategoryCreate, CategoryEntity categoryEntity) {
        SubCategoryEntity subCategoryEntity = new SubCategoryEntity();
        subCategoryEntity.setDescription(subCategoryCreate.getSubCategoryDescription());
        subCategoryEntity.setCategory(categoryEntity);
        return subCategoryEntity;
    }

}