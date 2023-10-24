package src.com.java.Daggers;

import src.com.java.Enemy;
import src.com.java.Player;
import src.com.java.Typer;

public class NecromancerDagger extends Dagger {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;
    
    public NecromancerDagger() {
        super("The Necromancer's Dagger", 30, "necromancer dagger", 15, 2);
        this.abilityName = "Summon Undead";
        this.abilityDescription = "By sacrificing some of your own lifeforce, summon a random skeleton to attack your opponent.";
        this.abilityAttackDialogue = "You grip the Necromancer's Dagger, as it begins to drain some life out of you.";
    }
    //[!IMPORTANT!]
    //ABILITY NOT COMPLETED ON PURPOSE AS THE WEAPON IS CURRENTLY UNUSABLE IN THE CURRENT VERSION!
    @Override public boolean useAbility(Player plr, Enemy enemy, int enemyDodgeChance) {
        System.out.println("Subtype calss ability called!");
        if(this.currentAbilityCooldown <= 0) {
            this.currentAbilityCooldown = this.abilityCooldown;
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
        String turn = this.abilityCooldown == 1 ? "turn" : "turns";
        return String.format("Ability: %s\nAbility Description: %s\nCooldown: %s %s\n", this.abilityName, this.abilityDescription, this.abilityCooldown, turn);
    }

}
