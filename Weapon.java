
import java.util.Random;

public class Weapon {
    
    Random rand = new Random();
    int lvl;
    String name;
    int type;
    int dmgMin;
    int dmgMax;
    int block;
    int crit;
    boolean whoIsIt;

    public Weapon(int lvl, String name,int type, boolean whoIsIt){

        this.lvl = lvl;
        this.name = name;
        this.type = type;
        this.whoIsIt = whoIsIt;

        //figures out what type it is and depending on that changes
        //the damage, block, and crit chance
        switch(type){
            case 0: //Sword medium damage, medium block, medium crit chance
                dmgMin = 3*lvl;
                dmgMax = 5*lvl;
                crit = 20;
                block = 4*lvl;
                break;
            case 1: //Hammer high damage, High block, low crit chance
                dmgMin = 5*lvl;
                dmgMax = 9*lvl;
                crit = 10;
                block = 9*lvl;
                break;
            case 2: //Spear low to high damage, low block, high crit chance
                dmgMin = 2*lvl;
                dmgMax = 7*lvl;
                crit = 30;
                block = 3*lvl;
                break;
        }
        

        //0:blades 1:bludgeon 2:Spears
    }   
    
    public boolean isCrit(){
        if(rand.nextInt(100) <= crit){
            return true;
        }
        else{
            return false;
        }
    }
    public void listStats() {
        System.out.println("---"+name+"---");
        System.out.println("Attack: "+dmgMin+" - "+dmgMax);
        System.out.println("Block: "+block);
        System.out.println("Level: "+lvl);
        System.out.println("Crit: "+crit+"%");
        System.out.println("------------");
        
    }
    public int getDmg(){
        return rand.nextInt((dmgMax+1) - dmgMin) + dmgMin;
    }
    public boolean getWhoIsit(){
        return whoIsIt;
    }
    public String getName() {
        return name;
    }
    public int getBlock() {
        return block;
    }
    public int getLvl() {
        return lvl;
    }
    

    
   
}
