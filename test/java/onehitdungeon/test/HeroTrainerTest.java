package onehitdungeon.test;

import onehitdungeon.core.HeroTrainerImpl;
import onehitdungeon.domain.items.ArmorItemImpl;
import onehitdungeon.domain.items.OffhandItemImpl;
import onehitdungeon.domain.items.WeaponItemImpl;
import onehitdungeon.interfaces.ArmorItem;
import onehitdungeon.interfaces.Hero;
import onehitdungeon.interfaces.HeroTrainer;
import onehitdungeon.interfaces.OffhandItem;
import onehitdungeon.interfaces.WeaponItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeroTrainerTest {
    private final static Integer PALADIN_WEAPON_BATTLE_POWER = 20;
    private final static Double PALADIN_WEAPON_PRICE_FOR_UPGRADE = 10D;

    private final static Integer PALADIN_OFFHAND_BATTLE_POWER = 10;
    private final static Double PALADIN_OFFHAND_PRICE_FOR_UPGRADE = 10D;

    private final static Integer PALADIN_ARMOR_BATTLE_POWER = 25;
    private final static Double PALADIN_ARMOR_PRICE_FOR_UPGRADE = 20D;

    private final static Integer MAGE_WEAPON_BATTLE_POWER = 45;
    private final static Double MAGE_WEAPON_PRICE_FOR_UPGRADE = 15D;

    private final static Integer MAGE_OFFHAND_BATTLE_POWER = 25;
    private final static Double MAGE_OFFHAND_PRICE_FOR_UPGRADE = 20D;

    private final static Integer MAGE_ARMOR_BATTLE_POWER = 10;
    private final static Double MAGE_ARMOR_PRICE_FOR_UPGRADE = 25D;

    private final static Integer PALADIN_WEAPON_BATTLE_POWER_INCREASER = 60;
    private final static Integer PALADIN_WEAPON_PRICE_FOR_UPGRADE_INCREASER = 50;
    private final static Integer PALADIN_OFFHAND_BATTLE_POWER_INCREASER = 50;
    private final static Integer PALADIN_OFFHAND_PRICE_FOR_UPGRADE_INCREASER = 100;
    private final static Integer PALADIN_ARMOR_BATTLE_POWER_INCREASER = 20;
    private final static Integer PALADIN_ARMOR_PRICE_FOR_UPGRADE_INCREASER = 75;

    private final static Integer MAGE_WEAPON_BATTLE_POWER_INCREASER = 20;
    private final static Integer MAGE_WEAPON_PRICE_FOR_UPGRADE_INCREASER = 67;
    private final static Integer MAGE_OFFHAND_BATTLE_POWER_INCREASER = 20;
    private final static Integer MAGE_OFFHAND_PRICE_FOR_UPGRADE_INCREASER = 100;
    private final static Integer MAGE_ARMOR_BATTLE_POWER_INCREASER = 100;
    private final static Integer MAGE_ARMOR_PRICE_FOR_UPGRADE_INCREASER = 100;

    private HeroTrainer heroTrainer;

    private Hero paladin;
    private Hero mage;

    @Before
    public void init() {
        this.heroTrainer = new HeroTrainerImpl();

        this.paladin = new Hero() {
            private WeaponItem paladinWeapon = new WeaponItemImpl(PALADIN_WEAPON_BATTLE_POWER, PALADIN_WEAPON_PRICE_FOR_UPGRADE);
            private OffhandItem paladinOffhand = new OffhandItemImpl(PALADIN_OFFHAND_BATTLE_POWER, PALADIN_OFFHAND_PRICE_FOR_UPGRADE);
            private ArmorItem paladinArmor = new ArmorItemImpl(PALADIN_ARMOR_BATTLE_POWER, PALADIN_ARMOR_PRICE_FOR_UPGRADE);

            @Override
            public String getHeroClass() {
                return "paladin";
            }

            @Override
            public Integer getTotalBattlePower() {
                return ((this.getWeapon().getBattlePower()
                        + this.getOffhand().getBattlePower()
                        + this.getArmor().getBattlePower()) * 4) / 9;
            }

            @Override
            public Double getGold() {
                return 0D;
            }

            @Override
            public void earnGold(Double gold) {

            }

            @Override
            public void payGold(Double gold) {

            }

            @Override
            public WeaponItem getWeapon() {
                return paladinWeapon;
            }

            @Override
            public OffhandItem getOffhand() {
                return paladinOffhand;
            }

            @Override
            public ArmorItem getArmor() {
                return paladinArmor;
            }

            @Override
            public Double getTotalPriceForUpgrade() {
                return this.getWeapon().getPriceForUpgrade()
                        + this.getOffhand().getPriceForUpgrade()
                        + this.getArmor().getPriceForUpgrade();
            }

            @Override
            public String getName() {
                return "Theros";
            }
        };

        this.mage = new Hero() {
            private WeaponItem mageWeapon = new WeaponItemImpl(MAGE_WEAPON_BATTLE_POWER, MAGE_WEAPON_PRICE_FOR_UPGRADE);
            private OffhandItem mageOffhand = (new OffhandItemImpl(MAGE_OFFHAND_BATTLE_POWER, MAGE_OFFHAND_PRICE_FOR_UPGRADE));
            private ArmorItem mageArmor = (new ArmorItemImpl(MAGE_ARMOR_BATTLE_POWER, MAGE_ARMOR_PRICE_FOR_UPGRADE));

            @Override
            public String getHeroClass() {
                return "mage";
            }

            @Override
            public Integer getTotalBattlePower() {
                return ((this.getWeapon().getBattlePower()
                        + this.getArmor().getBattlePower()
                        - this.getOffhand().getBattlePower()) * 3) / 4;
            }

            @Override
            public Double getGold() {
                return 0D;
            }

            @Override
            public void earnGold(Double gold) {

            }

            @Override
            public void payGold(Double gold) {

            }

            @Override
            public WeaponItem getWeapon() {
                return mageWeapon;
            }

            @Override
            public OffhandItem getOffhand() {
                return mageOffhand;
            }

            @Override
            public ArmorItem getArmor() {
                return mageArmor;
            }

            @Override
            public Double getTotalPriceForUpgrade() {
                return this.getWeapon().getPriceForUpgrade()
                        + this.getOffhand().getPriceForUpgrade()
                        + this.getArmor().getPriceForUpgrade();
            }

            @Override
            public String getName() {
                return "Morghot";
            }
        };
    }

    @Test
    public void paladinTotalBattlePowerShouldBeIncreased() {
        Integer oldWeaponBP = this.paladin.getWeapon().getBattlePower();
        Integer oldOffhandBP = this.paladin.getOffhand().getBattlePower();
        Integer oldArmorBP = this.paladin.getArmor().getBattlePower();

        this.heroTrainer.trainHero(this.paladin);

        Integer newWeaponBP = (oldWeaponBP * (PALADIN_WEAPON_BATTLE_POWER_INCREASER + 100)) / 100;
        Integer newOffhandBP = (oldOffhandBP * (PALADIN_OFFHAND_BATTLE_POWER_INCREASER + 100)) / 100;
        Integer newArmorBP = (oldArmorBP * (PALADIN_ARMOR_BATTLE_POWER_INCREASER + 100)) / 100;

        Integer newTotalBattlePower = ((newWeaponBP + newOffhandBP + newArmorBP) * 4) / 9;

        Assert.assertEquals(newTotalBattlePower, this.paladin.getTotalBattlePower());
    }

    @Test
    public void paladinTotalPriceForUpgradeShouldBeIncreased() {
        Double oldWeaponPrice = this.paladin.getWeapon().getPriceForUpgrade();
        Double oldOffhandPrice = this.paladin.getOffhand().getPriceForUpgrade();
        Double oldArmorPrice = this.paladin.getArmor().getPriceForUpgrade();

        this.heroTrainer.trainHero(this.paladin);

        Double newWeaponPrice = (oldWeaponPrice * (PALADIN_WEAPON_PRICE_FOR_UPGRADE_INCREASER + 100)) / 100;
        Double newOffhandPrice = (oldOffhandPrice * (PALADIN_OFFHAND_PRICE_FOR_UPGRADE_INCREASER + 100)) / 100;
        Double newArmorPrice = (oldArmorPrice * (PALADIN_ARMOR_PRICE_FOR_UPGRADE_INCREASER + 100)) / 100;

        Double newTotalPriceForUpgrade = newWeaponPrice + newOffhandPrice + newArmorPrice;

        Assert.assertEquals(newTotalPriceForUpgrade, this.paladin.getTotalPriceForUpgrade());
    }

    @Test
    public void mageTotalBattlePowerShouldBeIncreased() {
        Integer oldTotalBattlePower = this.mage.getTotalBattlePower();
        this.heroTrainer.trainHero(this.mage);
        Assert.assertTrue(oldTotalBattlePower < this.mage.getTotalBattlePower());
    }

    @Test
    public void mageTotalPriceForUpgradeShouldBeIncreased() {
        Double oldTotalPriceForUpgrade = this.paladin.getTotalPriceForUpgrade();
        this.heroTrainer.trainHero(this.paladin);
        Assert.assertTrue(oldTotalPriceForUpgrade < this.paladin.getTotalPriceForUpgrade());
    }

    @Test
    public void paladinWeaponBattlePowerShouldBeIncreased() {
        Integer oldBattlePower = this.paladin.getWeapon().getBattlePower();
        this.heroTrainer.trainHero(this.paladin);
        Integer newvalue = (oldBattlePower * (PALADIN_WEAPON_BATTLE_POWER_INCREASER + 100)) / 100;
        Assert.assertEquals(newvalue, this.paladin.getWeapon().getBattlePower());
    }

    @Test
    public void paladinWeaponPriceForUpgradeShouldBeIncreased() {
        Double oldPriceForUpgrade = this.paladin.getWeapon().getPriceForUpgrade();
        this.heroTrainer.trainHero(this.paladin);
        Assert.assertEquals((oldPriceForUpgrade * (PALADIN_WEAPON_PRICE_FOR_UPGRADE_INCREASER + 100)) / 100, this.paladin.getWeapon().getPriceForUpgrade(), 0.0);
    }

    @Test
    public void paladinOffhandBattlePowerShouldBeIncreased() {
        Integer oldBattlePower = this.paladin.getOffhand().getBattlePower();
        this.heroTrainer.trainHero(this.paladin);
        Assert.assertEquals((oldBattlePower * (PALADIN_OFFHAND_BATTLE_POWER_INCREASER + 100)) / 100, (int) this.paladin.getOffhand().getBattlePower());
    }

    @Test
    public void paladinOffhandPriceForUpgradeShouldBeIncreased() {
        Double oldPriceForUpgrade = this.paladin.getOffhand().getPriceForUpgrade();
        this.heroTrainer.trainHero(this.paladin);
        Assert.assertEquals((oldPriceForUpgrade * (PALADIN_OFFHAND_PRICE_FOR_UPGRADE_INCREASER + 100)) / 100, this.paladin.getOffhand().getPriceForUpgrade(), 0.0);
    }

    @Test
    public void paladinArmorBattlePowerShouldBeIncreased() {
        Integer oldBattlePower = this.paladin.getArmor().getBattlePower();
        this.heroTrainer.trainHero(this.paladin);
        Assert.assertEquals((oldBattlePower * (PALADIN_ARMOR_BATTLE_POWER_INCREASER + 100)) / 100, (int) this.paladin.getArmor().getBattlePower());
    }

    @Test
    public void paladinArmorPriceForUpgradeShouldBeIncreased() {
        Double oldPriceForUpgrade = this.paladin.getArmor().getPriceForUpgrade();
        this.heroTrainer.trainHero(this.paladin);
        Assert.assertEquals((oldPriceForUpgrade * (PALADIN_ARMOR_PRICE_FOR_UPGRADE_INCREASER + 100)) / 100, this.paladin.getArmor().getPriceForUpgrade(), 0.0);
    }

    @Test
    public void mageWeaponBattlePowerShouldBeIncreased() {
        Integer oldBattlePower = this.mage.getWeapon().getBattlePower();
        this.heroTrainer.trainHero(this.mage);
        Assert.assertEquals((oldBattlePower * (MAGE_WEAPON_BATTLE_POWER_INCREASER + 100)) / 100, (int) this.mage.getWeapon().getBattlePower());
    }

    @Test
    public void mageWeaponPriceForUpgradeShouldBeIncreased() {
        Double oldPriceForUpgrade = this.mage.getWeapon().getPriceForUpgrade();
        this.heroTrainer.trainHero(this.mage);
        Assert.assertEquals((oldPriceForUpgrade * (MAGE_WEAPON_PRICE_FOR_UPGRADE_INCREASER + 100)) / 100, this.mage.getWeapon().getPriceForUpgrade(), 0.0);
    }

    @Test
    public void mageOffhandBattlePowerShouldBeIncreased() {
        Integer oldBattlePower = this.mage.getOffhand().getBattlePower();
        this.heroTrainer.trainHero(this.mage);
        Assert.assertEquals((oldBattlePower * (MAGE_OFFHAND_BATTLE_POWER_INCREASER + 100)) / 100, (int) this.mage.getOffhand().getBattlePower());
    }

    @Test
    public void mageOffhandPriceForUpgradeShouldBeIncreased() {
        Double oldPriceForUpgrade = this.mage.getOffhand().getPriceForUpgrade();
        this.heroTrainer.trainHero(this.mage);
        Assert.assertEquals((oldPriceForUpgrade * (MAGE_OFFHAND_PRICE_FOR_UPGRADE_INCREASER + 100)) / 100, this.mage.getOffhand().getPriceForUpgrade(), 0.0);
    }

    @Test
    public void mageArmorBattlePowerShouldBeIncreased() {
        Integer oldBattlePower = this.mage.getArmor().getBattlePower();
        this.heroTrainer.trainHero(this.mage);
        Assert.assertEquals((oldBattlePower * (MAGE_ARMOR_BATTLE_POWER_INCREASER + 100)) / 100, (int) this.mage.getArmor().getBattlePower());
    }

    @Test
    public void mageArmorPriceForUpgradeShouldBeIncreased() {
        Double oldPriceForUpgrade = this.mage.getArmor().getPriceForUpgrade();
        this.heroTrainer.trainHero(this.mage);
        Assert.assertEquals((oldPriceForUpgrade * (MAGE_ARMOR_PRICE_FOR_UPGRADE_INCREASER + 100)) / 100, this.mage.getArmor().getPriceForUpgrade(), 0.0);
    }
}
