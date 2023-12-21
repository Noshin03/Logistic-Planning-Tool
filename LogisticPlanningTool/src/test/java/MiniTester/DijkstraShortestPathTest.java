package MiniTester;

import Service.DijkstraShortestPath;

import java.util.List;

public class DijkstraShortestPathTest {
    public static void main(String[] args) {
        int vertices = 5;
        DijkstraShortestPath graph = new DijkstraShortestPath(vertices);

        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 1, 2);
        graph.addEdge(2, 3, 5);
        graph.addEdge(3, 4, 3);

        int startNode = 0;
        int endNode = 4;
        List<Integer> shortestPath = graph.dijkstraShortestPath(startNode, endNode);

        System.out.println("Shortest path from node " + startNode + " to " + endNode + ": " + shortestPath);
    }
}
