package absf1;

/**
 * <p>Title: Oeffnen</p>
 * <p>Description: Hilfsklasse f�r das �ffnen einer Datei</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: FH Konstanz</p>
 * @author J�rgen Kambeitz
 * @version 1.0
 */

import java.io.*;
import java.util.*;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.beans.XMLDecoder;

public class Oeffnen {

  //Klassenattribute
  TableModel lkwModell;
  TableModel gebindeModell;
  HauptFrame hauptFrame;

  /**
   * Konstruktor der Klasse Oeffnen. Setzen des �bergebenen Parameters
   * @param hf
   */
  public Oeffnen( HauptFrame hf ) {
    hauptFrame = hf;

  }

  /**
   * Diese Methode dient zum �ffenen bereits gespeicherter Modelle. Sie erwartet einen Datei-Pfad, wo sich die zu
   * ladende Datei befindet.
   *
   * @param path - wo sich die zu �ffnende Datei befindet.
   */
  public void oeffneObjekt(File path) {
    lkwModell = (TableModel) hauptFrame.getJTableLkw().getModel();
    gebindeModell = (TableModel) hauptFrame.getJTableGebinde().getModel();
    try {
      if( path != null ) {

        // wieviele Spalten haben wir?
        int colsLKW = lkwModell.getColumnCount();
        int colsGebinde = gebindeModell.getColumnCount();

        // Variablen f�r das auslesen der Daten
        int rowsLKW = 0;
        int rowsGebinde = 0;
        String[][] valuesLKW = null;
        String[][] valuesGebinde = null;


        // Daten sind serialisert gespeichert
        FileInputStream file = new FileInputStream(path);
        ObjectInputStream input = new ObjectInputStream(file);

        try {
          //Anzahl Zeilen in den Tabellen auslesen
          rowsLKW = input.readInt();
          rowsGebinde = input.readInt();
          // Arrays erstellen
          valuesLKW = new String[rowsLKW][colsLKW];
          valuesGebinde = new String[rowsGebinde][colsGebinde];

          // serialisierte Array einlesen
          valuesLKW = (String[][]) input.readObject();
          valuesGebinde = (String[][]) input.readObject();
        }
        catch (ClassNotFoundException ex) {
          ex.printStackTrace();
        }
        catch (IOException ex) {
          ex.printStackTrace();
        }

        // zur�cksetzen der Tabellen
        ((DefaultTableModel) lkwModell).setNumRows(0);
        ((DefaultTableModel) gebindeModell).setNumRows(0);

        //richtige Anzahl an Zeilen erzeugen
        for (int i = 0; i < rowsLKW; i++) {
          ((DefaultTableModel) lkwModell).addRow(new Object[colsLKW]);
        }
        for (int i = 0; i < rowsGebinde; i++) {
          ((DefaultTableModel) gebindeModell).addRow(new Object[colsGebinde]);
        }

        //zur�ckschreiben der geladenen Daten
        for (int i = 0; i < rowsLKW; i++) {
          for (int j = 0; j < colsLKW; j++) {
            lkwModell.setValueAt(valuesLKW[i][j], i, j);
          }
        }

        for (int i = 0; i < rowsGebinde; i++) {
          for (int j = 0; j < colsGebinde; j++) {
            gebindeModell.setValueAt(valuesGebinde[i][j], i, j);
          }
        }
        input.close();
      }
    }
    catch( IOException ex ) {
      ex.printStackTrace();
    }
  }
}