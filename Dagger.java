public class Dagger extends Weapon {

    private String[] validSubtypes = {"rusty dagger", "rondel dagger", "stiletto dagger", "castillon dagger", "sacrificial dagger"};
    protected String subType; 

    public Dagger(String name, float dmg, String type, String subType) {
        super(name, dmg, type);
        for(int i = 0; i < validSubtypes.length; i++) {
            if(subType.equals(validSubtypes[i])) {
                throw new IllegalStateException("Invalid weapon subtype.");
            }
        }
        this.subType = subType.toLowerCase();
    }
    
}
