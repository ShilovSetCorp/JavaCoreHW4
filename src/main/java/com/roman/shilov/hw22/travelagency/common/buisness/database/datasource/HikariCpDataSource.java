package com.roman.shilov.hw22.travelagency.common.buisness.database.datasource;

import com.roman.shilov.hw22.travelagency.common.buisness.exceptions.ConnectionAchieveError;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariCpDataSource implements ConnectionProvider {

    private static volatile HikariCpDataSource INSTANCE = null;

    private HikariDataSource hikariDataSource;

    public static class HikariCpDataSourceBuilder {

        private String url;
        private String userName;
        private String password;
        private String driver;

        public HikariCpDataSourceBuilder() {
        }

        public HikariCpDataSourceBuilder appendUrl(String url) {
            this.url = url;
            return this;
        }

        public HikariCpDataSourceBuilder appendUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public HikariCpDataSourceBuilder appendPassword(String password) {
            this.password = password;
            return this;
        }

        public HikariCpDataSourceBuilder appendDriver(String driver) {
            this.driver = driver;
            return this;
        }


        public HikariConfig build() {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setUsername(this.userName);
            hikariConfig.setPassword(this.password);
            hikariConfig.setJdbcUrl(this.url);
            hikariConfig.setDriverClassName(this.driver);
            hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
            hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
            hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            return hikariConfig;
        }

    }

    @Override
    public Connection getConnection() throws SQLException {
        try {
            return hikariDataSource.getConnection();
        }catch (Exception e) {
            throw new ConnectionAchieveError();
        }
    }

    public static void init(HikariCpDataSourceBuilder hikariCpDataSourceBuilder) {
        HikariCpDataSource result = INSTANCE;

        if(result == null) {
            synchronized (HikariCpDataSource.class) {
                result = INSTANCE;
                if(result == null) {
                    result = new HikariCpDataSource();
                    result.hikariDataSource = new HikariDataSource(hikariCpDataSourceBuilder.build());
                    INSTANCE = result;
                }
            }
        }
    }

    public static HikariCpDataSource getInstance() {
        return INSTANCE;
    }
}
