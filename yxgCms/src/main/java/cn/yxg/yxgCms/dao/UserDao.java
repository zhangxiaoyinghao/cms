package cn.yxg.yxgCms.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.yxg.commons.dao.hibernate.AdvancedHibernateDao;
import cn.yxg.commons.webdev.vo.Page;
import cn.yxg.yxgCms.entity.Token;
import cn.yxg.yxgCms.entity.User;

@Repository
public class UserDao extends AdvancedHibernateDao<User>{

	public int findByPhone(String phone) {
		Criteria criteria = this.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", phone));
		return criteria.list().size();
	}

	public User findByPhoneAndPassword(String phone, String password) {
		Criteria criteria = this.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", phone));
		criteria.add(Restrictions.eq("password", password));
		return criteria.list().size()==0?null:(User)criteria.list().get(0);
	}

	public User findByWechatId(String wechatId) {
		Criteria criteria = this.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("wechatId", wechatId));
		return criteria.list().size()==0?null:(User)criteria.list().get(0);
	}

	public List<User> listByKeyword(String keyword, Integer borderId, Integer number) {
		Criteria criteria = this.getCurrentSession().createCriteria(User.class);
		if(!StringUtils.isBlank(keyword)){
			criteria.add(Restrictions.like("nickname", "%"+keyword+"%"));
		}
		if(null!=borderId){
			criteria.add(Restrictions.lt("id", borderId));
		}
		criteria.setMaxResults(number);
		criteria.addOrder(Order.desc("id"));
		return criteria.list();
	}

	public long count(String nickname, String username, String wechatId,
			Integer type) {
			Criteria criteria = this.getCurrentSession().createCriteria(User.class);
			if(!StringUtils.isBlank(nickname)){
				criteria.add(Restrictions.like("nickname", "%"+nickname+"%"));
			}
			if(!StringUtils.isBlank(username)){
				criteria.add(Restrictions.like("username", "%"+username+"%"));
			}
			if(!StringUtils.isBlank(wechatId)){
				criteria.add(Restrictions.like("wechatId", "%"+wechatId+"%"));
			}
			if(null!= type){
				criteria.add(Restrictions.eq("type", type));
			}
			return (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<User> list(String nickname, String username, String wechatId,
			Integer type, Page page) {
		Criteria criteria = this.getCurrentSession().createCriteria(User.class);
		if(!StringUtils.isBlank(nickname)){
			criteria.add(Restrictions.like("nickname", "%"+nickname+"%"));
		}
		if(!StringUtils.isBlank(username)){
			criteria.add(Restrictions.like("username", "%"+username+"%"));
		}
		if(!StringUtils.isBlank(wechatId)){
			criteria.add(Restrictions.like("wechatId", "%"+wechatId+"%"));
		}
		if(null!= type){
			criteria.add(Restrictions.eq("type", type));
		}
		if(null==page){
			page = new Page();
		}
		criteria.addOrder(Order.desc("createtime"));
		criteria.setMaxResults(page.getSize());
		criteria.setFirstResult(page.getIndex() * page.getSize());
		return criteria.list();
	}
	
}
