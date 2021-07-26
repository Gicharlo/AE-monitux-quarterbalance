package monitux.quarterbalance.entities;

import java.util.HashMap;
import java.util.Map;

public class CvmQuarters {
		
	
	private Map<Integer, String> companiesFirstQuarterIndA = new HashMap<Integer, String>();
	private Map<Integer, String> companiesFirstQuarterIndP = new HashMap<Integer, String>();
	
	private Map<Integer, String> companiesFirstQuarterConA = new HashMap<Integer, String>();
	private Map<Integer, String> companiesFirstQuarterConP = new HashMap<Integer, String>();
	
	private Map<Integer, String> companiesSecondQuarterIndA = new HashMap<Integer, String>();
	private Map<Integer, String> companiesSecondQuarterIndP = new HashMap<Integer, String>();
	
	private Map<Integer, String> companiesSecondQuarterConA = new HashMap<Integer, String>();
	private Map<Integer, String> companiesSecondQuarterConP = new HashMap<Integer, String>();
	
	private Map<Integer, String> companiesThirdQuarterIndA = new HashMap<Integer, String>();
	private Map<Integer, String> companiesThirdQuarterIndP = new HashMap<Integer, String>();
	
	private Map<Integer, String> companiesThirdQuarterConA = new HashMap<Integer, String>();
	private Map<Integer, String> companiesThirdQuarterConP = new HashMap<Integer, String>();
	
	private Map<Integer, String> companiesFourthQuarterIndA = new HashMap<Integer, String>();
	private Map<Integer, String> companiesFourthQuarterIndP = new HashMap<Integer, String>();
	
	private Map<Integer, String> companiesFourthQuarterConA = new HashMap<Integer, String>();
	private Map<Integer, String> companiesFourthQuarterConP = new HashMap<Integer, String>();
	
	

	public CvmQuarters() {

	}

	public Map<Integer, String> getCompaniesFirstQuarterIndA() {
		return companiesFirstQuarterIndA;
	}



	public Map<Integer, String> getCompaniesFirstQuarterIndP() {
		return companiesFirstQuarterIndP;
	}



	public Map<Integer, String> getCompaniesFirstQuarterConA() {
		return companiesFirstQuarterConA;
	}



	public Map<Integer, String> getCompaniesFirstQuarterConP() {
		return companiesFirstQuarterConP;
	}



	public Map<Integer, String> getCompaniesSecondQuarterIndA() {
		return companiesSecondQuarterIndA;
	}



	public Map<Integer, String> getCompaniesSecondQuarterIndP() {
		return companiesSecondQuarterIndP;
	}



	public Map<Integer, String> getCompaniesSecondQuarterConA() {
		return companiesSecondQuarterConA;
	}



	public Map<Integer, String> getCompaniesSecondQuarterConP() {
		return companiesSecondQuarterConP;
	}



	public Map<Integer, String> getCompaniesThirdQuarterIndA() {
		return companiesThirdQuarterIndA;
	}



	public Map<Integer, String> getCompaniesThirdQuarterIndP() {
		return companiesThirdQuarterIndP;
	}



	public Map<Integer, String> getCompaniesThirdQuarterConA() {
		return companiesThirdQuarterConA;
	}



	public Map<Integer, String> getCompaniesThirdQuarterConP() {
		return companiesThirdQuarterConP;
	}



	public Map<Integer, String> getCompaniesFourthQuarterIndA() {
		return companiesFourthQuarterIndA;
	}



	public Map<Integer, String> getCompaniesFourthQuarterIndP() {
		return companiesFourthQuarterIndP;
	}



	public Map<Integer, String> getCompaniesFourthQuarterConA() {
		return companiesFourthQuarterConA;
	}



	public Map<Integer, String> getCompaniesFourthQuarterConP() {
		return companiesFourthQuarterConP;
	}



	public void showCompanies() {

		this.companiesSecondQuarterConA.forEach((id, company) -> {

			System.out.println(id + " = { " + company + " } ");

		});

	}

}
