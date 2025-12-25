interface Burger {
    public abstract void prepare();
}

class BasicBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Basic Burger");
    }
}

class StandardBurder implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Standard Burger");
    }
}

class PremiumBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Premium Burger");
    }
}

class BurgerFactory {
    public Burger getBurger(String type) {
        if (type.equals("basic")) {
            return new BasicBurger();
        } else if (type.equals("standard")) {
            return new StandardBurder();
        } else if (type.equals("premium")) {
            return new PremiumBurger();
        } else {
            return null;
        }
    }
}

public class SimpleFactory {
    public static void main(String[] args) {
        BurgerFactory burgerFactory = new BurgerFactory();
        Burger myBurger = burgerFactory.getBurger("standard");
        myBurger.prepare();
    }
}
