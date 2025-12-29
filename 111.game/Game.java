import java.util.*;

class Entity {
    int x, y;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Player extends Entity {
    public Player(int x, int y) {
        super(x, y);
    }
}

class Wumpus extends Entity {
    public Wumpus(int x, int y) {
        super(x, y);
    }
}

class Gold extends Entity {
    boolean found = false;

    public Gold(int x, int y) {
        super(x, y);
    }
}

class Pit extends Entity {
    public Pit(int x, int y) {
        super(x, y);
    }
}

/* ---------- Cell ---------- */
class Cell {
    Set<String> contents = new LinkedHashSet<>();

    public Cell() {
        contents.add("Empty");
    }

    public void add(String item) {
        contents.remove("Empty");
        contents.add(item);
    }

    public boolean contains(String item) {
        return contents.contains(item);
    }

    public String display() {
        return String.join(", ", contents);
    }
}

/* ---------- Game ---------- */
public class Game {
    private static final int SIZE = 4;
    private final Cell[][] board = new Cell[SIZE][SIZE];
    private final Random random = new Random();

    Player player;
    Wumpus wumpus;
    Gold gold;
    List<Pit> pits = new ArrayList<>();

    public Game() {
        initBoard();
        placePlayer();
        placeWumpus();
        placeGold();
        placePits();
        addBreezeAndStench();
        printBoard();
    }

    /* ---------- Initialization ---------- */
    private void initBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    private void placePlayer() {
        player = new Player(0, 0);
        board[0][0].add("Player");
    }

    private void placeWumpus() {
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (x == 0 && y == 0);

        wumpus = new Wumpus(x, y);
        board[x][y].add("Wumpus");
    }

    private void placeGold() {
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (board[x][y].contains("Player") || board[x][y].contains("Wumpus"));

        gold = new Gold(x, y);
        board[x][y].add("Gold");
    }

    private void placePits() {
        while (pits.size() < 3) {
            int x = random.nextInt(SIZE);
            int y = random.nextInt(SIZE);

            if (board[x][y].contains("Player")
                    || board[x][y].contains("Wumpus")
                    || board[x][y].contains("Gold")
                    || board[x][y].contains("Pit")) {
                continue;
            }

            pits.add(new Pit(x, y));
            board[x][y].add("Pit");
        }
    }

    /* ---------- Environment Effects ---------- */
    private void addBreezeAndStench() {
        addAround(wumpus.x, wumpus.y, "Stench");

        for (Pit pit : pits) {
            addAround(pit.x, pit.y, "Breeze");
        }
    }

    private void addAround(int x, int y, String effect) {
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        for (int[] d : dirs) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (isValid(nx, ny)) {
                board[nx][ny].add(effect);
            }
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    /* ---------- Board Printing ---------- */
    private void printBoard() {
        System.out.println("\n========== WUMPUS WORLD ==========\n");

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("[ %-30s ] ", board[i][j].display());
            }
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
