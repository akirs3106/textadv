package src.com.java.PlayerClasses;

import src.com.java.*;
import src.com.java.Swords.*;

public class Warrior extends Player {

    String abilityName;
    int abilityCooldown;
    int activeCooldown;
    String abilityDescription;
    boolean adrenalineRushActive;
    boolean retaliationActive;

    public Warrior(String abilityName, int abilityCooldown, String abilityDescription) {
        super("Warrior", new Sword("Rusty Sword", 10, "rusty sword", 20, 0), 100, 100, 30);
        this.abilityName = abilityName;
        this.abilityCooldown = abilityCooldown;
        this.abilityDescription = abilityDescription;
        this.activeCooldown = 0;
        this.adrenalineRushActive = false;
        this.retaliationActive = false;
    }

    public void setAbilityName(String x) {
        this.abilityName = x;
    }

    public boolean useAbility(Enemy enemy) {
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
