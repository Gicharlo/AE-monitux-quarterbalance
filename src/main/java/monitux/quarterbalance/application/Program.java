package monitux.quarterbalance.application;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import monitux.quarterbalance.entities.CvmQuarters;
import monitux.quarterbalance.entities.XmlQuarters;
import monitux.quarterbalance.log.LoggingOutputStream;
import monitux.quarterbalance.services.CpAe;
import monitux.quarterbalance.services.CvmFile;

public class Program {

	public static void main(String[] args)
			throws IOException, ParserConfigurationException, SAXException, ParseException {

		CvmFile cvmFile = new CvmFile();
		CvmQuarters quarters = new CvmQuarters();
		CpAe cp = new CpAe();
		XmlQuarters xml = new XmlQuarters();
		LoggingOutputStream log = new LoggingOutputStream();

		String i = "I";
		String c = "C";

		String auxAN = "1";
		String auxPN = "2";

		String auxAL2 = "A";
		String auxPL2 = "P";

		String checkYear = "2021";

		File tr1IndA = new File("C:\\monitux\\quarterbalance\\cvmfile\\1TR\\IndA");
		File tr1IndP = new File("C:\\monitux\\quarterbalance\\cvmfile\\1TR\\IndP");

		File tr1ConA = new File("C:\\monitux\\quarterbalance\\cvmfile\\1TR\\ConA");
		File tr1ConP = new File("C:\\monitux\\quarterbalance\\cvmfile\\1TR\\ConP");

		File tr2IndA = new File("C:\\monitux\\quarterbalance\\cvmfile\\2TR\\IndA");
		File tr2IndP = new File("C:\\monitux\\quarterbalance\\cvmfile\\2TR\\IndP");

		File tr2ConA = new File("C:\\monitux\\quarterbalance\\cvmfile\\2TR\\ConA");
		File tr2ConP = new File("C:\\monitux\\quarterbalance\\cvmfile\\2TR\\ConP");

		File tr3IndA = new File("C:\\monitux\\quarterbalance\\cvmfile\\3TR\\IndA");
		File tr3IndP = new File("C:\\monitux\\quarterbalance\\cvmfile\\3TR\\IndP");

		File tr3ConA = new File("C:\\monitux\\quarterbalance\\cvmfile\\3TR\\ConA");
		File tr3ConP = new File("C:\\monitux\\quarterbalance\\cvmfile\\3TR\\ConP");

		File tr4IndA = new File("C:\\monitux\\quarterbalance\\cvmfile\\4TR\\IndA");
		File tr4IndP = new File("C:\\monitux\\quarterbalance\\cvmfile\\4TR\\IndP");

		File tr4ConA = new File("C:\\monitux\\quarterbalance\\cvmfile\\4TR\\ConA");
		File tr4ConP = new File("C:\\monitux\\quarterbalance\\cvmfile\\4TR\\ConP");

		// Locais que armazenam chamadas das CP em arquivos Xml:
		String firstPathIndA = "C:\\monitux\\quarterbalance\\cvmfile\\1TR\\IndA\\";
		String firstPathIndP = "C:\\monitux\\quarterbalance\\cvmfile\\1TR\\IndP\\";
		String firstPathConA = "C:\\monitux\\quarterbalance\\cvmfile\\1TR\\ConA\\";
		String firstPathConP = "C:\\monitux\\quarterbalance\\cvmfile\\1TR\\ConP\\";

		String secondPathIndA = "C:\\monitux\\quarterbalance\\cvmfile\\2TR\\IndA\\";
		String secondPathIndP = "C:\\monitux\\quarterbalance\\cvmfile\\2TR\\IndP\\";
		String secondPathConA = "C:\\monitux\\quarterbalance\\cvmfile\\2TR\\ConA\\";
		String secondPathConP = "C:\\monitux\\quarterbalance\\cvmfile\\2TR\\ConP\\";

		String thirdPathIndA = "C:\\monitux\\quarterbalance\\cvmfile\\3TR\\IndA\\";
		String thirdPathIndP = "C:\\monitux\\quarterbalance\\cvmfile\\3TR\\IndP\\";
		String thirdPathConA = "C:\\monitux\\quarterbalance\\cvmfile\\3TR\\ConA\\";
		String thirdPathConP = "C:\\monitux\\quarterbalance\\cvmfile\\3TR\\ConP\\";

		String fourthPathIndA = "C:\\monitux\\quarterbalance\\cvmfile\\4TR\\IndA\\";
		String fourthPathIndP = "C:\\monitux\\quarterbalance\\cvmfile\\4TR\\IndP\\";
		String fourthPathConA = "C:\\monitux\\quarterbalance\\cvmfile\\4TR\\ConA\\";
		String fourthPathConP = "C:\\monitux\\quarterbalance\\cvmfile\\4TR\\ConP\\";

		// File ITR_IND_ATIVO:
		File fileITRIndA = new File(
				"C:\\monitux\\quarterbalance\\cvmfile\\itr_cia_aberta_BPA_ind_" + checkYear + ".csv");

		// File ITR_IND_PASSIVO:
		File fileITRIndP = new File(
				"C:\\monitux\\quarterbalance\\cvmfile\\itr_cia_aberta_BPP_ind_" + checkYear + ".csv");

		// File ITR_CON_ATIVO:
		File fileITRConA = new File(
				"C:\\monitux\\quarterbalance\\cvmfile\\itr_cia_aberta_BPA_con_" + checkYear + ".csv");

		// File ITR_CON_PASSIVO:
		//
		File fileITRConP = new File(
				"C:\\monitux\\quarterbalance\\cvmfile\\itr_cia_aberta_BPP_con_" + checkYear + ".csv");

		// Pasta destino dos arquivos .cvs
		String destinationFolder = "C:\\monitux\\quarterbalance\\cvmfile\\";

		// busca na url cvm & descompacta arquivo zip e salvando na pasta:
		cvmFile.unzip(destinationFolder, checkYear);

		// Tratanto o Arquivo cvm Indivual Ativo:
		cvmFile.saveFile(fileITRIndA, quarters.getCompaniesFirstQuarterIndA(), quarters.getCompaniesSecondQuarterIndA(),
				quarters.getCompaniesThirdQuarterIndA(), quarters.getCompaniesFourthQuarterIndA());

		// Tratanto o Arquivo cvm Indivual Passivo:
		cvmFile.saveFile(fileITRIndP, quarters.getCompaniesFirstQuarterIndP(), quarters.getCompaniesSecondQuarterIndP(),
				quarters.getCompaniesThirdQuarterIndP(), quarters.getCompaniesFourthQuarterIndP());

		// Tratanto o Arquivo cvm Consolidado Ativo:
		cvmFile.saveFile(fileITRConA, quarters.getCompaniesFirstQuarterConA(), quarters.getCompaniesSecondQuarterConA(),
				quarters.getCompaniesThirdQuarterConA(), quarters.getCompaniesFourthQuarterConA());

		// Tratanto o Arquivo cvm Consolidado Passivo:
		cvmFile.saveFile(fileITRConP, quarters.getCompaniesFirstQuarterConP(), quarters.getCompaniesSecondQuarterConP(),
				quarters.getCompaniesThirdQuarterConP(), quarters.getCompaniesFourthQuarterConP());

		// mostrando arquivo cvm Ind A
		// quarters.showCompanies();

		// Buscando & Salvando arquivos das Chamadas CP dos ID de empresas com Ind A -
		// 1TR:
		cp.saveXml(quarters.getCompaniesFirstQuarterIndA(), firstPathIndA, i, checkYear);

		// Buscando & Salvando arquivos das Chamadas CP dos ID de empresas com Ind P -
		// 1TR:
		cp.saveXml(quarters.getCompaniesFirstQuarterIndP(), firstPathIndP, i, checkYear);

		// Buscando & Salvando arquivos das Chamadas CP dos ID de empresas com Con A -
		// 1TR:
		cp.saveXml(quarters.getCompaniesFirstQuarterConA(), firstPathConA, c, checkYear);

		// Buscando & Salvando arquivos das Chamadas CP dos ID de empresas com Con P -
		// 1TR:
		cp.saveXml(quarters.getCompaniesFirstQuarterConP(), firstPathConP, c, checkYear);

		// Buscando & Salvando arquivos das Chamadas CP dos ID de empresas com Ind A -
		// 2TR:
		cp.saveXml(quarters.getCompaniesSecondQuarterIndA(), secondPathIndA, i, checkYear);

		// Buscando & Salvando arquivos das Chamadas CP dos ID de empresas com Ind P -
		// 2TR:
		cp.saveXml(quarters.getCompaniesSecondQuarterIndP(), secondPathIndP, i, checkYear);

		// Buscando & Salvando arquivos das Chamadas CP dos ID de empresas com Con A -
		// 2TR:
		cp.saveXml(quarters.getCompaniesSecondQuarterConA(), secondPathConA, c, checkYear);

		// Buscando & Salvando arquivos das Chamadas CP dos ID de empresas com Con P -
		// 2TR:
		cp.saveXml(quarters.getCompaniesSecondQuarterConA(), secondPathConP, c, checkYear);

		// Buscando & Salvando arquivos das Chamadas CP dos ID de empresas com Ind A -
		// 3TR:
		cp.saveXml(quarters.getCompaniesThirdQuarterIndA(), thirdPathIndA, i, checkYear);

		// Buscando & Salvando arquivos das Chamadas CP dos ID de empresas com Ind P -
		// 3TR:
		cp.saveXml(quarters.getCompaniesThirdQuarterIndP(), thirdPathIndP, i, checkYear);

		// Buscando & Salvando arquivos das Chamadas CP dos ID de empresas com Con A -
		// 3TR:
		cp.saveXml(quarters.getCompaniesThirdQuarterConA(), thirdPathConA, c, checkYear);

		// Buscando & Salvando arquivos das Chamadas CP dos ID de empresas com Con P -
		// 3TR:
		cp.saveXml(quarters.getCompaniesThirdQuarterConP(), thirdPathConP, c, checkYear);

		// Salvando arquivos das Chamadas CP dos ID de empresas com Ind A - 4TR:
		cp.saveXml(quarters.getCompaniesFourthQuarterIndA(), fourthPathIndA, i, checkYear);

		// Salvando arquivos das Chamadas CP dos ID de empresas com Ind P - 4TR:
		cp.saveXml(quarters.getCompaniesFourthQuarterIndP(), fourthPathIndP, i, checkYear);

		// Salvando arquivos das Chamadas CP dos ID de empresas com Con A - 4TR:
		cp.saveXml(quarters.getCompaniesFourthQuarterConA(), fourthPathConA, c, checkYear);

		// Salvando arquivos das Chamadas CP dos ID de empresas com Con P - 4TR:
		cp.saveXml(quarters.getCompaniesFourthQuarterConP(), fourthPathConP, c, checkYear);

		// Lendo chamadas CP & Salvando Ind A - 1TR:
		cp.readingXml(tr1IndA, xml.getIdCPFirstQuarterIndA());

		// Lendo chamadas CP & Salvando Ind P - 1TR:
		cp.readingXml(tr1IndP, xml.getIdCPFirstQuarterIndP());

		// Lendo chamadas CP & Salvando Con A - 1TR:
		cp.readingXml(tr1ConA, xml.getIdCPFirstQuarterConA());

		// Lendo chamadas CP & Salvando Con P - 1TR:
		cp.readingXml(tr1ConP, xml.getIdCPFirstQuarterConP());

		// Lendo chamadas CP & Salvando Ind A - 2TR:
		cp.readingXml(tr2IndA, xml.getIdCPFirstQuarterIndA());

		// Lendo chamadas CP & Salvando Ind P - 2TR:
		cp.readingXml(tr2IndP, xml.getIdCPFirstQuarterIndP());

		// Lendo chamadas CP & Salvando Con A - 2TR:
		cp.readingXml(tr2ConA, xml.getIdCPFirstQuarterConA());

		// Lendo chamadas CP & Salvando Con P - 2TR:
		cp.readingXml(tr2ConP, xml.getIdCPFirstQuarterConP());

		// Lendo chamadas CP & Salvando Ind A - 3TR:
		cp.readingXml(tr3IndA, xml.getIdCPThirdQuarterIndA());

		// Lendo chamadas CP & Salvando Ind P - 3TR:
		cp.readingXml(tr3IndP, xml.getIdCPThirdQuarterIndP());

		// Lendo chamadas CP & Salvando Con A - 3TR:
		cp.readingXml(tr3ConA, xml.getIdCPThirdQuarterConA());

		// Lendo chamadas CP & Salvando Con P - 3TR:
		cp.readingXml(tr3ConP, xml.getIdCPThirdQuarterConP());

		// Lendo chamadas CP & Salvando Ind A - 4TR:
		cp.readingXml(tr4IndA, xml.getIdCPFourthQuarterIndA());

		// Lendo chamadas CP & Salvando Ind P - 4TR:
		cp.readingXml(tr4IndP, xml.getIdCPFourthQuarterIndP());

		// Lendo chamadas CP & Salvando Con A - 4TR:
		cp.readingXml(tr4ConA, xml.getIdCPFourthQuarterConA());

		// Lendo chamadas CP & Salvando Con P - 4TR:
		cp.readingXml(tr4ConP, xml.getIdCPFourthQuarterConP());

		// Validando Ind A - 1TR no broad:

		ByteArrayOutputStream stream = new ByteArrayOutputStream();

		PrintStream ps = new PrintStream(stream);

		PrintStream originalPrintStream = System.out;

		System.setOut(ps);

		cp.validateFirstQuarterInd(quarters.getCompaniesFirstQuarterIndA(), xml.getIdCPFirstQuarterIndA(), auxAN,
				auxAL2, checkYear);

		// Validando Ind P - 1TR no broad:
		cp.validateFirstQuarterInd(quarters.getCompaniesFirstQuarterIndP(), xml.getIdCPFirstQuarterIndP(), auxPN,
				auxPL2, checkYear);

		// Validando Con A - 1TR no broad:
		cp.validateFirstQuarterCon(quarters.getCompaniesFirstQuarterConA(), xml.getIdCPFirstQuarterConA(), auxAN,
				auxAL2, checkYear);

		// Validando Con P - 1TR no broad:
		cp.validateFirstQuarterCon(quarters.getCompaniesFirstQuarterConP(), xml.getIdCPFirstQuarterConP(), auxPN,
				auxPL2, checkYear);

		// Validando Ind A - 2TR no broad:
		cp.validateSecondQuarterInd(quarters.getCompaniesSecondQuarterIndA(), xml.getIdCPSecondQuarterIndA(), auxAN,
				auxAL2, checkYear);

		// Validando Ind P - 2TR no broad:
		cp.validateSecondQuarterInd(quarters.getCompaniesSecondQuarterIndP(), xml.getIdCPSecondQuarterIndP(), auxPN,
				auxPL2, checkYear);

		// Validando Con A - 2TR no broad:
		cp.validateSecondQuarterCon(quarters.getCompaniesSecondQuarterConA(), xml.getIdCPSecondQuarterConA(), auxAN,
				auxAL2, checkYear);

		// Validando Con P - 2TR no broad:
		cp.validateSecondQuarterCon(quarters.getCompaniesSecondQuarterConP(), xml.getIdCPSecondQuarterConP(), auxPN,
				auxPL2, checkYear);

		// Validando Ind A - 3TR no broad:
		cp.validateThirdQuarterInd(quarters.getCompaniesThirdQuarterIndA(), xml.getIdCPThirdQuarterIndA(), auxAN,
				auxAL2, checkYear);

		// Validando Ind P - 3TR no broad:
		cp.validateThirdQuarterInd(quarters.getCompaniesThirdQuarterIndP(), xml.getIdCPThirdQuarterIndP(), auxPN,
				auxPL2, checkYear);

		// Validando Con A - 3TR no broad:
		cp.validateThirdQuarterCon(quarters.getCompaniesThirdQuarterConA(), xml.getIdCPThirdQuarterConA(), auxAN,
				auxAL2, checkYear);

		// Validando Con P - 3TR no broad:
		cp.validateThirdQuarterCon(quarters.getCompaniesThirdQuarterConP(), xml.getIdCPThirdQuarterConP(), auxPN,
				auxPL2, checkYear);

		// Validando Ind A - 4TR no broad:
		cp.validateFourthQuarterInd(quarters.getCompaniesFourthQuarterIndA(), xml.getIdCPFourthQuarterIndA(), auxAN,
				auxAL2, checkYear);

		// Validando Ind P - 4TR no broad:
		cp.validateFourthQuarterInd(quarters.getCompaniesFourthQuarterIndP(), xml.getIdCPFourthQuarterIndP(), auxPN,
				auxPL2, checkYear);

		// Validando Con A - 4TR no broad:
		cp.validateFourthQuarterCon(quarters.getCompaniesFourthQuarterConA(), xml.getIdCPFourthQuarterConA(), auxAN,
				auxAL2, checkYear);

		// Validando Con P - 4TR no broad:
		cp.validateFourthQuarterCon(quarters.getCompaniesFourthQuarterConP(), xml.getIdCPFourthQuarterConP(), auxPN,
				auxPL2, checkYear);

		File path = new File("C:\\monitux\\quarterbalance\\logs\\console.txt");

		System.setOut(originalPrintStream);

		String output = new String(stream.toByteArray());

		log.whiteLog(path, output);

		// mostrando xml
		// xml.showXmlQuarters();
	}

}
