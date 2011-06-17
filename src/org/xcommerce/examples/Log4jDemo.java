package org.xcommerce.examples;

import org.apache.log4j.Logger; // usar o log4j

/**
 * Example on how to use the Log4J library.<br>
 * You must include <code>org.apache.log4j.Logger</code> to use Log4J.<br>
 * Then, you will need to create an Loger, like this:
 * <p>
 * <code>
 *	static Logger log = Logger.getLogger("org.xcommerce.examples.Log4jDemo");
 * </code>
 * </p>
 * To make a log, use one of the four methods:<br>
 * <ul>
 * 	<li><b><code>log.debug(String msg)</code></b> - To write a debug message</li>
 * 	<li><b><code>log.info(String msg)</code></b> - To write an info message</li>
 * 	<li><b><code>log.warn(String msg)</code></b> - To write a warning message</li>
 * 	<li><b><code>log.error(String msg)</code></b> - To write an error message</li>
 * 	<li><b><code>log.fatal(String msg)</code></b> - To write a fatal error message</li>
 * </ul>
 * @author Rodrigo Mello
 * */
public class Log4jDemo {
	
	/**
	 * The logger used on this example.
	 * */
	static Logger log = 
		Logger.getLogger("org.xcommerce.examples.Log4jDemo");
	
	/**
	 * Simple test
	 * @param args An vector of arguments. (Not used on this example)
	 * */
	public static void main(String args[]) {

		log.debug("This is my debug message.");
		log.info("This is my info message.");
		log.warn("This is my warn message.");
		log.error("This is my error message.");
		log.fatal("This is my fatal message.");
	}
}
