package src.com.java;

import java.util.*;
class Main {
    public static void main(String args[]) {

        System.out.print("Classes:\n\nWarrior\nBarbarian\nRogue\n\nChoose your class: ");
        
        Scanner scanner = new Scanner(System.in);
        String plrClass = scanner.next();

        Player plr = new Player(plrClass);

        plr.inspectWeapon();
        plr.viewStats();

        plr.takeDamage(new Sword("yes", 11, "longsword", 13));

        plr.viewStats();

        // Player p1 = new Player("Rogue");
        // Player p2 = new Player("Barbarian");
        // Player p3 = new Player("Warrior");

        // p1.inspectWeapon();
        // p2.inspectWeapon();
        // p3.inspectWeapon();

        // p1.viewStats();
        // p2.viewStats();
        // p3.viewStats();

    }
}