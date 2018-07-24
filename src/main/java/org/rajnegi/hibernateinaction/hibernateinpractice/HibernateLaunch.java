package org.rajnegi.hibernateinaction.hibernateinpractice;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.rajnegi.hibernateinaction.hibernateinpractice.entities.UserDetails;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public class HibernateLaunch 
{
	public static void main( String[] args )
    {
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	Session session = sessionFactory.openSession();
    	
    	
    	try {
    		
        	Transaction transaction = session.beginTransaction();
        	
        	/*UserDetails exampleUser = new UserDetails();
        	exampleUser.setName("User 1%");
        	
        	Criteria criteria = session.createCriteria(UserDetails.class);
        	criteria.add(Example.create(exampleUser).enableLike());
        	
        	List<UserDetails> resultList = (List<UserDetails>)criteria.list();*/
        	
        	
        	/*CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        	CriteriaQuery<UserDetails> criteriaQuery = criteriaBuilder.createQuery(UserDetails.class);
        	Root<UserDetails> from = criteriaQuery.from(UserDetails.class);
        	criteriaQuery.where(criteriaBuilder.equal(from.get("name"), "User 6"));
        	
        	List<UserDetails> resultList = session.createQuery(criteriaQuery).getResultList();*/
        	
        	/*for(UserDetails user : resultList) {
        		System.out.println(user.getName());
        	}*/
        	
        	/*UserDetails user4 = session.get(UserDetails.class, 4);
        	UserDetails user5 = session.get(UserDetails.class, 5);*/
        	
        	UserDetails user14 = new UserDetails("Rajeev Singh Negi");
        	session.persist(user14);
        	//System.out.println("user11.getUserId()== "+user11.getUserId());
        	
        	Query query = session.createQuery("from UserDetails where userid = 6");
        	query.setCacheable(true);
        	query.list();
        	
        	transaction.commit();
        	session.close();
        	
        	/*Cache cache = CacheManager.ALL_CACHE_MANAGERS.get(0).getCache("org.rajnegi.hibernateinaction.hibernateinpractice.entities.UserDetails");
        	System.out.println("Cache size = "+cache.getSize());
        	assert cache.getSize() != 0 : "ERRORR";*/
        	
        	Session session2 = sessionFactory.openSession();
        	session2.beginTransaction();
        	
        	//UserDetails user2 = session2.get(UserDetails.class, 4);
        	Query query2 = session2.createQuery("from UserDetails where userid = 6");
        	query2.setCacheable(true);
        	query2.list();
        	
        	session2.getTransaction().commit();
        	session2.close();
        	
    	
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}finally {
    		
        	sessionFactory.close();
    	}
    	
    }
}
