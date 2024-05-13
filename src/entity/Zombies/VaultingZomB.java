package entity.Zombies;

public class VaultingZomB extends Zombie {

    private boolean vault_on = true;

    public VaultingZomB(String name, int extra_health, boolean vault_on) {   //untuk subclass non-aquatic
        super(name, 100 + extra_health, 100, 1, false);
        this.vault_on = vault_on;
    }
    public VaultingZomB(String name, int extra_health_aquatic, boolean vault_on, boolean is_aquatic) {   //untuk subclass aquatic
        super(name, 100 + extra_health_aquatic, 100, 1, true);
    }
    public void setVault(boolean vault) {
        this.vault_on = vault;
    }
    public  void attack() {
        
    }

    // public boolean getAquatic() {    
    //     return is_aquatic;
    // }

    /*    public void takeDamage(Plant Tanaman) {

        } */
}
