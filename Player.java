public class Player extends Creature{
    private int level;
    private String name;
    private int attackPwr;
    private int health;
    public Player(int level,String name, int attackPwr,int health){
        
        super(level, name, attackPwr, health);
        this.level = level;
        this.name = name;
        this.attackPwr = attackPwr;
        this.health = health;
    }
    public int getHealth(){
        return this.health;
    }
}