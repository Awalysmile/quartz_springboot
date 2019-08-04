package com.miles.quartz.service.impl;

import com.miles.quartz.service.IReaderService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName ReaderServiceImpl
 * @Description TODO
 * @Author Miles
 * @Date 2019/8/4 13:44
 * @Version 1.0
 */
@Service("readerServiceImpl")
public class ReaderServiceImpl implements IReaderService {

//    @Scheduled(cron = "0/5 * * * * ?")
    @Override
    public void reading() {
        System.out.println("开始reading");
        System.out.println(new Date() + "执行了很复杂的reader业务逻辑");
        System.out.println("结束reading");
    }
}
