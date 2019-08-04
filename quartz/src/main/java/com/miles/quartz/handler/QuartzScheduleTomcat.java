package com.miles.quartz.handler;

import com.miles.quartz.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName QuartzHandlerTomCat
 * @Description TODO
 * @Author Miles
 * @Date 2019/8/4 17:47
 * @Version 1.0
 */
@Component
public class QuartzScheduleTomcat implements CommandLineRunner {

    @Autowired
    private IScheduleService service;

    @Override
    public void run(String... args) {
        System.out.println("开始调度");
        service.scheduleTask();
        System.out.println("结束调度");
    }
//    CommandLineRunner commandLineRunner = c -> service.scheduleTask();
}
