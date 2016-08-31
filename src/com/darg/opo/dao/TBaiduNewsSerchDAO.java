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

import com.darg.opo.pojo.TBaiduNewsSerch;
import com.darg.opo.pojo.TPublicSentimentKeyword;
import com.darg.opo.pojo.TPublicSentimentRresult;

/**
 	* A data access object (DAO) providing persistence and search support for TBaiduNewsSerch entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.darg.opo.pojo.TBaiduNewsSerch
  * @author MyEclipse Persistence Tools 
 */
public class TBaiduNewsSerchDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(TBaiduNewsSerchDAO.class);
		//property constants
	public static final String TITLE = "title";
	public static final String AUTHOR = "author";
	public static final String CONTENT = "content";
	public static final String NEWS_URL = "newsUrl";



	protected void initDao() {
		//do nothing
	}
    
    public void save(TBaiduNewsSerch transientInstance) {
        log.debug("saving TBaiduNewsSerch instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TBaiduNewsSerch persistentInstance) {
        log.debug("deleting TBaiduNewsSerch instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TBaiduNewsSerch findById( java.lang.Integer id) {
        log.debug("getting TBaiduNewsSerch instance with id: " + id);
        try {
            TBaiduNewsSerch instance = (TBaiduNewsSerch) getHibernateTemplate()
                    .get("com.darg.opo.pojo.TBaiduNewsSerch", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TBaiduNewsSerch instance) {
        log.debug("finding TBaiduNewsSerch instance by example");
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
      log.debug("finding TBaiduNewsSerch instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TBaiduNewsSerch as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}
    /**
     * like title模糊查询
     * @author rz
     * @param propertyName
     * @param value
     * @return
     */
    public List findBytitleLike(String title) {
        log.debug("findBytitleLike");
        try {
           String queryString = "SELECT model from TBaiduNewsSerch as model where model.title LIKE '%" + title + "%'";
  		 return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
           log.error("findBytitleLike", re);
           throw re;
        }
  	}
    /**
     * like newsUrl 模糊查询
     * @author rz
     * @param propertyName
     * @param value
     * @return
     */
    public List findByNewsUrlLike(String title) {
        log.debug("findBytitleLike");
        try {
           String queryString = "SELECT model from TBaiduNewsSerch as model where model.newsUrl LIKE '%" + title + "%'";
  		 return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
           log.error("findBytitleLike", re);
           throw re;
        }
  	}

	public List findByTitle(Object title
	) {
		return findByProperty(TITLE, title
		);
	}
	
	public List findByAuthor(Object author
	) {
		return findByProperty(AUTHOR, author
		);
	}
	
	public List findByContent(Object content
	) {
		return findByProperty(CONTENT, content
		);
	}
	
	public List findByNewsUrl(Object newsUrl
	) {
		return findByProperty(NEWS_URL, newsUrl
		);
	}
	

	public List findAll() {
		log.debug("finding all TBaiduNewsSerch instances");
		try {
			String queryString = "from TBaiduNewsSerch";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/**
	 * 查询时间范围内的最新消息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List findBaiduNewsSerch(String dateTime01, String dateTime02)throws Exception {

		log.info("TBaiduNewsSerchDAO .findBaiduNewsSerch()方法  Start");
		
		TPublicSentimentKeyword keyword = new TPublicSentimentKeyword();
		String queryString="SELECT t.* FROM T_BaiduNewsSerch as t WHERE t.publicTime >= '"+dateTime01+"' AND t.publicTime <='"+dateTime02+"'  order by t.publicTime desc";
		
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = (SQLQuery) session.createSQLQuery(queryString)
				.setResultTransformer(
						Transformers.aliasToBean(TBaiduNewsSerch.class));
		query.addScalar("cindex", StandardBasicTypes.INTEGER);
		query.addScalar("title", StandardBasicTypes.STRING);
		query.addScalar("author", StandardBasicTypes.STRING);
		query.addScalar("content", StandardBasicTypes.STRING);
		query.addScalar("newsUrl", StandardBasicTypes.STRING);
		query.addScalar("publicTime", StandardBasicTypes.TIMESTAMP);
		query.addScalar("ctime", StandardBasicTypes.TIMESTAMP);		


		List list = query.list();
		t.commit();
		session.close();

		log.info("TBaiduNewsSerchDAO .findBaiduNewsSerch()方法  end");
		return list;
	}
    public TBaiduNewsSerch merge(TBaiduNewsSerch detachedInstance) {
        log.debug("merging TBaiduNewsSerch instance");
        try {
            TBaiduNewsSerch result = (TBaiduNewsSerch) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TBaiduNewsSerch instance) {
        log.debug("attaching dirty TBaiduNewsSerch instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TBaiduNewsSerch instance) {
        log.debug("attaching clean TBaiduNewsSerch instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static TBaiduNewsSerchDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (TBaiduNewsSerchDAO) ctx.getBean("TBaiduNewsSerchDAO");
	}
}