package hotelbelegung;

import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Chart
{
//	KEY INPUT TO THIS CLASS:
	private JTable chartDataTab;		// contains raw data for chart - in at construction

//	THE KEY OUTPUT OF THIS CLASS:
	private ChartPanel chartPanel;		// A drawing of the chart based on the JTable

//	META DATA ABOUT JTABLE:
        private int numOfColumns;	// derived from # of columns in chartDataTab
        private int numOfSeries;	// derived from # of rows of chartDataTab
        private int numOfBars;
        private int numOfBarSeries;
        private int numOfSeriesBreaks;
        private int numOfBarSets;	// number of series to be shown as bars
        private int numOfLines;

//	DATA FROM JTABLE FOR CHART:
        private String[] colHeadings;	// derived from first row of chartDataTab
        private String[] rowTitles;	// derived from first column of chartDataTab
        private int rowTitleIndex;	// used to help fill rowTitles
        private double[] barTopLeftRefs;
        private boolean linesToChart;	// TO BE REMOVED
        private int[] areaRefs;
        private boolean areaToChart;
        private double[][] lineRefs;

//      DATA THAT DESCRIBES THE DRAWING OF THE CHART:
        private ChartColourScheme chartColors;	// passed in by constructor
        private boolean showSeriesLabs;	        // set - default is true
        private boolean showXAxisLabels;
        private boolean showYAxisLabels;
        private boolean showMinMaxLabels;
        private int chartPMax;		// maximum value for primary axis
        private int chartPMin;		// minimum value for primary axis
        private boolean frameBars;		// black line drawn around bars

//      DATA USED UNIVERSALLY FOR CALCULATIONS:
	private double minBuf;
	private double maxBuf;
	private Object objBuf;
	private String strBuf;
	private double longBuf;
	private String serTitBuf;

	public Chart(JTable tab, ChartColourScheme cols, int numBs, int numLs, boolean ar)
	{	// this constructor is passed a JTable and calculates values based on size
		// min/max values for chart are calculated and all elements are plotted on
		// the primary x-axis.
	this(tab, cols, numBs, numLs, ar, 0, 0, false, false);
	}

	public Chart(JTable tab, ChartColourScheme cols, int numBs, int numLs, boolean ar, int max, int min, boolean manMax, boolean label)
	{	// this constructor is used when the chart max and mins are set manually.

	showXAxisLabels = label;	// these should be set by the class instantiating
	showYAxisLabels = label;	// these should be set by the class instantiating
	showMinMaxLabels = true;	// chart - default is true.
	frameBars = true;

	chartDataTab = tab;
	chartColors = cols;

	initialiseMinMax();

	numOfLines = numLs;
	if (numOfLines > 0) linesToChart = true;
	areaToChart = ar;

	numOfBarSets = numBs;
// = number of series for bars

	numOfBarSeries = numBs;

	numOfSeries = numLs + numBs;		// here calculating the total number of bars,lines
	if(areaToChart) numOfSeries++;		// and areas that are to be drawn

	rowTitles = new String[numOfSeries];	// to store the names of the series to be charted
	rowTitleIndex = 0;

	numOfColumns = chartDataTab.getColumnCount()-1;		// the table contains an extra column
//	System.out.println("Num of Cols = " + numOfColumns);	// with the series titles

	Object objBuf;
	String strBuf;
	colHeadings = new String[numOfColumns];

        for (int cl = 0; cl < numOfColumns; cl++)	// set the column headings (row 1 of tab)
        {
		objBuf = chartDataTab.getValueAt(0, (cl+1));
		strBuf = objBuf.toString();
		colHeadings[cl] = strBuf;
        }

	setUpBarArray();			// current implementation neccessitates at least 1 bar set
	if (linesToChart) setUpLineArray();
	if (areaToChart) setUpAreaArray();

	if(manMax)
        {
		chartPMax = max;//(double)(max);
		chartPMin = min;//(double)(min);
        }
	else
        {
		chartPMax = (int) (maxBuf);
		chartPMin = (int) (minBuf);
        }

	showSeriesLabs = true;

	}

	public String getSeriesName(int a)	// for calculations by ChartPanel object
	{
		return rowTitles[a];
	}

	public int getNumberOfSeries()		// for calculations by ChartPanel object
	{
		return numOfSeries;
	}

	public boolean showSeriesLabels()
	{
		return showSeriesLabs;
	}

	public boolean drawBarFrames()
	{
		return frameBars;
	}

	public void setSeriesLabels(boolean vis)// this property can be set without needing to reset
	{					// the draw values - hence not passed in constructor
		showSeriesLabs = vis;		// would be modified in some GUI
	}

	public String getColHeading(int a)	// for calculations by ChartPanel object
	{
		return colHeadings[a];
	}

        public String getColName(int a)
        {
                return chartDataTab.getColumnName(a);
        }

	public ChartPanel getPanel()
	{
		chartPanel = new ChartPanel(chartColors, this);
		return chartPanel;
	}

        // Zusatz f�r Aufruf der graphischen Buchungsanzeige
	public ChartPanel getPanel(int buchung, int dauer, String labelAlt, String labelBuch)
	{
		chartPanel = new ChartPanel(chartColors, this, buchung, dauer,labelAlt, labelBuch);
		return chartPanel;
	}

	public boolean areaForChart()
	{
		return areaToChart;
	}

	public int getAreaRef(int y)		// for calculations by ChartPanel object
	{
		return areaRefs[y];
	}

	public double getLineRef(int a, int b)	// for calculations by ChartPanel object
	{
		return lineRefs[a][b];
	}

	public int getNumOfLines()		// for calculations by ChartPanel object
	{
		return numOfLines;
	}

	public boolean linesForChart()		// returns true if the chart requires lines.
	{
		return linesToChart;
	}

	public double getBarRef(int a)		// for calculations by ChartPanel object
	{
		if (a < numOfBars) return barTopLeftRefs[a];
		else return 0;
	}

	public int getNumOfBars()		// for calculations by ChartPanel object
	{
		return numOfBars;
	}

	public int getNumOfCols()		// for calculations by ChartPanel object
	{
		return numOfColumns;
	}

	public int getNumOfBarSeries()		// for calculations by ChartPanel object
	{
		return numOfBarSeries;
	}

	public boolean showXLabels()
	{
		return showXAxisLabels;
	}

        public boolean showYLabels()
	{
		return showYAxisLabels;
	}

	public boolean showMaxLabels()
	{
		return showMinMaxLabels;
	}

	public String returnChartMax()		// for calculations by ChartPanel object
	{
		return ("" + chartPMax);
	}

	public String returnChartMin()		// for calculations by ChartPanel object
	{
		return ("" + chartPMin);
	}

	public double returnPrimaryRange()
	{
		if (chartPMax> chartPMin) return (chartPMax- chartPMin);
		else return (chartPMin- chartPMax);
	}

	public double returnPrimaryMin()
	{
		return chartPMin;
	}

	public void setUpAreaArray()
	{
	areaRefs = new int[numOfColumns+2];		// an extra point for starting point & returning
							// to starting point...

		for (int t = 0; t < numOfColumns+1; t++)
                {
			objBuf = chartDataTab.getValueAt((numOfBarSeries+numOfLines+1), (t));
			strBuf = objBuf.toString();

				if (t == 0)
				{
				serTitBuf = strBuf;
				rowTitles[rowTitleIndex] = strBuf;
				rowTitleIndex++;
				}

				else
				{
				longBuf = Double.parseDouble(strBuf);
				areaRefs[t] = (int)(longBuf);

					if(longBuf>maxBuf) maxBuf = longBuf;
					if(longBuf<minBuf) minBuf = longBuf;
				}
			}
		areaRefs[0] = areaRefs[1];		// y displacement of 1 point same as origin
		areaRefs[numOfColumns +1] = areaRefs[numOfColumns]; 	// to return to edge

	}

	public void initialiseMinMax()
	{
	objBuf = chartDataTab.getValueAt(1, 1);		// set up primary values for
	strBuf = objBuf.toString();				// minimum and maximum
	longBuf = Double.parseDouble(strBuf);
	minBuf = longBuf;
	maxBuf = longBuf;
	}

	public void setUpBarArray()
	{
	numOfBars = numOfBarSeries * numOfColumns;
	numOfSeriesBreaks = numOfBars/numOfBarSeries;

	barTopLeftRefs = new double[numOfBars];

	int cumRef = 0;

		for (int cb = 0; cb < numOfColumns+1; cb++)
		{
			for (int b = 0; b < numOfBarSeries; b++)
			{
			objBuf = chartDataTab.getValueAt((b+1), (cb));
			strBuf = objBuf.toString();

				if (cb == 0)
				{
				serTitBuf = strBuf;
				rowTitles[rowTitleIndex] = strBuf;
				rowTitleIndex++;
				}

				else
				{
				longBuf = Double.parseDouble(strBuf);
				barTopLeftRefs[cumRef] = (double)(longBuf);
				cumRef++;
					if(longBuf>maxBuf) maxBuf = longBuf;
					if(longBuf<minBuf) minBuf = longBuf;
				}
			}
		}
	}

	public void setUpLineArray()
	{
	lineRefs = new double[numOfLines][numOfColumns];

		for (int lc = numOfBarSeries; lc < (numOfBarSeries + numOfLines); lc++)
		{
			for (int lb = 0; lb < numOfColumns+1; lb++)
			{
			objBuf = chartDataTab.getValueAt((lc+1), (lb));
			strBuf = objBuf.toString();

				if (lb == 0)
				{
				serTitBuf = strBuf;
				rowTitles[rowTitleIndex] = strBuf;
				rowTitleIndex++;
				}

				else
				{
				longBuf = Double.parseDouble(strBuf);		// called longBuf because
				lineRefs[lc-numOfBarSeries][lb-1] = longBuf;	//was originally type long

					if(longBuf>maxBuf) maxBuf = longBuf;
					if(longBuf<minBuf) minBuf = longBuf;
				}
			}
		}
	}

  public Chart() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
  }
}


