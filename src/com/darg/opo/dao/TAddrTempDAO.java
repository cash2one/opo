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
import com.darg.opo.pojo.T360topKeyWord;
import com.darg.opo.pojo.TAddrTemp;

/**
 	* A data access object (DAO) providing persistence and search support for TAddrTemp entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.darg.opo.pojo.TAddrTemp
  * @author MyEclipse Persistence Tools 
 */
public class TAddrTempDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TAddrTempDAO.class);
	//property constants
	public static final String KEY_WORD = "keyWord";
	public static final String URL = "url";

	protected void initDao() {
		//do nothing
	}

	public void save(TAddrTemp transientInstance) {
		log.debug("saving TAddrTemp instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TAddrTemp persistentInstance) {
		log.debug("deleting TAddrTemp instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TAddrTemp findById(java.lang.Integer id) {
		log.debug("getting TAddrTemp instance with id: " + id);
		try {
			TAddrTemp instance = (TAddrTemp) getHibernateTemplate().get("com.darg.opo.pojo.TAddrTemp", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TAddrTemp instance) {
		log.debug("finding TAddrTemp instance by example");
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
		log.debug("finding TAddrTemp instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TAddrTemp as model where model." + propertyName + "= ?";
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

	public List findAll() {
		log.debug("finding all TAddrTemp instances");
		try {
			String queryString = "from TAddrTemp";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/**
	 *  360网络热点Hot搜索排名前面list查询通过当天一天时间时间
	 * @author rz
	 * @param g_size
	 * @return
	 * @throws Exception
	 */
	public List<TAddrTemp> findEarthHotWordG_size(String g_size) {
		log.debug("findEarthHotWordG_size");
		try {

			Timestamp endCreatetime = CommonUtil.getNowTime_tamp();
			Timestamp tp = new Timestamp(System.currentTimeMillis());
			Long lg = tp.getTime() - 86400000*3;
			Timestamp startCreatetime = new Timestamp(lg);
			String queryString = "select distinct t.keyWord,t.id,t.url,t.ctime from T_AddrTemp as t"
			+ " where  t.ctime >= '" + startCreatetime + " ' and t.ctime <= '" + endCreatetime + "' ORDER BY  rand() LIMIT "+g_size;

			Session session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			SQLQuery query = (SQLQuery) session.createSQLQuery(queryString).setResultTransformer(Transformers.aliasToBean(TAddrTemp.class));
			
			query.addScalar("keyWord", StandardBasicTypes.STRING);
			query.addScalar("id", StandardBasicTypes.INTEGER);			
			query.addScalar("url", StandardBasicTypes.STRING);			
			query.addScalar("ctime", StandardBasicTypes.TIMESTAMP);
					
			List list = query.list();
			t.commit();
			session.close();		
			return list;
		} catch (RuntimeException re) {
			log.error("findEarthHotWordG_size", re);
			throw re;
		}
	}
	public TAddrTemp merge(TAddrTemp detachedInstance) {
		log.debug("merging TAddrTemp instance");
		try {
			TAddrTemp result = (TAddrTemp) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TAddrTemp instance) {
		log.debug("attaching dirty TAddrTemp instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TAddrTemp instance) {
		log.debug("attaching clean TAddrTemp instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TAddrTempDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TAddrTempDAO) ctx.getBean("TAddrTempDAO");
	}
}