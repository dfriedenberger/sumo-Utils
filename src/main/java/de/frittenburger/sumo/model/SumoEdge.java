package de.frittenburger.sumo.model;

public class SumoEdge {

	private String id;
	private String from;
	private String to;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
	@Override
	public String toString() {
		return "SumoEdge [id=" + id + ", from=" + from + ", to=" + to + "]";
	}
	
	
}
