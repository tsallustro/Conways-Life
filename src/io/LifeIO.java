package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import std.CellMap;
import std.Exe;

public class LifeIO {

	/*
	 * Private constructor to prevent instantiation
	 */
	private LifeIO() {

	}

	private static void promptForSize(Scanner in) {
		System.out.println("Input x size: ");
		CellMap.XSIZE = in.nextInt();
		in.nextLine(); //consume the newline character
		System.out.println("Input y size: ");
		CellMap.YSIZE = in.nextInt();
		in.nextLine();  //consume the newline character

	}

	private static CellMap promptForCustom(Scanner in) {
		CellMap map = new CellMap();
		System.out.println("Ok, lets start building a custom start.");
		while (true) {
			System.out.println("Pick from the following:");
			System.out.println("1. Start with this custom map");
			System.out.println("2. Add a glider");
			System.out.println("3. Add a pulsar");
			System.out.println("4. Add a single cell");
			int choice = in.nextInt();
			in.nextLine();  //consume the newline character
			switch (choice) {
				case 1: {
					System.out.println("Starting the simulation...");
					return map;
				}
				case 2: {

					System.out.println("Add a glider at what x: ");
					int x = in.nextInt();
					in.nextLine();  //consume the newline character

					System.out.println("And at what y: ");
					int y = in.nextInt();
					in.nextLine();  //consume the newline character

					map.buildGliderAt(x, y);
					break;
				}
				case 3: {
					System.out.println("Add a pulsar at what x: ");
					int x = in.nextInt();
					in.nextLine();  //consume the newline character

					System.out.println("And at what y: ");
					int y = in.nextInt();
					in.nextLine();  //consume the newline character

					map.buildPulsarAt(x, y);
					break;
				}
				case 4: {
					System.out.println("Add a single cell at what x: ");
					int x = in.nextInt();
					in.nextLine();  //consume the newline character

					System.out.println("And at what y: ");
					int y = in.nextInt();
					in.nextLine();  //consume the newline character

					map.setCell(x, y, true);
					break;
				}
				default: {
					System.out.println("Invalid choice.");
				}
			}
		}
	}

	private static CellMap promptForInput(Scanner in) {
		final CellMap CELLMAP = new CellMap();
		System.out.print("Please input path to input file (CSV): ");
		String path = in.nextLine();
		System.out.println();
		BufferedReader csvReader;

		try {
			csvReader = new BufferedReader(new FileReader(new File(path)));

			String row = "";
			int y = 0;
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				for (int x = 0; x < data.length; x++) {
					if (data[x].equalsIgnoreCase("true"))
						CELLMAP.setCell(x, y, true);
					else
						CELLMAP.setCell(x, y, false);
				}

				y++;
			}
			csvReader.close();

		} catch (IOException e) {
			System.err.println("Error reading file. See stack trace for more");
			e.printStackTrace();
		}
		return CELLMAP;
	}

	public static CellMap startPrompt(Scanner in) {
		CellMap map = new CellMap();
		promptForSize(in);
		System.out.println("What would you like to do: ");
		int choice = -1;
		do {

			System.out.println("1. Start with a random map");
			System.out.println("2. Build a custom map");
			System.out.println("3. Start from a file");
			choice = in.nextInt();
			in.nextLine();  //consume the newline character

			switch (choice) {
				case 1: {
					System.out.println("Randomizing...");
					map.randomize();
					break;
				}
				case 2: {
					map = promptForCustom(in);
					break;
					
				}
				case 3: {

					map = promptForInput(in);
					break;
					
				}
				default: {
					System.out.println("Invalid choice");
					break;
				}
			}
		} while (choice <= 0 || choice > 3);
		return map;
	}

}
