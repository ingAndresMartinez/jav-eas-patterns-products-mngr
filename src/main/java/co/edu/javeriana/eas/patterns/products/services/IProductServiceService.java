package co.edu.javeriana.eas.patterns.products.services;

import co.edu.javeriana.eas.patterns.common.exceptions.QuotationCoreException;
import co.edu.javeriana.eas.patterns.products.dtos.ProductServiceBaseDto;
import co.edu.javeriana.eas.patterns.products.dtos.ProductServiceDto;
import co.edu.javeriana.eas.patterns.products.dtos.ProductServiceWrapperDto;
import co.edu.javeriana.eas.patterns.products.enums.ECategory;
import co.edu.javeriana.eas.patterns.products.exceptions.ProductServiceNotFoundException;
import co.edu.javeriana.eas.patterns.products.exceptions.SubCategoryException;

import java.util.List;

public interface IProductServiceService {

    List<ProductServiceWrapperDto> getAllProducts() throws ProductServiceNotFoundException;

    ProductServiceWrapperDto getProductsByCategory(ECategory eCategory) throws ProductServiceNotFoundException;

    ProductServiceWrapperDto getProductsBySubCategory(int subCategory) throws QuotationCoreException;

    void addNewProductOrService(ProductServiceBaseDto productServiceBase) throws SubCategoryException;

    void updateProductOrService(ProductServiceDto productService) throws ProductServiceNotFoundException;

}