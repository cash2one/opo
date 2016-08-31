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
import com.darg.opo.pojo.TTopKeyWord;

/**
 	* A data access object (DAO) providing persistence and search support for T360topKeyWord entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.darg.opo.pojo.T360topKeyWord
  * @author MyEclipse Persistence Tools 
 */
public class T360topKeyWordDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(T360topKeyWordDAO.class);
		//property constants
	public static final String TITLE = "title";
	public static final String LTITLE = "ltitle";
	public static final String NEWS_URL = "newsUrl";
	public static final String IMG_URL = "imgUrl";
	public static final String KEYWORD = "keyword";
	public static final String COUNT = "count";



	protected void initDao() {
		//do nothing
	}
    
    public void save(T360topKeyWord transientInstance) {
        log.debug("saving T360topKeyWord instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(T360topKeyWord persistentInstance) {
        log.debug("deleting T360topKeyWord instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public T360topKeyWord findById( java.lang.Integer id) {
        log.debug("getting T360topKeyWord instance with id: " + id);
        try {
            T360topKeyWord instance = (T360topKeyWord) getHibernateTemplate()
                    .get("com.darg.opo.dao.T360topKeyWord", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(T360topKeyWord instance) {
        log.debug("finding T360topKeyWord instance by example");
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
      log.debug("finding T360topKeyWord instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from T360topKeyWord as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByTitle(Object title
	) {
		return findByProperty(TITLE, title
		);
	}
	
	public List findByLtitle(Object ltitle
	) {
		return findByProperty(LTITLE, ltitle
		);
	}
	
	public List findByNewsUrl(Object newsUrl
	) {
		return findByProperty(NEWS_URL, newsUrl
		);
	}
	
	public List findByImgUrl(Object imgUrl
	) {
		return findByProperty(IMG_URL, imgUrl
		);
	}
	
	public List findByKeyword(Object keyword
	) {
		return findByProperty(KEYWORD, keyword
		);
	}
	
	public List findByCount(Object count
	) {
		return findByProperty(COUNT, count
		);
	}
	

	public List findAll() {
		log.debug("finding all T360topKeyWord instances");
		try {
			String queryString = "from T360topKeyWord";
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
	public List<T360topKeyWord> find360HotG_sizeByTime(String g_size) {
		log.debug("find360HotG_sizeByTime");
		try {

			Timestamp endCreatetime = CommonUtil.getNowTime_tamp();
			Timestamp tp = new Timestamp(System.currentTimeMillis());
			Long lg = tp.getTime() - 86400000;
			Timestamp startCreatetime = new Timestamp(lg);
			String queryString = "select distinct t.title,t.cindex,t.ltitle,t.newsUrl,t.imgUrl,t.keyword,t.count,t.ctime from T_360TopKeyWord as t"
			+ " where  t.ctime >= '" + startCreatetime + " ' and t.ctime <= '" + endCreatetime + "' ORDER BY  rand() LIMIT "+g_size;

			Session session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			SQLQuery query = (SQLQuery) session.createSQLQuery(queryString).setResultTransformer(Transformers.aliasToBean(T360topKeyWord.class));
			
			query.addScalar("title", StandardBasicTypes.STRING);
			query.addScalar("cindex", StandardBasicTypes.INTEGER);			
			query.addScalar("ltitle", StandardBasicTypes.STRING);
			query.addScalar("newsUrl", StandardBasicTypes.STRING);
			query.addScalar("imgUrl", StandardBasicTypes.STRING);
			query.addScalar("keyword", StandardBasicTypes.STRING);
			query.addScalar("count", StandardBasicTypes.INTEGER);
			query.addScalar("ctime", StandardBasicTypes.TIMESTAMP);
					
			List list = query.list();
			t.commit();
			session.close();		
			return list;
		} catch (RuntimeException re) {
			log.error("find360HotG_sizeByTime", re);
			throw re;
		}
	}
    public T360topKeyWord merge(T360topKeyWord detachedInstance) {
        log.debug("merging T360topKeyWord instance");
        try {
            T360topKeyWord result = (T360topKeyWord) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(T360topKeyWord instance) {
        log.debug("attaching dirty T360topKeyWord instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(T360topKeyWord instance) {
        log.debug("attaching clean T360topKeyWord instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static T360topKeyWordDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (T360topKeyWordDAO) ctx.getBean("T360topKeyWordDAO");
	}
}