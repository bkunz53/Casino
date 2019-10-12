import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class buffReader {
	public buffReader(){

	}
	public void readFile() {

		BufferedReader reader = null;
		ArrayList <String> myFileLines = new ArrayList <String>();

		try {

			String sCurrentLine;

			reader = new BufferedReader(new FileReader("C:\\Users\\Brian K\\Desktop\\fileTest\\file.txt"));

			while ((sCurrentLine = reader.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		} finally {
			try {

				if (reader != null)reader.close();
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();

			}
		}
	}
}
