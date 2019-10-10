import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Database {
	
	protected final String DB_NAME = "data.db";
	protected Connection connection;
	protected Statement statement;
	protected PreparedStatement preparedStatement;
	protected Path dBPath;
	
	Database(){
		dBPath = Paths.get(DB_NAME);
		dBPath = dBPath.toAbsolutePath();
	}
	
	protected Connection dB_Establish_Connection() throws ClassNotFoundException, SQLException{

		Class.forName("org.sqlite.JDBC");
		
		String url = "jdbc:sqlite:" + dBPath.toString();
		
		connection = DriverManager.getConnection(url);
		
		return connection;
	}
}