class ChartPanel extends JPanel
{	// this panel draws an image of the chart object passed to it... it is itself the key output.

//	KEY INPUTS TO THIS CLASS:
	ChartColourScheme chartColors;	// colour scheme object to allow different colours easily
	Chart theChart;			// Contains all the data required for the paint(g)

//	DATA FROM CHART:
	int[] xAreaPoints;	// the x-co-ords of points used to draw area graph (if required)
	int numOfBars;
	int numOfBarSeries;
	int numOfBarSets;	// the # of series along the x-axis (therefore # of labels)
	double pRange; 		// max - min of values on primary axis.
	double pMin;
// all other data is retrieved from Chart when required.

//	CALCULATED FIELDS:
	int panelHeight;	// used to dynamically resize the chart everytime paint(g) is called
	int panelWidth;
	int barGap;		// the inter-object gap that is used throughout
	int yIndent;		// depends on panel height - used for y co-ord adjustments
	int xIndent;		// depends on panel width - used for x co-ord adjustments
	int xRightIndent;	// to allow for more space on RHS if a secondary axis is required
	int currentFontSize;	// for the purposes of adjusting the font if it is too big
	int drawnBars;
	int chartHeight;
	int chartWidth;
	int barWidth;
	int extraBarGap;

//      Parameter f�r farblich hinterlegte Anzeige der Buchung und Beschriftung
        int buchung;
        int dauer;
        String labelAlternative;
        String labelBuchung;
        boolean schalter = false;
        int highBelegung;

//	FORMATTING (NON CALCULATED) VARIABLES:
	boolean frameBars;				// true = draw line around bars

//	THE GRAPHICS OBJECT IS DECLARED GLOBALLY TO ALLOW DRAW SUBROUTINES:
	Graphics g;
        JScrollPane jScrollPane1 = new JScrollPane();

