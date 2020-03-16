package com.antnest.msger.core.mapping;

import com.antnest.msger.core.annotation.Endpoint;
import com.antnest.msger.core.annotation.Mapping;
import com.antnest.msger.core.utils.ClassUtils;
import com.antnest.msger.core.endpoint.BaseEndpoint;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpringHandlerMapper implements HandlerMapper, ApplicationContextAware {

    protected String[] packageNames;
    private Map<Integer, Map<Integer, Handler>> handlerMap = new HashMap();

    public SpringHandlerMapper(String... packageNames) {
        this.packageNames = packageNames;
    }

    public Handler getHandler(Integer delimiter, Integer key) {
        Map<Integer, Handler> childMap = handlerMap.get(delimiter);
        if (null == childMap) {
            return null;
        } else {
            return childMap.get(key);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        for (String packageName : packageNames) {
            List<Class<?>> handlerClassList = ClassUtils.getClassList(packageName, Endpoint.class);

            for (Class<?> handlerClass : handlerClassList) {
                Method[] methods = handlerClass.getDeclaredMethods();
                if (methods != null) {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(Mapping.class)) {
                            Mapping annotation = method.getAnnotation(Mapping.class);
                            String desc = annotation.desc();
                            int[] types = annotation.types();
                            BaseEndpoint endpoint = (BaseEndpoint) applicationContext.getBean(handlerClass);
                            Handler value = new Handler(endpoint, method, desc);

                            Integer endpointType = endpoint.getPointType();
                            Map<Integer, Handler> childMap = handlerMap.get(endpointType);
                            if (null == childMap) {
                                childMap = new HashMap<>();
                                handlerMap.put(endpointType, childMap);
                            }

                            for (int type : types) {
                                childMap.put(type, value);
                            }
                        }
                    }
                }
            }
        }
    }
}