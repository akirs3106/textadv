public class Weapon {
    
    protected String name;
    protected float dmg;
    protected String type;

    public Weapon(String name, float dmg, String type) {
        this.name = name;
        this.dmg = dmg;
        this.type = type;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public float getDmg(){
        return this.dmg;
    }

    public void setDmg(float dmg){
        this.dmg = dmg;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }


}
