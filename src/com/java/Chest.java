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

        Typer.typeStringln("You equipped the " + plr.getWeapon().getName() + "!\n");
    }

    private void viewItem() {
        Typer.typeStringln(String.format("%s Contains:\n%s", this.name, this.item.getName()));
    }

    public void interact(Player plr) {
        Typer.typeStrings(new String[] {
            "\nYou open the " + this.name + ".",
            "You begin to rummage through the " + this.name + "...\n"
        }, 1000);
        viewItem();
        Scanner scanner = new Scanner(System.in);
        boolean choosing = true;
        while (choosing) {
            Typer.typeStringln("\nWhat would you like to do?");
            Typer.typeString("1. Swap Weapons\n2. View " + this.item.getName() + " Stats\n3. View Equipped Weapon Stats\n4. Close " + this.name + "\n> ", 10);
            String input = scanner.next();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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
                        Typer.typeStringln("Please enter the number next to the option you wish to pick.");
                }

            }
        }
        
        
        
    }

}
