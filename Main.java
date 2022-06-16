
//import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

class Main {
  public static final Random rand = new Random();

  public static void main(String[] args) throws FileNotFoundException {

    // create EVERYTHING!!!
    Scanner scn = new Scanner(System.in);

    mainMenu(scn);
    // Make a list of the dungeon floors
    int floorNum = 0;

    // creates the player
    Player hero = printIntro(scn);

    // =============================================//
    // BEGIN PLAY
    while (hero.getHealth() > 0) {
      // for loop for each floor
      floorNum++;

      hero.resetCampfire();
      Dungeon currentFloor = createDungeons(floorNum);

      // prints intro for floor
      System.out.println("===FLOOR " + currentFloor.getFloor() + "===");
      System.out.println(
          "Steping down the stairs, you obseve on your map that this floor has " + currentFloor.getRooms() + " rooms.");
      continuePlay(scn);

      // for loop for each room
      for (int f = 0; f <= currentFloor.getRooms(); f++) {
        // passes floor
        hero.passFloor();

        // choice menu
        whatWillYouDo(hero);

        // last room = boss
        if (f == currentFloor.getRooms()) {
          System.out.println(
              "As your aprouch the doors of the last room, you notice the bodys of other heros around you...\n==BOSS==");
          Creature boss = createMonsters(currentFloor, rand);
          boss.changeLevel(currentFloor.getRandomLevel() * 2);
          new Battle(hero, boss, currentFloor);
        } else {

          // if its a obstacle
          if (currentFloor.isItObstacle()) {
            new Obstacle(hero);
            continuePlay(scn);

          }

          // if its a Treasure Room
          else if (currentFloor.isItTreasureRoom()) {
            hero.changeWeapon(treasureRoom(currentFloor.getRandomLevel(), hero, scn));
            continuePlay(scn);

          }

          // if not, then its a battle
          else {
            new Battle(hero, createMonsters(currentFloor, rand), currentFloor);
            continuePlay(scn);
          }
        }

        // Condition, you died!
        if (hero.getHealth() <= 0) {
          gameOver(currentFloor, hero);
        }
      }

      break;
    }
    // END PLAY
    // =============================================//

  }

  public static Dungeon createDungeons(int floorNum) {

    // floors will always have 5 floors plus 1 - 3 floors times the floornum //
    // random treasure room chance 25% to 10% .
    // min Level = base floornum // max level = floornum + 2 // the floor level is
    // floor num.
    Dungeon currentDungeon = new Dungeon(5 + ((rand.nextInt(4 - 1) + 1) * floorNum), rand.nextInt(26 - 10) + 10,
        floorNum, floorNum + 2, floorNum);
    return currentDungeon;
  }

  public static Creature createMonsters(Dungeon lvl, Random rand) {
    // uncertain how levels for monsters will work. maybe a multiplyer? ex. level 4
    // rat multiples all its stats by 1.4 or 4?
    // also how do i make this a .csv file so it looks better

    String names[] = { "Mummy", "Goblin", "Rat", "Skelly", "Golem", "Slime", "Possesed Armour", "Undead", "Witch",
        "Distraught Hero", "Granite Behemoth" };
    return new Creature(lvl.getRandomLevel(), names[rand.nextInt(names.length)], rand.nextInt(16 - 3) + 3,
        createWeapon(lvl.getRandomLevel(), false));
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
    Player hero = new Player(1, name, health, 10, createWeapon(1, true));

    System.out.println("it seems you hero is a strong fellow with the a STRENGTH of " + attackPwr + ", HEALTH of "
        + health + ", and is equiped with a " + hero.getWeapon().getName() + "\n");

    System.out.println(
        "Standing before the gaping stone arches of the DUNGEON, you still your beating heart and take the plundge!");
    continuePlay(scn);
    System.out.println();

    return hero;
  }

  public static Weapon createWeapon(int weaponLevel, boolean whoIsIt) {
    // int weaponLevel = rand.nextInt(lvl[1]-lvl[0])+lvl[0];
    String[][] weapons = { /* blades */{ "Sword", "Katana", "Dagger", "Rapier", "Kukri" },
        /* bludgeon */{ "Mace", "War Hammer", "Brass Knuckles", "Flails", "Staves" },
        /* Spears */{ "Spear", "Pike", "lance", "Halberd", "Glaive" } };
    int weaponName = rand.nextInt(5);
    int weaponType = rand.nextInt(3);
    return new Weapon(weaponLevel, weapons[weaponType][weaponName], weaponType, whoIsIt);

  }

  public static Weapon treasureRoom(int lvl, Creature creature, Scanner scn) {
    Weapon newWeapon = createWeapon(lvl, true);

    System.out.println(
        "Wandering into the next room, your greeted with a golden chest sitting in the middle of the room! as you open the chest your eyes are greeted with a LVL."
            + newWeapon.getLvl() + " " + newWeapon.getName() + "!");
    System.out.println("===CURRENT WEAPON===");
    creature.getWeapon().listStats();
    System.out.println("===NEW WEAPON===");
    newWeapon.listStats();
    System.out.println("\nWill you take the new weapon? (Y/N)");

    if (scn.next().toUpperCase().equals("Y")) {
      System.out
          .println("You exchange your " + creature.getWeapon().getName() + " for the new " + newWeapon.getName() + "!");
      return newWeapon;
    } else {
      System.out.println("You decide its not worth it and continue to the next room.");
      return creature.getWeapon();
    }
  }

