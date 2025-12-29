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

    public void move (int x, int y) {
        if (x < 0 || x >= 4 || y < 0 || y >= 4) {
            System.out.println("Invalid move. Try again.");
            return;
        }else {
            this.x = x;
            this.y = y;
        }
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

    private void moveToCoordinates(String move) {
        int oldX = player.x;
        int oldY = player.y;

        int newX = oldX;
        int newY = oldY;

        switch (move.toLowerCase()) {
            case "left":
                newY--;
                break;
            case "right":
                newY++;
                break;
            case "up":
                newX--;
                break;
            case "down":
                newX++;
                break;
            default:
                System.out.println("Invalid move");
                return;
        }

        if (!isValid(newX, newY)) {
            System.out.println("You went out of the boundaries !!!");
            return;
        }

        /* REMOVE player from old cell */
        board[oldX][oldY].contents.remove("Player");
        if (board[oldX][oldY].contents.isEmpty()) {
            board[oldX][oldY].contents.add("Empty");
        }

        /* UPDATE player position */
        player.x = newX;
        player.y = newY;

        /* ADD player to new cell */
        board[newX][newY].add("Player");
    }

    private boolean isSafe () {
        Set<String> effects = board[player.x][player.y].contents;
        if (effects.contains("Wumpus")) {
            System.out.println("There is a Wumpus here !!!");
            return false;
        }else if (effects.contains("Pit")) {
            System.out.println("There is a Pit here !!!");
            return false;
        }
        return true;
    }

    private void checkSurrounding () {
        Set<String> effects = board[player.x][player.y].contents;
        if (effects.contains("Stench") && !effects.contains("Breeze")) {
            System.out.println("There is a Stench here (Wumpus nearby) !!!");
            return;
        } else if (effects.contains("Breeze") && !effects.contains("Stench")) {
            System.out.println("There is a Breeze here (Pit nearby) !!!");
            return;
        } else if (effects.contains("Breeze") && effects.contains("Stench")) {
            System.out.println("There is a Breeze and a Stench here (Wumpus and Pit nearby) !!!");
            return;
        }
    }

    private boolean checkGoldFound () {
        Set<String> effects = board[player.x][player.y].contents;
        if (effects.contains("Gold") && !gold.found) {
            gold.found = true;
            effects.remove("Gold");
            if (effects.isEmpty()) {
                effects.add("Empty");
            }
            return true;
        }
        return false;
    }

    private boolean checkWin () {
        if (this.player.x == 0 && this.player.y == 0 && this.gold.found == true) {
            return true;
        }
        return false;
    }

    private void printRules () {
        System.out.println();
        System.out.println("\n========== WUMPUS WORLD RULES ==========\n");
        System.out.println("1. There is a Wumpus in one of the cells");
        System.out.println("2. There is a Pit in three of the cells");
        System.out.println("3. There is a Gold in one of the cells");
        System.out.println("4. The cells surrounding the Wumpus have a Stench");
        System.out.println("5. The cells surrounding the Pit have a Breeze");
        System.out.println();
        System.out.println("\n========== HOW TO PLAY ==========\n");
        System.out.println("1. Enter the direction (up/down/left/right) in which you want to move");
        System.out.println("2. If you find a Wumpus or a Pit, you will be dead");
        System.out.println("3. If you find the gold, you will be able to move to the starting point to finish the game");
        System.out.println("4. If you want to quit the game, type 'quit'");
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        printRules();
        while(true) {
            System.out.println("Enter your move: ");
            String move = sc.nextLine();
            if (move.equals("quit")) {
                System.out.println("Game Over!");
                break;
            }
            moveToCoordinates(move);
            printBoard();
            if (!isSafe()) {
                System.out.println("You are dead !!! Game Over!");
                break;
            }
            if (checkGoldFound()) {
                System.out.println("You found the gold !!! Get to the starting point to finish!");
            }
            checkSurrounding();
            if (checkWin()) {
                System.out.println("You won the game !!!");
                break;
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
