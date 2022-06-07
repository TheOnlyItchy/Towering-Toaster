import java.util.Random;

public class Creature{
    int level;
    String name;
    int attackPwr;
    int health;
    Weapon weapon;
    Random rand = new Random();
    public Creature(int level,String name,int attackPwr, int health, Weapon weapon){
        this.level = level;
        this.name = name;
        this.attackPwr = attackPwr;
        this.health = health;
        this.weapon = weapon;
    }

    public int getHealth(){
        return this.health;
    }

    public void takeDamage(int damage){
        health =- damage;
        System.out.println("You take -"+damage+" DMG! (HP:"+health+")");
    }

    public void heal(int healing){
        health =+ healing;
        System.out.println("You heal +"+healing+" HP! (HP:"+health+")");
    }

    public int attackDmg(){
        int baseDmg = weapon.getDmg();
        if(weapon.isCrit() && weapon.whoIsIt){
            System.out.println("You Swing your "+weapon.getName()+" and get a CRITICAL STRIKE dealing "+baseDmg*3);
            return baseDmg * 3;
        }
        else{
            System.out.println("You Swing your "+weapon.getName()+" and deal "+baseDmg);
        return baseDmg;
        }
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