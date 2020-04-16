package de.frittenburger.sumo.interfaces;

import de.frittenburger.sumo.model.SumoEdge;

public interface EdgeQueryService {

	SumoEdge selectEdgeByFromNode(String centerNode);

	SumoEdge selectEdgeByToNode(String node);

}
