package com.powerwin.dao;

import com.powerwin.entity.StateLog;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface IAdDao {

	int queryStatusById(@Param("adid") String adid);

	List<String> queryAdList();

	int insert_state_log(@Param("param") Map<String,Object> param);

	List<Integer> queryStateList(List<String> ads);

	void insertTogether(List<StateLog> logs);

	int querycloseTheAd(@Param("adid") String adid,@Param("refuse_reason")String refuse_reason);

	void queryupdatead3(List<String> ads);

	void queryupdatead2(List<String> ads);

	void queryupdatead1(List<String> ads);

	void disableadbylist(@Param("ads")List<String> ads,@Param("reason") String reason);

	void queryupdate_for_stable(@Param("stable") int stable,@Param("adid")String adid);

	List<Integer> kkkqueryLast1HourStateList(@Param("param") Map<String,Object> param);

	List<Integer> kkkqueryLast2HourStateList(@Param("param") Map<String,Object> param);

	int querycloseTheAd(@Param("adid") String adid);

	List<List<Integer>> kqueryall(Map<String,Object> param);

	List<List<Integer>> kqueryall2(Map<String,Object> param);

	List<String> queryAdList5();

	List<Integer> kkkquery(@Param("param")Map<String,Object> param);

}
