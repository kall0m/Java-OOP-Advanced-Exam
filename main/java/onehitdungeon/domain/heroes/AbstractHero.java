package onehitdungeon.domain.heroes;

import onehitdungeon.interfaces.ArmorItem;
import onehitdungeon.interfaces.Hero;
import onehitdungeon.interfaces.OffhandItem;
import onehitdungeon.interfaces.WeaponItem;

public abstract class AbstractHero implements Hero {
    private String name;

    private WeaponItem weaponItem;

    private OffhandItem offhandItem;

    private ArmorItem armorItem;

    private Double gold;

    private Integer timesTrained;

    protected AbstractHero(String name) {
        this.setName(name);
        this.setGold(0D);
        this.setTimesTrained(0);
    }

    private void setName(String name) {
        this.name = name;
    }

    protected void setWeaponItem(WeaponItem weaponItem) {
        this.weaponItem = weaponItem;
    }

    protected void setOffhandItem(OffhandItem offhandItem) {
        this.offhandItem = offhandItem;
    }

    protected void setArmorItem(ArmorItem armorItem) {
        this.armorItem = armorItem;
    }

    private void setGold(Double gold) {
        this.gold = gold;
    }

    protected Integer getTimesTrained() {
        return timesTrained;
    }

    private void setTimesTrained(Integer timesTrained) {
        this.timesTrained = timesTrained;
    }

    @Override
    public Double getGold() {
        return this.gold;
    }

    @Override
    public void earnGold(Double gold) {
        this.gold += gold;
    }

    @Override
    public void payGold(Double gold) {
        this.gold -= gold;
    }

    @Override
    public WeaponItem getWeapon() {
        return this.weaponItem;
    }

    @Override
    public OffhandItem getOffhand() {
        return this.offhandItem;
    }

    @Override
    public ArmorItem getArmor() {
        return this.armorItem;
    }

    @Override
    public Double getTotalPriceForUpgrade() {
        return this.getWeapon().getPriceForUpgrade()
                + this.getOffhand().getPriceForUpgrade()
                + this.getArmor().getPriceForUpgrade();
    }

    @Override
    public String getName() {
        return this.name;
    }
}
