package src.com.java.Swords;

import src.com.java.Enemy;
import src.com.java.Player;
import src.com.java.Typer;
import java.util.Random;

public class Longsword extends Sword {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;
    protected boolean riposte;

    public Longsword(String name, int dmg, int speedPenalty) {
        super(name, dmg, "longsword", speedPenalty, 1);
        this.abilityName = "Riposte";
        this.abilityDescription = "If attacked, you have a chance to block incoming damage and deal double damage to your opponent.";
        this.abilityAttackDialogue = "You ready your longsword in a defensive stance.";
        
    }
    


    @Override public boolean useAbility(Player plr, Enemy enemy, int enemyDodgeChance) {
        if(this.currentAbilityCooldown <= 0) {
            this.currentAbilityCooldown = this.abilityCooldown;
            Typer.typeStringln(abilityAttackDialogue);
            this.riposte = true;
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

    public boolean riposte(Player plr, Enemy enemy, int enemyDodgeChance) {
        Random random = new Random();
        int riposteDecider = random.nextInt(100) + 1;
        if(riposteDecider <= 75) {
            Typer.typeStringln(String.format("%s's %s is deflected by your %s!", enemy.getName(), enemy.getWeapon().getName(), plr.getWeapon().getName()));
            Typer.wait(200);
            plr.setDamageMultiplier(2.0);
            plr.attackEnemy(enemy, plr, enemyDodgeChance);
            plr.setDamageMultiplier(1);
            this.riposte = false;
            return true;
        } else {
            return false;
        }
    }

    @Override protected String getAbilityDescription() {
        String turn = this.abilityCooldown == 1 ? "turn" : "turns";
        return String.format("Ability: %s\nAbility Description: %s\nCooldown: %s %s\n", this.abilityName, this.abilityDescription, this.abilityCooldown, turn);
    }
    
}
