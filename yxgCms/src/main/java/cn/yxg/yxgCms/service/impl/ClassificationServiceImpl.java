package cn.yxg.yxgCms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.yxg.yxgCms.dao.ClassificationDao;
import cn.yxg.yxgCms.dto.ClassificationDto;
import cn.yxg.yxgCms.entity.Classification;
import cn.yxg.yxgCms.service.ClassificationService;


@Service
public class ClassificationServiceImpl implements ClassificationService{

	
	
	@Resource
	private ClassificationDao classificationDao;
			
	
	private static final Logger logger = LoggerFactory.getLogger(ClassificationServiceImpl.class);
	
	@Override
	public List<Classification> list() {
		
		return classificationDao.list();
	}

	@Override
	public String getRootParentNames(Integer parentId) {
		Classification  temp=classificationDao.get(parentId);
//		if(temp.getParent()==null){
//			return "无";
//		}
		
		StringBuilder sBuilder=new StringBuilder();
		if(temp!=null){
			sBuilder.append("/").append(temp.getName());
			while(temp.getParent()!=null){
				temp=temp.getParent();
				//过滤根节点
//				if(temp.getParent()!=null){
//				}
				sBuilder.insert(0,"/"+temp.getName());
			}
			return sBuilder.toString();
		}
		return null;
	}

	@Override
	public void save(ClassificationDto classificationDto) throws Exception {
		
		
		//查询  子节点名字去重
		Classification parent = classificationDao.get(classificationDto.getParentId());
		for (Classification child : parent.getChildren()) {
			if(classificationDto.getName().equals(child.getName())){
				throw new Exception("分类名重复，不能添加");
			}
		}
		Classification classification=new Classification();
		//建立关系
		classification.setParent(parent);
		classification.setName(classificationDto.getName());
		classification.setRanking(getClassificationRanking(parent));
		classificationDao.save(classification);
		
	}

	@Override
	public void save(Integer parentId, String name) {
		
		
		//查询  子节点名字去重
		Classification parent = classificationDao.get(parentId);
		for (Classification child : parent.getChildren()) {
			if(name.equals(child.getName())){
				throw new RuntimeException();
			}
		}

		Classification   classification=new Classification();
		classification.setParent(parent);
		classification.setName(name);
		classificationDao.save(classification);
	}

	@Override
	public Classification getClassification(Integer id) {
		return classificationDao.get(id);
	}

	@Override
	public void update(Integer id, String name) throws Exception {
		Classification classification = classificationDao.get(id);
		//修改的名称与数据一致
		if(name.equals(classification.getName())){
			return;
		}
		//查询  子节点名字去重
		for (Classification o : classification.getParent().getChildren()) {
			if(name.equals(o.getName())){
				throw new Exception("分类名重复，不能添加");
			}
		}
		classification.setName(name);
		
	}

	public void delete(Integer id) {
		Classification classification = classificationDao.get(id);
		
		
		classification.getParent().getChildren().remove(classification);
		classification.setParent(null);
		classificationDao.delete(classification);
	}


	@Override
	public Classification get(Integer id) {
		if(id==null||id<0){
			String hql="from Classification where parent.id is null";
			Classification classification = classificationDao.findByHQL(hql, 0, 0).get(0);
			return classification;
		}else{
			return classificationDao.get(id);
		}
	}

	@Override
	public void update(ClassificationDto classificationDto) throws Exception {
		
		int id=classificationDto.getId();
		String name = classificationDto.getName();
		Classification classification = classificationDao.get(id);
		//修改的名称与数据一致
		if(name.equals(classification.getName())){
			return;
		}
		
		Classification parent = classification.getParent();
		//
		if(parent!=null){
			//查询  子节点名字去重
			for (Classification o : classification.getParent().getChildren()) {
				if(name.equals(o.getName())){
					throw new Exception("分类名重复，不能添加");
				}
			}
		}
		classification.setName(name);
	}

