
package mappath;

import javax.swing.*;
import java.awt.*;

public class VertexText extends JComponent {
    
    String name;
    
    VertexText(String n){
        this.name = n;
        setBounds(0, 0, 200, 100);
        
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.drawString(name, 30, 30);
        
 
       
    }
    
}
