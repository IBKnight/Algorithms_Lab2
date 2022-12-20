package algorithms;

import java.util.ArrayList;
import java.util.Arrays;

class Vertex{
    char name = ' ';
    int mark = 0;

}

class Graph {
    final int vexCount = 20;

    Vertex[] knots = new Vertex[vexCount];
    int[][] adjMatrix = new int[vexCount][vexCount];
    int[][] weightMatrix = new int[vexCount][vexCount];
    ArrayList<Integer> loops = new ArrayList<>();

    int knotsCount = 0;
    public Graph() {
        for (int i = 0; i < vexCount; i++) {
            knots[i] = new Vertex();

        }
    }

    public int addVertex(char newName){
        if (knotsCount < vexCount){
            knots[knotsCount].name = newName;
            //knots[knotsCount].visited = false;
            knots[knotsCount].mark = 0;

            for (int i = 0; i < vexCount; i++) {
                adjMatrix[knotsCount][i] = 0;
                adjMatrix[i][knotsCount] = 0;
            }
            knotsCount++;
            return 0;
        }
        else {
            System.out.println("Не могу добавить узел");
            return -1;
        }
    }

    public int deleteVertex(char name){
        int x = indexOfName(name);
        if (x == -1) return -1;
        if (x > knotsCount){
            System.out.println("Точки нет");
            return 1;
        }

        while (x < knotsCount){
            for (int i = 0; i < knotsCount; i++) {
                adjMatrix[i][x] = adjMatrix[i][x + 1];
                weightMatrix[i][x] = weightMatrix[i][x + 1];
            }

            for (int i = 0; i < knotsCount; i++) {
                adjMatrix[x][i] = adjMatrix[x + 1][i];
                weightMatrix[x][i] = weightMatrix[x + 1][i];
            }
            x++;
            
        }
        knotsCount--;
        return 1;
    }

    public int editVertex(char name, int newMark){
        if (indexOfName(name) != -1){
            knots[indexOfName(name)].mark = newMark;
            return 1;
        }
        return -1;
    }

    private int indexOfName(char nameSearch){
        for (int i = 0; i < knotsCount; i++) {
            if (knots[i].name == nameSearch)
                return i;
        }
        return -1;
    }

    public int addEdge(char fromV, char toV, int weight){
        if (indexOfName(fromV) != -1 && indexOfName(toV) != -1 && weight > 0){
            adjMatrix[indexOfName(fromV)][indexOfName(toV)] = 1;
            weightMatrix[indexOfName(fromV)][indexOfName(toV)] = weight;
            return 1;
        }
        return -1;
    }

    public int deleteEdge(char fromV, char toV){
        if (indexOfName(fromV) != -1 && indexOfName(toV) != -1){
            adjMatrix[indexOfName(fromV)][indexOfName(toV)] = 0;
            weightMatrix[indexOfName(fromV)][indexOfName(toV)] = 0;
            return 1;
        }
        return -1;
    }

    public int editEdge(char fromV, char toV, int newWeight){
        if (indexOfName(fromV) != -1 && indexOfName(toV) != -1 && newWeight > 0) {
            weightMatrix[indexOfName(fromV)][indexOfName(toV)] = newWeight;
            return 1;
        }
        return -1;
    }

    public int getFirst(char vertex){
        for (int i = 0; i < knotsCount; i++) {
            if (adjMatrix[indexOfName(vertex)][i] == 1)
                return i;
        }
        return -1;
    }

    public int getFirst(int vertex){
        for (int i = 0; i < knotsCount; i++) {
            if (adjMatrix[vertex][i] == 1)
                return i;
        }
        return -1;
    }

    public int getNext(char vertex, int index){
        for (int i = index; i < knotsCount; i++) {
            if (adjMatrix[indexOfName(vertex)][i] == 1){
                return i;
            }
        }
        return -1;
    }

    public int getNext(Vertex vertex, int index){
        for (int i = index+1; i < knotsCount; i++) {
            if (adjMatrix[indexOfName(vertex.name)][i] == 1){
                return i;
            }
        }
        return -1;
    }

    public Vertex getVertex(int i){
        if (i <= knotsCount)
            return knots[i];
        return null;
    }

    public void setMarkDefalut(){
        for (int i = 0; i < knotsCount ; i++) {
            knots[i].mark = 0;
        }
    }

    public void printAdjMatrix(){

        System.out.print("  ");
        for (int i = 0; i < this.knotsCount ; i++) {
            System.out.print(knots[i].name + " ");
        }
        System.out.println();

        for (int i = 0; i < this.knotsCount; i++) {
            System.out.print(knots[i].name + " ");
            for (int j = 0; j < this.knotsCount; j++) {
                System.out.print(this.adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    public void printWeightMatrix(){

        System.out.print("  ");
        for (int i = 0; i < this.knotsCount ; i++) {
            System.out.print(knots[i].name + " ");
        }
        System.out.println();

        for (int i = 0; i < this.knotsCount; i++) {
            System.out.print(knots[i].name + " ");
            for (int j = 0; j < this.knotsCount; j++) {
                System.out.print(this.weightMatrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
