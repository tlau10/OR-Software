unit Status;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, ComCtrls;

type
  TAnzeige = class(TForm)
    AbbruchButton: TButton;
    Fortschritt: TProgressBar;
    FortschrittLabel: TLabel;
    ArtLabel: TLabel;
    StatusLabel: TLabel;
    procedure AbbruchButtonClick(Sender: TObject);
    procedure FormClose(Sender: TObject; var StatusCloseAction: TCloseAction);
  private
    { Private declarations }
    Berechnung: TThread;
    EnumEnabled: Boolean;
    procedure Zentrieren();
  public
    { Public declarations }
    MaxStatusValue: Int64;
    procedure FormZentrieren(Form: TForm);
    procedure InitStatus(Enum: Boolean; MaxValue: Int64; Sender: TThread);
    procedure UpdateStatus(Value: Int64);
  end;

var
  Anzeige: TAnzeige;

implementation

uses Standort;

{$R *.DFM}

procedure TAnzeige.FormZentrieren(Form: TForm);
begin
     Left := (Form.Width - Width) div 2 + Form.Left;
     Top  := (Form.Height - Height) div 2 + Form.Top;
end;

procedure TAnzeige.Zentrieren();
begin
     //ArtLabel.Left := (Anzeige.Width - ArtLabel.Width) div 2;
     //StatusLabel.Left := (Anzeige.Width - StatusLabel.Width) div 2;
     //Fortschritt.Left := (Anzeige.Width - Fortschritt.Width) div 2;
     //FortschrittLabel.Left := (Anzeige.Width - FortschrittLabel.Width) div 2;
     //AbbruchButton.Left := (Anzeige.Width - AbbruchButton.Width) div 2;
end;

procedure TAnzeige.InitStatus(Enum: Boolean; MaxValue: Int64; Sender: TThread);
begin
     EnumEnabled := Enum;
     MaxStatusValue := MaxValue;
     Berechnung := Sender;
     Fortschritt.Max := MaxStatusValue;
     Fortschritt.Position := 0;

     if EnumEnabled then
     begin
          ArtLabel.Caption := '- Enumerations-Verfahren -';
          StatusLabel.Caption := ' 0 von ' + IntToStr(MaxStatusValue) + ' Berechnungen ';
          Zentrieren();
     end
     else
     begin
          ArtLabel.Caption := '- Entscheidungsbaum-Verfahren -';
          StatusLabel.Caption := ' 0 von ' + IntToStr(MaxStatusValue) + ' Berechnungen ';
          Zentrieren();
     end;
     FortschrittLabel.Caption := '0 %';
end;

procedure TAnzeige.UpdateStatus(Value: Int64);
begin
     if EnumEnabled = True then
     begin
          if Value > MaxStatusValue then Value := MaxStatusValue;

          Fortschritt.Position := Value;
          StatusLabel.Caption := ' ' + IntToStr(Value) + ' von ' + IntToStr(MaxStatusValue) + ' Berechnungen ';
          //StatusLabel.Left := (Anzeige.Width - StatusLabel.Width) div 2;
     end
     else
     begin
          if Value = 0 then
          begin
               StatusLabel.Caption := 'Das erste optimale Ergebnis ist zul�ssig';
               //StatusLabel.Left := (Anzeige.Width - StatusLabel.Width) div 2;
               Fortschritt.Position := Fortschritt.Max;
               Value := Fortschritt.Max;
               MessageBeep(0);
          end
          else
          begin
               if Value > MaxStatusValue then Value := MaxStatusValue;

               Fortschritt.Position := Value;
               StatusLabel.Caption := ' ' + IntToStr(Value) + ' von ' + IntToStr(MaxStatusValue) + ' Berechnungen ';
               //StatusLabel.Left := (Anzeige.Width - StatusLabel.Width) div 2;
          end;
     end;
     FortschrittLabel.Caption := IntToStr((Value*100) div MaxStatusValue) + ' %'
end;

procedure TAnzeige.AbbruchButtonClick(Sender: TObject);
begin
     Formular.Enabled := True;

     if (Sender = AbbruchButton) and
        (AbbruchButton.Caption = 'Fertig') then
     begin
        Formular.Sicht.ActivePage := Formular.ErgebnisSicht;
     end
     else
     begin
          QueryPerformanceCounter(Formular.Bis);
         Formular.Sicht.ActivePage := Formular.EingabeSicht;
         Formular.StatusBar.Panels[0].Text := format('Dauer: %7.5g sec',[(Formular.Bis-Formular.Von) / Formular.d]);
     end;

     Anzeige.Close();

     // Anzeigewerte zuruecksetzen
     AbbruchButton.Caption := 'Abbrechen';
     AbbruchButton.Cancel := True;
     AbbruchButton.Default := False;
end;

procedure TAnzeige.FormClose(Sender: TObject; var StatusCloseAction: TCloseAction);
begin
     Formular.Enabled := True;
     Berechnung.Terminate;

     if (Sender = Anzeige) and
        (AbbruchButton.Caption = 'Fertig') then
     begin
        Formular.Sicht.ActivePage := Formular.ErgebnisSicht;
     end
     else
     if Formular.ErgebnisEnabled then
        Formular.Sicht.ActivePage := Formular.ErgebnisSicht
     else
        Formular.Sicht.ActivePage := Formular.EingabeSicht;

     // Berechnungsdauer ausgeben
     Formular.StatusBar.Panels[0].Text := format('Dauer: %7.5g sec',[(Formular.Bis-Formular.Von) / Formular.d]);

     // Anzeigewerte zuruecksetzen
     AbbruchButton.Caption := 'Abbrechen';
     AbbruchButton.Cancel := True;
     AbbruchButton.Default := False;

     // Hauptformular wieder aktivieren und focusieren
     Formular.SetFocus;
end;

end.
