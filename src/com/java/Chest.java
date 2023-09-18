package src.com.java;

import java.util.Scanner;
public class Chest {
    protected Weapon item;
    protected String name;
    protected int rarity;
    

    public Chest(Weapon item, String name, int rarity) {
        this.item = item;
        this.name = name;
        this.rarity = rarity;
    }

    public String getName() {
        return this.name;
    }

    private void swapItems(Player plr) {
        Weapon temp = plr.getWeapon();
        plr.setWeapon(item);
        this.item = temp;

        System.out.println("You equipped the " + plr.getWeapon().getName() + "!\n");
    }

    private void viewItem() {
        System.out.println(String.format("%s Contains:\n%s", this.name, this.item.getName()));
    }

    public void interact(Player plr) {
        System.out.println("\nYou open the " + this.name + ".");
        System.out.println("You begin to rummage through the " + this.name + "...\n");
        viewItem();
        Scanner scanner = new Scanner(System.in);
        boolean choosing = true;
        while (choosing) {
            System.out.print("\nWhat would you like to do?\n1. Swap Weapons\n2. View " + this.item.getName() + " Stats\n3. View Equipped Weapon Stats\n4. Close " + this.name + "\n> ");
            String input = scanner.next();
            if(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4")) {
                switch(Integer.parseInt(input)) {
                    case 1:
                        swapItems(plr);
                        viewItem();
                    break;
                    case 2: 
                        item.viewWeapon();
                    break;
                    case 3:
                        plr.inspectWeapon();
                    break;
                    case 4:
                        choosing = false; 
                    break;
                    default:
                        System.out.println("Please enter the number next to the option you wish to pick.");
                }

            }
        }
        
        
        
    }

}
