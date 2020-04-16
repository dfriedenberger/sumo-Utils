package de.frittenburger.sumo.impl;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import de.frittenburger.sumo.interfaces.EdgeQueryService;
import de.frittenburger.sumo.model.QueryServiceException;
import de.frittenburger.sumo.model.SumoEdge;
import de.frittenburger.sumo.model.SumoNet;

public class EdgeQueryServiceImpl implements EdgeQueryService {

	private final SumoNet net;

	public EdgeQueryServiceImpl(SumoNet net) {
		this.net = net;
	}

    private SumoEdge selectSingle(Predicate<SumoEdge> filter) throws QueryServiceException {
		
		List<SumoEdge> list = net.getEdges().stream().filter(filter).collect(Collectors.toList());
		if(list.size() == 1) 
			return list.get(0);
		throw new QueryServiceException("single "+list);
	}
    
	@Override
	public SumoEdge selectEdgeByFromNode(String node) {
		
			try {
				return selectSingle((e)-> e.getFrom().equals(node));
			} catch (QueryServiceException e) {
				e.printStackTrace();
				throw new RuntimeException("selectEdgeByFromNode "+node);
			}
		
	}

	

	@Override
	public SumoEdge selectEdgeByToNode(String node) {
		try {
			return selectSingle((e)-> e.getTo().equals(node));
		} catch (QueryServiceException e) {
			e.printStackTrace();
			throw new RuntimeException("selectEdgeByToNode "+node);
		}
	}

}
