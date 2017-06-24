package cn.yxg.yxgCms.service;

import java.util.List;


import cn.yxg.yxgCms.dto.ClassificationDto;
import cn.yxg.yxgCms.entity.Classification;

/**
 * 组织机构业务逻辑接口.
 * @author lpf
 *
 */
public interface ClassificationService {

	/**
	 * 获取全部组织机构列表.
	 * @return
	 */
	public List<Classification> list();


	/**
	 * 当前节点到根节点的名称
	 * @param id 当前节点
	 * @return
	 */
	public String getRootParentNames(Integer id);

	/**
	 * 保存组织结构
	 * @param ClassificationDto
	 * @throws Exception 
	 */
	public void save(ClassificationDto dto) throws Exception;

	/**
	 * 保存组织结构
	 * @param parentId 选中节点ID
	 * @param name		保存的节点名称
	 */
	public void save(Integer parentId, String name);

	
	/**
	 * 查询组织结构
	 * @param id
	 * @return
	 */
	public Classification getClassification(Integer id);

	/**
	 * 更新组织结构
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public void update(Integer id, String name) throws Exception;

	/**
	 * 删除组织结构
	 * @param id
	 * @return
	 */
	public void delete(Integer id);


	/**
	 * 获取组织结构
	 * @param id
	 * @return
	 */
	public Classification get(Integer id);
	/**
	 * 更新组织结构
	 * @param ClassificationDto
	 * @return
	 * @throws Exception 
	 */
	public void update(ClassificationDto dto) throws Exception;


	/**
	 * 获取顶级组织结构
	 *
	 * @return
	 */
	public Classification getRoot();


	/**
	 * 根据dto得到实体
	 * @param targetClassificationDtoSave
	 * @return
	 */
	public Classification getClassificationByDto(
			ClassificationDto targetClassificationDtoSave);
	
	/**
	 * 得到组织结构和子节点列表
	 * @param id
	 * @return
	 */
	public List<Classification> getClassificationAndAllChildList(Integer id);


	

	public List<Classification> get(ClassificationDto dto);


}
