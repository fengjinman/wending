package com.powerwin.dataSource;

import com.powerwin.dao.IAdDao;
import java.util.*;
import java.util.concurrent.Callable;
/**
 * Created by fengjinman Administrator on 2018/8/15.
 *
 * 检测广告的上下线次数，写入稳定性字段，5分钟运行一次
 */
public class Task implements Callable<Integer> {


    private IAdDao dao;
    private List<String> ads;

    public Task(IAdDao dao,List<String> ads) {
        this.dao = dao;
        this.ads = ads;
    }
    public Integer call() throws Exception {

        Iterator<String> iterator = ads.iterator();
        while (iterator.hasNext()){
            String adid = iterator.next();
            Map<String,Object> param = getParam(adid);
            List<Integer> count_list = dao.kkkquery(param);
            if(count_list.size()==3){
                System.out.println("count_list的长度为3---"+count_list);
                Integer current = count_list.get(0);
                Integer last = count_list.get(1);
                Integer lastandlast = count_list.get(2);
                if(current-last>5){
                    dao.queryupdate_for_stable(3,adid);
                    System.out.println("广告"+adid+"状态极不稳定");
                    if(lastandlast-last>5){
                        String reason = "禁用原因：在过去两个小时内状态不稳定";
                        dao.querycloseTheAd(adid,reason);
                        System.out.println("广告"+adid+"被禁用");
                    }
                }else if(current-last<=4&&current-last>2){
                    dao.queryupdate_for_stable(2,adid);
                    System.out.println("广告"+adid+"一般稳定");
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
