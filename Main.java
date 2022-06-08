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
     
    

    // creates the player
    Player hero = printIntro();
    
    

    /*
    Creature test = new Creature(1, "test", 5, 5,createWeapon(lvl, true));
    test.getWeapon().listStats();
    for(int i = 0; i<10; i++){
      test.attackDmg();
      
      
      
    }
    */
    //=============================================//
     //BEGIN PLAY
    while (hero.getHealth() > 0) {
      //for loop for each floor
      for(int i = 0; i<floorsList.length; i++){

        hero.resetCampfire();
        Dungeon currentFloor = floorsList[i];

        //prints intro for floor
        System.out.println("Steping down the stairs, you obseve on your map that this dungeon has "+currentFloor.getRooms()+" rooms.\n(Enter anything to continue)");
        scn.next();
        System.out.println();

        //determines the min max levels for this floor
        int lvl[] = {floorsList[i].getMinLevel(),floorsList[i].getMaxLevel()};
    
        // makes a list of monsters
        Creature monstersList[] = createMonsters(lvl);

        




        
        
        //for loop for each room
        for(int f = 0; f<currentFloor.getRooms(); f++){

          //choice menu
          whatWillYouDo(hero);


          //last room = boss
          if(f == currentFloor.getRooms()){
            System.out.println("As your aprouch the doors of the last room, you notice the bodys of other heros around you...");
            Creature boss = monstersList[rand.nextInt(monstersList.length)];
            boss.changeLevel(lvl[1]*2);
            Battle battle = new Battle(hero, boss, currentFloor);
          }

          //if its a obstacle
          else if (currentFloor.isItObstacle()){
            new Obstacle(hero);
            System.out.println("(Enter anything to continue)");
              scn.next();
          }

          //if its a Treasure Room
          else if(currentFloor.isItTreasureRoom()){
            treasureRoom(lvl, hero);
            System.out.println("(Enter anything to continue)");
            scn.next();
            
          }

          //if not, then its a battle
          else{
            Battle battle = new Battle(hero, monstersList[rand.nextInt(monstersList.length)], currentFloor);
            System.out.println("(Enter anything to continue)");
              scn.next();
          }

          //Condition, you died!
          if(hero.getHealth() <= 0){
            System.out.println("You fall to the ground as your health falls to zero...\n===GAME OVER===");
            System.out.println("===Final Stats=== ");
            System.out.println("Floors Reached: "+currentFloor);
            System.out.println("Level Reached: "+hero.getLevel());
            System.out.println("Money Obtained: "+hero.getMoney());
            System.exit(0);
          }
        }
      }
      break;
    }
    //END PLAY
    //=============================================//
    
  }

  private static Dungeon[] createDungeons() {
    //random number of rooms + treasure room chance
    Dungeon floorOne = new Dungeon(rand.nextInt(9 - 6) + 6, 25,1,3);
    Dungeon floorTwo = new Dungeon(rand.nextInt(11 - 6) + 6, 10,2,4);
    Dungeon floorThree = new Dungeon(rand.nextInt(12 - 8) + 8, 15,3,5);

    Dungeon[] floors = {floorOne,floorTwo,floorThree};
    
    return floors;
  }
  public static Creature[] createMonsters(int[] lvl) {
    // uncertain how levels for monsters will work. maybe a multiplyer? ex. level 4
    // rat multiples all its stats by 1.4 or 4?
    // also how do i make this a .csv file so it looks better

    // floor one monsters
    Creature goblin = new Creature(1, "Goblin", 3, 7,createWeapon(lvl, false));
    Creature rat = new Creature(1, "Rat", 1, 3,createWeapon(lvl, false));
    Creature skelly = new Creature(1, "Skelly", 2, 5,createWeapon(lvl, false));
    Creature golem = new Creature(1, "Stone Golem", 2, 15,createWeapon(lvl, false));
    Creature slime = new Creature(1, "Slime", 3, 5,createWeapon(lvl, false));

    Creature[] monsters = { goblin, rat, skelly, golem, slime };
    
    return monsters;
  }

  public static Player printIntro() {
    System.out.println("-_-_-_-_-_-_-_-_-");
    System.out.println("TOWERING TOASTER");
    System.out.println("-_-_-_-_-_-_-_-_-\n");
    System.out.println("What is this adventers name? \n");

    String name = scn.next();

    System.out.println("\nA worthy name indeed! \n");

    // random starting health between 40-35
    int health = rand.nextInt(41 - 35) + 35;
    int attackPwr = rand.nextInt(4 - 2) + 2;
    int startingWeaponLevel[] = {1,2};
    Player hero = new Player(1, name, health,10,createWeapon(startingWeaponLevel, true));

    System.out.println("it seems you hero is a strong fellow with the a STRENGTH of " + attackPwr + ", HEALTH of "+ health + ", and is equiped with a "+hero.getWeapon().getName()+"\n");

    

    
    System.out.println("Standing before the gaping stone arches of the DUNGEON, you still your beating heart and take the plundge!\n(Enter anything to continue)");
    scn.next();
    System.out.println();

    

    return hero;
  }
  public static Weapon createWeapon(int[] lvl, boolean whoIsIt){
    int weaponLevel = rand.nextInt(lvl[1]-lvl[0])+lvl[0];
    String[][] weapons = {/*blades*/{"Sword","Katana","Dagger","Rapier","Kukri"},/*bludgeon*/{"Mace","War Hammer","Brass Knuckles","Flails","Staves"},/*Spears*/{"Spear","Pike","lance","Halberd","Glaive"}};
    int weaponName = rand.nextInt(5);
    int weaponType = rand.nextInt(3);
    return new Weapon(weaponLevel, weapons[weaponType][weaponName], weaponType, whoIsIt);

  }
  public static Weapon treasureRoom(int[] lvl,Creature creature){
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
    System.out.println("===What Will You do?===");
    System.out.println("0) Continue to next room");
    System.out.println("1) "+hero.getName()+"'s Stats");
    System.out.println("2) Weapon Stats");
    System.out.println("3) Use Campfire ("+hero.getCampfire()+"/3)");
    int choice = scn.nextInt();

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
        break;

    } 

  }


}