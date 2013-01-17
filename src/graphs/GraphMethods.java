/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.*;
import java.util.HashSet;

public class GraphMethods <T> {

    private static <T> void deepFirst(Graph<T> graph, T t, Set<T> found){
        
        found.add(t);
        List<Edge<T>> edges = graph.getEdgesFrom(t);
        for (Edge<T> e : edges) {
            T ts = e.getDest();
            if (!found.contains(ts))
                deepFirst(graph, ts, found);
        } 
    }
    
    public static <T> boolean pathExists(Graph<T> graph, T from, T to){
        
        Set<T> found = new HashSet<T>();
        deepFirst(graph, from, found);
        return found.contains(to);
    }
    
    public static <T> List<Edge<T>> shortestPath(Graph<T> graph, T from, T to){
        
        boolean exists = pathExists(graph, from, to);
        if (!exists)
            return null;
        
        ArrayList<T> visited = new ArrayList<T>();
        ArrayList<Edge<T>> eList = new ArrayList<Edge<T>>();
        
        HashMap<T, Boolean> boolTable = new HashMap<T, Boolean>();
        HashMap<T, Integer> table = new HashMap<T, Integer>();
        
        for (T currentCity : graph.getNodes()){
            table.put(currentCity, Integer.MAX_VALUE);
            boolTable.put(currentCity, false);
        }
        
        table.put(from, 0);
        boolTable.put(from, true);
        
        T current = from;
        Edge<T> cc = null;
        Integer low = new Integer(Integer.MAX_VALUE);
    
        
        while(!boolTable.get(to)){
            visited.add(current);
        
        
        
        for (Edge<T> c : graph.getEdgesFrom(current)){
            
            if(table.get(c.getDest()) > (table.get(current)+c.getWeight())) {
                table.put(c.getDest(), (table.get(current)+c.getWeight()));
            }
        }
        
        for (T s : graph.getNodes()){
            if (!boolTable.get(s)){
                if(table.get(s)<low){
                    low = table.get(s);
                    current=s;
                }
            }
        }
    
        low = Integer.MAX_VALUE;
        boolTable.put(current,true);
        }

        
        visited.add(to);
        
        int loe = Integer.MAX_VALUE;
        
        
        for(int t=0; t<(visited.size()-1) ;t++){
            List<Edge<T>> l = graph.getEdgesBetween(visited.get(t), visited.get(t+1));
        
            for (Edge<T> ee : l){
                
                if (ee.getWeight()<loe){
                    loe=ee.getWeight();
                    cc=ee;
                }
            }
            
            loe=Integer.MAX_VALUE;
            eList.add(cc);
        }
        
        return eList;
        
    }
    
    
}
