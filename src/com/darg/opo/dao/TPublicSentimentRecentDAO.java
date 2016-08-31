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

import com.darg.opo.commutil.CommonUtil;
import com.darg.opo.pojo.TPublicSentimentRecent;

/**
 	* A data access object (DAO) providing persistence and search support for TPublicSentimentRecent entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.darg.opo.pojo.TPublicSentimentRecent
  * @author MyEclipse Persistence Tools 
 */
public class TPublicSentimentRecentDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TPublicSentimentRecentDAO.class);
	//property constants
	public static final String KEY_WORD = "keyWord";

	protected void initDao() {
		//do nothing
	}

	public void save(TPublicSentimentRecent transientInstance) {
		log.debug("saving TPublicSentimentRecent instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TPublicSentimentRecent persistentInstance) {
		log.debug("deleting TPublicSentimentRecent instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TPublicSentimentRecent findById(java.lang.Integer id) {
		log.debug("getting TPublicSentimentRecent instance with id: " + id);
		try {
			TPublicSentimentRecent instance = (TPublicSentimentRecent) getHibernateTemplate().get("com.darg.opo.pojo.TPublicSentimentRecent", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TPublicSentimentRecent instance) {
		log.debug("finding TPublicSentimentRecent instance by example");
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
		log.debug("finding TPublicSentimentRecent instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TPublicSentimentRecent as model where model." + propertyName + "= ?";
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
		log.debug("finding all TPublicSentimentRecent instances");
		try {
			String queryString = "from TPublicSentimentRecent";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TPublicSentimentRecent merge(TPublicSentimentRecent detachedInstance) {
		log.debug("merging TPublicSentimentRecent instance");
		try {
			TPublicSentimentRecent result = (TPublicSentimentRecent) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TPublicSentimentRecent instance) {
		log.debug("attaching dirty TPublicSentimentRecent instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TPublicSentimentRecent instance) {
		log.debug("attaching clean TPublicSentimentRecent instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TPublicSentimentRecentDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TPublicSentimentRecentDAO) ctx.getBean("TPublicSentimentRecentDAO");
	}
	
	/**
	 * 查找全部最新关键字
	 * 
	 * @return
	 * @throws Exception
	 */
	public List findNewkeywords(String Newkeyword)throws Exception {

		log.info("TPublicSentimentRecentDAO .findNewkeywords()方法  Start");
				
		String queryString="SELECT rc.cIndex, rc.keyWord, rc.cTime FROM T_PublicSentimentRecent AS rc WHERE rc.keyWord LIKE '%"+Newkeyword+"%' order by rc.ctime desc";
		
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = (SQLQuery) session.createSQLQuery(queryString)
				.setResultTransformer(
						Transformers.aliasToBean(TPublicSentimentRecent.class));
		query.addScalar("cindex", StandardBasicTypes.INTEGER);
		query.addScalar("keyWord", StandardBasicTypes.STRING);
		query.addScalar("ctime", StandardBasicTypes.TIMESTAMP);


		List list = query.list();
		t.commit();
		session.close();

		log.info("TPublicSentimentRecentDAO .findNewkeywords()方法  end");
		return list;
	}
	
	/**
	 * 
	 * 根据关键词Id查询单个关键词
	 * 
	 * @param 
	 * @param messageId
	 * @return
	 * @throws Exception
	 */
	public List findSinglekeywords(Integer cindex)
			throws Exception {

		log.info("TPublicSentimentRecentDAO .findSinglekeywords()方法  Start");
		// User user = new User();
		TPublicSentimentRecent tPublicSentimentRecent = new TPublicSentimentRecent();
		String queryString = "SELECT rc.cIndex, rc.keyWord, rc.cTime FROM T_PublicSentimentRecent AS rc WHERE rc.cIndex = '"+cindex+"'";
		

		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = (SQLQuery) session.createSQLQuery(queryString)
				.setResultTransformer(
						Transformers.aliasToBean(TPublicSentimentRecent.class));
		query.addScalar("cindex", StandardBasicTypes.INTEGER);
		query.addScalar("keyWord", StandardBasicTypes.STRING);
		query.addScalar("ctime", StandardBasicTypes.TIMESTAMP);


		List list = query.list();
		t.commit();
		session.close();

		log.info("TPublicSentimentRecentDAO .findSinglekeywords()方法  end");
		return list;
	}
	

	
	
}