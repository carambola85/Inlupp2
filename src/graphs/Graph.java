/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.*;

public interface Graph<T> {
    
    public void add(T t);
    public void connect(T t1, T t2, String s, int i);
   public Set<T> getNodes();
    public List<Edge<T>> getEdgesFrom(T t);
    public List<Edge<T>> getEdgesBetween(T t1, T t2);
    public void setConnectionWeight(T t1, T t2, String s, int i);
    
}
