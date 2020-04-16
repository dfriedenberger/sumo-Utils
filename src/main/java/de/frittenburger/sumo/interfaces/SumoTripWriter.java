package de.frittenburger.sumo.interfaces;

import java.io.OutputStream;
import java.util.List;

import de.frittenburger.sumo.model.SumoTrip;

public interface SumoTripWriter {

	void write(List<SumoTrip> trips, OutputStream out);

}
