package com.knight.dao;

import com.knight.bean.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherDao {

    Teacher getTeacherById(Integer id);

    List<Teacher> getTeacherByCondition(Teacher teacher);

    List<Teacher> getTeacherByIdIn(@Param("ids") List<Integer> ids);

    List<Teacher> getTeacherByConditionChoose(Teacher teacher);

}
