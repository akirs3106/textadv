package src.com.java;

public class Enemy {

    protected String name;
    protected Weapon weapon;
    protected String race;
    protected int maxHealth;
    protected int currentHealth;
    protected int speed;
    protected double xpValue;


    public Enemy(String name, Weapon weapon, String race, int maxHealth, int speed, double xpValue) {
        this.name = name;
        this.weapon = weapon;
        this.race = race;
        this.maxHealth = maxHealth;
        this.currentHealth = this.maxHealth;
        this.speed = speed - this.weapon.getSpeedPenalty();
        this.xpValue = xpValue;
    }

    public int getHp() {
        return this.currentHealth;
    }

    public int getSpeed() {
        return this.speed;
    }

    /**
     * Cosmetically invokes Player.takeDamage()
     * @param plr
     */
    public void attackPlayer(Player plr) {
        System.out.println(String.format("%s attacked you with its %s!\n", this.name, this.weapon.getName()));

        plr.takeDamage(this.weapon);
    }

    /**
     * Cosmetic setter for Enemy.currentHealth
     * @param wpn
     * @param plr
     */
    public void takeDamage(Weapon wpn, Player plr) {
        this.currentHealth -= wpn.getDmg();
        System.out.println(String.format("%s took %s damage!", this.name, wpn.getDmg()));

        if(this.currentHealth > 0) {
            System.out.println(String.format("%s's remaining HP: %s / %s\n", this.name, this.currentHealth, this.maxHealth));
        } else {
            System.out.println(String.format("%s has been defeated!", this.name));
            plr.gainXp(this.xpValue);
        }
        
    }

    public String getName() {
        return this.name;
    }
    
}
