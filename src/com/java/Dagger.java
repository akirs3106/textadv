package src.com.java;

public class Dagger extends Weapon {

    private String[] validSubtypes = {"rusty dagger", "rondel dagger", "stiletto dagger", "castillon dagger", "sacrificial dagger"};
    protected String subType; 

    public Dagger(String name, int dmg, String subType, int speedPenalty) {
        super(name, dmg, "dagger", speedPenalty);
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
