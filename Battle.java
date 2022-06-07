import java.util.Scanner;
import java.util.Random;

public class Battle {
    Player hero;
    Creature enemy;
    public static final Scanner scn = new Scanner(System.in);
    public static final Random rand = new Random();

    public Battle(Player hero, Creature enemy, Dungeon floor){
        this.hero = hero;
        this.enemy = enemy; 
        

        //makes the level the correct floor level
        enemy.changeLevel(floor.getRandomLevel());

        //=======================//
        System.out.println("Wandering into the next room, you spot a LVL."+enemy.getLevel()+" "+enemy.name+"!\n");

        while(hero.getHealth()>0||enemy.getHealth()>0){

        int playerChoice = choiceMenu();
        int enemyChoice = rand.nextInt(3);
        clash(playerChoice, enemyChoice);
        }
    }
    public int choiceMenu(){
        System.out.println("What will you do!");
        System.out.println("==========");
        System.out.println("0) Attack");
        System.out.println("1) Block");
        System.out.println("2) Grab");
        System.out.println("==========");
        return scn.nextInt();
    }
    public void clash(int pChoice,int eChoice){
        //Successful Attack
        if(pChoice == 0 && eChoice == 2){
            System.out.println("As the "+enemy.getName()+" goes to grab you, you quickly counter it with a slash!")
        }
        //Successful Block
        if(pChoice == 1 && eChoice == 0){

        }
        //Successful Grab
        if(pChoice == 2 && eChoice == 1){

        }
    }
}