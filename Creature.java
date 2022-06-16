import java.util.Random;

public class Creature{
    int level;
    String name;
    int health;
    Weapon weapon;
    int maxHealth;
    Random rand = new Random();
    public Creature(int level,String name, int health, Weapon weapon){
        this.level = level;
        this.name = name;
        this.health = health*level;
        this.weapon = weapon;
        maxHealth = health;
        
    }

    public int getHealth(){
        return health;
    }
    public int getMaxHealth() {
        return maxHealth;
    }

    public void takeDamage(int damage){
        health -= damage;
        System.out.println("You take -"+damage+" DMG! (HP:"+this.health+")");
    }
  public void takeDamageNoTxt(int damage){
        health -= damage;
        
    }

    public void heal(int healing){
        health += healing;
        System.out.println("You heal +"+healing+" HP! (HP:"+health+")");
    }

    public int attackDmg(){
        int baseDmg = weapon.getDmg();
        if(weapon.isCrit() && weapon.whoIsIt){
            System.out.println("CRITICAL STRIKE!");
            return baseDmg * 3;
        }
        else{
            return baseDmg;
        }
    }
    public int blockDmg(){
        int baseDmg = weapon.getBlock();
        if(weapon.isCrit() && weapon.whoIsIt){
            System.out.println("CRITICAL BLOCK!");
            return baseDmg * 5;
        }
        else{
            return baseDmg;
        }
    }
    public int grabDmg(){
            return 10*getLevel();
    }

    public Weapon getWeapon(){
        return weapon;
    }
    public void listStats() {
        System.out.println("---"+name+"---");
        System.out.println("Health: "+health);
        System.out.println("Weapon: "+weapon);
        System.out.println("Level: "+level);
        System.out.println("------------");
        
    }
    public int getLevel() {
        return level;
    }
    public void changeLevel(int newLevel){
        newLevel = level;
    }
    public String getName() {
        return name;
    }
    
}