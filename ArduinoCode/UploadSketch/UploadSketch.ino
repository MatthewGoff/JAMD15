#include <MazeUtils.h>

//Import Statements

const int Button1Pin=8;
const int Button2Pin=6;
const int LED1Pin=11;
const int LED2Pin=7;
const int LED3Pin=10;
const int LED4Pin=9;
const int IRLeftPin=1;
const int IRRightPin=2;
const int IRCenterPin=0;

const int dirPinRight = 2;
const int stepPinRight = 3;
const int dirPinLeft = 4;
const int stepPinLeft = 5;

Location 	myTarget(7,7);
Maze myMaze(16,16);
Location myLocation(0,0);
Location endLocation(7,7);
LocationList myPath = myMaze.getPath(myLocation,myTarget);

boolean  isRunning;
int myDirection;

void setup() {
  pinMode(Button1Pin, INPUT);
  pinMode(Button2Pin, INPUT);
  pinMode(LED1Pin, OUTPUT);
  pinMode(LED2Pin, OUTPUT);
  pinMode(LED3Pin, OUTPUT);
  pinMode(LED4Pin, OUTPUT);
  pinMode(IRLeftPin, INPUT);
  pinMode(IRRightPin, INPUT);
  pinMode(IRCenterPin, INPUT);
  
  pinMode(dirPinLeft, OUTPUT);
  pinMode(stepPinLeft, OUTPUT);
  pinMode(dirPinRight, OUTPUT);
  pinMode(stepPinRight, OUTPUT);

  isRunning = false;

  chooseTarget();
}

void loop()
{
  if (isRunning)
  {
    proceed();
  }
  update();
}

void addWall(int direction)
{
	myMaze.addWall(myLocation, direction);
}

void proceed() {

	int directionToGo;
        Location nextLocation(0,0);
	
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
}

void turnClockwise()
{
  digitalWrite(dirPinLeft, LOW);
  digitalWrite(dirPinRight, LOW);
  
  int i;
  for (i = 0; i<495; i++)
  {
    digitalWrite(stepPinLeft, LOW);
    digitalWrite(stepPinLeft, HIGH);
    digitalWrite(stepPinRight, LOW);
    digitalWrite(stepPinRight, HIGH);
    delayMicroseconds(2000);
  }
  
  
}

void turnCounterClockwise()
{
  digitalWrite(dirPinLeft, HIGH);
  digitalWrite(dirPinRight, HIGH);
  
  int i;
  for (i = 0; i<498; i++)
  {
    digitalWrite(stepPinLeft, LOW);
    digitalWrite(stepPinLeft, HIGH);
    digitalWrite(stepPinRight, LOW);
    digitalWrite(stepPinRight, HIGH);
    delayMicroseconds(2000);
  } 
}

void moveForward()
{
  digitalWrite(dirPinLeft, LOW);
  digitalWrite(dirPinRight, HIGH);
  
  int i;
  for (i = 0; i<1000; i++)
  {
    digitalWrite(stepPinLeft, LOW);
    digitalWrite(stepPinLeft, HIGH);
    digitalWrite(stepPinRight, LOW);
    digitalWrite(stepPinRight, HIGH);
    delayMicroseconds(3000);
  }
  
  myLocation = myLocation.getAdjacent(myDirection);
  myPath.deleteFirst();
}

void updateCell()
{  
  if (analogRead(IRLeftPin)>200)
  {
      addWall((myDirection-1)%4);
  }
	
  if (analogRead(IRCenterPin)>200)
  {
    addWall(analogRead(IRCenterPin));
  }
  
  if (analogRead(IRRightPin>200))
  {
    addWall((myDirection+1)%4);
  }
}

void chooseTarget()
{
	if (myLocation.equals(Location(7,7)))
	{
		myTarget = Location (0,0);
	} else 
	{
		myTarget = Location(7,7);
	}
}

void update()
{
  if (!digitalRead(Button1Pin))
  {
    isRunning = !isRunning;
  }
  if (!digitalRead(Button2Pin))
  {
    isRunning = false;
    reset();
  }
  
  if (isRunning)
  {
    digitalWrite(LED4Pin,HIGH);
  }
  else
  {
    digitalWrite(LED4Pin,LOW);
  }
}

void reset()
{
	myLocation = Location(0,0);
	myDirection = 0;
}

