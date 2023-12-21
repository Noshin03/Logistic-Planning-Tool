package Service;

import java.util.*;

public class DijkstraShortestPath {
    private int vertices;
    private List<List<Node>> adjacencyList;

    public DijkstraShortestPath(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        adjacencyList.get(source).add(new Node(destination, weight));
        adjacencyList.get(destination).add(new Node(source, weight)); // If graph is undirected
    }

    public List<Integer> dijkstraShortestPath(int start, int end) {
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        int[] previous = new int[vertices];
        Arrays.fill(previous, -1);

        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        minHeap.add(new Node(start, 0));

        while (!minHeap.isEmpty()) {
            Node currentNode = minHeap.poll();

            if (currentNode.vertex == end) {
                break;
            }

            for (Node neighbor : adjacencyList.get(currentNode.vertex)) {
                int newDistance = distance[currentNode.vertex] + neighbor.weight;
                if (newDistance < distance[neighbor.vertex]) {
                    distance[neighbor.vertex] = newDistance;
                    previous[neighbor.vertex] = currentNode.vertex;
                    minHeap.add(new Node(neighbor.vertex, newDistance));
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        int current = end;
        while (current != -1) {
            path.add(current);
            current = previous[current];
        }
        Collections.reverse(path);
        return path;
    }
}
