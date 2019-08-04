package com.miles.quartz.service.impl;

import com.miles.quartz.entity.ScheduleJob;
import com.miles.quartz.handler.QuartzHandler;
import com.miles.quartz.respority.ScheduleJobRepository;
import com.miles.quartz.service.IScheduleService;
import org.apache.commons.collections4.CollectionUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class ScheduleServiceIml implements IScheduleService {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private ScheduleJobRepository scheduleJobRepository;

    @Override
    public void scheduleTask() {
        // 创建两个job来模拟
        ArrayList<ScheduleJob> list = new ArrayList<>();
        ScheduleJob readerJob = new ScheduleJob();
        readerJob.setId(1);
        readerJob.setClassName(ReaderServiceImpl.class.getName());
        readerJob.setJobName(ReaderServiceImpl.class.getName());
        readerJob.setStatus(1);
        readerJob.setSpringId("readerServiceImpl");
        readerJob.setMethodName("reading");
        readerJob.setCron("0/10 * * * * ?");

        ScheduleJob writerJob = new ScheduleJob();
        writerJob.setId(2);
        writerJob.setClassName(WriterServiceImpl.class.getName());
        writerJob.setJobName(WriterServiceImpl.class.getName());
        writerJob.setStatus(1);
        writerJob.setSpringId("writerServiceImpl");
        writerJob.setMethodName("write");
        writerJob.setCron("0/5 * * * * ?");
        list.add(writerJob);
        list.add(readerJob);
        List<ScheduleJob> allJobs = scheduleJobRepository.findAllByStatus(1);
        if (CollectionUtils.isEmpty(allJobs)) {
            allJobs = list;
        }
        allJobs.forEach(this::execute);
    }

    private void execute(ScheduleJob job) {
        try {
            // 搞一个调度器
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            // 给TriggerKey起个名字
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName());
            // 根据cron表达式拿到调度构造器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
            // 建立触发
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                    .withSchedule(cronScheduleBuilder)
                    .withDescription(Thread.currentThread().getName() + "建立触发")
                    .build();

            // 添加作业
            JobKey jobKey = JobKey.jobKey(job.getJobName());

            // 建立作业
            JobDetail jobDetail = JobBuilder.newJob(QuartzHandler.class)
                    .withIdentity(jobKey)
                    .build();

            // 把数据放入JobDataMap中
            jobDetail.getJobDataMap().put("scheduleJob", job);

            // 终于调度了
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            System.out.println(e.getMessage());
        }
    }
}