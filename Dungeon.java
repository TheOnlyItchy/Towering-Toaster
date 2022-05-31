import java.util.Random;

public class Dungeon {

    private final Random rand = new Random();
    private int rooms;
    private int treasureRoomChance;

    public Dungeon(int rooms, int treasureRoomChance) {
        this.rooms = rooms;
        this.treasureRoomChance = treasureRoomChance;
    }

    public int getChance() {
        return this.treasureRoomChance;
    }

    public boolean isItTreasureRoom() {

        if (rand.nextInt(100) <= this.treasureRoomChance) {
            return true;
        } else {
            return false;
        }
    }
}