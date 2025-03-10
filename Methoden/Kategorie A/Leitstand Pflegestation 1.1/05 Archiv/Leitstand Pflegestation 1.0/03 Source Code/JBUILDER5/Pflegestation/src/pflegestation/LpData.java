package pflegestation;
import java.util.Vector;
import pflegestation.*;
import java.sql.Time;



/**
 * <p>Title: Leitstand Pflegestation</p>
 * <p>Description: Optimiert die Pflegevorg�nge einer Krankenhausstation</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: FH Konstanz</p>
 * @author Sebastian Hagen, Jonathan Feuerstein, Birgit Engler
 * @version 1.0
 */

public class LpData
{
    Vector vectorWorkCountUnit=new Vector();
    int m_intIntervall = 30;
    int m_intIntervallCount = 48;
    int m_intM = 10000;
    Vector vectorWorkingStep = new Vector();
    int m_intCountSwitchVariable = 0;//Anzahl Schaltervariablen
    int m_intCountVariable = 0;//Anzahl normale Variablen, 48*Anzahl T�tigkeiten
    int m_intGoalVariable = 0; // Anzahl Goal Variable
    int m_intCountAllVariable = 0;//Anzahl aller Variablen
    Vector vectorNeed;

    String data="";
    Vector vectorData=new Vector();
    String[][] matrix;
    String semikolon=";\n ";
    String plus=" + ";
    String moreEqual=" >= ";
    String Equal=" = ";
    String LessEqual = " <= ";
    String min="min:  ";
    String target ="";
    Vector restrictions=new Vector();

    public LpData(Vector in_vectorWorkingStep)
    {
        vectorWorkingStep=in_vectorWorkingStep;
        m_intCountSwitchVariable = vectorWorkingStep.size();// Anzahl Schaltervariablen
        m_intCountVariable = m_intIntervallCount*vectorWorkingStep.size(); // Anzahl normale Variablen, 48*Anzahl T�tigkeiten
        m_intGoalVariable = 1; // Anzahl Goal Variable
        m_intCountAllVariable = m_intCountSwitchVariable+m_intCountVariable+m_intGoalVariable;


        this.iniMatrix();
        this.setMatrix();
        this.printMatrix();
        this.setUnequation();
        this.setData();
        this.printData();
    }// Ende Konstruktor

