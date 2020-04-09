package co.edu.javeriana.eas.patterns.products.services;

import co.edu.javeriana.eas.patterns.products.dtos.SubCategoryCreateDto;
import co.edu.javeriana.eas.patterns.products.dtos.SubCategoryWrapperDto;
import co.edu.javeriana.eas.patterns.products.enums.ECategory;
import co.edu.javeriana.eas.patterns.products.exceptions.SubCategoryException;

import java.util.List;

public interface ISubCategoryService {

    List<SubCategoryWrapperDto> getAllSubCategories() throws SubCategoryException;

    SubCategoryWrapperDto getAllSubCategoriesByCategory(ECategory eCategory) throws SubCategoryException;

    void addNewSubCategory(SubCategoryCreateDto subCategoryCreate) throws SubCategoryException;

}