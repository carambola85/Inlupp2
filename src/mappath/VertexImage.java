package mappath;

import javax.swing.*;
import java.awt.*;


public class VertexImage extends JComponent{
    
    VertexCircle vCircle;
    VertexText vText;
    
    public VertexImage(int x, int y, String n){
        
        setBounds(x -25, y -25, 200, 200);
        setPreferredSize(new Dimension(200,200));
        setMaximumSize(new Dimension(200,200));
        setMinimumSize(new Dimension(200,200));
        
        this.setOpaque(true);
        this.setVisible(true);
        vCircle = new VertexCircle();
        add(vCircle);
        vText = new VertexText(n);
        add(vText);
        
    }
    
    
    
    
}
