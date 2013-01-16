
package graphs;

public class Edge<T> {
    private T dest;
    private String name;
    private int weight;
    
    protected Edge(T t, String name, int weight){
        this.dest = t;
        this.name = name;
        this.weight = weight;
    }
    
    public T getDest(){
        return dest;
    }
    
    public String getName(){
        return name;
    }
    
    public int getWeight(){
        return weight;
    }
    
    public String toString(){
        return "till " + dest + " med " + name + ": " + weight;
    }
    
}
