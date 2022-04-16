package com.snowflakes.demo;

import net.snowflake.client.jdbc.SnowflakeBasicDataSource;
import net.snowflake.client.log.ArgSupplier;
import net.snowflake.client.log.SFLogger;
import net.snowflake.client.log.SFLoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author mark anthony ortiz
 */
public class SnowflakeBasicDataSourceExtended extends SnowflakeBasicDataSource {

    static final SFLogger logger = SFLoggerFactory.getLogger(SnowflakeBasicDataSourceExtended.class);
    private Properties properties;

    private String url;

    public SnowflakeBasicDataSourceExtended(Properties properties, String url) {
        this.properties = properties;
        this.url = url;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        try {
            Connection con = DriverManager.getConnection(getUrl(), properties);
            logger.trace("Created a connection for {} at {}", properties.get("user"), (ArgSupplier) this::getUrl);
            return con;
        } catch (SQLException e) {
            logger.error("Failed to create a connection for {} at {}: {}", properties.get("user"), getUrl(), e);
            throw e;
        }
    }
}
