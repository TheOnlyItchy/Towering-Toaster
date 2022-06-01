public class Creature{
    int level;
    String name;
    int attackPwr;
    int health;
    public Creature(int level,String name,int attackPwr, int health, Weapon weapon){
        this.level = level;
        this.name = name;
        this.attackPwr = attackPwr;
        this.health = health;
    }
    public int getHealth(){
        return this.health;
    }
    public void takeDamage(int damage){
        health =- damage;
        System.out.println("You take -"+damage+" DMG! (HP:"+health+")");
    }
}