    public void iniMatrix()
    {
        try{
            int maxMatrix_col=0;



            for(int i=0;i<m_intCountAllVariable;i++)
            {
                maxMatrix_col++;
            }
            maxMatrix_col+=2;
            int maxMatrix_row=(vectorWorkingStep.size()*5+m_intIntervallCount)+2;
            matrix=new String[maxMatrix_row][maxMatrix_col];
            for(int row=0;row<maxMatrix_row;row++){
                for(int col=0;col<maxMatrix_col;col++){
                    matrix[row][col]=new String("0");
                }
            }
        }
        catch(Exception e)
        {

        }
    }
    public void setMatrix()
    {
        //set letzte zwei Zeilen
        int positionCol=0;

        try{
            for(int i=0;i<m_intCountAllVariable;i++)
            {
                    matrix[matrix.length-2][positionCol]=String.valueOf(i+1);//setze Nr
                    matrix[matrix.length-1][positionCol]=String.valueOf("x"+(positionCol+1));//set Variable X
                    positionCol++;

            }//Ende erster Zeile

            //setze letzte zwei Spalten
            int intTmpRow = vectorWorkingStep.size()*5+m_intIntervallCount;
            System.out.println(intTmpRow);
            positionCol=matrix[0].length-2;
            for(int i=0;i<intTmpRow;i++)
            {
                System.out.println("Z�hler1"+i);
                // = Restriktionen
                if(i<vectorWorkingStep.size())
                {
                    matrix[i][positionCol+1]="T�tigkeit"+i;
                    matrix[i][positionCol]=((WorkingStep)vectorWorkingStep.get(i%vectorWorkingStep.size())).getDuration();
                }
                else
                // >= Restriktionen 1.Block mit -M im B Vektor
                if(i<vectorWorkingStep.size()*2 && i>=vectorWorkingStep.size())
                {
                    //int j=i-vectorWorkingStep.size();
                    matrix[i][positionCol+1]="T�tigkeit"+i%vectorWorkingStep.size();
                    String strTmp = ((WorkingStep)vectorWorkingStep.elementAt(i%vectorWorkingStep.size())).getDuration();
                    int intTmp2 = Integer.parseInt(strTmp);
                    int intTmp = intTmp2-m_intM;
                    matrix[i][positionCol]=String.valueOf(intTmp);
                }
                else
                // >= Restriktionen 2.Block
                if(i<vectorWorkingStep.size()*3&& i>=vectorWorkingStep.size()*2)
                {
                    //int j=i-vectorWorkingStep.size()*2;
                    matrix[i][positionCol+1]="T�tigkeit"+i%vectorWorkingStep.size();
                    matrix[i][positionCol]=((WorkingStep)vectorWorkingStep.get(i%vectorWorkingStep.size())).getDuration();
                }
                else
                // SchalterVariable >= 0
                if(i<vectorWorkingStep.size()*4&& i>=vectorWorkingStep.size()*3)
                {
                    //int j=i-vectorWorkingStep.size()*3;
                    matrix[i][positionCol+1]="T�tigkeit"+i%vectorWorkingStep.size()+" Schalter";
                    matrix[i][positionCol]="0";
                }
                // SchalterVariable <= 1
                if(i<vectorWorkingStep.size()*5&& i>=vectorWorkingStep.size()*4)
                {
                    //int j=i-vectorWorkingStep.size()*4;
                    matrix[i][positionCol+1]="T�tigkeit"+i%vectorWorkingStep.size()+" Schalter";
                    matrix[i][positionCol]="1";
                }
                // Goal Programmierung hier letzten 3 Spaltn
                else
                if(i<vectorWorkingStep.size()*5+m_intIntervallCount && i>=vectorWorkingStep.size()*5)
                {
                    //Goal Variable Y
                    matrix[i][positionCol-1]="-1";

                    matrix[i][positionCol+1]="Periode"+(i-5*vectorWorkingStep.size());
                    matrix[i][positionCol]="0";
                }
                System.out.println("Z�hler"+i);
            }//Ende letzte Spalt

            //setze Rest der Matrix
            // T�tigkeiten
            int intWorkCount = 0;
            for(int i=0;i<vectorWorkingStep.size();i++)
            {//zeilenweise
                WorkingStep objWorkingStep = (WorkingStep)vectorWorkingStep.elementAt(i);
                int intPeriodeStart = (Integer.parseInt(objWorkingStep.getRegularStartHour())*60+Integer.parseInt(objWorkingStep.getRegularStartMinute()))/30+1;
                int intPeriodeEnd =   (Integer.parseInt(objWorkingStep.getRegularEndHour())*60+Integer.parseInt(objWorkingStep.getRegularEndMinute()))/30+1;

                int intPeriodeStartAlt = (Integer.parseInt(objWorkingStep.getAlternativeStartHour())*60+Integer.parseInt(objWorkingStep.getAlternativeEndMinute()))/30+1;
                int intPeriodeEndAlt = (Integer.parseInt(objWorkingStep.getAlternativeEndHour())*60+Integer.parseInt(objWorkingStep.getAlternativeEndMinute()))/30+1;
                int intAktPeriod = 0;

                for(int j=0;j<m_intCountVariable;j++)
                {
                    intAktPeriod++;

                    if(intAktPeriod >= intPeriodeStart && intAktPeriod < intPeriodeEnd)
                    {
                            matrix[i][j+intWorkCount*m_intIntervallCount]="1";
                    }
                    else
                    if(intAktPeriod >= intPeriodeStartAlt && intAktPeriod < intPeriodeEndAlt)
                    {
                            matrix[i][j+intWorkCount*m_intIntervallCount]="1";
                    }
                }
                intWorkCount++;
            }
            // Schaltervariablen >= Restriktionen 1.Block mit -M im B Vektor
            intWorkCount = 0;
            for(int i=vectorWorkingStep.size();i<vectorWorkingStep.size()*2;i++)
            {//zeilenweise
                int intCountSwitch = 0;
                WorkingStep objWorkingStep = (WorkingStep)vectorWorkingStep.elementAt(i%vectorWorkingStep.size());
                int intPeriodeStart = (Integer.parseInt(objWorkingStep.getRegularStartHour())*60+Integer.parseInt(objWorkingStep.getRegularStartMinute()))/30+1;
                int intPeriodeEnd =   (Integer.parseInt(objWorkingStep.getRegularEndHour())*60+Integer.parseInt(objWorkingStep.getRegularEndMinute()))/30+1;

                int intAktPeriod = 0;
                boolean blnSwitch = false;
                for(int j=0;j<m_intCountAllVariable+m_intCountSwitchVariable;j++)
                {

                     intAktPeriod++;

                    if(j>=m_intCountVariable+intWorkCount && j<m_intCountAllVariable)
                    {
                        if(blnSwitch == false)
                        {
                            matrix[i][j]=String.valueOf(-this.m_intM);
                            blnSwitch = true;
                        }
                    }
                    else
                    if(intAktPeriod >= intPeriodeStart && intAktPeriod < intPeriodeEnd)
                    {
                            matrix[i][j+intWorkCount*m_intIntervallCount]="1";
                    }
                }
                intWorkCount++;
            }
            // Schaltervariablen >= Restriktionen 2.Block mit +M
            intWorkCount = 0;
            for(int i=vectorWorkingStep.size()*2;i<vectorWorkingStep.size()*3;i++)
            {//zeilenweise
                int intCountSwitch = 0;
                WorkingStep objWorkingStep = (WorkingStep)vectorWorkingStep.elementAt(i%vectorWorkingStep.size());
                int intPeriodeStart = (Integer.parseInt(objWorkingStep.getAlternativeStartHour())*60+Integer.parseInt(objWorkingStep.getAlternativeStartMinute()))/30+1;
                int intPeriodeEnd =   (Integer.parseInt(objWorkingStep.getAlternativeEndHour())*60+Integer.parseInt(objWorkingStep.getAlternativeEndMinute()))/30+1;

                int intAktPeriod = 0;
                boolean blnSwitch = false;
                for(int j=0;j<m_intCountAllVariable+m_intCountSwitchVariable;j++)
                {

                     intAktPeriod++;

                    if(j>=m_intCountVariable+intWorkCount && j<m_intCountAllVariable)
                    {
                        if(blnSwitch == false)
                        {
                            matrix[i][j]=String.valueOf(this.m_intM);
                            blnSwitch = true;
                        }
                    }
                    else
                    if(intAktPeriod >= intPeriodeStart && intAktPeriod < intPeriodeEnd)
                    {
                            matrix[i][j+intWorkCount*m_intIntervallCount]="1";
                    }
                }
                intWorkCount++;
            }
            // // SchalterVariable >= 0
            intWorkCount = 0;
            for(int i=vectorWorkingStep.size()*3;i<vectorWorkingStep.size()*4;i++)
            {//zeilenweise
                boolean blnSwitch = false;
                for(int j=m_intCountVariable;j<m_intCountAllVariable;j++)
                {
                     if(j>=m_intCountVariable+intWorkCount && j<m_intCountAllVariable)
                    {
                        if(blnSwitch == false)
                        {
                            matrix[i][j]="1";
                            blnSwitch = true;
                        }
                    }
                }
                intWorkCount++;
            }
            // SchalterVariable <= 1
             intWorkCount = 0;
            for(int i=vectorWorkingStep.size()*4;i<vectorWorkingStep.size()*5;i++)
            {//zeilenweise
                boolean blnSwitch = false;
                for(int j=m_intCountVariable;j<m_intCountAllVariable;j++)
                {
                     if(j>=m_intCountVariable+intWorkCount && j<m_intCountAllVariable)
                    {
                        if(blnSwitch == false)
                        {
                            matrix[i][j]="1";
                            blnSwitch = true;
                        }
                    }
                }
                intWorkCount++;
            }
            // Goal Programmierung Variable Y
            int intAktPeriod = 0;
            for(int j=0;j<this.m_intCountVariable;j++)
            {//spaltenweise
                //int intCountSwitch = 0;
                intAktPeriod=j%m_intIntervallCount+1;
                WorkingStep objWorkingStep = (WorkingStep)vectorWorkingStep.elementAt(j/m_intIntervallCount);
                int intPeriodeStart = (Integer.parseInt(objWorkingStep.getRegularStartHour())*60+Integer.parseInt(objWorkingStep.getRegularStartMinute()))/30+1;
                int intPeriodeEnd =   (Integer.parseInt(objWorkingStep.getRegularEndHour())*60+Integer.parseInt(objWorkingStep.getRegularEndMinute()))/30+1;

                int intPeriodeStartAlt = (Integer.parseInt(objWorkingStep.getAlternativeStartHour())*60+Integer.parseInt(objWorkingStep.getAlternativeStartMinute()))/30+1;
                int intPeriodeEndAlt = (Integer.parseInt(objWorkingStep.getAlternativeEndHour())*60+Integer.parseInt(objWorkingStep.getAlternativeEndMinute()))/30+1;
                for(int i=vectorWorkingStep.size()*5+intAktPeriod-1;i<vectorWorkingStep.size()*5+this.m_intIntervallCount;i++)
                {


                    if(intAktPeriod >= intPeriodeStart && intAktPeriod < intPeriodeEnd)
                    {
                            matrix[i][j]="1";
                            break;
                    }
                    if(intAktPeriod >= intPeriodeStartAlt && intAktPeriod < intPeriodeEndAlt)
                    {
                            matrix[i][j]="1";
                            break;
                    }
                }

            }

        }
        catch(Exception e)
        {

        }
    }//end funktion
    public void printMatrix()
    {
        System.out.println("******  Matrix ********");
        for(int i=0;i<matrix.length;i++)
        {
          String line="";
            for(int j=0;j<matrix[i].length;j++)
            {
                line+=" "+matrix[i][j];
            }
            System.out.println("Matrix "+i+" "+line);
        }
        System.out.println("\n\n");
    }

