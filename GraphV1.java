package algorithms;


import java.util.ArrayList;

public class GraphV1 {
    public static void main(String[] args) {
        char[] vNames = {'a', 'b', 'c', 'd', 'e'};
        Graph graph = new Graph();
        var cycles = new ArrayList<Vertex>();

        for (int i = 0; i < vNames.length; i++) {
            graph.addVertex(vNames[i]);
        }


        graph.addEdge('a', 'b', 1);
        graph.addEdge('a', 'd', 3);
        graph.addEdge('d', 'c', 2);
        graph.addEdge('c', 'a', 6);
        graph.addEdge('c', 'b', 5);
        graph.addEdge('d', 'e', 1);
        graph.addEdge('e', 'c', 11);
        graph.addEdge('e', 'd', 10);
        graph.addEdge('e', 'a', 7);




//        char[] vNames = {'a', 'b', 'c', 'd', 'e'};
//        Graph graph = new Graph();
//        var cycles = new ArrayList<Vertex>();
//
//        for (int i = 0; i < vNames.length; i++) {
//            graph.addVertex(vNames[i]);
//        }
//
//        graph.addEdge('a', 'b', 1);
//        graph.addEdge('b', 'd', 3);
//        graph.addEdge('c', 'b', 15);
//        graph.addEdge('c', 'd', 5);
//        graph.addEdge('c', 'e', 11);
//        graph.addEdge('d', 'a', 7);
//        graph.addEdge('d', 'e', 1);

        graph.printAdjMatrix();
        graph.printWeightMatrix();
        for (int i = 0; i < graph.knotsCount ; i++) {
            SearchCycles(graph, graph.knots[i], cycles, 4);
            graph.setMarkDefalut();

        }
    }


    static void SearchCycles(Graph graph, Vertex from, ArrayList<Vertex> cycles, int currentCount){
        int nextIndex = graph.getFirst(from.name);
        cycles.add(from);
        while (nextIndex != -1){
            from.mark = 1;

            Vertex son = graph.getVertex(nextIndex);

            if (son.mark == 1){
                if (son.name == cycles.get(0).name && cycles.size() == currentCount - 1){
                    for (var v: cycles) {
                        System.out.print(v.name + "->");
                    }

                    System.out.print(son.name);
                    System.out.println();

                }
            }
            if (son.mark == 0){
                SearchCycles(graph, son, cycles, currentCount);
            }

            nextIndex = graph.getNext(from, nextIndex);

        }
        from.mark = 2;
        cycles.remove(from);

    }

}