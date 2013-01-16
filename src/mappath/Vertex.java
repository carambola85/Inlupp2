/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mappath;


public class Vertex{
    static int amount = 0;
    private String name;
    private int nr;
    
    Vertex(String name){
        
        this.name = name;
        this.nr = ++amount;
    }
    
    public String getName(){
        return name;
        
    }
    
    @Override
    public String toString(){
        
        return "This vertex was created as number " + nr + " and is called " + name + ".";
    }
}
