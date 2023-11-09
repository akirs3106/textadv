package src.com.java.Daggers;

import src.com.java.Enemy;
import src.com.java.Player;
import src.com.java.Typer;
import java.util.Random;

public class CastillonDagger extends Dagger {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;
    
    public CastillonDagger(String name, int dmg, int speedPenalty) {
        super(name, dmg, "castillon dagger", speedPenalty, 3);
        this.abilityName = "Relentless Slashes";
        this.abilityDescription = "Wildly swing your blade at your opponent, hitting them 2-4 times.";
        this.abilityAttackDialogue = "You begin to slash your blade around wildly.";
    }
    
    @Override public boolean useAbility(Player plr, Enemy enemy, int enemyDodgeChance) {
        if(this.currentAbilityCooldown <= 0) {
            this.currentAbilityCooldown = this.abilityCooldown;
            Typer.typeStringln(abilityAttackDialogue);
            Random random = new Random();
            int hitDecider = 0;
            int hits = 2;
            for(int i = 0; i < 2; i++) {
                hitDecider = random.nextInt(100) + 1;
                if(i == 0) {
                    if(hitDecider <= 100) {
                        hits += 1;
                    } else {
                        break;
                    }
                } else if(i == 1) {
                    if(hitDecider <= 60) {
                        hits += 1;
                    } else {
                        break;
                    }
                } else if(i == 2) {
                    if(hitDecider <= 30) {
                        hits += 1;
                    } else {
                        break;
                    }
                } else if (i == 3) {
                    if(hitDecider <= 15) {
                        hits += 1;
                    } else {
                        break;
                    }
                }
            }

            for(int i = 0; i < hits; i++) {
                plr.attackEnemy(enemy, plr, enemyDodgeChance);
                if(enemy.getHp() <= 0) {
                    return true;
                }
            }
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
