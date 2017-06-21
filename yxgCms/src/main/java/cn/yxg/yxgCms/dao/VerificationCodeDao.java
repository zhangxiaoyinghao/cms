package cn.yxg.yxgCms.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.yxg.commons.dao.hibernate.AdvancedHibernateDao;
import cn.yxg.yxgCms.dto.MessageVerifyDto;
import cn.yxg.yxgCms.entity.User;
import cn.yxg.yxgCms.entity.VerificationCode;
import cn.yxg.yxgCms.util.DateUtil;

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
