package src.com.java;

public class Player {
    private int maxhp;
    private int currenthp;
    private int xp;
    private int level;
    private int baseSpeed;
    protected int activeSpeed;
    private Weapon equippedWeapon;
    private String playerClass;

    /**
     * @param playerClass the player's class (Capitalize properly, used cosmetically)
     */
    public Player(String playerClass) {
        this.xp = 0;
        this.level = 0;
        this.playerClass = playerClass;

        if(playerClass.toLowerCase().equals("warrior")){
            this.equippedWeapon = new Sword("Rusty Sword", 10, "rusty sword", 0);
            this.baseSpeed = 100;
            this.maxhp = 100;
        } else if (playerClass.toLowerCase().equals("barbarian")) {
            this.equippedWeapon = new Axe("Rusty Axe", 20, "rusty axe", 0);
            this.baseSpeed = 75;
            this.maxhp = 125;
        } else if (playerClass.toLowerCase().equals("rogue")) {
            this.equippedWeapon = new Dagger("Rusty Dagger", 8, "rusty dagger", 0);
            this.baseSpeed = 125;
            this.maxhp = 75;
        }
            this.activeSpeed = this.baseSpeed;
            this.currenthp = this.maxhp;
    }


    /**
     * Prints visual representation of the player's weapon's stats.
     */
    public void inspectWeapon(){
        String str = String.format("\nName: %s \nDamage: %s \nSpeed Reduction: %s\n", this.equippedWeapon.name, this.equippedWeapon.dmg, this.equippedWeapon.speedPenalty);

        System.out.println(str);

    }

    /**
     * Prints visual representation of the player's stats.
     */
    public void viewStats(){
        String str = String.format("\nClass: %s \nHealth: %s / %s \nXP: %s \nLevel: %s \nSpeed: %s \n", this.playerClass, this.currenthp, this.maxhp, this.xp, this.level, this.activeSpeed);

        System.out.println(str);
    }

    public void takeDamage(Weapon wpn) {
        this.currenthp -= wpn.getDmg();
    }
    
}
