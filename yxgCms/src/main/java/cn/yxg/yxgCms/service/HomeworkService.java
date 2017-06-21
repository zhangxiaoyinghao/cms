package cn.yxg.yxgCms.service;

import java.util.List;

import cn.yxg.yxgCms.dto.DynamicListDto;
import cn.yxg.yxgCms.dto.HomepageDto;
import cn.yxg.yxgCms.dto.HomeworkListDto;
import cn.yxg.yxgCms.dto.IdListDto;
import cn.yxg.yxgCms.dto.RegistDto;
import cn.yxg.yxgCms.dto.StudentWorkCommentDto;
import cn.yxg.yxgCms.dto.SubmitStudentWorkDto;
import cn.yxg.yxgCms.dto.UserInfoDto;
import cn.yxg.yxgCms.dto.UserListDto;
import cn.yxg.yxgCms.dto.WechatLoginDto;
import cn.yxg.yxgCms.entity.Token;
import cn.yxg.yxgCms.entity.User;

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
