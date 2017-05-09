package com.my.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.my.spring.exception.ProductException;
import com.my.spring.pojo.Product;

public class ProductDAO extends DAO {

	public ProductDAO (){}
	
    public Product create(Product product)
            throws ProductException {
        try {
            begin();            
            getSession().save(product);     
            commit();
            return product;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create product", e);
            throw new ProductException("Exception while creating product: " + e.getMessage());
        }
    }

    public void setOffline(long pId)
            throws ProductException {
        try {
        	Product p=get(pId);
            begin();
            getSession();
            p.setStatus("Offline");
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new ProductException("Could not delete product", e);
        }
    }
    
    public void setOnline(long pId)
            throws ProductException {
        try {
        	Product p=get(pId);
            begin();
            getSession();
            p.setStatus("Active");
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new ProductException("Could not delete product", e);
        }
    }
    
    public Product get(long id) throws ProductException{

	try {
        begin();
        Query q = getSession().createQuery("from Product where productID= :productId ");
        q.setLong("productId", id);	
        Product product = (Product)q.uniqueResult();
        commit();
        return product;
	} catch (HibernateException e) {
        rollback();
        throw new ProductException("Could not get product", e);
    }
	
    }
    
    public List<Product> list() throws ProductException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Product where status= :status");
            q.setString("status", "Active");
            List<Product> products = q.list();
            commit();
            return products;
        } catch (HibernateException e) {
            rollback();
            throw new ProductException("Could not return products list", e);
        }
    	
    }
    
public List<Product> list(long userId) throws ProductException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Product where retailer_personID= :personID ");
            q.setLong("personID", userId);	
            List<Product> products = q.list();
            commit();
            return products;
        } catch (HibernateException e) {
            rollback();
            throw new ProductException("Could not return productlist ", e);
        }
    	
    }

public List<Product> list(String search_query) throws ProductException{
	
	try {
        begin();
        Criteria c = getSession().createCriteria(Product.class);
//        Product p = new Product();
//        p.setDescription(search_query);
//        System.out.println("DESC>>>"+p.getDescription());
//        p.setTitle("%"+search_query+"%");
//        System.out.println(search_query);
//        c.add(Example.create(p));
        c.add(Restrictions.or(Restrictions.ilike("description", "%"+search_query+"%"),Restrictions.ilike("title", "%"+search_query+"%")));
        List<Product> products = c.list();
        commit();
        return products;
    } catch (HibernateException e) {
        rollback();
        throw new ProductException("Could not return productlist ", e);
    }
	
}


}