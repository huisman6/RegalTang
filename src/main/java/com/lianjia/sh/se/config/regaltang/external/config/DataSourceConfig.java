package com.lianjia.sh.se.config.regaltang.external.config;

import javax.sql.DataSource;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DataSourceConfig {
  @Bean
  public DataSource dataSource() {
    // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    //@formatter:off
    EmbeddedDatabase db = builder
        .setType(EmbeddedDatabaseType.HSQL) // .H2 or .DERBY
        .addScript("db/hsql/sql/schema-sql.sql")
       // .addScript("db/hsql/sql/data-sql.sql")
        .setName("RegalTang")
        .build();
    //@formatter:on
    return db;
  }

  @Bean
  public JdbcTemplate getJdbcTemplate() {
    return new JdbcTemplate(this.dataSource());
  }

  @Bean
  public HSqlManagerBean hsqlManagerBean() {
    return new HSqlManagerBean();
  }

  /**
   * 此类只是为了实例化Hsql的数据库管理工具；必须等所有的单例Bean初始化之后，启动HSQL管理界面；
   * 
   * @author Huisman (SE)
   * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
   */
  static class HSqlManagerBean implements SmartInitializingSingleton {
    @Override
    public void afterSingletonsInstantiated() {
      // derby
      // DatabaseManagerSwing.main(new String[] { "--url", "jdbc:derby:memory:testdb", "--user", "",
      // "--password", "" });

      // h2
      // DatabaseManagerSwing.main(new String[] { "--url", "jdbc:h2:mem:testdb", "--user", "sa",
      // "--password", "" });

      // hsql
      DatabaseManagerSwing.main(new String[] {"--url", "jdbc:hsqldb:mem:RegalTang", "--user", "sa", "--password", ""});
    }
  }
}
