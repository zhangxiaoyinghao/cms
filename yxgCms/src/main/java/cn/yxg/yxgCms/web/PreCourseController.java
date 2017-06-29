package cn.yxg.yxgCms.web;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yxg.commons.util.json.JsonConverter;
import cn.yxg.commons.webdev.constant.ResponseStatusCode;
import cn.yxg.commons.webdev.http.RestResponse;
import cn.yxg.commons.webdev.vo.Page;
import cn.yxg.yxgCms.dto.CategoryPageDto;
import cn.yxg.yxgCms.dto.CourseCommentListDto;
import cn.yxg.yxgCms.dto.CourseCommentSubmitDto;
import cn.yxg.yxgCms.dto.CourseDetailDto;
import cn.yxg.yxgCms.dto.CourseFilterInputDto;
import cn.yxg.yxgCms.dto.CourseHomepageDto;
import cn.yxg.yxgCms.dto.CourseListDto;
import cn.yxg.yxgCms.dto.PreCourseDto;
import cn.yxg.yxgCms.entity.Course;
import cn.yxg.yxgCms.entity.PreCourse;
import cn.yxg.yxgCms.entity.User;
import cn.yxg.yxgCms.enumeration.RestResponseCode;
import cn.yxg.yxgCms.query.CourseQuery;
import cn.yxg.yxgCms.service.ClassificationService;
import cn.yxg.yxgCms.service.CourseService;
import cn.yxg.yxgCms.service.HomepageService;
import cn.yxg.yxgCms.service.PreCourseService;
import cn.yxg.yxgCms.service.PreMovieService;
import cn.yxg.yxgCms.service.SmsService;
import cn.yxg.yxgCms.service.UserService;
import cn.yxg.yxgCms.util.ResponseUtil;

