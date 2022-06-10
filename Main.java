//import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.*;
import java.util.prefs.*;


class Main {
  public static final Random rand = new Random();

  public static void main(String[] args)throws FileNotFoundException, IOException{

    

    // create EVERYTHING!!!
    Scanner scn = new Scanner(System.in);
    
    
    mainMenu(scn);
    // Make a list of the dungeon floors
    Dungeon[] floorsList = createDungeons();
     
    // creates the player
    Player hero = printIntro(scn);
    
    //=============================================//
     //BEGIN PLAY
    while (hero.getHealth() > 0) {
      //for loop for each floor
      for(int i = 0; i<=floorsList.length; i++){

        hero.resetCampfire();
        Dungeon currentFloor = floorsList[i];

        //prints intro for floor
        System.out.println("Steping down the stairs, you obseve on your map that this floor has "+currentFloor.getRooms()+" rooms.\n(Enter anything to continue)");
        scn.next();
        System.out.println();

        //determines the min max levels for this floor
        int lvl[] = {floorsList[i].getMinLevel(),floorsList[i].getMaxLevel()};
    
        // makes a list of monsters
        Creature monstersList[] = createMonsters(currentFloor.getRandomLevel());

        

        




        
        
        //for loop for each room
        for(int f = 0; f<=currentFloor.getRooms(); f++){
          
          gameOver(currentFloor, hero);
          
          
          //choice menu
          whatWillYouDo(hero);


          //last room = boss
          if(f == currentFloor.getRooms()){
            System.out.println("As your aprouch the doors of the last room, you notice the bodys of other heros around you...");
            Creature boss = monstersList[rand.nextInt(monstersList.length)];
            boss.changeLevel(lvl[1]*2);
            Battle battle = new Battle(hero, boss, currentFloor);
          }
          else{

            //if its a obstacle
            if (currentFloor.isItObstacle()){
              new Obstacle(hero);
              System.out.println("(Enter anything to continue)");
                scn.next();
            }

            //if its a Treasure Room
            else if(currentFloor.isItTreasureRoom()){
              treasureRoom(currentFloor.getRandomLevel(), hero,scn);
              System.out.println("(Enter anything to continue)");
              scn.next();
              
            }
          
            //if not, then its a battle
            else{
              Battle battle = new Battle(hero, monstersList[rand.nextInt(monstersList.length)], currentFloor);
              System.out.println("(Enter anything to continue)");
                scn.next();
            }
          }

          //Condition, you died!
          
            gameOver(currentFloor, hero);
            
        }
      }
      break;
    }
    //END PLAY
    //=============================================//
    
  }

  private static Dungeon[] createDungeons() {
    //random number of rooms + treasure room chance
    Dungeon floorOne = new Dungeon(rand.nextInt(9 - 6) + 6, 25,1,3,1);
    Dungeon floorTwo = new Dungeon(rand.nextInt(11 - 6) + 6, 10,2,4,1);
    Dungeon floorThree = new Dungeon(rand.nextInt(12 - 8) + 8, 15,3,5,1);

    Dungeon[] floors = {floorOne,floorTwo,floorThree};
    
    return floors;
  }
  
  public static Creature[] createMonsters(int lvl) {
    // uncertain how levels for monsters will work. maybe a multiplyer? ex. level 4
    // rat multiples all its stats by 1.4 or 4?
    // also how do i make this a .csv file so it looks better

    // floor one monsters
    Creature goblin = new Creature(lvl, "Goblin", 3, 7,createWeapon(lvl, false));
    Creature rat = new Creature(lvl, "Rat", 1, 3,createWeapon(lvl, false));
    Creature skelly = new Creature(lvl, "Skelly", 2, 5,createWeapon(lvl, false));
    Creature golem = new Creature(lvl, "Stone Golem", 2, 15,createWeapon(lvl, false));
    Creature slime = new Creature(lvl, "Slime", 3, 5,createWeapon(lvl, false));

    Creature[] monsters = { goblin, rat, skelly, golem, slime };
    
    return monsters;
  }

  public static Player printIntro(Scanner scn) {
    System.out.println("-_-_-_-_-_-_-_-_-");
    System.out.println("TOWERING TOASTER");
    System.out.println("-_-_-_-_-_-_-_-_-\n");
    System.out.println("What is this adventers name? \n");

    String name = scn.next();

    System.out.println("\nA worthy name indeed! \n");

    // random starting health between 40-35
    int health = rand.nextInt(41 - 35) + 35;
    int attackPwr = rand.nextInt(4 - 2) + 2;
    Player hero = new Player(1, name, health,10,createWeapon(1, true));

    System.out.println("it seems you hero is a strong fellow with the a STRENGTH of " + attackPwr + ", HEALTH of "+ health + ", and is equiped with a "+hero.getWeapon().getName()+"\n");

    

    
    System.out.println("Standing before the gaping stone arches of the DUNGEON, you still your beating heart and take the plundge!\n(Enter anything to continue)");
    scn.next();
    System.out.println();

    

    return hero;
  }
  
  public static Weapon createWeapon(int weaponLevel, boolean whoIsIt){
    //int weaponLevel = rand.nextInt(lvl[1]-lvl[0])+lvl[0];
    String[][] weapons = {/*blades*/{"Sword","Katana","Dagger","Rapier","Kukri"},/*bludgeon*/{"Mace","War Hammer","Brass Knuckles","Flails","Staves"},/*Spears*/{"Spear","Pike","lance","Halberd","Glaive"}};
    int weaponName = rand.nextInt(5);
    int weaponType = rand.nextInt(3);
    return new Weapon(weaponLevel, weapons[weaponType][weaponName], weaponType, whoIsIt);

  }
  
