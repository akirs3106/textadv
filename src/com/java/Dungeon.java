package src.com.java;

import java.util.Scanner;
public class Dungeon {
    protected Room[] rooms;
    protected String name;
    protected Room activeRoom;
    protected int activeRoomIndex;
    protected static int enemyEncounterMultiplier;

    public Dungeon(Room[] rooms, String name) {
        this.rooms = rooms;
        this.name = name;
        this.activeRoom = rooms[0];
        this.activeRoomIndex = 0;
        Dungeon.enemyEncounterMultiplier = 0;
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

    public static int getEnemyEncounterMultiplier() {
        return Dungeon.enemyEncounterMultiplier;
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
                    Typer.typeString("\nAre you sure you wish to enter the next room? You may not be able to come back.\n\nY/N\n> ");
                    String input = scanner.next().toLowerCase();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    if(input.equals("y")) {
                        Typer.typeStringln("\nYou enter the next room.");
                        Typer.typeStringln(nextRoom.getDescription());
                        currentRoom.setActiveRoomStatus(false);
                        nextRoom.setActiveRoomStatus(true);
                        this.activeRoom = nextRoom;
                        this.activeRoomIndex = nextRoomIndex;
                        Main.startBossEncounter(plr);
                        choosing = false;
                    } else if (input.equals("n")) {
                        choosing = false;
                        return false;
                    } else {
                        Typer.typeStringln("Please input \"Y\" or \"N\".");
                    }
                }
            } else {
                Typer.typeStringln("\nYou enter the next room.");
                Dungeon.enemyEncounterMultiplier += 1;
                Typer.typeStringln(nextRoom.description);
                currentRoom.setActiveRoomStatus(false);
                nextRoom.setActiveRoomStatus(true);
                this.activeRoom = nextRoom;
                this.activeRoomIndex = nextRoomIndex;
                this.activeRoom.playerDecideEncounter(plr);
                return true;
            }
        } else {
            Typer.typeStringln("\nYou can't seem to find the entrance to next next room. Maybe try searching this one first.");
            return false;
        }
        return false;
    }

    public boolean enterPreviousRoom(Player plr) {
        Room currentRoom = this.activeRoom;
        int previousRoomIndex = this.activeRoomIndex - 1;
        if(previousRoomIndex >= 0 && !currentRoom.getType().equals("start")) {
            Room previousRoom = rooms[previousRoomIndex];
            Typer.typeStringln("\nYou retreat back a room.");
            Typer.typeStringln(previousRoom.getDescription());
            currentRoom.setActiveRoomStatus(false);
            previousRoom.setActiveRoomStatus(true);
            this.activeRoom = previousRoom;
            this.activeRoomIndex = previousRoomIndex;
            this.activeRoom.playerDecideEncounter(plr);
            return true;
        } else {
            Typer.typeStringln("\nYou cannot exit the dungeon this way, the entrance is blocked!");
            return false;
        }
    }
    
}
