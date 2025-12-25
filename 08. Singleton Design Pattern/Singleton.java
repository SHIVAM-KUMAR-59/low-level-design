public class Singleton {
    private static Singleton instance = null;

    private Singleton () {
        System.out.println("Singleton constructor called");
    }

    public static Singleton getInstance() {
        if (instance == null) {
            return new Singleton();
        }else {
            return instance;
        }
    }

    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1);
        System.out.println(instance2);
    }
}
