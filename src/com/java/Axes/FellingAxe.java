package src.com.java.Axes;

import src.com.java.Enemy;
import src.com.java.Player;
import src.com.java.Typer;
import java.util.Random;

public class FellingAxe extends Axe {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;
    
    public FellingAxe(String name, int dmg, int speedPenalty) {
        super(name, dmg, "felling axe", speedPenalty, 0);
        this.abilityName = "Skull Splitter";
        this.abilityDescription = "When used, you have a small chance of killing an enemy immediately, or doing no damage at all.";
        this.abilityAttackDialogue = String.format("You raise your %s high above your head.", name);
    }
    
    @Override public boolean useAbility(Player plr, Enemy enemy, int enemyDodgeChance) {
        if(this.currentAbilityCooldown <= 0) {
            this.currentAbilityCooldown = this.abilityCooldown;
            Typer.typeStringln(this.abilityAttackDialogue);
            Random random = new Random();
            int hitDecider = random.nextInt(100) + 1;
            Typer.wait(1000);
            if(enemy.getType().equals("boss")) {
                if(hitDecider <= 1) {
                    enemy.takeRawDamage(enemy.getHp(), plr);
                } else {
                    Typer.typeStringln(String.format("Your %s flys right past %s's head, slamming into the ground.", this.name, enemy.getName()));
                }
            } else {
                if(hitDecider <= 10) {
                    enemy.takeRawDamage(enemy.getHp(), plr);
                } else {
                    Typer.typeStringln(String.format("Your %s flys right past %s's head, slamming into the ground.", this.name, enemy.getName()));
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
        return String.format("Ability: %s\n Ability Description: %s\nCooldown: %s turns\n", this.abilityName, this.abilityDescription, this.abilityCooldown);
    }

}
