package src.com.java.PlayerClasses;

import src.com.java.*;
import src.com.java.Daggers.*;

public class Rogue extends Player {

    String abilityName;
    int abilityCooldown;
    String abilityDescription;
    boolean hideActive;
    boolean keenEyedActive;
    
    public Rogue(String abilityName, int abilityCooldown, String abilityDescription) {
        super("Rogue", new Dagger("Rusty Dagger", 8, "rusty dagger", 0, 0), 125, 75, 40, 0);
        this.abilityName = abilityName;
        this.abilityCooldown = abilityCooldown;
        this.abilityDescription = abilityDescription;
        this.hideActive = false;
        this.keenEyedActive = false;
    }

    @Override public void setAbilityName(String x) {
        this.abilityName = x;
    }

    @Override public boolean getKeenEyedActive() {
        return this.keenEyedActive;
    }

    @Override public void setKeenEyedActive(boolean x) {
        this.keenEyedActive = x;
    }

    @Override public boolean getHideActive() {
        return this.hideActive;
    }

    @Override public void setHideActive(boolean x, Enemy enemy) {
        this.hideActive = x;
        if(!x) {
            this.calculateDodgeChance(enemy);
        }
    }

    @Override public boolean useAbility(Enemy enemy) {
        if(this.activeCooldown <= 0) {
            this.activeCooldown = abilityCooldown;
            switch(this.abilityName) {
                case "Hide":
                    Typer.typeStrings(new String[] {
                        String.format("Whilst %s is briefly distracted, you utilize the darkness of the room to hide yourself", enemy.getName()),
                        String.format("%s begins to look around, wondering where you may have disappeared to.", enemy.getName())
                    }, 500);
                    this.hideActive = true;
                    this.setDodgeChance(80);
                break;
                case "Keen Eyed":
                    Typer.typeStringln(String.format("You begin to inspect %s in much more detail", enemy.getName()));
                    Typer.typeStrings(new String[] {
                        String.format("You begin to study %s's physique...", enemy.getName()),
                        String.format("You have learned %s's health pool!", enemy.getName()),
                        String.format("You begin to observe %s's footwork.", enemy.getName()),
                        String.format("You have learned %s's speed statistic!", enemy.getName()),
                        String.format("You begin to study %s's %s...", enemy.getName(), enemy.getWeapon().getName()),
                        String.format("You gaze at the sharp edge of %s's %s...", enemy.getName(), enemy.getWeapon().getName()),
                        String.format("You have learned the damage of %s's %s!", enemy.getName(), enemy.getWeapon().getName()),
                        String.format("You begin to study the materials of %s %s...", enemy.getName(), enemy.getWeapon().getName()),
                        String.format("You have discovered the speed reduction of %s's %s!", enemy.getName(), enemy.getWeapon().getName())
                    }, 500);
                    this.keenEyedActive = true;
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
