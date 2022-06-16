import java.util.Random;

public class Player extends Creature{

    private int level;
    private String name;
    //private int attackPwr;
    private int money;
    private int maxHealth;
    private int xpLimit = 100;
    private int xp;
    private int campFire = 3;
    private Random rand = new Random();
    private int floorsPassed;

    public Player(int level, String name, int health, int money, Weapon weapon){
        
        super(level,name,health,weapon);
        this.level = level;
        this.name = name;
        //this.attackPwr = attackPwr;
        this.health = health;
        this.money = money;
        maxHealth = health;
        this.weapon = weapon;
        
    }
    public void changeMoney(int gains, boolean affect){
        if(!affect){
            if(money-gains <= 0){
                System.out.println("You lose the rest of your money.(-"+getMoney()+")");
                money = 0;
            }
            else{
                money-=gains;
                System.out.println();
                System.out.println("You lose $"+gains+" ($"+getMoney()+")");
            }
        }
        else{
            money+=gains;
            System.out.println("You gain $"+gains+" ($"+getMoney()+")");
        }
    }
    public int getMoney() {
        return money;
    }
    public void heal(int healings) {
        if(healings+health >= maxHealth){
            health = maxHealth;
            System.out.println("You heal to full heath! (HP:"+health+")");
        }
        else{
            health+=healings;
            System.out.println("You heal "+healings+" HP! (HP:"+health+")");
        }
    }
    public void listStats() {
        System.out.println("---"+name+"---");
        System.out.println("Weapon: "+weapon.getName());
        System.out.println("Money: "+money);
        System.out.println("Level: "+level);
        System.out.println("XP: "+xp+"/"+xpLimit);
        System.out.println("HP: "+health+"/"+maxHealth);
        System.out.println("------------");
        
    }
    public void gainXp(){
      int gains = rand.nextInt(51 - 10) + 10;
        if(xp+gains>=xpLimit){
            System.out.println("You gained "+(xp+gains)+"XP!");
            levelUp();
            xp = xp+gains-xpLimit;
        }
        else{
            xp += gains;
            System.out.println("You gained "+gains+"XP!");
            
        }
    }
    public void levelUp(){
        level+=1;
        int addMaxHealth = level*rand.nextInt(11 - 6) + 6;
        System.out.println("===LEVEL UP!===");
        System.out.println("LVL." + (level - 1) + " to LVL."+level);
        System.out.println("MAX HP " +health  + " to "+(health+addMaxHealth));
        System.out.println("You heal "+10*level+"HP");
      System.out.println("================");
      
        health+=addMaxHealth;
    }
    public int getCampfire(){
        return campFire;
    }
    public void resetCampfire(){
        campFire = 3;
    }
    public void useCampfire(){
        campFire -= 1;
    }
    public int getFloorsPassed(){
        return floorsPassed;
    }
    public void passFloor(){
        floorsPassed++;
    }
    public int getLevel() {
        return level;
    }
    public void changeWeapon(Weapon newWeapon){
        weapon = newWeapon;
    }
  
    
}