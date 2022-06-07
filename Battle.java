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
        System.out.println("Wandering into the next room, you spot a LVL."+enemy.getLevel()+" "+enemy.name+" weilding a "+enemy.getWeapon().getName()+"!\n");

        while(hero.getHealth()>0||enemy.getHealth()>0){

        int playerChoice = choiceMenu();
        int enemyChoice = rand.nextInt(3);
        System.out.println();
        clash(playerChoice, enemyChoice);
        }
        if(enemy.getHealth()>0){
          System.out.println("You felled the "+enemy.getLevel()+" "+enemy.name);
          hero.gainXp();
        }
    }
    public int choiceMenu(){
      System.out.println();
        System.out.println("What will you do!");
        System.out.println("==========");
        System.out.println("0) Attack");
        System.out.println("1) Block");
        System.out.println("2) Grab");
        System.out.println("==========");
        return scn.nextInt();
        
    }
    public void clash(int pChoice,int eChoice){
        //==========SUCCESSFUL==========
        //Successful Attack
        if(pChoice == 0 && eChoice == 2){
            int damage = hero.attackDmg();
            System.out.println("As the "+enemy.getName()+" goes to grab you, you quickly counter it with your "+hero.getWeapon().getName()+" dealing "+damage+" DMG!");
            enemy.takeDamage(damage);
        }
        //Successful Block
        else if(pChoice == 1 && eChoice == 0){
            int damage = hero.blockDmg();
            System.out.println("As the "+enemy.getName()+" goes to swing at you, you raise your "+hero.getWeapon().getName()+" blocking there attempt dealing "+damage+" DMG!");
            enemy.takeDamage(damage);
        }
        //Successful Grab
        else if(pChoice == 2 && eChoice == 1){
            int damage = hero.grabDmg();
            System.out.println("As the "+enemy.getName()+" preemptively tries to block, you leap forward grabing them, throwing them at the wall with all your strength dealing "+damage+" DMG!");
            enemy.takeDamage(damage);
        }
        //==========UNSUCCESSFUL==========
        //Unsuccessful Attack
        else if(pChoice == 2 && eChoice == 0){
            int damage = enemy.attackDmg();
            System.out.println("As the "+enemy.getName()+" goes to swing at you you, you attempt to grab them failing as you get cut taking "+damage+" DMG! (HP: "+(hero.getHealth()-damage)+")");
            hero.takeDamage(damage);
        }
        //Unsuccessful Block
        else if(pChoice == 0 && eChoice == 1){
            int damage = enemy.blockDmg();
            System.out.println("As the "+enemy.getName()+" raises there "+enemy.getWeapon().getName()+", you swing your "+hero.getWeapon().getName()+", however your stirke isnt strong enough and you bounch back taking "+damage+" DMG! (HP: "+(hero.getHealth()-damage)+")");
            hero.takeDamage(damage);
        }
        //Unsuccessful Grab
        else if(pChoice == 1 && eChoice == 2){
            int damage = enemy.grabDmg();
            System.out.println("As the "+enemy.getName()+" goes to grab you, you raise your "+hero.getWeapon().getName()+" in attempts to block, however they still grab you throwing you agenst the wall taking "+damage+" DMG! (HP: "+(hero.getHealth()-damage)+")");
            hero.takeDamage(damage);
        }
        else{
          System.out.println("You both attempt the same move and akwardly bounce off eachother!");
        }
    }
}