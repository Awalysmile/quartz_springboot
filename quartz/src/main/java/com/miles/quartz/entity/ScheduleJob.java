package com.miles.quartz.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName ScheduleJob
 * @Description Vo
 * @Author Miles
 * @Date 2019/8/4 12:41
 * @Version 1.0
 */
@Data
@Entity
@Table
public class ScheduleJob {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; 
    
    private String jobName; // 任务名
    
    private String cron;    //  cron 表达式
    
    private String springId;    // 找到bean对象的名称
    
    private String methodName;    // 需要被调度方法名

    private  String className;  // 类名

    private Integer status;    // 状态
}
