package com.powerwin.dataSource;


import com.powerwin.dao.IAdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by fengjinman Administrator on 2018/7/23.
 */
@Service
public class BootServer {

    @Autowired
    IAdDao dao;

    public void main(){

        List<String> strings = dao.queryAdList();
        for(int i=0;i<strings.size();i++){
            int state = dao.queryStatusById(strings.get(i));
            long mts = System.currentTimeMillis();
            Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(mts);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH)+1;
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);
            int create = month*1000000 + day*10000+hour*100+min;
            Map<String,Object> param = new HashMap<String, Object>();
            param.put("adid",strings.get(i));
            param.put("state",state);
            param.put("year",year);
            param.put("month",month);
            param.put("day",day);
            param.put("hour",hour);
            param.put("create",create);
            dao.insert_state_log(param);
        }
    }
}
