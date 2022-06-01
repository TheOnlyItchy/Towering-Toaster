public class Player extends Creature{
    private int level;
    private String name;
    private int attackPwr;
    private int health;
    private int money;
    private int maxHealth;
    public Player(int level,String name, int attackPwr,int health, int money){
        
        super(level, name, attackPwr, health);
        this.level = level;
        this.name = name;
        this.attackPwr = attackPwr;
        this.health = health;
        this.money = money;
        maxHealth = health;
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
    
  
    
}