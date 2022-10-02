
package edu.network.greet;

import edu.network.Greetable;

public class HelloGreet extends Greetable{

	@Override
	public String buildResponse(String name) {
		return "Hello, " + name;
	}

}
