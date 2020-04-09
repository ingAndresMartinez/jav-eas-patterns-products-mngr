package co.edu.javeriana.eas.patterns.products.services.impl;

import co.edu.javeriana.eas.patterns.common.enums.EExceptionCode;
import co.edu.javeriana.eas.patterns.persistence.entities.CategoryEntity;
import co.edu.javeriana.eas.patterns.persistence.entities.SubCategoryEntity;
import co.edu.javeriana.eas.patterns.persistence.repositories.ICategoryRepository;
import co.edu.javeriana.eas.patterns.persistence.repositories.ISubCategoryRepository;
import co.edu.javeriana.eas.patterns.products.dtos.*;
import co.edu.javeriana.eas.patterns.products.enums.ECategory;
import co.edu.javeriana.eas.patterns.products.exceptions.SubCategoryException;
import co.edu.javeriana.eas.patterns.products.mappers.SubCategoryMapper;
import co.edu.javeriana.eas.patterns.products.services.ISubCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubCategoryServiceImpl implements ISubCategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubCategoryServiceImpl.class);

    private ICategoryRepository categoryRepository;
    private ISubCategoryRepository subCategoryRepository;

    @Override
    public List<SubCategoryWrapperDto> getAllSubCategories() throws SubCategoryException {
        LOGGER.info("INICIA CONSULTA DE SUB CATEGORIAS ACTUALES.");
        List<SubCategoryEntity> allSubCategories = new ArrayList<>();
        subCategoryRepository.findAll().forEach(allSubCategories::add);
        validateHasSubCategories(allSubCategories);
        Map<ECategory, List<SubCategoryDto>> filterByCategory = filterListByCategory(allSubCategories);
        List<SubCategoryWrapperDto> result = Arrays.asList(createWrapper(ECategory.PRODUCT, filterByCategory.get(ECategory.PRODUCT)),
                createWrapper(ECategory.SERVICE, filterByCategory.get(ECategory.SERVICE)));
        LOGGER.info("FINALIZA CONSULTA DE SUB CATEGORIAS ACTUALES. RESULTADO [{}]", result);
        return result;
    }

    @Override
    public SubCategoryWrapperDto getAllSubCategoriesByCategory(ECategory eCategory) throws SubCategoryException {
        LOGGER.info("INICIA CONSULTA DE SUB CATEGORIAS ACTUALES. [{}]", eCategory);
        List<SubCategoryEntity> listSubCategories = subCategoryRepository.findByCategory(eCategory.getCategory());
        validateHasSubCategories(listSubCategories);
        Map<ECategory, List<SubCategoryDto>> filterByCategory = filterListByCategory(listSubCategories);
        SubCategoryWrapperDto result = createWrapper(eCategory, filterByCategory.get(eCategory));
        LOGGER.info("FINALIZA CONSULTA DE SUB CATEGORIAS ACTUALES [{}]. RESULTADO -> [{}]", eCategory, result);
        return result;
    }

    @Override
    public void addNewSubCategory(SubCategoryCreateDto subCategoryCreate) throws SubCategoryException {
        LOGGER.info("INICIA CREACIÓN DE SUB CATEGORIA. [{}]", subCategoryCreate);
        CategoryEntity categoryEntity = categoryRepository.findById(subCategoryCreate.getCategoryId())
                .orElseThrow(() -> new SubCategoryException(EExceptionCode.BLOCKING, "La categoria no existe."));
        SubCategoryEntity subCategoryEntity = SubCategoryMapper
                .subCategoryDtoMapperInSubCategoryEntity(subCategoryCreate, categoryEntity);
        subCategoryRepository.save(subCategoryEntity);
        LOGGER.info("FINALIZA CREACIÓN DE SUB CATEGORIA. [{}]", subCategoryCreate);
    }

    private void validateHasSubCategories(List<SubCategoryEntity> allSubCategories) throws SubCategoryException {
        if (allSubCategories.isEmpty()) {
            throw new SubCategoryException(EExceptionCode.BLOCKING, "No se encontraron categorias registradas.");
        }
    }

    private Map<ECategory, List<SubCategoryDto>> filterListByCategory(List<SubCategoryEntity> allSubCategories) {
        List<SubCategoryDto> products = new ArrayList<>();
        List<SubCategoryDto> services = new ArrayList<>();
        allSubCategories.forEach(subCategoryEntity -> {
            if (subCategoryEntity.getCategory().getId() == ECategory.PRODUCT.getCategory()) {
                products.add(SubCategoryMapper.subCategoryEntityMapperInSubCategoryDto(subCategoryEntity));
            } else {
                services.add(SubCategoryMapper.subCategoryEntityMapperInSubCategoryDto(subCategoryEntity));
            }
        });
        Map<ECategory, List<SubCategoryDto>> filter = new EnumMap<>(ECategory.class);
        filter.put(ECategory.PRODUCT, products);
        filter.put(ECategory.SERVICE, services);
        return filter;
    }

    private SubCategoryWrapperDto createWrapper(ECategory eCategory, List<SubCategoryDto> list) {
        SubCategoryWrapperDto subCategoryWrapperDto = new SubCategoryWrapperDto();
        subCategoryWrapperDto.setCategoryId(eCategory.getCategory());
        subCategoryWrapperDto.setCategoryDescription(eCategory.name());
        subCategoryWrapperDto.setSubCategories(list);
        return subCategoryWrapperDto;
    }

    @Autowired
    public void setCategoryRepository(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setSubCategoryRepository(ISubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

}