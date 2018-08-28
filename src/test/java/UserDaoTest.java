/**
 * Created by fengjinman Administrator on 2018/7/10.
 */

import org.springframework.beans.factory.annotation.Autowired;
import com.powerwin.dao.IAdDao;
import org.junit.Test;
import java.util.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class UserDaoTest {

//    @Autowired
//    IAdDao adDao;

    @Autowired
    IAdDao dao;

    @Test
    public void test19(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("year",2018);
        map.put("month",8);
        map.put("day",28);
        map.put("adid",923423);
        map.put("last_1_hour",8);
        List<Integer> integers = dao.kselectLastCount(map);
        System.out.println(integers);
    }


    @Test
    public void test2(){
//        List<String> strings = adDao.queryAdList();
//        for(int a = 0;a<strings.size();a++){
//            long mts = System.currentTimeMillis();
//            Calendar cal = new GregorianCalendar();
//            cal.setTimeInMillis(mts);
//            int year = cal.get(Calendar.YEAR);
//            int month = cal.get(Calendar.MONTH)+1;
//            int day = cal.get(Calendar.DAY_OF_MONTH);
//            int hour = cal.get(Calendar.HOUR_OF_DAY);
//            int min = cal.get(Calendar.MINUTE);
//            int current = month*1000000 + day*10000+hour*100+min;
//            int last_1_hour = month*1000000 + day*10000+(hour-1)*100+min;
//            int last_2_hour = month*1000000 + day*10000+(hour-2)*100+min;
//            Map<String,Object> param = new HashMap<String,Object>();
//            String adid = strings.get(a);
//            param.put("year",year);
//            param.put("month",month);
//            param.put("day",day);
//            param.put("hour",hour);
//            param.put("min",min);
//            param.put("current",current);
//            param.put("last_1_hour",last_1_hour);
//            param.put("adid",adid);
//            param.put("last_2_hour",last_2_hour);
//            //前一小时
//            List<Integer> states = adDao.kkkqueryLast1HourStateList(param);
//            int change_count = 0;
//
//            for(int i = 0;i<states.size()-1;i++){
//                int old = states.get(i);
//                int next = states.get(i+1);
//                if(old != next){
//                    change_count += 1;
//                }
//            }
//
//            if(change_count>5){
//                System.out.println("ad : 此订单在"+last_1_hour+"到"+current+"时间段内表现极不稳定");
//            }
//            //前前一小时
//            List<Integer> states2 = adDao.kkkqueryLast2HourStateList(param);
//            int change_count2 = 0;
//
//            for(int i = 0;i<states2.size()-1;i++){
//                int old = states2.get(i);
//                int next = states2.get(i+1);
//                if(old != next){
//                    change_count2 += 1;
//                }
//            }
//
//            if(change_count2>5){
//                System.out.println("ad : 此订单在"+last_2_hour+"到"+last_1_hour+"时间段内表现极不稳定");
//            }
//
//            //如果两个都为极不稳定  在集合中删除这个广告   禁用此订单
//            if(change_count > 5 && change_count2 >5){
//                System.out.println("成功！");
////            all_ads.remove(adTotal);
////            iAdDao.closeTheAd(adid);
//            }
//        }

    }


    @Test
    public void test1(){
//        long mts = System.currentTimeMillis();
//        Calendar cal = new GregorianCalendar();
//        cal.setTimeInMillis(mts);
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH)+1;
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        int hour = cal.get(Calendar.HOUR_OF_DAY);
//        int create = year*1000000 + month*10000 + day*100+hour;
//        Map<String,Object> param = new HashMap<String, Object>();
//        param.put("adid","1013220734");
//        param.put("state",4);
//        param.put("year",year);
//        param.put("month",month);
//        param.put("day",day);
//        param.put("hour",hour);
//        param.put("create",create);
//        int i = adDao.insert_state_log(param);
//        System.out.println(i);
    }
    @Test
    public void test3(){
//        List<String> strings = adDao.queryAdList();
//        System.out.println(strings);


    }

}