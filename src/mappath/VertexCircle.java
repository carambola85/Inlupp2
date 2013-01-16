package mappath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VertexCircle extends JComponent{
    
    Vertex anchor;
    boolean markerad = false;
    public VertexCircle(Vertex v){
    boolean markerad = false;
        
        this.anchor = v;
        setBounds(0, 0, 30, 30);
        setPreferredSize(new Dimension(30,30));
        setMaximumSize(new Dimension(30,30));
        setMinimumSize(new Dimension(30,30));
        
        
        this.setVisible(true);
        
        
        
    }
   
    
    public Vertex getAnchor(){
        return anchor;
    }
    
    public void toggleMarkerad(){
        markerad = !markerad;
    }
    
    public boolean getMarkerad(){
        return markerad;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(markerad){
            g.setColor(Color.RED);
            g.fillOval(0, 0, 30, 30);
        }
        else{
            g.setColor(Color.BLUE);
            System.out.println("tjabba");
            g.fillOval(0,0,30,30);
        }
 
       
    }
    
}
