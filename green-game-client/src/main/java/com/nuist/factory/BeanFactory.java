package com.nuist.factory;

import com.nuist.exception.MyException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BeanFactory {

    private static BeanFactory instance = new BeanFactory();

    private Properties properties = new Properties();

    private BeanFactory() {
        InputStream is = BeanFactory.class
                .getClassLoader()
                .getResourceAsStream("factory.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            throw new MyException("Load properties happen io exception!");
        }
    }

    public static BeanFactory getInstance() {
        if (instance == null) {
            instance = new BeanFactory();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> clazz) {
        String interfaceName = clazz.getSimpleName();
        String className = properties.getProperty(interfaceName);
        try {
            return (T) Class.forName(className).newInstance();
        } catch (Exception e) {
            throw new MyException("Can not find service!");
        }
    }
}
