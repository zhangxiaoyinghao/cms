package cn.yxg.yxgCms.service.impl;

import javax.annotation.Resource;

import cn.yxg.yxgCms.dao.TokenDao;
import cn.yxg.yxgCms.entity.Token;
import cn.yxg.yxgCms.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService{

	@Resource
	private TokenDao tokenDao;
	
	@Override
	public boolean checkToken(String tokenStr) {
		if (StringUtils.isBlank(tokenStr)){
			return false;
		}
		int tokenNumber = tokenDao.findByToken(tokenStr);
		return tokenNumber==0?false:true;
	}
	
}
