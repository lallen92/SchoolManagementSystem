package school.management.system;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcMySQLHelper
{

    public static Connection getConnection() throws SQLException
    {
        MysqlDataSource ds = getMySQLDataSource();
        return ds.getConnection();
    }
    public static void closeConnection(Connection con) throws SQLException
    {
        if ( con != null )
        {
            con.close();
        }
    }

    public static void closePreparedStatement(PreparedStatement stmt) throws SQLException
    {
        if ( stmt != null )
        {
            stmt.close();
        }
    }


    public  static MysqlDataSource getMySQLDataSource()
    {

        Properties props = new Properties();
        String fileName = System.getProperty("user.dir") + "\\db.properties";

        try (FileInputStream in = new FileInputStream(fileName))
        {
            props.load(in);
        }
        catch (IOException ex)
        {
            Logger lgr = Logger.getLogger(JdbcMySQLHelper.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL(props.getProperty("mysql.url"));
        ds.setUser(props.getProperty("mysql.username"));
        ds.setPassword(props.getProperty("mysql.password"));

        return ds;
    }
}