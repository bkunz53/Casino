import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class buffWriter {
	public buffWriter(){
		
	}
	public void write(String content){
		try {


			File outfile = new File("C:\\Users\\Brian K\\Desktop\\fileTest\\file.txt");

			// if file doesnt exists, then create it
			if (!outfile.exists()) {
				outfile.createNewFile();
			}

			FileWriter fw = new FileWriter(outfile.getAbsoluteFile());  //Open the file for writing
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();   //Always a good habit to flush when you are through
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}