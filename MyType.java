// Mkhanyisi Gamedze
// CS231 Data Structures
// project 9 lab :  Graphs 

//import java.util.Hashmap;
import java.util.ArrayList;

// the direction class
public enum Direction {
	// enums
	NORTH, EAST, SOUTH, WEST;
	
	
	// get opposite directions field
	private Direction opposite;

	// static method sets default
    static {
        NORTH.opposite = SOUTH;
        SOUTH.opposite = NORTH;
        EAST.opposite = WEST;
        WEST.opposite = EAST;
    }
	
	// static method, that returns opposite direction
    public Direction opposite(Direction d) {
        return d.opposite;
    }




}