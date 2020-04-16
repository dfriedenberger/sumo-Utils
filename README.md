# sumo-Utils
Utils for SUMO Simulator 

- Can produce SUMO Trips from departure schedule. 

## GetStarted
Translate with maven and try example in path ```src\main\java\de\frittenburger\sumo\cli\Example.java```

Produces trips for train station "Frankfurt(Main) Hbf". 
Departure schedule is downloaded from "https://dbf.finalrewind.org/FF.json?version=3"

```
?xml version="1.0" encoding="UTF-8"?>
<!-- generated on Thu Apr 16 22:30:38 CEST 2020 via https://github.com/dfriedenberger/sumo-Utils -->
<routes xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="http://sumo.dlr.de/xsd/routes_file.xsd">
   <vType id="rail_rail" vClass="rail">
   </vType>
   <trip id="rail1" depart="40.00" departLane="best" departSpeed="max" from="154495069" to="20697184#1" type="rail_rail"/>
   <trip id="rail2" depart="80.00" departLane="best" departSpeed="max" from="-159493336" to="-154495069" type="rail_rail"/>
   <trip id="rail3" depart="120.00" departLane="best" departSpeed="max" from="154495105" to="159493336" type="rail_rail"/>
   <trip id="rail4" depart="160.00" departLane="best" departSpeed="max" from="-48720639" to="-154495105" type="rail_rail"/>
   <trip id="rail5" depart="200.00" departLane="best" departSpeed="max" from="154495066" to="159493336" type="rail_rail"/>
   <trip id="rail6" depart="240.00" departLane="best" departSpeed="max" from="-20697184#1" to="-154495066" type="rail_rail"/>
...
```


