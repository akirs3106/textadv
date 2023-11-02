package src.com.java.PlayerClasses;

import src.com.java.*;
import src.com.java.Swords.*;

public class Warrior extends Player {

    String abilityName;
    int abilityCooldown;
    String abilityDescription;
    boolean adrenalineRushActive;
    boolean retaliationActive;
    int retaliationDamage;
    boolean hitInRetaliation;

    public Warrior(String abilityName, int abilityCooldown, String abilityDescription) {
        super("Warrior", new Sword("Rusty Sword", 10, "rusty sword", 20, 0), 100, 100, 30, 0);
        this.abilityName = abilityName;
        this.abilityCooldown = abilityCooldown;
        this.abilityDescription = abilityDescription;
        this.adrenalineRushActive = false;
        this.retaliationActive = false;
        this.retaliationDamage = this.equippedWeapon.getDmg();
        this.hitInRetaliation = false;
    }

    @Override public void setAbilityName(String x) {
        this.abilityName = x;
    }

    @Override public boolean getAdrenalineRushActive() {
        return this.adrenalineRushActive;
    }

    @Override public void setAdrenalineRushActive(boolean x) {
        this.adrenalineRushActive = x;
    }

    @Override public boolean getRetaliation() {
        return this.retaliationActive;
    }

    @Override public void setRetaliation(boolean x) {
        this.retaliationActive = x;
    }

    @Override public void setRetaliationDamage(int x) {
        this.retaliationDamage = this.equippedWeapon.getDmg() + x;
    }

    @Override public void setHitInRetaliation(Enemy enemy) {
        this.hitInRetaliation = true;
        this.setRetaliationDamage(enemy.getWeapon().getDmg());
    }

    @Override public boolean getHitInRetaliation() {
        return this.hitInRetaliation;
    }

    @Override public void triggerRetaliation (Enemy enemy) {
        Typer.typeStringln(String.format("Using the power from %s's attack in addition to your own weapon's power, you return a counterattack!", enemy.getName()));
        
        this.attackEnemyNoDodge(enemy, this, this.retaliationDamage);
        this.resetRetaliation();
    }

    @Override public void resetRetaliation() {
        this.hitInRetaliation = false;
        this.retaliationActive = false;
        this.setRetaliationDamage(0);
    }

    @Override public boolean useAbility(Enemy enemy) {
        if(this.activeCooldown <= 0) {
            this.activeCooldown = this.abilityCooldown;
            switch(this.abilityName) {
                case "Retaliation":
                    Typer.typeStringln("You prepare yourself for your enemy's next attack.");
                    this.retaliationActive = true;
                break;
                case "Adrenaline Rush":

                break;
            }
            return true;
        } else {
            String turn = activeCooldown == 1 ? "turn" : "turns";
            Typer.typeStringln(String.format("%s is on cooldown for %s more %s!", this.abilityName, this.activeCooldown, turn));
            return false;
        }
    }

}
