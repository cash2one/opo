package com.darg.opo.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.darg.opo.pojo.TMolestKeyFormat;

/**
 	* A data access object (DAO) providing persistence and search support for TMolestKeyFormat entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.darg.opo.pojo.TMolestKeyFormat
  * @author MyEclipse Persistence Tools 
 */
public class TMolestKeyFormatDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TMolestKeyFormatDAO.class);
	//property constants
	public static final String PRE = "pre";
	public static final String NEXT = "next";

	protected void initDao() {
		//do nothing
	}

	public void save(TMolestKeyFormat transientInstance) {
		log.debug("saving TMolestKeyFormat instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TMolestKeyFormat persistentInstance) {
		log.debug("deleting TMolestKeyFormat instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TMolestKeyFormat findById(java.lang.Integer id) {
		log.debug("getting TMolestKeyFormat instance with id: " + id);
		try {
			TMolestKeyFormat instance = (TMolestKeyFormat) getHibernateTemplate().get("com.darg.opo.pojo.TMolestKeyFormat", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TMolestKeyFormat instance) {
		log.debug("finding TMolestKeyFormat instance by example");
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
		log.debug("finding TMolestKeyFormat instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TMolestKeyFormat as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPre(Object pre) {
		return findByProperty(PRE, pre);
	}

	public List findByNext(Object next) {
		return findByProperty(NEXT, next);
	}

	public List findAll() {
		log.debug("finding all TMolestKeyFormat instances");
		try {
			String queryString = "from TMolestKeyFormat";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TMolestKeyFormat merge(TMolestKeyFormat detachedInstance) {
		log.debug("merging TMolestKeyFormat instance");
		try {
			TMolestKeyFormat result = (TMolestKeyFormat) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TMolestKeyFormat instance) {
		log.debug("attaching dirty TMolestKeyFormat instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TMolestKeyFormat instance) {
		log.debug("attaching clean TMolestKeyFormat instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TMolestKeyFormatDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TMolestKeyFormatDAO) ctx.getBean("TMolestKeyFormatDAO");
	}
}