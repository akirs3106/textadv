package src.com.java.Swords;

import src.com.java.Enemy;
import src.com.java.Player;
import src.com.java.Typer;

public class Shortsword extends Sword {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;

    public Shortsword(String name, int dmg, int speedPenalty) {
        super(name, dmg, "shortsword", speedPenalty, 2);
        this.abilityName = "\"Extended\" Melee Attack";
        this.abilityDescription = "Throw the weapon at your enemy, dealing increased damage and increasing your dodge chance, but making you unable to attack for one turn.";
        this.abilityAttackDialogue = String.format("You throw your %s with all your might.");
    }
    
    @Override public boolean useAbility(Player plr, Enemy enemy, int enemyDodgeChance) {
        if(this.abilityCooldown <= 0) {
            this.abilityCooldown = 3;
            return true;
        } else {
            if(this.abilityCooldown == 1) {
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
