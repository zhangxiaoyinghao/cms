package cn.yxg.yxgCms.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.aspectj.weaver.patterns.OrAnnotationTypePattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import cn.yxg.commons.util.json.JsonConverter;
import cn.yxg.commons.webdev.constant.ResponseStatusCode;
import cn.yxg.commons.webdev.http.RestResponse;
import cn.yxg.yxgCms.dto.ClassificationDto;
import cn.yxg.yxgCms.dto.ClassificationInfoDto;
import cn.yxg.yxgCms.entity.Classification;
import cn.yxg.yxgCms.service.ClassificationService;

/**
 * @author lpf
 *
 */
@Controller
@RequestMapping("yxgCms/classification")
public class ClassificationController {
	
	
	@Resource
	private ClassificationService classificationServiceImpl;
	
	
	private static final Logger logger = LoggerFactory.getLogger(ClassificationController.class); 

	
	/**
	 * 
	 * 分类管理页
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model){
//		if (checkAuthorization(new String[] { "cas_Classification_list" })) {
//			return returnAuthorizationError();
//		}
//		List<Classification> list=ClassificationService.list();
//		List<UserInfo> users = userServiceImpl.list();
//		List<UserDto> userDtos = new ArrayList<>();
//		for (UserInfo user : users) {
//			if(user==null)continue;
//			userDtos.add(user.convertUserDto());
//		}
//		model.addAttribute("users",userDtos);
		return "site.yxgCms.classification.list";
	}
	
	/**
	 * 根据父节点id  获得分类列表
	 * @return
	 */
	@RequestMapping(value = "getClassifications")
	@ResponseBody
	public RestResponse getClassifications(Integer id){
		RestResponse restResponse = new RestResponse();
//		List<Classification> list=ClassificationService.list(id);
		
		List<String> data=new ArrayList<>();
		Classification Classification=classificationServiceImpl.get(id);
		if(id==null||id<0){
			boolean flug=(Classification.getChildren().size()>0);
			String json="{ id:'"+Classification.getId()+"',name:'"+Classification.getName()+"',isParent:"+flug+",drag:false}";
			data.add(json);
		}else{
			Set<Classification> childrens = Classification.getChildren();
			String json;
			for (Classification child : childrens) {
				boolean flug=(child.getChildren().size()>0);
				json="{ id:'"+child.getId()+"',name:'"+child.getName()+"',isParent:"+flug+"}";
				data.add(json);
			}
		}
		restResponse.getBody().put("data", data);
		return restResponse;
	}
	/**
	 * 根据id  获得分类
	 * @return
	 */
	@RequestMapping(value = "getClassification")
	@ResponseBody
	public RestResponse getClassification(Integer id){
		RestResponse restResponse = new RestResponse();
		Classification Classification=classificationServiceImpl.get(id);
//		List<UserInfo> users = userServiceImpl.listAllByClassificationId(id);
		restResponse.getBody().put("Classification", Classification);
//		restResponse.getBody().put("users", users);
		return restResponse;
	}
	
	/**
	 * 跳转到添加分类页面  
	 * @param id 选中的节点ID
	 * @return
	 */
	@RequestMapping(value = "savePage", method = RequestMethod.GET)
	@ResponseBody
	public RestResponse savePage(Integer id,Model model){
//		if (checkAuthorization(new String[] { "cas_Classification_add" })) {
//			return returnRestAuthorizationError();
//		}
		RestResponse restResponse=new RestResponse();
		String rootName=null;
		//不选默认为根节点
		if(id==null){
			restResponse.setStatusCode(ResponseStatusCode.OK);
			rootName="/分类";
			restResponse.getBody().put("data", rootName);
			return restResponse;
		}
		
		rootName=classificationServiceImpl.getRootParentNames(id);
		
		if(rootName!=null){
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.getBody().put("data", rootName);
		}else{
			restResponse
			.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
		}
		
		
		return restResponse;
	}
	
	/**
	 * 跳转到更新分类页面
	 * @param id 分类ID
	 * @return
	 */
	@RequestMapping(value = "updatePage", method = RequestMethod.GET)
	@ResponseBody
	public RestResponse updatePage(Integer id,Model model){
//		if (checkAuthorization(new String[] { "cas_Classification_update" })) {
//			return returnRestAuthorizationError();
//		}
		RestResponse restResponse=new RestResponse();
		
		if(id==null){
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.getBody().put("data", id);
			
			return restResponse;
		}
		
		String rootName=classificationServiceImpl.getRootParentNames(id);
		
		rootName=rootName.substring(0, rootName.lastIndexOf("/"));
		if("".equals(rootName)){
			rootName="无";
		}
		Classification Classification=classificationServiceImpl.getClassification(id);
		
		if(Classification!=null){
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.getBody().put("data", rootName);
			restResponse.getBody().put("name", Classification.getName());
		}else{
			restResponse
			.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
		}
		return restResponse;
	}
	
