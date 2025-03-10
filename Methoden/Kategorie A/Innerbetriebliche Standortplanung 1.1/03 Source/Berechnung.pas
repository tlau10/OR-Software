unit Berechnung;

interface

uses
    Grids, SysUtils, Dialogs, Forms, Classes, Windows,
    Matrix, Feld;
    
type
    TDaten = class(TObject)
      // Variablen
      AnzOrte, AnzPaare: Word;
      StandortPaare, MaschinenPaare: TIntArray;
      ZMatrix: TIntMatrix;
      // Methoden
      constructor Init(N: Word);
      destructor Kill();
      procedure EingabeDaten(Standorte, Maschinen: TStringGrid);
      procedure BerechneMatrix();
      procedure RechneEnumeration();
      procedure RechneEntscheidungsbaum();
    end;

var UrMatrix: TIntMatrix;

implementation

uses Standort, Enumeration, Entscheidungsbaum;

constructor TDaten.Init(N: Word);
begin
     inherited Create();
     AnzOrte := N;
     AnzPaare := (AnzOrte*(AnzOrte-1)) div 2;
     StandortPaare := TIntArray.Dim(AnzPaare);
     MaschinenPaare := TIntArray.Dim(AnzPaare);
     ZMatrix := TIntMatrix.Dim(AnzPaare,AnzPaare);
     UrMatrix := TIntMatrix.Dim(AnzPaare,AnzPaare);
end;

destructor TDaten.Kill();
begin
     StandortPaare.Kill;
     MaschinenPaare.Kill;
     ZMatrix.Kill;
     inherited Destroy();
end;

procedure TDaten.EingabeDaten(Standorte, Maschinen: TStringGrid);
var i,j,z: Word;
    Value: Cardinal;
begin
     z := 0;
     for i := 1 to AnzOrte-1 do
         for j := i+1 to AnzOrte do
         begin
              Inc(z);
              Value := StrToInt(Standorte.Cells[j,i]);
              StandortPaare.SetValue(z,Value);
         end;

     z := 0;
     for i := 1 to AnzOrte-1 do
         for j := i+1 to AnzOrte do
         begin
              Inc(z);
              Value := StrToInt(Maschinen.Cells[j,i])+
                       StrToInt(Maschinen.Cells[i,j]);
              MaschinenPaare.SetValue(z,Value);
         end;
end;

procedure TDaten.BerechneMatrix();
var i,j: Word;
    ValOne,ValTwo,Value: Cardinal;
begin
     for i := 1 to AnzPaare do
     begin
          ValOne := StandortPaare.GetValue(i);
          for j := 1 to AnzPaare do
          begin
               ValTwo := MaschinenPaare.GetValue(j);
               Value := ValOne * ValTwo;
               ZMatrix.SetValue(j,i,Value);
               UrMatrix.SetValue(j,i,Value);
          end;
     end;
end;

procedure TDaten.RechneEnumeration();
var Berechnung: TEnum;
begin
     Berechnung := TEnum.Create(ZMatrix,AnzOrte);
     Berechnung.Start;
end;

procedure TDaten.RechneEntscheidungsbaum();
var Berechnung: TDakin;
    Counter: LongWord;
    i,j: Word;
begin
     Counter := 0;
     for i := 1 to AnzPaare do
         for j := 1 to AnzPaare do
         begin
              if ZMatrix.GetValue(i,j) = 0 then Inc(Counter);
         end;

     if Counter > ((AnzPaare * AnzPaare)-AnzPaare) then
     begin
          Formular.Enabled := True;
          MessageBeep(1);
          Formular.SetStatusInfo('Die Eingabedaten sind unzureichend!');
          Formular.ErgebnisSicht.TabVisible := False;
          Exit;
     end;

     Berechnung := TDakin.Create(ZMatrix,AnzOrte);
     Berechnung.Start; // von Resume zu Start geändert
end;

end.
