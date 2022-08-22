package bg.softuni.proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheableInvocationHandler implements InvocationHandler {

    private final Object realObject;
    private final Map<String,Object> caches = new ConcurrentHashMap<>();
    public CacheableInvocationHandler(Object realObject){
        this.realObject = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Cacheable cacheableAnnotation = realObject
                .getClass()
                .getMethod(method.getName(),method.getParameterTypes())
                .getAnnotation(Cacheable.class);


        if (cacheableAnnotation != null){
            String cacheId = cacheableAnnotation.value();

            if (caches.containsKey(cacheId)){
                return caches.get(cacheId);
            }else{
                var valueToCache = method.invoke(realObject,args);
                caches.put(cacheId,valueToCache);
                return valueToCache;
            }

        }
       return method.invoke(realObject,args);
    }
}
