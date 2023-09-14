package src.com.java;

public class Chest {
    protected Weapon item;
    protected String name;
    protected int rarity;
    

    public Chest(Weapon item, String name, int rarity) {
        this.item = item;
        this.name = name;
        this.rarity = rarity;
    }

    public void swapItems(Player plr) {
        Weapon temp = plr.getWeapon();
        plr.setWeapon(item);
        this.item = temp;

        System.out.println("You equipped the " + plr.getWeapon().getName() + "!");
    }

    public void viewItem() {
        System.out.println(String.format("%s Contains:\n%s", this.name, this.item.getName()));
    }

}
