package com.darg.opo.dao;

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

import com.darg.opo.pojo.TNetResult;

/**
 	* A data access object (DAO) providing persistence and search support for TNetResult entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.darg.opo.pojo.TNetResult
  * @author MyEclipse Persistence Tools 
 */
public class TNetResultDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TNetResultDAO.class);
	//property constants
	public static final String KEY_WORD = "keyWord";
	public static final String URL = "url";
	public static final String FLAG = "flag";

	protected void initDao() {
		//do nothing
	}

	public void save(TNetResult transientInstance) {
		log.debug("saving TNetResult instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TNetResult persistentInstance) {
		log.debug("deleting TNetResult instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TNetResult findById(java.lang.Integer id) {
		log.debug("getting TNetResult instance with id: " + id);
		try {
			TNetResult instance = (TNetResult) getHibernateTemplate().get("com.darg.opo.pojo.TNetResult", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TNetResult instance) {
		log.debug("finding TNetResult instance by example");
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
		log.debug("finding TNetResult instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TNetResult as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKeyWord(Object keyWord) {
		return findByProperty(KEY_WORD, keyWord);
	}

	public List findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findByFlag(Object flag) {
		return findByProperty(FLAG, flag);
	}

	public List findAll() {
		log.debug("finding all TNetResult instances");
		try {
			String queryString = "from TNetResult";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}	
	/**
	 *  定网预警查询通过时间
	 * @author rz
	 * @param g_size
	 * @param dateTime01
	 * @param dateTime02
	 * @return
	 */
	public List<TNetResult> findNetAddrsWarnG_sizeByTwoTime(String dateTime01, String dateTime02) {
		log.debug("findNetAddrsWarnG_sizeByTwoTime");
		try {		
			String queryString = "select distinct t.keyWord,t.cindex,t.url,t.ctime,t.flag from T_NetResult as t"
			+ " where  t.ctime >= '" + dateTime01 + "' and t.ctime <= '" + dateTime02 + "' GROUP BY t.keyWord";

			Session session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			SQLQuery query = (SQLQuery) session.createSQLQuery(queryString).setResultTransformer(Transformers.aliasToBean(TNetResult.class));
			
			query.addScalar("keyWord", StandardBasicTypes.STRING);
			query.addScalar("cindex", StandardBasicTypes.INTEGER);			
			query.addScalar("url", StandardBasicTypes.STRING);						
			query.addScalar("ctime", StandardBasicTypes.TIMESTAMP);
			query.addScalar("flag", StandardBasicTypes.STRING);
								
			List list = query.list();
			t.commit();
			session.close();		
			return list;
		} catch (RuntimeException re) {
			log.error("findNetAddrsWarnG_sizeByTwoTime", re);
			throw re;
		}
	}
	
	/**
	 * 查找定网预警
	 * @author zf
	 * @return
	 * @throws Exception
	 */
	public List findNetAlarmInfo()throws Exception {

		log.info("TNetResultDAO.findNetAlarmInfo()方法  Start");
				
		//String queryString="SELECT rc.cIndex, rc.keyWord, rc.cTime FROM T_PublicSentimentRecent AS rc WHERE rc.keyWord LIKE '%"+Newkeyword+"%'";
		String queryString="SELECT nr.cindex, nr.keyWord, nr.url, nr.cTime,nr.flag FROM T_NetResult AS nr WHERE nr.cTime < NOW() AND nr.cTime > CAST(CURDATE() AS DATETIME) AND nr.flag = 1";
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = (SQLQuery) session.createSQLQuery(queryString)
				.setResultTransformer(
						Transformers.aliasToBean(TNetResult.class));
		query.addScalar("cindex", StandardBasicTypes.INTEGER);
		query.addScalar("keyWord", StandardBasicTypes.STRING);
		query.addScalar("ctime", StandardBasicTypes.TIMESTAMP);
		query.addScalar("url", StandardBasicTypes.STRING);
		query.addScalar("flag", StandardBasicTypes.STRING);


		List list = query.list();
		t.commit();
		session.close();

		log.info("TNetResultDAO .findNetAlarmInfo()方法  end");
		return list;
	}
	
	
	public TNetResult merge(TNetResult detachedInstance) {
		log.debug("merging TNetResult instance");
		try {
			TNetResult result = (TNetResult) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TNetResult instance) {
		log.debug("attaching dirty TNetResult instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TNetResult instance) {
		log.debug("attaching clean TNetResult instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TNetResultDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TNetResultDAO) ctx.getBean("TNetResultDAO");
	}
}