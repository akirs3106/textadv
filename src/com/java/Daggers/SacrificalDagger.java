package src.com.java.Daggers;

import src.com.java.Enemy;
import src.com.java.Player;
import src.com.java.Typer;

public class SacrificalDagger extends Dagger {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;

    public SacrificalDagger() {
        super("Sacrifical Dagger", 30, "sacrificial dagger", 20, 2);
        this.abilityName = "Sacrifical Strike";
        this.abilityDescription = "Offer your own blood to the dagger, increasing the power of its next attack.";
        this.abilityAttackDialogue = "You strike yourself with your own Sacrifical Dagger.";
    }
    
    @Override public boolean useAbility(Player plr, Enemy enemy, int enemyDodgeChance) {
        if(this.currentAbilityCooldown <= 0) {
            this.currentAbilityCooldown = this.abilityCooldown;
            Typer.typeStrings(new String[] {
                this.abilityAttackDialogue,
                "Your blood flows into the dagger, as its engravings begin to glow crimson.",
                "Your Sacrificial Dagger pulses with great power."
            }, 500);
            plr.takeDamage(20);
            plr.setDamageMultiplier(2);
            return true;
        } else {
            if(this.currentAbilityCooldown == 1) {
                Typer.typeStringln(String.format("%s is on cooldown for 1 turn!", this.abilityName));
            } else {
                Typer.typeStringln(String.format("%s is on cooldown for %s more turns!", this.abilityName, this.currentAbilityCooldown));
            }
            return false;
        }
    }

    @Override protected String getAbilityDescription() {
        return String.format("Ability: %s\n Ability Description: %s\nCooldown: %s turns\n", this.abilityName, this.abilityDescription, this.abilityCooldown);
    }
    
}
