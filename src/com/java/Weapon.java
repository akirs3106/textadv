package src.com.java;

public class Weapon {
    
    protected String name;
    protected int dmg;
    protected String type;
    protected int speedPenalty;
    protected int abilityCooldown;
    protected int currentAbilityCooldown;
    protected final int initialDamage;

    public Weapon(String name, int dmg, String type, int speedPenalty, int abilityCooldown) {
        this.name = name;
        this.dmg = dmg;
        this.type = type;
        this.speedPenalty = Math.abs(speedPenalty);
        this.currentAbilityCooldown = 0;
        this.abilityCooldown = abilityCooldown;
        this.initialDamage = dmg;
    }

    public String getName(){
        return this.name;
    }

    public int getDmg(){
        return this.dmg;
    }

    public String getType() {
        return this.type;
    }

    public int getSpeedPenalty() {
        return speedPenalty;
    }

    public void setDamage(int x) {
        this.dmg = x;
    }

    public int getInitialDamage() {
        return this.initialDamage;
    }

    public void viewWeapon() {
        Typer.typeStringln(String.format("\nWeapon: %s \nDamage: %s \nSpeed Reduction: %s\n%s\n", this.name, this.dmg, this.speedPenalty, getAbilityDescription()));
    }

    /**
     * Should be overriden in weapon subtype classes, starter weapons do not have abilities.
     * @return boolean
     */
    public boolean useAbility(Player plr, Enemy enemy, int enemyDodgeChance) {
        Typer.typeStringln("This weapon has no special ability.");
        return false;
    }
    
    //Longsword Override Method(s)
    public boolean getRiposte() {return false;}
    public boolean riposte(Player plr, Enemy enemy, int enemyDodgeChance) {return false;}
    public void setRiposte(boolean x, Player plr) {}


    public void reduceCooldown() {
        this.currentAbilityCooldown -= 1;
        if(this.currentAbilityCooldown < 0) {
            this.currentAbilityCooldown = 0;
        }
    }

    public int getCurrentCooldown() {
        return this.currentAbilityCooldown;
    }

    public void setCurrentCooldown(int x) {
        this.currentAbilityCooldown = x;
    }

    public int getAbilityCooldown() {
        return this.abilityCooldown;
    }

    protected String getAbilityDescription() {
        return "This weapon has no special ability.";
    }

    public void compareWeapon(Weapon wpn) {
        String compareDmg = "";
        if(this.dmg - wpn.getDmg() > 0) {
            compareDmg = "(+" + (this.dmg - wpn.getDmg()) + ")";
        } else if (this.dmg - wpn.getDmg() < 0) {
            compareDmg = "(" + (this.dmg - wpn.getDmg()) + ")";
        }
        String compareSpeedPen = "";
        if(this.speedPenalty - wpn.getSpeedPenalty() > 0) {
            compareSpeedPen = "(+" + (this.speedPenalty - wpn.getSpeedPenalty()) + ")";
        } else if (this.speedPenalty - wpn.getSpeedPenalty() < 0) {
            compareSpeedPen = "(" + (this.speedPenalty - wpn.getSpeedPenalty()) + ")";
        }
        Typer.typeStringln(String.format("\nWeapon: %s \nDamage: %s %s \nSpeed Reduction: %s %s\n%s\n", this.name, this.dmg, compareDmg, this.speedPenalty, compareSpeedPen, getAbilityDescription()));
    }


}
