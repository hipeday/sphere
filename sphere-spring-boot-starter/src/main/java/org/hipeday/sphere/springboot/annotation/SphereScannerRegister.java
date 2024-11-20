package org.hipeday.sphere.springboot.annotation;

import jakarta.annotation.Nonnull;
import org.hipeday.sphere.core.util.StringUtils;
import org.hipeday.sphere.spring.scanner.ClassPathClientScanner;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Sphere 客户端扫描注册器
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class SphereScannerRegister implements ImportBeanDefinitionRegistrar {

    /**
     * 需要扫描的包路径
     */
    private static final List<String> scanPackages = new ArrayList<>();

    private BeanFactory beanFactory;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, @Nonnull BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(SphereScan.class.getName());
        // 先扫描 @SphereScan 注解中定义的包
        if (annotationAttributes != null) {
            AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(annotationAttributes);
            for (String pkg : annoAttrs.getStringArray("value")) {
                if (StringUtils.hasText(pkg)) {
                    scanPackages.add(pkg);
                }
            }

            for (String pkg : annoAttrs.getStringArray("basePackages")) {
                if (StringUtils.hasText(pkg)) {
                    scanPackages.add(pkg);
                }
            }

            for (Class<?> basePackageClasses : annoAttrs.getClassArray("basePackageClasses")) {
                scanPackages.add(ClassUtils.getPackageName(basePackageClasses));
            }

            // 是否扫描整个项目
            if (annoAttrs.getBoolean("globalScan")) {
                scanPackages.addAll(AutoConfigurationPackages.get(beanFactory));
            }

            ClassPathClientScanner scanner = new ClassPathClientScanner(registry);
            scanner.registerFilters();
            scanner.doScan(Objects.requireNonNull(StringUtils.toStringArray(scanPackages)));
        }
    }

    public static List<String> getScanPackages() {
        return scanPackages;
    }

    public static void clearScanPackages() {
        scanPackages.clear();
    }
}
