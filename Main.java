import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class Main {
  public static final Scanner scn = new Scanner(System.in);
  public static final Random rand = new Random();
  public static void main(String[] args) {
    

    //makes a list of monsters
    ArrayList<String> Monsters = new ArrayList<>();
    Monsters.add("Penis");
    Creature[] epic = createMonsters();

    //creates the player
    Player hero = printIntro();

    //the dungeon class has a certain amount of rooms along with certain monsters that can appear.
    Dungeon FloorOne = new Dungeon(5, epic);

    
    
  }
  public static Creature[] createMonsters(){
    //uncertain how levels for monsters will work. maybe a multiplyer? ex. level 4 rat multiples all its stats by 1.4 or 4?
    //also how do i make this a .csv file so it looks better

    
    // try (Scanner scanner = new Scanner(new File("book.csv"));) {
    //while (scanner.hasNextLine()) {
    //    records.add(getRecordFromLine(scanner.nextLine()));
    //}
    
    Creature goblin = new Creature(1, "Goblin", 3, 7);
    Creature rat = new Creature(1,"Rat",1,3);
    Creature skelly = new Creature(1, "Skelly", 2, 5);
    Creature golem = new Creature(1,"Stone Golem", 2,15);
    Creature slime = new Creature(1,"Slime",3,5);

    Creature[] monsters = {goblin,rat,skelly,golem,slime};

    return monsters;
  }
  public static Player printIntro(){
    System.out.println("-_-_-_-_-_-_-_-_-");
    System.out.println("TOWERING TOASTER");
    System.out.println("-_-_-_-_-_-_-_-_-\n");
    System.out.println("What is this adventers name? \n");

    String name = scn.next();

    System.out.println("\nA worthy name indeed! \n");
    
    //random starting health between 8-12
    int health = rand.nextInt(21-15) + 15 ;
    int attackPwr = rand.nextInt(4-2) + 2;

    System.out.println("it seems you hero is a strong fellow with the a STRENGTH of " +attackPwr+" and a HEALTH of "+health+".\n");
    
    Player hero = new Player(1,name,attackPwr,health);

    return hero;
  }
}