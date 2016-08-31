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
import com.darg.opo.pojo.TPublicSentimentHresult;
import com.darg.opo.pojo.TPublicSentimentKeyword;
import com.darg.opo.pojo.TPublicSentimentRresult;

/**
 	* A data access object (DAO) providing persistence and search support for TPublicSentimentHresult entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.darg.opo.pojo.TPublicSentimentHresult
  * @author MyEclipse Persistence Tools 
 */
public class TPublicSentimentHresultDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TPublicSentimentHresultDAO.class);
	//property constants
	public static final String KEY_WORD = "keyWord";
	public static final String FLAG = "flag";

	protected void initDao() {
		//do nothing
	}

	public void save(TPublicSentimentHresult transientInstance) {
		log.debug("saving TPublicSentimentHresult instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TPublicSentimentHresult persistentInstance) {
		log.debug("deleting TPublicSentimentHresult instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TPublicSentimentHresult findById(java.lang.Integer id) {
		log.debug("getting TPublicSentimentHresult instance with id: " + id);
		try {
			TPublicSentimentHresult instance = (TPublicSentimentHresult) getHibernateTemplate().get("com.darg.opo.pojo.TPublicSentimentHresult", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TPublicSentimentHresult instance) {
		log.debug("finding TPublicSentimentHresult instance by example");
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
		log.debug("finding TPublicSentimentHresult instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TPublicSentimentHresult as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	/**
	 * 查询往日预警模糊查询通过关键词Like（时间范围）
	 * @param keyWord
	 * @return
	 */
	public List findByPropertyKeyWordLike(String keyWord) {
		log.debug("findByPropertyKeyWordLike");
		try {
			Timestamp endCreatetime = CommonUtil.getNowTime_tamp();
			Timestamp startCreatetime = CommonUtil.getBeforeYesterdayTime_tamp();
			String queryString = "SELECT t from TPublicSentimentHresult as t where t.keyWord LIKE '%" + keyWord + "%' and t.ctime >= '" + startCreatetime + " ' and t.ctime <= '" + endCreatetime + "'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("findByPropertyKeyWordLike", re);
			throw re;
		}
	}
	  

	public List findByKeyWord(Object keyWord) {
		return findByProperty(KEY_WORD, keyWord);
	}

	public List findByFlag(Object flag) {
		return findByProperty(FLAG, flag);
	}

	public List findAll() {
		log.debug("finding all TPublicSentimentHresult instances");
		try {
			String queryString = "from TPublicSentimentHresult";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TPublicSentimentHresult merge(TPublicSentimentHresult detachedInstance) {
		log.debug("merging TPublicSentimentHresult instance");
		try {
			TPublicSentimentHresult result = (TPublicSentimentHresult) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TPublicSentimentHresult instance) {
		log.debug("attaching dirty TPublicSentimentHresult instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TPublicSentimentHresult instance) {
		log.debug("attaching clean TPublicSentimentHresult instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TPublicSentimentHresultDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TPublicSentimentHresultDAO) ctx.getBean("TPublicSentimentHresultDAO");
	}

/**
 * 查询时间范围内的往日预警
 * 
 * @return
 * @throws Exception
 */
public List searchHResult(String dateTime01, String dateTime02)throws Exception {

	log.info("TPublicSentimentHresultDAO .searchHResult()方法  Start");
	
	//TPublicSentimentKeyword keyword = new TPublicSentimentKeyword();
	String queryString="SELECT DISTINCT t.keyWord,t.cindex,t.ctime,t.flag FROM T_PublicSentimentHResult AS t WHERE t.cTime >='"+dateTime01+"' AND t.cTime <='"+dateTime02 +"'  GROUP BY t.keyWord order by t.ctime desc";
	
	Session session = getHibernateTemplate().getSessionFactory()
			.openSession();
	Transaction t = session.beginTransaction();
	SQLQuery query = (SQLQuery) session.createSQLQuery(queryString)
			.setResultTransformer(
					Transformers.aliasToBean(TPublicSentimentRresult.class));
	query.addScalar("cindex", StandardBasicTypes.INTEGER);
	query.addScalar("keyWord", StandardBasicTypes.STRING);
	query.addScalar("ctime", StandardBasicTypes.TIMESTAMP);
	query.addScalar("flag", StandardBasicTypes.STRING);


	List list = query.list();
	t.commit();
	session.close();

	log.info("TPublicSentimentHresultDAO .searchHResult()方法  end");
	return list;
}



}