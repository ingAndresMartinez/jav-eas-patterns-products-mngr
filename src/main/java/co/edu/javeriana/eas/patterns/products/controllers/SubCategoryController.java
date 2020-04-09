package co.edu.javeriana.eas.patterns.products.controllers;

import co.edu.javeriana.eas.patterns.products.dtos.SubCategoryCreateDto;
import co.edu.javeriana.eas.patterns.products.dtos.SubCategoryWrapperDto;
import co.edu.javeriana.eas.patterns.products.enums.ECategory;
import co.edu.javeriana.eas.patterns.products.exceptions.SubCategoryException;
import co.edu.javeriana.eas.patterns.products.services.ISubCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sub-category")
public class SubCategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubCategoryController.class);

    private ISubCategoryService subCategoryService;

    @GetMapping
    public ResponseEntity<List<SubCategoryWrapperDto>> getAllSubCategories() {
        try {
            return new ResponseEntity<>(subCategoryService.getAllSubCategories(), HttpStatus.OK);
        } catch (SubCategoryException e) {
            LOGGER.error("Error en consulta de sub categorias [getAllSubCategories]", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{eCategory}")
    public ResponseEntity<SubCategoryWrapperDto> getAllSubCategoriesByCategory(@PathVariable ECategory eCategory) {
        try {
            return new ResponseEntity<>(subCategoryService.getAllSubCategoriesByCategory(eCategory), HttpStatus.OK);
        } catch (SubCategoryException e) {
            LOGGER.error("Error en consulta de sub categorias [getAllSubCategoriesByCategory]", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> addNewSubCategory(@RequestBody SubCategoryCreateDto subCategoryCreate) {
        try {
            subCategoryService.addNewSubCategory(subCategoryCreate);
        } catch (SubCategoryException e) {
            LOGGER.error("Error en creación de sub categoria", e);
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Autowired
    public void setSubCategoryService(ISubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

}