package org.hipeday.sphere.spring.scanner;

import org.hipeday.sphere.core.annotation.SphereClient;
import org.hipeday.sphere.core.logging.SphereLogger;
import org.hipeday.sphere.core.util.ArrayUtils;
import org.hipeday.sphere.spring.beans.ClientFactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

/**
 * class path接口客户端扫描器
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class ClassPathClientScanner extends ClassPathBeanDefinitionScanner {

    private final static Class<?> CLIENT_FACTORY_BEAN_CLASS = ClientFactoryBean.class;
    private static final SphereLogger log = SphereLogger.getLogger(ClassPathClientScanner.class);

    public ClassPathClientScanner(BeanDefinitionRegistry registry) {
        // 不使用默认Class扫描拦截器
        super(registry, false);

        registerFilters();
    }

    /**
     * 校验当前 Class 实例是否符合 Sphere 接口客户端实例注册条件
     * 目前的注册条件是必须为 {@code interface} 并且不能是 {@code final}修饰
     * 同时还需要使用{@link SphereClient}注解标识才会被识别为 Sphere 客户端
     * 如果符合条件返回 {@code true} 反之返回 {@code false}
     *
     * @param reader 元数据阅读器
     * @param readerFactory 元数据阅读器工厂
     * @return 当前元数据(Class)是否符合 Sphere 客户端实例条件
     */
    private boolean interfaceFilter(MetadataReader reader, MetadataReaderFactory readerFactory) {
        ClassMetadata classMetadata = reader.getClassMetadata();

        // 如果class对象不是接口或者使用final修饰则不注册为Sphere接口客户端
        if (!classMetadata.isInterface() || classMetadata.isFinal()) {
            return false;
        }

        String[] superClassNames = reader.getClassMetadata().getInterfaceNames();
        boolean hasSphereClient = false;
        if (ArrayUtils.isNotEmpty(superClassNames)) {
            for (String superClassName : superClassNames) {
                try {
                    MetadataReader superMetadataReader = readerFactory.getMetadataReader(superClassName);
                    hasSphereClient = interfaceFilter(superMetadataReader, readerFactory);
                } catch (IOException ignored) {}
                if (hasSphereClient) {
                    return true;
                }
            }
        }

        AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();
        for (String annotationType : annotationMetadata.getAnnotationTypes()) {
            if (SphereClient.class.getName().equals(annotationType)) {
                return true;
            }
        }

        return false;
    }

    public void registerFilters() {
        addIncludeFilter(this::interfaceFilter);
        addExcludeFilter((metadataReader, metadataReaderFactory) -> {
            String className = metadataReader.getClassMetadata().getClassName();
            return className.endsWith("package-info");
        });
    }

    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        GenericBeanDefinition definition;
        for (BeanDefinitionHolder holder : beanDefinitions) {
            definition = (GenericBeanDefinition) holder.getBeanDefinition();
            assert getRegistry() != null;
            registerProxyBean(definition, getRegistry());
            log.info("[Sphere] Created Sphere Client Bean with name '{}' and Proxy of '{}' client interface", holder.getBeanName(), definition.getBeanClassName());
        }
    }

    private void registerProxyBean(GenericBeanDefinition beanDefinition, BeanDefinitionRegistry registry) {
        beanDefinition.getPropertyValues().add("clientInterface", beanDefinition.getBeanClassName());
        beanDefinition.setBeanClass(CLIENT_FACTORY_BEAN_CLASS);
        beanDefinition.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
        beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        registry.registerBeanDefinition(Objects.requireNonNull(beanDefinition.getBeanClassName()), beanDefinition);
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        if (!beanDefinitionHolders.isEmpty()) {
            processBeanDefinitions(beanDefinitionHolders);
        }
        return beanDefinitionHolders;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

}
