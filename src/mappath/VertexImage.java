package mappath;

import javax.swing.*;
import java.awt.*;
import java.awt.Event;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;


public class VertexImage extends JComponent{
    
    private VertexCircle vCircle;
    private VertexText vText;
    
    
    public VertexImage(int x, int y, String n, Vertex v){
        
        
        setBounds(x -25, y -25, 200, 200);
        setPreferredSize(new Dimension(200,200));
        setMaximumSize(new Dimension(200,200));
        setMinimumSize(new Dimension(200,200));
        
        this.setOpaque(true);
        this.setVisible(true);
        vCircle = new VertexCircle(v);
        add(vCircle);
        vText = new VertexText(n);
        add(vText);
        
    }
    
    public void setMouseListener(MouseAdapter m){
        vCircle.addMouseListener(m);
    }
    
    
    
    
     
}
