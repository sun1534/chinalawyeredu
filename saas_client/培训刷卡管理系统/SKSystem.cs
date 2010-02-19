using System;
using System.Net;
using System.IO;
using System.Threading;
using System.Text;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.Data.OleDb;
using System.Data.SqlClient;
using System.Runtime.InteropServices;
namespace 培训刷卡管理系统
{
    /// <summary>
    /// Form1 的摘要说明。
    /// </summary>
    /// 

    public partial class SKSystem : System.Windows.Forms.Form
    {
        private Access SKDB;
        private string INIFile;
        private SKProcess SKP;
        private Thread TDSKMon, TDTestMode, TDAutoRemoteWrite;
        private string BasePath;

        private string SKClassID;

        private DataSet TableDS;

        private ListViewSorter LVSorter;

        public SKSystem()
        {
            string EPath = Application.ExecutablePath;

            BasePath = EPath.Substring(0, EPath.LastIndexOf(@"\"));
            INIFile = BasePath + @"\SK.ini";
            SKDB = new Access();
            SKP = new SKProcess();
            TableDS = new DataSet();

            System.Windows.Forms.Control.CheckForIllegalCrossThreadCalls = false;

            SKClassID = INIReadValue("系统设置", "考勤的课程编号");
            Logger.LogInfo("考勤的课程编号:::" + SKClassID);

            //
            // Windows 窗体设计器支持所必需的
            //
            InitializeComponent();

            WebName.Text = INIReadValue("系统设置", "WebName");
            PSWord.Text = INIReadValue("系统设置", "PSWord");
            UserID.Text = INIReadValue("系统设置", "UserID");
            string cpuid = HardwareInfo.getCpuId();
            //if (clientKey == null||clientKey.Equals("")||!cpuid.Equals(clientKey))
            //{
            //    INIWriteValue("系统设置", "ClientKey", cpuid);
            //}
            ElearningInterace.cpuid = cpuid;

            //AutoGetSPData.Checked=INIReadValue("系统设置","AutoGetSPData")=="YES"?true:false;
            //TestMode.Checked=INIReadValue("系统设置","TestMode")=="YES"?true:false;

            DownCardNo.Checked = INIReadValue("系统设置", "DownCardNo") == "YES" ? true : false;
            DownLessonInfo.Checked = INIReadValue("系统设置", "DownLessonInfo") == "YES" ? true : false;
            LawyerPhoto.Checked = INIReadValue("系统设置", "LawyerPhoto") == "YES" ? true : false;
            SKOpenDialgBox.Checked = INIReadValue("系统设置", "SKOpenDialgBox") == "YES" ? true : false;
            OldLessonType.Checked = INIReadValue("系统设置", "OldLessonType") == "YES" ? true : false;

            //RemoteWrite.Checked=INIReadValue("系统设置","RemoteWrite")=="YES"?true:false;

            LVSorter = new ListViewSorter();
            SKlV.ListViewItemSorter = LVSorter;
            LVSorter.SortOrder = System.Windows.Forms.SortOrder.Descending;
            LVSorter.SortColumn = 3;
            //    MessageBox.Show("==============--------------------");
            InitSKClassID(true, OldLessonType.Checked);
            Logger.LogInfo("LINE:78::初始化课程设置成功....CPUID:" + cpuid);

            SKDB.CleanYSKQ(); //清除原始刷卡记录
            Logger.LogInfo("LINE:81::清除原始刷卡记录成功....");

            TDSKMon = new Thread(new ThreadStart(SKMonitor));
            TDSKMon.IsBackground = true;
            TDSKMon.Start();

            TDTestMode = null;

            //SKByHand Section
            SKBHLb.Text = "";
            LessonNameLb.Text = "";
            SWSNameLb.Text = "";
            LawyerNameLb.Text = "";
            ErrorLb.Text = "";
            ReqMinutesLb.Text = "";
            KHTB.Text = "";
            CreditNoTB.Text = "";
            hzbh.Text = "";

        }

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// 

        private bool SKDialgBoxChked(bool IsShow, string CardNo, string LawyerName)
        {
            if (IsShow)
            {
                ListViewItem FndItem = SKlV.FindItemWithText(CardNo);
                if (FndItem == null)
                {
                    string Message = LawyerName + " 的刷卡记录有效吗？";
                    string Caption = "刷卡记录确认";
                    MessageBoxButtons Buttons = MessageBoxButtons.YesNo;
                    DialogResult Result;

                    // Displays the MessageBox.

                    Result = MessageBox.Show(this, Message, Caption, Buttons);

                    return Result == DialogResult.Yes ? true : false;
                }
            }

            return true;
        }

        public void SKMonitor()
        {
            OleDbDataReader SKRec;

            if (SKClassID == "")
            {
                while (SKClassID == "") ;
            }


            string IDList = "0";

            while (true)
            {


                SKRec = SKDB.ExecuteQuery(string.Format("select yskq.ID,yskq.rq,yskq.sj,yskq.SKMode,lawyers.xm,lawyers.kh,lawyers.SWSName,lawyers.LawCreditNo,lawyers.mobile,lawyers.PhotoURL" +
                                          " from yskq,lawyers where yskq.bh=lawyers.bh and yskq.ID not in ({0}) order by yskq.rq,yskq.sj", IDList));


                while (SKRec.HasRows & SKRec.Read())
                {
                    string RQ, SJ;

                    IDList += (IDList != "" ? "," : "") + SKRec["ID"].ToString();
                    RQ = ((DateTime)SKRec["rq"]).ToString("yyyy-MM-dd");
                    SJ = SKRec["sj"].ToString().Replace("-", ":");



                    LawyerName.Text = SKRec["xm"].ToString();
                    LawyerCredit.Text = SKRec["LawCreditNo"].ToString();
                    SWSName.Text = SKRec["SWSName"].ToString().Replace("律师事务所", "");
                    huiyuanbh.Text = SKRec["mobile"].ToString();

                    ShowPhoto(Photo, SKRec["PhotoURL"].ToString());

                    if (SKDialgBoxChked(SKOpenDialgBox.Checked, SKRec["kh"].ToString(), SKRec["xm"].ToString()))
                    {
                        SKDB.ExecuteSQL(string.Format("insert into SKRecs(KH,LawyerID,SKDT,SKMode,LessonID,LessonName) values('{0}','{1}','{2}','{3}','{4}','{5}')",
                                               SKRec["kh"], SKRec["LawCreditNo"], RQ + " " + SJ, SKRec["SKMode"], SKClassID, ClassName.Text));

                        ListViewItem li = new ListViewItem();
                        li.SubItems.Clear();
                        li.SubItems[0].Text = SKRec["kh"].ToString();
                        li.SubItems.Add(SKRec["xm"].ToString());
                        li.SubItems.Add(SKRec["mobile"].ToString());
                        li.SubItems.Add(RQ);
                        li.SubItems.Add(SJ);

                        OleDbDataReader BMRcd = SKDB.ExecuteQuery(string.Format("select * from ClassReg where LessonID='{0}' and LawyerID='{1}'",
                                                                SKClassID, SKRec["LawCreditNo"]));
                        if (BMRcd.HasRows)
                        {
                            li.ForeColor = System.Drawing.Color.Red;
                        }

                        BMRcd.Close();

                        SKlV.Items.Add(li);


                        SKNum.Text = (Convert.ToInt16(SKNum.Text) + 1).ToString();
                    }

                }

                SKRec.Close();
                Thread.Sleep(50);

            }
        }

        private void ShowPhoto(PictureBox PBox, string PhotoFile)
        {
            PBox.Image = null;
            if (!string.IsNullOrEmpty(PhotoFile))
            {
                try
                {
                    PBox.Image = System.Drawing.Image.FromFile(BasePath + @"\photo\" + PhotoFile.Replace("/", @"\"));
                   // PBox.Image = System.Drawing.Image.FromFile(ElearningInterace.logopath + PhotoFile);
                }
                catch (Exception e)
                {
                    Logger.LogInfo("ShowPhoto失败:" + e.Message + "\r\n" + e.StackTrace);
                }
            }
        }

        public void AutoSKRecsExport()
        {
            BackupData BKData = new BackupData(WebName.Text, UserID.Text, PSWord.Text);
            BKData.ShowState = ShowState;
            BKData.StateBar = PBLine;

            while (true)
            {
                int RecsNum = BKData.UploadSKRecs();
                AutoExportNum.Text = (Convert.ToInt16(AutoExportNum.Text) + RecsNum).ToString();

                Thread.Sleep(6000);
            }
        }


        [DllImport("kernel32")]
        private static extern long WritePrivateProfileString(string section, string key, string val, string filepath);
        [DllImport("kernel32")]
        private static extern int GetPrivateProfileString(string section, string key, string def, StringBuilder retval,
                                                          int size, string filepath);

        public void INIWriteValue(string section, string key, string value)//对ini文件进行写操作的函数 
        {
            WritePrivateProfileString(section, key, value, INIFile);
        }

        public string INIReadValue(string section, string key)//对ini文件进行读操作的函数 
        {
            StringBuilder temp = new StringBuilder(255);
            int i = GetPrivateProfileString(section, key, "", temp, 255, INIFile);
            return temp.ToString();
        }


        /// <summary>
        /// 应用程序的主入口点。
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.Run(new SKSystem());
        }


        private void GetLawyersDataBtn_Click(object sender, System.EventArgs e)
        {
            ShowState.Text = "备份操作开始......";
            ShowState.Refresh();

            BackupData BKData = new BackupData(WebName.Text, UserID.Text, PSWord.Text);
            BKData.ShowState = ShowState;
            BKData.StateBar = PBLine;

            if (DownCardNo.Checked)
            {
                BKData.BKLawyersInfo(LawyerPhoto.Checked);
                MessageBox.Show("完成律师资料/照片的本地备份...");
            }

            if (DownLessonInfo.Checked)
            {
                BKData.BKLessonsInfo();
                Thread.Sleep(2000);
                InitSKClassID(false, OldLessonType.Checked);

                MessageBox.Show("完成课程资料的本地备份...");
            }
        }

        private void UploadBtn_Click(object sender, System.EventArgs e)
        {
            ShowState.Text = "上传操作开始......";
            ShowState.Refresh();

            BackupData BKData = new BackupData(WebName.Text, UserID.Text, PSWord.Text);
            BKData.ShowState = ShowState;
            BKData.StateBar = PBLine;

            int RecsNum = BKData.UploadSKRecs();

            AutoExportNum.Text = (Convert.ToInt16(AutoExportNum.Text) + RecsNum).ToString();
            MessageBox.Show(string.Format("完成 {0} 条记录的上传......", RecsNum));

        }

        private void DownCardNo_CheckedChanged(object sender, System.EventArgs e)
        {
            INIWriteValue("系统设置", "DownCardNo", DownCardNo.Checked ? "YES" : "NO");
            if (!DownCardNo.Checked) LawyerPhoto.Checked = false;
        }

        private void HaveTMode_CheckedChanged(object sender, System.EventArgs e)
        {

            if (HaveTMode.Checked)
            {
                TDTestMode = new Thread(new ThreadStart(SKP.TestModeSK));
                TDTestMode.IsBackground = true;
                TDTestMode.Start();
            }
            else TDTestMode.Abort();

        }

        private void InitSKClassID(bool FirstTime, bool IsShowOldLesson)
        {

            //    MessageBox.Show("==============" + FirstTime);

            string SavedClassID = INIReadValue("系统设置", "考勤的课程编号");

            if (IsShowOldLesson)
                SKDB.AddTableInDataSet(TableDS, "Lesson", "select * from LessonInfo");
            else
                SKDB.AddTableInDataSet(TableDS, "Lesson", "select * from LessonInfo where LessonType='培训课程'");

            //KCBHComboBox.DataBindings.Clear();
            KCBHComboBox.DataSource = TableDS.Tables["Lesson"];
            KCBHComboBox.DisplayMember = "LessonName";
            KCBHComboBox.ValueMember = "LessonID";


            ClassName.DataBindings.Clear();
            ClassName.DataBindings.Add("Text", TableDS.Tables["Lesson"], "LessonName");
            //   MessageBox.Show(FirstTime+"");
            if (FirstTime)
                KCBHComboBox.SelectedValue = SavedClassID;
            else
            {
                if (KCBHComboBox.SelectedValue != null)
                {
                    SKClassID = KCBHComboBox.SelectedValue.ToString();
                    INIWriteValue("系统设置", "考勤的课程编号", KCBHComboBox.SelectedValue.ToString());
                }
            }

        }

        private void KCBHComboBox_SelectedValueChanged(object sender, System.EventArgs e)
        {

            if (KCBHComboBox.SelectedValue != null)
            {
                SKClassID = KCBHComboBox.SelectedValue.ToString();
                INIWriteValue("系统设置", "考勤的课程编号", KCBHComboBox.SelectedValue.ToString());
            }
        }

        private void RemoteWrite_CheckedChanged(object sender, System.EventArgs e)
        {
            if (RemoteWrite.Checked)
            {
                TDAutoRemoteWrite = new Thread(new ThreadStart(AutoSKRecsExport));
                TDAutoRemoteWrite.IsBackground = true;
                TDAutoRemoteWrite.Start();
            }
            else TDAutoRemoteWrite.Abort();
        }

        private void PSWord_TextChanged(object sender, System.EventArgs e)
        {
            INIWriteValue("系统设置", "PSWord", PSWord.Text);
        }

        private void UserID_TextChanged(object sender, System.EventArgs e)
        {
            INIWriteValue("系统设置", "UserID", UserID.Text);
        }

        private void WebName_TextChanged(object sender, System.EventArgs e)
        {
            INIWriteValue("系统设置", "WebName", WebName.Text);
        }

        private void DownLessonInfo_CheckedChanged(object sender, System.EventArgs e)
        {
            INIWriteValue("系统设置", "DownLessonInfo", DownLessonInfo.Checked ? "YES" : "NO");
        }

        private void OldLessonType_CheckedChanged(object sender, EventArgs e)
        {
            INIWriteValue("系统设置", "OldLessonType", OldLessonType.Checked ? "YES" : "NO");
            InitSKClassID(false, OldLessonType.Checked);
        }

        private void LawyerPhoto_CheckedChanged(object sender, EventArgs e)
        {
            INIWriteValue("系统设置", "LawyerPhoto", LawyerPhoto.Checked ? "YES" : "NO");
            if (LawyerPhoto.Checked) DownCardNo.Checked = true;
        }

        private void SKOpenDialgBox_CheckedChanged(object sender, EventArgs e)
        {
            INIWriteValue("系统设置", "SKOpenDialgBox", SKOpenDialgBox.Checked ? "YES" : "NO");
        }

        private void tabCtrl_SelectedIndexChanged(object sender, System.EventArgs e)
        {
            if (tabCtrl.SelectedTab.Name == "tabKQTJ")
            {
                InitSKGL();
            }

            if (tabCtrl.SelectedTab.Name == "tabSKByHand")
            {
                LessonNameLb.Text = ClassName.Text;

                OleDbDataReader SKClassRec = SKDB.ExecuteQuery(string.Format("select * from LessonInfo  where LessonID='{0}'", SKClassID));

                if (SKClassRec.HasRows)
                {
                    SKClassRec.Read();

                    SKDTPicker.Value = (DateTime)SKClassRec["LessonSDT"];
                    ReqMinutesLb.Text = SKClassRec["ReqMinutes"].ToString();
                }

                SKClassRec.Close();
            }

        }

        private void InitSKGL()
        {
            SKDB.AddTableInDataSet(TableDS, "SKLessons", "select distinct LessonID,LessonName from SKRecs");


            cBSKLessons.DataSource = TableDS.Tables["SKLessons"];
            cBSKLessons.DisplayMember = "LessonName";
            cBSKLessons.ValueMember = "LessonID";

            SKRecsDataGrid(cBSKLessons.SelectedValue, rBLawyer.Checked ? "Lawyers" : rBSWS.Checked ? "SWS" : "");
        }

        private void SKRecsDataGrid(object SelectedClass, string DGMode)
        {
            string SKLessonID = (SelectedClass == null) ? "" : SelectedClass.ToString();

            string SQLStr;
            switch (DGMode)
            {
                case "SWS":
                    SQLStr = string.Format("select Lawyers.SWSName,count(SKLawyerRecs.LawyerID) as LawyerIDs " +
                                         "from (select distinct LawyerID from SKRecs where LessonID='{0}') as SKLawyerRecs,Lawyers" +
                                         " where SKLawyerRecs.LawyerID=Lawyers.LawCreditNo group by Lawyers.SWSName", SKLessonID);
                    SKDB.AddTableInDataSet(TableDS, "SKSWS", SQLStr);

                    dGKQ.SetDataBinding(TableDS, "SKSWS");
                    dGKQ.TableStyles.Clear();
                    dGKQ.TableStyles.Add(dataGridTableStyleSWS);
                    dGKQ.CaptionVisible = true;
                    dGKQ.CaptionText = string.Format("  共有 【{0}】 个律师事务所    【{1}】 名律师参加了本课程",
                                                   TableDS.Tables["SKSWS"].Compute("Count(SWSName)", "SWSName<>''"),
                                                   TableDS.Tables["SKSWS"].Compute("Sum(LawyerIDs)", "true"));

                    break;


                case "Lawyers":
                    SQLStr = string.Format("select distinct Lawyers.SWSName,Lawyers.xm,min(SKRecs.SKDT) as minSJ,max(SKRecs.SKDT) as maxSJ from SKRecs,Lawyers" +
                        " where SKRecs.LawyerID=Lawyers.LawCreditNo and LessonID='{0}' group by Lawyers.SWSName,Lawyers.xm", SKLessonID);
                    SKDB.AddTableInDataSet(TableDS, "SKLawyers", SQLStr);

                    dGKQ.SetDataBinding(TableDS, "SKLawyers");
                    dGKQ.TableStyles.Clear();
                    dGKQ.TableStyles.Add(dataGridTableStyleLawyers);
                    dGKQ.CaptionVisible = false;

                    break;
            }
        }

        private void cBSKLessons_SelectionChangeCommitted(object sender, System.EventArgs e)
        {
            SKRecsDataGrid(cBSKLessons.SelectedValue, rBLawyer.Checked ? "Lawyers" : rBSWS.Checked ? "SWS" : "");
        }

        private void rBLawyer_CheckedChanged(object sender, System.EventArgs e)
        {
            SKRecsDataGrid(cBSKLessons.SelectedValue, rBLawyer.Checked ? "Lawyers" : rBSWS.Checked ? "SWS" : "");
        }

        private void btDelSKRecs_Click(object sender, System.EventArgs e)
        {

            if (cBSKLessons.Text != "")
            {
                Access DB = new Access();
                OleDbDataReader DR = DB.ExecuteQuery(string.Format("select * from SKRecs where LessonName='{0}' and IsToRemote=false", cBSKLessons.Text));

                bool CanDel = !DR.HasRows;

                DR.Close();

                if (CanDel)
                {
                    DB.ExecuteSQL(string.Format("delete from SKRecs where LessonName='{0}'", cBSKLessons.Text));
                    Thread.Sleep(1000);
                    InitSKGL();
                }
                else
                    MessageBox.Show(string.Format("课程：【{0}】有未上传的考勤记录，请先上传有关记录......", cBSKLessons.Text));
            }
        }

        private void SKSystem_FormClosing(object sender, FormClosingEventArgs e)
        {
            Access DB = new Access();
            OleDbDataReader DR = DB.ExecuteQuery("select * from SKRecs where IsToRemote=false");

            if (DR.HasRows)
            {

                string Message = "有未上传的刷卡记录。想执行上传操作后再退出吗？";
                string Caption = "上传提醒";
                MessageBoxButtons Buttons = MessageBoxButtons.YesNo;
                DialogResult Result;

                // Displays the MessageBox.

                Result = MessageBox.Show(this, Message, Caption, Buttons);

                if (Result == DialogResult.Yes)
                {
                    UploadBtn_Click(null, null);
                }

                //e.Cancel = Result == DialogResult.Yes ? true : false;
            }

            DR.Close();
        }

        //SKByHand Section

        private void OKBtn_Click(object sender, EventArgs e)
        {
            ErrorLb.Text = "";
            if (CreditNoTB.Text != "")
            {
                int SKMinutes = Convert.ToInt32(SKMinuteTB.Text.Trim());
                DateTime[] SKDTArray = new DateTime[SKMinutes <= 0 ? 1 : 2];

                if (SKMinutes <= 0)
                    SKDTArray[0] = SKDTPicker.Value.AddMinutes(SKMinutes);
                else
                {
                    SKDTArray[0] = SKDTPicker.Value;
                    SKDTArray[1] = SKDTPicker.Value.AddMinutes(SKMinutes);
                }

                string SKMsg = "";
                for (int i = 0; i < SKDTArray.Length; i++)
                {
                    SKMsg += string.Format("卡号：{0} 姓名：{1} 刷卡日期：{2} 刷卡时间：{3}\n",
                                          KHTB.Text, LawyerNameLb.Text,
                                          SKDTArray[i].ToString("yyyy-MM-dd"),
                                          SKDTArray[i].ToString("HH-mm-ss"));
                }

                string Caption = "输入刷卡记录确认";
                MessageBoxButtons Buttons = MessageBoxButtons.YesNo;

                if (MessageBox.Show(this, SKMsg, Caption, Buttons) == DialogResult.Yes)
                {
                    Access SKDB = new Access();
                    for (int i = 0; i < SKDTArray.Length; i++)
                    {
                        string sql = string.Format("insert into yskq(bh,rq,sj,SKMode) values({0},'{1}','{2}','手动')",
                                                      hzbh.Text,
                                                      SKDTArray[i].ToString("yyyy-MM-dd"),
                                                      SKDTArray[i].ToString("HH-mm-ss"));
                        //  System.Windows.Forms.MessageBox.Show(sql);
                        SKDB.ExecuteSQL(sql);
                    }
                }
            }
            else
                ErrorLb.Text = "请先查找律师的执业证号或卡号...";

        }

        private void SchBtn_Click(object sender, EventArgs e)
        {
            Access SKDB = new Access();
            DataSet TableDS = new DataSet();

            string SQLStr = string.Format("select SWSName,XM,LawCreditNO,KH,bh,mobile from Lawyers where SWSName like '%{0}%'and XM like '%{1}%' and mobile like '%{2}%'order by SWSName,XM",
                                           SWSNameTB.Text.Trim(), XMTB.Text.Trim(), lsbh.Text.Trim());

            SKDB.AddTableInDataSet(TableDS, "LawyersInfo", SQLStr);

            DGLawInfo.SetDataBinding(TableDS, "LawyersInfo");
            DGLawInfo.TableStyles.Clear();
            DGLawInfo.TableStyles.Add(LawInfoTableStyle);
        }

        private void DGLawInfo_CurrentCellChanged(object sender, EventArgs e)
        {

            int RNum = DGLawInfo.CurrentCell.RowNumber;

            DataTable DT = ((DataSet)DGLawInfo.DataSource).Tables[0];

            SWSNameLb.Text = DT.Rows[RNum]["SWSName"].ToString();
            LawyerNameLb.Text = DT.Rows[RNum]["XM"].ToString();
            KHTB.Text = DT.Rows[RNum]["KH"].ToString();
            CreditNoTB.Text = DT.Rows[RNum]["LawCreditNO"].ToString();
            SKBHLb.Text = DT.Rows[RNum]["mobile"].ToString();
            hzbh.Text = DT.Rows[RNum]["BH"].ToString();
            ErrorLb.Text = "";

        }
        private const string lawyersql = "select swsname,xm,lawcreditno,kh,photourl,mobile from lawyers where kh='{0}'";
        private void SKlV_SelectedIndexChanged(object sender, EventArgs e)
        {
            ListView lv = (ListView)sender;
            int cnt = lv.SelectedItems.Count;
            if (cnt >= 1)
            {
                ListViewItem item = lv.SelectedItems[0];

                Access SKDB = new Access();
                DataSet TableDS = new DataSet();

                string SQLStr = string.Format(lawyersql, item.SubItems[0].Text);

                SKDB.AddTableInDataSet(TableDS, "LawyersInfo", SQLStr);

                //this.Photo. = TableDS.Tables[0].Rows["photourl"].ToString();

                ShowPhoto(this.Photo, TableDS.Tables[0].Rows[0]["photourl"].ToString());
                this.LawyerCredit.Text = TableDS.Tables[0].Rows[0]["lawcreditno"].ToString();
                this.LawyerName.Text = TableDS.Tables[0].Rows[0]["xm"].ToString();
                this.SWSName.Text = TableDS.Tables[0].Rows[0]["swsname"].ToString();
                this.huiyuanbh.Text = TableDS.Tables[0].Rows[0]["mobile"].ToString();

                //int subcnt = item.SubItems.Count;
                //StringBuilder sb = new StringBuilder();
                //for (int i = 0; i < subcnt; i++)
                //{
                //   System.Windows.Forms.ListViewItem.ListViewSubItem subitem=item.SubItems[i];
                //    sb.Append(subitem.Name + "=" + subitem.Text);
                //    sb.Append("|||");
                //}
                //MessageBox.Show(sb.ToString());
            }
            //string text = lv.SelectedItems[0].Text;

            //this.LawyerCredit.Text = text;
        }



        //private void SKlV_ItemSelectionChanged(object sender, ListViewItemSelectionChangedEventArgs e)
        //{
        //    ListView lv = (ListView)sender;
        //    MessageBox.Show(lv + "===>>>" + lv.SelectedItems.Count);
        //    //    string text = lv.SelectedItems[0].Text;
        //    //    this.LawyerCredit.Text = text;
        //}


    }

    public class ListViewSorter : IComparer
    {
        private Comparer comparer;
        private int sortColumn;
        private System.Windows.Forms.SortOrder sortOrder;
        public ListViewSorter()
        {
            sortColumn = 0;
            sortOrder = System.Windows.Forms.SortOrder.None;
            comparer = Comparer.Default;
        }
        //指定进行排序的列
        public int SortColumn
        {
            get { return sortColumn; }
            set { sortColumn = value; }
        }
        //指定按升序或降序进行排序
        public System.Windows.Forms.SortOrder SortOrder
        {
            get { return sortOrder; }
            set { sortOrder = value; }
        }
        public int Compare(object x, object y)
        {
            int CompareResult;
            ListViewItem itemX = (ListViewItem)x;
            ListViewItem itemY = (ListViewItem)y;
            //在这里您可以提供自定义的排序
            CompareResult = comparer.Compare(itemX.SubItems[this.sortColumn].Text, itemY.SubItems[this.sortColumn].Text);
            if (this.SortOrder == System.Windows.Forms.SortOrder.Ascending)
                return CompareResult;
            else
                if (this.SortOrder == System.Windows.Forms.SortOrder.Descending)
                    return (-CompareResult);
                else
                    return 0;
        }
    }

}
