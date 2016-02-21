package madeexercises.opdracht18.main;

import java.io.IOException;

/**
 * Created by Kevin Haitsna on 17-2-2016.
 */
public class Main {

    public static void main(String[] args) {
        try {
            Runner runner = new Runner();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

		/*
		runner.start();

		try {
			runner.join();
		} catch (InterruptedException e) {
			System.err.println("Main : Was interrupted while waiting for the program to die.");
			e.printStackTrace();
		}
		*/
    }

}
