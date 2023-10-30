package src.com.java.PlayerClasses;

import src.com.java.*;
import src.com.java.Swords.*;

public class Warrior extends Player {

    String abilityName;
    int abilityCooldown;
    String abilityDescription;
    boolean adrenalineRushActive;
    boolean retaliationActive;

    public Warrior(String abilityName, int abilityCooldown, String abilityDescription) {
        super("Warrior", new Sword("Rusty Sword", 10, "rusty sword", 20, 0), 100, 100, 30, 0);
        this.abilityName = abilityName;
        this.abilityCooldown = abilityCooldown;
        this.abilityDescription = abilityDescription;
        this.adrenalineRushActive = false;
        this.retaliationActive = false;
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

    @Override public boolean useAbility(Enemy enemy) {
        if(this.activeCooldown <= 0) {
            switch(this.abilityName) {
                case "Retaliation":

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
