package by.bsuir.mycoolstore.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The {@code JpaConfig} class provides configuration for JPA (Java Persistence API) in the application.
 */
@Configuration
@EnableJpaRepositories(basePackages = "by.bsuir.mycoolstore")
@EnableTransactionManagement
public class JpaConfig {
    /**
     * Creates and configures the entity manager factory bean.
     *
     * @return The configured {@link LocalEntityManagerFactoryBean}.
     */
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        var factoryBean = new LocalEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("myCoolStoreSpring");

        return factoryBean;
    }

    /**
     * Creates and configures the JPA transaction manager.
     *
     * @param managerFactory The {@link EntityManagerFactory} to be associated with the transaction manager.
     * @return The configured {@link JpaTransactionManager}.
     */
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory managerFactory) {
        var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(managerFactory);

        return transactionManager;
    }
}
