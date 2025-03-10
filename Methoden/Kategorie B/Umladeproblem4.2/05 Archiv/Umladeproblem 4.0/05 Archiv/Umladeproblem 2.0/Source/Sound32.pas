unit Sound32;

//  Bildet das alte Dos-Sound-System f�r 32-Anwendungen nach
//  Wolfgang Ott, Dez. 1999

interface
   
  Procedure DosNoSound;

  procedure DosSound(Hz: Word);


implementation

uses
  Windows, SysUtils, WinProcs;


Procedure SetPort(w:byte;adr:Word); register // ist eh default
                                             // Reihenfolge eax, edx, ebx
asm
 out dx,al   // Register sind bereits korrekt gesetzt
end;

Function Port(adr:Word):byte; register;
asm
 mov dx,adr
 in al,dx
 mov @result,al
end;



procedure DosSound(Hz: Word);
var tw:word;
begin
 If Win32Platform=Ver_PlatForm_Win32_NT then windows.beep (Hz, -1) {falls NT}
 else
  begin
  tw:=round(1.19315e6/Hz);
  setport ($43,$B6);    //
  setport(lo(tw),$42);  //
  setport(hi(tw),$42);  //
  setport(port($61) or 3,$61);
  end;
end;

Procedure DosNoSound;
begin
 If Win32Platform=Ver_PlatForm_Win32_NT then windows.beep (0, 0) {falls NT}
 else
  begin
  setport (port($61) and not 3,$61);
  end;
end;
      
end.
