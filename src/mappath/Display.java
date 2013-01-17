package mappath;

import graphs.ListGraph;
import graphs.Edge;
import graphs.GraphMethods;

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
import static javax.swing.JOptionPane.*;
import javax.swing.event.*;



public class Display extends JFrame {
    
    
    JButton buttonHitta, buttonVisa, buttonPlats, buttonForb, buttonChange;
    JFileChooser jfc; 
    BildPanel bild = null;
    mouseListen ml = new mouseListen();
    ListGraph<Vertex> lg = new ListGraph<Vertex>();
    GraphMethods gm = new GraphMethods();
    
    circleListen cl = new circleListen();
    VertexCircle c1 = null, c2 = null;
    listListen ll = new listListen();
    changeListen change = new changeListen();
    
    VertexHandler v = new VertexHandler(lg);
    boolean markerad;
    
    
    
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
        changeItem.addActionListener(change);
        
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
        int total = 0;
        
        @Override
        public void actionPerformed(ActionEvent ave){
            
           
           if (c1 == null || c2 == null){
                JOptionPane.showMessageDialog(null, "Du måste välja mellan vilka två städer först");
           }
           
           else if(gm.pathExists(lg, c1.getAnchor(), c2.getAnchor())){
            
            ArrayList<Edge> shortestPath = new ArrayList();
            shortestPath = (ArrayList)gm.shortestPath(lg, c1.getAnchor(), c2.getAnchor());
            
            for (Edge e : shortestPath){
                total += e.getWeight();
            
            
            ShortestPathList shortL = new ShortestPathList(shortestPath, c1.getAnchor(), c2.getAnchor(), total);
            JOptionPane.showConfirmDialog(null, shortL, "Kortaste vägen", JOptionPane.DEFAULT_OPTION);
            total = 0;
           }
           }
           else{
               JOptionPane.showMessageDialog(null, "Det finns ingen möjlig väg mellan noderna du valt");
           }
        }
    }
    
    class visaListen implements ActionListener{
        
        public void actionPerformed(ActionEvent ave){
           if (c1 == null || c2 == null){
                JOptionPane.showMessageDialog(null, "Du måste välja mellan vilka två städer först");
           }
           
           else{
                EdgeList el = new EdgeList(c1.getAnchor(), c2.getAnchor()); 
           
                int svar = JOptionPane.showConfirmDialog(null, el, "Kopplingar", JOptionPane.DEFAULT_OPTION);
           }
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
           
            lg = new ListGraph<Vertex>();
    
        }
    }
    
     class circleListen extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent mev){
            VertexCircle c = (VertexCircle)mev.getSource();
            if (c.getMarkerad()){
                if (c == c1){
                    c.toggleMarkerad();
                    c1 = null;
                    repaint();
                }
                else if (c == c2){
                    c.toggleMarkerad();
                    c2 = null;
                    repaint();
                }
            }
            
            else{
                if (c1 == null || c2 == null){
                    if (c1 == null){
                    	c1 = c;
                    	c.toggleMarkerad();
                    	repaint();
                    }
                
                    else if (c2 == null && c1 != c){
                        c2 = c;
                        c.toggleMarkerad();
                        repaint();
                    }
                    
                }
            
        }
    }
    }
    
    
    class forbListen implements ActionListener{
        
        public void actionPerformed(ActionEvent ave){
            
            if(c1 == null || c2 == null){
                JOptionPane.showMessageDialog(null, "Du måste välja mellan vilka två städer först");
            }
            
            else{
            NewEdgeForm form = new NewEdgeForm(c1.getAnchor(), c2.getAnchor());
            
            
            int svar = JOptionPane.showConfirmDialog(null, form, "Ny förbindelse", JOptionPane.OK_CANCEL_OPTION);
            if (svar != OK_OPTION){
                return;
            }
            else{ 
            String name = form.getName();
            int time = form.getTime();
            
            lg.connect(c1.getAnchor(), c2.getAnchor(), name, time);
            
            }
            
                    
            }
            
        }
    }
    class NewEdgeForm extends JPanel{
        
        private JTextField namn, tid;
        public NewEdgeForm(Vertex v1, Vertex v2){
            setLayout (new BoxLayout(this,BoxLayout.Y_AXIS));
            JPanel rad0 = new JPanel();
            JPanel rad1 = new JPanel();
            JPanel rad2 = new JPanel();
            
            JLabel rad3 = new JLabel ("Skapa förbindelse mellan " + v1.getName() + " och " + v2.getName() +".");
            JLabel rad1text = new JLabel ("Namn:");
            JLabel rad2text = new JLabel ("Tid i minuter:");
            namn = new JTextField(15);
            tid = new JTextField(4);
            
            rad0.add(rad3);
            rad1.add(rad1text);
            rad1.add(namn);
            rad2.add(rad2text);
            rad2.add(tid);
            
            add(rad0);
            add(rad1);
            add(rad2);
            
            
        }
        
        public String getName(){
            return namn.getText();
        }
        public int getTime(){
            return Integer.parseInt(tid.getText());
        }

    }
    
    class ChangeEdgeForm extends JPanel{
        
        private JTextField namn, tid;
        public ChangeEdgeForm(Edge e, Vertex v1, Vertex v2){
            setLayout (new BoxLayout(this,BoxLayout.Y_AXIS));
            JPanel rad0 = new JPanel();
            JPanel rad1 = new JPanel();
            JPanel rad2 = new JPanel();
            
            JLabel rad3 = new JLabel ("Ändra tiden mellan " + v1.getName() + " och " + v2.getName());
            JLabel rad1text = new JLabel ("Namn:");
            JLabel rad2text = new JLabel ("Tid i minuter:");
            namn = new JTextField(15);
            namn.setEnabled(false);
            namn.setText(e.getName());
            tid = new JTextField(4);
            
            rad0.add(rad3);
            rad1.add(rad1text);
            rad1.add(namn);
            rad2.add(rad2text);
            rad2.add(tid);
            
            add(rad0);
            add(rad1);
            add(rad2);
            
            
        }

        
        public String getName(){
            return namn.getText();
        }
        public int getTime(){
            return Integer.parseInt(tid.getText());
        }

    }
    
    class EdgeList extends JPanel{
        
        EdgeList(Vertex v1, Vertex v2){
            
            ArrayList a = (ArrayList)lg.getEdgesBetween(v1, v2);
            Edge[] edgeArray = new Edge[a.size() +1];
            a.toArray(edgeArray);
            
            setLayout(new BorderLayout());
            JPanel panel = new JPanel();
            setPreferredSize(new Dimension(300,300));
            JPanel northPanel = new JPanel();
            northPanel.setPreferredSize(new Dimension(300,100));
            add(northPanel, BorderLayout.NORTH);
            JLabel label = new JLabel("Förbindelser från " + v1.getName() + " till " + v2.getName());
            northPanel.add(label);
            JList list = new JList(edgeArray);
            list.addListSelectionListener(ll);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setPreferredSize(new Dimension(300,200));
            panel.add(list, BorderLayout.CENTER);
            add(panel);
            
            
        }

        
    }
    
    class ShortestPathList extends JPanel{
        
        ShortestPathList(ArrayList ar, Vertex v1, Vertex v2, int total){
            
            ArrayList a = ar;
            Edge[] edgeArray = new Edge[a.size() +1];
            a.toArray(edgeArray);
            
            setLayout(new BorderLayout());
            JPanel panel = new JPanel();
            setPreferredSize(new Dimension(300,300));
            JPanel northPanel = new JPanel();
            northPanel.setPreferredSize(new Dimension(300,100));
            add(northPanel, BorderLayout.NORTH);
            JPanel southPanel = new JPanel();
            southPanel.setPreferredSize(new Dimension(300,100));
            add(southPanel, BorderLayout.SOUTH);
            JLabel totalLabel = new JLabel("Totalt: "+total);
            JLabel label = new JLabel("Kortaste vägen mellan " + v1.getName() + " och " + v2.getName());
            northPanel.add(label);
            southPanel.add(totalLabel);
            JList list = new JList(edgeArray);
            list.addListSelectionListener(ll);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setPreferredSize(new Dimension(300,200));
            panel.add(list, BorderLayout.CENTER);
            add(panel);
            
            
        }

        
    }
    class nyPlatsListen implements ActionListener{
        
        public void actionPerformed(ActionEvent ave){
            bild.addMouseListener(ml);
            
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    }
    
    
    class changeListen implements ActionListener{
        
        Edge edge;
        
        public void setSelection(Edge e){
            this.edge = e;
        }
        
        public void actionPerformed(ActionEvent ave){
            
            if (c1 == null || c2 == null){
                JOptionPane.showMessageDialog(null, "Du måste välja mellan vilka två städer först");
            }
            else{
                EdgeList e = new EdgeList(c1.getAnchor(), c2.getAnchor());
            
                int svar = JOptionPane.showConfirmDialog(null, e, "Ändra förbindelse", JOptionPane.OK_CANCEL_OPTION);
                
                if(svar != OK_OPTION){
                    return;
                }
                else{
                    
                    ll.changeEdge(c1.getAnchor(), c2.getAnchor(), edge);
                    
                    
                }
                
                
                        
            }
            
        }
    }
    
    class listListen implements ListSelectionListener{
        Edge e;
        public void valueChanged(ListSelectionEvent lev){
           JList l = (JList)lev.getSource();
           if (!lev.getValueIsAdjusting()){
               e = (Edge)l.getSelectedValue();
               
           }
        }
        
        public void changeEdge(Vertex v1, Vertex v2, Edge e3){
            Edge edge = e3;
            ChangeEdgeForm cef = new ChangeEdgeForm(e, v1, v2);
            int svar = JOptionPane.showConfirmDialog(null, cef, "Välj förbindelse att ändra", JOptionPane.OK_CANCEL_OPTION);
            
            if (svar != OK_OPTION){
                return;
            }
            int newWeight = cef.getTime();
            e.setWeight(newWeight);
            
            ArrayList<Edge> edges = (ArrayList)lg.getEdgesBetween(v2,v1);
            for (Edge e2: edges){
                if (e2.getName().equalsIgnoreCase(e.getName())){
                e2.setWeight(newWeight);
                }
            }
            
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
      
            Vertex v = new Vertex(platsNamn);
            
            
            
            VertexImage vImage = new VertexImage(x, y, platsNamn, v);
            vImage.setMouseListener(cl);
            
            
            
            bild.add(vImage);
            
            
            
            lg.add(v);
            bild.repaint();
           
            bild.removeMouseListener(ml);
            setCursor(Cursor.getDefaultCursor());
            
            
            
            
        }
    }
    
    
}
