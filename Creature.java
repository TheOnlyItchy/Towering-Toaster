public class Creature{
    int level;
    String name;
    int attackPwr;
    int health;
    public Creature(int level,String name,int attackPwr, 
int health){
        this.level = level;
        this.name = name;
        this.attackPwr = attackPwr;
        this.health = health;
    }
    public int getHealth(){
        return this.health;
    }
}