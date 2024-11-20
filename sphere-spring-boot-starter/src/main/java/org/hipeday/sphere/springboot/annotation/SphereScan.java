package org.hipeday.sphere.springboot.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Sphere 客户端扫描注解
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({SphereScannerRegister.class})
public @interface SphereScan {

    String[] value() default {};

    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};

    /**
     * 是否开启全局扫描，即为扫描整个项目
     *
     * @return 是否开启全局扫描
     */
    boolean globalScan() default false;

}
