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
            this.activeSpeed = calculateActiveSpeed();
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

    
    /**
     * Cosmetic setter for Player.currenthp
     * @param wpn
     */
    public void takeDamage(Weapon wpn) {
        this.currenthp -= wpn.getDmg();

        System.out.println("You took " + wpn.getDmg() + "!");
        System.out.println(String.format("Remaining health: %s / %s", this.currenthp, this.maxhp));
    }
    

    /**
     * Cosmetic setter for Player.xp additionally invokes Player.levelUp() if this.xp is divisible by 100 & level is less than 10
     * @param gainedXp
     */
    public void gainXp(int gainedXp) {
        this.xp += gainedXp;
        System.out.println("You received " + gainedXp + "XP!\n");
        if(this.xp % 100 == 0 && this.level < 10) {
            levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        this.maxhp += 10;
        this.currenthp = maxhp;
        this.baseSpeed += 5;
        this.activeSpeed = calculateActiveSpeed();

        System.out.println("You levelled up to level " + this.level + "!");
        System.out.println("\nYour max HP has been increased to " + maxhp + "!");
        System.out.println("You have been fully healed!");
        System.out.println("You base speed has been increased to " + baseSpeed + "!");



    }

    private int calculateActiveSpeed() {

        return this.baseSpeed - this.equippedWeapon.getSpeedPenalty();

    }


    /**
     * Cosmetically invokes Enemy.takeDamage()
     * @param enemy
     * @param plr
     */
    public void attackEnemy(Enemy enemy, Player plr) {
        System.out.println(String.format("\nYou attacked %s with your %s!\n", enemy.getName(), this.equippedWeapon.getName()));
        enemy.takeDamage(this.equippedWeapon, plr);
    }
    
}
