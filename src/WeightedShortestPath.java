/*
3 (code) Weighted shortest Path [15 points]
Write a program that takes the adjacency matrix representation of a graph with n nodes, a start node and
a destination node as input and returns the shortest path from start to target using Dijkstra.
 */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Collections;

public class WeightedShortestPath {
    public static List<Integer> shortestPath(int[][] graph, int start, int target){
        int n = graph.length;//Number of Nodes

        boolean[] visited = new boolean[n];//Keeps track of nodes already seen 7 prevents looping forever
        int[] distance = new int[n];
        int[] parent = new int[n];

        Arrays.fill(distance, Integer.MAX_VALUE);//Keeps track of where each node came from
        Arrays.fill(parent, -1);

        distance[start] = 0;

        for (int count = 0; count < n; count++){//Loops until no nodes remain for exploration
            int current = getSmallestUnvisited(distance, visited);//Takes next node out of the queue

            if (current == target){
                break;
            }

            visited[current] = true;

            for (int neighbor = 0; neighbor < n; neighbor++){//Checks every possible neighbor
                if(graph[current][neighbor] > 0 && !visited[neighbor]){//If current is connected to neighbor AND neighbor has not already been visitied
                    int newDistance = distance[current] + graph[current][neighbor];

                    if(newDistance < distance[neighbor]){
                        distance[neighbor] = newDistance;
                        parent[neighbor] = current;
                    }
                }
            }
        }
        if(distance[target] == Integer.MAX_VALUE){
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

    private static int getSmallestUnvisited(int[] distance, boolean[] visited){
        int smallestDistance = Integer.MAX_VALUE;
        int smallestNode = -1;

        for (int i = 0; i < distance.length; i++){
            if(!visited[i] && distance[i] < smallestDistance){
                smallestDistance = distance[i];
                smallestNode = i;
            }
        }
        return smallestNode;
    }

    public static void main(String[] args){
        int[][] graph = {
                {0,4,1,0,0},//Node 0
                {1,0,2,5,0},//Node 1
                {1,0,0,1,3},//Node 2
                {0,0,0,0,2},//Node 3
                {0,0,0,0,0},//Node 4
        };
        int start = 0;
        int target = 4;

        List<Integer> path = shortestPath(graph, start, target);
        System.out.println("Shortest path: " + path);
    }
}//end UnweightedShortestPath