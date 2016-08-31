package com.darg.opo.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.darg.opo.pojo.TPublicSentimentHead;

/**
 	* A data access object (DAO) providing persistence and search support for TPublicSentimentHead entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.darg.opo.pojo.TPublicSentimentHead
  * @author MyEclipse Persistence Tools 
 */
public class TPublicSentimentHeadDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TPublicSentimentHeadDAO.class);
	//property constants
	public static final String HEAD = "head";

	protected void initDao() {
		//do nothing
	}

	public void save(TPublicSentimentHead transientInstance) {
		log.debug("saving TPublicSentimentHead instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TPublicSentimentHead persistentInstance) {
		log.debug("deleting TPublicSentimentHead instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TPublicSentimentHead findById(java.lang.Integer id) {
		log.debug("getting TPublicSentimentHead instance with id: " + id);
		try {
			TPublicSentimentHead instance = (TPublicSentimentHead) getHibernateTemplate().get("com.darg.opo.pojo.TPublicSentimentHead", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TPublicSentimentHead instance) {
		log.debug("finding TPublicSentimentHead instance by example");
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
		log.debug("finding TPublicSentimentHead instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TPublicSentimentHead as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByHead(Object head) {
		return findByProperty(HEAD, head);
	}

	public List findAll() {
		log.debug("finding all TPublicSentimentHead instances");
		try {
			String queryString = "from TPublicSentimentHead";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List serchUnionKeywordListFy(String g_serchValue) {
		log.debug("serchUnionKeywordListFy");
		try {
			String queryString = "select model from TPublicSentimentHead as model where model.head LIKE '%"+g_serchValue+"%' order by model.ctime DESC";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("serchUnionKeywordListFy failed", re);
			throw re;
		}
	}

	public TPublicSentimentHead merge(TPublicSentimentHead detachedInstance) {
		log.debug("merging TPublicSentimentHead instance");
		try {
			TPublicSentimentHead result = (TPublicSentimentHead) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TPublicSentimentHead instance) {
		log.debug("attaching dirty TPublicSentimentHead instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TPublicSentimentHead instance) {
		log.debug("attaching clean TPublicSentimentHead instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TPublicSentimentHeadDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TPublicSentimentHeadDAO) ctx.getBean("TPublicSentimentHeadDAO");
	}
}