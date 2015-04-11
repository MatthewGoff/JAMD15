

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

boolean isRunning;
int myDirection;
boolean myMaze[16][16];
int myLocation[2];
int dirToGo;


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
}

void loop()
{
  if (isRunning)
  {
    proceed();
  }
  update();
}

void proceed()
{
  
 myMaze[myLocation[0]][myLocation[1]] = isBad();

  do
  {
    dirToGo = random(4);
    while (myDirection != dirToGo)
    {
      //System.out.println("In order to go " + directionToGo + " I'm turning clockwise!");
      turnClockwise();
    }
  } while (getAdjacent(dirToGo)&&analogRead(IRCenterPin)>200);
  moveForward();
}

boolean isBad()
{
  return (((analogRead(IRLeftPin)>200)||getAdjacent((myDirection-1)%4))&&((analogRead(IRCenterPin)>200)||getAdjacent(myDirection))&&((analogRead(IRRightPin)>200)||getAdjacent((myDirection+1)%4)));
}

boolean getAdjacent(int dir)
{
  if (dir==0) {return myMaze[myLocation[0]][myLocation[1]+1];}
  if (dir==1) {return myMaze[myLocation[0]+1][myLocation[1]];}
  if (dir==2) {return myMaze[myLocation[0]][myLocation[1]-1];}
  if (dir==3) {return myMaze[myLocation[0]+1][myLocation[1]];}
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
  myDirection = (myDirection+1)%4;
  
  
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
  myDirection = (myDirection-1)%4;
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
  if (myDirection == 0) {myLocation[1]++;}
  else if (myDirection == 1) {myLocation[0]++;}
  else if (myDirection == 2) {myLocation[1]--;}
  else if (myDirection == 3) {myLocation[0]--;}

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
	myLocation[0]=0;
        myLocation[1]=0;
	myDirection = 0;
}