	public ChartPanel(ChartColourScheme col, Chart ch)
	{
	        chartColors = col;
	        theChart = ch;
		numOfBars = theChart.getNumOfBars();
		numOfBarSeries = theChart.getNumOfBarSeries();
		numOfBarSets = theChart.getNumOfCols();
	}

	public ChartPanel(ChartColourScheme col, Chart ch, int buch, int dau, String labelAlt, String labelBuch)
	{
	        chartColors = col;
	        theChart = ch;
		numOfBars = theChart.getNumOfBars();
		numOfBarSeries = theChart.getNumOfBarSeries();
		numOfBarSets = theChart.getNumOfCols();
                buchung = buch;
                dauer = dau;
                labelAlternative = labelAlt;
                labelBuchung = labelBuch;
                schalter = true;
	}

	public void paint(Graphics gr)
	{
	g = gr;
	drawnBars = 0;
	frameBars = true;		// TEMP - look to chart object for this value
	pRange = theChart.returnPrimaryRange();
	pMin = theChart.returnPrimaryMin();

	panelHeight = this.getHeight();
	panelWidth = this.getWidth();
	setGaps(panelHeight, panelWidth);		// determine gaps depending on size of panel

	chartHeight = panelHeight - 2*yIndent;			// determing the height & width of the piece
	chartWidth = panelWidth - (xIndent + xRightIndent);	// of the panel we draw the chart in.
	extraBarGap = 2*barGap;	// also need to change below...
	barWidth = (int)((chartWidth - ((numOfBarSets-1) * extraBarGap))/numOfBars);

	preparePanel();
	drawPrimaryAxis();
	if (theChart.areaForChart()) drawArea();
	drawBars();
	if (theChart.showXLabels()) drawXAxisLabels();
        if (theChart.showYLabels()) drawYAxisLabels();
	if (theChart.showMaxLabels()) drawMaxMinLabels();
	if (theChart.linesForChart()) drawTheLines();
//	if (theChart.showSeriesLabels()) drawSeriesLabels();
	}

