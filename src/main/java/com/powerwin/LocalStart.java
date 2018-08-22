package com.powerwin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * Created by fengjinman Administrator on 2018/7/18.
 */
public class LocalStart {
    public static void main(String[] args) {
        String[] locations = { "spring-mybatis.xml","switch_coagentquartz.xml" };
        @SuppressWarnings({ "resource", "unused" })
        ApplicationContext context = new ClassPathXmlApplicationContext(locations);
    }
}
