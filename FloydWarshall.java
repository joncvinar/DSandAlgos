import static java.lang.System.out;
public class FloydWarshall
{
    final static int INF = 999999999;
    public static void main(String[] args)
    {
        int[][] adjacencyMatrix;
        adjacencyMatrix = new int[][]{
                new int[]{0, 3, 8, INF, -4},
                new int[]{INF, 0, INF, 1, 7},
                new int[]{INF, 4, 0, INF, INF},
                new int[]{2, INF, -5, 0, INF},
                new int[]{INF, INF, INF, 6, 0}};
        int[][] shortestDistances = Floyd_Warshall(adjacencyMatrix);

        out.println("The following is the adjacency Matrix of Distances:");
        displayDistances(adjacencyMatrix);

        out.println("\n\nThe following is the Pairwise Shortest Distances:");
        displayDistances(shortestDistances);
    }
    public static int[][] Floyd_Warshall(int[][] w)
    {
        int n = w.length;
        int d[][] = new int[n][n];
        for(int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                d[i][j] = w[i][j];
            }

        for(int k = 0; k < n; k++)
        {
            for(int i = 0; i < n; i++)
            {
                for(int j = 0; j < n; j++)
                {
                    if (d[i][k] + d[k][j] < d[i][j])
                    {
                        d[i][j] = d[i][k] + d[k][j];
                    }
                }
            }
        }
        return d;
    }
    public static void displayDistances(int[][] d)
    {
        int n = d.length;
        for(int i = 0; i < n; i++)
            out.print("\t" + (i + 1));
        out.println("\n |_______________________");
        for(int i = 0; i < n; i++)
        {
            out.print((i+1) + " | ");
            for(int j = 0; j < n; j++)
            {
                if(d[i][j] == INF) {
                    out.print( "\tINF");
                } else
                    out.print( "\t" + d[i][j]);
            }
            out.println();
        }
    }

}
