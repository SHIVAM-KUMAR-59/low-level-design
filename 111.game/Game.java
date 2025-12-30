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

class PathNode {
    int x, y;
    PathNode parent;
    String direction;
    
    public PathNode(int x, int y, PathNode parent, String direction) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.direction = direction;
    }
}

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
            if (board[x][y].contains("Player") || board[x][y].contains("Wumpus") || 
                board[x][y].contains("Gold") || board[x][y].contains("Pit")) {
                continue;
            }
            pits.add(new Pit(x, y));
            board[x][y].add("Pit");
        }
    }
    
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
    
    private void printBoard() {
        System.out.println("\n========== WUMPUS WORLD ==========\n");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("[ %-30s ] ", board[i][j].display());
            }
            System.out.println("\n");
        }
    }
    
    private void printRules() {
        System.out.println("\n========== WUMPUS WORLD RULES ==========\n");
        System.out.println("1. There is a Wumpus in one of the cells");
        System.out.println("2. There are Pits in three of the cells");
        System.out.println("3. There is Gold in one of the cells");
        System.out.println("4. The cells surrounding the Wumpus have a Stench");
        System.out.println("5. The cells surrounding the Pit have a Breeze");
        System.out.println("\n========== AUTOMATED BFS PATHFINDING ==========\n");
        System.out.println("The AI will find the optimal safe path to the gold and back!\n");
    }
    
    private String getDirectionName(int dx, int dy) {
        if (dx == 1 && dy == 0) return "DOWN";
        if (dx == -1 && dy == 0) return "UP";
        if (dx == 0 && dy == 1) return "RIGHT";
        if (dx == 0 && dy == -1) return "LEFT";
        return "UNKNOWN";
    }
    
    private List<PathNode> findPathBFS(int startX, int startY, int targetX, int targetY) {
        System.out.println("\nStarting BFS from (" + startX + ", " + startY + ") to (" + targetX + ", " + targetY + ")");
        
        boolean[][] visited = new boolean[SIZE][SIZE];
        Queue<PathNode> queue = new LinkedList<>();
        
        queue.add(new PathNode(startX, startY, null, "START"));
        visited[startX][startY] = true;
        
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
        int exploredCells = 0;
        
        while (!queue.isEmpty()) {
            PathNode current = queue.poll();
            exploredCells++;
            
            System.out.println("   Exploring cell (" + current.x + ", " + current.y + ")");
            
            if (current.x == targetX && current.y == targetY) {
                System.out.println("Target found at (" + targetX + ", " + targetY + ")!");
                System.out.println("Total cells explored: " + exploredCells);
                
                List<PathNode> path = new ArrayList<>();
                PathNode node = current;
                while (node != null) {
                    path.add(0, node);
                    node = node.parent;
                }
                return path;
            }
            
            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];
                
                if (!isValid(newX, newY)) {
                    System.out.println("\t(" + newX + ", " + newY + ") - Out of bounds");
                    continue;
                }
                
                if (visited[newX][newY]) {
                    System.out.println("\t(" + newX + ", " + newY + ") - Already visited");
                    continue;
                }
                
                if (board[newX][newY].contains("Wumpus")) {
                    System.out.println("\t(" + newX + ", " + newY + ") - Wumpus detected! Avoiding...");
                    continue;
                }
                
                if (board[newX][newY].contains("Pit")) {
                    System.out.println("\t(" + newX + ", " + newY + ") - Pit detected! Avoiding...");
                    continue;
                }
                
                if (board[newX][newY].contains("Stench")) {
                    System.out.println("\t(" + newX + ", " + newY + ") - Stench detected (Wumpus nearby), but safe to pass");
                }
                
                if (board[newX][newY].contains("Breeze")) {
                    System.out.println("\t(" + newX + ", " + newY + ") - Breeze detected (Pit nearby), but safe to pass");
                }
                
                String dirName = getDirectionName(dir[0], dir[1]);
                System.out.println("\t(" + newX + ", " + newY + ") - Safe cell, adding to queue [" + dirName + "]");
                
                visited[newX][newY] = true;
                queue.add(new PathNode(newX, newY, current, dirName));
            }
        }
        
        System.out.println("No safe path found to target!");
        return null;
    }
    
    private void executePath(List<PathNode> path, String phase) {
        if (path == null || path.size() <= 1) {
            System.out.println("No path to execute!");
            return;
        }
        
        System.out.println("\nExecuting " + phase + " path:");
        System.out.println("   Total steps: " + (path.size() - 1));
        
        for (int i = 1; i < path.size(); i++) {
            PathNode step = path.get(i);
            System.out.println("\nStep " + i + ": Moving " + step.direction + " to (" + step.x + ", " + step.y + ")");
            
            board[player.x][player.y].contents.remove("Player");
            if (board[player.x][player.y].contents.isEmpty()) {
                board[player.x][player.y].contents.add("Empty");
            }
            
            player.x = step.x;
            player.y = step.y;
            board[player.x][player.y].add("Player");
            
            System.out.println("   Current cell contents: " + board[player.x][player.y].display());
            
            if (board[player.x][player.y].contains("Gold") && !gold.found) {
                System.out.println("\nGOLD FOUND!");
                gold.found = true;
                board[player.x][player.y].contents.remove("Gold");
            }
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void play() {
        printRules();
        
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("PHASE 1: Finding path from START (0, 0) to GOLD (" + gold.x + ", " + gold.y + ")");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        List<PathNode> pathToGold = findPathBFS(0, 0, gold.x, gold.y);
        
        if (pathToGold == null) {
            System.out.println("\n💀 GAME OVER: No safe path to gold exists!");
            return;
        }
        
        executePath(pathToGold, "TO GOLD");
        
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("PHASE 2: Finding path from GOLD (" + gold.x + ", " + gold.y + ") back to START (0, 0)");
        System.out.println("═══════════════════════════════════════════════════════════");
        
        List<PathNode> pathToStart = findPathBFS(gold.x, gold.y, 0, 0);
        
        if (pathToStart == null) {
            System.out.println("\n💀 GAME OVER: No safe path back to start!");
            return;
        }
        
        executePath(pathToStart, "BACK TO START");
        
        System.out.println("\n");
        // printBoard();
        
        System.out.println("\nCONGRATULATIONS!");
        System.out.println("You successfully found the gold and returned safely!");
        System.out.println("Total steps taken: " + (pathToGold.size() + pathToStart.size() - 2));
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}