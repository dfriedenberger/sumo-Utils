package de.frittenburger.sumo.interfaces;

import java.io.IOException;
import java.io.InputStream;

import de.frittenburger.sumo.model.SumoNet;

public interface SumoNetReader {

	SumoNet read(InputStream in) throws IOException;
	
}
