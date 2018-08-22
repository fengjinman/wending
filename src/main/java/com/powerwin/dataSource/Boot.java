package com.powerwin.dataSource;

import com.powerwin.dao.IAdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Created by fengjinman Administrator on 2018/8/15.
 */
@Service
public class Boot {

    @Autowired
    private IAdDao dao;


    public void main() {
        try {

            ExecutorService service = Executors.newCachedThreadPool();
            ExecutorCompletionService<Integer> s = new ExecutorCompletionService<Integer>(service);

            List<String> ads = dao.queryAdList();
            long startTime = System.currentTimeMillis();
            int count = 0;
            while(ads.size()>10000){
                Iterator<String> iterator = ads.iterator();
                List<String> temp = new ArrayList<String>();
                while(iterator.hasNext()){
                    temp.add(iterator.next());
                    iterator.remove();
                    if(temp.size()==10000){
                        break;
                    }
                }
                Task task = new Task(dao,temp);
                s.submit(task);
                count += 1;
            }
            Task task = new Task(dao,ads);
            s.submit(task);
            count += 1;

            int total =0;
            for(int i=0;i<count;i++){
                total += s.take().get();
            }
            if(total == count){
                System.out.println("提交了"+count+"个任务，"+(count-1)+"个任务处理10000条广告,1个任务处理"+ads.size()+"条广告");
                long endTime = System.currentTimeMillis();
                System.out.println("耗时 : " + (endTime - startTime) / 1000);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }
}
