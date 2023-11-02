package src.com.java.PlayerClasses;

import src.com.java.*;
import src.com.java.Axes.*;
import java.util.Random;

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
            this.activeCooldown = this.abilityCooldown;
            switch(this.abilityName) {
                case "Battlecry":
                    Typer.typeStrings(new String[] {
                        "You let out a terrifying roar that continues to echo throughout the room.",
                        String.format("You look at %s, who now seems more hesitant to approach you.", enemy.getName()),
                        String.format("%s's defense has been lowered!", enemy.getName()),
                        String.format("%s's speed has been lowered!", enemy.getName()),
                        String.format("Your damage has increased!")
                    }, 200);
                    enemy.reduceDefense(0.25);
                    enemy.reduceActiveSpeed((int)(enemy.getSpeed()*0.25));
                    this.setDamageMultiplier(1.5);
                    this.abilityActiveLength = 2;
                break;
                case "Savage Charge":
                    Typer.typeStringln(String.format("You rush forwards towards %s.", enemy.getName()));
                    Random random = new Random();
                    int successDecider = random.nextInt(100) + 1;
                    if(successDecider <= 90) {
                        Typer.typeStringln(String.format("You shoulder slams straight into %s, leaving them dazed.", enemy.getName()));
                        enemy.setStunned(true);
                    } else {
                        Typer.typeStringln(String.format("%s manages to avoid your charge!", enemy.getName()));
                    }
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
