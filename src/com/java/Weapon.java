package src.com.java;

public class Weapon {
    
    protected String name;
    protected float dmg;
    protected String type;
    protected int speedPenalty;

    public Weapon(String name, float dmg, String type, int speedPenalty) {
        this.name = name;
        this.dmg = dmg;
        this.type = type;
        this.speedPenalty = speedPenalty;
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
