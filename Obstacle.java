import java.util.Random;
import java.util.Scanner;

public class Obstacle {
    Player hero;

    public Obstacle(Player hero){
        

        this.hero = hero;
        Random rand = new Random();
        Scanner scn = new Scanner(System.in);

        //Random 10 events that can happen (Currently 5)
        int event = rand.nextInt(5);
        //0 = good outcome; 1 = bad outcome
        int correct = rand.nextInt(2);
        switch(event){
            case 0: //Creepy Chest
                System.out.println("Wandering into the next room, you shift your gaze to the vine covered room. In the center on a pedestal sits a small erie chest\nDo you wish to open it? (Y/N)");
                if(scn.next().toUpperCase().equals("Y")){
                    if(correct == 0){
                        System.out.println("With sweaty hands you open the chest, as you lift the lid it reveals a small pouch of gold!");
                        hero.changeMoney(rand.nextInt(20 - 11) + 11, true); // 10 - 20 gold rewarded
                    }
                    else{
                        System.out.println("With sweaty hands you open the chest, as you lift the lid it reveals a protruding tongue reaching its way to your wallet! before you struggle free of it, it grabs some coins and returns to the chest sealing it shut.");
                        hero.changeMoney(rand.nextInt(10 - 6) + 6, false); // 5 - 10 gold stolen
                    }
                    
                }
                else{
                    System.out.println("You decide this chest is too creepy! you walk to the door and leave to the next room");
                    
                }
                break;
            case 1: // Risky Traps
                System.out.println("Wandering into the next room, you notice a door to your left, presumed to exit this room. but ahead of you lies a corridor with a chest tucked behind some ferns. It might be a trap...\nDo you wish to risk getting the chest? (Y/N)");
                if(scn.next().toUpperCase().equals("Y")){
                    if(correct == 0){
                        System.out.println("With baited breath you swagger down the hallway, you reach the chest unscaled. The chest's rusted hinges lift to reveal a bag of jewles!");
                        hero.changeMoney(rand.nextInt(30 - 16) + 16, true); // 30 - 15 gold rewarded
                    }
                    else{
                        System.out.println("With baited breath you swagger down the hallway, you lumber onto a pressure plate, the walls open to fire arrows! although painful, its not leathal. The chest's rusted hinges lift to reveal a bag of jewles!");
                        hero.changeMoney(rand.nextInt(35 - 21) + 21, true); // 5 - 10 gold rewarded
                        hero.takeDamage(rand.nextInt(7 - 2) + 2); // 7 - 1 damage taken
                    }
                }
                else{
                    System.out.println("You decide its not worth it! you walk to the door and leave to the next room");
                    
                }
                break;
            case 2: // Strange Bottles
                System.out.println("Wandering into the next room, you notice how nicely furnished it is, of course it could do with some dusting, but still very homey. Laying over the piano in the corner of the room is a skelton grasping a liquor bottle. Its contents look brown and mushy, but dispite its appernce it doesnt smell half bad!\nDo you want to take a swig? (Y/N)");
                if(scn.next().toUpperCase().equals("Y")){
                    if(correct == 0){
                        System.out.println("With your strong hands you break the bones fused to the bottle, you drink the entire contents. Even with its odd apperence and consistency, it tastes amazing!");
                        hero.heal(rand.nextInt(15 - 2) + 2); // 15 - 1 health rewarded
                    }
                    else{
                        System.out.println("With your strong hands you break the bones fused to the bottle, you drink the entire contents. It tastes just as it looks, disgusting!");
                        hero.takeDamage(1);// 1 damage taken
                    }
                }
                else{
                    System.out.println("You decide its not a good idea to drink random drinks from strangers! you walk to the door and leave to the next room");
                    
                }
                break;
            case 3: //Bouncy Bed
                System.out.println("Wandering into the next room, you find it completly empty all except for a bed with a mattress, you walk over and sit on it. it's very bouncy!\nDo you want to jump on the bed? (Y/N)");
                if(scn.next().toUpperCase().equals("Y")){
                    if(correct == 0){
                        System.out.println("With your beefy legs you start jumping on the bed, you continue to jump on it for a concering ammount of time. without knowing how long you've been bouncing, you decide you've had enough. As you dismount the bed you notice the mattress is completely destroyed, but you find underneath it a few coins!");
                        hero.changeMoney(rand.nextInt(5 - 2) + 2, true); // 5 - 1 gold rewarded
                    }
                    else{
                        System.out.println("With your beefy legs you start jumping on the bed. After a few minutes the beds legs give way and you come tumbling to the floor. Not entirly sure what you were expecting.");
                        hero.takeDamage(rand.nextInt(5 - 2) + 2); // 5 - 1 damage taken
                    }
                }
                else{
                    System.out.println("You remember a old proverb about monkeys jumping on a bed remember what happen to them, you decide its not the best idea to bounce on it. you walk to the door and leave to the next room");
                    
                }
                break;
            case 4: //
                System.out.println("Wandering into the next room, you find a old kitchen including a dining room table. Dispite the lack of electricity, there is a toaster with the cord pluged into nothing. Beside it, there is a bread bag with the clip missing.\nDo you want to toast some toast? (Y/N)");
                if(scn.next().toUpperCase().equals("Y")){
                    System.out.println("With a curious look, you open the bread bag and place a single piece of bread into it. Even though you know there is no electricity, you still become surprised as the toaster proceads to not work. You look around yourself hopeing no one saw what a idiot you looked like. you quitly leave the room.");
                }
                else{
                    System.out.println("You know theres no electricty, therfore it wont work! feeling extra smart, you walk to the door and leave to the next room");
                    hero.heal(1);
                }
                break;
                case 5: //
                System.out.println("/nDo you want to take a swig? (Y/N)");
                if(scn.next().toUpperCase().equals("Y")){
                    if(correct == 0){
                        System.out.println("");
                        hero.changeMoney(rand.nextInt(30 - 16) + 16, true); // 30 - 15 gold rewarded
                    }
                    else{
                        System.out.println("");
                        hero.changeMoney(rand.nextInt(35 - 21) + 21, true); // 5 - 10 gold rewarded
                        hero.takeDamage(rand.nextInt(7 - 2) + 2); // 7 - 1 damage taken
                    }
                }
                else{
                    System.out.println("You decide its not worth it! you walk to the door and leave to the next room");
                    
                }
                break;
                case 6: //
                System.out.println("/nDo you want to take a swig? (Y/N)");
                if(scn.next().toUpperCase().equals("Y")){
                    if(correct == 0){
                        System.out.println("");
                        hero.changeMoney(rand.nextInt(30 - 16) + 16, true); // 30 - 15 gold rewarded
                    }
                    else{
                        System.out.println("");
                        hero.changeMoney(rand.nextInt(35 - 21) + 21, true); // 5 - 10 gold rewarded
                        hero.takeDamage(rand.nextInt(7 - 2) + 2); // 7 - 1 damage taken
                    }
                }
                else{
                    System.out.println("You decide its not worth it! you walk to the door and leave to the next room");
                    
                }
                break;
                case 7: //
                System.out.println("/nDo you want to take a swig? (Y/N)");
                if(scn.next().toUpperCase().equals("Y")){
                    if(correct == 0){
                        System.out.println("");
                        hero.changeMoney(rand.nextInt(30 - 16) + 16, true); // 30 - 15 gold rewarded
                    }
                    else{
                        System.out.println("");
                        hero.changeMoney(rand.nextInt(35 - 21) + 21, true); // 5 - 10 gold rewarded
                        hero.takeDamage(rand.nextInt(7 - 2) + 2); // 7 - 1 damage taken
                    }
                }
                else{
                    System.out.println("You decide its not worth it! you walk to the door and leave to the next room");
                    
                }
                break;
                case 8: //
                System.out.println("/nDo you want to take a swig? (Y/N)");
                if(scn.next().toUpperCase().equals("Y")){
                    if(correct == 0){
                        System.out.println("");
                        hero.changeMoney(rand.nextInt(30 - 16) + 16, true); // 30 - 15 gold rewarded
                    }
                    else{
                        System.out.println("");
                        hero.changeMoney(rand.nextInt(35 - 21) + 21, true); // 5 - 10 gold rewarded
                        hero.takeDamage(rand.nextInt(7 - 2) + 2); // 7 - 1 damage taken
                    }
                }
                else{
                    System.out.println("You decide its not worth it! you walk to the door and leave to the next room");
                    
                }
                break;
                case 9: //
                System.out.println("/nDo you want to take a swig? (Y/N)");
                if(scn.next().toUpperCase().equals("Y")){
                    if(correct == 0){
                        System.out.println("");
                        hero.changeMoney(rand.nextInt(30 - 16) + 16, true); // 30 - 15 gold rewarded
                    }
                    else{
                        System.out.println("");
                        hero.changeMoney(rand.nextInt(35 - 21) + 21, true); // 5 - 10 gold rewarded
                        hero.takeDamage(rand.nextInt(7 - 2) + 2); // 7 - 1 damage taken
                    }
                }
                else{
                    System.out.println("You decide its not worth it! you walk to the door and leave to the next room");
                    
                }
                break;
        }
    }
    
}
