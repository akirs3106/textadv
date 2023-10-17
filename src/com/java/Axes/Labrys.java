package src.com.java.Axes;

public class Labrys extends Axe {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;
    
    public Labrys() {
        super("The Labrys", 55, "the labrys", 45);
        this.abilityName = "Thunderous Call";
        this.abilityDescription = "Channel the power of The Labrys to summon a lightning bolt to strike your enemy.";
        this.abilityAttackDialogue = "You raise The Labrys upwards as it begins to channel the energy of a storm.";
    }
    
    @Override public boolean useAbility() {
        return true;
    }

    @Override protected String getAbilityDescription() {
        return String.format("Ability: %s\n Ability Description: %s\n", this.abilityName, this.abilityDescription);
    }

}
