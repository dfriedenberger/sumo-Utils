package de.frittenburger.sumo.cli;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.frittenburger.sumo.impl.EdgeQueryServiceImpl;
import de.frittenburger.sumo.impl.SumoNetXmlReader;
import de.frittenburger.sumo.impl.SumoTripXmlWriter;
import de.frittenburger.sumo.interfaces.EdgeQueryService;
import de.frittenburger.sumo.interfaces.SumoNetReader;
import de.frittenburger.sumo.interfaces.SumoTripWriter;
import de.frittenburger.sumo.model.SumoEdge;
import de.frittenburger.sumo.model.SumoNet;
import de.frittenburger.sumo.model.SumoTrip;

public class Example {

	
	
	static Map<String,String> readNode(String filename) throws IOException
	{
		return Files.readAllLines(new File(filename).toPath()).stream()
			.filter(x -> !(x.trim().isEmpty() || x.startsWith("#")))
			.map(x -> x.split("=")).collect(Collectors.toMap(x -> x[0].trim(),x -> x[1].trim()));
	}
	
	private static List<FahrplanEntry> readfahrPlan(String filename,List<String> center) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		JsonNode tree = mapper.readTree(new File(filename));
		
		List<FahrplanEntry> plan = new ArrayList<>();
		
		for(JsonNode node : tree.get("departures"))
		{
			int plattform = node.get("platform").asInt();
			JsonNode route = node.get("route");
			List<String> from = new ArrayList<>();
			List<String> to = new ArrayList<>();
			boolean found = false;
			for(JsonNode station : route)
			{
				String name = station.get("name").asText();
				if(center.contains(name))
				{
					found = true;
					continue;
				}
				if(found)
					to.add(name);
				else
					from.add(name);
			}
			String time = node.get("scheduledDeparture").asText();
			if(time.equals("null"))
			{
			    time = node.get("scheduledArrival").asText();
			}
		    //System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node));
			plan.add(new FahrplanEntry(plattform,time,from,to));
		}
		return plan;
	}
	
	private static String map2node(Map<String, String> border, Map<String, String> borderstations,List<String> stations) {

		for(String station : stations)
		{
			if(!borderstations.containsKey(station)) continue;
			String key = borderstations.get(station);
			String node = border.get(key);
			if(node == null)
			{
				System.out.println("key "+key+" not found");
				continue;
			}
			return node;
		}
		
		System.out.println("no station found "+stations);

		return null;
	}
	
	public static void main(String[] args) throws IOException, ParserConfigurationException {


		SumoNetReader reader = new SumoNetXmlReader();
		SumoNet net = reader.read(new FileInputStream("example/frankfurt-bahnhof.net.xml"));
		
		EdgeQueryService query = new EdgeQueryServiceImpl(net);
		
		
		Map<String,String> border = readNode("example/node-border.txt");
		Map<String,String> center = readNode("example/node-center.txt");
		Map<String,String> stations = readNode("example/stations.txt");

		
		double depart = 0;
		List<SumoTrip> trips = new ArrayList<>();
		//read Fahrplan
		for(FahrplanEntry e : readfahrPlan("example/data.json",Arrays.asList("Frankfurt(Main)Hbf","Frankfurt Hbf (tief)")))
		{
			int plattform = e.getPlattform();
			//int time = e.getTime();
			List<String> from = e.getFrom();
			List<String> to = e.getTo();
			
			String centerNode = center.get(""+plattform);
			if(centerNode == null)
			{
				System.out.println("plattform "+plattform+" not found");
				continue;
			}
			
			if(to.size() > 0)
			{
				String node = map2node(border,stations,to);

				SumoEdge fromEdge = query.selectEdgeByFromNode(centerNode);
				SumoEdge toEdge = query.selectEdgeByToNode(node);

				trips.add(new SumoTrip(fromEdge.getId(),toEdge.getId(),depart+= 40));			}
			
			if(from.size() > 0)
			{
				String node = map2node(border,stations,from);
				SumoEdge fromEdge = query.selectEdgeByFromNode(node);
				SumoEdge toEdge = query.selectEdgeByToNode(centerNode);

				trips.add(new SumoTrip(fromEdge.getId(),toEdge.getId(),depart+= 40));
			}
			
		}
		
		SumoTripWriter writer = new SumoTripXmlWriter();
		writer.write(trips,new FileOutputStream("trips.gen.xml"));
	}

	


	

}
