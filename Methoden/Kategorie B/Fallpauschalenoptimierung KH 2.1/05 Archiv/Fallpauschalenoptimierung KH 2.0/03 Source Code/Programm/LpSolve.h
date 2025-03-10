
//-- Nutzen der Datei ------------------------------------------------//
// Datei erledigt folgende Aufgaben                                   //
// - schreiben in eine Datei                                          //
// - liefern der L�sung (d.h. auselesen der Ausgabedatei vom LP-Solve //
// - schreiben in einen String                                        //
// - lese aus einer Datei                                             //
//--------------------------------------------------------------------//


#ifndef LpSolveH
#define LpSolveH
#include <string>
#include <vector>
using namespace std;

class LPSolve
{
public:
        LPSolve();
        void schreibeInDatei(char *dateiName, string funktionen);
        string schreibeInString( char *dateiName );
        int lieferLoesung( string stringName );
     	int leseAusDatei( char *dateiName );

private:

};

//---------------------------------------------------------------------------
#endif
