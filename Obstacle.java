import java.util.Random;
import java.util.Scanner;

public class Obstacle {
    
    public static final Scanner scn = new Scanner(System.in);

        Player hero;

    public Obstacle(Player hero){

        this.hero = hero;

        //Random 10 events that can happen
        int event = new Random().nextInt(10);
        //0 = good outcome; 1 = bad outcome
        int correct = new Random().nextInt(1);
        switch(event){
            case 0: //Creepy Chest
                System.out.println("Wandering into the next room, you shift your gaze to the vine covered room. In the center on a pedestal sits a small erie chest/nDo you wish to open it? (Y/N)");
                if(scn.next().toUpperCase() == "Y"){
                    if(correct == 0){
                        System.out.println("With sweaty hands you open the chest, as you lift the lid it reveals a small pouch of gold!");
                        hero.changeMoney(new Random().nextInt(20 - 11) + 11, true); // 10 - 20 gold rewarded
                    }
                    else{
                        System.out.println("With sweaty hands you open the chest, as you lift the lid it reveals a protruding tongue reaching its way to your wallet! before you struggle free of it, it grabs some coins and returns to the chest sealing it shut.");
                        hero.changeMoney(new Random().nextInt(10 - 6) + 6, false); // 5 - 10 gold stolen
                    }
                    
                }
                else{
                    System.out.println("You decide this chest is too creepy! you walk to the door and leave to the next room");
                }
                break;
            case 1: // Risky Traps
                System.out.println("Wandering into the next room, you notice a door to your left, presumed to exit this room. but ahead of you lies a corridor with a chest tucked behind some ferns. It might be a trap.../nDo you wish to risk getting the chest? (Y/N)");
                if(scn.next().toUpperCase() == "Y"){
                    if(correct == 0){
                        System.out.println("With baited breath you swagger down the hallway, you reach the chest unscaled. The chest's rusted hinges lift to reveal a bag of jewles!");
                        hero.changeMoney(new Random().nextInt(30 - 16) + 16, true); // 30 - 15 gold rewarded
                    }
                    else{
                        System.out.println("With baited breath you swagger down the hallway, you lumber onto a pressure plate, the walls open to fire arrows! although painful, its not leathal. The chest's rusted hinges lift to reveal a bag of jewles!");
                        hero.changeMoney(new Random().nextInt(35 - 21) + 21, true); // 5 - 10 gold rewarded
                        hero.takeDamage(new Random().nextInt(7 - 2) + 2); // 7 - 1 damage taken
                    }
                    break;
                }
                else{

                    
                    break;
                }
        }
    }
    
}
