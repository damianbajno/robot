package logowanie;

import org.apache.log4j.Logger;

public class Logowanie {
	final static Logger logger = Logger.getLogger(Logowanie.class);
	public Logowanie(String nameBooks,  Object obj) {
		logger.info( obj+"  "+nameBooks);
	}

}