	/**
	 * 保存分类
	 * @param parentId 选中的父节点ID
	 * @param name	添加的节点名称
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse save(Model model,@Valid ClassificationDto ClassificationDto ,BindingResult bResult){
		// 判断是否需要审核
		RestResponse restResponse=new RestResponse();
		if(bResult.hasErrors()){
			restResponse.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			restResponse.setMessage(bResult.getFieldError().getDefaultMessage());
			return restResponse;
		}
//		if (systemAuditUtil.isNeedAudit()) {
//			return MessageAuditWating.responseMessage("创建分类");
//		} else {
			try {
				classificationServiceImpl.save(ClassificationDto);
				restResponse.setStatusCode(ResponseStatusCode.OK);
				restResponse.setMessage("保存分类成功");
	
			} catch (Exception e) {
				logger.error("保存分类异常", e);
				restResponse.setMessage("保存分类失败，请检查分类名称是否重复");
				restResponse
				.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
				
				
			}
//		}
		return restResponse;	
	}
	/**
	 * 更新分类
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse update(Model model,@Valid ClassificationDto ClassificationDto ,BindingResult bResult ){
		RestResponse restResponse=new RestResponse();
		if(bResult.hasErrors()){
			restResponse.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			restResponse.setMessage(bResult.getFieldError().getDefaultMessage());
			return restResponse;
		}
//		if (systemAuditUtil.isNeedAudit()) {
//			return MessageAuditWating.responseMessage("修改分类");
//		} else {
			try {
				classificationServiceImpl.update(ClassificationDto);
				restResponse.setStatusCode(ResponseStatusCode.OK);
				restResponse.setMessage("更新分类成功");
			} catch (Exception e) {
				logger.error("修改分类异常", e);
				restResponse.setMessage("更新分类失败，请查看名称是否重复！");
				restResponse
				.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
			}
//		}
		return restResponse;	
		
	}
	
	/**
	 * 删除分类
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse delete(Integer id){
//		if (checkAuthorization(new String[] { "cas_Classification_delete" })) {
//			return returnRestAuthorizationError();
//		}
		RestResponse restResponse=new RestResponse();
//		if (systemAuditUtil.isNeedAudit()) {
//			return MessageAuditWating.responseMessage("删除分类");
//		} else {
			try {
				classificationServiceImpl.delete(id);
				restResponse.setStatusCode(ResponseStatusCode.OK);
				restResponse.setMessage("删除分类成功");
				
			} catch (Exception e) {
				logger.error("删除组织异常", e);
				restResponse.setMessage("删除分类失败，请先清除子节点信息");
				restResponse
				.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
			}
//		}
		
		return restResponse;	
		
	}
//	/** 检查用户权限 */
//	private boolean checkAuthorization(String[] authorizations) {
//		Map<String, String> authorizationMap = AclThreadCache
//				.getAuthorization();
//		if (authorizationMap != null) {
//			for (String authorization : authorizations) {
//				if (authorizationMap.containsKey(authorization)) {
//					return false;
//				}
//			}
//		}
//		return true;
//	}
//	private String returnAuthorizationError() {
//		return "site.cas.error.error";
//	}
	private RestResponse returnRestAuthorizationError() {
		RestResponse response=new RestResponse();
		response.setStatusCode(ResponseStatusCode.FORBIDDEN);
		response.setMessage("您没有该操作的权限，请核查自己权限");
		return response;
	}
	
	/**
	 * 拖拽更改分类关系
	 * @param parentId 选中的父节点ID
	 * @param name	添加的节点名称
	 * @return
	 */
//	@RequestMapping(value = "updateClassificationByDarg")
//	@ResponseBody
//	public RestResponse updateClassificationByDarg(Model model,@Valid DragDto dragDto ,BindingResult bResult){
//		// 判断是否需要审核
//		RestResponse restResponse=new RestResponse();
//		if(bResult.hasErrors()){
//			restResponse.setStatusCode(ResponseStatusCode.BAD_REQUEST);
//			restResponse.setMessage(bResult.getFieldError().getDefaultMessage());
//			return restResponse;
//		}
//		if (systemAuditUtil.isNeedAudit()) {
//			return MessageAuditWating.responseMessage("移动分类关系");
//		} else {
//			try {
//				classificationServiceImpl.updateClassificationByDarg(dragDto);
//				restResponse.setStatusCode(ResponseStatusCode.OK);
//				restResponse.setMessage("移动分类成功");
//	
//			} catch (NameAlreadyExistException e) {
//				logger.error("移动分类异常", e);
//				restResponse.setMessage(e.getMessage());
//				restResponse
//				.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
//			}catch (Exception e) {
//				logger.error("移动分类异常", e);
//				restResponse.setMessage("移动分类失败");
//				restResponse
//				.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
//			}
//		}
//		return restResponse;	
//	}
	
