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
                    Typer.typeStrings(new String[] {
                        "You let out a terrifying roar that continues to echo throughout the room.",
                        String.format("You look at %s, who now seems more hesitant to approach you.", enemy.getName()),
                        String.format("%s's defense has been lowered!"),
                        String.format("%s's speed has been lowered!"),
                        String.format("Your damage has increased!")
                    }, 200);
                    enemy.reduceDefense(0.25);
                    enemy.reduceActiveSpeed((int)(enemy.getSpeed()*0.25));
                    this.setDamageMultiplier(1.5);
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

    @Override public void resetAbilityEffects(Enemy enemy) {
        switch(this.abilityName) {
            case "Battlecry": 
                enemy.resetDefenseReduction();
                enemy.calculateActiveSpeed();
                this.resetWeaponDamage();
            break;
        }
    }


}
