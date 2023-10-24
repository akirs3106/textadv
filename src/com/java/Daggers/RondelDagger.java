package src.com.java.Daggers;

import src.com.java.Enemy;
import src.com.java.Player;
import src.com.java.Typer;

public class RondelDagger extends Dagger {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;

    public RondelDagger(String name, int dmg, int speedPenalty) {
        super(name, dmg, "rondel dagger", speedPenalty, 2);
        this.abilityName = "Counterattack";
        this.abilityDescription = "If attacked first, use the recoil from being hit to attack your opponent with increased damage";
    }
    
    @Override public boolean useAbility(Player plr, Enemy enemy, int enemyDodgeChance) {
        System.out.println("Subtype calss ability called!");
        if(this.currentAbilityCooldown <= 0) {
            this.currentAbilityCooldown = this.abilityCooldown;
            Typer.typeStringln(String.format("You swing your %s at %s, using the recoil from their attack to your advantage!", this.name, enemy.getName()));
            if(enemyDodgeChance > 0) {
                plr.attackEnemyAbility(enemy, plr, enemyDodgeChance, (int)(plr.getWeapon().getDmg()*1.6));
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
