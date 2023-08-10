import java.util.*;
import java.util.Comparator;

/**
 * Library for graph analysis
 *
 * @author Chipo Chibbamulilo
 * @author Chikwanda Chisha
 *
 */
public class GraphLib {
    /**
     * Takes a random walk from a vertex, up to a given number of steps
     * So a 0-step path only includes start, while a 1-step path includes start and one of its out-neighbors,
     * and a 2-step path includes start, an out-neighbor, and one of the out-neighbor's out-neighbors
     * Stops earlier if no step can be taken (i.e., reach a vertex with no out-edge)
     * @param g		graph to walk on
     * @param start	initial vertex (assumed to be in graph)
     * @param steps	max number of steps
     * @return		a list of vertices starting with start, each with an edge to the sequentially next in the list;
     * 			    null if start isn't in graph
     */
    public static <V,E> List<V> randomWalk(Graph<V,E> g, V start, int steps) {

        // initialize path;
        ArrayList<V> path= new ArrayList<>();
        path.add(start);

        for(int i=steps; i>0;i--) {
            //stop if the vertex has no outNeighbor
            if (g.outNeighbors(path.get(path.size() - 1)) == null) break;

            else {
                ArrayList<V> neighbors = new ArrayList<>();

                for (V v : g.outNeighbors(path.get(path.size() - 1))) {
                    //add the neighbors of v into neighbors array
                    neighbors.add(v);
                }
                //randomly pick the index in neighbors in which the walk proceeds, add to list
                if(!neighbors.isEmpty()) {
                    int j = (int) (Math.random() * neighbors.size());
                    path.add(neighbors.get(j));
                }
            }
        }
        return path;
    }


    /**
     * Orders vertices in decreasing order by their in-degree
     * @param g		graph
     * @return		list of vertices sorted by in-degree, decreasing (i.e., largest at index 0)
     */
    public static <V,E> List<V> verticesByInDegree(Graph<V,E> g) {
        ArrayList<V> decreaseList=new ArrayList<>();

        for (V v: g.vertices()){
          decreaseList.add(v);
        }

        //custom comparator
        Comparator<V> listComparator = (v1, v2)-> {

                if (g.inDegree(v1) > g.inDegree(v2)) return 1;

                else if (g.inDegree(v1) == g.inDegree(v2)) {
                    return 0;
                }
                else {
                    return -1;
                }
        };
        // sort according to descending order
        decreaseList.sort(listComparator);

        for(V i:decreaseList) System.out.println(g.inDegree(i));

        return decreaseList;
    }


    public static void main(String[] args) {
        Graph<String,String> g=new AdjacencyMapGraph<String,String>();

        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");
        g.insertVertex("E");
        g.insertUndirected("A","B","undirected");
        g.insertUndirected("A","C","undirected");
        g.insertUndirected("B","C","undirected");
        g.insertDirected("E","B","directed");
        g.insertDirected("A","D","directed");
        g.insertDirected("C","D","directed");
        g.insertDirected("E","C","directed");
        g.insertDirected("A","E","directed");

        System.out.println("Path taken: "+ randomWalk(g,"A",5));
        System.out.println("-------\n"+"Sorted:\n"+verticesByInDegree(g));


    }

}
