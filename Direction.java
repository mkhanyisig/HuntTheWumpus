// Mkhanyisi Gamedze
// CS231 Data Structures
// project 9 lab :  Graphs 

//import java.util.Hashmap;
import java.util.ArrayList;

// the direction class
public enum Direction {
	// enums
	NORTH, EAST, SOUTH, WEST;
	
    /*
	// static method sets default
    static {
        NORTH.opposite = SOUTH;
        SOUTH.opposite = NORTH;
        EAST.opposite = WEST;
        WEST.opposite = EAST;
    }
    */ 
	
	// static method, that returns opposite direction
    public static Direction opposite(Direction d) {
        if (d==NORTH){
            return SOUTH;
        }
        else if(d==SOUTH){
            return NORTH;
        }
        else if(d==EAST){
            return WEST;
        }
        else{
            return EAST;
        }

    }




}
