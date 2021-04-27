package pl.waw.rubach.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class DBInitializeConfig {

	@Value("${webbridge.cleardatabase}")
	private Boolean clearDatabase = false;

	@Autowired
	private DataSource dataSource;

	private static Logger logger = LoggerFactory.getLogger(DBInitializeConfig.class);
	
	@PostConstruct
	public void initialize(){
		try {
			if (clearDatabase) {
				logger.info("Deleting existing data in the database and recreating the structure");
				Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				statement.execute("DROP TABLE IF EXISTS BID");
				statement.execute("DROP TABLE IF EXISTS BID_SYSTEM");
			/*	statement.executeUpdate(
						"CREATE TABLE `Bid` (" +
								"`bidID` INTEGER, " +
								"`bidLevel` INTEGER, " +
								"`level` INTEGER, " +
								"`suit` TEXT, " +
								"`pointsMin` INTEGER, " +
								"`pointsMax` INTEGER, " +
								"`suitLength` TEXT, " +
								"`bidType` TEXT, " +
								"`bidClass` TEXT, " +
								"`afterInterven` INTEGER NOT NULL, " +
								"`shortDesc` TEXT, " +
								"`description` TEXT, " +
								"`assumption` INTEGER, " +
								"`parentBidBidId` INTEGER, " +
								"`bidSystemBidSystemId` INTEGER, " +
								"PRIMARY KEY(`bidID`)" +
								")");
				statement.executeUpdate(
						"CREATE TABLE `BidSystem` (" +
								"`bidSystemID` INTEGER, " +
								"`name` TEXT, " +
								"PRIMARY KEY(`bidSystemID`))");
*/
				statement.executeUpdate(
						"create table BID ( "
								+ "BIDID INTEGER PRIMARY KEY, "
								+ "BID_LEVEL INTEGER , "
								+ "LEVEL INTEGER,"
								+ "SUIT VARCHAR(2),"
								+ "POINTS_MIN INTEGER,"
								+ "POINTS_MAX INTEGER,"
								+ "SUIT_LENGTH VARCHAR(4),"				//odp mogę wpisać wiecej niz 4 znaki czyli to znaczy co innego - tak to jest coś tam od sql
								+ "BID_TYPE VARCHAR(30),"
								+ "BID_CLASS VARCHAR(30),"
								+ "AFTER_INTERVEN INTEGER,"
								+ "ASSUMPTION INTEGER,"
								+ "SHORT_DESC VARCHAR(100),"
								+ "DESCRIPTION VARCHAR(1000),"
								+ "PARENT_BID_BIDID INTEGER,"
								+ "BID_SYSTEM_BID_SYSTEMID INTEGER"
								+ ") ");
				statement.executeUpdate(
						"create table BID_SYSTEM ( "
								+ "BID_SYSTEMID INTEGER PRIMARY KEY, "
								+ "NAME VARCHAR(50)"
								+ ") ");
				statement.close();
				connection.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
