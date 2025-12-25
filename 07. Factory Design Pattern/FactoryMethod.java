interface Burger {
    public abstract void prepare();
}

interface BurgerFactory {
    public abstract Burger createBurger(String type);
}

class BasicBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Basic Burger");
    }
}

class StandardBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Standard Burger");
    }
}

class PremiumBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Premium Burger");
    }
}

class BasicWheatBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Basic Wheat Burger");
    }
}

class StandardWheatBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Standard Wheat Burger");
    }
}

class PremiumWheatBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Premium Wheat Burger");
    }
}

class NormalBurgerFactory implements BurgerFactory {
    public Burger createBurger(String type) {
        if (type.equals("basic")) {
            return new BasicBurger();
        } else if (type.equals("standard")) {
            return new StandardBurger();
        } else if (type.equals("premium")) {
            return new PremiumBurger();
        } else {
            return null;
        }
    }
}

class WheatBurgerFactory implements BurgerFactory {
    public Burger createBurger(String type) {
        if (type.equals("basic")) {
            return new BasicWheatBurger();
        } else if (type.equals("standard")) {
            return new StandardWheatBurger();
        } else if (type.equals("premium")) {
            return new PremiumWheatBurger();
        } else {
            return null;
        }
    }
}

public class FactoryMethod {
    public static void main(String[] args) {
        BurgerFactory normalBurgerFactory = new NormalBurgerFactory();
        Burger myBurger = normalBurgerFactory.createBurger("premium");
        myBurger.prepare();
    }
}
