interface Burger {
    public abstract void prepare();
}

interface GarlicBread {
    public abstract void prepare();
}

interface Factory {
    public abstract Burger createBurger(String type);
    public abstract GarlicBread createGarlicBread(String type);
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

class BasicGarlicBread implements GarlicBread {
    public void prepare() {
        System.out.println("Preparing Basic Garlic Bread");
    }
}

class StandardGarlicBread implements GarlicBread {
    public void prepare() {
        System.out.println("Preparing Standard Garlic Bread");
    }
}

class PremiumGarlicBread implements GarlicBread {
    public void prepare() {
        System.out.println("Preparing Premium Garlic Bread");
    }
}

class BasicWheatGarlicBread implements GarlicBread {
    public void prepare() {
        System.out.println("Preparing Basic Wheat Garlic Bread");
    }
}

class StandardWheatGarlicBread implements GarlicBread {
    public void prepare() {
        System.out.println("Preparing Standard Wheat Garlic Bread");
    }
}

class PremiumWheatGarlicBread implements GarlicBread {
    public void prepare() {
        System.out.println("Preparing Premium Wheat Garlic Bread");
    }
}

class NormalFactory implements Factory {
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

    public GarlicBread createGarlicBread(String type) {
        if (type.equals("basic")) {
            return new BasicGarlicBread();
        } else if (type.equals("standard")) {
            return new StandardGarlicBread();
        } else if (type.equals("premium")) {
            return new PremiumGarlicBread();
        } else {
            return null;
        }
    }
}

class WheatFactory implements Factory {
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

    public GarlicBread createGarlicBread(String type) {
        if (type.equals("basic")) {
            return new BasicWheatGarlicBread();
        } else if (type.equals("standard")) {
            return new StandardWheatGarlicBread();
        } else if (type.equals("premium")) {
            return new PremiumWheatGarlicBread();
        } else {
            return null;
        }
    }
}

public class AbstractFactoryMethod {
    public static void main(String[] args) {
        Factory normalBurgerFactory = new NormalFactory();
        Factory wheatBurgerFactory = new WheatFactory();
        Burger myBurger = normalBurgerFactory.createBurger("premium");
        GarlicBread myGarlicBread = normalBurgerFactory.createGarlicBread("premium");
        myBurger.prepare();
        myGarlicBread.prepare();
        myBurger = wheatBurgerFactory.createBurger("premium");
        myGarlicBread = wheatBurgerFactory.createGarlicBread("premium");
        myBurger.prepare();
        myGarlicBread.prepare();
    }
}
