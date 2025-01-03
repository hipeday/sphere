package org.hipeday.sphere.core.proxy;

import org.hipeday.sphere.core.context.ApplicationContext;
import org.hipeday.sphere.core.reflection.Function;
import org.hipeday.sphere.core.registry.support.FunctionRegistry;
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

/**
 * 接口代理处理器
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class InterfaceProxyHandler<T> implements InvocationHandler {

    /**
     * 接口类
     */
    private final Class<T> interfaceClass;
    private final FunctionRegistry functionRegistry;
    private final List<Annotation> annotations = new LinkedList<>();
    private final MethodHandles.Lookup defaultMethodLookup;


    /**
     * 构造方法
     *
     * @param interfaceClass 接口类
     */
    public InterfaceProxyHandler(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
        ApplicationContext applicationContext = ApplicationContext.getApplicationContext();
        functionRegistry = applicationContext.getFunctionRegistry();

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
                boolean registered = functionRegistry.isRegistered(method);
                if (!registered) {
                    Function<T> function = new Function<>(method, interfaceClass);
                    functionRegistry.register(method, function);
                }
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

        Function<?> function = functionRegistry.getInstance(method);

        if (function == null) {
            // TODO 执行一些默认方法
            throw new NoSuchMethodException("Method not found: " + methodName);
        }

        // 调用函数
        return function.invoke(args);
    }

    private Object invokeDefaultMethod(Object proxy, Method method, Object[] args)
            throws Throwable {
        return defaultMethodLookup.findSpecial(interfaceClass, method.getName(), MethodType.methodType(method.getReturnType(),
                        method.getParameterTypes()), interfaceClass)
                .bindTo(proxy).invokeWithArguments(args);
    }

}
