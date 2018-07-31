package com.powerwin.dao;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IAdDao {

	int queryStatusById(@Param("adid") String adid);

	List<String> queryAdList();

	int insert_state_log(@Param("param") Map<String,Object> param);









	List<Integer> kkkqueryLast1HourStateList(@Param("param") Map<String,Object> param);

	List<Integer> kkkqueryLast2HourStateList(@Param("param") Map<String,Object> param);

	int querycloseTheAd(@Param("adid") String adid);

}
