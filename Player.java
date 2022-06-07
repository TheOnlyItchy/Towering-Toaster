import java.util.Random;

public class Player extends Creature{
    private int level;
    private String name;
    private int attackPwr;
    private int health;
    private int money;
    private int maxHealth;
    private Weapon weapon;
    private int xpLimit = 100;
    private int xp;
    private Random rand = new Random();
    public Player(int level,String name,int health, int money,Weapon weapon){
        
        super(level, name, 0, health, weapon);
        this.level = level;
        this.name = name;
        this.attackPwr = attackPwr;
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
        System.out.println("Weapon: "+weapon);
        System.out.println("Level: "+level);
        System.out.println("Money: "+money);
        System.out.println("------------");
        
    }
    public void gainXp(int gains){
        if(xp+gains>=xpLimit){
            System.out.println("You gained "+(xp+gains)+"XP!");
            levelUp();
            xp = xp+gains-xpLimit;
        }
        else{
            xp+=gains;
            System.out.println("You gained "+xp+"XP!");
            
        }
    }
    public void levelUp(){
        level+=1;
        int addMaxHealth = level*rand.nextInt(11 - 6) + 6;
        System.out.println("===LEVEL UP!===");
        System.out.println("LVL." + (level - 1) + " to LVL."+level);
        System.out.println("MAX HP: " +health  + " to MAX HP:"+(health+addMaxHealth));
        System.out.println("You heal "+10*level+"HP");
        health+=addMaxHealth;
    }
  
    
}