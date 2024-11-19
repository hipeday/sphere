package org.hipeday.sphere.core.proxy;

import org.hipeday.sphere.core.config.SphereConfiguration;
import org.hipeday.sphere.core.reflection.SphereMethod;
import org.hipeday.sphere.core.util.ArrayUtils;
import org.hipeday.sphere.core.util.MethodHandlesUtils;

import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 接口代理处理器
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class InterfaceProxyHandler<T> implements InvocationHandler {

    private final Map<Method, SphereMethod<T>> sphereMethodCache = new ConcurrentHashMap<>();

    private final List<Annotation> annotations = new LinkedList<>();

    private final MethodHandles.Lookup defaultMethodLookup;

    /**
     * 配置
     */
    private final SphereConfiguration configuration;

    /**
     * 接口类
     */
    private final Class<T> interfaceClass;

    /**
     * 构造方法
     *
     * @param configuration 配置
     * @param interfaceClass 接口类
     */
    public InterfaceProxyHandler(SphereConfiguration configuration, Class<T> interfaceClass) {
        this.configuration = configuration;
        this.interfaceClass = interfaceClass;

        // 初始化
        init();

        defaultMethodLookup = MethodHandlesUtils.lookup(interfaceClass);
    }

    private void init() {

        // 初始化注解
        initAnnotations();

        // 初始化方法
        initMethods();
    }

    private void initMethods() {
        initMethods(interfaceClass);
    }

    public void initMethods(Class<?> clazz) {
        Class<?>[] superClasses = clazz.getInterfaces();
        if (ArrayUtils.isNotEmpty(superClasses)) {
            for (Class<?> superClass : superClasses) {
                initMethods(superClass);
            }
        }
        Method[] methods = clazz.getDeclaredMethods();

        if (ArrayUtils.isNotEmpty(methods)) {
            for (Method method : methods) {
                if (method.isDefault()) {
                    continue;
                }
                SphereMethod<T> sphereMethod = new SphereMethod<>(this, configuration, method, interfaceClass);
                sphereMethodCache.put(method, sphereMethod);
            }
        }
    }

    public void initAnnotations() {
        Class<?>[] superClasses = interfaceClass.getInterfaces();
        if (ArrayUtils.isNotEmpty(superClasses)) {
            for (Class<?> superClass : superClasses) {
                initAnnotations(superClass);
            }
        }
    }

    public void initAnnotations(Class<?> clazz) {
        Class<?>[] superClasses = clazz.getInterfaces();
        if (ArrayUtils.isNotEmpty(superClasses)) {
            for (Class<?> superClass : superClasses) {
                initAnnotations(superClass);
            }
        }
        Annotation[] annotations = clazz.getAnnotations();
        if (ArrayUtils.isNotEmpty(annotations)) {
            this.annotations.addAll(Arrays.asList(annotations));
        }
    }



    /**
     *
     * @param proxy 调用该方法的代理实例
     *
     * @param method 与代理实例上调用的接口方法相对应的 {@code Method} 实例。{@code Method}
     *               对象的声明类将是声明该方法的接口，
     *               该接口可以是代理类通过其继承该方法的代理接口的超接口。
     *
     * @param args 包含在代理实例上的方法调用中传递的参数值的对象数组，
     *             如果接口方法不带参数，则为 {@code null}。
     *             基本类型的参数包装在适当的基本包装类的实例中，
     *             例如 {@code java.lang.Integer} 或 {@code java.lang.Boolean}。
     *
     * @return 从方法调用返回的值
     * @throws Throwable 方法调用抛出的异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String methodName = method.getName();
        if (method.isDefault()) {
            return invokeDefaultMethod(proxy, method, args);
        }

        SphereMethod<T> sphereMethod = sphereMethodCache.get(method);

        if (sphereMethod == null) {
            // TODO 执行一些默认方法
            throw new NoSuchMethodException("Method not found: " + methodName);
        }

        // 调用函数
        return sphereMethod.invoke(args);
    }

    private Object invokeDefaultMethod(Object proxy, Method method, Object[] args)
            throws Throwable {
        return defaultMethodLookup.findSpecial(interfaceClass, method.getName(), MethodType.methodType(method.getReturnType(),
                        method.getParameterTypes()), interfaceClass)
                .bindTo(proxy).invokeWithArguments(args);
    }

}
