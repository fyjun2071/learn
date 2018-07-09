package learn.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;

public class DSLLambda {

    public static void main(String[] args) {

        Graph g1 = GraphBuilder.Graph( g -> {
            g.edge( e -> {
                e.from("a");
                e.to("b");
                e.weight(12.4);
            });

            g.edge( e -> {
                e.from("c");
                e.to("d");
                e.weight(13.4);
            });
        });

        Graph.printGraph(g1);

    }

}

class Graph {

    private List<Edge> edges;
    private Set<Vertex> vertices;

    public Graph() {
        edges = new ArrayList<>();
        vertices = new TreeSet<>();
    }

    public void addEdge(Edge edge) {
        getEdges().add(edge);
    }

    public void addVertice(Vertex v) {
        getVertices().add(v);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public static void printGraph(Graph g) {
        System.out.println("Vertices...");
        for (Vertex v : g.getVertices()) {
            System.out.print(v.getLabel() + " ");
        }
        System.out.println("");
        System.out.println("Edges...");
        for (Edge e : g.getEdges()) {
            System.out.println(e);
        }
    }
}

class Edge {
    private Vertex fromVertex;
    private Vertex toVertex;
    private Double weight;

    @Override
    public String toString() {
        return fromVertex.getLabel() + " to " +
                toVertex.getLabel() + " with weight " +
                getWeight();
    }

    public Vertex getFromVertex() {
        return fromVertex;
    }

    public void setFromVertex(Vertex fromVertex) {
        this.fromVertex = fromVertex;
    }

    public Vertex getToVertex() {
        return toVertex;
    }

    public void setToVertex(Vertex toVertex) {
        this.toVertex = toVertex;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}

class Vertex implements Comparable<Vertex> {
    private String label;

    public Vertex(String label) {
        this.label = label.toUpperCase();
    }

    @Override
    public int compareTo(Vertex o) {
        return (this.getLabel().compareTo(o.getLabel()));
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

class GraphBuilder {

    private Graph graph;

    public static Graph Graph(Consumer<GraphBuilder> gConsumer){
        GraphBuilder gBuilder = new GraphBuilder();
        gConsumer.accept(gBuilder);
        return gBuilder.graph;
    }

    public void edge(Consumer<EdgeBuilder> eConsumer){
        EdgeBuilder eBuilder = new EdgeBuilder();
        eConsumer.accept(eBuilder);
        Edge e = eBuilder.edge();
        graph.addEdge(e);
        graph.addVertice(e.getFromVertex());
        graph.addVertice(e.getToVertex());
    }

    public Graph getGraph() {
        return graph;
    }

    public void printGraph() {
        Graph.printGraph(graph);
    }
}

class EdgeBuilder {

    Edge edge;

    public EdgeBuilder() {
        edge = new Edge();
    }

    public EdgeBuilder from(String lbl) {
        Vertex v = new Vertex(lbl);
        edge.setFromVertex(v);
        return this;
    }

    public EdgeBuilder to(String lbl) {
        Vertex v = new Vertex(lbl);
        edge.setToVertex(v);
        return this;
    }

    public EdgeBuilder weight(Double d) {
        edge.setWeight(d);
        return this;
    }

    public Edge edge() {
        return edge;
    }
}
