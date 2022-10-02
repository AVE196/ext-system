package edu.network.greet;

import edu.network.Greetable;

public class MorningGreet extends Greetable{

	@Override
	public String buildResponse(String name) {
		return "Goog morning, " + name;
	}

}
