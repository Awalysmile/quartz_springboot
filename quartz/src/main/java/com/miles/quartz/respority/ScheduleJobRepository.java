package com.miles.quartz.respority;

import com.miles.quartz.entity.ScheduleJob;

import java.util.List;

public interface ScheduleJobRepository extends BaseRepository<ScheduleJob, Long> {

    List<ScheduleJob> findAllByStatus(Integer status);

//    List<ScheduleJob> findAllByStatusAndClassName(String status,String classname);

//    List<ScheduleJob> findAllByClassName(String classname);

//    List<ScheduleJob> findAllByCron(String cron);

}
