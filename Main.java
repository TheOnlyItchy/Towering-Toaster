//import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


class Main {
  public static final Scanner scn = new Scanner(System.in);
  public static final Random rand = new Random();

  public static void main(String[] args) {

    

    // create EVERYTHING!!!
    // makes a list of monsters
    Creature[] monstersList = createMonsters();

    // Make a list of the dungeon floors
    Dungeon[] floorsList = createDungeons();

    // creates the player
    Player hero = printIntro();
      System.out.println(hero.getHealth());

    // BEGIN PLAY
    while (hero.getHealth() > 0) {
      //for loop for each floor
      for(int i = 0; i<floorsList.length; i++){
        Dungeon currentFloor = floorsList[i];
        System.out.println("Steping down the stairs, you obseve on your map that this dungeon has "+currentFloor.getRooms()+" rooms.");

        //for loop for each room
        for(int f = 0; f<currentFloor.getRooms(); f++){
          //if its a obstacle
          if (currentFloor.isItObstacle()){
            new Obstacle();
          }
          //if its a Rreasure Room
          if(currentFloor.isItTreasureRoom()){

          }
        }
      }
      break;
    }

  }

  private static Dungeon[] createDungeons() {
    //random number of rooms + treasure room chance
    Dungeon floorOne = new Dungeon(rand.nextInt(9 - 6) + 6, 25);
    Dungeon floorTwo = new Dungeon(rand.nextInt(11 - 6) + 6, 10);
    Dungeon floorThree = new Dungeon(rand.nextInt(12 - 8) + 8, 15);

    Dungeon[] floors = {floorOne,floorTwo,floorThree};
    System.out.println("Dungeon Loaded");
    return floors;
  }

  public static Creature[] createMonsters() {
    // uncertain how levels for monsters will work. maybe a multiplyer? ex. level 4
    // rat multiples all its stats by 1.4 or 4?
    // also how do i make this a .csv file so it looks better

    // floor one monsters
    Creature goblin = new Creature(1, "Goblin", 3, 7);
    Creature rat = new Creature(1, "Rat", 1, 3);
    Creature skelly = new Creature(1, "Skelly", 2, 5);
    Creature golem = new Creature(1, "Stone Golem", 2, 15);
    Creature slime = new Creature(1, "Slime", 3, 5);

    Creature[] monsters = { goblin, rat, skelly, golem, slime };
    System.out.println("Monsters Loaded");
    return monsters;
  }

  public static Player printIntro() {
    System.out.println("-_-_-_-_-_-_-_-_-");
    System.out.println("TOWERING TOASTER");
    System.out.println("-_-_-_-_-_-_-_-_-\n");
    System.out.println("What is this adventers name? \n");

    String name = scn.next();

    System.out.println("\nA worthy name indeed! \n");

    // random starting health between 8-12
    int health = rand.nextInt(21 - 15) + 15;
    int attackPwr = rand.nextInt(4 - 2) + 2;

    System.out.println("it seems you hero is a strong fellow with the a STRENGTH of " + attackPwr + " and a HEALTH of "
        + health + ".\n");

    Player hero = new Player(1, name, attackPwr, health,0);
    System.out.println("Standing before the gaping stone arches of the DUNGEON, you still your beating heart and take the plundge!\n");
    return hero;
  }


}