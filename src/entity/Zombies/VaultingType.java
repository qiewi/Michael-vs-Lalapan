package entity.Zombies;

public class VaultingType extends Zombie {

    private boolean vault_on = true;

    public VaultingType(String name, int extra_health, boolean vault_on, int x, int y) {
        super(name, 100 + extra_health, 100, 1, false, x ,y);
        this.vault_on = vault_on;
    }

    public boolean getVault() {
        return vault_on;
    }
    
    public void setVault(boolean vault_on) {
        this.vault_on = vault_on;
    }

    public void action() {
        setX(getX() - 80);
        setVault(false);
    }
    
    public void actionStop() {
        
    }

    // public boolean getAquatic() {    
    //     return is_aquatic;
    // }

    /*    public void takeDamage(Plant Tanaman) {

        } */
}
