# Conways Life
My Implementation of Conway's Game of Life

This program is my implementation of Conway's Game of Life (https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life), a simple chaotic 
automation that defines cells in a grid as either alive (black) or dead (white). The program observes the following rules:

-Any live cell with two or three live neighbours survives.

-Any dead cell with three live neighbours becomes a live cell.

-All other live cells die in the next generation. Similarly, all other dead cells stay dead.

## Game Starts
The game can start in 3 ways:

Random: The application can generate a pseudorandom initial state with a 50/50 chance that a given cell will be alive on start. 

Custom: The application will guide the user into building their own start by one-by-one adding either single cells or predefined patterns.

From file: The program will read a CSV file with to build the simulation. If the specified file is too small for the X/Y of the gamearea,
           the rest of the field will be populated with dead (false) cells. Likewise, if the file is too big, it will cut off any values that
           will not fit. Values in the CSV file must be either "TRUE" or "FALSE". An example is shown in the file test.csv
           
## File Overview

test.csv: An example CSV file for reading a start from a file

### src:
#### std: The standard folder
This folder contains the main classes: CellMap, an object that keeps keeps track of the status of all cells; GameArea, an extension of JPanel that the data contained in the main CellMap is painted on; Window, a separate class for the JFrame containing all of the graphical elements; and Exe, the location of the `main()` method and the main CellMap (`public static CellMap MAP`).

#### io:
This package only contains 1 class, LifeIO, which is dedicated to prompting the user for input on start.
    
