package com.powerwin.util;

/**
 * Created by fengjinman Administrator on 2018/8/22.
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
/**
 * 使用log4j 以及自定义io 的日志工具类
 * @author Administrator
 */
public class LogUtil {

    /**
     * @param count 总共读到的数量
     * @param count_success 成功的数量
     * @param count_ignore 忽略的数量
     * @param count_fail 失败的数量
     */
    public static void write_log(int count,int count_success,int count_ignore,int count_fail){
        Logger logger = Logger.getLogger(LogUtil.class);
        String message ="从redis中读取到"+count+"条数据,成功了"+count_success+"条,忽略了"+count_ignore+"条,失败了"+count_fail+"条";
        logger.info(message);
    }
    public static void write_log(Long count,int count_success,int count_ignore,int count_fail){
        Logger logger = Logger.getLogger(LogUtil.class);
        String message ="从redis中读取到"+count+"条数据,成功了"+count_success+"条,忽略了"+count_ignore+"条,失败了"+count_fail+"条";
        logger.info(message);
    }
    /**
     * @param result 回调给媒体或者渠道的信息url+result
     */
    public static void write_log(String result){
        Logger logger = Logger.getLogger(LogUtil.class);
        logger.info(result);
    }

    /**
     * @param log_time 日志的名字--当天的时间
     * @param active_type 数据的类型
     * @param out
     * @param bout
     * @param infos 要写入文件的内容
     */
    public static void write_log(Date log_time,String active_type,FileWriter out ,BufferedWriter bout,String infos){
        String log_created = new SimpleDateFormat("yyyyMMdd").format(log_time);
        String log_subdirectories = new SimpleDateFormat("yyyyMMdd-H").format(log_time);
        String separator = File.separator;
        String directory = "DataInput_log"+separator+"infos" + separator + log_created + separator + active_type;
        String fileName = log_subdirectories + ".log";
        File f = new File(directory, fileName);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            out = new FileWriter(f, true);
            bout = new BufferedWriter(out);
            bout.write(infos);
            bout.write("\n");
            bout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bout != null) {
                try {
                    bout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