    public String getData()
    {
        return data;
    }

    public Vector getVectorData()
    {
        return vectorData;
    }
    public void setUnequation()
    {
        try{
            for(int i=0;i<matrix.length-2;i++)
            {
            String res="";
            boolean more=false;
            int bedarfItem=Integer.parseInt(matrix[i][matrix[i].length-2]);

                for(int j=0;j<matrix[i].length-2;j++)
                {
                    String xFactor=matrix[i][j];
                    if(!xFactor.equalsIgnoreCase("0"))
                    {
                        String x=matrix[matrix.length-1][j];

                        if(more==false)
                        {
                            res+=xFactor+x;
                            more=true;
                        }
                        else
                            res+=plus+xFactor+x;
                    }
                }
                 // = Restriktionen
                if(i<vectorWorkingStep.size())
                {
                    res+=Equal + bedarfItem;
                }
                else
                // >= Restriktionen 1.Block mit -M im B Vektor
                if(i<vectorWorkingStep.size()*2 && i>=vectorWorkingStep.size())
                {
                   res+=moreEqual + bedarfItem;
                }
                else
                // >= Restriktionen 2.Block
                if(i<vectorWorkingStep.size()*3&& i>=vectorWorkingStep.size()*2)
                {
                    res+=moreEqual + bedarfItem;
                }
                else
                // SchalterVariable >= 0
                if(i<vectorWorkingStep.size()*4&& i>=vectorWorkingStep.size()*3)
                {
                    res+=moreEqual + bedarfItem;
                }
                // SchalterVariable <= 1
                if(i<vectorWorkingStep.size()*5&& i>=vectorWorkingStep.size()*4)
                {
                  res+=LessEqual + bedarfItem;
                }
                // Goal Programmierung <=
                else
                if(i<vectorWorkingStep.size()*5+m_intIntervallCount && i>=vectorWorkingStep.size()*5)
                {
                    res+=LessEqual + bedarfItem;
                }

            if(!res.equals(""))
                restrictions.add(res);
            }
            target =min+"1"+matrix[matrix.length-1][matrix[matrix.length-1].length-3];
            // Ganzzahligkeit
            String strInt;
            for(int z = 0; z<matrix[0].length-2; z++)
            {
                strInt="int "+matrix[matrix.length-1][z];
                if(!strInt.equals(""))
                restrictions.add(strInt);
            }
        }catch(Exception e)
        {
            System.out.println("Execption");
        }
    }
    public void setData()
    {
        //data=target+semikolon;
        vectorData.add(new String(target+semikolon));
        for(int i=0; i<restrictions.size();i++)
        {
            //data+=(String)restrictions.elementAt(i)+semikolon;
            vectorData.add(new String(restrictions.elementAt(i)+semikolon));
        }
    }
    public void printData()
    {
        System.out.println("****** das Ziel & die Ungleichungen *********");
        for (int i=0; i<vectorData.size(); i++)
        {
            System.out.println((String)vectorData.elementAt(i));
        }

    }
    public Vector representSolution(Vector in_vectorStringSolution, Vector in_vectorStringSolutionVariable)
    {
        vectorNeed = new Vector();
        // initialisieren
        for (int i = 0; i<this.m_intIntervallCount; i++)
        {
            String strTmp = "0";
            vectorNeed.add(strTmp);
        }
        try
        {
            for (int z = 1; z < in_vectorStringSolution.size(); z++)
            {
               int intValuetmp = 0;

               double dblValue = Double.parseDouble((String)in_vectorStringSolution.elementAt(z));
                long lngValue = Math.round(dblValue);
                int intValue = Integer.parseInt(new Long(lngValue).toString());
                String strVariable = ((String)in_vectorStringSolutionVariable.elementAt(z)).substring(1,((String)in_vectorStringSolutionVariable.elementAt(z)).length());
                int intVariable = Integer.parseInt(strVariable);
                if(intVariable <= this.m_intCountVariable)
                {
                    //System.out.println(intVariable%m_intIntervallCount-1);
                    intValuetmp = Integer.parseInt((String)vectorNeed.elementAt(intVariable%m_intIntervallCount-1));
                    intValuetmp+= intValue;
                    vectorNeed.setElementAt(String.valueOf(intValuetmp),intVariable%m_intIntervallCount-1);
                }
            }
        }
        catch(Exception e)
        {
            //System.out.println("exception");
        }
        for(int i = 0;i<m_intIntervallCount;i++)
        {
            //System.out.println("Periode"+i+":"+(String)vectorNeed.elementAt(i));
        }
        return vectorNeed;
    }


}//ende Klasse

