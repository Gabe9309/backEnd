import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Download_PDF {
	
	private final String URL = "https://www.uscis.gov/sites/default/files/USCIS/"
			+ "Office%20of%20Citizenship/Citizenship%20Resource%20Center%20Site/"
			+ "Publications/100q.pdf";
	private URL url;
	private InputStream input;
	private FileOutputStream output;
	
	Download_PDF() throws MalformedURLException, IOException, FileNotFoundException{
		connect();
		open_Input_Stream();
		open_Output_Stream();
		downloader();
		connection_Closer();
	}
	
//----------------------------------------------------------------------------------------------	

	
	private void connect() throws MalformedURLException{
		url = new URL(URL);
	}
	
//----------------------------------------------------------------------------------------------	
	
	private void open_Input_Stream() throws IOException{
		input = url.openStream();
	}
	
//----------------------------------------------------------------------------------------------	
	
	private void open_Output_Stream() throws FileNotFoundException{
		output = new FileOutputStream(new File("Downloaded_English.pdf"));
	}
	
//----------------------------------------------------------------------------------------------	

	private void downloader() throws IOException{
		int length = -1;
		  byte[] buffer = new byte[1024];
		  while ((length = input.read(buffer)) > -1) {
			  output.write(buffer, 0, length);
		  }
	}
	
//----------------------------------------------------------------------------------------------	
	
	private void connection_Closer() throws IOException{
		output.close();
		input.close();
	}
	
//----------------------------------------------------------------------------------------------



}