  public static void whatWillYouDo(Player hero) {

    int choice = 100;
    System.out.println("===What Will You do?===");
    System.out.println("0) Continue to next room");
    System.out.println("1) " + hero.getName() + "'s Stats");
    System.out.println("2) Weapon Stats");
    System.out.println("3) Use Campfire (" + hero.getCampfire() + "/3)");
    try {
      choice = new Scanner(System.in).nextInt();
    } catch (InputMismatchException e) {
      System.out.println("Inccorect Input Error: Only numerical inputs within the range allowed.");
      whatWillYouDo(hero);
    }
    switch (choice) {
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
        if (hero.getCampfire() > 0) {
          System.out
              .println("You take some wood and worn rags and create a fire, the tender warth heals your aliments.");
          hero.heal(rand.nextInt(51 - 20) + 20);
          hero.useCampfire();
          whatWillYouDo(hero);
          break;
        } else {
          System.out.println("You dont have enough matierals for a campfire.");
          whatWillYouDo(hero);
        }
      default:
        System.out.println("Inccorect Input Error: Only numerical inputs within the range allowed.");
        whatWillYouDo(hero);
        break;

    }

  }

  public static void mainMenu(Scanner consoleScn) throws FileNotFoundException {
    int choice = 0;
    System.out.println();
    System.out.println("-_-_-_-_-_-_-_-_-");
    System.out.println("TOWERING TOASTER");
    System.out.println("-_-_-_-_-_-_-_-_-\n");
    System.out.println("0) New Game");
    System.out.println("1) Continue Game (Does not work yet)");
    System.out.println("2) Records");
    System.out.println("3) Tutorial");
    System.out.println("-_-_-_-_-_-_-_-_-\n");
    try{
      choice = consoleScn.nextInt();
    } catch(InputMismatchException e){
      System.out.println("Inccorect Input Error: Only numerical inputs within the range allowed.");
      mainMenu(consoleScn);
    }
    switch (choice) {
      case 0:
        break;

      case 1:
        System.out.println("Doesnt work yet");
        mainMenu(consoleScn);
        break;
      case 2:
        printRecords(new Player(0, "test", 1, 0, new Weapon(0, "test", 0, true)));
        mainMenu(consoleScn);
        break;
      case 3:
        Scanner fileScn = new Scanner(new File("Tutorial.txt"));
        while (fileScn.hasNextLine()) {
          String line = fileScn.nextLine();
          System.out.println(line);
        }
        fileScn.close();
        mainMenu(consoleScn);
        break;
      default:
        System.out.println("Inccorect Input Error: Only numerical inputs within the range allowed.");
        mainMenu(consoleScn);
    }
  }

  public static void gameOver(Dungeon currentFloor, Player hero) throws FileNotFoundException {

    System.out.println("You fall to the ground as your health falls to zero...\n===GAME OVER===");
    System.out.println("===Final Stats=== ");
    System.out.println("Floors Reached: " + currentFloor.getFloor());
    System.out.println("Level Reached: " + hero.getLevel());
    System.out.println("Money Obtained: $" + hero.getMoney());
    System.out.println("================= ");

    printRecords(hero);

    System.exit(0);

  }

  public static void printRecords(Player hero) throws FileNotFoundException {
    int recordFloors = 0;
    int recordLevel = 0;
    int recordMoney = 0;

    // File things
    File recordFile = new File("Records.txt");

    Scanner fileScn = new Scanner(recordFile);

    // Grabs Values from file

    recordFloors = Integer.parseInt(fileScn.nextLine());
    recordLevel = Integer.parseInt(fileScn.nextLine());
    recordMoney = Integer.parseInt(fileScn.nextLine());

    PrintStream recordPrint = new PrintStream(recordFile);

    // file contents store, deletes file, checks if original value is greater, if so
    // replaces the original value, if not, repirnts the orignial value
    // maybe a better way to do this using fileWriter? append somehow?

    System.out.println("===Records=== ");
    if (recordFloors < hero.getFloorsPassed()) {
      System.out.println("(NEW RECORD) Floors Reached: " + hero.getFloorsPassed());
      recordPrint.println(hero.getFloorsPassed());
    } else {
      recordPrint.println(recordFloors);
      System.out.println("Floors Reached: " + recordFloors);
    }
    if (recordLevel < hero.getLevel()) {
      System.out.println("(NEW RECORD) Level Reached: " + hero.getLevel());
      recordPrint.println(hero.getLevel());
    } else {
      System.out.println("Level Reached: " + recordLevel);
      recordPrint.println(recordLevel);
    }
    if (recordMoney < hero.getMoney()) {
      System.out.println("(NEW RECORD) Money Obtained: $" + hero.getMoney());
      recordPrint.println(hero.getMoney());
    } else {
      System.out.println("Money Obtained: $" + recordMoney);
      recordPrint.println(recordMoney);
    }
    System.out.println("============= ");

    fileScn.close();
  }

  public static void continuePlay(Scanner scn) {
    System.out.println("(Enter anything to continue)");
    scn.next();
  }

}