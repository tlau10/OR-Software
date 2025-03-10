package absf1;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.help.HelpSet;
import javax.help.JHelp;
import javax.help.JHelpIndexNavigator;
import javax.help.JHelpNavigator;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;

/**
 * <p>Title: TransportOptimizer</p>
 * <p>Description: Hauptklasse der gesamten Applikation.</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: FH Konstanz</p>
 * @author Juergen Kambeitz und Florian P�tz
 * @version 1.0
 */

public class HauptFrame extends JFrame {
  private JPanel contentPane;
  private JMenuBar jMenuBar1 = new JMenuBar();
  private JMenu jMenuDatei = new JMenu();
  private JMenuItem jMenuDateiNeu = new JMenuItem();
  private JMenu jMenuAnsicht = new JMenu();
  private JMenuItem jMenuAnsichtEingabeLkws = new JMenuItem();
  private ImageIcon image1;
  private ImageIcon image2;
  private ImageIcon image3;
  private BorderLayout borderLayout1 = new BorderLayout();
  private JMenuItem jMenuDateiBeenden = new JMenuItem();
  private JMenu jMenuBearbeiten = new JMenu();
  private JMenuItem jMenuBearbeitenBerechnen = new JMenuItem();
  private JMenuItem jMenuBearbeitenGrafikErzeugen = new JMenuItem();
  private JMenuItem jMenuAnsichtEingabeGebinde = new JMenuItem();
  private JMenuItem jMenuAnsichtErgebnis = new JMenuItem();
  private JMenuItem jMenuAnsichtGrafik = new JMenuItem();
  private JMenu jMenuHilfe = new JMenu();
  private JMenuItem jMenuHilfeUeber = new JMenuItem();
  private JTabbedPane jTabbedPane1 = new JTabbedPane();
  private JPanel jPanelGrafik = new JPanel();
  private JPanel jPanelEingabeLkws = new JPanel();
  private JPanel jPanelEingabeGebinde = new JPanel();
  private JPanel jPanelErgebnis = new JPanel();
  private JPanel jPanel5 = new JPanel();
  private TitledBorder titledBorder1;
  private JPanel jPanel6 = new JPanel();
  private TitledBorder titledBorder2;
  private JPanel jPanel7 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private XYLayout xYLayout2 = new XYLayout();
  private JPanel jPanelButtonsLkws = new JPanel();
  private JButton jButtonWeiterLkw = new JButton();
  private JButton jButtonZuruecksetzenLkw = new JButton();
  private XYLayout xYLayout3 = new XYLayout();
  private JScrollPane jScrollPane2 = new JScrollPane();
  private JTable jTableLkw = new JTable();
  private XYLayout xYLayout4 = new XYLayout();
  private XYLayout xYLayout5 = new XYLayout();
  private XYLayout xYLayout6 = new XYLayout();
  private XYLayout xYLayout7 = new XYLayout();
  private XYLayout xYLayout8 = new XYLayout();
  private XYLayout xYLayout9 = new XYLayout();
  private XYLayout xYLayout10 = new XYLayout();
  private JPanel jPanel9 = new JPanel();
  private XYLayout xYLayout11 = new XYLayout();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JTable jTableGebinde = new JTable();
  private JPanel jPanelButtonsGebinde = new JPanel();
  private XYLayout xYLayout12 = new XYLayout();
  private JButton jButtonZuruecksetzenGebinde = new JButton();
  private JButton jButtonGebindeBerechnen = new JButton();
  private JMenu jMenuBearbeitenSolver = new JMenu();
  private JMenuItem jMenuBearbeitenSolverXa = new JMenuItem();
  private JMenuItem jMenuBearbeitenSolverMops = new JMenuItem();
  private JButton jButtonLkwErzeugen = new JButton();
  private JButton jButtonGebindeErzeugen = new JButton();
  private JButton jButtonLkwLoeschen = new JButton();
  private JButton jButtonGebindeLoeschen = new JButton();
  private JMenuItem jMenuDateiOeffnen = new JMenuItem();
  private JMenuItem jMenuDateiSpeichern = new JMenuItem();
  private SolverEingabeBox seb = new SolverEingabeBox();



  //Eigene Variablen

  private File selFile;
  boolean bereitsGespeichert = false;
  private JMenuItem jMenuDateiSpeichernUnter = new JMenuItem();
  private JCheckBoxMenuItem jCheckBoxMenuSolverLp = new JCheckBoxMenuItem();
  private JMenuItem jMenuSolverPfade = new JMenuItem();
  private TitledBorder titledBorder3;
  private JPanel jPanel1 = new JPanel();
  private XYLayout xYLayout13 = new XYLayout();
  private TitledBorder titledBorder4;
  private JPanel jPanel2 = new JPanel();
  private JPanel jPanel3 = new JPanel();
  private XYLayout xYLayout14 = new XYLayout();
  private JScrollPane jScrollPane3 = new JScrollPane();
  private JTable jTableErgebnis = new JTable();
  private JButton jButtonGrafik = new JButton();
  private XYLayout xYLayout15 = new XYLayout();
  private JButton jButtonEingabeDatenAendern = new JButton();
  private JPanel jPanel4 = new JPanel();
  private JButton jButtonEingabeDatenAendern1 = new JButton();
  private JScrollPane jScrollPane4 = new JScrollPane();
  private XYLayout xYLayout16 = new XYLayout();
  private XYLayout xYLayout17 = new XYLayout();
  private XYLayout xYLayout18 = new XYLayout();
  private JButton jButtonBeenden = new JButton();
  private JPanel jPanel8 = new JPanel();
  private JPanel jPanel10 = new JPanel();


