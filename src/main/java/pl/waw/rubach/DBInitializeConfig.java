package pl.waw.rubach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.persistence.ManyToOne;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class DBInitializeConfig {

	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void initialize(){
		try {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.execute("DROP TABLE IF EXISTS BID");
			statement.executeUpdate(
					"create table BID ( "
							+ "BIDID INTEGER PRIMARY KEY, "
							+ "BID_LEVEL INTEGER , "
							+ "LEVEL INTEGER,"
							+ "SUIT VARCHAR(2),"
							+ "POINTS_MIN INTEGER,"
							+ "POINTS_MAX INTEGER,"
							+ "SUIT_LENGTH INTEGER,"
							+ "BID_TYPE VARCHAR(30),"
							+ "BID_CLASS VARCHAR(30),"
							+ "AFTER_INTERVEN BOOLEAN,"
							+ "DESCRIPTION VARCHAR(1000),"
							+ "PARENT_BID_BIDID LONG"
							+ ") ");

			statement.execute("DROP TABLE IF EXISTS CUSTOMER");
			statement.execute("DROP TABLE IF EXISTS ACCOUNT");
			statement.executeUpdate(
					"create table CUSTOMER ( "
							+ "CUSTOMERID INTEGER PRIMARY KEY, "
							+ "FIRST_NAME VARCHAR(50), "
							+ "LAST_NAME VARCHAR(50)"
							+ ") ");
			statement.executeUpdate(
					"create table ACCOUNT ( "
							+ "ACCOUNTID INTEGER PRIMARY KEY, "
							+ "BALANCE NUMERIC, "
							+ "SAVINGS BOOLEAN,"
							+ "CUSTOMER_CUSTOMERID INTEGER,"
							+ "DTYPE VARCHAR(40)"
							+ ") ");
			statement.close();
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
