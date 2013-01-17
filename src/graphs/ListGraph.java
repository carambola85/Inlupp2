/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.*;


public class ListGraph<T> implements Graph<T> {
    
    private Map<T, List<Edge<T>>> nodes = new HashMap<>();
    String toStr;
    
    @Override
    public void add(T t){
        if (nodes.containsKey(t))
            throw new IllegalArgumentException("Node finns");
        nodes.put(t, new ArrayList<Edge<T>>());
    }
    
    @Override
    public void connect(T t1, T t2, String s, int weight){
        
        if (!nodes.containsKey(t1) || !nodes.containsKey(t2)){
            throw new NoSuchElementException("Node finns ej vid connect");
        }
        
        if (nodes.get(t1) == nodes.get(t2)){
            //this shouldn't happen as you can't select the same circle twice (as it will be deselected on the second click), but just in case
            throw new IllegalArgumentException("Origin and destination is the same object");
        }
        
            boolean exists = false;
            
            List<Edge<T>> fromList = nodes.get(t1);
            
            List<Edge<T>> toList = nodes.get(t2);
            
            for (Edge<T> e : fromList){
                if(e.getName().equalsIgnoreCase(s) && e.getDest() == t2) {
                    exists = true;
                }
            }
            
            if (!exists){
                Edge<T> e1 = new Edge<T>(t2, s, weight);
                Edge<T> e2 = new Edge<T>(t1, s, weight);
                fromList.add(e1);
                toList.add(e2);
                
                
            }
            else{
                throw new IllegalArgumentException("Finns redan en båge med detta namn");
            }
        
    }
  
    @Override
    public void setConnectionWeight(T t1, T t2, String s, int i){
        boolean found = false;
        
        if (!nodes.containsKey(t1) || !nodes.containsKey(t2)){
            throw new NoSuchElementException("En av noderna finns ej för viktändring");
        }
        
        List<Edge<T>> fromList = nodes.get(t1);
        
        for (Edge<T> e : fromList){
            if(e.getDest() == t2){
                e.setWeight(i);
                found = true;
                
            }
        }
        if (!found){
            throw new NoSuchElementException("Någon sådan koppling existerar ej");
        }
    }
    
   
    @Override
    public Set<T> getNodes() {
        return new HashSet<T>(nodes.keySet());
    
    }
    
    @Override
    public List<Edge<T>> getEdgesFrom(T t){
        
        if(nodes.containsKey(t)){
            return nodes.get(t);
        }
        else{
            throw new NoSuchElementException("Denna nod existerar ej");
        }
    }
    
    
    @Override
    public List<Edge<T>> getEdgesBetween(T t1, T t2){
        if (!nodes.containsKey(t1) || !nodes.containsKey(t2)){
            throw new NoSuchElementException("En av dessa nodes existerar ej");
        }
        
        
        ArrayList<Edge<T>> edges = new ArrayList<>();
        List<Edge<T>> fromList = nodes.get(t1);
        
        for(Edge e : fromList){
            if(e.getDest() == t2){
                edges.add(e);
            }
        }
        
        return edges;
    };
    
    @Override
    public String toString(){
        for (Map.Entry<T,List<Edge<T>>> me : nodes.entrySet()){
            toStr += "\n" + me.getKey() + ": " + me.getValue();
        }
        return toStr;
    }
    
    
    
}
