package de.frittenburger.sumo.impl;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.junit.Test;

import de.frittenburger.sumo.interfaces.EdgeQueryService;
import de.frittenburger.sumo.interfaces.SumoNetReader;
import de.frittenburger.sumo.model.SumoNet;

public class TestSumoNetXmlReader {

	@Test
	public void test() throws Exception {
		
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("aschaffenburg-bahnhof.net.xml");
		
		SumoNetReader reader = new SumoNetXmlReader();
		
		
		SumoNet net = reader.read(in);
		
		assertEquals(278, net.getEdges().size());
	
		
		EdgeQueryService query = new EdgeQueryServiceImpl(net);

		
		//buffer_stop
		//    <edge id="-411084988" from="4128182227" to="4128182226" priority="-1" type="railway.rail">
        //     <edge id="411084988" from="4128182226" to="4128182227" priority="-1" type="railway.rail">

		assertEquals("-411084988", query.selectEdgeByFromNode("4128182227").getId());
		assertEquals("411084988", query.selectEdgeByToNode("4128182227").getId());

	}

}
