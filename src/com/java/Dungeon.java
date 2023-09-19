package src.com.java;

public class Dungeon {
    Room[] rooms;
    String name;
    Room activeRoom;

    public Dungeon(Room[] rooms, String name) {
        this.rooms = rooms;
        this.name = name;
        for (Room room : rooms) {
            if(room.activeRoomStatus()) {
                this.activeRoom = room;
            }
        }
    }
    
}
