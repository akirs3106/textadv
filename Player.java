public class Player {
    private int hp;
    private int xp;
    private int level;
    private int baseSpeed;
    protected int activeSpeed;
    private Weapon equippedWeapon;
    private String playerClass;

    public Player(int hp, String playerClass) {
        this.hp = hp;
        this.xp = 0;
        this.level = 0;
        this.playerClass = playerClass;

        if(playerClass.toLowerCase().equals("warrior")){
            this.equippedWeapon = new Sword("Rusty Sword", 10, "sword", "rusty sword");
            this.baseSpeed = 100;
        } else if (playerClass.toLowerCase().equals("barbarian")) {
            this.equippedWeapon = new Axe("Rusty Axe", 20, "axe", "rusty axe");
            this.baseSpeed = 75;
        } else if (playerClass.toLowerCase().equals("rogue")) {
            this.equippedWeapon = new Dagger("Rusty Dagger", 8, "dagger", "rusty dagger");
            this.baseSpeed = 115;
        }
            this.activeSpeed = this.baseSpeed;
    }

    public void inspectWeapon(){
        String str = "";

        str += "Name: " + equippedWeapon.name + "/n";
    }
    
}
