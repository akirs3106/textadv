public class Axe extends Weapon {

    protected String subType;

    public Axe(String name, float dmg, String type, String subType) {
        super(name, dmg, type);
        this.subType = subType;
    }
    
}
