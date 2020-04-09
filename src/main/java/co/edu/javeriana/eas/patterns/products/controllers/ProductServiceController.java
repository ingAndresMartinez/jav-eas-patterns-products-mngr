package co.edu.javeriana.eas.patterns.products.controllers;

import co.edu.javeriana.eas.patterns.common.enums.EExceptionCode;
import co.edu.javeriana.eas.patterns.common.exceptions.QuotationCoreException;
import co.edu.javeriana.eas.patterns.products.dtos.ProductServiceBaseDto;
import co.edu.javeriana.eas.patterns.products.dtos.ProductServiceDto;
import co.edu.javeriana.eas.patterns.products.dtos.ProductServiceWrapperDto;
import co.edu.javeriana.eas.patterns.products.enums.ECategory;
import co.edu.javeriana.eas.patterns.products.exceptions.ProductServiceNotFoundException;
import co.edu.javeriana.eas.patterns.products.exceptions.SubCategoryException;
import co.edu.javeriana.eas.patterns.products.services.IProductServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-service")
public class ProductServiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceController.class);

    private IProductServiceService productServiceService;

    @GetMapping
    public ResponseEntity<List<ProductServiceWrapperDto>> getAllProductsServices() {
        try {
            return new ResponseEntity<>(productServiceService.getAllProducts(), HttpStatus.OK);
        } catch (ProductServiceNotFoundException e) {
            LOGGER.error("Error en consulta de productos y servicios [getAllProductsServices]", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category/{eCategory}")
    public ResponseEntity<ProductServiceWrapperDto> getAllProductsServicesByCategory(@PathVariable ECategory eCategory) {
        try {
            return new ResponseEntity<>(productServiceService.getProductsByCategory(eCategory), HttpStatus.OK);
        } catch (ProductServiceNotFoundException e) {
            LOGGER.error("Error en consulta de productos y servicios [getAllProductsServicesByCategory]", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/sub-category/{subCategoryId}")
    public ResponseEntity<ProductServiceWrapperDto> getAllProductsServicesBySubCategory(@PathVariable int subCategoryId) {
        try {
            return new ResponseEntity<>(productServiceService.getProductsBySubCategory(subCategoryId), HttpStatus.OK);
        } catch (QuotationCoreException e) {
            LOGGER.error("Error en consulta de productos y servicios [getAllProductsServicesBySubCategory]", e);
            HttpStatus httpStatus = HttpStatus.NOT_FOUND;
            if (e.getExceptionCode() == EExceptionCode.BLOCKING) {
                httpStatus = HttpStatus.BAD_REQUEST;
            }
            return new ResponseEntity<>(httpStatus);
        }
    }

    @PostMapping
    public ResponseEntity<Void> addNewProductService(@RequestBody ProductServiceBaseDto productServiceBase) {
        try {
            productServiceService.addNewProductOrService(productServiceBase);
        } catch (SubCategoryException e) {
            LOGGER.error("Error en creación de productos y servicios", e);
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateProductService(@RequestBody ProductServiceDto productService) {
        try {
            productServiceService.updateProductOrService(productService);
        } catch (ProductServiceNotFoundException e) {
            LOGGER.error("Error en actualización de productos y servicios", e);
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Autowired
    public void setProductServiceService(IProductServiceService productServiceService) {
        this.productServiceService = productServiceService;
    }
}