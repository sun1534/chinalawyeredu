using System;
using System.Data;
using System.Data.OleDb;
using System.Windows.Forms;

using System.Threading;

namespace 培训刷卡管理系统
{
	/// <summary>
	/// access 的摘要说明。
	/// </summary>
	public class Access
	{
		private OleDbConnection SKDB;
		public string ConnStr;

		public Access()
		{
			string EPath=Application.ExecutablePath;
			string CurrntPath=EPath.Substring(0,EPath.LastIndexOf(@"\")+1);
			ConnStr="Provider=Microsoft.Jet.OLEDB.4.0;Data Source="+CurrntPath+"SK.mdb";
			SKDB=new OleDbConnection(ConnStr);
			SKDB.Open();
		}

		public int ExecuteSQL(string SQLCmd)
		{
			OleDbCommand aCommand = new OleDbCommand(SQLCmd,SKDB);

                try
                {
                    return aCommand.ExecuteNonQuery();
                }
                catch (Exception e)
                {
                    MessageBox.Show(e.Message+"\n"+SQLCmd);
                }

            return 0;

		}

		public OleDbDataReader ExecuteQuery(string SQLCmd)
		{
			OleDbCommand aCommand = new OleDbCommand(SQLCmd,SKDB); 
				return aCommand.ExecuteReader();
		}

		public void AddTableInDataSet(DataSet DS,string TableName,string SQLStr)
		{
			OleDbConnection SKDBTmp=new OleDbConnection(ConnStr);
			SKDBTmp.Open();

			try
			{
				OleDbDataAdapter Tmp=new OleDbDataAdapter(SQLStr,SKDBTmp);
				if (DS.Tables[TableName]!=null) 
                    DS.Tables[TableName].Rows.Clear();
				Tmp.Fill(DS,TableName);
			}
			finally
			{
				SKDBTmp.Close();
			}
		}

        public void CleanYSKQ()
        {
            this.ExecuteSQL("delete from yskq");
        }

		public void Release()
		{
            SKDB.Close();
		}

	}
}
