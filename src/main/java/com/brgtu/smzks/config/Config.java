package com.brgtu.smzks.config;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;



@Configuration
@ComponentScan()
@EnableJpaRepositories(basePackages = "com.brgtu.smzks.dao")
public class Config {

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
            throws
            SQLException,
            NamingException
    {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
        entityManagerFactoryBean.setPackagesToScan("com.brgtu.smzks");

        entityManagerFactoryBean.setJpaProperties(properties());

        return entityManagerFactoryBean;
    }


    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource toReturn = new DriverManagerDataSource("jdbc:mysql://localhost:3306/smzks",
                "root", "12345678");
        toReturn.setDriverClassName("com.mysql.jdbc.Driver");
        return toReturn;
    }

    @Bean
    public JpaTransactionManager transactionManager()
            throws
            SQLException,
            NamingException
    {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }


    private Properties properties()
    {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.default_schema", "smzks");

        return properties;
    }
}
