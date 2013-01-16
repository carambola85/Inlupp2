/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.*;



public class ListGraph<T> implements Graph<T> {
    
    private Map<T, List<Edge<T>>> nodes = new HashMap<T, List<Edge<T>>>();
    String toStr;
    
    @Override
    public void add(T t){
        
    }
    
    @Override
    public void connect(T t1, T t2, String s, int i){
        
    }
  
    @Override
    public void setConnectionWeight(T t1, T t2, String s, int i){
        
    }
    
    @Override
    public Map<T, List<Edge<T>>> getNodes(){
        return nodes;
    }
    
    @Override
    public List<Edge<T>> getEdgesFrom(T t){
        
        return nodes.get(t);
}
    
    @Override
    public List<Edge<T>> getEdgesBetween(T t1, T t2){
        ArrayList<Edge<T>> edges = new ArrayList<Edge<T>>();
        return edges;
    };
    
    @Override
    public String toString(){
        
        return toStr;
    }
    
    
    
}
