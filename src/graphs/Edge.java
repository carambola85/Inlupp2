
package graphs;

public class Edge<T> {
    private T dest;
    private String name;
    private int weight;
    
    protected Edge(T t, String name, int weight){
        if (weight < 0)
            throw new IllegalArgumentException("Negativ vikt för edge ej tillåten");
        
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
    
    public void setWeight(int w){
        if(w > 0)
            this.weight = w;
    }
    
    public String toString(){
        return "till " + dest + " med " + name + " tar " + weight + " minuter.";
    }
    
}
