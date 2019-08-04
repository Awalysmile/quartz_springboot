package com.miles.quartz.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @ClassName SpringUtil
 * @Description 从bean工厂拿到bean对象工具类
 * @Author Miles
 * @Date 2019/8/4 12:58
 * @Version 1.0
 */
@Component
public class SpringUtil implements BeanFactoryPostProcessor {

    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory listableBeanFactory) throws BeansException {
        Optional.ofNullable(listableBeanFactory).ifPresent(c -> beanFactory = c);
    }

    public static Object getBeanByName(String name) {
        return beanFactory.getBean(name);
    }
    public static <E> E getBeanByClazz(Class<E> eClass) {
        return beanFactory.getBean(eClass);
    }
}
