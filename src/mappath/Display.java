package mappath;

import graphs.ListGraph;

import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.JInternalFrame.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.io.*;
import java.util.*;



public class Display extends JFrame {
    
    
    JButton buttonHitta, buttonVisa, buttonPlats, buttonForb, buttonChange;
    JFileChooser jfc; 
    BildPanel bild = null;
    mouseListen ml = new mouseListen();
    ListGraph<Vertex> lg = new ListGraph<Vertex>();
    
    VertexHandler v = new VertexHandler(lg);
    
    
    
    
    Display(){
        super("Map Path Finder");
        
        setLayout(new BorderLayout());
       
        JPanel northPanel = new JPanel();
        jfc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Bilder", "gif", "jpg", "jpeg", "png");
        jfc.addChoosableFileFilter(filter);
        
        
        
        buttonHitta = new JButton("Hitta väg");
        buttonVisa = new JButton("Visa förbindelse");
        buttonPlats = new JButton("Ny plats");
        buttonForb = new JButton("Ny förbindelse");
        buttonChange = new JButton("Ändra förbindelse");
        
        JMenuBar mbr = new JMenuBar();
        JMenu arkivMenu = new JMenu("Arkiv");
        JMenu opMenu = new JMenu("Operationer");
        JMenu helpMenu = new JMenu("Help");
        
        JMenuItem nyttItem = new JMenuItem("Nytt");
        nyttItem.addActionListener(new nyListen());
        
        JMenuItem avslutaItem = new JMenuItem("Avsluta");
        
        JMenuItem hittaItem = new JMenuItem("Hitta väg");
        hittaItem.addActionListener(new hittaListen());
        
        JMenuItem visaItem = new JMenuItem("Visa förbindelse");
        visaItem.addActionListener(new visaListen());
        
        JMenuItem platsItem = new JMenuItem("Ny plats");
        platsItem.addActionListener(new nyPlatsListen());
        
        JMenuItem forbItem = new JMenuItem("Ny förbindelse");
        forbItem.addActionListener(new forbListen());
                
        JMenuItem changeItem = new JMenuItem("Ändra förbindelse");
        changeItem.addActionListener(new changeListen());
        
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new aboutListen());
        
        arkivMenu.add(nyttItem);
        arkivMenu.add(avslutaItem);
        opMenu.add(hittaItem);
        opMenu.add(visaItem);
        opMenu.add(platsItem);
        opMenu.add(forbItem);
        opMenu.add(changeItem);
        
        helpMenu.add(aboutItem);
        
        
        
        setJMenuBar(mbr);
        mbr.add(arkivMenu);
        mbr.add(opMenu);
        mbr.add(helpMenu);
        
        
        
        northPanel.add(buttonHitta);
        buttonHitta.addActionListener(new hittaListen());
        
        northPanel.add(buttonVisa);
        buttonVisa.addActionListener(new visaListen());
        
       
        northPanel.add(buttonPlats);
        buttonPlats.addActionListener(new nyPlatsListen());
        
        northPanel.add(buttonForb);
        buttonForb.addActionListener(new forbListen());
        
        northPanel.add(buttonChange);
        buttonChange.addActionListener(new changeListen());
        
        
        
        add(northPanel, BorderLayout.NORTH);
        
        
        setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(300,300);
        setResizable(false);
    }
    
    class hittaListen implements ActionListener{
        
        public void actionPerformed(ActionEvent ave){
            JOptionPane.showMessageDialog(null, "Hitta-knappen");
        }
    }
    
    class visaListen implements ActionListener{
        
        public void actionPerformed(ActionEvent ave){
            JOptionPane.showMessageDialog(null, "Visa-knappen");
        }
    }
    
    
    
    class nyListen implements ActionListener{
        
        public void actionPerformed(ActionEvent ave){
            
            int svar = jfc.showOpenDialog(Display.this);
            if(svar != JFileChooser.APPROVE_OPTION)
                return;
            
            File file = jfc.getSelectedFile();
        String filename = file.getAbsolutePath();
            
            if(bild != null)
                remove(bild);
                
            bild = new BildPanel(filename);
            add(bild, BorderLayout.CENTER);
            bild.setLayout(null);
           
            pack();
            validate();
            repaint();
        }
    }
    
    
    
    
    class forbListen implements ActionListener{
        
        public void actionPerformed(ActionEvent ave){
            JOptionPane.showMessageDialog(null, "Förbindelse-knappen");
            System.out.println(lg.toString());
        }
    }
    
    class nyPlatsListen implements ActionListener{
        
        public void actionPerformed(ActionEvent ave){
            bild.addMouseListener(ml);
            
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    }
    
    
    class changeListen implements ActionListener{
        
        public void actionPerformed(ActionEvent ave){
            JOptionPane.showMessageDialog(null, "Ändra-knappen");
        }
    }
    
    class aboutListen implements ActionListener{
        
        public void actionPerformed(ActionEvent ave){
            JOptionPane.showMessageDialog(null, "Made by Anders och Jimmy");
        }
    }
    
    class mouseListen extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent mev){
            int x = mev.getX();
            int y = mev.getY();
            
            String platsNamn = JOptionPane.showInputDialog(null, "Ange namn på stad");
      
            v.addVertex(bild, x, y, platsNamn);
            bild.repaint();
           
            bild.removeMouseListener(ml);
            setCursor(Cursor.getDefaultCursor());
            
            
            
            
        }
    }
    
    
}
