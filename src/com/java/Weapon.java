package src.com.java;

public class Weapon {
    
    protected String name;
    protected int dmg;
    protected String type;
    protected int speedPenalty;
    protected int abilityCooldown;

    public Weapon(String name, int dmg, String type, int speedPenalty) {
        this.name = name;
        this.dmg = dmg;
        this.type = type;
        this.speedPenalty = Math.abs(speedPenalty);
        this.abilityCooldown = 0;
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

    public void reduceCooldown() {
        this.abilityCooldown -= 1;
        if(this.abilityCooldown < 0) {
            this.abilityCooldown = 0;
        }
    }

    public int getCooldown() {
        return this.abilityCooldown;
    }

    protected String getAbilityDescription() {
        return "Ability: This weapon has no special ability.";
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