	public void calculateXAreaPoints(int barW)
	{
		int xInc = (numOfBarSeries * barW) + 2*barGap;
		int xPos = xIndent - barGap + xInc/2;

		xAreaPoints[0] = xIndent;		// 1st two points are same regardless
		xAreaPoints[1] = xPos;				// we only need to set the last x-2

		for(int h = 1; h < numOfBarSets; h++)		// hence loops numofsets - 2.
		{
		xPos += xInc;
		xAreaPoints[h+1] = xPos;

		}

		xAreaPoints[numOfBarSets+1] = xAreaPoints[numOfBarSets] + xInc/2;
		xAreaPoints[numOfBarSets+2] = xIndent;
	}

	public Font getIdealFont(int c)			// c is an adjuster - minsued from calculated
	{						// font size

	int fontSize = getFontSize(c);

	Font fontBuf = new Font("Arial", Font.BOLD, fontSize);
	currentFontSize = fontSize;
	return fontBuf;
	}

	public int getFontSize(int y)
	{
	int fontS = 0;
//	int fontSizeBuf = 2 + (3*xIndent/numOfBarSets)*yIndent/30;

	int fontSizeBuf = barGap + 7;
	fontS = fontSizeBuf;


	if (fontSizeBuf<8)
	{
	fontS = 8;
	}
		else
		{
			if (fontSizeBuf>15)
			{
				fontS = 15;

			}

			else
			{
			fontS = fontSizeBuf;
			}
		}


	if(fontS > (yIndent/2)) fontS = yIndent/2-2;
	return (fontS-y);
	}

	public void setGaps(int hgt, int wdth)
	{
	double yIndentBuf = (0.2*hgt)/2;
	yIndent = (int)(yIndentBuf);
	double xIndentBuf = (0.15*wdth)/2;
	xIndent = (int)(xIndentBuf);

	double xIndentRightBuf = (0.05*wdth)/2;
	xRightIndent = (int)(xIndentRightBuf);

// the greater the number of bars to be drawn the smaller barGap needs to be...

	barGap = (int)(2*(xIndent/numOfBars) +1);

	if(barGap>6) barGap = 6;		// maximum gap is 6

	while(barGap>yIndent/4)		// use yIndent to modify gap
		{
		barGap += (-1);
		}
	}

	public void preparePanel()
	{
	g.clearRect(0,0,panelWidth, panelHeight);
	g.setColor(Color.white);
	g.fillRect(0,0,panelWidth,panelHeight);

	g.setColor(chartColors.getChartAreaColor());
	g.fillRect(barGap, barGap/2, panelWidth - 2*barGap, panelHeight - barGap);
	}

