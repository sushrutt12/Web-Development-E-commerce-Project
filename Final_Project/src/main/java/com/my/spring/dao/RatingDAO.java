package com.my.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.my.spring.exception.ProductException;
import com.my.spring.exception.RatingException;
import com.my.spring.pojo.Product;
import com.my.spring.pojo.Rating;

public class RatingDAO extends DAO {

	public Rating createRating(Rating r) throws RatingException {
		try {
			begin();
			Query q = getSession().createQuery("from Rating where productID= :productID AND postedBy= :postedBy");
			q.setLong("productID", r.getProduct().getId());
			q.setString("postedBy", r.getPostedBy());
			Rating rating = (Rating) q.uniqueResult();
			if (rating == null)
				getSession().save(r);
			else {
				rating.setDescription(r.getDescription());
				rating.setScore(r.getScore());
				rating.setTitle(r.getTitle());
				getSession().save(rating);
							}
			commit();
			return r;
		} catch (HibernateException e) {
			rollback();
			//
			throw new RatingException("Exception while creating rating: " + e.getMessage());
		}

	}

	public List getList(long productID) throws RatingException {

		try {
			begin();
			Query q = getSession().createQuery("from Rating where productID= :productID");
			q.setLong("productID", productID);
			List<Rating> ratings = q.list();
			commit();
			return ratings;
		} catch (HibernateException e) {
			rollback();
			throw new RatingException("Could not return productlist ", e);
		}
	}

	// public void deleteRating(Rating r) throws RatingException{
	//
	// try {
	// begin();
	// Query q = getSession().createQuery("from Rating where productID=
	// :productID");
	// q.setLong("productID", productID);
	// List<Rating> ratings = q.list();
	// commit();
	// return ratings;
	// } catch (HibernateException e) {
	// rollback();
	// throw new RatingException("Could not return productlist ", e);
	// }
	//
	// }
}
