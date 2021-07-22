package monitux.quarterbalance.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time {
	
	SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	private String initialDate;  
	private String finishDate;  

	private long startTime;
	private long finishTime;
	private double totalTime;	
	
	public SimpleDateFormat getFormatDate() {
		return formatDate;
	}

	public void setFormatDate(SimpleDateFormat formatDate) {
		this.formatDate = formatDate;
	}

	public String getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(long finishTime) {
		this.finishTime = finishTime;
	}
	
	public double getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}

	public void startTime() {
		long startAux = System.currentTimeMillis();
		String initialDateAux = this.getFormatDate().format(Calendar.getInstance().getTime());
		
		
		this.setInitialDate(initialDateAux);
		this.setStartTime(startAux);
		
		
	}
	
	public void finishTime() {
		long finishAux = System.currentTimeMillis();
		String finishDateAux = this.getFormatDate().format(Calendar.getInstance().getTime());
		
		this.setFinishDate(finishDateAux);
		this.setFinishTime(finishAux);
	}
	
	public void totalTime() {
		double totalAux = this.getFinishTime() - this.getStartTime();
		totalAux /= 60000;
		this.setTotalTime(totalAux);
	
		System.out.println("Initial date:" + this.getInitialDate());
		System.out.println("Finish date:" + this.getFinishDate());
		System.out.println( "Runtime(min): " + this.getTotalTime());
		
	}

}
