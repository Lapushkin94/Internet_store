package com.mms.repository.implementations;

import com.mms.model.CategoryEntity;
import com.mms.repository.interfaces.CategoryRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CategoryEntity> findAllCategories(int page) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from CategoryEntity").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CategoryEntity> findAllCategoriesWithoutPages() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from CategoryEntity").list();
    }

    @Override
    public void saveCategory(CategoryEntity categoryEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(categoryEntity);
    }

    @Override
    public void deleteCategory(CategoryEntity categoryEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(categoryEntity);
    }

    @Override
    public void updateCategory(CategoryEntity categoryEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(categoryEntity);
    }

    @Override
    public CategoryEntity findCategoryById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(CategoryEntity.class, id);
    }

    @Override
    public int getCategoryCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count (*) from CategoryEntity", Number.class).getSingleResult().intValue();
    }

    @Override
    public CategoryEntity findCategoryByName(String categoryName) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from CategoryEntity categoryEnt WHERE categoryEnt.nameOfCategory =: categoryName", CategoryEntity.class)
                .setParameter("categoryName", categoryName)
                .getSingleResult();
    }
}
