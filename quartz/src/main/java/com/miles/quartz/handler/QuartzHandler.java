package com.miles.quartz.handler;

import com.miles.quartz.entity.ScheduleJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.miles.quartz.util.SpringUtil.getBeanByClazz;
import static com.miles.quartz.util.SpringUtil.getBeanByName;


/**
 * @ClassName handler
 * @Description TODO
 * @Author Miles
 * @Date 2019/8/4 12:55
 * @Version 1.0
 */
@Component
public class QuartzHandler implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        Object object;
        Method method = null;
        try {
            ScheduleJob job = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
            // SpringUtil工具类取到bean对象并通反射调用需要执行的方法
            object = getBeanByName(job.getSpringId()) != null
                    ? getBeanByName(job.getSpringId())
                    : getBeanByClazz(Class.forName(job.getClassName()));
            if (object != null) {
                method = object.getClass().getMethod(job.getMethodName());
            }
            if (method != null) {
                method.setAccessible(true);
                method.invoke(object);
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
    }
}
