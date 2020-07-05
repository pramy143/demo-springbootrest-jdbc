package com.example.repository;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.ext.oracle.Oracle10DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import javax.sql.DataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

@TestExecutionListeners(
    listeners = {
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
    },
    mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
@Configuration
public abstract class AbstractDependencyInjectionSpringContextTests extends NamedParameterJdbcDaoSupport {

    @Autowired
    private Environment env;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private IDatabaseConnection iDbConnection;

    @BeforeEach
    public void beforeEach() throws DatabaseUnitException {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSourceDBDirect());
        this.iDbConnection = new DatabaseConnection(DataSourceUtils.getConnection(dataSourceDBDirect()));
        this.iDbConnection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
    }

    @AfterEach
    public void afterEach() throws SQLException {
        DataSourceUtils.releaseConnection(iDbConnection.getConnection(), dataSourceDBDirect());
    }

    /*@BeforeEach
    public void parentBefore() throws DatabaseUnitException, SQLException, IOException {
        // we will use DBUnit to prepare the database with a proper dataset

        // initialize your database connection here
        IDatabaseConnection connection = new DatabaseDataSourceConnection(
            dataSourceDBDirect(), "payments_hub");
        connection.getConfig()
                  .setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
        // initialize your dataset here, from the file containing the test
        // dataset
        IDataSet dataSet = new FlatXmlDataSet(new FileInputStream(
            "payments-dataset.xml"));

        try {
            DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        } finally {
            connection.close();
        }
    }*/


    public IDatabaseConnection getiDbConnection() {
        return iDbConnection;
    }

    public void setiDbConnection(final IDatabaseConnection iDbConnection) {
        this.iDbConnection = iDbConnection;
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSourceDBDirect());
    }

    @Bean
    public DataSource dataSourceDBDirect() {
        final DriverManagerDataSource dataTestSource = new DriverManagerDataSource();
        dataTestSource.setSchema("payments_hub");
        dataTestSource.setUrl("jdbc:mysql://localhost:3306/payments_hub?useSSL=false");
        dataTestSource.setUsername("payment");
        dataTestSource.setPassword("payment");

        return dataTestSource;
    }


}
