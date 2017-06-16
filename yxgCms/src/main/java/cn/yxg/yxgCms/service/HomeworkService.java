package cn.yxg.yxgAppServer.service;

import java.util.List;

import cn.yxg.yxgAppServer.dto.DynamicListDto;
import cn.yxg.yxgAppServer.dto.HomepageDto;
import cn.yxg.yxgAppServer.dto.HomeworkListDto;
import cn.yxg.yxgAppServer.dto.IdListDto;
import cn.yxg.yxgAppServer.dto.RegistDto;
import cn.yxg.yxgAppServer.dto.StudentWorkCommentDto;
import cn.yxg.yxgAppServer.dto.SubmitStudentWorkDto;
import cn.yxg.yxgAppServer.dto.UserInfoDto;
import cn.yxg.yxgAppServer.dto.UserListDto;
import cn.yxg.yxgAppServer.dto.WechatLoginDto;
import cn.yxg.yxgAppServer.entity.Token;
import cn.yxg.yxgAppServer.entity.User;

public interface HomeworkService {

	HomeworkListDto getHomework(Integer borderId, int number);

	DynamicListDto getStudentWorksByTime(User currentUser, int homework, Integer borderId, int number);

	List<Integer> getStudentWorksIdByPraise(int homework);

	DynamicListDto getStudentWorksByIdList(User currentUser, IdListDto idListDto);

	void addStudentWork(User currentUser,
			SubmitStudentWorkDto submitStudentWorkDto);

	void addStudentWorkComment(User currentUser,
			StudentWorkCommentDto studentWorkCommentDto);

	void addStudentWorkPraise(User currentUser, int studentWork);

	boolean deleteStudentWork(User currentUser, int studentWork);

	boolean deleteStudentWorkComment(User currentUser, int studentWorkComment);


}
