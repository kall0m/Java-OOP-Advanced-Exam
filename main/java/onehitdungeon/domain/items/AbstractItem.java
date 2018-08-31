package onehitdungeon.domain.items;

import onehitdungeon.interfaces.Item;

public abstract class AbstractItem implements Item {
    private Integer battlePower;

    private Double priceForUpgrade;

    protected AbstractItem(Integer battlePower, Double priceForUpgrade) {
        this.setBattlePower(battlePower);
        this.setPriceForUpgrade(priceForUpgrade);
    }

    @Override
    public Integer getBattlePower() {
        return this.battlePower;
    }

    @Override
    public Double getPriceForUpgrade() {
        return this.priceForUpgrade;
    }

    protected void setBattlePower(Integer battlePower) {
        this.battlePower = battlePower;
    }

    protected void setPriceForUpgrade(Double priceForUpgrade) {
        this.priceForUpgrade = priceForUpgrade;
    }
}
