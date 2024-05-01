package entity.Zombies;

public class VaultingZombie extends Zombie {

    private boolean vault_on = true;

    public VaultingZombie(String name, int extra_health, boolean vault_on) {
        super(name, 100 + extra_health, 100, 1, false);
        this.vault_on = vault_on;
    }

    public boolean getVault() {
        return vault_on;
    }

    public void setVault(boolean vault) {
        this.vault_on = vault;
    }

    public void attack() {
        
    }

    // public boolean getAquatic() {    
    //     return is_aquatic;
    // }

    /*    public void takeDamage(Plant Tanaman) {

        } */
}
