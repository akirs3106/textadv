package src.com.java.PlayerClasses;

import src.com.java.*;
import src.com.java.Axes.*;

public class Barbarian extends Player {

    String abilityName;
    int abilityCooldown;
    String abilityDescription;
    boolean battlecryActive;
    
    public Barbarian(String abilityName, int abilityCooldown, String abilityDescription) {
        super("Barbarian", new Axe("Rusty Axe", 20, "rusty axe", 30, 0), 75, 125, 20, 0);
        this.abilityName = abilityName;
        this.abilityCooldown = abilityCooldown;
        this.abilityDescription = abilityDescription;
        this.battlecryActive = false;
    }

    @Override public void setAbilityName(String x) {
        this.abilityName = x;
    }

    @Override public void setBattlecry(boolean x) {
        this.battlecryActive = x;
    }

    @Override public boolean getBattlecry() {
        return this.battlecryActive;
    }

    @Override public boolean useAbility(Enemy enemy) {
        if(this.activeCooldown <= 0) {
            switch(this.abilityName) {
                case "Battlecry":

                break;
                case "Savage Charge":
                    
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