@Controller
@RequestMapping("yxgCms/course")
public class PreCourseController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(PreCourseController.class);
	
	@Resource
	private UserService userServiceImpl;
	
	@Resource
	private SmsService smsServiceImpl;
	
	@Resource
	private HomepageService homepageServiceImpl;
	
	@Autowired  
    private HttpServletRequest request;
	
	@Resource
	private Properties yxgCmsConfig;
	
	@Resource
	private PreCourseService preCourseServiceImpl; 
	
	@Resource
	private CourseService courseServiceImpl; 
	
	@Resource
	private PreMovieService preMovieServiceImpl;
	
	@Resource
	private ClassificationService classificationServiceImpl; 
	
	
	

	/**
	* @author :zy
	* @Title: listPage 
	* @Description: TODO
	* @param @param model
	* @param @return     
	* @return String     
	* @throws
	 */
	@RequestMapping("listPage")
	public String listPage(ModelMap model) {
		return "site.yxgCms.course.list";
	}
	/**
	 * @author :zy
	 * @Title: infoPage 
	 * @Description: TODO
	 * @param @param model
	 * @param @return     
	 * @return String     
	 * @throws
	 */
	@RequestMapping("infoPage")
	public String infoPage(ModelMap model) {
		return "site.yxgCms.course.info";
	}
	
	/**
	 * 
	 	发布课程查询
	 * 
	 * @param cq
	 * @return
	 */
	@RequestMapping(value = "published", method = RequestMethod.GET)
	@ResponseBody
	public RestResponse findPublishedContents(CourseQuery cq) {
		logger.info("查询发布后课程参数：【" + JsonConverter.format(cq) + "】");
		RestResponse restResponse = new RestResponse();
		try {
			Page page = new Page();
			page.setIndex(cq.getPage() == null ? 0 : cq.getPage());
			if(cq.getPageSize()!=null){
				page.setSize(cq.getPageSize());
			}
			List<Course> course = courseServiceImpl.execList(cq, page);
			// TODO
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.setMessage("查询发布后课程成功！");
			restResponse.getBody().put("course", course);
			restResponse.getBody().put("page", page);
			restResponse.getBody().put("query", cq);
		} catch (Exception e) {
			e.printStackTrace();
			restResponse
			.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
			restResponse.setMessage("查询课程异常！");
		}
		return restResponse;
	}
	/**
	 * 
	 	课程查询
	 * 
	 * @param cq
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public RestResponse findContents(CourseQuery cq) {
		logger.info("查询课程参数：【" + JsonConverter.format(cq) + "】");
		RestResponse restResponse = new RestResponse();
		try {
			Page page = new Page();
			page.setIndex(cq.getPage() == null ? 0 : cq.getPage());
			if(cq.getPageSize()!=null){
				page.setSize(cq.getPageSize());
			}
			List<PreCourse> course = preCourseServiceImpl.execList(cq, page);
			// TODO
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.setMessage("查询课程成功！");
			restResponse.getBody().put("course", course);
			restResponse.getBody().put("page", page);
			restResponse.getBody().put("query", cq);
		} catch (Exception e) {
			e.printStackTrace();
			restResponse
					.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
			restResponse.setMessage("查询课程异常！");
		}
		return restResponse;
	}

	
	/**
	 * 
	 	视频删除
	 * 
	 * @param cq
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public RestResponse delete(@PathVariable int id) {
		logger.info("删除课程参数：【" + id + "】");
		RestResponse restResponse = new RestResponse();
		try {
			preCourseServiceImpl.delete(id);
			// TODO
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.setMessage("删除课程成功！");
		} catch (Exception e) {
			e.printStackTrace();
			restResponse
			.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
			restResponse.setMessage("删除课程异常！");
		}
		return restResponse;
	}

	
	/**
	 * 
	 * 审核课程
	 * 
	 * @param cq
	 * @return
	 */
	@RequestMapping(value = "{id}/{action}", method = RequestMethod.PUT)
	@ResponseBody
	public RestResponse addContent(@PathVariable int id,@PathVariable int action) {
		logger.info("审核课程参数：【" + id+","+ action + "】");
		RestResponse restResponse = new RestResponse();
		restResponse.setStatusCode(ResponseStatusCode.OK);
		restResponse.setMessage("审核课程成功！");
		try {
			PreCourse course = preCourseServiceImpl.get(id);
			if(checkCourseStatus(course,action)){
				if(action==1){//销售
					preCourseServiceImpl.execDeploy(course);
				}else if(action==2){//停售
					preCourseServiceImpl.execStop(course);
				}
				course.setStatus(action);
				preCourseServiceImpl.update(course);
			}else{
				logger.error("check course status ["+course.getStatus()+" to "+action+"] failed!");
				restResponse.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
				restResponse.setMessage("审核课程失败！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			restResponse.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
			restResponse.setMessage("审核课程异常！");
		}
		return restResponse;
	}
	private boolean checkCourseStatus(PreCourse course, int action) {
		// TODO Auto-generated method stub
		switch (course.getStatus()) {
		case 0://未审核
			if(action==2){
				return false;
			}
			break;
		case 1://销售中
			if(action==0){
				return false;
			}
			break;
		case 2://停售
			if(action==0){
				return false;
			}
			break;
		case 3://修改待审核
			if(action==2||action==0){
				return false;
			}
			break;

		default:
			break;
		} 
		return true;
	}
	/**
	 * 
	 * 新增课程
	 * 
	 * @param cq
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse addContent(@RequestBody @Valid PreCourseDto dto,BindingResult bResult) {
		logger.info("新增课程参数：【" + JsonConverter.format(dto) + "】");
		RestResponse restResponse = new RestResponse();
		if(bResult.hasErrors()){
			restResponse.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			restResponse.setMessage(bResult.getFieldError().getDefaultMessage());
			return restResponse;
		}
		try {
			if(preCourseServiceImpl.checkMovie(dto)){
				preCourseServiceImpl.execAdd(dto);
				restResponse.setStatusCode(ResponseStatusCode.OK);
				restResponse.setMessage("新增课程成功！");
			}else{
				logger.error("check movie "+JsonConverter.format(dto.getMovies())+" failed!");
				restResponse.setStatusCode(ResponseStatusCode.OK);
				restResponse.setMessage("新增课程失败！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			restResponse
			.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
			restResponse.setMessage("新增课程异常！");
		}
		return restResponse;
	}
	/**
	 * 
	 * 更新课程
	 * 
	 * @param cq
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	public RestResponse updateContent(@RequestBody @Valid PreCourseDto dto,BindingResult bResult) {
		logger.info("修改课程参数：【" + JsonConverter.format(dto) + "】");
		RestResponse restResponse = new RestResponse();
		if(bResult.hasErrors()){
			restResponse.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			restResponse.setMessage(bResult.getFieldError().getDefaultMessage());
			return restResponse;
		}
		try {
			preCourseServiceImpl.execUpdate(dto);
			restResponse.setStatusCode(ResponseStatusCode.OK);
			restResponse.setMessage("修改课程成功！");
		} catch (Exception e) {
			e.printStackTrace();
			restResponse
			.setStatusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR);
			restResponse.setMessage("修改课程异常！");
		}
		return restResponse;
	}
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="homepage",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse homepage() {
		try{
			CourseHomepageDto courseHomepageDto = courseServiceImpl.getCourseHomepage();
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), courseHomepageDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	
	@RequestMapping(value="listByCategory",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse listByCategory(@RequestParam(value="category",required=true) int category) {
		try{

			CategoryPageDto categoryPageDto = courseServiceImpl.getCategoryPage(category);
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), categoryPageDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="search",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse search(@RequestParam(value="keyword",required=false) String keyword, @RequestParam(value="borderId",required=false) Integer borderId,  @RequestParam(value="number",required=false) Integer number) {
		try{

			CourseListDto courseListDto = courseServiceImpl.getCourseList(keyword,borderId,number==null?Integer.parseInt(yxgCmsConfig.get("default.page.size").toString()):number);
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), courseListDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="list",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse list(@RequestBody @Valid CourseFilterInputDto courseFilterInputDto) {
		try{

			CourseListDto courseListDto = courseServiceImpl.getCourseListByFilter(courseFilterInputDto);
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), courseListDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="getCourseDetail",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse getCourseDetail(@RequestParam(value="course",required=true) int course) {
		try{
			User user = null;
			String tokenStr = request.getHeader("token");
			if(!StringUtils.isBlank(tokenStr)){
				user = userServiceImpl.getByToken(tokenStr);
			}
			CourseDetailDto courseDetailDto = courseServiceImpl.getCourseDetail(course,user);
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), courseDetailDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	
	@RequestMapping(value="getCourseComment",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse getCourseComment(@RequestParam(value="course",required=true) int course,@RequestParam(value="borderId",required=false) Integer borderId,  @RequestParam(value="number",required=false) Integer number) {
		try{
			
			CourseCommentListDto courseCommentListDto = courseServiceImpl.getCourseCommentList(course,borderId,number==null?Integer.parseInt(yxgCmsConfig.get("default.page.size").toString()):number);
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), courseCommentListDto);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="join",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse join(@RequestParam(value="course",required=true) int course) {
		try{
			
			User user = null;
			String tokenStr = request.getHeader("token");
			if(!StringUtils.isBlank(tokenStr)){
				user = userServiceImpl.getByToken(tokenStr);
			}
			if(user==null){
				logger.error("没找到对应该token的用户");
				return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
			}
			
			courseServiceImpl.addCourseOrder(user,course);
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), null);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	@RequestMapping(value="submitComment",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse submitComment(@RequestBody @Valid CourseCommentSubmitDto courseCommentSubmitDto) {
		try{
			
			User user = null;
			String tokenStr = request.getHeader("token");
			if(!StringUtils.isBlank(tokenStr)){
				user = userServiceImpl.getByToken(tokenStr);
			}
			if(user==null){
				logger.error("没找到对应该token的用户");
				return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
			}
			
			courseServiceImpl.addCourseComment(user,courseCommentSubmitDto.getCourse(),courseCommentSubmitDto.getContent());
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), null);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
	
	@RequestMapping(value="collect",method = RequestMethod.POST)
	@ResponseBody
	public RestResponse collect(@RequestParam(value="course",required=true) int course) {
		try{
			
			User user = null;
			String tokenStr = request.getHeader("token");
			if(!StringUtils.isBlank(tokenStr)){
				user = userServiceImpl.getByToken(tokenStr);
			}
			if(user==null){
				logger.error("没找到对应该token的用户");
				return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
			}
			
			courseServiceImpl.addCourseCollect(user,course);
			
			
			return ResponseUtil.setRestResponse(RestResponseCode.SUCCESS, RestResponseCode.SUCCESS.getName(), null);
		}catch(Exception e){
			logger.error(e.getMessage());
			return ResponseUtil.setRestResponse(RestResponseCode.INTERNERROR, RestResponseCode.INTERNERROR.getName(), null);
		}
	}
	
}