	public void drawPrimaryAxis()
	{
	g.setColor(chartColors.getAxisColor());
	g.drawLine(xIndent - barGap, yIndent, xIndent - barGap, panelHeight - yIndent + barGap);
	g.drawLine(xIndent - barGap, panelHeight - yIndent + barGap, panelWidth - xRightIndent + barGap, panelHeight - yIndent + barGap);
//	g.drawLine(panelWidth - xIndent + barGap, yIndent, panelWidth - xIndent + barGap, panelHeight - yIndent + barGap);
// now the axes have been drawn - secondary axis is drawn if required (check chart object)
	}

	public void drawArea()
	{
	g.setColor(chartColors.getAreaColor());
	int[] yAreaPoints = new int[numOfBarSets +3];		// to determine y-coords...
	double yAreaPtBuf;

		for (int d = 1; d < numOfBarSets+1; d++)
		{
		yAreaPtBuf = ((pRange-(theChart.getAreaRef(d)-pMin)))*chartHeight/pRange + yIndent;

		yAreaPoints[d] = (int)(yAreaPtBuf);
		}

		yAreaPoints[0] = panelHeight-yIndent + barGap/2;
		yAreaPoints[numOfBarSets+1] = yAreaPoints[0];
		yAreaPoints[numOfBarSets+2] = yAreaPoints[0];

	xAreaPoints = new int[numOfBarSets +3];
	calculateXAreaPoints(barWidth);
	g.fillPolygon(xAreaPoints, yAreaPoints, numOfBarSets+3);
	}

	public void drawBars()
	{
	int curBarSeries = 1;				// to determine which colour to use
	int curBarSet = 0;				// which block of columns are being drawn

	extraBarGap = 0;
        String labelText;
	int xDrawPoint = xIndent;		// records how far across the x-axis we have gone.
        int zaehler = 0;
        int tmpBuchung = buchung;
        int tmpxDrawPoint = 0;
        int fontLabel = 14 - (numOfBars/3);
        if (fontLabel < 9) {
          fontLabel = 9;
        }
        int addSpace = 5;
        if (numOfBars/3 == 1) {
          addSpace = 60;
        }
        else if (numOfBars/3 == 2) {
          addSpace = 20;
        }
        else if (numOfBars/3 == 3) {
          addSpace = 7;
        }

		for(int a = 0; a < numOfBars; a++)
		{
		double num = theChart.getBarRef(a);	// bar ref is a % scores showing how far up
		double yPerc = (double)((pRange-(num-pMin))/pRange*chartHeight);	// the axis the point is

		int yCoord = (int)(yPerc);	// this is converted into a proportion of chartheight

                // Drucke x-Achsen-Labels in Graphik
                g.setFont(new Font("Arial", Font.BOLD, fontLabel));
                labelText = theChart.getSeriesName(a);
                g.setColor(chartColors.getFontColor());
                g.drawString(labelText, xDrawPoint+addSpace, (panelHeight - (xIndent + barGap)/2));

                tmpxDrawPoint = xDrawPoint;
		xDrawPoint += extraBarGap;	// increment for the next bar to be drawn

                // Zusatz f�r Darstellung der Buchung
                if (schalter) {
                  if ((a == tmpBuchung) && (zaehler < dauer)) {
                    g.setColor(Color.red);
                    zaehler++;
                    tmpBuchung++;
                    if (zaehler == 1) {
                      highBelegung = xDrawPoint;
                    }
                  }
                  else {
		    g.setColor(Color.blue);
                  }
                }
                else {
                  g.setColor(chartColors.getSeriesColor(curBarSeries-1));
                }
	        g.fillRect(xDrawPoint, yCoord + yIndent, barWidth - barGap, (chartHeight - yCoord));

		if(theChart.drawBarFrames())
		{
		g.setColor(Color.black);
		g.drawRect(xDrawPoint, yCoord + yIndent, barWidth - barGap, (chartHeight - yCoord));
		}

		extraBarGap = 0;	// so that a larger gap appears between x-axis groups
		xDrawPoint += barWidth;

		drawnBars++;
		curBarSeries++;						// to get bar color
			if(curBarSeries>numOfBarSeries)			// implies new column
			{
			curBarSet++;					// new block of columns
			curBarSeries = 1;				// start with 1st color
			extraBarGap = 2*barGap;			// larger gap for 1st element of new group

			g.setColor(chartColors.getAxisColor());	// to draw line seperating x-axis groups

			if (!(curBarSet > numOfBarSets-1))
			g.drawLine(xDrawPoint + (int)(barGap/2), panelHeight - yIndent, xDrawPoint + (int)(3*barGap/2-barGap), panelHeight - yIndent + 2*barGap);
			}
		}
	}

