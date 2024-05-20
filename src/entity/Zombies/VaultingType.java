package entity.Zombies;

public class VaultingType extends Zombie {

    private boolean vault_on = true;
    private float speed = -0.3f;

    public VaultingType(String name, int extra_health, boolean vault_on, boolean is_aquatic, int x, int y) {
        super(name, 100 + extra_health, 100, 1, is_aquatic, x ,y);
        this.vault_on = vault_on;
        setSpeed(speed);
    }

    public boolean getVault() {
        return vault_on;
    }
    
    public void setVault(boolean vault_on) {
        this.vault_on = vault_on;
    }

    public void action() {
        setVault(false);
        setX(getX()-130);
        setSpeed(-0.15f);
    }
    
    public void actionStop() {
        
    }

    // public boolean getAquatic() {    
    //     return is_aquatic;
    // }

    /*    public void takeDamage(Plant Tanaman) {

        } */
}
