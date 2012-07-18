package com.mars.mission;
/*
 * Entry point for Mars Rover
 * Accepts an input file as an argument
 */


import java.io.*;
import java.util.ArrayList;

class LaunchMission {

	protected Field field = null;
	
	// Main entry point - 
	// Open file specified as command line arg
	// and start mission
	public static void main(String args[])
		throws java.io.IOException, java.io.FileNotFoundException
	{
		if(args.length == 1)
		{
			String line;
			ArrayList<String> lines = new ArrayList<String>();
			
			// Read file one line at a time into an ArrayList
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
	        while((line = br.readLine()) != null) {
	            lines.add(line); 
	        	//System.out.println(line);
	        	//String[] words = line.split(" ");
	        	//for(int i = 0; i < words.length; i++)
	        	//    System.out.printf("Word %d: %s\r\n", i, words[i]);
	        }
	        br.close();
	        
	        LaunchMission mission = new LaunchMission();
	        
	        // First line is field size
	        mission.createField(lines.get(0));
	        
	        // Process lines two at a time.
	        // First of pair is rover landing spot and heading
	        // Second is a list of commands
	        for(int i = 1; i < (lines.size()); i+=2) {
	        	// Deploy Rover
	        	Rover r = new Rover(lines.get(i), mission.field);
	        	// Send commands to Rover
	        	r.command(lines.get(i+1));
	        	// print Rover location
	        	System.out.println(r.toString());
        	}
		}
		else
		{
			System.out.println("Please supply a mission parameter file as an argument");
			System.exit(1);
		}
		System.exit(0);
	}
	
	/**
	 * 
	 * @param fieldInput - e.g. "10 5", a space-delimited string
	 *        containing an x and y size for a field that starts at 0,0
	 */
	public void createField(String fieldInput) {
		String[] sizeParams = fieldInput.split(" ");
		if(sizeParams.length != 2) throw new IllegalArgumentException("Expected two size parameters");
		int x = Integer.parseInt(sizeParams[0]);
		int y = Integer.parseInt(sizeParams[1]);
		
		this.field = new Field(x, y);
	}
	
}