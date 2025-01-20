import java.util.*;
import java.io.*;

public class GraphComposition {
    private static final StdIn in = new StdIn();
    private static final PrintWriter out = new PrintWriter(System.out);

    // Union-Find (DSU) structure
    static class DSU {
        int[] parent, rank;

        public DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i <= n; ++i) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        public boolean unite(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return false;
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }

    public static void solveOne() {
        int n = in.nextInt();
        int m1 = in.nextInt();
        int m2 = in.nextInt();

        // DSUs for F and G
        DSU dsuF = new DSU(n);
        DSU dsuG = new DSU(n);

        // Read edges for G and unite in dsuG
        for (int i = 0; i < m2; ++i) {
            int u = in.nextInt();
            int v = in.nextInt();
            dsuG.unite(u, v);
        }

        // List of edges for F that need to be processed
        List<int[]> edgesF = new ArrayList<>();
        for (int i = 0; i < m1; ++i) {
            int u = in.nextInt();
            int v = in.nextInt();
            edgesF.add(new int[]{u, v});
        }

        // Process edges in F to remove bad edges
        List<int[]> toRemove = new ArrayList<>();
        for (int[] edge : edgesF) {
            int u = edge[0];
            int v = edge[1];
            if (dsuG.find(u) != dsuG.find(v)) {
                toRemove.add(edge); // Bad edge in F
            } else {
                dsuF.unite(u, v); // Good edge, unite in dsuF
            }
        }

        // Count unique components in dsuF and dsuG
        Set<Integer> componentsF = new HashSet<>();
        Set<Integer> componentsG = new HashSet<>();
        for (int i = 1; i <= n; ++i) {
            componentsF.add(dsuF.find(i));
            componentsG.add(dsuG.find(i));
        }

        int operations = toRemove.size() + (componentsF.size() - componentsG.size());
        out.println(operations);
    }

    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        while (t-- > 0) {
            solveOne();
        }
        out.flush();
    }
}

class StdIn {
    BufferedReader br;
    StringTokenizer st;

    public StdIn() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
