package com.my.spring.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.my.spring.exception.CategoryException;
import com.my.spring.pojo.Category;
import com.my.spring.pojo.Product;

public class CategoryDAO extends DAO {

    public Category get(String title) throws CategoryException {
        try {
            begin();
            Query q=getSession().createQuery("from Category where title= :title");
            q.setString("title",title);
            Category category=(Category)q.uniqueResult();
            commit();
            return category;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not obtain the named category " + title + " " + e.getMessage());
        }
    }
    
    public Category get(long catId) throws CategoryException {
    	try {
            begin();
            Query q = getSession().createQuery("from Category where categoryID= :id");
            q.setLong("id", catId);	
            Category c= (Category)q.uniqueResult();
            commit();
            return c;
    	} catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not obtain the named category " +" " + e.getMessage());
        }
    }

    public List<Category> list() throws CategoryException {
        try {
            begin();
            Query q = getSession().createQuery("from Category");
            List<Category> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not list the categories", e);
        }
    }
    
    public List<Product> productList(long catId) throws CategoryException {
        try {
            begin();
            Query q = getSession().createQuery("select products from Category categoryID= :catId");
            q.setLong("catId",catId);
            List<Product> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not list the product in the category", e);
        }
    }

    public Category create(String title) throws CategoryException {
        try {
            begin();
            Category cat = new Category(title);
            getSession().save(cat);
            commit();
            return cat;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create the category", e);
            throw new CategoryException("Exception while creating category: " + e.getMessage());
        }
    }

    public void update(Category category) throws CategoryException {
        try {
            begin();
            getSession().update(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not save the category", e);
        }
    }

    public void delete(Category category) throws CategoryException {
        try {
            begin();
            getSession().delete(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not delete the category", e);
        }
    }
}