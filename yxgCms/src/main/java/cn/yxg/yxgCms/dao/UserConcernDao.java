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
import cn.yxg.yxgAppServer.entity.UserConcern;

@Repository
public class UserConcernDao extends AdvancedHibernateDao<UserConcern>{

	public boolean hasConcerned(User user, User concernedUser) {
		Criteria criteria = this.getCurrentSession().createCriteria(UserConcern.class);
		
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("concernedUser", concernedUser));
		return criteria.list().size()==0?false:true;
	}
	
}
