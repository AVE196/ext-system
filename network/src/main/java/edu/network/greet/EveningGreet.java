
package edu.network.greet;

import edu.network.Greetable;

public class EveningGreet extends Greetable{

	@Override
	public String buildResponse(String name) {
		return "Goog evening, " + name;
	}

}
