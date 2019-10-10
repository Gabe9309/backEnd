
import java.sql.SQLException;

public class Database_Manager extends Database{

	Database_Manager(Storage[] storage_Object) throws ClassNotFoundException, SQLException{
		dB_add_To_Table(storage_Object);
	}
	
	
	public void dB_add_To_Table(Storage[] storage_Object) throws ClassNotFoundException, SQLException {
		
		Class.forName("org.sqlite.JDBC");
		
		String sql = "INSERT INTO studyGuide(id,question,answers) VALUES(?,?,?)";
	
		for(int i  = 0; i < storage_Object.length; i++) {
			
			if(connection == null)
				connection = dB_Establish_Connection();
		
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, i);
			preparedStatement.setString(2, storage_Object[i].get_Question());
			preparedStatement.setString(3, storage_Object[i].merge_Answers());
			preparedStatement.executeUpdate();
		}
	}
	
//Functions for future use.
//--------------------------------------------------------------------------------------------------------------
	
	public void dB_Delete_Table() {
		
	}

//--------------------------------------------------------------------------------------------------------------
	
	public void dB_Modify_Table() {
		
	}
	
	/*public static void main(String[] args) {
		Database_Manager manager = new Database_Manager();
		manager.dB_add_To_Table();

	}*/

}
