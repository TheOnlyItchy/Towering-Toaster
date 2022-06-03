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

    public Weapon(int lvl, String name,int type){

        this.lvl = lvl;
        this.name = name;
        this.type = type;

        //figures out what type it is and depending on that changes
        //the damage, block, and crit chance
        switch(type){
            case 0: //Sword medium damage, medium block, medium crit chance
                dmgMin = 3*lvl;
                dmgMax = 5*lvl;
                crit = 3*lvl;
                block = 3*lvl;
                break;
            case 1: //Hammer high damage, High block, low crit chance
                dmgMin = 4*lvl;
                dmgMax = 7*lvl;
                crit = 10;
                block = 5*lvl;
                break;
            case 2: //Spear low to high damage, low block, high crit chance
                dmgMin = 3*lvl;
                dmgMax = 7*lvl;
                crit = 30;
                block = 2*lvl;
                break;
        }
        

        //0:blades 1:bludgeon 2:Spears
    }  
    public int getDmg(){
        int baseDmg = rand.nextInt(dmgMax - dmgMin) + dmgMin;
        if(isCrit()){
            System.out.println("CRITICAL STRIKE X3 DAMAGE!!!");
            return baseDmg * 3;
        }
        else{
        return baseDmg;
        }
    }
    public boolean isCrit(){
        if(rand.nextInt(100) <= crit){
            return true;
        }
        else{
            return false;
        }
    }

    
   
}
