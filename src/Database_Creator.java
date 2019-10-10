
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database_Creator extends Database{
	private Database_Manager manager;
//---------------------------------------------------------------------------------------------------------------
	
	
//---------------------------------------------------------------------------------------------------------------
	
	Database_Creator(Storage[] storage_Object) throws ClassNotFoundException, SQLException{
		dB_Create();
		dB_Create_Table();
		manager = new Database_Manager(storage_Object);
	}
//---------------------------------------------------------------------------------------------------------------
	
	public void dB_Create() throws ClassNotFoundException, SQLException{
		
		Class.forName("org.sqlite.JDBC");
		
		String url = "jdbc:sqlite:" + dBPath.toString();
		
		connection = DriverManager.getConnection(url);
		
	}//End db_Create
	
//--------------------------------------------------------------------------------------------------------------
	
	public void dB_Create_Table() throws ClassNotFoundException, SQLException {
		
		String sql = "CREATE TABLE studyGuide (\n"
				+ "    id integer PRIMARY KEY , \n"
				+ "    question text, \n"
				+"     answers text \n"
				+");";
		
		if (connection == null)
			connection = dB_Establish_Connection();
		
		statement = connection.createStatement();
		statement.execute(sql);
		
		if(connection != null) 
			connection.close();
		
	}
	
}
