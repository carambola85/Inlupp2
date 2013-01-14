package mappath;

import javax.swing.*;
import java.awt.*;
import java.util.*;
        
public class BildPanel extends JPanel {
    private ImageIcon image;
    ArrayList<VertexImage> vertexImageArray = new ArrayList();
    
    BildPanel(String filename){
        setLayout(null);
        image = new ImageIcon(filename);
        setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
        
    }
    
    public void addVertexImage(VertexImage v){
        vertexImageArray.add(v);
    }
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
            g.drawImage(image.getImage(), 0, 0, this);
           
       
    }
    
}
