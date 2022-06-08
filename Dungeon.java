import java.util.Random;

public class Dungeon {

    private final Random rand = new Random();
    private int rooms;
    private int specialRoomChance;
    private int minLevel;
    private int maxLevel;
    private int floor;

    public Dungeon(int rooms, int treasureRoomChance, int minLevel, int maxLevel, int floor ){
        this.rooms = rooms;
        this.specialRoomChance = treasureRoomChance;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.floor = floor;
    }

    public int getRooms(){
        return this.rooms;
    }

    public int getChance() {
        return this.specialRoomChance;
    }

    public boolean isItTreasureRoom() {

        if (rand.nextInt(100) <= specialRoomChance) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isItObstacle(){
        if (rand.nextInt(100) <= specialRoomChance+20) {
            return true;
        } else {
            return false;
        }
    }
    public int getMaxLevel() {
        return maxLevel;
    }
    public int getMinLevel() {
        return minLevel;
    }
    public int getRandomLevel(){
        return rand.nextInt(maxLevel+1 - minLevel) + minLevel;
    }
    public int getFloor() {
        return floor;
    }

}