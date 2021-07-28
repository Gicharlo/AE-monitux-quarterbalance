package monitux.quarterbalance.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class XmlQuarters {
	private Map<Integer, Map<Set<String>, Set<String>>> exerciseDate = new HashMap<>();
	
	private Map<Integer, Map<Set<String>, Set<String>>> idCPFirstQuarterIndA = new HashMap<Integer, Map<Set<String>, Set<String>>>();
	private Map<Integer, Map<Set<String>, Set<String>>> idCPFirstQuarterIndP = new HashMap<Integer,Map<Set<String>, Set<String>>>();

	private  Map<Integer, Map<Set<String>, Set<String>>> idCPFirstQuarterConA = new HashMap<Integer,Map<Set<String>, Set<String>>>();
	private  Map<Integer, Map<Set<String>, Set<String>>> idCPFirstQuarterConP = new HashMap<Integer,Map<Set<String>, Set<String>>>();

	private Map<Integer, Map<Set<String>, Set<String>>> idCPSecondQuarterIndA = new HashMap<Integer,Map<Set<String>, Set<String>>>();
	private Map<Integer, Map<Set<String>, Set<String>>> idCPSecondQuarterIndP = new HashMap<Integer,Map<Set<String>, Set<String>>>();

	private Map<Integer, Map<Set<String>, Set<String>>> idCPSecondQuarterConA = new HashMap<Integer,Map<Set<String>, Set<String>>>();
	private Map<Integer, Map<Set<String>, Set<String>>> idCPSecondQuarterConP = new HashMap<Integer,Map<Set<String>, Set<String>>>();

	private Map<Integer, Map<Set<String>, Set<String>>> idCPThirdQuarterIndA = new HashMap<Integer,Map<Set<String>, Set<String>>>();
	private Map<Integer, Map<Set<String>, Set<String>>> idCPThirdQuarterIndP = new HashMap<Integer,Map<Set<String>, Set<String>>>();

	private Map<Integer, Map<Set<String>, Set<String>>> idCPThirdQuarterConA = new HashMap<Integer,Map<Set<String>, Set<String>>>();
	private Map<Integer, Map<Set<String>, Set<String>>> idCPThirdQuarterConP = new HashMap<Integer,Map<Set<String>, Set<String>>>();

	private Map<Integer, Map<Set<String>, Set<String>>> idCPFourthQuarterIndA = new HashMap<Integer,Map<Set<String>, Set<String>>>();
	private Map<Integer, Map<Set<String>, Set<String>>> idCPFourthQuarterIndP = new HashMap<Integer,Map<Set<String>, Set<String>>>();

	private Map<Integer, Map<Set<String>, Set<String>>> idCPFourthQuarterConA = new HashMap<Integer,Map<Set<String>, Set<String>>>();
	private Map<Integer, Map<Set<String>, Set<String>>> idCPFourthQuarterConP = new HashMap<Integer,Map<Set<String>, Set<String>>>();

	public XmlQuarters() {

	}

	public Map<Integer, Map<Set<String>, Set<String>>> getExerciseDate() {
		return exerciseDate;
	}
	
	
	public Map<Integer, Map<Set<String>, Set<String>>> getIdCPFirstQuarterIndA() {
		return idCPFirstQuarterIndA;
	}

	public Map<Integer, Map<Set<String>, Set<String>>> getIdCPFirstQuarterIndP() {
		return idCPFirstQuarterIndP;
	}

	public Map<Integer, Map<Set<String>, Set<String>>> getIdCPFirstQuarterConA() {
		return idCPFirstQuarterConA;
	}

	public Map<Integer, Map<Set<String>, Set<String>>> getIdCPFirstQuarterConP() {
		return idCPFirstQuarterConP;
	}
	
	

	public XmlQuarters(Map<Integer, Map<Set<String>, Set<String>>> exerciseDate) {
		this.exerciseDate = exerciseDate;
	}
	

	public Map<Integer, Map<Set<String>, Set<String>>> getIdCPSecondQuarterIndA() {
		return idCPSecondQuarterIndA;
	}

	public Map<Integer, Map<Set<String>, Set<String>>> getIdCPSecondQuarterIndP() {
		return idCPSecondQuarterIndP;
	}

	public Map<Integer, Map<Set<String>, Set<String>>> getIdCPSecondQuarterConA() {
		return idCPSecondQuarterConA;
	}

	public Map<Integer, Map<Set<String>, Set<String>>> getIdCPSecondQuarterConP() {
		return idCPSecondQuarterConP;
	}

	public Map<Integer, Map<Set<String>, Set<String>>> getIdCPThirdQuarterIndA() {
		return idCPThirdQuarterIndA;
	}

	public Map<Integer, Map<Set<String>, Set<String>>> getIdCPThirdQuarterIndP() {
		return idCPThirdQuarterIndP;
	}

	public Map<Integer, Map<Set<String>, Set<String>>> getIdCPThirdQuarterConA() {
		return idCPThirdQuarterConA;
	}

	public Map<Integer, Map<Set<String>, Set<String>>> getIdCPThirdQuarterConP() {
		return idCPThirdQuarterConP;
	}

	public Map<Integer, Map<Set<String>, Set<String>>> getIdCPFourthQuarterIndA() {
		return idCPFourthQuarterIndA;
	}

	public Map<Integer, Map<Set<String>, Set<String>>> getIdCPFourthQuarterIndP() {
		return idCPFourthQuarterIndP;
	}

	public Map<Integer, Map<Set<String>, Set<String>>> getIdCPFourthQuarterConA() {
		return idCPFourthQuarterConA;
	}

	public Map<Integer, Map<Set<String>, Set<String>>> getIdCPFourthQuarterConP() {
		return idCPFourthQuarterConP;
	}

	public void showXmlQuarters() {
		this.exerciseDate.forEach((id, exerciseDate) -> {
			System.out.println(id + " = {" + exerciseDate + " } ");

		});
	}

	public void showXmlQuarterstese() {
		this.idCPSecondQuarterConA .forEach((id, exerciseDate) -> {
			exerciseDate.forEach((indCon, quarter) -> {
				System.out.println(id + " = {" + quarter + ", " + indCon + " } ");

			});
		});
	}

}
