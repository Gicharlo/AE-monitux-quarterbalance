package monitux.quarterbalance.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class Logs {

	public void log() {

	}

	public void whiteFileDetailsLog(File path, String line) {
		// BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path)

		try (FileOutputStream fos = new FileOutputStream(path)) {

			PrintWriter a = new PrintWriter(new FileWriter(path, true));

			a.append(line + "\n");

			a.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void whiteFileLog(File path, Set<String> line) {

		try (FileOutputStream fos = new FileOutputStream(path)) {
			PrintWriter a = new PrintWriter(new FileWriter(path, true));

			for (String valor : line) {

				a.append(valor + "\n");

			}
			a.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public void deteleLog() {

	}
}