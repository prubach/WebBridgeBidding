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
							+ "SUIT_LENGTH VARCHAR(4),"
							+ "BID_TYPE VARCHAR(30),"
							+ "BID_CLASS VARCHAR(30),"
							+ "AFTER_INTERVEN BOOLEAN,"
							+ "SHORT_DESC VARCHAR(100),"
							+ "DESCRIPTION VARCHAR(1000),"
							+ "PARENT_BID_BIDID INTEGER,"
							+ "BID_SYSTEM_BID_SYSTEMID INTEGER"
							+ ") ");
			statement.execute("DROP TABLE IF EXISTS BID_SYSTEM");
			statement.executeUpdate(
					"create table BID_SYSTEM ( "
							+ "BID_SYSTEMID INTEGER PRIMARY KEY, "
							+ "NAME VARCHAR(50)"
							+ ") ");
			statement.close();
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
