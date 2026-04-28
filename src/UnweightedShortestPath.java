/*
2 (code) Unweighted shortest Path [15 points]
Write a program that takes the adjacency matrix representation of a graph with n nodes, a start node and
a destination node as input and returns the shortest path from start to target using BFS.
 */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Collections;

public class UnweightedShortestPath {
    public static List<Integer> shortestPath(int[][] graph, int start, int target){
        int n = graph.length;//Number of Nodes

        boolean[] visited = new boolean[n];//Keeps track of nodes already seen 7 prevents looping forever
        int[] parent = new int[n];
        Arrays.fill(parent, -1);//Keeps track of where each node came from

        Queue<Integer> queue = new LinkedList<>();//Using queue for FIFO in BFS

        visited[start] = true;
        queue.add(start);//Marks starting node as visited and put into queue

        while (!queue.isEmpty()){//Loops until no nodes remain for exploration
            int current = queue.remove();//Takes next node out of the queue
            if (current == target){//Stop searching if target is found
                break;
            }

            for (int neighbor = 0; neighbor < n; neighbor++){//Checks every possible neighbor
                if(graph[current][neighbor] == 1 && !visited[neighbor]){//If current is connected to neighbor AND neighbor has not already been visitied
                    visited[neighbor] = true;//Mark Neighbor as visited
                    parent[neighbor] = current;//Remember that we reached neighbor from current
                    queue.add(neighbor);//Add neighbor to the queue to explore later
                }
            }
        }
        if(!visited[target]){
            return new ArrayList<>();
        }//If the target was never visited, then no path exists

        List<Integer> path = new ArrayList<>();
        int current = target;//Start at the target and work backward

        while (current != -1){
            path.add(current);
            current = parent[current];
        }//This follows the parent links backward

        Collections.reverse(path);//Reverses the path
        return path;
    }//end shortestPath

    public static void main(String[] args){
        int[][] graph = {
                {0,1,1,0,0},//Node 0 connects to 1 and 2
                {1,0,0,1,0},//Node 1 connects to 0 and 3
                {1,0,0,1,1},//Node 2 connects to 0 and 4
                {0,1,1,0,1},//Node 3 connects to 1
                {0,0,1,1,0},//Node 4 connects to 2
        };
        int start = 0;
        int target = 4;

        List<Integer> path = shortestPath(graph, start, target);
        System.out.println("Shortest path: " + path);
    }
}//end UnweightedShortestPath

//I keep a visited array to avoid revisiting nodes and a parent array to
// remember how each node was reached. After BFS reaches the target,
// I reconstruct the path by following the parent array backward from
// the target to the start, then reverse the result.
