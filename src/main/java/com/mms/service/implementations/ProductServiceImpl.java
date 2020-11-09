package com.mms.service.implementations;

import com.mms.model.ProductEntity;
import com.mms.repository.interfaces.ProductRepository;
import com.mms.dto.ProductDTO;
import com.mms.dto.converterDTO.ProductConverter;
import com.mms.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.mms.dto.converterDTO.ProductConverter.toDto;
import static com.mms.dto.converterDTO.ProductConverter.toEntity;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<ProductDTO> getAllExistingProducts(int page) {
        return productRepository.findAllExistingProducts(page).stream()
                .map(ProductConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ProductDTO> getAllProductsInStoreNotNullQuantity(int page) {
        return productRepository.findAllProductsInStoreNotNullQuantity(page).stream()
                .map(ProductConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int getProductCount() {
        return productRepository.getProductCount();
    }

    @Override
    @Transactional
    public int getProductCountNotNullQuantity() {
        return productRepository.getProductCountNotNullQuantity();
    }

    @Override
    @Transactional
    public void addProduct(ProductDTO productDTO) {
        logger.info("adding product " + productDTO.getId());
        productRepository.saveProduct(toEntity(productDTO));
    }

    @Override
    @Transactional
    public void deleteProduct(ProductDTO productDTO) {
        logger.info("deleting product " + productDTO.getId());
        productRepository.deleteProduct(toEntity(productDTO));
    }

    @Override
    @Transactional
    public void editProduct(ProductDTO productDTO) {
        logger.info("editing product " + productDTO.getId());
        productRepository.updateProduct(toEntity(productDTO));
    }

    @Override
    @Transactional
    public ProductDTO getProduct(int id) {
        logger.info("getting product by id " + id);
        return toDto(productRepository.findProductById(id));
    }

    @Override
    @Transactional
    public List<ProductDTO> getAllProductsByCategoryId(int categoryId) {
        return productRepository.findAllProductsByCategoryId(categoryId).stream()
                .map(ProductConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int getMaxProductPriceInStore() {
        return productRepository.getMaxProductPriceInStore();
    }

    @Override
    @Transactional
    public int getMinProductPriceInStore() {
        return productRepository.getMinProductPriceInStore();
    }

    @Override
    @Transactional
    public List<ProductDTO> getAllProductsUsingFilter(int page, String inputProductName, Boolean isInStore, Integer minPrice, Integer maxPrice, String inputNameOfCategory) {
        return productRepository.findAllProductsUsingFilter(page, inputProductName, isInStore, minPrice, maxPrice, inputNameOfCategory).stream()
                .map(ProductConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int getProductCountUsingFilter(String inputProductName, Boolean isInStore, Integer minPrice, Integer maxPrice, String inputNameOfCategory) {
        return productRepository.findAllProductsUsingFilterWithoutPages(inputProductName, isInStore, minPrice, maxPrice, inputNameOfCategory).size();
    }

    @Override
    @Transactional
    public String editProductAndReturnNameStatus(ProductDTO product) {

        try {
            ProductDTO productDTO = ProductConverter.toDto(productRepository.findProductsByName(product.getName()));
            if (product.getId() == productDTO.getId()) {
                logger.info("editing product with id = " + product.getId());
                ProductEntity productToUpdate = productRepository.findProductById(product.getId());
                productToUpdate.setName(product.getName());
                productToUpdate.setAlternative_name(product.getAlternative_name());
                productToUpdate.setBrandName(product.getBrandName());
                productToUpdate.setPrice(product.getPrice());
                productToUpdate.setCategory(product.getCategory());
                productToUpdate.setQuantityInStore(product.getQuantityInStore());
                productToUpdate.setColor(product.getColor());
                productToUpdate.setWeight(product.getWeight());
                productToUpdate.setCountry(product.getCountry());
                productToUpdate.setDescription(product.getDescription());
                productRepository.updateProduct(productToUpdate);

//                ProductDTO productDTO1 = ProductConverter.toDto(productRepository.findProductById(product.getId()));
//                productDTO1.setName(product.getName());
//                productDTO1.setAlternative_name(product.getAlternative_name());
//                productDTO1.setBrandName(product.getBrandName());
//                productDTO1.setPrice(product.getPrice());
//                productDTO1.setCategory(product.getCategory());
//                productDTO1.setQuantityInStore(product.getQuantityInStore());
//                productDTO1.setColor(product.getColor());
//                productDTO1.setWeight(product.getWeight());
//                productDTO1.setCountry(product.getCountry());
//                productDTO1.setDescription(product.getDescription());
//                productRepository.updateProduct(ProductConverter.toEntity(productDTO1));
            } else {
                logger.info("fail editing product " + product.getId());
                return "redirect:/catalog/editProduct/" + product.getId() + "/?uniqName=0";
            }
        } catch (NoResultException exc) {
            logger.info("editing product with id = " + product.getId());
            productRepository.updateProduct(ProductConverter.toEntity(product));
        }

        return "okStatus";
    }

    @Override
    @Transactional
    public String addProductAndReturnUnicNameStatus(ProductDTO product) {

        try {
            productRepository.findProductsByName(product.getName());
            logger.info("fail adding product " + product.getName() + " " + product.getId());
            return "redirect:/catalog/addProduct/?uniqName=0";
        } catch (NoResultException exc) {
            logger.info("adding product with id = " + product.getId());
            productRepository.saveProduct(ProductConverter.toEntity(product));
        }

        return "okStatus";
    }
}
