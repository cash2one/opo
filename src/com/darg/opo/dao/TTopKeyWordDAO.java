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
import com.darg.opo.pojo.TPublicSentimentRresult;
import com.darg.opo.pojo.TTopKeyWord;

/**
 * A data access object (DAO) providing persistence and search support for
 * TTopKeyWord entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.darg.opo.pojo.TTopKeyWord
 * @author MyEclipse Persistence Tools
 */
public class TTopKeyWordDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TTopKeyWordDAO.class);
	// property constants
	public static final String KEY_WORD = "keyWord";
	public static final String COUNT = "count";

	protected void initDao() {
		// do nothing
	}

	public void save(TTopKeyWord transientInstance) {
		log.debug("saving TTopKeyWord instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TTopKeyWord persistentInstance) {
		log.debug("deleting TTopKeyWord instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TTopKeyWord findById(java.lang.Integer id) {
		log.debug("getting TTopKeyWord instance with id: " + id);
		try {
			TTopKeyWord instance = (TTopKeyWord) getHibernateTemplate().get("com.darg.opo.pojo.TTopKeyWord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TTopKeyWord instance) {
		log.debug("finding TTopKeyWord instance by example");
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
		log.debug("finding TTopKeyWord instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TTopKeyWord as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKeyWord(Object keyWord) {
		return findByProperty(KEY_WORD, keyWord);
	}

	public List findByCount(Object count) {
		return findByProperty(COUNT, count);
	}

	public List findAll() {
		log.debug("finding all TTopKeyWord instances");
		try {
			String queryString = "from TTopKeyWord";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * 查询百度top，有娱乐，教育等区分，查询一天的
	 * @author rz
	 * @param type
	 * @return
	 */
	public List<TTopKeyWord> findBaiduHotByTime(String type) {
		log.debug("findBaiduHotByTime");
		try {

			Timestamp endCreatetime = CommonUtil.getNowTime_tamp();
			Timestamp tp = new Timestamp(System.currentTimeMillis());
			Long lg = tp.getTime() - 86400000;
			Timestamp startCreatetime = new Timestamp(lg);
			String queryString = "select distinct t.keyWord,t.cindex,t.count,t.type,t.ctime from T_TopKeyWord as t"
			+ " where t.type='" + type + "' and t.ctime >= '" + startCreatetime + "' and t.ctime <= '" + endCreatetime + "' GROUP BY t.keyWord ORDER BY t.count DESC LIMIT 15";

			Session session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			SQLQuery query = (SQLQuery) session.createSQLQuery(queryString).setResultTransformer(Transformers.aliasToBean(TTopKeyWord.class));
			
			query.addScalar("keyWord", StandardBasicTypes.STRING);
			query.addScalar("cindex", StandardBasicTypes.INTEGER);
			query.addScalar("count", StandardBasicTypes.INTEGER);
			query.addScalar("type", StandardBasicTypes.STRING);
			query.addScalar("ctime", StandardBasicTypes.TIMESTAMP);
			
			List list = query.list();
			t.commit();
			session.close();		
			return list;
		} catch (RuntimeException re) {
			log.error("findBaiduHotByTime", re);
			throw re;
		}
	}
	/**
	 * 网络热点top50list查询通过时间
	 * @author rz
	 * @param type
	 * @return
	 */
	public List<TTopKeyWord> findBaiduHot50ByTime(String type) {
		log.debug("findBaiduHot50ByTime");
		try {			
			String queryString = "select distinct t.keyWord,t.cindex,t.count,t.type,t.ctime from T_TopKeyWord as t "
			+ " where t.type='" + type + "' order by t.ctime desc";

			Session session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			SQLQuery query = (SQLQuery) session.createSQLQuery(queryString).setResultTransformer(Transformers.aliasToBean(TTopKeyWord.class));
			
			query.addScalar("keyWord", StandardBasicTypes.STRING);
			query.addScalar("cindex", StandardBasicTypes.INTEGER);
			query.addScalar("count", StandardBasicTypes.INTEGER);
			query.addScalar("type", StandardBasicTypes.STRING);
			query.addScalar("ctime", StandardBasicTypes.TIMESTAMP);
			
			List list = query.list();
			t.commit();
			session.close();		
			return list;
		} catch (RuntimeException re) {
			log.error("findBaiduHot50ByTime", re);
			throw re;
		}
	}
	/**
	 * 查询百度top，有娱乐，教育等区分，查询一天的
	 * @author rz
	 * @param type
	 * @return
	 */
	public List<TTopKeyWord> findBauduHotG_sizeByTime(String g_size,String type) {
		log.debug("findBaiduHotByTime");
		try {

			Timestamp endCreatetime = CommonUtil.getNowTime_tamp();
			Timestamp startCreatetime = CommonUtil.getYesterdayTime_tamp();
			String queryString = "select distinct t.keyWord,t.cindex,t.count,t.type,t.ctime from T_TopKeyWord as t"
			+ " where t.type='" + type + "' and t.ctime >= '" + startCreatetime + " ' and t.ctime <= '" + endCreatetime + "' GROUP BY t.keyWord ORDER BY t.count DESC LIMIT "+g_size;

			Session session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			SQLQuery query = (SQLQuery) session.createSQLQuery(queryString).setResultTransformer(Transformers.aliasToBean(TTopKeyWord.class));
			
			query.addScalar("keyWord", StandardBasicTypes.STRING);
			query.addScalar("cindex", StandardBasicTypes.INTEGER);
			query.addScalar("count", StandardBasicTypes.INTEGER);
			query.addScalar("type", StandardBasicTypes.STRING);
			query.addScalar("ctime", StandardBasicTypes.TIMESTAMP);
			
			List list = query.list();
			t.commit();
			session.close();		
			return list;
		} catch (RuntimeException re) {
			log.error("findBaiduHotByTime", re);
			throw re;
		}
	}
	/**
	 * 查询模糊查询百度top50，查询一天的
	 * @author rz
	 * @param type
	 * @return
	 */
	public List<TTopKeyWord> findBauduHotFyByTime(String type,String keyWord) {
		log.debug("findBauduHotFyByTime");
		try {

			Timestamp endCreatetime = CommonUtil.getNowTime_tamp();
			Timestamp tp = new Timestamp(System.currentTimeMillis());
			Long lg = tp.getTime() - 86400000;
			Timestamp startCreatetime = new Timestamp(lg);
			String queryString = "select distinct t.keyWord,t.cindex,t.count,t.type,t.ctime from T_TopKeyWord as t"
			+ " where t.keyWord LIKE '%"+keyWord+"%' and t.type='" + type + "' and t.ctime >= '" + startCreatetime + " ' and t.ctime <= '" + endCreatetime + "' ORDER BY t.count DESC";

			Session session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			SQLQuery query = (SQLQuery) session.createSQLQuery(queryString).setResultTransformer(Transformers.aliasToBean(TTopKeyWord.class));
			
			query.addScalar("keyWord", StandardBasicTypes.STRING);
			query.addScalar("cindex", StandardBasicTypes.INTEGER);
			query.addScalar("count", StandardBasicTypes.INTEGER);
			query.addScalar("type", StandardBasicTypes.STRING);
			query.addScalar("ctime", StandardBasicTypes.TIMESTAMP);
			
			List list = query.list();
			t.commit();
			session.close();		
			return list;
		} catch (RuntimeException re) {
			log.error("findBauduHotFyByTime", re);
			throw re;
		}
	}
	/**
	 * 查询模糊查询百度top50，查询一天的,子查询，传递两个参数，都是同一个keyword Like
	 * @author rz
	 * @param type
	 * @return
	 */
	public List<TTopKeyWord> findBauduHotFyByTime(String type,String keyWord_big,String keyWord_small) {
		log.debug("findBauduHotFyByTime");
		try {

			Timestamp endCreatetime = CommonUtil.getNowTime_tamp();
			Timestamp tp = new Timestamp(System.currentTimeMillis());
			Long lg = tp.getTime() - 86400000;
			Timestamp startCreatetime = new Timestamp(lg);
			String queryString = "select t1.* from (select distinct t.keyWord,t.cindex,t.count,t.type,t.ctime from T_TopKeyWord as t"
			+ " where t.keyWord LIKE '%"+keyWord_big+"%' and t.type='" + type + "' and t.ctime >= '" + startCreatetime + " ' and t.ctime <= '"
					+ endCreatetime + "' ORDER BY t.count DESC) as t1 where t1.keyWord LIKE '%"+keyWord_small+"%'";

			Session session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			SQLQuery query = (SQLQuery) session.createSQLQuery(queryString).setResultTransformer(Transformers.aliasToBean(TTopKeyWord.class));
			
			query.addScalar("keyWord", StandardBasicTypes.STRING);
			query.addScalar("cindex", StandardBasicTypes.INTEGER);
			query.addScalar("count", StandardBasicTypes.INTEGER);
			query.addScalar("type", StandardBasicTypes.STRING);
			query.addScalar("ctime", StandardBasicTypes.TIMESTAMP);
			
			List list = query.list();
			t.commit();
			session.close();		
			return list;
		} catch (RuntimeException re) {
			log.error("findBauduHotFyByTime", re);
			throw re;
		}
	}
	public TTopKeyWord merge(TTopKeyWord detachedInstance) {
		log.debug("merging TTopKeyWord instance");
		try {
			TTopKeyWord result = (TTopKeyWord) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TTopKeyWord instance) {
		log.debug("attaching dirty TTopKeyWord instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TTopKeyWord instance) {
		log.debug("attaching clean TTopKeyWord instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TTopKeyWordDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TTopKeyWordDAO) ctx.getBean("TTopKeyWordDAO");
	}
	/**
	 * 查询时间范围内的网络热点
	 * 
	 * @return
	 * @throws Exception
	 */
	public List searchTopKeyword(String dateTime01, String dateTime02)throws Exception {

		log.info("TTopKeyWordDAO .searchTopKeyword()方法  Start");
		
		//TPublicSentimentKeyword keyword = new TPublicSentimentKeyword();
		String queryString="SELECT top.* FROM T_TopKeyWord AS top WHERE top.cTime >='"+dateTime01+"' AND top.cTime <='"+dateTime02+ "'  order by top.ctime desc";
		
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = (SQLQuery) session.createSQLQuery(queryString)
				.setResultTransformer(
						Transformers.aliasToBean(TTopKeyWord.class));
		query.addScalar("cindex", StandardBasicTypes.INTEGER);
		query.addScalar("keyWord", StandardBasicTypes.STRING);
		query.addScalar("count", StandardBasicTypes.INTEGER);
		query.addScalar("type", StandardBasicTypes.STRING);
		query.addScalar("ctime", StandardBasicTypes.TIMESTAMP);


		List list = query.list();
		t.commit();
		session.close();

		log.info("TTopKeyWordDAO .searchTopKeyword()方法  end");
		return list;
	}
	
	
	
	
	
	
	
}