	public void drawXAxisLabels()
	{
		int colLabelIndent = (int)(chartWidth/numOfBarSets);	// available gap for labels
		g.setFont(getIdealFont(0));		// determine ideal font size
		g.setColor(chartColors.getFontColor());

		int xTextInsert = xIndent;
		int xTextIncrease = (numOfBarSeries * barWidth) + 2*barGap;
		int yTextInsert = panelHeight - (yIndent + barGap)/2 + barGap + 10;

		for(int k = 0; k<numOfBarSets; k++)
		{
                // Zusatz f�r Darstellung der dynamischen Beschriftungen
                if (schalter) {
                  g.setColor(Color.blue);
	 	  g.drawString(theChart.getColName(1), xTextInsert, yTextInsert);
		}
                else {
                  g.drawString(theChart.getColHeading(k), xTextInsert, yTextInsert);
                }
		xTextInsert += xTextIncrease;
		}
	}

       	public void drawYAxisLabels()
	{
		int colLabelIndent = (int)(chartWidth/numOfBarSets);	// available gap for labels
		g.setFont(getIdealFont(0));		// determine ideal font size
		g.setColor(chartColors.getFontColor());

		int xTextInsert = highBelegung + 2;
		int xTextIncrease = (numOfBarSeries * barWidth) + 2*barGap;
		int yTextInsert = 2*yIndent/3;//panelHeight - (xIndent + barGap)/2;

		for(int k = 0; k<numOfBarSets; k++)
		{
                // Zusatz f�r Darstellung der dynamischen Beschriftungen
                if (schalter) {
                  g.setColor(Color.red);
	 	  g.drawString(theChart.getColName(0), xTextInsert, yTextInsert);
		}
                else {
                  g.drawString(theChart.getColHeading(k), xTextInsert, yTextInsert);
                }
                xTextInsert += xTextIncrease;
		}
	}

	public void drawMaxMinLabels()
	{
		String maxString = theChart.returnChartMax();
		int xJustIndentMax = 0;

		if (maxString.length()>4)
		{
		maxString = maxString.substring(0,4);
		}
		else
		{
		xJustIndentMax = ((4-maxString.length())*barGap);
		}

		String minString = theChart.returnChartMin();
		int xJustIndentMin = 0;

		if (minString.length()>4)
		{
		minString = minString.substring(0,4);
		}

		if (minString.length()<4)
		{
		xJustIndentMin = ((3-minString.length())*barGap);
		}
		g.setColor(chartColors.getAxisColor());

		g.setFont(getIdealFont(0));
		for(int adjust = 0; adjust<6; adjust++)
		{
			if((maxString.length()*3*currentFontSize/4)>(xIndent-2*barGap))
			{
			g.setFont(getIdealFont(adjust));
			}
		}

                g.drawString(maxString, xIndent - 2*barGap - (maxString.length()*2*currentFontSize/4) , yIndent + 2*barGap);
                g.drawString(minString, xIndent - 2*barGap - (minString.length()*2*currentFontSize/4) , chartHeight + yIndent);
//                g.drawString("test", xIndent - 2*barGap - (4*2*currentFontSize/4) , highBelegung);
	}

