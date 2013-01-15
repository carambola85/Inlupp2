
package mappath;

import graphs.ListGraph;

public class VertexHandler {
    
    ListGraph lg;

    VertexHandler(ListGraph lg) {
        this.lg = lg;
    }
    
    public void addVertex(BildPanel bild, int x, int y, String platsNamn ){
        
            VertexImage vImage = new VertexImage(x, y, platsNamn);
            
            bild.add(vImage);
            lg.add(platsNamn);
            
            
            
    }
        
}
