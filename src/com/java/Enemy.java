package src.com.java;

public class Enemy {

    protected String name;
    protected Weapon weapon;
    protected String race;
    protected int maxHealth;
    protected int currentHealth;
    protected int speed;
    protected double xpValue;
    protected String type;
    protected boolean hit;
    protected boolean hitPlayer;


    public Enemy(String name, Weapon weapon, String race, int maxHealth, int speed, double xpValue, String type) {
        this.name = name;
        this.weapon = weapon;
        this.race = race;
        this.maxHealth = maxHealth;
        this.currentHealth = this.maxHealth;
        this.speed = speed - this.weapon.getSpeedPenalty();
        this.xpValue = xpValue;
        this.type = type;
        this.hit = false;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }
    public String getName() {
        return this.name;
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
        Typer.typeStringln(String.format("%s attacked you with its %s!\n", this.name, this.weapon.getName()));

        plr.takeDamage(this.weapon.getDmg());
        this.hitPlayer = true;
    }

    public void inspect() {
        if(this.hit) {
            Typer.typeStringsNoSpace(new String[] {
                String.format("Name: %s", this.name),
                String.format("Health: %s / %s", this.currentHealth, this.maxHealth),
                "Speed: ???",
                String.format("Race: %s", this.race)
            }, 100);
        } else {
            Typer.typeStringsNoSpace(new String[] {
                String.format("Name: %s", this.name),
                "Health: ??? / ???",
                "Speed: ???",
                String.format("Race: %s", this.race)
            }, 100);
        }
        if(this.hitPlayer) {
            Typer.typeStringsNoSpace(new String[] {
                String.format("\nWeapon: %s", this.weapon.getName()),
                String.format("Damage: %s", this.weapon.getDmg()),
                "Speed Reduction: ???"
            }, 100);
        } else {
            Typer.typeStringsNoSpace(new String[] {
                String.format("\nWeapon: %s", this.weapon.getName()),
                "Damage: ???",
                "Speed Reduction: ???"
            }, 100);
        }
    }

    /**
     * Cosmetic setter for Enemy.currentHealth
     * @param wpn
     * @param plr
     */
    public void takeDamage(Weapon wpn, Player plr, boolean crit) {

        if(crit) {
            this.currentHealth -= wpn.getDmg()*2;
            Typer.typeStringln(String.format("%s took %s damage!", this.name, wpn.getDmg()*2));
        } else {
            this.currentHealth -= wpn.getDmg();
            Typer.typeStringln(String.format("%s took %s damage!", this.name, wpn.getDmg()));
        }
        

        if(this.currentHealth > 0) {
            Typer.typeStringln(String.format("%s's remaining HP: %s / %s\n", this.name, this.currentHealth, this.maxHealth));
        } else {
            Typer.typeStringln(String.format("%s has been defeated!", this.name));
            plr.gainXp(this.xpValue);
        }
        
    }
    
}
