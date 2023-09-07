package src.com.java;

import java.util.*;
class Main {

    public final String[][] swordNames  = {
        {"Rusty Longsword", "Bronze Longsword", "Steel Longsword"},
        {"Rusty Shortsword", "Bronze Shortsword", "Steel Shortsword"},
        {"Rusty Rapier", "Bronze Rapier", "Steel Rapier"}
    };

    public final String[][] axeNames = {
        {"Rusty Battleaxe", "Bronze Battleaxe", "Steel Battleaxe"},
        {"Rusty Felling Axe", "Bronze Felling Axe", "Steel Felling Axe"},
        {"Rusty Halberd", "Bronze Halberd", "Steel Halberd"}
    };

    public final String[][] daggerNames = {
        {"Rusty Rondel Dagger", "Bronze Rondel Dagger", "Steel Rondel Dagger"},
        {"Rusty Stiletto Dagger", "Bronze Stiletto Dagger", "Steel Stiletto Dagger"},
        {"Rusty Castillon Dagger", "Bronze Castillon Dagger", "Steel Castillon Dagger"}
    };
    
    public static void main(String args[]) {


        String plrClasses[] = {"Warrior" , "Barbarian", "Rogue"};

        System.out.print("Classes:\n\n1. Warrior\n2. Barbarian\n3. Rogue\n\nChoose your class: ");
        
        Scanner scanner = new Scanner(System.in);

        String plrClass = plrClasses[scanner.nextInt()-1];
        scanner.close();

        Player plr = new Player(plrClass);

        // Enemy enemy = new Enemy("Skeleton Footman", new Sword("Steel Longsword", 10, "longsword", 10), "skeleton", 100, 100, 100);

        // plr.inspectWeapon();
        // plr.viewStats();

        // enemy.attackPlayer(plr);

        // plr.attackEnemy(enemy, plr);
        // plr.attackEnemy(enemy, plr);
        // plr.attackEnemy(enemy, plr);
        // plr.attackEnemy(enemy, plr);
        // plr.attackEnemy(enemy, plr);

        // plr.viewStats();

    }

    public void startEncounter(Enemy enemy) {

        System.out.println(String.format("%s approaches you!", enemy.getName()));

    }

