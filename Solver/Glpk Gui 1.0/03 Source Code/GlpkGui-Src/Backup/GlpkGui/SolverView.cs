﻿using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Windows.Forms;

using GlpkSharp;


namespace GlpkGui
{
    public partial class SolverView : UserControl
    {
        public SolverView()
        {
            InitializeComponent();
            tableauView.SolverView = this;
        }

    }




}
