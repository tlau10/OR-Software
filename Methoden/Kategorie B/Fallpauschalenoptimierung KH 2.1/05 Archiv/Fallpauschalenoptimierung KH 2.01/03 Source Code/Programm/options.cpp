//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop

#include "options.h"
#include "registry.h"
#include "MainFormMDI.h"
#include <string>
#include <iostream>
#include <string>

#include <fstream.h>
#include <string.h>
#include <ctype.h>

//---------------------------------------------------------------------------
#pragma package(smart_init)
#pragma resource "*.dfm"
TOptionsForm *OptionsForm;
//---------------------------------------------------------------------------
__fastcall TOptionsForm::TOptionsForm(TComponent* Owner): TForm(Owner)
{

}
//---------------------------------------------------------------------------
void __fastcall TOptionsForm::BitBtn1Click(TObject *Sender)
{
	// save
  //reg_setxadir(EditXA->Text.c_str());
  //reg_setmopsdir(EditMOPS->Text.c_str());
  reg_setlpsolvedir(EditLPSolve->Text.c_str());
  MainForm->solverFilePath = EditLPSolve->Text.c_str();
  //reg_setstradadir(EditSTRADA->Text.c_str());
  //reg_setweidenauerdir(EditWeid->Text.c_str());
  reg_setworkdir(EditTemp->Text.c_str());
  MainForm->workSpacePath = EditTemp->Text.c_str();

  MainForm->closeChildForm();
}
//---------------------------------------------------------------------------
void __fastcall TOptionsForm::FormActivate(TObject *Sender)
{

  //EditXA->Text = reg_getxadir();
  //EditMOPS->Text = req_getmopsdir();
  string solveDir;
  solveDir = req_getlpsolvedir();

  solveDir.erase(solveDir.length() - 1, 1);

  EditLPSolve->Text = solveDir.c_str();
  //EditSTRADA->Text = reg_getstradadir();
  //EditWeid->Text = reg_getweidenauerdir();
  EditTemp->Text = reg_getworkdir();
}
//---------------------------------------------------------------------------
void __fastcall TOptionsForm::FormCreate(TObject *Sender)
{
	this->Left = 0;
	this->Top = 0;
	this->Width = 500;
	this->Height = 180;
}
//---------------------------------------------------------------------------

void __fastcall TOptionsForm::BitBtn2Click(TObject *Sender)
{
	MainForm->closeChildForm();
}
//---------------------------------------------------------------------------

void __fastcall TOptionsForm::btnFindSolverClick(TObject *Sender)
{
	// �ffnen

	OpenDialog1->InitialDir = reg_getdatadir();          // Datei -> Oeffnen
	OpenDialog1->FileName = "LP_SOLVE.EXE";

	if (OpenDialog1->Execute())
	{
		// open the dialog box
		char dir[200];
		getcwd(dir,sizeof(dir));       // gets current working directory
		reg_setdatadir(dir);          // das letze Verz. wird in der Registry gespeichert

		this->EditLPSolve->Text = OpenDialog1->FileName.c_str();
	}
}
//---------------------------------------------------------------------------

void __fastcall TOptionsForm::btnFindWorkSpaceClick(TObject *Sender)
{
	// �ffnen

	OpenDialog1->InitialDir = reg_getdatadir();          // Datei -> Oeffnen
	OpenDialog1->FileName = "LP_SOLVE.EXE";

	if (OpenDialog1->Execute())
	{
		// open the dialog box
		char dir[200];
		getcwd(dir,sizeof(dir));       // gets current working directory
		reg_setdatadir(dir);          // das letze Verz. wird in der Registry gespeichert

		this->EditTemp->Text = OpenDialog1->FileName.c_str();
	}
}
//---------------------------------------------------------------------------

