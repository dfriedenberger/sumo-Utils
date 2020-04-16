package de.frittenburger.sumo.model;

import java.text.DecimalFormat;


public class SumoTrip {

	private static int ID = 1;
	private String id;
	private String depart;
	private String from;
	private String to;

	public SumoTrip(String from, String to, double depart) {
		
		this.id = "rail" + ID++;
		this.depart = format(depart);
		this.from = from;
		this.to = to;
		
	}

	

	public String toXmlLine() {
		return "<trip" 
				+ attr("id",id)
				+ attr("depart",depart)
				+ attr("departLane","best")
				+ attr("departSpeed","max")
				+ attr("from",from)
				+ attr("to",to)
				+ attr("type","rail_rail")+"/>";
	}

	
    private static String attr(String key, String value) {
		return " "+key+"=\""+value+"\"";
	}
    private static DecimalFormat df = new DecimalFormat("#.00"); 

    private static String format(double depart) {
		return df.format(depart).replace(',', '.');
	}
}
