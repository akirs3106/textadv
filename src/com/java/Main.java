package src.com.java;

import java.util.*;
class Main {
    public static void main(String args[]) {

        String plrClasses[] = {"Warrior" , "Barbarian", "Rogue"};

        System.out.print("Classes:\n\n1. Warrior\n2. Barbarian\n3. Rogue\n\nChoose your class: ");
        
        Scanner scanner = new Scanner(System.in);

        String plrClass = plrClasses[scanner.nextInt()-1];
        scanner.close();

        Player plr = new Player(plrClass);
        // Enemy enemy = new Enemy("Skeleton Footman", new Sword("Steel Longsword", 10, "longsword", 10), "skeleton", 100, 100, 100);

        // plr.inspectWeapon();
        // plr.viewStats();

        // enemy.attackPlayer(plr);

        // plr.attackEnemy(enemy, plr);
        // plr.attackEnemy(enemy, plr);
        // plr.attackEnemy(enemy, plr);
        // plr.attackEnemy(enemy, plr);
        // plr.attackEnemy(enemy, plr);

        // plr.viewStats();

    }

    public void startEncounter(Enemy enemy) {

        System.out.println(String.format("%s approaches you!", enemy.getName()));

    }

    public Enemy createRandomSkeleton() {
        
    }
}