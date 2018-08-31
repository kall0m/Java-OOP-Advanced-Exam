package onehitdungeon.domain.heroes;

import onehitdungeon.domain.items.ArmorItemImpl;
import onehitdungeon.domain.items.OffhandItemImpl;
import onehitdungeon.domain.items.WeaponItemImpl;

public class PaladinHero extends AbstractHero {
    private final static Integer PALADIN_WEAPON_BATTLE_POWER = 20;
    private final static Double PALADIN_WEAPON_PRICE_FOR_UPGRADE = 10D;

    private final static Integer PALADIN_OFFHAND_BATTLE_POWER = 10;
    private final static Double PALADIN_OFFHAND_PRICE_FOR_UPGRADE = 10D;

    private final static Integer PALADIN_ARMOR_BATTLE_POWER = 25;
    private final static Double PALADIN_ARMOR_PRICE_FOR_UPGRADE = 20D;

    public PaladinHero(String name) {
        super(name);
        this.setWeaponItem(new WeaponItemImpl(PALADIN_WEAPON_BATTLE_POWER, PALADIN_WEAPON_PRICE_FOR_UPGRADE));
        this.setOffhandItem(new OffhandItemImpl(PALADIN_OFFHAND_BATTLE_POWER, PALADIN_OFFHAND_PRICE_FOR_UPGRADE));
        this.setArmorItem(new ArmorItemImpl(PALADIN_ARMOR_BATTLE_POWER, PALADIN_ARMOR_PRICE_FOR_UPGRADE));
    }

    @Override
    public String getHeroClass() {
        return this.getClass().getSimpleName().replace("Hero", "");
    }

    @Override
    public Integer getTotalBattlePower() {
        return ((this.getWeapon().getBattlePower()
                + this.getOffhand().getBattlePower()
                + this.getArmor().getBattlePower()) * 4) / 9;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("%s - Lvl. %d Paladin")
                .append(System.lineSeparator())
                .append("* Mace - %d (BP)")
                .append(System.lineSeparator())
                .append("* Shield - %d (BP)")
                .append(System.lineSeparator())
                .append("* Cuirass - %d (BP)")
                .append(System.lineSeparator())
                .append("####################")
                .append(System.lineSeparator())
                .append("Gold: %.2f")
                .append(System.lineSeparator())
                .append("Upgrade cost: %.2f");

        return String.format(sb.toString(),
                this.getName(),
                this.getTimesTrained() + 1,
                this.getWeapon().getBattlePower(),
                this.getOffhand().getBattlePower(),
                this.getArmor().getBattlePower(),
                this.getGold(),
                this.getTotalPriceForUpgrade());
    }
}
