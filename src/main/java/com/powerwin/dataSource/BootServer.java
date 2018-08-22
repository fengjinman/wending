package com.powerwin.dataSource;

import com.powerwin.dao.IAdDao;
import com.powerwin.entity.StateLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Created by fengjinman Administrator on 2018/7/23.
 */
@Service
public class BootServer {

    @Autowired
    IAdDao dao;

    private Logger log = LoggerFactory.getLogger(BootServer.class);

    public void main(){

        System.out.println("程序开始，处理开启广告");
        List<String> strings = dao.queryAdList();
        ExecutorService service = Executors.newCachedThreadPool();
        ExecutorCompletionService<Integer> s = new ExecutorCompletionService<Integer>(service);

        long startTime = System.currentTimeMillis();
        int count = 0;
        while(strings.size()>10000){
            Iterator<String> it = strings.iterator();
            List<String> temp = new ArrayList<String>();
            while(it.hasNext()){
                temp.add(it.next());
                it.remove();
                if(temp.size()==10000){
                    break;
                }
            }
            WriteTask task = new WriteTask(dao,temp);
            s.submit(task);
            count += 1;
        }
        WriteTask task = new WriteTask(dao,strings);
        s.submit(task);
        count += 1;

        int total = 0;
        try {
            for(int i=0;i<count;i++){
                total += s.take().get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(total==count){
            System.out.println("提交了"+count+"个任务，"+(count-1)+"个任务处理10000条广告,1个任务处理"+strings.size()+"条广告");
            long endTime = System.currentTimeMillis();
            System.out.println("耗时 : " + (endTime - startTime) / 1000);
            System.out.println("程序结束");
        }
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