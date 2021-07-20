package monitux.quarterbalance.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggingOutputStream {

	public void log() {
		
		
		
		//System.out.println(err);

	}

	public void whiteLog(File path, String line) {
		//BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path)
		
		try (FileOutputStream fos = new FileOutputStream(path)) {
			
			PrintWriter a = new PrintWriter (new FileWriter (path, true));
		

			a.append(line + "\n");			
			a.append("a");
			a.close();	

		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void showLog(String path, String line) {

		try (BufferedReader buffRead = new BufferedReader(new FileReader(path))) {

			while (true) {
				if (line != null) {
					System.out.println(line);

				} else
					break;
				line = buffRead.readLine();
			}
			buffRead.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public void deteleLog() {

	}
}