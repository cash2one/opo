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
import com.darg.opo.pojo.TPublicSentimentResult;

/**
 	* A data access object (DAO) providing persistence and search support for TPublicSentimentResult entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.darg.opo.pojo.TPublicSentimentResult
  * @author MyEclipse Persistence Tools 
 */
public class TPublicSentimentResultDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TPublicSentimentResultDAO.class);
	//property constants
	public static final String KEY_WORD = "keyWord";
	public static final String TODAY_COUNT = "todayCount";
	public static final String FLAG = "flag";

	protected void initDao() {
		//do nothing
	}

	public void save(TPublicSentimentResult transientInstance) {
		log.debug("saving TPublicSentimentResult instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TPublicSentimentResult persistentInstance) {
		log.debug("deleting TPublicSentimentResult instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TPublicSentimentResult findById(java.lang.Integer id) {
		log.debug("getting TPublicSentimentResult instance with id: " + id);
		try {
			TPublicSentimentResult instance = (TPublicSentimentResult) getHibernateTemplate().get("com.darg.opo.pojo.TPublicSentimentResult", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TPublicSentimentResult instance) {
		log.debug("finding TPublicSentimentResult instance by example");
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
		log.debug("finding TPublicSentimentResult instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TPublicSentimentResult as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKeyWord(Object keyWord) {
		return findByProperty(KEY_WORD, keyWord);
	}

	public List findByTodayCount(Object todayCount) {
		return findByProperty(TODAY_COUNT, todayCount);
	}

	public List findByFlag(Object flag) {
		return findByProperty(FLAG, flag);
	}

	public List findAll() {
		log.debug("finding all TPublicSentimentResult instances");
		try {
			String queryString = "from TPublicSentimentResult";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/**
	 * 查询今日日预警模糊查询通过关键词Like（时间范围）
	 * @author rz
	 * @param keyWord
	 * @return
	 */
	public List findByPropertyKeyWordLike(String keyWord) {
		log.debug("findByPropertyKeyWordLike");
		try {
			Timestamp endCreatetime = CommonUtil.getNowTime_tamp();
			Timestamp startCreatetime = CommonUtil.getBeforeYesterdayTime_tamp();
			String queryString = "SELECT t from TPublicSentimentResult as t where t.keyWord LIKE '%" + keyWord + "%' and t.ctime >= '" + startCreatetime + " ' and t.ctime <= '" + endCreatetime + "'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("findByPropertyKeyWordLike", re);
			throw re;
		}
	}
	/**
	 * 查找当日预警
	 * @author zf
	 * @return
	 * @throws Exception
	 */
	public List findAlarmInfo(String g_size)throws Exception {

		log.info("TPublicSentimentResultDAO .findAlarmInfo()方法  Start");
				
		//String queryString="SELECT rc.cIndex, rc.keyWord, rc.cTime FROM T_PublicSentimentRecent AS rc WHERE rc.keyWord LIKE '%"+Newkeyword+"%'";
		String queryString="SELECT rc.cIndex, rc.keyWord, rc.cTime,rc.todayCount FROM T_PublicSentimentResult AS rc WHERE rc.cTime < NOW() AND rc.cTime > CAST(CURDATE() AS DATETIME) AND rc.flag=1 LIMIT 0,"+g_size+"";
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = (SQLQuery) session.createSQLQuery(queryString)
				.setResultTransformer(
						Transformers.aliasToBean(TPublicSentimentResult.class));
		query.addScalar("cindex", StandardBasicTypes.INTEGER);
		query.addScalar("keyWord", StandardBasicTypes.STRING);
		query.addScalar("ctime", StandardBasicTypes.TIMESTAMP);
		query.addScalar("todayCount", StandardBasicTypes.INTEGER);


		List list = query.list();
		t.commit();
		session.close();

		log.info("TPublicSentimentResultDAO .findAlarmInfo()方法  end");
		return list;
	}
	/**
	 * 查看查找当日预警
	 * @author zf
	 * @return
	 * @throws Exception
	 */
	public List findAlarmLookInfo()throws Exception {

		log.info("TPublicSentimentResultDAO .findAlarmInfo()方法  Start");
				
		//String queryString="SELECT rc.cIndex, rc.keyWord, rc.cTime FROM T_PublicSentimentRecent AS rc WHERE rc.keyWord LIKE '%"+Newkeyword+"%'";
		String queryString="SELECT rc.cIndex, rc.keyWord, rc.cTime,rc.todayCount FROM T_PublicSentimentResult AS rc WHERE rc.cTime < NOW() AND rc.cTime > CAST(CURDATE() AS DATETIME)";
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = (SQLQuery) session.createSQLQuery(queryString)
				.setResultTransformer(
						Transformers.aliasToBean(TPublicSentimentResult.class));
		query.addScalar("cindex", StandardBasicTypes.INTEGER);
		query.addScalar("keyWord", StandardBasicTypes.STRING);
		query.addScalar("ctime", StandardBasicTypes.TIMESTAMP);
		query.addScalar("todayCount", StandardBasicTypes.INTEGER);


		List list = query.list();
		t.commit();
		session.close();

		log.info("TPublicSentimentResultDAO .findAlarmInfo()方法  end");
		return list;
	}
	public TPublicSentimentResult merge(TPublicSentimentResult detachedInstance) {
		log.debug("merging TPublicSentimentResult instance");
		try {
			TPublicSentimentResult result = (TPublicSentimentResult) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TPublicSentimentResult instance) {
		log.debug("attaching dirty TPublicSentimentResult instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TPublicSentimentResult instance) {
		log.debug("attaching clean TPublicSentimentResult instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TPublicSentimentResultDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TPublicSentimentResultDAO) ctx.getBean("TPublicSentimentResultDAO");
	}
}