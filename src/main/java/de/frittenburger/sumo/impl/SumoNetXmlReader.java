package de.frittenburger.sumo.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.frittenburger.sumo.interfaces.SumoNetReader;
import de.frittenburger.sumo.model.SumoEdge;
import de.frittenburger.sumo.model.SumoNet;

public class SumoNetXmlReader implements SumoNetReader {

	private final DocumentBuilder dBuilder;
	public SumoNetXmlReader() throws ParserConfigurationException
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dBuilder = dbFactory.newDocumentBuilder();
	}
	
	
	
	
	@Override
	public SumoNet read(InputStream in) throws IOException {

			try {
				
				Document doc = dBuilder.parse(in);
				
				doc.getDocumentElement().normalize();

				String root = doc.getDocumentElement().getNodeName();
				if(!root.equals("net"))		
					throw new IOException("Not an sumo net root="+root);
				
				
				SumoNet net = new SumoNet();

				
				NodeList nList = doc.getElementsByTagName("edge");
						

				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node edgeNode = nList.item(temp);
					NamedNodeMap attributes = edgeNode.getAttributes();	
					Node typeNode = attributes.getNamedItem("type");
					if(typeNode != null)
					{
						String type = typeNode.getNodeValue();
						if(type.equals("railway.rail"))
						{
							/*
							 *    <edge id="-33286436" from="1670255768" to="378269914" priority="-1" type="railway.rail" shape="3796.13,2102.77 3811.95,2112.74 3864.41,2143.54 3887.02,2156.67 3902.91,2166.08">
                             *       <lane id="-33286436_0" index="0" speed="13.89" length="123.96" shape="3797.11,2101.49 3812.78,2111.37 3865.22,2142.16 3887.83,2155.29 3903.73,2164.71"/>
                             *    </edge>
							 */
							SumoEdge edge = new SumoEdge(); 
							edge.setId(attributes.getNamedItem("id").getNodeValue());
							edge.setFrom(attributes.getNamedItem("from").getNodeValue());
							edge.setTo(attributes.getNamedItem("to").getNodeValue());
							net.getEdges().add(edge);
						}
					}
					
				}
				return net;

			} catch (SAXException e) {
				throw new IOException(e.getMessage());
			}
		
		
		
	}

}
