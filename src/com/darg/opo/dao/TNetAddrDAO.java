package com.darg.opo.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.darg.opo.pojo.TNetAddr;
import com.darg.opo.pojo.TPublicSentimentRecent;

/**
 	* A data access object (DAO) providing persistence and search support for TNetAddr entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.darg.opo.pojo.TNetAddr
  * @author MyEclipse Persistence Tools 
 */
public class TNetAddrDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TNetAddrDAO.class);
	//property constants
	public static final String URL = "url";

	protected void initDao() {
		//do nothing
	}

	public void save(TNetAddr transientInstance) {
		log.debug("saving TNetAddr instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TNetAddr persistentInstance) {
		log.debug("deleting TNetAddr instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TNetAddr findById(java.lang.Integer id) {
		log.debug("getting TNetAddr instance with id: " + id);
		try {
			TNetAddr instance = (TNetAddr) getHibernateTemplate().get("com.darg.opo.pojo.TNetAddr", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TNetAddr instance) {
		log.debug("finding TNetAddr instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TNetAddr instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TNetAddr as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findAll() {
		log.debug("finding all TNetAddr instances");
		try {
			String queryString = "from TNetAddr";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TNetAddr merge(TNetAddr detachedInstance) {
		log.debug("merging TNetAddr instance");
		try {
			TNetAddr result = (TNetAddr) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TNetAddr instance) {
		log.debug("attaching dirty TNetAddr instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TNetAddr instance) {
		log.debug("attaching clean TNetAddr instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TNetAddrDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TNetAddrDAO) ctx.getBean("TNetAddrDAO");
	}
	
	/**
	 * 查找全部定网网址
	 * 
	 * @return
	 * @throws Exception
	 */
	public List findNetAddrListFy(String g_serchValue)throws Exception {

		log.info("NetAddrDAO .findNetAddr()方法  Start");
		
		TNetAddr tNetAddr = new TNetAddr();
		String queryString="SELECT nd.cIndex, nd.cTime,nd.url FROM T_NetAddr as nd where nd.url Like '%"+g_serchValue+"%'";		
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = (SQLQuery) session.createSQLQuery(queryString).setResultTransformer(Transformers.aliasToBean(TNetAddr.class));
		query.addScalar("cindex", StandardBasicTypes.INTEGER);
		query.addScalar("url", StandardBasicTypes.STRING);
		query.addScalar("ctime", StandardBasicTypes.TIMESTAMP);
		List list = query.list();
		t.commit();
		session.close();

		log.info("NetAddrDAO .findNetAddr()方法    end");
		return list;
	}
	
}