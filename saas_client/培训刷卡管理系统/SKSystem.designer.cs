namespace 培训刷卡管理系统
{
    partial class SKSystem
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                if (components != null)
                {
                    components.Dispose();
                }
            }

            TDSKMon.Abort();

            base.Dispose(disposing);
        }


        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(SKSystem));
            this.tabCtrl = new System.Windows.Forms.TabControl();
            this.tabSK = new System.Windows.Forms.TabPage();
            this.huiyuanbh = new System.Windows.Forms.Label();
            this.label26 = new System.Windows.Forms.Label();
            this.SKOpenDialgBox = new System.Windows.Forms.CheckBox();
            this.ClassName = new System.Windows.Forms.Label();
            this.label9 = new System.Windows.Forms.Label();
            this.SWSName = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.LawyerCredit = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.LawyerName = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.Photo = new System.Windows.Forms.PictureBox();
            this.SKlV = new System.Windows.Forms.ListView();
            this.columnHeader1 = new System.Windows.Forms.ColumnHeader();
            this.columnHeader2 = new System.Windows.Forms.ColumnHeader();
            this.columnHeader5 = new System.Windows.Forms.ColumnHeader();
            this.columnHeader3 = new System.Windows.Forms.ColumnHeader();
            this.columnHeader4 = new System.Windows.Forms.ColumnHeader();
            this.tabSKByHand = new System.Windows.Forms.TabPage();
            this.lsbh = new System.Windows.Forms.TextBox();
            this.label24 = new System.Windows.Forms.Label();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.hzbh = new System.Windows.Forms.Label();
            this.label25 = new System.Windows.Forms.Label();
            this.CreditNoTB = new System.Windows.Forms.Label();
            this.KHTB = new System.Windows.Forms.Label();
            this.ReqMinutesLb = new System.Windows.Forms.Label();
            this.label14 = new System.Windows.Forms.Label();
            this.SKBHLb = new System.Windows.Forms.Label();
            this.SWSNameLb = new System.Windows.Forms.Label();
            this.LawyerNameLb = new System.Windows.Forms.Label();
            this.LessonNameLb = new System.Windows.Forms.Label();
            this.label15 = new System.Windows.Forms.Label();
            this.SKMinuteTB = new System.Windows.Forms.TextBox();
            this.label16 = new System.Windows.Forms.Label();
            this.SKDTPicker = new System.Windows.Forms.DateTimePicker();
            this.label17 = new System.Windows.Forms.Label();
            this.label18 = new System.Windows.Forms.Label();
            this.label19 = new System.Windows.Forms.Label();
            this.label20 = new System.Windows.Forms.Label();
            this.label21 = new System.Windows.Forms.Label();
            this.label22 = new System.Windows.Forms.Label();
            this.ErrorLb = new System.Windows.Forms.Label();
            this.OKBtn = new System.Windows.Forms.Button();
            this.label23 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.SchBtn = new System.Windows.Forms.Button();
            this.XMTB = new System.Windows.Forms.TextBox();
            this.SWSNameTB = new System.Windows.Forms.TextBox();
            this.DGLawInfo = new System.Windows.Forms.DataGrid();
            this.LawInfoTableStyle = new System.Windows.Forms.DataGridTableStyle();
            this.dataGridTextBoxColumn7 = new System.Windows.Forms.DataGridTextBoxColumn();
            this.dataGridTextBoxColumn8 = new System.Windows.Forms.DataGridTextBoxColumn();
            this.dataGridTextBoxColumn12 = new System.Windows.Forms.DataGridTextBoxColumn();
            this.dataGridTextBoxColumn9 = new System.Windows.Forms.DataGridTextBoxColumn();
            this.dataGridTextBoxColumn10 = new System.Windows.Forms.DataGridTextBoxColumn();
            this.dataGridTextBoxColumn11 = new System.Windows.Forms.DataGridTextBoxColumn();
            this.tabKQTJ = new System.Windows.Forms.TabPage();
            this.btDelSKRecs = new System.Windows.Forms.Button();
            this.rBLawyer = new System.Windows.Forms.RadioButton();
            this.rBSWS = new System.Windows.Forms.RadioButton();
            this.cBSKLessons = new System.Windows.Forms.ComboBox();
            this.label11 = new System.Windows.Forms.Label();
            this.dGKQ = new System.Windows.Forms.DataGrid();
            this.dataGridTableStyleSWS = new System.Windows.Forms.DataGridTableStyle();
            this.dataGridTextBoxColumn1 = new System.Windows.Forms.DataGridTextBoxColumn();
            this.dataGridTextBoxColumn2 = new System.Windows.Forms.DataGridTextBoxColumn();
            this.dataGridTableStyleLawyers = new System.Windows.Forms.DataGridTableStyle();
            this.dataGridTextBoxColumn3 = new System.Windows.Forms.DataGridTextBoxColumn();
            this.dataGridTextBoxColumn4 = new System.Windows.Forms.DataGridTextBoxColumn();
            this.dataGridTextBoxColumn5 = new System.Windows.Forms.DataGridTextBoxColumn();
            this.dataGridTextBoxColumn6 = new System.Windows.Forms.DataGridTextBoxColumn();
            this.tabAdmin = new System.Windows.Forms.TabPage();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.LawyerPhoto = new System.Windows.Forms.CheckBox();
            this.DownLessonInfo = new System.Windows.Forms.CheckBox();
            this.PSWord = new System.Windows.Forms.TextBox();
            this.UserID = new System.Windows.Forms.TextBox();
            this.label10 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.WebName = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.GetLawyersDataBtn = new System.Windows.Forms.Button();
            this.DownCardNo = new System.Windows.Forms.CheckBox();
            this.groupBox4 = new System.Windows.Forms.GroupBox();
            this.UploadBtn = new System.Windows.Forms.Button();
            this.RemoteWrite = new System.Windows.Forms.CheckBox();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.OldLessonType = new System.Windows.Forms.CheckBox();
            this.KCBHComboBox = new System.Windows.Forms.ComboBox();
            this.label3 = new System.Windows.Forms.Label();
            this.HaveTMode = new System.Windows.Forms.CheckBox();
            this.label13 = new System.Windows.Forms.Label();
            this.label12 = new System.Windows.Forms.Label();
            this.SKNum = new System.Windows.Forms.Label();
            this.AutoExportNum = new System.Windows.Forms.Label();
            this.PBLine = new System.Windows.Forms.ProgressBar();
            this.ShowState = new System.Windows.Forms.Label();
            this.tabCtrl.SuspendLayout();
            this.tabSK.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.Photo)).BeginInit();
            this.tabSKByHand.SuspendLayout();
            this.groupBox3.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DGLawInfo)).BeginInit();
            this.tabKQTJ.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dGKQ)).BeginInit();
            this.tabAdmin.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.groupBox4.SuspendLayout();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // tabCtrl
            // 
            this.tabCtrl.Controls.Add(this.tabSK);
            this.tabCtrl.Controls.Add(this.tabSKByHand);
            this.tabCtrl.Controls.Add(this.tabKQTJ);
            this.tabCtrl.Controls.Add(this.tabAdmin);
            this.tabCtrl.Location = new System.Drawing.Point(5, 13);
            this.tabCtrl.Name = "tabCtrl";
            this.tabCtrl.SelectedIndex = 0;
            this.tabCtrl.Size = new System.Drawing.Size(659, 440);
            this.tabCtrl.TabIndex = 1;
            this.tabCtrl.SelectedIndexChanged += new System.EventHandler(this.tabCtrl_SelectedIndexChanged);
            // 
            // tabSK
            // 
            this.tabSK.Controls.Add(this.huiyuanbh);
            this.tabSK.Controls.Add(this.label26);
            this.tabSK.Controls.Add(this.SKOpenDialgBox);
            this.tabSK.Controls.Add(this.ClassName);
            this.tabSK.Controls.Add(this.label9);
            this.tabSK.Controls.Add(this.SWSName);
            this.tabSK.Controls.Add(this.label2);
            this.tabSK.Controls.Add(this.LawyerCredit);
            this.tabSK.Controls.Add(this.label6);
            this.tabSK.Controls.Add(this.LawyerName);
            this.tabSK.Controls.Add(this.label4);
            this.tabSK.Controls.Add(this.Photo);
            this.tabSK.Controls.Add(this.SKlV);
            this.tabSK.Location = new System.Drawing.Point(4, 21);
            this.tabSK.Name = "tabSK";
            this.tabSK.Size = new System.Drawing.Size(651, 415);
            this.tabSK.TabIndex = 0;
            this.tabSK.Text = "自动刷卡";
            this.tabSK.UseVisualStyleBackColor = true;
            // 
            // huiyuanbh
            // 
            this.huiyuanbh.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.huiyuanbh.Font = new System.Drawing.Font("宋体", 10.5F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.huiyuanbh.Location = new System.Drawing.Point(503, 304);
            this.huiyuanbh.Name = "huiyuanbh";
            this.huiyuanbh.Size = new System.Drawing.Size(141, 23);
            this.huiyuanbh.TabIndex = 41;
            this.huiyuanbh.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label26
            // 
            this.label26.Font = new System.Drawing.Font("新宋体", 10.5F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label26.Location = new System.Drawing.Point(433, 304);
            this.label26.Name = "label26";
            this.label26.Size = new System.Drawing.Size(77, 23);
            this.label26.TabIndex = 40;
            this.label26.Text = "会员编号:";
            this.label26.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // SKOpenDialgBox
            // 
            this.SKOpenDialgBox.Location = new System.Drawing.Point(506, 173);
            this.SKOpenDialgBox.Name = "SKOpenDialgBox";
            this.SKOpenDialgBox.RightToLeft = System.Windows.Forms.RightToLeft.No;
            this.SKOpenDialgBox.Size = new System.Drawing.Size(110, 24);
            this.SKOpenDialgBox.TabIndex = 39;
            this.SKOpenDialgBox.Text = "刷卡记录需确认";
            this.SKOpenDialgBox.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.SKOpenDialgBox.CheckedChanged += new System.EventHandler(this.SKOpenDialgBox_CheckedChanged);
            // 
            // ClassName
            // 
            this.ClassName.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.ClassName.Font = new System.Drawing.Font("宋体", 9.5F, System.Drawing.FontStyle.Bold);
            this.ClassName.Location = new System.Drawing.Point(96, 8);
            this.ClassName.Name = "ClassName";
            this.ClassName.Size = new System.Drawing.Size(334, 36);
            this.ClassName.TabIndex = 37;
            this.ClassName.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label9
            // 
            this.label9.Font = new System.Drawing.Font("新宋体", 10.5F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label9.Location = new System.Drawing.Point(16, 13);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(72, 24);
            this.label9.TabIndex = 36;
            this.label9.Text = "考勤课程:";
            this.label9.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // SWSName
            // 
            this.SWSName.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.SWSName.Font = new System.Drawing.Font("宋体", 10.5F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.SWSName.Location = new System.Drawing.Point(436, 365);
            this.SWSName.Name = "SWSName";
            this.SWSName.Size = new System.Drawing.Size(208, 42);
            this.SWSName.TabIndex = 35;
            this.SWSName.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label2
            // 
            this.label2.Font = new System.Drawing.Font("宋体", 10.5F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label2.Location = new System.Drawing.Point(433, 341);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(91, 24);
            this.label2.TabIndex = 34;
            this.label2.Text = "所属事务所:";
            this.label2.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // LawyerCredit
            // 
            this.LawyerCredit.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.LawyerCredit.Font = new System.Drawing.Font("宋体", 10.5F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.LawyerCredit.Location = new System.Drawing.Point(503, 259);
            this.LawyerCredit.Name = "LawyerCredit";
            this.LawyerCredit.Size = new System.Drawing.Size(141, 23);
            this.LawyerCredit.TabIndex = 31;
            this.LawyerCredit.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label6
            // 
            this.label6.Font = new System.Drawing.Font("新宋体", 10.5F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label6.Location = new System.Drawing.Point(433, 259);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(77, 23);
            this.label6.TabIndex = 30;
            this.label6.Text = "执业证号:";
            this.label6.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // LawyerName
            // 
            this.LawyerName.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.LawyerName.Font = new System.Drawing.Font("宋体", 10.5F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.LawyerName.Location = new System.Drawing.Point(503, 219);
            this.LawyerName.Name = "LawyerName";
            this.LawyerName.Size = new System.Drawing.Size(141, 24);
            this.LawyerName.TabIndex = 29;
            this.LawyerName.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label4
            // 
            this.label4.Font = new System.Drawing.Font("新宋体", 10.5F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label4.Location = new System.Drawing.Point(433, 219);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(43, 24);
            this.label4.TabIndex = 28;
            this.label4.Text = "姓名:";
            this.label4.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // Photo
            // 
            this.Photo.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.Photo.Location = new System.Drawing.Point(499, 12);
            this.Photo.Name = "Photo";
            this.Photo.Size = new System.Drawing.Size(117, 155);
            this.Photo.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.Photo.TabIndex = 27;
            this.Photo.TabStop = false;
            // 
            // SKlV
            // 
            this.SKlV.Alignment = System.Windows.Forms.ListViewAlignment.Default;
            this.SKlV.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(224)))), ((int)(((byte)(224)))), ((int)(((byte)(224)))));
            this.SKlV.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.columnHeader1,
            this.columnHeader2,
            this.columnHeader5,
            this.columnHeader3,
            this.columnHeader4});
            this.SKlV.Font = new System.Drawing.Font("宋体", 10.5F);
            this.SKlV.ForeColor = System.Drawing.Color.Black;
            this.SKlV.FullRowSelect = true;
            this.SKlV.GridLines = true;
            this.SKlV.Location = new System.Drawing.Point(3, 47);
            this.SKlV.MultiSelect = false;
            this.SKlV.Name = "SKlV";
            this.SKlV.Size = new System.Drawing.Size(422, 360);
            this.SKlV.TabIndex = 24;
            this.SKlV.UseCompatibleStateImageBehavior = false;
            this.SKlV.View = System.Windows.Forms.View.Details;
            this.SKlV.SelectedIndexChanged += new System.EventHandler(this.SKlV_SelectedIndexChanged);
            // 
            // columnHeader1
            // 
            this.columnHeader1.Text = "卡号";
            this.columnHeader1.Width = 75;
            // 
            // columnHeader2
            // 
            this.columnHeader2.Text = "姓名";
            this.columnHeader2.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.columnHeader2.Width = 86;
            // 
            // columnHeader5
            // 
            this.columnHeader5.Text = "律师编号";
            this.columnHeader5.Width = 90;
            // 
            // columnHeader3
            // 
            this.columnHeader3.Text = "日期";
            this.columnHeader3.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.columnHeader3.Width = 89;
            // 
            // columnHeader4
            // 
            this.columnHeader4.Text = "时间";
            this.columnHeader4.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.columnHeader4.Width = 77;
            // 
            // tabSKByHand
            // 
            this.tabSKByHand.Controls.Add(this.lsbh);
            this.tabSKByHand.Controls.Add(this.label24);
            this.tabSKByHand.Controls.Add(this.groupBox3);
            this.tabSKByHand.Controls.Add(this.ErrorLb);
            this.tabSKByHand.Controls.Add(this.OKBtn);
            this.tabSKByHand.Controls.Add(this.label23);
            this.tabSKByHand.Controls.Add(this.label5);
            this.tabSKByHand.Controls.Add(this.label8);
            this.tabSKByHand.Controls.Add(this.SchBtn);
            this.tabSKByHand.Controls.Add(this.XMTB);
            this.tabSKByHand.Controls.Add(this.SWSNameTB);
            this.tabSKByHand.Controls.Add(this.DGLawInfo);
            this.tabSKByHand.Location = new System.Drawing.Point(4, 21);
            this.tabSKByHand.Name = "tabSKByHand";
            this.tabSKByHand.Padding = new System.Windows.Forms.Padding(3);
            this.tabSKByHand.Size = new System.Drawing.Size(651, 415);
            this.tabSKByHand.TabIndex = 3;
            this.tabSKByHand.Text = "手动刷卡";
            this.tabSKByHand.UseVisualStyleBackColor = true;
            // 
            // lsbh
            // 
            this.lsbh.Location = new System.Drawing.Point(210, 10);
            this.lsbh.Name = "lsbh";
            this.lsbh.Size = new System.Drawing.Size(87, 21);
            this.lsbh.TabIndex = 24;
            // 
            // label24
            // 
            this.label24.AutoSize = true;
            this.label24.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label24.Location = new System.Drawing.Point(145, 14);
            this.label24.Name = "label24";
            this.label24.Size = new System.Drawing.Size(70, 12);
            this.label24.TabIndex = 23;
            this.label24.Text = "律师编号：";
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.hzbh);
            this.groupBox3.Controls.Add(this.label25);
            this.groupBox3.Controls.Add(this.CreditNoTB);
            this.groupBox3.Controls.Add(this.KHTB);
            this.groupBox3.Controls.Add(this.ReqMinutesLb);
            this.groupBox3.Controls.Add(this.label14);
            this.groupBox3.Controls.Add(this.SKBHLb);
            this.groupBox3.Controls.Add(this.SWSNameLb);
            this.groupBox3.Controls.Add(this.LawyerNameLb);
            this.groupBox3.Controls.Add(this.LessonNameLb);
            this.groupBox3.Controls.Add(this.label15);
            this.groupBox3.Controls.Add(this.SKMinuteTB);
            this.groupBox3.Controls.Add(this.label16);
            this.groupBox3.Controls.Add(this.SKDTPicker);
            this.groupBox3.Controls.Add(this.label17);
            this.groupBox3.Controls.Add(this.label18);
            this.groupBox3.Controls.Add(this.label19);
            this.groupBox3.Controls.Add(this.label20);
            this.groupBox3.Controls.Add(this.label21);
            this.groupBox3.Controls.Add(this.label22);
            this.groupBox3.Location = new System.Drawing.Point(7, 30);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(303, 349);
            this.groupBox3.TabIndex = 21;
            this.groupBox3.TabStop = false;
            // 
            // hzbh
            // 
            this.hzbh.AutoSize = true;
            this.hzbh.Location = new System.Drawing.Point(224, 292);
            this.hzbh.Name = "hzbh";
            this.hzbh.Size = new System.Drawing.Size(47, 12);
            this.hzbh.TabIndex = 24;
            this.hzbh.Text = "label27";
            this.hzbh.Visible = false;
            // 
            // label25
            // 
            this.label25.AutoSize = true;
            this.label25.Font = new System.Drawing.Font("宋体", 10.5F);
            this.label25.Location = new System.Drawing.Point(36, 165);
            this.label25.Name = "label25";
            this.label25.Size = new System.Drawing.Size(77, 14);
            this.label25.TabIndex = 23;
            this.label25.Text = "律师编号：";
            // 
            // CreditNoTB
            // 
            this.CreditNoTB.AutoSize = true;
            this.CreditNoTB.Font = new System.Drawing.Font("宋体", 10.5F, System.Drawing.FontStyle.Bold);
            this.CreditNoTB.Location = new System.Drawing.Point(113, 227);
            this.CreditNoTB.Name = "CreditNoTB";
            this.CreditNoTB.Size = new System.Drawing.Size(15, 14);
            this.CreditNoTB.TabIndex = 22;
            this.CreditNoTB.Text = "a";
            // 
            // KHTB
            // 
            this.KHTB.AutoSize = true;
            this.KHTB.Font = new System.Drawing.Font("宋体", 10.5F, System.Drawing.FontStyle.Bold);
            this.KHTB.Location = new System.Drawing.Point(113, 196);
            this.KHTB.Name = "KHTB";
            this.KHTB.Size = new System.Drawing.Size(15, 14);
            this.KHTB.TabIndex = 21;
            this.KHTB.Text = "a";
            // 
            // ReqMinutesLb
            // 
            this.ReqMinutesLb.AutoSize = true;
            this.ReqMinutesLb.Font = new System.Drawing.Font("宋体", 10.5F, System.Drawing.FontStyle.Bold);
            this.ReqMinutesLb.Location = new System.Drawing.Point(109, 292);
            this.ReqMinutesLb.Name = "ReqMinutesLb";
            this.ReqMinutesLb.Size = new System.Drawing.Size(22, 14);
            this.ReqMinutesLb.TabIndex = 20;
            this.ReqMinutesLb.Text = "ａ";
            // 
            // label14
            // 
            this.label14.AutoSize = true;
            this.label14.Font = new System.Drawing.Font("宋体", 10.5F);
            this.label14.Location = new System.Drawing.Point(36, 292);
            this.label14.Name = "label14";
            this.label14.Size = new System.Drawing.Size(77, 14);
            this.label14.TabIndex = 19;
            this.label14.Text = "刷卡时长：";
            // 
            // SKBHLb
            // 
            this.SKBHLb.AutoSize = true;
            this.SKBHLb.Font = new System.Drawing.Font("宋体", 10.5F, System.Drawing.FontStyle.Bold);
            this.SKBHLb.Location = new System.Drawing.Point(113, 165);
            this.SKBHLb.Name = "SKBHLb";
            this.SKBHLb.Size = new System.Drawing.Size(15, 14);
            this.SKBHLb.TabIndex = 18;
            this.SKBHLb.Text = "a";
            // 
            // SWSNameLb
            // 
            this.SWSNameLb.Font = new System.Drawing.Font("宋体", 9.5F, System.Drawing.FontStyle.Bold);
            this.SWSNameLb.Location = new System.Drawing.Point(113, 79);
            this.SWSNameLb.Name = "SWSNameLb";
            this.SWSNameLb.Size = new System.Drawing.Size(188, 49);
            this.SWSNameLb.TabIndex = 17;
            this.SWSNameLb.Text = "ａ";
            this.SWSNameLb.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // LawyerNameLb
            // 
            this.LawyerNameLb.AutoSize = true;
            this.LawyerNameLb.Font = new System.Drawing.Font("宋体", 10.5F, System.Drawing.FontStyle.Bold);
            this.LawyerNameLb.Location = new System.Drawing.Point(113, 133);
            this.LawyerNameLb.Name = "LawyerNameLb";
            this.LawyerNameLb.Size = new System.Drawing.Size(22, 14);
            this.LawyerNameLb.TabIndex = 16;
            this.LawyerNameLb.Text = "ａ";
            // 
            // LessonNameLb
            // 
            this.LessonNameLb.AutoEllipsis = true;
            this.LessonNameLb.Font = new System.Drawing.Font("宋体", 9.5F, System.Drawing.FontStyle.Bold);
            this.LessonNameLb.Location = new System.Drawing.Point(113, 17);
            this.LessonNameLb.Name = "LessonNameLb";
            this.LessonNameLb.Size = new System.Drawing.Size(184, 54);
            this.LessonNameLb.TabIndex = 15;
            this.LessonNameLb.Text = "ａ";
            this.LessonNameLb.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label15
            // 
            this.label15.AutoSize = true;
            this.label15.Font = new System.Drawing.Font("宋体", 10.5F);
            this.label15.Location = new System.Drawing.Point(167, 325);
            this.label15.Name = "label15";
            this.label15.Size = new System.Drawing.Size(63, 14);
            this.label15.TabIndex = 14;
            this.label15.Text = "（分钟）";
            // 
            // SKMinuteTB
            // 
            this.SKMinuteTB.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.SKMinuteTB.Font = new System.Drawing.Font("宋体", 10.5F);
            this.SKMinuteTB.Location = new System.Drawing.Point(116, 320);
            this.SKMinuteTB.Name = "SKMinuteTB";
            this.SKMinuteTB.Size = new System.Drawing.Size(49, 23);
            this.SKMinuteTB.TabIndex = 13;
            this.SKMinuteTB.Text = "0";
            this.SKMinuteTB.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // label16
            // 
            this.label16.AutoSize = true;
            this.label16.Font = new System.Drawing.Font("宋体", 10.5F);
            this.label16.Location = new System.Drawing.Point(36, 325);
            this.label16.Name = "label16";
            this.label16.Size = new System.Drawing.Size(77, 14);
            this.label16.TabIndex = 12;
            this.label16.Text = "填写时长：";
            // 
            // SKDTPicker
            // 
            this.SKDTPicker.CustomFormat = "yyyy-MM-dd HH:mm:ss";
            this.SKDTPicker.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.SKDTPicker.Location = new System.Drawing.Point(112, 259);
            this.SKDTPicker.Name = "SKDTPicker";
            this.SKDTPicker.Size = new System.Drawing.Size(160, 21);
            this.SKDTPicker.TabIndex = 11;
            // 
            // label17
            // 
            this.label17.AutoSize = true;
            this.label17.Font = new System.Drawing.Font("宋体", 10.5F);
            this.label17.Location = new System.Drawing.Point(8, 259);
            this.label17.Name = "label17";
            this.label17.Size = new System.Drawing.Size(105, 14);
            this.label17.TabIndex = 9;
            this.label17.Text = "课程开始时间：";
            // 
            // label18
            // 
            this.label18.AutoSize = true;
            this.label18.Font = new System.Drawing.Font("宋体", 10.5F);
            this.label18.Location = new System.Drawing.Point(36, 37);
            this.label18.Name = "label18";
            this.label18.Size = new System.Drawing.Size(77, 14);
            this.label18.TabIndex = 3;
            this.label18.Text = "刷卡课程：";
            // 
            // label19
            // 
            this.label19.AutoSize = true;
            this.label19.Font = new System.Drawing.Font("宋体", 10.5F);
            this.label19.Location = new System.Drawing.Point(9, 227);
            this.label19.Name = "label19";
            this.label19.Size = new System.Drawing.Size(105, 14);
            this.label19.TabIndex = 7;
            this.label19.Text = "输入执业证号：";
            // 
            // label20
            // 
            this.label20.AutoSize = true;
            this.label20.Font = new System.Drawing.Font("宋体", 10.5F);
            this.label20.Location = new System.Drawing.Point(64, 196);
            this.label20.Name = "label20";
            this.label20.Size = new System.Drawing.Size(49, 14);
            this.label20.TabIndex = 6;
            this.label20.Text = "卡号：";
            // 
            // label21
            // 
            this.label21.AutoSize = true;
            this.label21.Font = new System.Drawing.Font("宋体", 10.5F);
            this.label21.Location = new System.Drawing.Point(36, 133);
            this.label21.Name = "label21";
            this.label21.Size = new System.Drawing.Size(77, 14);
            this.label21.TabIndex = 4;
            this.label21.Text = "律师姓名：";
            // 
            // label22
            // 
            this.label22.AutoSize = true;
            this.label22.Font = new System.Drawing.Font("宋体", 10.5F);
            this.label22.Location = new System.Drawing.Point(23, 96);
            this.label22.Name = "label22";
            this.label22.Size = new System.Drawing.Size(91, 14);
            this.label22.TabIndex = 5;
            this.label22.Text = "所属事务所：";
            // 
            // ErrorLb
            // 
            this.ErrorLb.AutoSize = true;
            this.ErrorLb.BackColor = System.Drawing.SystemColors.ActiveBorder;
            this.ErrorLb.Font = new System.Drawing.Font("宋体", 9F);
            this.ErrorLb.ForeColor = System.Drawing.Color.Red;
            this.ErrorLb.Location = new System.Drawing.Point(16, 385);
            this.ErrorLb.Name = "ErrorLb";
            this.ErrorLb.Size = new System.Drawing.Size(17, 12);
            this.ErrorLb.TabIndex = 22;
            this.ErrorLb.Text = "ａ";
            // 
            // OKBtn
            // 
            this.OKBtn.Location = new System.Drawing.Point(242, 385);
            this.OKBtn.Name = "OKBtn";
            this.OKBtn.Size = new System.Drawing.Size(68, 23);
            this.OKBtn.TabIndex = 19;
            this.OKBtn.Text = "确定";
            this.OKBtn.UseVisualStyleBackColor = true;
            this.OKBtn.Click += new System.EventHandler(this.OKBtn_Click);
            // 
            // label23
            // 
            this.label23.AutoSize = true;
            this.label23.Location = new System.Drawing.Point(121, 58);
            this.label23.Name = "label23";
            this.label23.Size = new System.Drawing.Size(0, 12);
            this.label23.TabIndex = 20;
            this.label23.Tag = "刷卡课程";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label5.Location = new System.Drawing.Point(457, 14);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(44, 12);
            this.label5.TabIndex = 13;
            this.label5.Text = "姓名：";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label8.Location = new System.Drawing.Point(303, 14);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(57, 12);
            this.label8.TabIndex = 12;
            this.label8.Text = "事务所：";
            // 
            // SchBtn
            // 
            this.SchBtn.Location = new System.Drawing.Point(573, 8);
            this.SchBtn.Name = "SchBtn";
            this.SchBtn.Size = new System.Drawing.Size(52, 23);
            this.SchBtn.TabIndex = 11;
            this.SchBtn.Text = "查询";
            this.SchBtn.UseVisualStyleBackColor = true;
            this.SchBtn.Click += new System.EventHandler(this.SchBtn_Click);
            // 
            // XMTB
            // 
            this.XMTB.Location = new System.Drawing.Point(505, 9);
            this.XMTB.Name = "XMTB";
            this.XMTB.Size = new System.Drawing.Size(64, 21);
            this.XMTB.TabIndex = 10;
            // 
            // SWSNameTB
            // 
            this.SWSNameTB.Location = new System.Drawing.Point(364, 9);
            this.SWSNameTB.Name = "SWSNameTB";
            this.SWSNameTB.Size = new System.Drawing.Size(87, 21);
            this.SWSNameTB.TabIndex = 9;
            // 
            // DGLawInfo
            // 
            this.DGLawInfo.BackgroundColor = System.Drawing.Color.FromArgb(((int)(((byte)(224)))), ((int)(((byte)(224)))), ((int)(((byte)(224)))));
            this.DGLawInfo.CaptionBackColor = System.Drawing.Color.WhiteSmoke;
            this.DGLawInfo.CaptionForeColor = System.Drawing.SystemColors.ControlText;
            this.DGLawInfo.CaptionVisible = false;
            this.DGLawInfo.DataMember = "";
            this.DGLawInfo.HeaderForeColor = System.Drawing.SystemColors.ControlText;
            this.DGLawInfo.Location = new System.Drawing.Point(316, 37);
            this.DGLawInfo.Name = "DGLawInfo";
            this.DGLawInfo.PreferredColumnWidth = 150;
            this.DGLawInfo.ReadOnly = true;
            this.DGLawInfo.RowHeadersVisible = false;
            this.DGLawInfo.Size = new System.Drawing.Size(330, 371);
            this.DGLawInfo.TabIndex = 8;
            this.DGLawInfo.TableStyles.AddRange(new System.Windows.Forms.DataGridTableStyle[] {
            this.LawInfoTableStyle});
            this.DGLawInfo.CurrentCellChanged += new System.EventHandler(this.DGLawInfo_CurrentCellChanged);
            // 
            // LawInfoTableStyle
            // 
            this.LawInfoTableStyle.DataGrid = this.DGLawInfo;
            this.LawInfoTableStyle.GridColumnStyles.AddRange(new System.Windows.Forms.DataGridColumnStyle[] {
            this.dataGridTextBoxColumn7,
            this.dataGridTextBoxColumn8,
            this.dataGridTextBoxColumn12,
            this.dataGridTextBoxColumn9,
            this.dataGridTextBoxColumn10,
            this.dataGridTextBoxColumn11});
            this.LawInfoTableStyle.HeaderForeColor = System.Drawing.SystemColors.ControlText;
            this.LawInfoTableStyle.MappingName = "LawyersInfo";
            this.LawInfoTableStyle.ReadOnly = true;
            // 
            // dataGridTextBoxColumn7
            // 
            this.dataGridTextBoxColumn7.Format = "";
            this.dataGridTextBoxColumn7.FormatInfo = null;
            this.dataGridTextBoxColumn7.HeaderText = "律师事务所";
            this.dataGridTextBoxColumn7.MappingName = "SWSName";
            this.dataGridTextBoxColumn7.ReadOnly = true;
            this.dataGridTextBoxColumn7.Width = 120;
            // 
            // dataGridTextBoxColumn8
            // 
            this.dataGridTextBoxColumn8.Format = "";
            this.dataGridTextBoxColumn8.FormatInfo = null;
            this.dataGridTextBoxColumn8.HeaderText = "姓名";
            this.dataGridTextBoxColumn8.MappingName = "XM";
            this.dataGridTextBoxColumn8.ReadOnly = true;
            this.dataGridTextBoxColumn8.Width = 75;
            // 
            // dataGridTextBoxColumn12
            // 
            this.dataGridTextBoxColumn12.Format = "";
            this.dataGridTextBoxColumn12.FormatInfo = null;
            this.dataGridTextBoxColumn12.HeaderText = "会员编号";
            this.dataGridTextBoxColumn12.MappingName = "mobile";
            this.dataGridTextBoxColumn12.ReadOnly = true;
            this.dataGridTextBoxColumn12.Width = 75;
            // 
            // dataGridTextBoxColumn9
            // 
            this.dataGridTextBoxColumn9.Format = "";
            this.dataGridTextBoxColumn9.FormatInfo = null;
            this.dataGridTextBoxColumn9.MappingName = "KH";
            this.dataGridTextBoxColumn9.ReadOnly = true;
            this.dataGridTextBoxColumn9.Width = 0;
            // 
            // dataGridTextBoxColumn10
            // 
            this.dataGridTextBoxColumn10.Format = "";
            this.dataGridTextBoxColumn10.FormatInfo = null;
            this.dataGridTextBoxColumn10.MappingName = "LawCreditNO";
            this.dataGridTextBoxColumn10.ReadOnly = true;
            this.dataGridTextBoxColumn10.Width = 0;
            // 
            // dataGridTextBoxColumn11
            // 
            this.dataGridTextBoxColumn11.Format = "";
            this.dataGridTextBoxColumn11.FormatInfo = null;
            this.dataGridTextBoxColumn11.MappingName = "bh";
            this.dataGridTextBoxColumn11.Width = 0;
            // 
            // tabKQTJ
            // 
            this.tabKQTJ.BackColor = System.Drawing.Color.LightGray;
            this.tabKQTJ.Controls.Add(this.btDelSKRecs);
            this.tabKQTJ.Controls.Add(this.rBLawyer);
            this.tabKQTJ.Controls.Add(this.rBSWS);
            this.tabKQTJ.Controls.Add(this.cBSKLessons);
            this.tabKQTJ.Controls.Add(this.label11);
            this.tabKQTJ.Controls.Add(this.dGKQ);
            this.tabKQTJ.Location = new System.Drawing.Point(4, 21);
            this.tabKQTJ.Name = "tabKQTJ";
            this.tabKQTJ.Size = new System.Drawing.Size(651, 415);
            this.tabKQTJ.TabIndex = 2;
            this.tabKQTJ.Text = "考勤统计";
            this.tabKQTJ.UseVisualStyleBackColor = true;
            // 
            // btDelSKRecs
            // 
            this.btDelSKRecs.Location = new System.Drawing.Point(480, 384);
            this.btDelSKRecs.Name = "btDelSKRecs";
            this.btDelSKRecs.Size = new System.Drawing.Size(144, 24);
            this.btDelSKRecs.TabIndex = 6;
            this.btDelSKRecs.Text = "删除该课程的本地考勤";
            this.btDelSKRecs.Click += new System.EventHandler(this.btDelSKRecs_Click);
            // 
            // rBLawyer
            // 
            this.rBLawyer.Location = new System.Drawing.Point(520, 16);
            this.rBLawyer.Name = "rBLawyer";
            this.rBLawyer.Size = new System.Drawing.Size(72, 24);
            this.rBLawyer.TabIndex = 5;
            this.rBLawyer.Text = "律师考勤";
            this.rBLawyer.CheckedChanged += new System.EventHandler(this.rBLawyer_CheckedChanged);
            // 
            // rBSWS
            // 
            this.rBSWS.Checked = true;
            this.rBSWS.Location = new System.Drawing.Point(424, 16);
            this.rBSWS.Name = "rBSWS";
            this.rBSWS.Size = new System.Drawing.Size(88, 24);
            this.rBSWS.TabIndex = 4;
            this.rBSWS.TabStop = true;
            this.rBSWS.Text = "事务所考勤";
            // 
            // cBSKLessons
            // 
            this.cBSKLessons.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cBSKLessons.Location = new System.Drawing.Point(80, 16);
            this.cBSKLessons.Name = "cBSKLessons";
            this.cBSKLessons.Size = new System.Drawing.Size(328, 20);
            this.cBSKLessons.TabIndex = 3;
            this.cBSKLessons.SelectedIndexChanged += new System.EventHandler(this.cBSKLessons_SelectionChangeCommitted);
            // 
            // label11
            // 
            this.label11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label11.Location = new System.Drawing.Point(8, 15);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(72, 23);
            this.label11.TabIndex = 2;
            this.label11.Text = "考勤课程：";
            this.label11.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // dGKQ
            // 
            this.dGKQ.BackgroundColor = System.Drawing.Color.FromArgb(((int)(((byte)(224)))), ((int)(((byte)(224)))), ((int)(((byte)(224)))));
            this.dGKQ.CaptionBackColor = System.Drawing.Color.WhiteSmoke;
            this.dGKQ.CaptionForeColor = System.Drawing.SystemColors.ControlText;
            this.dGKQ.DataMember = "";
            this.dGKQ.HeaderForeColor = System.Drawing.SystemColors.ControlText;
            this.dGKQ.Location = new System.Drawing.Point(8, 48);
            this.dGKQ.Name = "dGKQ";
            this.dGKQ.PreferredColumnWidth = 150;
            this.dGKQ.ReadOnly = true;
            this.dGKQ.RowHeadersVisible = false;
            this.dGKQ.Size = new System.Drawing.Size(616, 328);
            this.dGKQ.TabIndex = 1;
            this.dGKQ.TableStyles.AddRange(new System.Windows.Forms.DataGridTableStyle[] {
            this.dataGridTableStyleSWS,
            this.dataGridTableStyleLawyers});
            // 
            // dataGridTableStyleSWS
            // 
            this.dataGridTableStyleSWS.DataGrid = this.dGKQ;
            this.dataGridTableStyleSWS.GridColumnStyles.AddRange(new System.Windows.Forms.DataGridColumnStyle[] {
            this.dataGridTextBoxColumn1,
            this.dataGridTextBoxColumn2});
            this.dataGridTableStyleSWS.HeaderForeColor = System.Drawing.SystemColors.ControlText;
            this.dataGridTableStyleSWS.MappingName = "SKSWS";
            this.dataGridTableStyleSWS.ReadOnly = true;
            // 
            // dataGridTextBoxColumn1
            // 
            this.dataGridTextBoxColumn1.Format = "";
            this.dataGridTextBoxColumn1.FormatInfo = null;
            this.dataGridTextBoxColumn1.HeaderText = "事务所名称";
            this.dataGridTextBoxColumn1.MappingName = "SWSName";
            this.dataGridTextBoxColumn1.ReadOnly = true;
            this.dataGridTextBoxColumn1.Width = 460;
            // 
            // dataGridTextBoxColumn2
            // 
            this.dataGridTextBoxColumn2.Format = "";
            this.dataGridTextBoxColumn2.FormatInfo = null;
            this.dataGridTextBoxColumn2.HeaderText = "课程参加人数";
            this.dataGridTextBoxColumn2.MappingName = "LawyerIDs";
            this.dataGridTextBoxColumn2.ReadOnly = true;
            this.dataGridTextBoxColumn2.Width = 120;
            // 
            // dataGridTableStyleLawyers
            // 
            this.dataGridTableStyleLawyers.DataGrid = this.dGKQ;
            this.dataGridTableStyleLawyers.GridColumnStyles.AddRange(new System.Windows.Forms.DataGridColumnStyle[] {
            this.dataGridTextBoxColumn3,
            this.dataGridTextBoxColumn4,
            this.dataGridTextBoxColumn5,
            this.dataGridTextBoxColumn6});
            this.dataGridTableStyleLawyers.HeaderForeColor = System.Drawing.SystemColors.ControlText;
            this.dataGridTableStyleLawyers.MappingName = "SKLawyers";
            this.dataGridTableStyleLawyers.ReadOnly = true;
            // 
            // dataGridTextBoxColumn3
            // 
            this.dataGridTextBoxColumn3.Format = "";
            this.dataGridTextBoxColumn3.FormatInfo = null;
            this.dataGridTextBoxColumn3.HeaderText = "所属事务所";
            this.dataGridTextBoxColumn3.MappingName = "SWSName";
            this.dataGridTextBoxColumn3.ReadOnly = true;
            this.dataGridTextBoxColumn3.Width = 260;
            // 
            // dataGridTextBoxColumn4
            // 
            this.dataGridTextBoxColumn4.Format = "";
            this.dataGridTextBoxColumn4.FormatInfo = null;
            this.dataGridTextBoxColumn4.HeaderText = "姓名";
            this.dataGridTextBoxColumn4.MappingName = "XM";
            this.dataGridTextBoxColumn4.ReadOnly = true;
            this.dataGridTextBoxColumn4.Width = 75;
            // 
            // dataGridTextBoxColumn5
            // 
            this.dataGridTextBoxColumn5.Format = "";
            this.dataGridTextBoxColumn5.FormatInfo = null;
            this.dataGridTextBoxColumn5.HeaderText = "进场时间";
            this.dataGridTextBoxColumn5.MappingName = "minSJ";
            this.dataGridTextBoxColumn5.ReadOnly = true;
            this.dataGridTextBoxColumn5.Width = 115;
            // 
            // dataGridTextBoxColumn6
            // 
            this.dataGridTextBoxColumn6.Format = "";
            this.dataGridTextBoxColumn6.FormatInfo = null;
            this.dataGridTextBoxColumn6.HeaderText = "离场时间";
            this.dataGridTextBoxColumn6.MappingName = "maxSJ";
            this.dataGridTextBoxColumn6.ReadOnly = true;
            this.dataGridTextBoxColumn6.Width = 115;
            // 
            // tabAdmin
            // 
            this.tabAdmin.Controls.Add(this.groupBox2);
            this.tabAdmin.Controls.Add(this.groupBox4);
            this.tabAdmin.Controls.Add(this.groupBox1);
            this.tabAdmin.Location = new System.Drawing.Point(4, 21);
            this.tabAdmin.Name = "tabAdmin";
            this.tabAdmin.Size = new System.Drawing.Size(651, 415);
            this.tabAdmin.TabIndex = 1;
            this.tabAdmin.Text = "系统管理";
            this.tabAdmin.UseVisualStyleBackColor = true;
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.LawyerPhoto);
            this.groupBox2.Controls.Add(this.DownLessonInfo);
            this.groupBox2.Controls.Add(this.PSWord);
            this.groupBox2.Controls.Add(this.UserID);
            this.groupBox2.Controls.Add(this.label10);
            this.groupBox2.Controls.Add(this.label7);
            this.groupBox2.Controls.Add(this.WebName);
            this.groupBox2.Controls.Add(this.label1);
            this.groupBox2.Controls.Add(this.GetLawyersDataBtn);
            this.groupBox2.Controls.Add(this.DownCardNo);
            this.groupBox2.Location = new System.Drawing.Point(40, 37);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(552, 104);
            this.groupBox2.TabIndex = 11;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "本地备份";
            // 
            // LawyerPhoto
            // 
            this.LawyerPhoto.Location = new System.Drawing.Point(122, 64);
            this.LawyerPhoto.Name = "LawyerPhoto";
            this.LawyerPhoto.RightToLeft = System.Windows.Forms.RightToLeft.No;
            this.LawyerPhoto.Size = new System.Drawing.Size(73, 24);
            this.LawyerPhoto.TabIndex = 19;
            this.LawyerPhoto.Text = "律师照片";
            this.LawyerPhoto.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.LawyerPhoto.CheckedChanged += new System.EventHandler(this.LawyerPhoto_CheckedChanged);
            // 
            // DownLessonInfo
            // 
            this.DownLessonInfo.Location = new System.Drawing.Point(206, 64);
            this.DownLessonInfo.Name = "DownLessonInfo";
            this.DownLessonInfo.RightToLeft = System.Windows.Forms.RightToLeft.No;
            this.DownLessonInfo.Size = new System.Drawing.Size(104, 24);
            this.DownLessonInfo.TabIndex = 17;
            this.DownLessonInfo.Text = "培训课程资料";
            this.DownLessonInfo.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.DownLessonInfo.CheckedChanged += new System.EventHandler(this.DownLessonInfo_CheckedChanged);
            // 
            // PSWord
            // 
            this.PSWord.Location = new System.Drawing.Point(444, 22);
            this.PSWord.Name = "PSWord";
            this.PSWord.PasswordChar = '*';
            this.PSWord.Size = new System.Drawing.Size(64, 21);
            this.PSWord.TabIndex = 16;
            this.PSWord.Visible = false;
            this.PSWord.TextChanged += new System.EventHandler(this.PSWord_TextChanged);
            // 
            // UserID
            // 
            this.UserID.Location = new System.Drawing.Point(324, 22);
            this.UserID.Name = "UserID";
            this.UserID.Size = new System.Drawing.Size(96, 21);
            this.UserID.TabIndex = 15;
            this.UserID.Visible = false;
            this.UserID.TextChanged += new System.EventHandler(this.UserID_TextChanged);
            // 
            // label10
            // 
            this.label10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label10.Location = new System.Drawing.Point(428, 24);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(8, 16);
            this.label10.TabIndex = 14;
            this.label10.Text = "/";
            this.label10.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.label10.Visible = false;
            // 
            // label7
            // 
            this.label7.Location = new System.Drawing.Point(236, 26);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(88, 16);
            this.label7.TabIndex = 13;
            this.label7.Text = "用户名/密码：";
            this.label7.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.label7.Visible = false;
            // 
            // WebName
            // 
            this.WebName.Location = new System.Drawing.Point(78, 22);
            this.WebName.Name = "WebName";
            this.WebName.Size = new System.Drawing.Size(152, 21);
            this.WebName.TabIndex = 4;
            this.WebName.Visible = false;
            this.WebName.TextChanged += new System.EventHandler(this.WebName_TextChanged);
            // 
            // label1
            // 
            this.label1.Location = new System.Drawing.Point(6, 26);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(71, 16);
            this.label1.TabIndex = 3;
            this.label1.Text = "站点名称：";
            this.label1.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.label1.Visible = false;
            // 
            // GetLawyersDataBtn
            // 
            this.GetLawyersDataBtn.Location = new System.Drawing.Point(448, 64);
            this.GetLawyersDataBtn.Name = "GetLawyersDataBtn";
            this.GetLawyersDataBtn.Size = new System.Drawing.Size(80, 23);
            this.GetLawyersDataBtn.TabIndex = 9;
            this.GetLawyersDataBtn.Text = "开始备份";
            this.GetLawyersDataBtn.Click += new System.EventHandler(this.GetLawyersDataBtn_Click);
            // 
            // DownCardNo
            // 
            this.DownCardNo.Location = new System.Drawing.Point(16, 64);
            this.DownCardNo.Name = "DownCardNo";
            this.DownCardNo.RightToLeft = System.Windows.Forms.RightToLeft.No;
            this.DownCardNo.Size = new System.Drawing.Size(104, 24);
            this.DownCardNo.TabIndex = 12;
            this.DownCardNo.Text = "律师卡号资料";
            this.DownCardNo.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.DownCardNo.CheckedChanged += new System.EventHandler(this.DownCardNo_CheckedChanged);
            // 
            // groupBox4
            // 
            this.groupBox4.Controls.Add(this.UploadBtn);
            this.groupBox4.Controls.Add(this.RemoteWrite);
            this.groupBox4.Location = new System.Drawing.Point(40, 169);
            this.groupBox4.Name = "groupBox4";
            this.groupBox4.Size = new System.Drawing.Size(552, 70);
            this.groupBox4.TabIndex = 12;
            this.groupBox4.TabStop = false;
            this.groupBox4.Text = "刷卡数据上传";
            // 
            // UploadBtn
            // 
            this.UploadBtn.Location = new System.Drawing.Point(437, 28);
            this.UploadBtn.Name = "UploadBtn";
            this.UploadBtn.Size = new System.Drawing.Size(91, 23);
            this.UploadBtn.TabIndex = 10;
            this.UploadBtn.Text = "上传考勤数据";
            this.UploadBtn.Click += new System.EventHandler(this.UploadBtn_Click);
            // 
            // RemoteWrite
            // 
            this.RemoteWrite.Location = new System.Drawing.Point(16, 27);
            this.RemoteWrite.Name = "RemoteWrite";
            this.RemoteWrite.RightToLeft = System.Windows.Forms.RightToLeft.No;
            this.RemoteWrite.Size = new System.Drawing.Size(128, 24);
            this.RemoteWrite.TabIndex = 0;
            this.RemoteWrite.Text = "同步上传刷卡数据";
            this.RemoteWrite.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.RemoteWrite.Visible = false;
            this.RemoteWrite.CheckedChanged += new System.EventHandler(this.RemoteWrite_CheckedChanged);
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.OldLessonType);
            this.groupBox1.Controls.Add(this.KCBHComboBox);
            this.groupBox1.Controls.Add(this.label3);
            this.groupBox1.Controls.Add(this.HaveTMode);
            this.groupBox1.Location = new System.Drawing.Point(40, 264);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(552, 104);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "系统设置";
            // 
            // OldLessonType
            // 
            this.OldLessonType.Location = new System.Drawing.Point(441, 30);
            this.OldLessonType.Name = "OldLessonType";
            this.OldLessonType.RightToLeft = System.Windows.Forms.RightToLeft.No;
            this.OldLessonType.Size = new System.Drawing.Size(103, 24);
            this.OldLessonType.TabIndex = 15;
            this.OldLessonType.Text = "显示往期课程";
            this.OldLessonType.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.OldLessonType.CheckedChanged += new System.EventHandler(this.OldLessonType_CheckedChanged);
            // 
            // KCBHComboBox
            // 
            this.KCBHComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.KCBHComboBox.Location = new System.Drawing.Point(82, 31);
            this.KCBHComboBox.Name = "KCBHComboBox";
            this.KCBHComboBox.Size = new System.Drawing.Size(351, 20);
            this.KCBHComboBox.TabIndex = 12;
            this.KCBHComboBox.SelectedValueChanged += new System.EventHandler(this.KCBHComboBox_SelectedValueChanged);
            // 
            // label3
            // 
            this.label3.Location = new System.Drawing.Point(16, 35);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(65, 16);
            this.label3.TabIndex = 10;
            this.label3.Text = "考勤课程：";
            // 
            // HaveTMode
            // 
            this.HaveTMode.Location = new System.Drawing.Point(16, 64);
            this.HaveTMode.Name = "HaveTMode";
            this.HaveTMode.RightToLeft = System.Windows.Forms.RightToLeft.No;
            this.HaveTMode.Size = new System.Drawing.Size(84, 24);
            this.HaveTMode.TabIndex = 11;
            this.HaveTMode.Text = "模拟刷卡";
            this.HaveTMode.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.HaveTMode.CheckedChanged += new System.EventHandler(this.HaveTMode_CheckedChanged);
            // 
            // label13
            // 
            this.label13.BackColor = System.Drawing.Color.Transparent;
            this.label13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label13.Location = new System.Drawing.Point(124, 465);
            this.label13.Name = "label13";
            this.label13.Size = new System.Drawing.Size(43, 16);
            this.label13.TabIndex = 36;
            this.label13.Text = "已上传";
            this.label13.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // label12
            // 
            this.label12.BackColor = System.Drawing.Color.Transparent;
            this.label12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label12.Location = new System.Drawing.Point(11, 465);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(48, 16);
            this.label12.TabIndex = 35;
            this.label12.Text = "已刷卡";
            this.label12.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // SKNum
            // 
            this.SKNum.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(224)))), ((int)(((byte)(224)))), ((int)(((byte)(224)))));
            this.SKNum.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.SKNum.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.SKNum.ForeColor = System.Drawing.Color.Red;
            this.SKNum.Location = new System.Drawing.Point(60, 465);
            this.SKNum.Name = "SKNum";
            this.SKNum.Size = new System.Drawing.Size(56, 16);
            this.SKNum.TabIndex = 34;
            this.SKNum.Text = "0";
            this.SKNum.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // AutoExportNum
            // 
            this.AutoExportNum.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(224)))), ((int)(((byte)(224)))), ((int)(((byte)(224)))));
            this.AutoExportNum.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.AutoExportNum.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.AutoExportNum.ForeColor = System.Drawing.Color.Red;
            this.AutoExportNum.Location = new System.Drawing.Point(167, 465);
            this.AutoExportNum.Name = "AutoExportNum";
            this.AutoExportNum.Size = new System.Drawing.Size(56, 16);
            this.AutoExportNum.TabIndex = 33;
            this.AutoExportNum.Text = "0";
            this.AutoExportNum.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // PBLine
            // 
            this.PBLine.ForeColor = System.Drawing.Color.Red;
            this.PBLine.Location = new System.Drawing.Point(508, 465);
            this.PBLine.Name = "PBLine";
            this.PBLine.Size = new System.Drawing.Size(147, 16);
            this.PBLine.TabIndex = 32;
            this.PBLine.Visible = false;
            // 
            // ShowState
            // 
            this.ShowState.BackColor = System.Drawing.Color.Transparent;
            this.ShowState.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.ShowState.ForeColor = System.Drawing.Color.Red;
            this.ShowState.Location = new System.Drawing.Point(235, 465);
            this.ShowState.Name = "ShowState";
            this.ShowState.Size = new System.Drawing.Size(266, 16);
            this.ShowState.TabIndex = 37;
            this.ShowState.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // SKSystem
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(668, 491);
            this.Controls.Add(this.label13);
            this.Controls.Add(this.label12);
            this.Controls.Add(this.SKNum);
            this.Controls.Add(this.AutoExportNum);
            this.Controls.Add(this.PBLine);
            this.Controls.Add(this.ShowState);
            this.Controls.Add(this.tabCtrl);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "SKSystem";
            this.Text = "律师行业协会培训刷卡系统(V2.1)";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.SKSystem_FormClosing);
            this.tabCtrl.ResumeLayout(false);
            this.tabSK.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.Photo)).EndInit();
            this.tabSKByHand.ResumeLayout(false);
            this.tabSKByHand.PerformLayout();
            this.groupBox3.ResumeLayout(false);
            this.groupBox3.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DGLawInfo)).EndInit();
            this.tabKQTJ.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dGKQ)).EndInit();
            this.tabAdmin.ResumeLayout(false);
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            this.groupBox4.ResumeLayout(false);
            this.groupBox1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tabCtrl;
        private System.Windows.Forms.TabPage tabSK;
        private System.Windows.Forms.CheckBox SKOpenDialgBox;
        private System.Windows.Forms.Label ClassName;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label SWSName;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label LawyerCredit;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label LawyerName;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.PictureBox Photo;
        private System.Windows.Forms.ListView SKlV;
        private System.Windows.Forms.ColumnHeader columnHeader1;
        private System.Windows.Forms.ColumnHeader columnHeader2;
        private System.Windows.Forms.ColumnHeader columnHeader3;
        private System.Windows.Forms.ColumnHeader columnHeader4;
        private System.Windows.Forms.TabPage tabKQTJ;
        private System.Windows.Forms.Button btDelSKRecs;
        private System.Windows.Forms.RadioButton rBLawyer;
        private System.Windows.Forms.RadioButton rBSWS;
        private System.Windows.Forms.ComboBox cBSKLessons;
        private System.Windows.Forms.Label label11;
        private System.Windows.Forms.DataGrid dGKQ;
        private System.Windows.Forms.TabPage tabAdmin;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.CheckBox LawyerPhoto;
        private System.Windows.Forms.CheckBox DownLessonInfo;
        private System.Windows.Forms.TextBox PSWord;
        private System.Windows.Forms.TextBox UserID;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox WebName;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button GetLawyersDataBtn;
        private System.Windows.Forms.CheckBox DownCardNo;
        private System.Windows.Forms.GroupBox groupBox4;
        private System.Windows.Forms.Button UploadBtn;
        private System.Windows.Forms.CheckBox RemoteWrite;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.CheckBox OldLessonType;
        private System.Windows.Forms.ComboBox KCBHComboBox;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.CheckBox HaveTMode;
        private System.Windows.Forms.Label label13;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.Label SKNum;
        private System.Windows.Forms.Label AutoExportNum;
        private System.Windows.Forms.ProgressBar PBLine;
        private System.Windows.Forms.Label ShowState;
        private System.Windows.Forms.DataGridTableStyle dataGridTableStyleSWS;
        private System.Windows.Forms.DataGridTextBoxColumn dataGridTextBoxColumn1;
        private System.Windows.Forms.DataGridTextBoxColumn dataGridTextBoxColumn2;
        private System.Windows.Forms.DataGridTableStyle dataGridTableStyleLawyers;
        private System.Windows.Forms.DataGridTextBoxColumn dataGridTextBoxColumn3;
        private System.Windows.Forms.DataGridTextBoxColumn dataGridTextBoxColumn4;
        private System.Windows.Forms.DataGridTextBoxColumn dataGridTextBoxColumn5;
        private System.Windows.Forms.DataGridTextBoxColumn dataGridTextBoxColumn6;
        private System.Windows.Forms.TabPage tabSKByHand;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Button SchBtn;
        private System.Windows.Forms.TextBox XMTB;
        private System.Windows.Forms.TextBox SWSNameTB;
        private System.Windows.Forms.DataGrid DGLawInfo;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.Label ReqMinutesLb;
        private System.Windows.Forms.Label label14;
        private System.Windows.Forms.Label SKBHLb;
        private System.Windows.Forms.Label SWSNameLb;
        private System.Windows.Forms.Label LawyerNameLb;
        private System.Windows.Forms.Label LessonNameLb;
        private System.Windows.Forms.Label label15;
        private System.Windows.Forms.TextBox SKMinuteTB;
        private System.Windows.Forms.Label label16;
        private System.Windows.Forms.DateTimePicker SKDTPicker;
        private System.Windows.Forms.Label label17;
        private System.Windows.Forms.Label label18;
        private System.Windows.Forms.Label label19;
        private System.Windows.Forms.Label label20;
        private System.Windows.Forms.Label label21;
        private System.Windows.Forms.Label label22;
        private System.Windows.Forms.Label ErrorLb;
        private System.Windows.Forms.Button OKBtn;
        private System.Windows.Forms.Label label23;
        private System.Windows.Forms.DataGridTableStyle LawInfoTableStyle;
        private System.Windows.Forms.DataGridTextBoxColumn dataGridTextBoxColumn7;
        private System.Windows.Forms.DataGridTextBoxColumn dataGridTextBoxColumn8;
        private System.Windows.Forms.DataGridTextBoxColumn dataGridTextBoxColumn12;
        private System.Windows.Forms.Label KHTB;
        private System.Windows.Forms.Label CreditNoTB;
        private System.Windows.Forms.DataGridTextBoxColumn dataGridTextBoxColumn9;
        private System.Windows.Forms.DataGridTextBoxColumn dataGridTextBoxColumn10;
        private System.Windows.Forms.DataGridTextBoxColumn dataGridTextBoxColumn11;
        private System.Windows.Forms.TextBox lsbh;
        private System.Windows.Forms.Label label24;
        private System.Windows.Forms.Label label25;
        private System.Windows.Forms.Label label26;
        private System.Windows.Forms.Label huiyuanbh;
        private System.Windows.Forms.ColumnHeader columnHeader5;
        private System.Windows.Forms.Label hzbh;

    }
}