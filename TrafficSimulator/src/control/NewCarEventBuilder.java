package control;

import exceptions.InvalidParametersException;
import ini.IniSection;
import model.Event;
import model.NewCarEvent;

/**
 * This class is used by the Controller in order to parse the Events.
 * In this case, it checks whether the IniSection corresponds with a
 * new_vehicle Event of type "car".
 *
 */
public class NewCarEventBuilder extends EventBuilder{

	public NewCarEventBuilder() {
		_tag = "new_vehicle";
		_keys = new String[] {"time", "id", "itinerary", "max_speed", "type", "resistance", "fault_probability", "max_fault_duration", "seed"};
		_defaultValues = new String[] {"", "", "", "", "", "car", "", "", "", ""};
	}
	
	public Event parse(IniSection section) throws InvalidParametersException {
		try {
			if(!section.getTag().equals(_tag) || section.getValue("type") == null || !section.getValue("type").equals("car"))
				return null;
			else {
				return new NewCarEvent(EventBuilder.parseNonNegInt(section, "time", 0),
						EventBuilder.validID(section, "id"),
						EventBuilder.parsePosInt(section, "max_fault_duration"),
						EventBuilder.parseArray(section, "itinerary"),
						EventBuilder.parsePosInt(section, "resistance"),
						EventBuilder.parsePosInt(section, "max_speed"),
						EventBuilder.parseNonNegDouble(section, "fault_probability"),
						EventBuilder.parsePosLong(section, "seed", System.currentTimeMillis()));
			}
		}
		catch(InvalidParametersException e) {
			throw new InvalidParametersException(_tag + " event not added. " + e.getMessage());
		}
	}
	
	public String toString() {
		return "New Car";
	}
}