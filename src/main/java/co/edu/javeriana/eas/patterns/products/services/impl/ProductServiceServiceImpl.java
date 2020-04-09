package co.edu.javeriana.eas.patterns.products.services.impl;

import co.edu.javeriana.eas.patterns.common.enums.EExceptionCode;
import co.edu.javeriana.eas.patterns.common.exceptions.QuotationCoreException;
import co.edu.javeriana.eas.patterns.persistence.entities.ProductServiceEntity;
import co.edu.javeriana.eas.patterns.persistence.entities.SubCategoryEntity;
import co.edu.javeriana.eas.patterns.persistence.repositories.IProductServiceRepository;
import co.edu.javeriana.eas.patterns.persistence.repositories.ISubCategoryRepository;
import co.edu.javeriana.eas.patterns.products.dtos.ProductServiceBaseDto;
import co.edu.javeriana.eas.patterns.products.dtos.ProductServiceDto;
import co.edu.javeriana.eas.patterns.products.dtos.ProductServiceWrapperDto;
import co.edu.javeriana.eas.patterns.products.enums.ECategory;
import co.edu.javeriana.eas.patterns.products.exceptions.ProductServiceNotFoundException;
import co.edu.javeriana.eas.patterns.products.exceptions.SubCategoryException;
import co.edu.javeriana.eas.patterns.products.mappers.ProductServiceMapper;
import co.edu.javeriana.eas.patterns.products.services.IProductServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceServiceImpl implements IProductServiceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceServiceImpl.class);

    private IProductServiceRepository productServiceRepository;
    private ISubCategoryRepository subCategoryRepository;

    @Override
    public List<ProductServiceWrapperDto> getAllProducts() throws ProductServiceNotFoundException {
        LOGGER.info("INICIA CONSULTA DE PRODUCTOS Y SERVICIOS ACTUALES.");
        List<ProductServiceEntity> allProductsServices = new ArrayList<>();
        productServiceRepository.findAll().forEach(allProductsServices::add);
        validateHasProductsAndServices(allProductsServices);
        Map<ECategory, List<ProductServiceDto>> filterByCategory = filterListByCategory(allProductsServices);
        List<ProductServiceWrapperDto> result = Arrays.asList(createWrapper(ECategory.PRODUCT, filterByCategory.get(ECategory.PRODUCT)),
                createWrapper(ECategory.SERVICE, filterByCategory.get(ECategory.SERVICE)));
        LOGGER.info("FINALIZA CONSULTA DE PRODUCTOS Y SERVICIOS ACTUALES. RESULTADO -> [{}]", result);
        return result;
    }

    @Override
    public ProductServiceWrapperDto getProductsByCategory(ECategory eCategory) throws ProductServiceNotFoundException {
        LOGGER.info("INICIA CONSULTA DE PRODUCTOS Y SERVICIOS ACTUALES POR CATEGORIA. [{}]", eCategory);
        List<ProductServiceEntity> listProductOrService = productServiceRepository.findByCategory(eCategory.getCategory());
        validateHasProductsAndServices(listProductOrService);
        Map<ECategory, List<ProductServiceDto>> filterByCategory = filterListByCategory(listProductOrService);
        ProductServiceWrapperDto result = createWrapper(eCategory, filterByCategory.get(eCategory));
        LOGGER.info("FINALIZA CONSULTA DE PRODUCTOS Y SERVICIOS ACTUALES POR CATEGORIA [{}]. RESULTADO -> [{}]", eCategory, result);
        return result;
    }

    @Override
    public ProductServiceWrapperDto getProductsBySubCategory(int subCategory) throws QuotationCoreException {
        LOGGER.info("INICIA CONSULTA DE PRODUCTOS Y SERVICIOS ACTUALES POR SUB CATEGORIA. [{}]", subCategory);
        SubCategoryEntity subCategoryEntity = subCategoryRepository.findById(subCategory)
                .orElseThrow(() -> new SubCategoryException(EExceptionCode.BLOCKING, "La Sub categoria no existe."));
        List<ProductServiceEntity> listProductOrService = productServiceRepository.findBySubCategory(subCategoryEntity);
        validateHasProductsAndServices(listProductOrService);
        Map<ECategory, List<ProductServiceDto>> filterByCategory = filterListByCategory(listProductOrService);
        ECategory eCategory = subCategoryEntity.getCategory().getId() == ECategory.PRODUCT.getCategory() ? ECategory.PRODUCT : ECategory.SERVICE;
        ProductServiceWrapperDto result = createWrapper(eCategory, filterByCategory.get(eCategory));
        LOGGER.info("FINALIZA CONSULTA DE PRODUCTOS Y SERVICIOS ACTUALES POR SUB CATEGORIA. [{}] RESULTADO -> [{}]", subCategory, result);
        return result;
    }

    @Override
    public void addNewProductOrService(ProductServiceBaseDto productServiceBase) throws SubCategoryException {
        LOGGER.info("INICIA CREACIÓN DE PRODUCTO O SERVICIO. [{}]", productServiceBase);
        SubCategoryEntity subCategoryEntity = subCategoryRepository.findById(productServiceBase.getSubCategoryId())
                .orElseThrow(() -> new SubCategoryException(EExceptionCode.BLOCKING, "La Sub categoria no existe."));
        ProductServiceEntity productServiceEntity = ProductServiceMapper
                .productServiceBaseDtoMapperInProductServiceEntity(productServiceBase, subCategoryEntity);
        productServiceRepository.save(productServiceEntity);
        LOGGER.info("FINALIZA CREACIÓN DE PRODUCTO O SERVICIO. [{}]", productServiceBase);
    }

    @Override
    public void updateProductOrService(ProductServiceDto productService) throws ProductServiceNotFoundException {
        LOGGER.info("INICIA MODIFICACIÓN DE PRODUCTO O SERVICIO. [{}]", productService);
        ProductServiceEntity productServiceEntity = productServiceRepository.findById(productService.getProductServiceId())
                .orElseThrow(() -> new ProductServiceNotFoundException(EExceptionCode.BLOCKING, "El producto no existe."));
        productServiceEntity.setDescription(productService.getProductServiceDescription());
        productServiceRepository.save(productServiceEntity);
        LOGGER.info("FINALIZA MODIFICACIÓN DE PRODUCTO O SERVICIO. [{}]", productService);
    }

    private void validateHasProductsAndServices(List<ProductServiceEntity> allProductsServices) throws ProductServiceNotFoundException {
        if (allProductsServices.isEmpty()) {
            throw new ProductServiceNotFoundException(EExceptionCode.NON_BLOCKING, "No se encontraron productos registrados.");
        }
    }

    private Map<ECategory, List<ProductServiceDto>> filterListByCategory(List<ProductServiceEntity> allProductsServices) {
        List<ProductServiceDto> products = new ArrayList<>();
        List<ProductServiceDto> services = new ArrayList<>();
        allProductsServices.forEach(productServiceEntity -> {
            if (productServiceEntity.getSubCategory().getCategory().getId() == ECategory.PRODUCT.getCategory()) {
                products.add(ProductServiceMapper.productServiceEntityMapperInProductServiceDto(productServiceEntity));
            } else {
                services.add(ProductServiceMapper.productServiceEntityMapperInProductServiceDto(productServiceEntity));
            }
        });
        Map<ECategory, List<ProductServiceDto>> filter = new EnumMap<>(ECategory.class);
        filter.put(ECategory.PRODUCT, products);
        filter.put(ECategory.SERVICE, services);
        return filter;
    }

    private ProductServiceWrapperDto createWrapper(ECategory eCategory, List<ProductServiceDto> list) {
        ProductServiceWrapperDto productServiceWrapperDto = new ProductServiceWrapperDto();
        productServiceWrapperDto.setCategoryId(eCategory.getCategory());
        productServiceWrapperDto.setCategoryDescription(eCategory.name());
        productServiceWrapperDto.setProductsServices(list);
        return productServiceWrapperDto;
    }

    @Autowired
    public void setProductServiceRepository(IProductServiceRepository productServiceRepository) {
        this.productServiceRepository = productServiceRepository;
    }

    @Autowired
    public void setSubCategoryRepository(ISubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }
}