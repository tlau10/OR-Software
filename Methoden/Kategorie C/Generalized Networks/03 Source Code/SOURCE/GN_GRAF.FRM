��,�;  _    	GN_Grafik( 0�� Netzwerk - Grafik�  �  �#  �   B #����$Form15�  6�  7�#  8�  �&   auswert� Zeichnen8��w �$   PrintG� Drucken���w �'   zurueck� 	Schlie�enx���  �7   aus_txt�    � �	�< � 
 MS Serif  A� ��=  �  menge2�      � ��K � MS Serif  A� � �<  �  multi�        V�K � MS Serif  A� � �#   Linie1�    �  �#  �  �9  �  menge�   �  *x K � 
 MS Serif  A� ��)  �	 
knotenname�   w���  �&  � knoten� � ��g  ��6  �  __	 
�Q�Q+   � (#�_� �� � w � � �Z � �| �d�� <g 1�S�^!�                   +  ,@	Form_Load�  xagenerieren�i! Tabelle� Rowm ColB�Text�  endwhile�  ThenExit� daten� vonw bez_var1 nach� matrix_b 
obergrenze� untergrenzeI max6�satz� ober_ unter� anz_var� mJ�xV satz_bez var matrixw matrix_v� d� suche_nach_wertQ zeile mult� anz_rest� endfor? b_vektorG operat� operator� zielJ kosten@zurueck_Click� ausgabeA Rows� Cols! boudns� ColWidth� Elseausgabe�b2 Vektor, vectoru 
stddialog1e filename��dummy ofc file\ dummy1��ergeb� ergebnis� Bezeichnung2_Click Werte� eol��du� var_bez��anfang ausgabe_Click~ anzahlj Bezeichnung1_Click5 Ergebnis_Change� werte_Change� graphik<�a abstand1	 maximum��knoten� Visible%�
knotennamex Caption��links�oben��Left��Topa	 
Form_Paint�	DrawWidth��menge� mengen� hight�Height� 	backstyleS 	DrawStyle$
 xa_datei_schreiben� c2 xak Gn� clp� aus��	dateiname� 	erstellenh Enabledq TITLEX�s� matrix_��j� thens� exe� dateiU@auswert_click� auswert_gestartet  Su� auswer_gestartet��auswert 	anz_resst� 
problemart" 
probelmarti abbruch� msg_box{ Achtung! antwort? graphikerstellen�abbruch1X knotenname_Click� Index�	menge_out4@PrintG_ClickH printg� commaND� vivible� zurueckE visileW printfrm, zueruck* sat� ss� satz1� satz2� text1 text2' Bezeichnung2��screen�	 menge_ClickH�printer� Form problemart_Clickw printform_s2K ergebnis_copy� 
werte_copy� 
printform2� form1| ausgabe_copy� grid� rnum� r ausgabe_cop g�
 ausgabe_cop_Click4 UnloadMe� Txt� AusgabeGrid= Ausgabe_Grid� textboxr grid1
 v 	ScaleModep drawith��cx��cyx CurrentXd 	currentyy�
 CurrentY� drawidth�
 FontBold{
 pprinter>�X1a�y1� X2� Y2
 x1cxF DrawMode�	 	fillstyleU 	ForeColor� FontSize	 menge_2��posk y��l��t3 lp�
 Ansatz   in	 	eintragen satzt&	 anz_res�
 Endsub�	 	dummy_act� activity   laenge� msg   ok� 
outputtext�
 output_text� txtbox�	 Text1_Change	@Befehl1_ClickG	 grahpik�	 frm� standarddialg8
 standarddialog   	bsp_datei�	 BlattM
 Textergebnis�	 gotoend   p_links;	 p_obenv	 p_p_obenU	 
p_p_links1   p_links1n	 	FillColor�
�helpS
 satzergebnis�	 startpos  �	ergebnis2   char
 Text2_Change�	 Text2_clickj
 Bezeichnung4_Click�
 menge2_Click multiplikator��menge2.
�multi   linie1 linie3& linie2_
 linie4C
�aus_txtY
 austxt�s1�
�s2�
�s3�
 warnung{ FontUnderline   sa�
 linie� Thenprinter2 anz   
anzahl_varM flag�
�
max_anzahl   rest  xy   aus_txt_Click   o   ben�enddruck� xapath   	aktxapath   prgpath  �Width   KommaPos   EXEPath   Gr��eZ der� 	festlegeni multi_Click   Warnung_Change   Bezeichnung3_Click� 
EingabeTab��EingTab   Zeichnen   Grafik_Zeichnen   z k� zz! ii Zeit1_Timer� zeit1   enable   interval   iput  �menge_in  �GN_MDIj      �   �       u  � ^  �       ~v &  Variablen zur Grafik-Ausgabefunktion      ;	  ��������    auswert_click0�	     X  �       v .    >>> wird �ber den Zeichnen-Button gestartet    v    Dim dummy As String * 10  � �  w  , # � �  $  �	  � �  S M
�     S
  � � �     Y
      O 5      v 0    Textfelder f�r die Angabe der transportierten  v :    Mengen in der Graphik erzeugen, Belegung erfolgt sp�ter     w �  !> ���  w   �� v   Kanten-Werte  w   
� v   Kanten-Multiplikatoren  w   �	� v   Effektive Kanten-Werte   w N ��     � w     �� � E 
,�  G Q   ��  \ jv  ( 1.Knoten benennenr  ,�  \ Qv  ( Sichtbar machene  � � �  uv  ( Positionierenc  � �  ~     w   �� Z �  w   \� v & ' Weitere Knotenbezeichnungen erzeugen  w   G� v  ' Weitere Knoten erzeugenn v "  pos = 8000 / satz.anz_rest + 300 � '  �� � ,�  �    w ��   ~� �� E ��  w   ~  w ��   u w   u  w ��   ~� � E  �  w   ~  w ��   u ��  w   u  w ��   ~� � E X� � w   ~  w ��   u ��  w   u    w   u w   G �v    Knoten positionieren  w   ~ w   G � , w   G Qv    sichtbar machenp   w   u� � �  w   \ �v # + Knotenbezeichnungen positionieren  w   ~� � �  w   \ �  w  w   \ j , w   \ Qv  , sichtbar machen   w ��  w   Y �   v )    Ausgabe Text: "Output" am Senke-Knotene   w ��   ~� � �  8
 �   w ��   u� ��  8
 �  �  " Output   8
 j  , 8
 Q      v 4    Linien ziehen und Zahlen in die Graphik eintragen    � �   w �  !> ��R
 v   anzahl = 0  w   � �  ,  w   � �  #   w   � � � � I d w f  ,  u� ,�  ,  ~� ,� `  #  u� ,�  #  ~� ,� b �� �    8    v /   Ausgabe des eingespeisten Werts an der Kante  ,  ~ #  ~� � �� � 2 �  w   � �  ,  u #  u� � �� �(�  w   � �  w    �4�� w   � j , w   � Q  v *   Ausgabe des Multiplikators an der Kante  ,  ~ #  ~� � �� � � �  w   
 �  ,  u #  u� � ��  w   
 � �  �  *   w   � �4��2 w   
 j , w   
 Q   v 4   Ausgabe des tats�chlichen Flu�wertes in der Kante v )   d.h eingespeister Wert * Multiplikatorn  ,  ~ #  ~� � �� � ��  w   �	 �  ,  u #  u� � ��  w   �	 �  w  n	  �4�� w   �	 j , w   �	 Q   w N ��     + � ?   ��5 = x
      9 	  �	������a     Befehl1_Click0(      X  �     w     x     9 	  ��������    	 Form_Load0x     X  Z        � "   
  w  � 2     �    � � �  � T$    ! � } �  � � ��  �   ! �� ��  �� � ��  �    , � ?    v 7    Die Grafikausg. kann nicht direkt aufgerufen werden.n  v +    Hier �ber den Button Zeichnen (auswert).u  v 6    Grund: anscheinen wird ein Klick-Ereignis ben�tigt,  v !    um Linien zeichnen zu k�nnenc      9 	  ��������     PrintG_Click0b     X  k         v    Abfragefenster  �  4  Druck: Grafik & LP-Ansatz � A ' 1   1�� E n C �
    v    Printer Objekt definieren   v    Blatt1: Druck der Grafik  v    Kopfzeile Blatt1   O �
  � , ! Kv  & InitialisierungA � � ! d � ! | �   ! �  !e f �  8 GENERALIZED NETWORKS  u v  & Ausgabe Titel  � , ! K �   ! d  !e f �  � Beispieldatei    %� u v  0 Ausgabe Beispieldateii � , ! K  !e f A�  �	 \ Blatt 1 2u v  ( Ausgabe aktuelles Datumm � , ! K  !e f �   > **************************  u    v    Ende Kopfzeile      v +   Ausgabe der Grafik an den Standarddruckern    � , ! K  � ! j
   !e f �  � Graphik:  u   �  ! j
    � �  '
  V  � �  
 	  �   � V � � ? E w  , # � �   2  �
       w �  !> ��~   w N ��   � w   ��  \ jv  ' 1.Knoten benennen  � , ?v # ' x-Koordinate: linker Blattabsatnda �  Ev $ ' y-Koordinate: rechter Blattabsatnd  � ,�  uv  ' Positionieren des 1.Knotensn � �  ~   ? ! Kv ! ' Initialisierung der Koordinatene  E ! dv  ' des Printer-Objekts  �( ! �v  ' Druck-Einstellungent � ! � � ! � � ! � � ! '  !e f �  u ?� �  ~ E� ` � ,� � � � � � � _ z  ( v  ( Druckausgabe 1. Knoten  �  u ?�  ! Kv ! ( Initialisierung der Koordinatene �  ~ E�  ! dv  ( des Printer-Objektsr  !e f �  \u v  ( Druckausgabe Knotennamea   w   �� Z ,  v "  pos = 8000 / satz.anz_rest + 300  � (#  �� � ,�  �   w ��   ~� �� E ��  w   ~  w ��   u w   u   w ��   ~� � E 6�  w   ~  w ��   u ��  w   u   w ��   ~� � E �� � w   ~  w ��   u ��  w   u   !e f  w   u ?�  w   ~ E� ` � ,� � � � � � � _ z    !e f  w   \u    w ��  w v  ( Ausgabe der weiteren knoten Y 2  v (  Ausgabe Text: "Output" am Senke-Knoten �( ! �  w ��   u ?� � @�  ! Kv ! 3 Initialisierung der Koordinatenn  w ��   ~ E� � 2 �  ! dv  3 des Printer-Objektse   !e f �  	 Output  u    v 3  Linien ziehen und Zahlen in die Graphik eintragenb � ! � � ! �    w �  !> ��d   w   � �  ,   w   � �  #    v <  Linien um den Teil k�rzen, mit dem sie in den Knoten gehen   ,  ~ #  ~� E �
 !e f  ,  u ?� � � �  ,  ~ E� � � � `  #  u� � �  ?�  #  ~ E� � � � b � � � � � � � �      ,  ~ #  ~� E p !e f  ,  u ?� � � �  ,  ~ E� � � � `  #  u� � �  ?�  #  ~ E� � � � b � � � � � � � �      ,  ~ #  ~� E  !e f  ,  u ?� � ,�  ,  ~ E� `  #  u� ,�  ?�  #  ~ E� b � � � � � � � �       v 3  Linienmitte f�r Ausgabe der Kantenwerte ermittelnt   ,  ~ E�  #  ~�  E� � �� � � �  v  : 170= Korrekturfaktor   ,  u ?�  #  u�  ?� � �� � d �  �v  : 100=Korrekturfaktorr     � ! Kv ! + Initialisierung der Koordinateni    ! dv  + des Printer-Objekts     v    Ausgabe des Kantenwerts   !e f  w    �u     v *   Ausgabe des Multiplikators an der Kante   � ! Kv ! + Initialisierung der Koordinaten    � � �  ! dv  + des Printer-Objektsd   !e f �  F *    w   � �4��2u     v 4   Ausgabe des tats�chlichen Flu�wertes in der Kante  v )   d.h eingespeister Wert * Multiplikatorn   � ! Kv ! + Initialisierung der Koordinateni   � ,�  ! dv  + des Printer-Objektsd   !e f  w  n	  �4��u    w N ��      �   ! �    v    Druck gew�hlter Output  � , ! K  � X ! d  � ! j
    �� I   !e f �  � Gew�hlter Input:  �       2 2u   2 `  !e f �  . Gew�hlter Output: �  J     2 ^2u   8     �  ! j
    v    Druck Optimum.  � , ! K  � L ! d  � ! j
   !e f �  � Optimal Solution: u   �  ! j
  � , ! K    �� E &�   MAXIMUM:   � 2 <�  , MINIMUM:   �   v +   printer.Print Text & "  " & ergebnis.Textn  v #   printer.Print "  " & warnung.Textt  �( ! �    v    Ende Seite 1      v %   Neue Seite: drucken des LP-Ansatzes*  v    Kopfzeile Blatt2     !e    x v  % Seitenumbruch   � , ! Kv  % Initialisierung   � � ! d  � ! |  �   ! �   !e f �  � GENERALIZED NETWORKS  u v  % Ausgabe Titelo  � , ! K  � � ! d   !e f  %u v  % Ausgabe Beispieldateia  � , ! K   !e f A�  8	 \ Blatt 2 2u v  % Ausgabe aktuelles Datum   � , ! K   !e f �   � **************************  u    v    Ende Kopfzeile   � , ! K  � ! j
   !e f �  �
 LP-Ansatz:  u   �  ! j
  �( ! �  � , ! Kv ! % Initialisierung der Koordinatenu  � �	 ! dv  % des Printer-Objektsd  � J ?  � �	 E    v !    LP-Ansatz in Tabelle eintragend   w �  �> ���  1�  !> ���   ? ! Kv ! , Initialisierung der Koordinatena   E ! dv  , des Printer-Objekts    !e f  1 w    Su v  , Matrizen-Werte   ?� ��  ? M ��  � J ?  E� ��  E  M ��     v    Druck Text "Zielfunktion"e � , ! K  E ! dv +   Druck Text "Zielfunktion" unterhalb Werten  !e f � " H Zielfunktions-Koeffizienten:  u     v    Druck Text "Bounds � , ! K  E� ��  ! d  !e f � $ � Bounds der Variablen (Kanten):  u v " 4 Druck Text "Bounds" unterhalb ZF �  ?    v #   Druck der Werte f�r ZF und BOUNDSa   w �  !> ���   v   Variablen-Bezeichnung     ?� ^�  ! K � � ! d  !e f  w    � u   v   Zielfunktions-KoeffizientenB  ?� ^�  ! K  E� ��  ! d  !e f  w    �u v  ' Zielfkt eintragena  v   BOUNDS  ?� ^�  ! K  E� ��  ! d  !e f � w    � u v  ' Bounds eintragen  ?� ��  ?  M ��     v    b-Vektor-Bezeichnung    !� �  � � ,�  ! K  � � ! d   !e f �  . b-Vektor  u     v    Max oder Min eintragen    �� E ��  t --> Max  � 2 ��  � --> Min  �     !� �  � � ,�  ! K   E� ��  ! d   !e f  � u     v    B-Vektor eintragen  � �	 E   w �  �> ���   !� �  � � ,�  ! K  E ! d  w    � w    �2 � v 
 / B-Vektor  !e f  � u   E� ��  E  M ��    v "   Zeichnen der Linien im LP-Ansatz  �  ?  � � E  v ,   Linie unterhalb der Variablenbezeichnungen   !e f � , E`   !�� � � �� � � �  Eb � � � � � � � �       ? ! K   E ! d    v    Linien innerhalb der Matrixx   w �  !> ��V  !e f  ? E� �� `  ? E  �� �� � � � d � b � � � � � � � �     ?� ��  ?   w N ��     v    Teillinien der Zielfunktionx  �  ?   w �  !> ��2  !e f  ? E  �� �� � � � �� `  ? E  �� � �� � � � �� b � � � � � � � �     ?� ��  ?   w N ��     v    Teillinien der Boundsn  �  ?   w �  !> ��  !e f  ? E  �� �� � � � �� `  ? E  �� �� � � � 4� b � � � � � � � �     ?� ��  ?   w N ��      !e    x v   Ende des Druck-Objektes0     "�
= �   ���
 �  f Drucker-Fehler !!!  �  ' 1 = �    9   	  ������    zurueck_Click0�     X  �       v    >>> Schlie�en - Button   � F  w     c    v    Datenstruktur l�schene     c�� F > ��,  w �� 2 > ���   �  c w    S  � � c   �   � � c   �   w N ��  �  c   � �  �      c   �  �       c   �   cN ��      w �� 2 > ��� �  w    � �  b     w    �   w N ��     G v   Form beenden    9 	  ��������   