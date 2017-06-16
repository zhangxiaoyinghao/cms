package cn.yxg.yxgAppServer.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.yxg.commons.dao.hibernate.AdvancedHibernateDao;
import cn.yxg.yxgAppServer.entity.Token;
import cn.yxg.yxgAppServer.entity.User;

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
	
}
