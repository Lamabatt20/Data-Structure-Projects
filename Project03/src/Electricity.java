
import java.time.LocalDate;

public class Electricity {
	private LocalDate date;
	private double Israeli_Lines;
	private double Gaza_Power_Plant;
	private double Egyptian_Lines;
	private double Total_daily_Supply_available_in;
	private double Overall_demand;
	private double Power_Cuts_hours_day;
	private double Temp;
	public Electricity(LocalDate date, double israeli_Lines, double gaza_Power_Plant, double egyptian_Lines,
			double total_daily_Supply_available_in, double overall_demand, double power_Cuts_hours_day, double temp) {
		super();
		this.date = date;
		Israeli_Lines = israeli_Lines;
		Gaza_Power_Plant = gaza_Power_Plant;
		Egyptian_Lines = egyptian_Lines;
		Total_daily_Supply_available_in = total_daily_Supply_available_in;
		Overall_demand = overall_demand;
		Power_Cuts_hours_day = power_Cuts_hours_day;
		Temp = temp;
	}
	
	public Electricity() {
		super();
	}

	@Override
	public String toString() {
		return date + "," + Israeli_Lines + ","
				+ Gaza_Power_Plant + ", " + Egyptian_Lines + ","
				+ Total_daily_Supply_available_in + "," + Overall_demand + ","
				+ Power_Cuts_hours_day + "," + Temp + " ";
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getIsraeli_Lines() {
		return Israeli_Lines;
	}
	public void setIsraeli_Lines(double israeli_Lines) {
		Israeli_Lines = israeli_Lines;
	}
	public double getGaza_Power_Plant() {
		return Gaza_Power_Plant;
	}
	public void setGaza_Power_Plant(double gaza_Power_Plant) {
		Gaza_Power_Plant = gaza_Power_Plant;
	}
	public double getEgyptian_Lines() {
		return Egyptian_Lines;
	}
	public void setEgyptian_Lines(double egyptian_Lines) {
		Egyptian_Lines = egyptian_Lines;
	}
	public double getTotal_daily_Supply_available_in() {
		return Total_daily_Supply_available_in;
	}
	public void setTotal_daily_Supply_available_in(double total_daily_Supply_available_in) {
		Total_daily_Supply_available_in = total_daily_Supply_available_in;
	}
	public double getOverall_demand() {
		return Overall_demand;
	}
	public void setOverall_demand(double overall_demand) {
		Overall_demand = overall_demand;
	}
	public double getPower_Cuts_hours_day() {
		return Power_Cuts_hours_day;
	}
	public void setPower_Cuts_hours_day(double power_Cuts_hours_day) {
		Power_Cuts_hours_day = power_Cuts_hours_day;
	}
	public double getTemp() {
		return Temp;
	}
	public void setTemp(double temp) {
		Temp = temp;
	}
	
	
}
