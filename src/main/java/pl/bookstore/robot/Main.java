package pl.bookstore.robot;

import org.apache.log4j.Logger;

import com.jaunt.NotFound;
import com.jaunt.ResponseException;

import getbooks.Matras;
import getbooks.Pwn;

public class Main {

	final static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) throws NotFound, ResponseException {
		logger.info("Robot Start");

		Pwn pwd = new Pwn();
		System.out.println(pwd);
		Matras matras = new Matras();
		System.out.println(matras);


	}
}
