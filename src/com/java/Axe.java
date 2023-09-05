package src.com.java;

public class Axe extends Weapon {

    private String[] validSubtypes = {"rusty axe", "battleaxe", "felling axe", "halberd", "the labrys"};
    protected String subType; 

    public Axe(String name, float dmg, String subType, int speedPenalty) {
        super(name, dmg, "axe", speedPenalty);
        for(int i = 0; i < validSubtypes.length; i++) {
            if(subType.equals(validSubtypes[i])) {
                this.subType = subType.toLowerCase();
                break;
            }
            if(i == validSubtypes.length-1) {
                throw new IllegalStateException("Invalid weapon subtype: " + subType);
            }
        }
    }
    
}
