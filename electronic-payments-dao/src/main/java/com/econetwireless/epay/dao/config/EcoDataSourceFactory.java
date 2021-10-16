package com.econetwireless.epay.dao.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Driver;

public class EcoDataSourceFactory implements DataSourceFactory, ConnectionProperties {

    private final ComboPooledDataSource comboPooledDataSource;
    private static final Logger LOGGER = LoggerFactory.getLogger(EcoDataSourceFactory.class);


    public EcoDataSourceFactory() {
        this(getComboPooledDataSource());
    }

    public EcoDataSourceFactory(ComboPooledDataSource comboPooledDataSource) {
        this.comboPooledDataSource = comboPooledDataSource;
    }

    private static ComboPooledDataSource getComboPooledDataSource() {

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        try {
            comboPooledDataSource.setDriverClass("org.hsqldb.jdbc.JDBCDriver");
            comboPooledDataSource.setJdbcUrl("jdbc:hsqldb:mem:epay");
            comboPooledDataSource.setMinPoolSize(1);
            comboPooledDataSource.setMaxPoolSize(100);
            comboPooledDataSource.setMaxIdleTime(20);

            LOGGER.info("Minimum Pool Size : {}, Maximum Pool Size  : {}, Maximum Idle Time  : {}",
                    comboPooledDataSource.getMinPoolSize(),
                    comboPooledDataSource.getMaxPoolSize(),
                    comboPooledDataSource.getMaxIdleTime());

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        return comboPooledDataSource;

    }


    @Override
    public ConnectionProperties getConnectionProperties() {


        return this;

    }

    @Override
    public DataSource getDataSource() {

        return comboPooledDataSource;
    }


    @Override
    public String toString() {
        return comboPooledDataSource.getDataSourceName();
    }

    @Override
    public void setDriverClass(Class<? extends Driver> driverClass) {

        try {
            comboPooledDataSource.setDriverClass(driverClass.getName());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setUrl(String url) {

        comboPooledDataSource.setJdbcUrl(url);
    }

    @Override
    public void setUsername(String username) {

    }

    @Override
    public void setPassword(String password) {

    }
}
