package com.mms.repository.implementations;

import com.mms.model.ProductEntity;
import com.mms.repository.interfaces.ProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProductEntity> findAllExistingProducts(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ProductEntity").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProductEntity> findAllProductsInStoreNotNullQuantity(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ProductEntity WHERE quantityInStore!=0").setFirstResult(10 * (page - 1))
                .setMaxResults(10)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProductEntity> findAllProductsUsingFilter(int page, String inputProductName, Boolean isInStore, Integer minPrice, Integer maxPrice, String inputNameOfCategory) {
        Session session = sessionFactory.getCurrentSession();

        Integer inputQuantity;
        if (isInStore == null) inputQuantity = 0;
        else if (isInStore) inputQuantity = 0;
        else inputQuantity = null;

        if(inputProductName != null) inputProductName = "%" + inputProductName + "%";

        return session.createQuery("select products from ProductEntity products where" +
                "(:inputProductName is null or products.name like :inputProductName)" +
                "and(trim(:inputQuantity) is null or products.quantityInStore != :inputQuantity)" +
                "and(trim(:minPrice) is null or products.price > :minPrice)" +
                "and(trim(:maxPrice) is null or products.price < :maxPrice)" +
                "and(trim(:inputNameOfCategory) is null or products.category.nameOfCategory = :inputNameOfCategory)")
                .setParameter("inputProductName", inputProductName)
                .setParameter("inputQuantity", inputQuantity)
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .setParameter("inputNameOfCategory", inputNameOfCategory)
                .setFirstResult(10 * (page - 1))
                .setMaxResults(10)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProductEntity> findAllProductsUsingFilterWithoutPages(String inputProductName, Boolean isInStore, Integer minPrice, Integer maxPrice, String inputNameOfCategory) {
        Session session = sessionFactory.getCurrentSession();

        String inputQuantity;
        if (isInStore == null) inputQuantity = "NotNull";
        else if (isInStore) inputQuantity = "NotNull";
        else inputQuantity = null;

        if(inputProductName != null) inputProductName = "%" + inputProductName + "%";

        return session.createQuery("select products from ProductEntity products where" +
                "(:inputProductName is null or products.name like :inputProductName)" +
                "and(trim(:inputQuantity) is null or products.quantityInStore != 0)" +
                "and(trim(:minPrice) is null or products.price >= :minPrice)" +
                "and(trim(:maxPrice) is null or products.price <= :maxPrice)" +
                "and(:inputNameOfCategory is null or products.category.nameOfCategory = :inputNameOfCategory)")
                .setParameter("inputProductName", inputProductName)
                .setParameter("inputQuantity", inputQuantity)
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .setParameter("inputNameOfCategory", inputNameOfCategory)
                .list();
    }


    @Override
    public void saveProduct(ProductEntity productEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(productEntity);
    }

    @Override
    public void deleteProduct(ProductEntity productEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(productEntity);
    }

    @Override
    public void updateProduct(ProductEntity productEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(productEntity);
    }

    @Override
    public ProductEntity findProductById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(ProductEntity.class, id);
    }

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public ProductEntity findProductByIdTransactional(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(ProductEntity.class, id);
    }

//    @Override
////    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    @Lock(LockModeType.OPTIMISTIC)
//    public int getProductQuantityByProductId(int inputId) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("select prod.quantityInStore from ProductEntity prod where prod.id = :inputId", Number.class)
//                .setParameter("inputId", inputId).getSingleResult().intValue();
//    }

    @Override
    public int getProductCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count (*) from ProductEntity", Number.class).getSingleResult().intValue();
    }

    @Override
    public int getProductCountNotNullQuantity() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count (*) from ProductEntity WHERE quantityInStore!=0", Number.class)
                .getSingleResult()
                .intValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProductEntity> findAllProductsByCategoryId(int categoryId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ProductEntity productEn WHERE productEn.category.id =: categoryId")
                .setParameter("categoryId", categoryId)
                .list();
    }

    @Override
    public int getMinProductPriceInStore() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select MIN(price) from ProductEntity", Number.class).getSingleResult().intValue();
    }

    @Override
    public int getMaxProductPriceInStore() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select MAX(price) from ProductEntity", Number.class).getSingleResult().intValue();
    }

    @Override
    public ProductEntity findProductsByName(String productName) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ProductEntity productEn WHERE productEn.name =: productName", ProductEntity.class)
                .setParameter("productName", productName)
                .getSingleResult();
    }
}
