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
        Scanner scanner = new Scanner(System.in);
        boolean choosing = true;
        viewItem();
        while (choosing) {
            String chestChoices[] = {"Swap", "Compare", "Close"};
            Typer.typeStringln("\nWhat would you like to do?");
            Typer.typeString("1. Swap Weapons \n2. Compare Weapons \n3. Close Chest\n> ", 10);
            try {
                String input = scanner.next();
                int choice = Integer.parseInt(input)-1;

                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                if(choice >= 0 && choice < chestChoices.length) {
                    switch(chestChoices[choice]) {
                    case "Swap":
                        swapItems(plr);
                        viewItem();
                    break;
                    case "Compare":
                        item.compareWeapon(plr.getWeapon());
                    break;
                    case "Close":
                        choosing = false;
                    break;
                    default:
                        System.out.println("You shouldn't see this.");
                        scanner.next();
                        System.exit(0);
                }
                } else {
                    Typer.typeStringln("Please enter the number next to the option you wish to pick.");
                }
            } catch (Exception e) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                Typer.typeStringln("Please enter the number next to the option you wish to pick.");
            }
        }
        
        
        
    }

}
