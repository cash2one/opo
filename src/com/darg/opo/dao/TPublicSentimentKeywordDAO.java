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

import com.darg.opo.pojo.TPublicSentimentKeyword;
import com.darg.opo.pojo.TPublicSentimentRecent;

/**
 	* A data access object (DAO) providing persistence and search support for TPublicSentimentKeyword entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.darg.opo.pojo.TPublicSentimentKeyword
  * @author MyEclipse Persistence Tools 
 */
public class TPublicSentimentKeywordDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TPublicSentimentKeywordDAO.class);
	//property constants
	public static final String KEY_WORD = "keyWord";

	protected void initDao() {
		//do nothing
	}

	public void save(TPublicSentimentKeyword transientInstance) {
		log.debug("saving TPublicSentimentKeyword instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TPublicSentimentKeyword persistentInstance) {
		log.debug("deleting TPublicSentimentKeyword instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TPublicSentimentKeyword findById(java.lang.Integer id) {
		log.debug("getting TPublicSentimentKeyword instance with id: " + id);
		try {
			TPublicSentimentKeyword instance = (TPublicSentimentKeyword) getHibernateTemplate().get("com.darg.opo.pojo.TPublicSentimentKeyword", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TPublicSentimentKeyword instance) {
		log.debug("finding TPublicSentimentKeyword instance by example");
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
		log.debug("finding TPublicSentimentKeyword instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TPublicSentimentKeyword as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKeyWord(Object keyWord) {
		return findByProperty(KEY_WORD, keyWord);
	}

	public List findAll() {
		log.debug("finding all TPublicSentimentKeyword instances");
		try {
			String queryString = "from TPublicSentimentKeyword";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TPublicSentimentKeyword merge(TPublicSentimentKeyword detachedInstance) {
		log.debug("merging TPublicSentimentKeyword instance");
		try {
			TPublicSentimentKeyword result = (TPublicSentimentKeyword) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TPublicSentimentKeyword instance) {
		log.debug("attaching dirty TPublicSentimentKeyword instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TPublicSentimentKeyword instance) {
		log.debug("attaching clean TPublicSentimentKeyword instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TPublicSentimentKeywordDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TPublicSentimentKeywordDAO) ctx.getBean("TPublicSentimentKeywordDAO");
	}
	
	/**
	 * 模糊查询敏感词
	 * 
	 * @return
	 * @throws Exception
	 */
	public List findSensitiveKeywords(String keyWord)throws Exception {

		log.info("TPublicSentimentKeywordsDAO .findSensitiveKeywords()方法  Start");
		
		TPublicSentimentKeyword keyword = new TPublicSentimentKeyword();
		String queryString="SELECT keyword.cIndex, keyword.keyWord, keyword.cTime FROM T_PublicSentimentKeyword AS keyword WHERE keyword.keyWord LIKE '%"+keyWord+"%'";
		
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = (SQLQuery) session.createSQLQuery(queryString)
				.setResultTransformer(
						Transformers.aliasToBean(TPublicSentimentKeyword.class));
		query.addScalar("cindex", StandardBasicTypes.INTEGER);
		query.addScalar("keyWord", StandardBasicTypes.STRING);
		query.addScalar("ctime", StandardBasicTypes.TIMESTAMP);


		List list = query.list();
		t.commit();
		session.close();

		log.info("TPublicSentimentKeywordsDAO .findSensitiveKeywords()方法  end");
		return list;
	}
	
	
	
	
	
	
	
}