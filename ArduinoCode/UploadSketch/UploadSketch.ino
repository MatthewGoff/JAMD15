//Import Statements

const int Button1Pin=8;
const int Button2Pin=6;
const int LED1Pin=11;
const int LED2Pin=7;
const int LED3Pin=10;
const int LED4Pin=9;
const int IRLeftPin=1;
const int IRRightPin=2;
const int IRCenter=0;

Direction 	myDirection;
LocationList 	myPath;
Location 	myLocation;
Location 	myTarget;
Location 	endLocation;
Maze 		myMaze;
boolean  isRunning;


void setup() {
  // put your setup code here, to run once:
	myMaze = new Maze(16,16);
	myLocation = new Location(0,0);
	endLocation = new Location(7,7);
	myDirection = Direction.NORTH;

	isRunning = false;

	chooseTarget();
	myPath = myMaze.getPath(myLocation,myTarget);
}

void loop() {

	Direction directionToGo;
	
	//System.out.println("I am at " + myLocation + " going to " + endLocation);
	//System.out.println("I am at my destination: "+ (myLocation.equals(endLocation)));
	if (myLocation.equals(myTarget))
	{
		chooseTarget();
	}
	
	updateCell();
	
	//once we have all the walls we can sense, find the new path
	myPath = myMaze.getPath(myLocation, myTarget);
	//System.out.println(myPath);
	myPath.deleteFirst(); //gets rid of the location its already at
	nextLocation = myPath.getFirst();
	directionToGo = myLocation.getDirectionOf(nextLocation);
	
	while (myDirection != directionToGo)
	{
		//System.out.println("In order to go " + directionToGo + " I'm turning clockwise!");
		turnClockwise();
	}
	moveForward();
	
	//if we're at the desired location, go to the next location
	
	update();

}

turnClockwise()
{
  myDirection = myDirection.getClockwise();
}

turnCounterClockwise()
{
  myDirection = myDirection.getClockwise();
}

moveForward()
{
  
}