  public static Weapon treasureRoom(int lvl,Creature creature,Scanner scn){
    Weapon newWeapon = createWeapon(lvl, true);

    System.out.println("Wandering into the next room, your greeted with a golden chest sitting in the middle of the room! as you open the chest your eyes are greeted with a LVL."+newWeapon.getLvl()+" "+newWeapon.getName()+"!");
    System.out.println("===CURRENT WEAPON===");
    creature.getWeapon().listStats();
    System.out.println("===NEW WEAPON===");
    newWeapon.listStats();
    System.out.println("\nWill you take the new weapon? (Y/N)");

    if(scn.next().toUpperCase().equals("Y")){
      System.out.println("You exchange your "+creature.getWeapon().getName()+" for the new "+newWeapon.getName()+"!");
      return newWeapon;
      }
      else{
          System.out.println("You decide its not worth it and continue to the next room.");
          return creature.getWeapon();
      }
  }
  
  public static void whatWillYouDo(Player hero){
    
    int choice = 100;
    System.out.println("===What Will You do?===");
    System.out.println("0) Continue to next room");
    System.out.println("1) "+hero.getName()+"'s Stats");
    System.out.println("2) Weapon Stats");
    System.out.println("3) Use Campfire ("+hero.getCampfire()+"/3)");
    try{
     choice = new Scanner(System.in).nextInt();
    }
    catch(InputMismatchException e){
      System.out.println("Inccorect Input Error: Only numerical inputs within the range allowed.");
      whatWillYouDo(hero);
    }
    switch(choice){
      case 0:
        break;
      
      case 1:
        hero.listStats();
        whatWillYouDo(hero);
        break;

      case 2:
        hero.getWeapon().listStats();
        whatWillYouDo(hero);
        break;

      case 3:
        if(hero.getCampfire()>0){
          System.out.println("You take some wood and worn rags and create a fire, the tender warth heals your aliments.");
          hero.heal(rand.nextInt(51-20)+20);
          hero.useCampfire();
          whatWillYouDo(hero);
          break;
        }
        else{
          System.out.println("You dont have enough matierals for a campfire.");
          whatWillYouDo(hero);
        }
      default:
        System.out.println("Inccorect Input Error: Only numerical inputs within the range allowed.");
        whatWillYouDo(hero);
        break;

    } 

  }
  
  public static void mainMenu(Scanner consoleScn){
    System.out.println();
    System.out.println("-_-_-_-_-_-_-_-_-");
    System.out.println("TOWERING TOASTER");
    System.out.println("-_-_-_-_-_-_-_-_-\n");
    System.out.println("0) New Game");
    System.out.println("1) Continue Game (Does not work yet)");
    System.out.println("2) Records");
    System.out.println("3) Tutorial");
    System.out.println("-_-_-_-_-_-_-_-_-\n");
    int choice = consoleScn.nextInt();

    switch(choice){
      case 0:
        break;
      
      case 1:
        System.out.println("Doesnt work yet");
        mainMenu(consoleScn);
        break;
      case 2:
       
       break;
    }
  }
  
  public static void gameOver(Dungeon currentFloor, Player hero)throws FileNotFoundException {

    
    System.out.println("You fall to the ground as your health falls to zero...\n===GAME OVER===");
    System.out.println("===Final Stats=== ");
    System.out.println("Floors Reached: "+currentFloor.getFloor());
    System.out.println("Level Reached: "+hero.getLevel());
    System.out.println("Money Obtained: $"+hero.getMoney());
    
      printRecords(hero);
     
       
    System.exit(0);
    
    
  


}
  public static void printRecords(Player hero)throws FileNotFoundException{
    int recordFloors = 0;
    int recordLevel = 0;
    int recordMoney = 0;

    

    //File things
    File recordFile = new File("Records.txt");

    Scanner fileScn = new Scanner(recordFile);
    
    PrintStream recordPrint = new PrintStream(new FileOutputStream(recordFile, true));

    System.out.println("pre while");
    //Grabs Values from file
      
     
      
      recordFloors = Integer.parseInt(fileScn.nextLine());
      recordLevel = Integer.parseInt(fileScn.nextLine());
      recordMoney = Integer.parseInt(fileScn.nextLine());  
    
    System.out.println("post while");
    System.out.println(recordFloors);
    System.out.println(recordLevel);
    System.out.println(recordMoney);

    hero.changeMoney(100, true);
    System.out.println("post change money");
    
    

    if(recordFloors < hero.getFloorsPassed()){
      System.out.println("NEW RECORD");
      recordPrint.println(hero.getFloorsPassed);
    }
    if(recordLevel < hero.getLevel()){
      System.out.println("NEW RECORD");
      
      
    }
    if(recordMoney < hero.getMoney()){
      System.out.println("NEW RECORD");
      recordPrint.write(hero.getMoney());
    }

    System.out.println("===Records=== ");
    System.out.println("Floors Reached: "+recordFloors);
    System.out.println("Level Reached: "+recordLevel);
    System.out.println("Money Obtained: $"+recordMoney);
  
  }
  
}