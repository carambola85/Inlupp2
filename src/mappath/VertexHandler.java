
package mappath;

import graphs.ListGraph;

public class VertexHandler {
    
    ListGraph lg;

    VertexHandler(ListGraph lg) {
        this.lg = lg;
    }
    
    public void addVertex(BildPanel bild, int x, int y, String platsNamn ){
        
            
            
            Vertex v = new Vertex(platsNamn);
            VertexImage vImage = new VertexImage(x, y, platsNamn, v);
            bild.add(vImage);
            
            System.out.println(v.toString());
            
            lg.add(v);
            
            
            
    }
        
}
