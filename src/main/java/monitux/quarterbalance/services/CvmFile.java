package monitux.quarterbalance.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CvmFile {

	private static final int BUFFER = 2048;

	// private static final String fileName =
	// "C:\\temp\\monitux\\quarterbalance\\cvmfile\\itr_cia_aberta_BPA_ind_2021.csv";

	private String searchFileCvm(String year) {
		try {
			URL url = new URL("http://dados.cvm.gov.br/dados/CIA_ABERTA/DOC/ITR/DADOS/itr_cia_aberta_"+ year +".zip");
			String path = "C:\\monitux\\quarterbalance\\cvmfile\\cvmfile.zip";
			InputStream is = url.openStream();
			FileOutputStream fos = new FileOutputStream(path);

			int bytes = 0;

			while ((bytes = is.read()) != -1) {
				fos.write(bytes);
			}

			is.close();
			fos.close();
			return path;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
			// throw new RuntimeException("One or more fields have errors");
		}
	}

	public void unzip(String destinationFolder, String year) {

		try {
			File unzipped = new File(searchFileCvm(year));

			// Se não existir a pasta destino
			// será criada por nosso programa
			if (!unzipped.exists()) {
				unzipped.mkdirs();
			}

			BufferedOutputStream dest = null;
			FileInputStream fis = new FileInputStream(unzipped);
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
			
			ZipEntry entry;
			
			while ((entry = zis.getNextEntry()) != null) {
				System.out.println("Extracting file: " + entry);
				int count;
				byte data[] = new byte[BUFFER];

				// Cria os arquivos no disco
				FileOutputStream fos2 = new FileOutputStream(destinationFolder + entry.getName());
				dest = new BufferedOutputStream(fos2, BUFFER);

				while ((count = zis.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
			}

			zis.close();
		} catch (IOException e) {
			throw new RuntimeException(" One or more paths were incorrect");
		}

	}

	public void saveFile(File fileITRIndA, Map<Integer, String> firstQuarter, Map<Integer, String> secondQuarter,
			Map<Integer, String> thirdQuarter, Map<Integer, String> fourthQuarter) {

		String line = "";
		String dateAux;
		
		boolean firstLine = true;
		//String[] qtrs = { "1T2021", "2T2020", "3T2020", "4T2020" };

		try {
			BufferedReader br = new BufferedReader(new FileReader(fileITRIndA));
			while ((line = br.readLine()) != null) {
				String[] colunas = new String[14];
				colunas = line.split(";");

				if (firstLine) {
					firstLine = false;
					continue;
				}

				dateAux = colunas[1];
				LocalDate date = toLocalDate(dateAux);
				
				

				if (date.getMonthValue() == 3) {
					
					firstQuarter.put(Integer.parseInt(colunas[04]), colunas[3]);
					
				}if (date.getMonthValue() == 6) {
					
					secondQuarter.put(Integer.parseInt(colunas[04]), colunas[3]);
					
				}if (date.getMonthValue() == 9) {
					
					thirdQuarter.put(Integer.parseInt(colunas[04]), colunas[3]);
					
				}if (date.getMonthValue() == 12) {
					
					fourthQuarter.put(Integer.parseInt(colunas[04]), colunas[3]);
				}
			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public LocalDate toLocalDate(String date) throws ParseException {

		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8, 10));

		LocalDate converted = LocalDate.of(year, month, day);

		return converted;
	}
}
