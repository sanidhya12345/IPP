import java.util.*;
public class AtlassianPart3 {

    private static final Scanner sc = new Scanner(System.in);

    // BFS function to compute shortest path from node 1 to node N
    private static void bfs(long n, long src, long[] visited, long[] level, long[] valueArray, ArrayList<ArrayList<Long>> graph) {
        Queue<Long> queue = new LinkedList<>();
        if (valueArray[(int) src] == 1) {
            queue.add(src);
            visited[(int) src] = 1;
            level[(int) src] = 0;
        }
        while (!queue.isEmpty()) {
            long vert = queue.poll();
            for (long u : graph.get((int) vert)) {
                if (visited[(int) u] == 0) {
                    if (valueArray[(int) u] == 1) {
                        visited[(int) u] = 1;
                        queue.add(u);
                        level[(int) u] = level[(int) vert] + 1;
                    }
                }
            }
        }
        if (level[(int) n] == Long.MAX_VALUE) {
            System.out.println("Unable to reach the final node");
            return;
        }
        System.out.println("Shortest Path Distance: " + level[(int) n]);
    }

    // Optimized multi-source BFS using universal node 0
    private static void virusBfs(long n, long k, long[] valueArray, ArrayList<ArrayList<Long>> graph, List<Long> virusNodes) {
        Queue<Long> queue = new LinkedList<>();
        long[] visited = new long[(int) n + 1];
        long[] level = new long[(int) n + 1];

        // Add universal node (0) and connect all virus nodes to it
        for (long virus : virusNodes) {
            queue.add(virus);
            visited[(int) virus] = 1;
            level[(int) virus] = 0;
        }

        while (!queue.isEmpty()) {
            long v = queue.poll();
            for (long u : graph.get((int) v)) {
                if (visited[(int) u] == 0 && level[(int) v] + 1 <= k) {
                    valueArray[(int) u] = 0; // Infected
                    visited[(int) u] = 1;
                    level[(int) u] = level[(int) v] + 1;
                    queue.add(u);
                }
            }
        }
    }

    private static ArrayList<ArrayList<Long>> buildGraph(long n, long m) {
        ArrayList<ArrayList<Long>> graph = new ArrayList<>();
        for (long i = 0; i <= n; i++) { // Include index 0 for universal node
            graph.add(new ArrayList<>());
        }
        for (long i = 1; i <= m; i++) {
            long u = sc.nextLong();
            long v = sc.nextLong();
            graph.get((int) u).add(v);
            graph.get((int) v).add(u);
        }
        return graph;
    }

    public static void main(String[] args) {
        long n = sc.nextLong(); // number of vertices
        long m = sc.nextLong(); // number of edges
        long k = sc.nextLong(); // virus spread limit
        long g = sc.nextLong(); // number of virus nodes

        long[] valueArray = new long[(int) n + 1];
        ArrayList<ArrayList<Long>> graph = buildGraph(n, m);
        List<Long> virusNodes = new ArrayList<>();

        // Input node values (1 = normal, 0 = infected)
        for (int i = 1; i <= n; i++) {
            valueArray[i] = sc.nextLong();
            if (valueArray[i] == 0) {
                virusNodes.add((long) i);
            }
        }

        // Run optimized multi-source BFS from universal node
        virusBfs(n, k, valueArray, graph, virusNodes);

        long[] visited1 = new long[(int) n + 1];
        long[] level1 = new long[(int) n + 1];
        Arrays.fill(level1, Long.MAX_VALUE);

        // Run BFS from node 1
        bfs(n, 1, visited1, level1, valueArray, graph);
    }
}