	/**********************************************************************
	 *     																  *
	 *     																  *
	 *     				rest 请求										  *
	 *     																  *
	 *     																  *
	 **********************************************************************
	 */
	
	/**
	 * 保存分类
	 * @param parentId 选中的父节点ID
	 * @param name	添加的节点名称
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse add(Model model,@RequestBody @Valid ClassificationDto ClassificationDto ,BindingResult bResult){
		// 判断是否需要审核
		logger.info("调用保存分类接口参数：【" + JsonConverter.format(ClassificationDto) + "】");

		RestResponse restResponse=new RestResponse();
		if(bResult.hasErrors()){
			restResponse.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			restResponse.setMessage(bResult.getFieldError().getDefaultMessage());
			return restResponse;
		}
		try {
			if(ClassificationDto.getParentId()==null){
				Classification parent = classificationServiceImpl.get(ClassificationDto.getParentId());
				ClassificationDto.setParentId(parent.getId());
			}
			classificationServiceImpl.save(ClassificationDto);
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.setMessage("保存分类成功");

		} catch (Exception e) {
			logger.error("保存分类异常", e);
			restResponse.setMessage("保存分类失败，请检查分类名称是否重复");
			restResponse
			.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
			
			
		}
		return restResponse;	
	}
	/**
	 * 更新分类
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	public RestResponse edit(Model model,@RequestBody @Valid ClassificationDto ClassificationDto ,BindingResult bResult ){
		
		logger.info("调用更新分类接口参数：【" + JsonConverter.format(ClassificationDto) + "】");

		RestResponse restResponse=new RestResponse();
		if(bResult.hasErrors()){
			restResponse.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			restResponse.setMessage(bResult.getFieldError().getDefaultMessage());
			return restResponse;
		}
		try {
			classificationServiceImpl.update(ClassificationDto);
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.setMessage("更新分类成功");
		} catch (Exception e) {
			logger.error("修改分类异常", e);
			restResponse.setMessage("更新分类失败，请查看名称是否重复！");
			restResponse
			.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
		}
		return restResponse;	
		
	}
	/**
	 * 删除分类
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public RestResponse deleteClassification(@PathVariable Integer id){
		
		logger.info("调用删除分类接口参数：【" + JsonConverter.format(id) + "】");

		RestResponse restResponse=new RestResponse();
		 
		try {
			classificationServiceImpl.delete(id);
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.setMessage("删除分类成功");
			
		} catch (Exception e) {
			logger.error("删除组织异常", e);
			restResponse.setMessage("删除分类失败，请先清除子节点信息");
			restResponse
			.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
		}
		return restResponse;	
	}
	
	
	/**
	 *  获得分类
	 * @return
	 */
	@RequestMapping(value = "Classifications",method=RequestMethod.GET)
	@ResponseBody
	public RestResponse getById(Integer id){
		RestResponse restResponse = new RestResponse();
		logger.info("调用分类树结构查询，根节点id是【" + JsonConverter.format(id) + "】");
		try {
			Classification Classification = classificationServiceImpl.get(id);
			
//			iterationClassification(Classification);
			//转成string
			String ClassificationStr = JsonConverter.formatByExpose(Classification);
			//序列化成对象
			ClassificationInfoDto ClassificationInfoDto = new ObjectMapper().readValue(ClassificationStr, ClassificationInfoDto.class);
			
			restResponse.getBody().put("Classification", ClassificationInfoDto);
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.setMessage("查询分类成功");
			
		} catch (Exception e) {
			logger.error("查询分类异常", e);
			restResponse.setMessage("查询出错");
			restResponse
			.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
		}
		
		return restResponse;
	}
	/**
	 *  获得分类
	 * @return
	 */
	@RequestMapping(value = "find",method=RequestMethod.POST)
	@ResponseBody
	public RestResponse getByName(@RequestBody ClassificationDto dto){
		logger.info("调用分类查询接口参数：【" + JsonConverter.format(dto) + "】");
		RestResponse restResponse = new RestResponse();
		
		try {
			List<Classification> Classification = classificationServiceImpl.get(dto);
			
			restResponse.getBody().put("Classifications", Classification);
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.setMessage("查询分类成功");
			
		} catch (Exception e) {
			logger.error("查询分类异常", e);
			restResponse.setMessage("查询出错");
			restResponse
			.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
		}
		
		return restResponse;
	}

	
//	/**
//	 * 迭代分类 给userInfos 丰富ldap属性
//	 * @param Classification
//	 */
//	private void iterationClassification(Classification Classification) {
//		
//		Set<UserInfo> userInfos1 = Classification.getUserInfos();
//		for (UserInfo userInfo : userInfos1){
//			userInfo=	userServiceImpl.getUserInfoAndLdapUserById(userInfo.getId());
//			userInfo.setUserRoleMappings(null);
//			userInfo.setRoles(null);
//			userInfo.setGroups(null);
//		}
//		if (Classification.getChildren().size()>0) {
//			
//			Set<Classification> childrens = Classification.getChildren();
//			for (Classification child : childrens) {
//				Set<UserInfo> userInfos = child.getUserInfos();
//				for (UserInfo userInfo : userInfos){
//					userInfo=	userServiceImpl.getUserInfoAndLdapUserById(userInfo.getId());
//					userInfo.setUserRoleMappings(null);
//					userInfo.setRoles(null);
//					userInfo.setGroups(null);
//				}
//				iterationClassification(child);
//			}
//			
//		}
//	}

//	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
//		String s= "{\"id\":4,\"name\":\"某中心\",\"enable\":true,\"childrens\":[],\"userInfos\":[{\"id\":\"uid\\u003dpaikem,ou\\u003dUsers\",\"enable\":true,\"upload\":false,\"groups\":[{\"id\":16,\"name\":\"媒资编目组\",\"category\":{\"name\":\"mam_catalog_group\"},\"parent\":{\"id\":1,\"name\":\"用户组\",\"category\":{\"name\":\"mam_systemConfig_group\"}}},{\"id\":20,\"name\":\"媒资审核组\",\"category\":{\"name\":\"mam_verify_group\"},\"parent\":{\"id\":1,\"name\":\"用户组\",\"category\":{\"name\":\"mam_systemConfig_group\"}}},{\"id\":55,\"name\":\"前台卖家\",\"category\":{\"name\":\"视频交易服务平台\"},\"parent\":{\"id\":1,\"name\":\"用户组\",\"category\":{\"name\":\"mam_systemConfig_group\"}}},{\"id\":70,\"name\":\"媒资编目审核组\",\"category\":{\"name\":\"mam_verify_group\"},\"parent\":{\"id\":1,\"name\":\"用户组\",\"category\":{\"name\":\"mam_systemConfig_group\"}}}],\"ldapUser\":{\"dn\":{},\"username\":\"paikem\",\"nickname\":\"付艳丽\",\"description\":\"无\",\"email\":\"fuyl@163.com\",\"telelphone\":\"0\",\"Classification\":\"某中心\"}}]}";
////		ClassificationInfoDto ClassificationInfoDto = JsonConverter.parse(s, ClassificationInfoDto.class);
//		ObjectMapper mapper=new ObjectMapper();
//		ClassificationInfoDto ClassificationInfoDto = mapper.readValue(s, ClassificationInfoDto.class);
//		System.out.println(ClassificationInfoDto);
//	}
	
	
	/**
	 *  获得分类
	 * @return
	 */
//	@RequestMapping(value = "userInfos",method=RequestMethod.GET)
//	@ResponseBody
//	public RestResponse classifications(Integer id){
//		logger.info("调用分类查询接口参数：【" + JsonConverter.format(id) + "】");
//		RestResponse restResponse = new RestResponse();
//		
//		try {
//			
//			Classification Classification = classificationServiceImpl.get(id);
//			
////			for (UserInfo userInfo : userInfos){
////				userInfo=	userServiceImpl.getUserInfoAndLdapUserById(userInfo.getId());
////				if(userInfo.getLdapUser()!=null){
////					userInfo.getLdapUser().setPassword(null);
////				}
////				userInfo.setUserRoleMappings(null);
////				userInfo.setRoles(null);
////				userInfo.setGroups(null);
////			}
//			restResponse.getBody().put("Classifications",	Classification.getChildren());
////			restResponse.getBody().put("userInfos",	userInfos);
//			
//			restResponse.setStatusCode(ResponseStatusCode.OK);
//			restResponse.setMessage("查询分类用户成功");
//			
//		} catch (Exception e) {
//			logger.error("查询分类用户异常", e);
//			restResponse.setMessage("查询出错");
//			restResponse
//			.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
//		}
//		
//		return restResponse;
//	}
	
	/**
	 *  获得分类
	 * @return
	 */
	@RequestMapping(value = "all",method=RequestMethod.GET)
	@ResponseBody
	public RestResponse all(){
		logger.info("调用分类查询接口");
		RestResponse restResponse = new RestResponse();
		
		try {
			List<Classification> list = classificationServiceImpl.list();
			for (Classification Classification : list) {
				Classification.setParent(null);
			}
			restResponse.getBody().put("Classifications",	list);
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.setMessage("查询分类用户成功");
			
		} catch (Exception e) {
			logger.error("查询分类用户异常", e);
			restResponse.setMessage("查询出错");
			restResponse
			.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
		}
		
		return restResponse;
	}
	
	
}