  /**
   * Konstruktor der Klasse HauptFrame.
   */
  public HauptFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Hier werden die Komponenten des Fensters initialisiert.
   * @throws Exception
   */
  private void jbInit() throws Exception  {

    contentPane = (JPanel) this.getContentPane();
    titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151)),"Eingabematrix f�r die LKWs");
    titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151)),"Eingabematrix f�r die Gebinde");
    titledBorder3 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151)),"Ausgabematrix des Ergebnis");
    titledBorder4 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151)),"Ergebnis");
    contentPane.setLayout(borderLayout1);
    this.setResizable(false);
    this.setSize(new Dimension(800, 600));
    this.setTitle("BeladungsOptimierungsProgramm BOP");
    jMenuDatei.setText("Datei");
    jMenuDateiNeu.setActionCommand("Neu");
    jMenuDateiNeu.setText("Neu...");
    jMenuDateiNeu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(78, java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuDateiNeu.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuDateiNeu_actionPerformed(e);
      }
    });
    jMenuAnsicht.setText("Ansicht");
    jMenuAnsichtEingabeLkws.setText("Eingabe LKWs");
    jMenuAnsichtEingabeLkws.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuAnsichtEingabeLkws_actionPerformed(e);
      }
    });
    jMenuDateiBeenden.setText("Beenden");
    jMenuDateiBeenden.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuDateiBeenden_actionPerformed(e);
      }
    });
    jMenuBearbeiten.setText("Bearbeiten");
    jMenuBearbeitenBerechnen.setText("Berechnen...");
    jMenuBearbeitenBerechnen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuBearbeitenBerechnen_actionPerformed(e);
      }
    });
    jMenuBearbeitenGrafikErzeugen.setEnabled(false);
    jMenuBearbeitenGrafikErzeugen.setText("Grafik erzeugen...");
    jMenuBearbeitenGrafikErzeugen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuBearbeitenGrafikErzeugen_actionPerformed(e);
      }
    });
    jMenuAnsichtEingabeGebinde.setText("Eingabe Gebinde");
    jMenuAnsichtEingabeGebinde.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuAnsichtEingabeGebinde_actionPerformed(e);
      }
    });
    jMenuAnsichtErgebnis.setEnabled(false);
    jMenuAnsichtErgebnis.setText("Ergebnis");
    jMenuAnsichtErgebnis.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuAnsichtErgebnis_actionPerformed(e);
      }
    });
    jMenuAnsichtGrafik.setEnabled(false);
    jMenuAnsichtGrafik.setText("Grafik");
    jMenuAnsichtGrafik.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuAnsichtGrafik_actionPerformed(e);
      }
    });
    jMenuHilfe.setText("Hilfe");
    jMenuHilfeUeber.setText("�ber...");
    jMenuHilfeUeber.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuHilfeUeber_actionPerformed(e);
      }
    });
    contentPane.setMinimumSize(new Dimension(800, 600));
    contentPane.setPreferredSize(new Dimension(800, 600));
    jTabbedPane1.setToolTipText("");
    jPanelErgebnis.setToolTipText("Anzeige des errechneten Ergebnis");
    jPanelErgebnis.setLayout(null);
    jPanelEingabeGebinde.setToolTipText("Eingabe der Gebinde-Daten");
    jPanelEingabeGebinde.setLayout(null);
    jPanelGrafik.setNextFocusableComponent(jPanelEingabeLkws);
    jPanelGrafik.setToolTipText("Anzeige der Ergebnisgrafik");
    jPanelGrafik.setLayout(null);
    jPanelEingabeLkws.setToolTipText("Eingabe der LKW-Daten");
    jPanelEingabeLkws.setLayout(null);
    jPanel5.setBorder(titledBorder1);
    jPanel5.setBounds(new Rectangle(7, 5, 769, 467));
    jPanel5.setLayout(xYLayout2);
    jPanel6.setBorder(titledBorder2);
    jPanel6.setMinimumSize(new Dimension(761, 463));
    jPanel6.setPreferredSize(new Dimension(761, 463));
    jPanel6.setBounds(new Rectangle(7, 5, 769, 466));
    jPanel6.setLayout(xYLayout4);
    jPanel7.setLayout(xYLayout1);
    xYLayout1.setWidth(100);
    xYLayout1.setHeight(100);
    jPanel7.setBorder(BorderFactory.createEtchedBorder());
    jPanelButtonsLkws.setBorder(BorderFactory.createEtchedBorder());
    jPanelButtonsLkws.setLayout(xYLayout3);
    jButtonWeiterLkw.setText("Weiter");
    jButtonWeiterLkw.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonWeiterLkw_actionPerformed(e);
      }
    });
    jButtonZuruecksetzenLkw.setText("Zur�cksetzen");
    jButtonZuruecksetzenLkw.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonZuruecksetzenLkw_actionPerformed(e);
      }
    });
    jScrollPane2.setColumnHeader(null);
    jScrollPane2.getViewport().setBackground(Color.white);
    jTableLkw.setName("Tabelle Lkws");
    jTableLkw.setBorder(BorderFactory.createLineBorder(Color.black));
    jTableLkw.setNextFocusableComponent(jButtonZuruecksetzenLkw);
    jTableLkw.setCellSelectionEnabled(true);


    String[] tabellenKopfLkw = {"LKW-Typ", "L�nge", "Breite", "H�he", "Zuladung", "Anzahl"};//Daten f�r den Tabellenkopf von LKW
    String[][] tabellenObjektLkw = {                 //Konstruktion des TabellenObjekts, welches dann an jTableLkw �bergeben wird.
    {"1", "", "", "", "", ""}};
    jTableLkw.setModel(new javax.swing.table.DefaultTableModel(tabellenObjektLkw, tabellenKopfLkw));//�bergabe der Daten an die Tabelle

    String[] tabellenKopfGebinde ={"Gebinde-Typ", "L�nge", "Breite", "H�he", "Gewicht", /*"Stapelbar",*/ "Anzahl"/*, "Sammelsendung"*/}; //Daten f�r den Tabellenkopf von Gebinde
    String[][] tabellenObjektGebinde = {{"1", "", "", "", "", ""}};

    jTableGebinde.setModel(new javax.swing.table.DefaultTableModel(tabellenObjektGebinde, tabellenKopfGebinde));//�bergabe der Daten an die Tabelle

    //Dynamisches Zusammenbauen des Tabellenkopfes f�r das Ergebnis
    String[] tabellenKopfErgebnis = new String[jTableGebinde.getModel().getRowCount()+2];
    tabellenKopfErgebnis[0] = "LKW-Typ";
    tabellenKopfErgebnis[1] = "Anzahl";
    for(int i=1; i<=jTableGebinde.getModel().getRowCount(); i++) {
      tabellenKopfErgebnis[i+1] = "Gebinde "+i;
    }
    //String[][] tabellenObjektErgebnis = new String[jTableGebinde.getModel().getRowCount()+2][0];
    String[][] tabellenObjektErgebnis = {{"","","","",""}};

    jTableErgebnis.setModel(new javax.swing.table.DefaultTableModel(tabellenObjektErgebnis, tabellenKopfErgebnis));

    xYLayout7.setHeight(100);
    xYLayout7.setWidth(100);
    xYLayout10.setHeight(100);
    xYLayout10.setWidth(100);
    jPanel9.setBorder(BorderFactory.createEtchedBorder());
    jPanel9.setLayout(xYLayout11);
    jPanelButtonsGebinde.setLayout(xYLayout12);
    jPanelButtonsGebinde.setBorder(BorderFactory.createEtchedBorder());
    jButtonZuruecksetzenGebinde.setText("Zur�cksetzen");
    jButtonZuruecksetzenGebinde.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonZuruecksetzenGebinde_actionPerformed(e);
      }
    });
    jButtonGebindeBerechnen.setToolTipText("");
    jButtonGebindeBerechnen.setText("Berechnen");
    jButtonGebindeBerechnen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonGebindeBerechnen_actionPerformed(e);
      }
    });
    jScrollPane1.getViewport().setBackground(Color.white);
    jMenuBearbeitenSolver.setActionCommand("Solverpfad");
    jMenuBearbeitenSolver.setText("Solver...");
    jMenuBearbeitenSolverXa.setEnabled(false);
    jMenuBearbeitenSolverXa.setText("XA");
    jMenuBearbeitenSolverMops.setEnabled(false);
    jMenuBearbeitenSolverMops.setText("MOPS");
    jButtonLkwErzeugen.setText("Lkw hinzuf�gen");
    jButtonLkwErzeugen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonLkwErzeugen_actionPerformed(e);
      }
    });
    jButtonGebindeErzeugen.setText("Gebinde hinzuf�gen");
    jButtonGebindeErzeugen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonGebindeErzeugen_actionPerformed(e);
      }
    });
    jButtonLkwLoeschen.setText("Markierten LKW l�schen");
    jButtonLkwLoeschen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonLkwLoeschen_actionPerformed(e);
      }
    });
    jButtonGebindeLoeschen.setText("Markiertes Gebinde l�schen");
    jButtonGebindeLoeschen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonGebindeLoeschen_actionPerformed(e);
      }
    });
    jMenuDateiOeffnen.setText("�ffnen...");
    jMenuDateiOeffnen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuDateiOeffnen_actionPerformed(e);
      }
    });
    jMenuDateiSpeichern.setEnabled(false);
    jMenuDateiSpeichern.setText("Speichern...");
    jMenuDateiSpeichern.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuDateiSpeichern_actionPerformed(e);
      }
    });
    jMenuDateiSpeichernUnter.setText("Speichern unter...");
    jMenuDateiSpeichernUnter.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuDateiSpeichernUnter(e);
      }
    });
    jCheckBoxMenuSolverLp.setText("LP-Solve");
    jMenuSolverPfade.setText("Solverpfade...");
    jMenuSolverPfade.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuSolverPfade_actionPerformed(e);
      }
    });
    jPanel1.setBorder(titledBorder4);
    jPanel1.setMinimumSize(new Dimension(761, 463));
    jPanel1.setPreferredSize(new Dimension(761, 463));
    jPanel1.setBounds(new Rectangle(7, 5, 769, 467));
    jPanel1.setLayout(xYLayout13);
    jPanel2.setBorder(BorderFactory.createEtchedBorder());
    jPanel2.setLayout(xYLayout14);
    jPanel3.setBorder(BorderFactory.createEtchedBorder());
    jPanel3.setLayout(xYLayout15);
    jScrollPane3.getViewport().setBackground(Color.white);
    jButtonGrafik.setText("Grafik");
    jButtonGrafik.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonGrafik_actionPerformed(e);
      }
    });
    jButtonEingabeDatenAendern.setText("Eingabedaten �ndern");
    jButtonEingabeDatenAendern.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonEingabeDatenAendern_actionPerformed(e);
      }
    });
    jPanel4.setLayout(xYLayout18);
    jPanel4.setBounds(new Rectangle(0, 0, 769, 467));
    jPanel4.setPreferredSize(new Dimension(761, 463));
    jPanel4.setMinimumSize(new Dimension(761, 463));
    jPanel4.setBorder(titledBorder4);
    jButtonEingabeDatenAendern1.setText("Eingabedaten �ndern");
    jButtonEingabeDatenAendern1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonEingabeDatenAendern1_actionPerformed(e);
      }
    });
    jButtonBeenden.setText("Beenden");
    jButtonBeenden.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonBeenden_actionPerformed(e);
      }
    });
    jPanel8.setBorder(BorderFactory.createEtchedBorder());
    jPanel8.setLayout(xYLayout16);
    jPanel10.setBorder(BorderFactory.createEtchedBorder());
    jPanel10.setLayout(xYLayout17);
    jMenuDatei.add(jMenuDateiNeu);
    jMenuDatei.add(jMenuDateiOeffnen);
    jMenuDatei.add(jMenuDateiSpeichern);
    jMenuDatei.add(jMenuDateiSpeichernUnter);
    jMenuDatei.addSeparator();
    jMenuDatei.add(jMenuDateiBeenden);
    jMenuAnsicht.add(jMenuAnsichtEingabeLkws);
    jMenuAnsicht.add(jMenuAnsichtEingabeGebinde);
    jMenuAnsicht.add(jMenuAnsichtErgebnis);
    jMenuAnsicht.add(jMenuAnsichtGrafik);
    jMenuBar1.add(jMenuDatei);
    jMenuBar1.add(jMenuAnsicht);
    jMenuBar1.add(jMenuBearbeiten);
    jMenuBar1.add(jMenuHilfe);
    this.setJMenuBar(jMenuBar1);
    contentPane.add(jTabbedPane1, BorderLayout.CENTER);
    jTabbedPane1.add(jPanelEingabeLkws,   "Eingabe LKWs");
    jPanelEingabeLkws.add(jPanel5, null);
    jPanel5.add(jPanel7,         new XYConstraints(7, 1, 742, 383));
    jPanel7.add(jScrollPane2,                           new XYConstraints(0, 0, 736, 377));
    jScrollPane2.getViewport().add(jTableLkw, null);
    jPanel5.add(jPanelButtonsLkws,       new XYConstraints(7, 394, 742, 41));
    jPanelButtonsLkws.add(jButtonLkwErzeugen, new XYConstraints(8, 5, -1, -1));
    jPanelButtonsLkws.add(jButtonWeiterLkw, new XYConstraints(660, 5, -1, -1));
    jPanelButtonsLkws.add(jButtonZuruecksetzenLkw, new XYConstraints(547, 5, -1, -1));
    jPanelButtonsLkws.add(jButtonLkwLoeschen,   new XYConstraints(131, 5, -1, -1));
    jTabbedPane1.add(jPanelEingabeGebinde,    "Eingabe Gebinde");
    jPanelEingabeGebinde.add(jPanel6, null);
    jPanel6.add(jPanel9,     new XYConstraints(7, 1, 742, 383));
    jPanel9.add(jScrollPane1,    new XYConstraints(0, 0, 738, 378));
    jPanel6.add(jPanelButtonsGebinde,  new XYConstraints(7, 394, 742, 41));
    jPanelButtonsGebinde.add(jButtonGebindeBerechnen,  new XYConstraints(634, 5, -1, -1));
    jPanelButtonsGebinde.add(jButtonZuruecksetzenGebinde,  new XYConstraints(520, 5, -1, -1));
    jPanelButtonsGebinde.add(jButtonGebindeErzeugen,  new XYConstraints(8, 5, -1, -1));
    jScrollPane1.getViewport().add(jTableGebinde, null);
    jTabbedPane1.add(jPanelErgebnis,   "Ergebnis");
    jPanelErgebnis.add(jPanel1, null);
    jPanel1.add(jPanel2,         new XYConstraints(5, 0, 745, -1));
    jPanel2.add(jScrollPane3,         new XYConstraints(1, 0, 739, 378));
    jScrollPane3.getViewport().add(jTableErgebnis, null);
    jPanel1.add(jPanel3,          new XYConstraints(6, 392, 745, 44));
    jPanel3.add(jButtonGrafik,    new XYConstraints(666, 7, -1, -1));
    jButtonGrafik.setEnabled(false);
    jPanel3.add(jButtonEingabeDatenAendern,   new XYConstraints(508, 7, -1, -1));
    jTabbedPane1.setEnabledAt(2, false);
    jTabbedPane1.add(jPanelGrafik,  "Grafik");
    jPanelGrafik.add(jPanel4, null);
    jPanel4.add(jPanel10, new XYConstraints(5, 0, 745, -1));
    jPanel10.add(jScrollPane4, new XYConstraints(1, 0, 739, 378));
    jPanel4.add(jPanel8, new XYConstraints(6, 392, 745, 44));
    jPanel8.add(jButtonEingabeDatenAendern1, new XYConstraints(489, 7, -1, -1));
    jPanel8.add(jButtonBeenden,  new XYConstraints(650, 7, -1, -1));
    jTabbedPane1.setEnabledAt(3, false);
    contentPane.add(jTabbedPane1, BorderLayout.CENTER);
    jMenuBearbeiten.add(jMenuBearbeitenBerechnen);
    jMenuBearbeiten.add(jMenuBearbeitenGrafikErzeugen);
    jMenuBearbeiten.addSeparator();
    jMenuBearbeiten.add(jMenuBearbeitenSolver);
    jMenuBearbeiten.add(jMenuSolverPfade);
    jMenuHilfe.add(jMenuHilfeUeber);
    jMenuBearbeitenSolver.add(jCheckBoxMenuSolverLp);
    jMenuBearbeitenSolver.add(jMenuBearbeitenSolverXa);
    jMenuBearbeitenSolver.add(jMenuBearbeitenSolverMops);
    jPanelButtonsGebinde.add(jButtonGebindeLoeschen,  new XYConstraints(159, 5, -1, -1));
    jScrollPane4.getViewport();
  }

  /**
   * Diese Methode wird ausgef�hrt nach Datei | Beenden action performed
   * @param e
   */
  public void jMenuDateiBeenden_actionPerformed(ActionEvent e) {
    System.exit(0);
  }

  /**
   * Diese Methode wird ausgef�hrt nach Hilfe | Ueber action performed
   * @param e
   */
  public void jMenuHilfeUeber_actionPerformed(ActionEvent e) {
//    HauptFrame_AboutBox dlg = new HauptFrame_AboutBox(this);
//    Dimension dlgSize = dlg.getPreferredSize();
//    Dimension frmSize = getSize();
//    Point loc = getLocation();
//    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
//    dlg.setModal(true);
//    dlg.pack();
//    dlg.show();
	  
		JHelp helpViewer = null;
		try {
			// Hauptfenster in der n�chsten Zeile ersetzen durch aktuellen
			// Klassennamen
			ClassLoader cl = HauptFrame.class.getClassLoader();
			URL url = HelpSet.findHelpSet(cl, "jhelpset.hs");
			helpViewer = new JHelp(new HelpSet(cl, url));
			// Darzustellendes Kapitel festlegen, ID muss im XML existieren!
			// helpViewer.getsetCurrentID("Simple.Introduction");

			Enumeration eNavigators = helpViewer.getHelpNavigators();
			while (eNavigators.hasMoreElements()) {
				JHelpNavigator nav = (JHelpNavigator) eNavigators.nextElement();
				if (nav instanceof JHelpIndexNavigator) {
					helpViewer.removeHelpNavigator(nav);
				}
			}
		} catch (Exception ex) {
			System.err.println("API Help Set not found");
		}

		JFrame frame = new JFrame();
		frame.setTitle("Hilfe zu Beladungsoptimierung");
		frame.setSize(800, 600);
		frame.getContentPane().add(helpViewer);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	  
	  
  }

  /**
   * Dient dem Schliessen des Fensters
   * @param e
   */
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      jMenuDateiBeenden_actionPerformed(null);
    }
  }


  /**
   * Diese Methode wird ausgef�hrt nach der Bet�tigung des "Weiter" - Buttons auf der Seite "Eingabe der LKW-Daten"
   * @param e
   */
  void jButtonWeiterLkw_actionPerformed(ActionEvent e) {

    jTabbedPane1.setSelectedIndex(1);//Ruft die Seite "Eingabe der Gebinde-Daten" auf

  }

  /**
   * Diese Methode wird ausgef�hrt nach Datei | Neu action performed
   * @param e
   */
  void jMenuDateiNeu_actionPerformed(ActionEvent e) {

    //Zuerst wird die Tabelle auf der Seite "Eingabe der LKW-Daten" zur�ckgesetzt
    if( jTableLkw.getModel() instanceof DefaultTableModel )
    {
      ((DefaultTableModel)jTableLkw.getModel()).setNumRows(0);
    }
    else
    {
      int cols = jTableLkw.getModel().getColumnCount();
      jTableLkw.setModel( new DefaultTableModel(0, cols) );
    }

    //Dann wird die Tabelle auf der Seite "Eingabe der Gebinde-Daten" zur�ckgesetzt
    if( jTableGebinde.getModel() instanceof DefaultTableModel )
    {
      ((DefaultTableModel)jTableGebinde.getModel()).setNumRows(0);
    }
    else
    {
       int cols = jTableGebinde.getModel().getColumnCount();
       jTableGebinde.setModel( new DefaultTableModel(0, cols) );
    }

    //Alle Einstellungen des GUI werden wieder auf die Initialwerte gesetzt
    bereitsGespeichert = false;
    jTabbedPane1.setSelectedIndex(0);
    jTabbedPane1.setEnabledAt(2, false);
    jTabbedPane1.setEnabledAt(3, false);

    jMenuAnsichtErgebnis.setEnabled(false);
    jMenuAnsichtGrafik.setEnabled(false);
    jMenuBearbeitenGrafikErzeugen.setEnabled(false);
  }

  /**
     * Diese Methode wird ausgef�hrt nach Ansicht | Eingabe LKWs action performed
     * @param e
   */
  void jMenuAnsichtEingabeLkws_actionPerformed(ActionEvent e) {

    jTabbedPane1.setSelectedIndex(0); //Die zugeh�rige Seite wird aktiv gesetzt

  }

  /**
    * Diese Methode wird ausgef�hrt nach Ansicht | Eingabe Gebinde action performed
    * @param e
   */
  void jMenuAnsichtEingabeGebinde_actionPerformed(ActionEvent e) {

    jTabbedPane1.setSelectedIndex(1);//Die zugeh�rige Seite wird aktiv gesetzt

  }

  /**
    * Diese Methode wird ausgef�hrt nach Ansicht | Ergebnis action performed
    * @param e
   */
  void jMenuAnsichtErgebnis_actionPerformed(ActionEvent e) {

    jTabbedPane1.setSelectedIndex(2);//Die zugeh�rige Seite wird aktiv gesetzt

  }

  /**
    * Diese Methode wird ausgef�hrt nach Ansicht | Grafik action performed
    * @param e
   */
  void jMenuAnsichtGrafik_actionPerformed(ActionEvent e) {

    jTabbedPane1.setSelectedIndex(3);//Die zugeh�rige Seite wird aktiv gesetzt

  }

  /**
   * Wird ausgef�hrt, wenn der Button "Zur�cksetzten" auf der Seite "Eingabe der Gebinde-Daten" gedr�ckt wird
   * @param e
   */
  void jButtonZuruecksetzenGebinde_actionPerformed(ActionEvent e) {

    //Die Eintr�ge in der Tabelle werden gel�scht (sofern vorhanden)
    if( jTableGebinde.getModel() instanceof DefaultTableModel )
                {
                        ((DefaultTableModel)jTableGebinde.getModel()).setNumRows(0);
                }
                else
                {
                        int cols = jTableGebinde.getModel().getColumnCount();
                        jTableGebinde.setModel( new DefaultTableModel(0, cols) );
                }
  }

  /**
   * Wird ausgef�hrt, wenn Der Button "LKW hinzuf�gen" gedr�ckt wird
   * @param e
   */
  void jButtonLkwErzeugen_actionPerformed(ActionEvent e) {

    //Ermittelt die vorhandene Zahl an Eintr�gen in der Tabelle und f�gt einen leeren Eintrag am Ende hinzu.
    if( jTableLkw.getModel() instanceof DefaultTableModel )
    {
        int cols = jTableLkw.getModel().getColumnCount();
        ((DefaultTableModel)jTableLkw.getModel()).addRow(new Object[cols]);
        int rows = jTableLkw.getModel().getRowCount();
        jTableLkw.getModel().setValueAt(String.valueOf(rows), rows-1 , 0 );

    }
  }

  /**
   * Wird ausgef�hrt, wenn Der Button "Gebinde hinzuf�gen" gedr�ckt wird
   * @param e
   */
  void jButtonGebindeErzeugen_actionPerformed(ActionEvent e) {

    //Ermittelt die vorhandene Zahl an Eintr�gen in der Tabelle und f�gt einen leeren Eintrag am Ende hinzu.
    if( jTableGebinde.getModel() instanceof DefaultTableModel )
    {
        int cols = jTableGebinde.getModel().getColumnCount();
        ((DefaultTableModel)jTableGebinde.getModel()).addRow(new Object[cols]);
        int rows = jTableGebinde.getModel().getRowCount();
        jTableGebinde.getModel().setValueAt(String.valueOf(rows), rows-1 , 0 );

    }
  }

  /**
   * Wird ausgef�hrt, wenn der Button "Markierten LKW l�schen" gedr�ckt wird
   * @param e
   */
  void jButtonLkwLoeschen_actionPerformed(ActionEvent e) {

    if( jTableLkw.getModel() instanceof DefaultTableModel )
    {
      //Ermittelt den zu l�schenden Eintrag
      int del = jTableLkw.getSelectedRow();
      //Eintrag wird gel�scht
      ((DefaultTableModel)jTableLkw.getModel()).removeRow(del);

      //Vorhandene Eintr�ge werden neu durchnummeriert
      int rows = jTableLkw.getModel().getRowCount();
      for (int i=0; i < rows; i++)
      {
        jTableLkw.getModel().setValueAt(String.valueOf(i+1), i , 0 );

      }
    }
  }

  /**
   * Wird ausgef�hrt, wenn der Button "Markiertes Gebinde l�schen" gedr�ckt wird
   * @param e
   */
  void jButtonGebindeLoeschen_actionPerformed(ActionEvent e) {

    if( jTableGebinde.getModel() instanceof DefaultTableModel )
    {
      //Ermittelt den zu l�schenden Eintrag
      int del = jTableGebinde.getSelectedRow();
      //Eintrag wird gel�scht
      ((DefaultTableModel)jTableGebinde.getModel()).removeRow(del);

      //Vorhandene Eintr�ge werden neu durchnummeriert
      int rows = jTableGebinde.getModel().getRowCount();
      for (int i=0; i < rows; i++)
      {
        jTableGebinde.getModel().setValueAt(String.valueOf(i+1), i , 0 );

      }
    }

  }

  /**
  * Diese Methode wird ausgef�hrt nach Datei | Speichern action performed
  * @param e
   */
  void jMenuDateiSpeichern_actionPerformed(ActionEvent e) {
    Speichern sp = new Speichern( jTableLkw.getModel(), jTableGebinde.getModel() );
    sp.speichernObjekt( selFile );
  }

  /**
  * Diese Methode wird ausgef�hrt nach Datei | Oeffnen action performed
  * @param e
   */
  void jMenuDateiOeffnen_actionPerformed(ActionEvent e) {
    Oeffnen of = new Oeffnen(this);

    //Ein FileChooser Dialog wird konfiguriert und angezeigt
//    String filename = File.separator + "bop";
    String filename = "Daten";

    JFileChooser fc = new JFileChooser(new File(filename));

    BopFilter myFilter = new BopFilter();
    fc.addChoosableFileFilter(myFilter);

    fc.showOpenDialog(new JFrame());
    File oeffneFile = fc.getSelectedFile();
    of.oeffneObjekt( oeffneFile );

   }

   /**
    * Get-Methode der Membervariable jTableLkw
    * @return
    */
   public JTable  getJTableLkw() {

     return jTableLkw;
   }

   /**
    * Get-Methode der Membervariable jTableLkw
    * @return
    */
   public JTable getJTableGebinde(){

     return jTableGebinde;
   }


   /**
   * Diese Methode wird ausgef�hrt nach Datei | Speichern unter action performed
   * @param e
   */
   void jMenuDateiSpeichernUnter(ActionEvent e) {

    //das Flag f�r "Bereits gespeichert" wird auf wahr gesetzt
    bereitsGespeichert = true;

    //FileChooser anzeigen
    JFileChooser fc = new JFileChooser();

    //nur .bop-Dateien anzeigen
    BopFilter myFilter = new BopFilter();
    fc.addChoosableFileFilter(myFilter);

    fc.showSaveDialog(new JFrame());
    selFile = fc.getSelectedFile();

    //wenn die Datei noch nicht mti .bop endet h�nge .bop an.
    if(!selFile.getName().endsWith(".bop"))
    {
      String tmp = selFile.getAbsolutePath() + ".bop";
      selFile = new File(tmp);
    }

    Speichern sp = new Speichern( jTableLkw.getModel(), jTableGebinde.getModel());
    sp.speichernObjekt(selFile);
    jMenuDateiSpeichern.setEnabled(true);

  }

  /**
  * Diese Methode wird ausgef�hrt nach Bearbeiten | Solverpfade action performed
  * @param e
   */
  void jMenuSolverPfade_actionPerformed(ActionEvent e) {
    Dimension dlgSize = seb.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    seb.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    seb.setModal(true);
    seb.pack();
    seb.show();

  }


  /**
   * Wird aufgerufen, nach dem der Men�-Eintrag Bearbeiten | Berechnen gew�hlt wurde
   * @param e
   */
  void jMenuBearbeitenBerechnen_actionPerformed(ActionEvent e) {
    //weiterreichen an n�chsten Event-Listener
    jButtonGebindeBerechnen_actionPerformed(e);
  }


  /**
   * Wird aufgerufen nach dem Dr�cken des "Berechnen"-Buttons
   * @param e
   */
  void jButtonGebindeBerechnen_actionPerformed(ActionEvent e) {
    Berechnung myBerechnung = new Berechnung(jTableLkw.getModel(),jTableGebinde.getModel(), seb.getSolverPfadLp());

    int pruefWert = myBerechnung.pruefeDaten();
    if(pruefWert == 1)
    {
      JOptionPane.showMessageDialog(null, "Eingabedaten scheinen fehlerhaft. Sind die Angaben in mm?", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
    }

    int returnValue=myBerechnung.berechne();
    if(returnValue== 0){
      System.out.println("Ergebnis ok. Status 0");
      showErgebnis(myBerechnung.parseErgebnis(), myBerechnung.getVarianten());
    }
    else if(returnValue== 3) {
     System.out.println("Ergebnis nicht ok. Status 3");
      showErgebnis(myBerechnung.parseErgebnis(), myBerechnung.getVarianten());
      JOptionPane.showMessageDialog(null, "Problem nicht l�sbar!", "Fehler", JOptionPane.ERROR_MESSAGE);
    }
    else
    {
      System.out.println("Das war nicht vorgesehen");
    }
  }



  /**
   * Zeigt das Ergebnis, welches der Solver liefert an.
   * @param ergebnis
   * @param varianten
   */
  void showErgebnis(ArrayList ergebnis, ArrayList varianten){
    String[] tabellenKopfErgebnis = new String[jTableGebinde.getModel().getRowCount()+2];
    tabellenKopfErgebnis[0] = "LKW-Typ";
    tabellenKopfErgebnis[1] = "Anzahl";
    for(int i=1; i<=jTableGebinde.getModel().getRowCount(); i++) {
      tabellenKopfErgebnis[i+1] = "Gebinde "+i;
    }
    //String[][] tabellenObjektErgebnis = new String[jTableGebinde.getModel().getRowCount()+2][0];
    String[][] tabellenObjektErgebnis = {{"","","","",""}};

    jTableErgebnis.setModel(new javax.swing.table.DefaultTableModel(tabellenObjektErgebnis, tabellenKopfErgebnis));
    for(int y=0; y<jTableErgebnis.getModel().getRowCount();y++) {
      ((DefaultTableModel)jTableErgebnis.getModel()).removeRow(y);
    }
    for(int j=1; j < ergebnis.size(); j++)
    {
      String[] dataRow = new String[jTableGebinde.getModel().getRowCount()+2];
      String temp = String.valueOf(ergebnis.get(j));
      int[] resultat = (int[])varianten.get(Integer.parseInt(temp.substring(1,5).trim())-1);
      dataRow[0]= String.valueOf(resultat[0]); //LKW Typ
      dataRow[1]= temp.substring(temp.length()-4,temp.length()); //Anzahl des Typs
      for(int k=1; k<=jTableGebinde.getModel().getRowCount(); k++) {
        dataRow[k+1]= String.valueOf(resultat[k]); //Varianten

      }
      ((DefaultTableModel)jTableErgebnis.getModel()).addRow(dataRow);
    }

    jTabbedPane1.setSelectedIndex(2);
    jTabbedPane1.setEnabledAt(2, true);
    jMenuAnsichtErgebnis.setEnabled(true);
    jMenuBearbeitenGrafikErzeugen.setEnabled(false);


}

/**
 * Wird aufgerufen, wenn der Button zum �ndern der Eingabedaten gedr�ckt wird
 * @param e
 */
  void jButtonEingabeDatenAendern_actionPerformed(ActionEvent e) {

    jTabbedPane1.setSelectedIndex(0);

  }

  /**
   * Wird aufgerufen, wenn der Button "Grafik" gedr�ckt wird
   * @param e
   */
  void jButtonGrafik_actionPerformed(ActionEvent e) {

    jTabbedPane1.setEnabledAt(3, true);
    jTabbedPane1.setSelectedIndex(3);
    jMenuAnsichtGrafik.setEnabled(true);
  }

  /**
 * Wird aufgerufen, wenn der Button zum �ndern der Eingabedaten gedr�ckt wird
 * @param e
 */
  void jButtonEingabeDatenAendern1_actionPerformed(ActionEvent e) {

    jTabbedPane1.setSelectedIndex(0);
  }

  /**
   * Wird aufgerufen, wenn der "Beenden" - Button gedr�ckt wird
   * @param e
   */
  void jButtonBeenden_actionPerformed(ActionEvent e) {

    System.exit(0);
  }



  /**
   * Wird aufgerufen, nach dem der Men�-Eintrag "Grafik erzeugen" ausgew�hlt wurde
   * @param e
   */
  void jMenuBearbeitenGrafikErzeugen_actionPerformed(ActionEvent e) {

    jTabbedPane1.setEnabledAt(3, true);
    jTabbedPane1.setSelectedIndex(3);
    jMenuAnsichtGrafik.setEnabled(true);

  }

  /**
  * Wird ausgef�hrt, wenn der Button "Zur�cksetzten" auf der Seite "Eingabe der LKW-Daten" gedr�ckt wird
  * @param e
  */
  void jButtonZuruecksetzenLkw_actionPerformed(ActionEvent e) {

    if( jTableLkw.getModel() instanceof DefaultTableModel )
                {
                        ((DefaultTableModel)jTableLkw.getModel()).setNumRows(0);
                }
                else
                {
                        int cols = jTableLkw.getModel().getColumnCount();
                        jTableLkw.setModel( new DefaultTableModel(0, cols) );
                }


  }
}
