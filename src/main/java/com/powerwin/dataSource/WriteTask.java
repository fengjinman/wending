package com.powerwin.dataSource;

import com.powerwin.dao.IAdDao;
import com.powerwin.entity.StateLog;

import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by fengjinman Administrator on 2018/8/21.
 *
 * 查询当前小时下的广告上下线次数，写入日志当中
 */
public class WriteTask implements Callable<Integer>{


    private IAdDao dao;
    private List<String> ads;

    public WriteTask(IAdDao dao,List<String> ads) {
        this.dao = dao;
        this.ads = ads;
    }
    public Integer call() throws Exception {
        List<Integer> change_counts = dao.queryStateList(ads);
        List<StateLog> logs = new ArrayList<StateLog>();
        for(int i=0;i<ads.size();i++){
            String adid = ads.get(i);
            Integer change_count = change_counts.get(i);
            StateLog log = setValue(adid,change_count);
            logs.add(log);
        }
        dao.insertTogether(logs);
        return 1;
    }

    public StateLog setValue(String adid,Integer change_count){
        long mts = System.currentTimeMillis();
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(mts);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int create = month*1000000 + day*10000+hour*100+min;
        StateLog log = new StateLog();
        log.setAdid(adid);
        log.setChange_count(change_count);
        log.setYear(year);
        log.setMonth(month);
        log.setDay(day);
        log.setHour(hour);
        log.setCreate(create);
        return log;
    }

}
