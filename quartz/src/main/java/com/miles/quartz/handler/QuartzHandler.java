package com.miles.quartz.handler;

import com.miles.quartz.entity.ScheduleJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static com.miles.quartz.util.SpringUtil.getBeanByClzz;
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
        try {
            ScheduleJob job = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
            // SpringUtil工具类取到bean对象并通反射调用需要执行的方法
            Optional.ofNullable(Optional.ofNullable(getBeanByName(job.getSpringId()))
                    .orElse(getBeanByClzz(Class.forName(job.getClassName())))).ifPresent(
                    c -> {
                        try {
                            (c.getClass().getMethod(job.getMethodName())).invoke(c);
                        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                            System.out.println(e.getMessage());
                        }
                    }
            );

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());

        }
    }
}
