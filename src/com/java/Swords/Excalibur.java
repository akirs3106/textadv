package src.com.java.Swords;

public class Excalibur extends Sword {
    
    protected String abilityDescription;
    protected String abilityName;
    protected String abilityAttackDialogue;

    public Excalibur() {
        super("Excalibur", 40, "excalibur", 35);
        this.abilityName = "Holy Imbuement";
        this.abilityDescription = "Using this will imbue the sword with holy power, doubling its damage for its next attack.";
        this.abilityAttackDialogue = "Excalibur begins to glow with a holy aura, as it is imbued with holy power.";
    }
    
    @Override public boolean useAbility() {
        return true;
    }

    @Override protected String getAbilityDescription() {
        return String.format("Ability: %s\n Ability Description: %s\n", this.abilityName, this.abilityDescription);
    }
    
}
