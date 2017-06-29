package cn.yxg.yxgCms.listener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import cn.yxg.commons.util.json.JsonConverter;
import cn.yxg.yxgCms.entity.Conf;
import cn.yxg.yxgCms.service.ADRecommendService;
import cn.yxg.yxgCms.service.ConfService;
import cn.yxg.yxgCms.web.ADController;

/**
 * TODO Put here a description of what this class does.
 *
 * @author zhangying.
 *         Created 2016-7-14.
 */
@Component
public class ADInitializer  implements ApplicationListener<ContextRefreshedEvent>{
	
	@Resource
	private ConfService confServiceImpl;
	
	@Resource
	private ADRecommendService adRecommendServiceImpl;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ADInitializer.class);
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub.
		if(event.getApplicationContext().getParent() == null){
			Conf conf = confServiceImpl.findByKey("adConf");
			try{
				List<Map<String,Object>> confItems = JsonConverter.parseByJackson(conf.getContent(), List.class);
				for (Map<String, Object> map : confItems) {
					int type = (int)map.get("type");
					int count = (int)map.get("count");
					adRecommendServiceImpl.execUpdate(type,count);
				}
			}catch(Exception e){
				logger.error("初始化广告位失败！",e);
				System.exit(0);
			}
		}
		
	}
	
}
