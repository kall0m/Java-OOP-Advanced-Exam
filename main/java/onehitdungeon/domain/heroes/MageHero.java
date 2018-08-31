package onehitdungeon.domain.heroes;

import onehitdungeon.domain.items.ArmorItemImpl;
import onehitdungeon.domain.items.OffhandItemImpl;
import onehitdungeon.domain.items.WeaponItemImpl;

public class MageHero extends AbstractHero{
    private final static Integer MAGE_WEAPON_BATTLE_POWER = 45;
    private final static Double MAGE_WEAPON_PRICE_FOR_UPGRADE = 15D;

    private final static Integer MAGE_OFFHAND_BATTLE_POWER = 25;
    private final static Double MAGE_OFFHAND_PRICE_FOR_UPGRADE = 20D;

    private final static Integer MAGE_ARMOR_BATTLE_POWER = 10;
    private final static Double MAGE_ARMOR_PRICE_FOR_UPGRADE = 25D;

    public MageHero(String name) {
        super(name);
        this.setWeaponItem(new WeaponItemImpl(MAGE_WEAPON_BATTLE_POWER, MAGE_WEAPON_PRICE_FOR_UPGRADE));
        this.setOffhandItem(new OffhandItemImpl(MAGE_OFFHAND_BATTLE_POWER, MAGE_OFFHAND_PRICE_FOR_UPGRADE));
        this.setArmorItem(new ArmorItemImpl(MAGE_ARMOR_BATTLE_POWER, MAGE_ARMOR_PRICE_FOR_UPGRADE));
    }

    @Override
    public String getHeroClass() {
        return this.getClass().getSimpleName().replace("Hero", "");
    }

    @Override
    public Integer getTotalBattlePower() {
        return ((this.getWeapon().getBattlePower()
                + this.getArmor().getBattlePower()
                - this.getOffhand().getBattlePower()) * 3) / 4;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("%s - Lvl. %d Mage")
                .append(System.lineSeparator())
                .append("* Staff - %d (BP)")
                .append(System.lineSeparator())
                .append("* Orb - %d (BP)")
                .append(System.lineSeparator())
                .append("* Cape - %d (BP)")
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
