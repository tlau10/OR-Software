========================================================================
       MICROSOFT FOUNDATION CLASS BIBLIOTHEK : FaBePlan
========================================================================


Diese FaBePlan-Anwendung hat der Klassen-Assistent f�r Sie erstellt. Diese Anwendung
zeigt nicht nur die prinzipielle Verwendung der Microsoft Foundation Classes, 
sondern dient auch als Ausgangspunkt f�r die Erstellung Ihrer eigenen DLLs.

Diese Datei enth�lt die Zusammenfassung der Bestandteile aller Dateien, die 
Ihre FaBePlan-Anwendung bilden.

FaBePlan.dsp
    Diese Datei (Projektdatei) enth�lt Informationen auf Projektebene und wird zur
    Erstellung eines einzelnen Projekts oder Teilprojekts verwendet. Andere Benutzer k�nnen
    die Projektdatei (.dsp) gemeinsam nutzen, sollten aber die Makefiles lokal exportieren.

FaBePlan.h
    Hierbei handelt es sich um die Haupt-Header-Datei der Anwendung. Diese enth�lt 
	andere projektspezifische Header (einschlie�lich Resource.h) und deklariert die
	Anwendungsklasse CFaBePlanApp.

FaBePlan.cpp
    Hierbei handelt es sich um die Haupt-Quellcodedatei der Anwendung. Diese enth�lt die
    Anwendungsklasse CFaBePlanApp.

FaBePlan.rc
	Hierbei handelt es sich um eine Auflistung aller Ressourcen von Microsoft Windows, die 
	vom Programm verwendet werden. Sie enth�lt die Symbole, Bitmaps und Cursors, die im 
	Unterverzeichnis RES abgelegt sind. Diese Datei l�sst sich direkt in Microsoft
	Visual C++ bearbeiten.

FaBePlan.clw
    Diese Datei enth�lt Informationen, die vom Klassen-Assistenten verwendet wird, um bestehende
    Klassen zu bearbeiten oder neue hinzuzuf�gen.  Der Klassen-Assistent verwendet diese Datei auch,
    um Informationen zu speichern, die zum Erstellen und Bearbeiten von Nachrichtentabellen und
    Dialogdatentabellen ben�tigt werden und um Prototyp-Member-Funktionen zu erstellen.

res\FaBePlan.ico
    Dies ist eine Symboldatei, die als Symbol f�r die Anwendung verwendet wird. Dieses 
	Symbol wird durch die Haupt-Ressourcendatei FaBePlan.rc eingebunden.

res\FaBePlan.rc2
    Diese Datei enth�lt Ressourcen, die nicht von Microsoft Visual C++ bearbeitet wurden.
	In diese Datei werden alle Ressourcen abgelegt, die vom Ressourcen-Editor nicht bearbeitet 
	werden k�nnen.



/////////////////////////////////////////////////////////////////////////////

F�r das Hauptfenster:

MainFrm.h, MainFrm.cpp
    	Diese Dateien enthalten die Frame-Klasse CMainFrame, die von
    	CFrameWnd abgeleitet wurde und alle SDI-Frame-Merkmale steuert.

res\Toolbar.bmp
    Diese Bitmap-Datei wird zum Erstellen unterteilter Bilder f�r die Symbolleiste verwendet.
    Die erste Symbol- und Statusleiste wird in der Klasse CMainFrame erstellt.
    Bearbeiten Sie diese Bitmap der Symbolleiste mit dem Ressourcen-Editor, und
    aktualisieren Sie IDR_MAINFRAME TOOLBAR in FaBePlan.rc, um Schaltfl�chen f�r die
    Symbolleiste hinzuzuf�gen.
/////////////////////////////////////////////////////////////////////////////

Der Klassen-Assistent erstellt einen Dokumenttyp und eine Ansicht(View):

FaBePlanDoc.h, FaBePlanDoc.cpp - das Dokument
    	Diese Dateien enthalten die Klasse CFaBePlanDoc. Bearbeiten Sie diese Dateien,
  	um Ihre speziellen Dokumentdaten hinzuzuf�gen und das Speichern und Laden von 
	Dateien zu implementieren (mit Hilfe von CFaBePlanDoc::Serialize).

FaBePlanView.h, FaBePlanView.cpp - die Ansicht des Dokuments
    	Diese Dateien enthalten die Klasse CFaBePlanView.
    	CFaBePlanView-Objekte werden verwendet, um CFaBePlanDoc-Objekte anzuzeigen.


/////////////////////////////////////////////////////////////////////////////

Hilfe-Unterst�tzung:

FaBePlan.hpj
    	Diese Datei ist die Hilfe-Projektdatei, die der Hilfe-Compiler f�r das 
	Erstellen der Hilfedatei Ihrer Anwendung verwendet.

hlp\*.bmp
    	Hierbei handelt es sich um Bitmap-Dateien, die f�r die Hilfethemen der 
	Standardbefehle der Microsoft Foundation Classes-Bibliothek erforderlich
	sind.

hlp\*.rtf
    	Diese Datei enth�lt die Hilfethemen f�r Standard-MFC-Befehle und 
    	Oberfl�chenobjekte.

/////////////////////////////////////////////////////////////////////////////
Andere Standarddateien:

StdAfx.h, StdAfx.cpp
    	Mit diesen Dateien werden vorkompilierte Header-Dateien (PCH) mit der Bezeichnung 
	FaBePlan.pch und eine vorkompilierte Typdatei mit der Bezeichnung StdAfx.obj
	erstellt.

Resource.h
    	Dies ist die Standard-Header-Datei, die neue Ressourcen-IDs definiert.
    	Microsoft Visual C++ liest und aktualisiert diese Datei.

/////////////////////////////////////////////////////////////////////////////
Weitere Hinweise:

Der Klassen-Assistent f�gt "ZU ERLEDIGEN:" im Quellcode ein, um die Stellen anzuzeigen, 
an denen Sie Erweiterungen oder Anpassungen vornehmen k�nnen.

Wenn Ihre Anwendung die MFC in einer gemeinsam genutzten DLL verwendet und Ihre Anwendung
eine andere als die aktuell auf dem Betriebssystem eingestellte Sprache verwendet, muss 
die entsprechend lokalisierte Ressource MFC42XXX.DLL von der Microsoft Visual C++ CD-ROM 
in das Verzeichnis system oder system32 kopiert und in MFCLOC.DLL umbenannt werden ("XXX" 
steht f�r die Abk�rzung der Sprache. So enth�lt beispielsweise MFC42DEU.DLL die ins Deutsche 
�bersetzten Ressourcen). Anderenfalls erscheinen einige Oberfl�chenelemente Ihrer Anwendung 
in der Sprache des Betriebssystems.

/////////////////////////////////////////////////////////////////////////////
