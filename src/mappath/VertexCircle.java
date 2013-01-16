package mappath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VertexCircle extends JComponent{
    
    Vertex anchor;
    boolean markerad = false;
    public VertexCircle(Vertex v){
        
        this.anchor = v;
        setBounds(0, 0, 30, 30);
        setPreferredSize(new Dimension(30,30));
        setMaximumSize(new Dimension(30,30));
        setMinimumSize(new Dimension(30,30));
        
        
        this.setVisible(true);
        addMouseListener(new circleListen());
        
        
    }
    
    class circleListen extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent mev){
            markerad = !markerad;
            System.out.println(anchor.toString());
            repaint();
        }
    }
    
    public Vertex getAnchor(){
        return anchor;
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
            g.fillOval(0,0,30,30);
        }
 
       
    }
    
}