	public void drawTheLines()
	{
		double yBuf;
		double yPer;
		int yPos = 1;		// current point at which to draw
		int yLastPos = 0;	// these are to connect the line to the last point
		int xPos = 0;
		int xLastPos = 0;
		int xIncrease = (numOfBarSeries * barWidth) + 2*barGap;	// lines always drawn at

		for(int f = 0; f<theChart.getNumOfLines(); f++)		// centre of series
			{
			xPos = xIndent - barGap + xIncrease/2;
			xLastPos = xPos;
			g.setColor(chartColors.getLineColor(f));

			for(int h = 0; h < numOfBarSets; h++)
				{

				yBuf = theChart.getLineRef(f, h);
				yPer = (double)((pRange-(yBuf-pMin))/pRange*chartHeight);
				yPos = (int)(yPer);
// as is done for bars and areas the value stored in chart is extracted - then the % of the range
// that this value contains is calculated.  This % is then used to determine how far up the y-axis
// the line should go (i.e. the height of the line).

				if(h>0) xPos += xIncrease;

				if(!(xLastPos == xPos))	// won't try draw a point at beginning
                                {
			        g.drawLine(xLastPos-barGap/2+1, yLastPos+yIndent, xPos-barGap/2+1, yPos+yIndent);
			        g.drawLine(xLastPos-barGap/2, yLastPos+yIndent, xPos-barGap/2, yPos+yIndent);
			        g.fillOval(xLastPos-3*barGap/2 + 1, yLastPos+yIndent-barGap+1, 2*barGap, 2*barGap);
// this line draws the circular data point
                                }

				xLastPos = xPos;
				yLastPos = yPos;
			}

		g.fillOval(xLastPos-3*barGap/2 + 1, yLastPos+yIndent-barGap+1, 2*barGap, 2*barGap);
		}
	}

	public void drawSeriesLabels()
	{
		int numSeries = theChart.getNumberOfSeries();
		int seriesLabelInc = chartWidth/numSeries;
//		int labelInPoint = 2*barGap;
		int labelInPoint = xIndent - barGap;	// start labels flush with y-axis

		String labelText = "";
		int totChars = 0;
		String strBuf = "";
		int labFontSize;

		for (int y = 0; y < numSeries; y++)
		{
		strBuf = theChart.getSeriesName(y);
		totChars += strBuf.length() + 3;
		}

//		System.out.println("There are " + totChars + " characters.");
//		System.out.println("Available width = " + chartWidth);
		labFontSize = (int)(chartWidth/totChars/3*4)+1;
		if (labFontSize>14) labFontSize = 12;
		if (labFontSize<8) labFontSize = 7;
//		System.out.println("Calculated font = " + labFontSize);
                int zaehler = 0;
                int tmpBuchung = buchung;

			for(int q = 0; q < numSeries; q++)
			{
			labelInPoint += barGap/5;	// gap between series labels
			if(q<numOfBarSeries)
				{
                                // Zusatz f�r Darstellung der Buchung
                                if (schalter) {
                                  if ((q == tmpBuchung) && (zaehler < dauer)) {
                                    g.setColor(Color.red);
                                    zaehler++;
                                    tmpBuchung++;
                                  }
                                  else {
		                    g.setColor(Color.blue);
                                  }
                                }
                                else {
                                  g.setColor(chartColors.getSeriesColor(q));
                                }
			        g.fillRect(labelInPoint+barGap/2, 2*yIndent/3-2*barGap, 2*barGap, 2*barGap);
				}
			else
				if(theChart.areaForChart()&(q==(theChart.getNumberOfSeries()-1)))
					{
					g.setColor(chartColors.getAreaColor());
					int xBegin = labelInPoint+barGap/2;
					int xPts[] = new int[6];
					int yBegin = 2*yIndent/3;

					int yPts[] = new int[6];
					xPts[0] = xBegin;
					xPts[1] = xBegin + barGap/2;
					xPts[2] = xBegin + barGap;
					xPts[3] = xBegin + 2*barGap;
					xPts[4] = xPts[3];
					xPts[5] = xPts[0];

					yPts[0] = yBegin;
					yPts[1] = yBegin - barGap;
					yPts[2] = yBegin - barGap;
					yPts[3] = yBegin - 2*barGap;
					yPts[4] = yBegin;
					yPts[5] = yPts[0];

					g.fillPolygon(xPts, yPts, 6);
					}
				else
					{
					g.setColor(chartColors.getLineColor(q-numOfBarSeries));
                                        g.drawLine(labelInPoint+barGap/2,  2*yIndent/3-barGap, labelInPoint+barGap/2+2*barGap,  2*yIndent/3-barGap);
					}

//			g.setFont(getIdealFont(0));
			g.setFont(new Font("Arial", Font.BOLD, labFontSize));
			labelText = theChart.getSeriesName(q);
			seriesLabelInc = 2*labelText.length()*labFontSize/3;
			labelInPoint += 3*barGap;
			g.setColor(chartColors.getFontColor());
			g.drawString(labelText, labelInPoint, 2*yIndent/3);

			labelInPoint += seriesLabelInc;
		}
	}
}