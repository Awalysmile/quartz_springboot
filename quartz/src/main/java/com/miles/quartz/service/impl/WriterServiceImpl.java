package com.miles.quartz.service.impl;

import com.miles.quartz.service.IWriterService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName WriterServiceImpl
 * @Description TODO
 * @Author Miles
 * @Date 2019/8/4 13:47
 * @Version 1.0
 */
@Service("writerServiceImpl") // 标识一下bean名字
public class WriterServiceImpl implements IWriterService {

//    @Scheduled(cron = "0/30 * * * * ?")

    public void write() {
        System.out.println("开始write");
        System.out.println(new Date() + "执行了很复杂的write业务逻辑");
        System.out.println("结束write");
    }
}
