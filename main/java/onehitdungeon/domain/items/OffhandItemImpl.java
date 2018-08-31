package onehitdungeon.domain.items;

import onehitdungeon.interfaces.OffhandItem;

public class OffhandItemImpl extends AbstractItem implements OffhandItem {
    public OffhandItemImpl(Integer battlePower, Double priceForUpgrade) {
        super(battlePower, priceForUpgrade);
    }
}
