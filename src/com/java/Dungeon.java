package src.com.java;

public class Dungeon {
    Room[][] rooms;
    String name;

    public Dungeon(Room[][] rooms, String name) {
        this.rooms = rooms;
        this.name = name;
<<<<<<< Updated upstream
=======
        this.activeRoom = rooms[0];
        this.activeRoomIndex = 0;
    }

    public Room getActiveRoom() {
        return this.activeRoom;
    }

    public void findActiveRoom() {
        for(int i = 0; i < this.rooms.length; i++) {
            if(this.rooms[i].activeRoomStatus()) {
                this.activeRoom = this.rooms[i];
                this.activeRoomIndex = i;
                return;
            }
        }
    }

    public int dungeonLength() {
        return this.rooms.length;
    }
    /**
     * 
     * @param plr
     * @return true if the player entered the next room, false if they did not.
     */
    public boolean enterNextRoom(Player plr) {
        Room currentRoom = this.activeRoom;
        int nextRoomIndex = this.activeRoomIndex + 1;
        if(currentRoom.getSearched() && nextRoomIndex < rooms.length) {
            Room nextRoom = rooms[nextRoomIndex];
            if(nextRoom.getType() == "boss") {
                boolean choosing = true;
                Scanner scanner = new Scanner(System.in);
                while(choosing) {
                    System.out.println("\nAre you sure you wish to enter the next room? You may not be able to come back.\n\nY/N\n> ");
                    String input = scanner.next().toLowerCase();
                    if(input == "y") {
                        System.out.println("\nYou enter the next room.");
                        System.out.println(nextRoom.getDescription());
                        currentRoom.setActiveRoomStatus(false);
                        nextRoom.setActiveRoomStatus(true);
                        this.activeRoom = nextRoom;
                        this.activeRoomIndex = nextRoomIndex;
                        Main.startBossEncounter(plr);
                        choosing = false;
                    } else if (input == "n") {
                        choosing = false;
                        return false;
                    } else {
                        System.out.println("Please input \"Y\" or \"N\".");
                    }
                }
            } else {
                System.out.println("\nYou enter the next room.");
                System.out.println(nextRoom.description);
                currentRoom.setActiveRoomStatus(false);
                nextRoom.setActiveRoomStatus(true);
                this.activeRoom = nextRoom;
                this.activeRoomIndex = nextRoomIndex;
                this.activeRoom.playerDecideEncounter(plr);
                return true;
            }
        } else {
            System.out.println("\nYou can't seem to find the entrance to next next room. Maybe try searching this one first.");
            return false;
        }
        return false;
    }

    public boolean enterPreviousRoom(Player plr) {
        Room currentRoom = this.activeRoom;
        int previousRoomIndex = this.activeRoomIndex--;
        if(previousRoomIndex >= 0 && !currentRoom.getType().equals("start")) {
            Room previousRoom = rooms[previousRoomIndex];
            System.out.println("\nYou retreat back a room.");
            System.out.println(previousRoom.getDescription());
            currentRoom.setActiveRoomStatus(false);
            previousRoom.setActiveRoomStatus(true);
            this.activeRoom = previousRoom;
            this.activeRoomIndex = previousRoomIndex;
            this.activeRoom.playerDecideEncounter(plr);
            return true;
        } else {
            System.out.println("\nYou cannot exit the dungeon this way, the entrance is blocked!");
            return false;
        }
>>>>>>> Stashed changes
    }
    
}
