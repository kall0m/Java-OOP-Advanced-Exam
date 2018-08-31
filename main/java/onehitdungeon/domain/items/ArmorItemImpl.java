package onehitdungeon.domain.items;

import onehitdungeon.interfaces.ArmorItem;

public class ArmorItemImpl extends AbstractItem implements ArmorItem {
    public ArmorItemImpl(Integer battlePower, Double priceForUpgrade) {
        super(battlePower, priceForUpgrade);
    }
}
