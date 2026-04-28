/*
3 (code) Weighted shortest Path [15 points]
Write a program that takes the adjacency matrix representation of a graph with n nodes, a start node and
a destination node as input and returns the shortest path from start to target using Dijkstra.
 */
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class WeightedShortestPath {
    public static List<Integer> shortestPath(int[][] graph, int start, int target){
        int n = graph.length;//Number of Nodes

        boolean[] visited = new boolean[n];//Keeps track of nodes already seen & prevents looping forever
        int[] distance = new int[n];//Stores distance from start node to all other nodes
        int[] parent = new int[n];//Tracks previous node of each node.

        Arrays.fill(distance, Integer.MAX_VALUE);//Keeps track of where each node came from
        Arrays.fill(parent, -1);//Fills parent array with -1

        distance[start] = 0;

        for (int count = 0; count < n; count++){//Loops until no nodes remain for exploration
            int current = getSmallestUnvisited(distance, visited);//Chooses unvisited node with shortest distance

            if (current == target){//If node is the target, stop
                break;
            }

            visited[current] = true;

            for (int neighbor = 0; neighbor < n; neighbor++){//Checks every possible neighbor
                if(graph[current][neighbor] > 0 && !visited[neighbor]){//If current is connected to neighbor AND neighbor has not already been visitied
                    int newDistance = distance[current] + graph[current][neighbor];//Finds cost of reaching neighbor from current node

                    if(newDistance < distance[neighbor]){//Checks if new path is better than old path
                        distance[neighbor] = newDistance;//Updates the best distance to the neighbor
                        parent[neighbor] = current; //keeps track of path to neighbor
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

    //Finds unvisited node with shortest distance
    private static int getSmallestUnvisited(int[] distance, boolean[] visited){
        int smallestDistance = Integer.MAX_VALUE; //Starts smallest distance at "infinity"
        int smallestNode = -1; //Starts with no node

        for (int i = 0; i < distance.length; i++){//Loops through every node.
            if(!visited[i] && distance[i] < smallestDistance){//checks if node i has not been visited & is its distance smaller than the current smallest distance
                smallestDistance = distance[i];//updates the smallest distance found so far
                smallestNode = i; //Stores which node has the smallest distance.
            }
        }
        return smallestNode; //Returns the closest unvisited node
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