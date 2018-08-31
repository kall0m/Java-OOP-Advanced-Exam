package onehitdungeon.factories;

import onehitdungeon.interfaces.Hero;

import java.lang.reflect.InvocationTargetException;

import static onehitdungeon.constants.ClassesReferences.HEROES_REFERENCE;

public final class HeroFactory {
    public static Hero create(String name, String type) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (Hero) Class.forName(HEROES_REFERENCE + type.concat("Hero")).getDeclaredConstructor(String.class).newInstance(name);
    }
}