	@Override
	public Classification getRoot() {
		// TODO Auto-generated method stub.
		String hql = "from Classification where parent.id is NULL";
		List<Classification> orgs = classificationDao.findByHQL(hql,0,0);
		if(orgs!=null&&orgs.size()>0){
			return orgs.get(0);
		}else{
			logger.error("could not find the default Classification!");
			return null;
		}
	}

	@Override
	public Classification getClassificationByDto(
			ClassificationDto classificationDto) {
		Classification Classification = new Classification();
		Classification.setName(classificationDto.getName());
		Classification.setId(classificationDto.getId());
		if (classificationDto.getParentId() > 0) {
			Classification parent = get(classificationDto.getParentId());
			Classification.setParent(parent);
		}
		return Classification;
	}

	@Override
	public List<Classification> getClassificationAndAllChildList(Integer id){
		
		Classification classification = classificationDao.get(id);
		List<Classification> classificationList=new ArrayList<>();
		if(classification==null){
			return classificationList;
		}
		classificationList.add(classification);
		getChildClassification(classificationList, classification);
		
		return classificationList;
		
	}
	
	/**
	 * 递归子节点
	 * @param ClassificationList 
	 * @param Classification
	 */
	private void getChildClassification(List<Classification> ClassificationList,
			Classification Classification) {
		
		if(Classification.getChildren().size()>0){
			for (Classification org : Classification.getChildren()) {
				ClassificationList.add(org);
				if(org.getChildren().size()>0){
					getChildClassification(ClassificationList, org);
				}
			}
		}
		
	}

//	@Override
//	public void updateClassificationByDarg(DragDto dragDto) {
//		Integer id = dragDto.getId();
//		Integer targetId = dragDto.getTargetId();
//		String type = dragDto.getType();
//		
//		Classification Classification = get(id);
//		Classification target = get(targetId);
//		
//		Integer ranking=null;
//			if("inner".equals(type)){
//				
//				isNameExist(Classification, target);
//				
//				Classification.getParent().getChildren().remove(Classification);
//				Classification.setParent(target);
//				ranking = getClassificationRanking(target);
//			}else if("prev".equals(type)){
//				
//				isNameExist(Classification, target.getParent());
//				
//				ranking = target.getRanking();
//				updateClassificationRanking(target,ranking);
//				Classification.setParent(target.getParent());
//				
//			}else if("next".equals(type)){
//				
//				isNameExist(Classification, target.getParent());
//				
//				ranking = target.getRanking()+1;
//				updateClassificationRanking(target,ranking);
//				Classification.setParent(target.getParent());
//			}else{
//				throw new IllegalArgumentException("类型参数错误");
//			}
//			
//		classification.setRanking(ranking);
//		classificationDao.update(Classification);
//	}

	/**
	 * 更新分类 排序状态
	 * @param target 目标节点
	 * @param ranking 当前节点ranking值
	 */
	private void updateClassificationRanking( Classification target,Integer ranking) {
		Set<Classification> childrens = target.getParent().getChildren();
			for (Classification Classification : childrens) {
				if(Classification.getRanking()>=ranking){
					Classification.setRanking(Classification.getRanking()+1);
				}
			}
	}

	/**
	 * 根据父分类 得到对应ranking值
	 * @param parent 父节点
	 */
	private Integer getClassificationRanking(Classification parent) {
		Set<Classification> childrens = parent.getChildren();
		int i=childrens.size();
		for (Classification org : childrens) {
			if((--i)==0){
				return org.getRanking()+1;
			}
		}
		return 1;
	}
	
	/**
	 * 判断同级别节点下名称是否已存在
	 * @param Classification
	 * @param parent
	 * @throws Exception 
	 */
	private void isNameExist(Classification Classification,Classification parent) throws Exception{
		String name=Classification.getName();
		Set<Classification> childrens = parent.getChildren();
		if(childrens.contains(Classification)){
			return;
		}
		for (Classification o : childrens) {
			if(name.equals(o.getName())){
				throw new Exception("操作失败，分类名重复。");
			}
		}
	}

	@Override
	public List<Classification> get(ClassificationDto dto) {
		
		return classificationDao.get(dto);
	}


}
