//import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


class Main {
  public static final Scanner scn = new Scanner(System.in);
  public static final Random rand = new Random();

  public static void main(String[] args) {

    

    // create EVERYTHING!!!

     // Make a list of the dungeon floors
     Dungeon[] floorsList = createDungeons();
     int minMax[] = {floorsList[0].getMinLevel(),floorsList[0].getMaxLevel()};

    // makes a list of monsters
    Creature[] monstersList = createMonsters(minMax);

    // creates the player
    Player hero = printIntro();
      
    while (true){
      createWeapon(minMax);
      
      scn.next();
    }
      
      /*
     //BEGIN PLAY
    while (hero.getHealth() > 0) {
      //for loop for each floor
      for(int i = 0; i<floorsList.length; i++){
        Dungeon currentFloor = floorsList[i];
        System.out.println("Steping down the stairs, you obseve on your map that this dungeon has "+currentFloor.getRooms()+" rooms.");

        





        
        //for loop for each room
        for(int f = 0; f<currentFloor.getRooms(); f++){
          //if its a obstacle
          if (currentFloor.isItObstacle()){
            new Obstacle(hero);
          }
          //if its a Treasure Room
          if(currentFloor.isItTreasureRoom()){

          }
        }
      }
      break;
    }
    */
  }

  private static Dungeon[] createDungeons() {
    //random number of rooms + treasure room chance
    Dungeon floorOne = new Dungeon(rand.nextInt(9 - 6) + 6, 25,1,3);
    Dungeon floorTwo = new Dungeon(rand.nextInt(11 - 6) + 6, 10,2,4);
    Dungeon floorThree = new Dungeon(rand.nextInt(12 - 8) + 8, 15,3,5);

    Dungeon[] floors = {floorOne,floorTwo,floorThree};
    System.out.println("Dungeon Loaded");
    return floors;
  }

  public static Creature[] createMonsters(int[] lvl) {
    // uncertain how levels for monsters will work. maybe a multiplyer? ex. level 4
    // rat multiples all its stats by 1.4 or 4?
    // also how do i make this a .csv file so it looks better

    // floor one monsters
    Creature goblin = new Creature(1, "Goblin", 3, 7,createWeapon(lvl));
    Creature rat = new Creature(1, "Rat", 1, 3,createWeapon(lvl));
    Creature skelly = new Creature(1, "Skelly", 2, 5,createWeapon(lvl));
    Creature golem = new Creature(1, "Stone Golem", 2, 15,createWeapon(lvl));
    Creature slime = new Creature(1, "Slime", 3, 5,createWeapon(lvl));

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

    System.out.println("it seems you hero is a strong fellow with the a STRENGTH of " + attackPwr + ", HEALTH of "
        + health + ", and is equiped with a \n");

    int startingWeaponLevel[] = {1,1};
    Player hero = new Player(1, name, attackPwr, health,100,createWeapon(startingWeaponLevel));
    System.out.println("Standing before the gaping stone arches of the DUNGEON, you still your beating heart and take the plundge!\n");
    return hero;
  }
  public static Weapon createWeapon(int[] lvl){
    int weaponLevel = rand.nextInt(lvl[1]-lvl[0])+lvl[0];
    String[][] weapons = {/*blades*/{"Sword","Katana","Dagger","Rapier","Kukri"},/*bludgeon*/{"Mace","War Hammer","Brass Knuckles","Flails","Staves"},/*Spears*/{"Spear","Pike","lance","Halberd","Glaive"}};
    int weaponName = rand.nextInt(5);
    int weaponType = rand.nextInt(3);
    return new Weapon(weaponLevel, weapons[weaponType][weaponName], weaponType);

  }


}