package de.frittenburger.sumo.cli;

import java.util.List;

public class FahrplanEntry {

		private final int plattform;
		private final String time;
		private final List<String> from;
		private final List<String> to;

		public FahrplanEntry(int plattform, String time, List<String> from,
				List<String> to) {
			this.plattform = plattform;
			this.time = time;
			this.from = from;
			this.to = to;
		
		}

		public int getPlattform() {
			return plattform;
		}

		public String getTime() {
			return time;
		}

		public List<String> getFrom() {
			return from;
		}

		public List<String> getTo() {
			return to;
		}

		@Override
		public String toString() {
			return "Entry [plattform=" + plattform + ", time=" + time
					+ ", from=" + from + ", to=" + to + "]";
		}
		
	
}
