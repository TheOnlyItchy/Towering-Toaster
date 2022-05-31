import java.util.Random;

public class Dungeon {

    private final Random rand = new Random();
    private int rooms;
    private int specialRoomChance;

    public Dungeon(int rooms, int treasureRoomChance) {
        this.rooms = rooms;
        this.specialRoomChance = treasureRoomChance;
    }

    public int getRooms(){
        return this.rooms;
    }

    public int getChance() {
        return this.specialRoomChance;
    }

    public boolean isItTreasureRoom() {

        if (rand.nextInt(100) <= this.specialRoomChance) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isItObstacle(){
        if (rand.nextInt(100) <= this.specialRoomChance+20) {
            return true;
        } else {
            return false;
        }
    }

}