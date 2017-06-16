package cn.yxg.yxgAppServer.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.yxg.commons.dao.hibernate.AdvancedHibernateDao;
import cn.yxg.yxgAppServer.dto.MessageVerifyDto;
import cn.yxg.yxgAppServer.entity.User;
import cn.yxg.yxgAppServer.entity.VerificationCode;
import cn.yxg.yxgAppServer.util.DateUtil;

@Repository
public class VerificationCodeDao extends AdvancedHibernateDao<VerificationCode>{

	public int findTimesInOneDay(String phone) {
		Criteria criteria = this.getCurrentSession().createCriteria(VerificationCode.class);
		criteria.add(Restrictions.eq("phone", phone));
		criteria.add(Restrictions.gt("createtime", DateUtil.getCurrentDateZero()));
		return criteria.list().size();
	}

	public int getByPhoneAndCode(String phone,String code) {
		Criteria criteria = this.getCurrentSession().createCriteria(VerificationCode.class);
		criteria.add(Restrictions.eq("phone", phone));
		criteria.add(Restrictions.eq("code", code));
		criteria.add(Restrictions.gt("exceedTime", new Date()));
		return criteria.list().size();
	}
	
}
