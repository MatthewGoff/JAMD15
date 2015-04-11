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

Direction 	myDirection;
LocationList 	myPath;
Location 	myLocation;
Location 	myTarget;
Location 	endLocation;
Maze 		myMaze;

boolean  isRunning;


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
  
  
  myMaze = new Maze(16,16);
  myLocation = new Location(0,0);
  endLocation = new Location(7,7);
  myDirection = Direction.NORTH;

  isRunning = false;

  chooseTarget();
  myPath = myMaze.getPath(myLocation,myTarget);
}

void loop()
{
  if (isRunning)
  {
    proceed();
  }
  update();
}

void addWall(Direction direction)
{
	myMaze.addWall(myLocation, direction);
}

void proceed() {

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
      addWall(myDirection.getCounterClockwise());
  }
	
  if (analogRead(IRCenterPin)>200)
  {
    addWall(analogRead(IRCenterPin));
  }
  
  if (analogRead(IRRightPin>200)
  {
    addWall(myDirection.getClockwise());
  }
}

void chooseTarget()
{
	if (myLocation.equals(new Location(7,7)))
	{
		myTarget = new Location (0,0);
	} else 
	{
		myTarget = new Location(7,7);
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
	myLocation = new Location(0,0);
	myDirection = Direction.NORTH;
}

