package com.hpe.bboss.balance.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableTransactionManagement
@AutoConfigureAfter(DataSource.class)
public class MybatisConfig implements TransactionManagementConfigurer {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DemoDataSource dds;

	@Bean
	public DataSource dataSource() {
		DruidDataSource datasource = new DruidDataSource();
		// 配置连接信息
		datasource.setUrl(dds.getUrl());
		datasource.setUsername(dds.getUsername());
		datasource.setPassword(dds.getPassword());
		datasource.setDriverClassName(dds.getDriverClassName());

		// 配置连接池
		datasource.setInitialSize(dds.getInitialSize());
		datasource.setMinIdle(dds.getMinIdle());
		datasource.setMaxActive(dds.getMaxActive());
		datasource.setMaxWait(dds.getMaxWait());
		datasource.setTimeBetweenEvictionRunsMillis(dds.getTimeBetweenEvictionRunsMillis());
		datasource.setMinEvictableIdleTimeMillis(dds.getMinEvictableIdleTimeMillis());
		datasource.setValidationQuery(dds.getValidationQuery());
		datasource.setTestWhileIdle(dds.isTestWhileIdle());
		datasource.setTestOnBorrow(dds.isTestOnReturn());
		datasource.setTestOnReturn(dds.isTestOnReturn());
		datasource.setPoolPreparedStatements(dds.isPoolPreparedStatements());
		datasource.setMaxPoolPreparedStatementPerConnectionSize(dds.getMaxPoolPreparedStatementPerConnectionSize());
		try {
			datasource.setFilters(dds.getFilters());
		} catch (SQLException e) {
			logger.error("druid configuration initialization filter", e);
		}
		datasource.setConnectionProperties(dds.getConnectionProperties());

		return datasource;
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean() {
		logger.info("SqlSessionFactory is initializing");
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource());

		// 添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			bean.setMapperLocations(resolver.getResources("classpath:/mybatis/mapper/*.xml"));
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			logger.info("SqlSessionFactory is initialized");
		}
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

}
