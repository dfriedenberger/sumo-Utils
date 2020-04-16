package de.frittenburger.sumo.impl;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import de.frittenburger.sumo.interfaces.SumoTripWriter;
import de.frittenburger.sumo.model.SumoTrip;

public class SumoTripXmlWriter implements SumoTripWriter {

	@Override
	public void write(List<SumoTrip> trips, OutputStream out) {

		
		try(PrintWriter writer = new PrintWriter(new OutputStreamWriter(out,StandardCharsets.UTF_8)))
		{
			writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			writer.println("<!-- generated on "+new Date()+" via https://github.com/dfriedenberger/sumo-Utils -->");
			writer.println("<routes xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"http://sumo.dlr.de/xsd/routes_file.xsd\">");
			writer.println("   <vType id=\"rail_rail\" vClass=\"rail\">");
			writer.println("   </vType>");
			for(SumoTrip trip : trips)
				writer.println("   "+trip.toXmlLine());
			writer.println("</routes>");
		}
	    
	}

}
