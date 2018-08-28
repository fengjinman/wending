package com.powerwin.dataSource;

import com.powerwin.dao.IAdDao;
import org.apache.log4j.Logger;
import java.util.*;
import java.util.concurrent.Callable;
/**
 * Created by fengjinman Administrator on 2018/8/15.
 * 检测广告的上下线次数，写入稳定性字段，5分钟运行一次
 */
public class Task implements Callable<Integer> {

    private Logger log  = Logger.getLogger(Task.class);


    private IAdDao dao;
    private List<String> ads;

    public Task(IAdDao dao,List<String> ads) {
        this.dao = dao;
        this.ads = ads;
    }
    public Integer call() throws Exception {

        Iterator<String> iterator = ads.iterator();
        while (iterator.hasNext()){
            synchronized (this){
                String adid = iterator.next();

                Integer change_count = dao.queryChangeCount(adid);
                if(change_count<=2){
                    log.info("查询到当前广告的change_count还小于3，结束稳定性测试");
                    continue;
                }
                Map<String,Object> param = getParam(adid);
                List<Integer> currentCounts = null;
                List<Integer> LastCounts = null;
                List<Integer> Last2Counts = null;
                Integer currentCount = 0;
                Integer LastCount = 0;
                Integer Last2Count = 0;
                try {
                    currentCounts = dao.kselectCurrentCount(param);
                    LastCounts = dao.kselectLastCount(param);
                    Last2Counts = dao.kselectLast2Count(param);
                    if(currentCounts==null||currentCounts.size()==0){
                        continue;
                    }else{
                        currentCount = currentCounts.get(0);
                        log.info("查询得到的currentCount = "+currentCount);
                    }
                    if(LastCounts!=null&&LastCounts.size()!=0){
                        LastCount = LastCounts.get(0);
                        log.info("查询得到的LastCount = "+LastCount);
                    }else{
                        if(currentCount>2){
                            dao.queryupdate_for_stable(2,adid);
                            log.info("上下线的次数介于2~4次之间，修改广告"+adid+",stability = 2");
                            continue;
                        }else if(currentCount>5){
                            dao.queryupdate_for_stable(3,adid);
                            log.info("过去一小时上下线超过五次，修改广告"+adid+",stability = 3");
                            continue;
                        }
                    }
                    if(Last2Counts!=null&&Last2Counts.size()!=0){
                        Last2Count = Last2Counts.get(0);
                        log.info("查询得到的Last2Count = "+Last2Count);
                    }else{
                        if(currentCount-LastCount>2){
                            dao.queryupdate_for_stable(2,adid);
                            log.info("上下线的次数介于2~4次之间，修改广告"+adid+",stability = 2");
                            continue;
                        }else if(currentCount-LastCount>5){
                            dao.queryupdate_for_stable(3,adid);
                            log.info("过去一小时上下线超过五次，修改广告"+adid+",stability = 3");
                            continue;
                        }else{
                            log.info("没有查询到过去第二个小时的上下线记录，没有满足的条件，判断提前结束");
                            continue;
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("运行时发生异常："+e);
                    continue;
                }
                if(currentCount!=null&&LastCount!=null&&Last2Count!=null){
                    if(currentCount>=LastCount&&LastCount>=Last2Count){
                        log.info("查询结果依次为---"+currentCount+","+LastCount+","+Last2Count+",广告id = "+adid);
                        if(currentCount-LastCount>5){
                            dao.queryupdate_for_stable(3,adid);
                            log.info("过去一小时上下线超过五次，修改广告"+adid+",stability = 3");
                            if(LastCount-Last2Count>5){
                                String reason = "禁用原因：在过去两个小时内状态不稳定";
                                dao.querycloseTheAd(adid,reason);
                                log.info("广告"+adid+"被禁用");
                            }
                        }else if(currentCount-LastCount<=4&&currentCount-LastCount>2){
                            dao.queryupdate_for_stable(2,adid);
                            log.info("上下线的次数介于2~4次之间，修改广告"+adid+",stability = 2");
                        }
                    }else{
                        log.info("查询得到的上线下次数数值大小有误，currentCount="+currentCount+",LastCount="+LastCount+",Last2Count="+Last2Count);
                        continue;
                    }
                }
            }
        }
        return 1;
    }

    /**
     * 封装查询指定之间段状态的参数
     * @param adid
     * @return
     */
    public Map<String,Object> getParam(String adid){
        long mts = System.currentTimeMillis();
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(mts);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int last_1_hour = hour-1;
        int last_2_hour = hour-2;
        if(hour==0){
            last_1_hour = 23;
            last_2_hour = 22;
        }else if(hour ==1){
            last_1_hour =24;
            last_2_hour =23;
        }else if(hour == 2){
            last_1_hour = 1;
            last_2_hour = 24;
        }
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("year",year);
        param.put("month",month);
        param.put("day",day);
        param.put("hour",hour);
        param.put("last_1_hour",last_1_hour);
        param.put("last_2_hour",last_2_hour);
        param.put("adid",adid);
        return param;
    }
}
