package src.com.java.Daggers;

import src.com.java.Enemy;
import src.com.java.Player;
import src.com.java.Typer;

public class StilettoDagger extends Dagger {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;

    public StilettoDagger(String name, int dmg, int speedPenalty) {
        super(name, dmg, "stiletto dagger", speedPenalty, 2);
        this.abilityName = "Precision Strike";
        this.abilityDescription = "Attack your enemy's weakpoint, having an increased chance of a critical hit.";
        this.abilityAttackDialogue = String.format("You aim and thrust your %s forwards.", name);

    }
    
    @Override public boolean useAbility(Player plr, Enemy enemy, int enemyDodgeChance) {
        if(this.currentAbilityCooldown <= 0) {
            this.currentAbilityCooldown = this.abilityCooldown;
            Typer.typeStringln(abilityDescription);
            plr.setCritChanceCap(2);
            plr.attackEnemy(enemy, plr, enemyDodgeChance);
            plr.setCritChanceCap(10);
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
