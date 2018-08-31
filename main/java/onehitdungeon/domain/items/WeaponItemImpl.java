package onehitdungeon.domain.items;

import onehitdungeon.interfaces.WeaponItem;

public class WeaponItemImpl extends AbstractItem implements WeaponItem {
    public WeaponItemImpl(Integer battlePower, Double priceForUpgrade) {
        super(battlePower, priceForUpgrade);
    }
}