    public Weapon createRandomWeapon(String type) {

        Random rand = new Random();

        int randWeaponSubTypeSelector = rand.nextInt((2-0) + 1);
        int randWeaponTierSelector = rand.nextInt((2-0) + 1);

        Weapon weapon;
        String weaponName;
        String weaponSubType;
        int weaponDamage;
        int weaponSpeedPenalty;

        switch(type) {
            case "sword": 
                weaponName = swordNames[randWeaponSubTypeSelector][randWeaponTierSelector];

                switch(randWeaponSubTypeSelector) {
                    case 0: 
                        weaponSubType = "longsword";
                    break;
                    case 1:
                        weaponSubType = "shortsword";
                    break;
                    case 2: 
                        weaponSubType = "rapier";
                    break;
                    default:
                        weaponSubType = null;
                    
                }

                switch(randWeaponTierSelector) {
                    case 0: 
                        weaponDamage = rand.nextInt((15 - 10) + 1) + 10;
                        weaponSpeedPenalty = rand.nextInt((30 - 20) + 20);
                    break;
                    case 1: 
                        weaponDamage = rand.nextInt((20 - 15) + 1) + 15;
                        weaponSpeedPenalty = rand.nextInt((35 - 25) + 25);
                    break;
                    case 2:
                        weaponDamage = rand.nextInt((25 - 20) + 1) + 20;
                        weaponSpeedPenalty = rand.nextInt((40 - 30) + 30);
                    break;
                    default:
                        weaponDamage = 0;
                        weaponSpeedPenalty = 0;

                }
                weapon = new Sword(weaponName, weaponDamage, weaponSubType, weaponSpeedPenalty);
                break;
            case "axe":
                weaponName = axeNames[randWeaponSubTypeSelector][randWeaponTierSelector];

                switch(randWeaponSubTypeSelector) {
                    case 0: 
                        weaponSubType = "battleaxe";
                    break;
                    case 1:
                        weaponSubType = "felling axe";
                    break;
                    case 2: 
                        weaponSubType = "halberd";
                    break;
                    default:
                        weaponSubType = null;
                }

                switch(randWeaponTierSelector) {
                    case 0: 
                        weaponDamage = rand.nextInt((25 - 20) + 1) + 20;
                        weaponSpeedPenalty = rand.nextInt((40 - 30) + 30);
                    break;
                    case 1: 
                        weaponDamage = rand.nextInt((30 - 25) + 1) + 25;
                        weaponSpeedPenalty = rand.nextInt((45 - 35) + 35);
                    break;
                    case 2:
                        weaponDamage = rand.nextInt((35 - 30) + 1) + 30;
                        weaponSpeedPenalty = rand.nextInt((50 - 45) + 45);
                    break;
                    default:
                        weaponDamage = 0;
                        weaponSpeedPenalty = 0;
                }

                weapon = new Axe(weaponName, weaponDamage, weaponSubType, weaponSpeedPenalty);
                break;
            case "dagger": 
                weaponName = axeNames[randWeaponSubTypeSelector][randWeaponTierSelector];

                switch(randWeaponSubTypeSelector) {
                    case 0: 
                        weaponSubType = "rondel dagger";
                    break;
                    case 1:
                        weaponSubType = "stiletto dagger";
                    break;
                    case 2: 
                        weaponSubType = "castillon dagger";
                    break;
                    default:
                        weaponSubType = null;
                }

                switch(randWeaponTierSelector) {
                    case 0: 
                        weaponDamage = rand.nextInt((13 - 8) + 1) + 8;
                        weaponSpeedPenalty = rand.nextInt((15 - 10) + 10);
                    break;
                    case 1: 
                        weaponDamage = rand.nextInt((18 - 13) + 1) + 13;
                        weaponSpeedPenalty = rand.nextInt((20 - 15) + 15);
                    break;
                    case 2:
                        weaponDamage = rand.nextInt((23 - 18) + 1) + 18;
                        weaponSpeedPenalty = rand.nextInt((25 - 20) + 20);
                    break;
                    default:
                        weaponDamage = 0;
                        weaponSpeedPenalty = 0;
                }
                weapon = new Dagger(weaponName, weaponDamage, weaponSubType, weaponSpeedPenalty);
            break;
            default:
                weapon = null;
        }
        return weapon;
    }

    public Enemy createRandomSkeleton() {



        String skeletonNames[] = {"Skeleton Warrior", "Skeleton Axeman", "Skeleton Assassin"};
        Random random = new Random();
        //Used to determine the name of the skeleton, as well as the type of weapon they're using
        int randSelector = random.nextInt((2-0) + 1);

        Weapon skelWeapon;
        String skelName = skeletonNames[randSelector];
        int skelHp;
        int skelSpeed;
        double skelXp;

        switch(randSelector) {
            case 0:
                skelWeapon = createRandomWeapon("sword");
                skelHp = random.nextInt((120 - 90) + 1) + 90;
                skelSpeed = random.nextInt((115 - 80) + 1) + 85;
            break;
            case 1: 
                skelWeapon = createRandomWeapon("axe");
                skelHp = random.nextInt((150 - 115) + 1) + 115;
                skelSpeed = random.nextInt((90 - 60) + 1) + 60;
            break;
            case 2:
                skelWeapon = createRandomWeapon("dagger");
                skelHp = random.nextInt((100 - 50) + 1) + 50;
                skelSpeed = random.nextInt((140 - 110) + 1) + 115;
            break;
            default:
            skelHp = 0;
            skelSpeed = 0;
            skelWeapon = createRandomWeapon(null);
            throw new IllegalStateException("randSelector is not within required range.");

        }

        skelXp = (skelHp + skelSpeed + skelWeapon.getDmg()) / 3;



        return new Enemy(skelName, skelWeapon, "skeleton", skelHp, skelSpeed, skelXp);

        
    }
}