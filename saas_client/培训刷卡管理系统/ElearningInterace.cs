using System;
using System.Collections.Generic;
using System.Text;
using System.Net;
using System.Web;
using System.Configuration;
using System.Xml;
namespace 培训刷卡管理系统
{
    /// <summary>
    /// 培训系统接口，包括验证，获取律师信息，获取课程信息，上传考勤记录等
    /// </summary>
    public class ElearningInterace
    {
        public static string url;
        public static string groupid;
        public static string logopath;
        public static string cpuid = "";
        static ElearningInterace()
        {
            url = ConfigurationManager.AppSettings["remoteurl"].ToString();
            groupid = ConfigurationManager.AppSettings["groupid"].ToString();
            logopath = ConfigurationManager.AppSettings["PhotoBaseUrl"].ToString();
        }

        private string SendRequest(byte[] byte1)
        {

            HttpWebRequest request = null;
            HttpWebResponse response = null;
            StringBuilder sbb = new StringBuilder();
            System.IO.Stream _RequestStream = null;
            System.IO.Stream _ResponseStream = null;
            System.IO.StreamReader reader = null;
            try
            {
                request = (HttpWebRequest)WebRequest.Create(url);
                //if (request != null)
                //    Logger.LogDebug("===request::" + request);
                //else
                //    Logger.LogDebug("获得的请求是空的呢");
                request.Method = "POST";
                request.ContentType = "application/x-www-form-urlencoded";
                
                request.ContentLength = byte1.Length;
                //request.TransferEncoding = "utf-8";
                //request.SendChunked = true;

                _RequestStream = request.GetRequestStream();
                _RequestStream.Write(byte1, 0, byte1.Length);
                //_RequestStream.Flush();


                response = (HttpWebResponse)request.GetResponse();
   
                _ResponseStream = response.GetResponseStream();
                reader = new System.IO.StreamReader(_ResponseStream, System.Text.Encoding.UTF8);
                string line = "";

                while ((line = reader.ReadLine()) != null)
                {
                    sbb.Append(line);
                //    Console.WriteLine(line);                    
                }
                Logger.LogInfo("返回消息:\r\n"+sbb.ToString());
            }
            catch (Exception ex)
            {
                Logger.LogInfo("请求失败:" + ex.Message + "\r\n" + ex.StackTrace);
                throw new Exception(ex.Message);
            }
            finally
            {
                if (_RequestStream != null)
                    _RequestStream.Close();
                if (_ResponseStream != null)
                    _ResponseStream.Close();
                if (reader != null)
                    reader.Close();
                if (response != null)
                    response.Close();
            }
            return sbb.ToString();
        }

        /// <summary>
        /// 用户登录
        /// </summary>
        /// <param name="username"></param>
        /// <param name="password"></param>
        /// <returns></returns>
        public string Login(string username, string password)
        {
            System.Text.StringBuilder sb = new StringBuilder();
            sb.Append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            sb.Append("<method name=\"login\" groupid=\"" + groupid + "\" cpuid=\"" + cpuid + "\">");
            sb.Append("<loginname>").Append(username).Append("</loginname>");
            sb.Append("<password>").Append(password).Append("</password>");
            sb.Append("</method>");
            byte[] byte1 = System.Text.Encoding.UTF8.GetBytes(sb.ToString());
            string response = this.SendRequest(byte1);
            System.Xml.XmlDocument doc = new System.Xml.XmlDocument();
            doc.LoadXml(response);
            string respcode = doc.GetElementsByTagName("respcode").Item(0).InnerText;
            string respmsg = doc.GetElementsByTagName("respmsg").Item(0).InnerText;
            if (System.Int32.Parse(respcode) < 0)
            {
                System.Windows.Forms.MessageBox.Show(respmsg); 
            }

            return respcode + "|" + respmsg;
        }
        /// <summary>
        /// 得到律师信息, 这里最好增量的方式
        /// </summary>
        /// <returns></returns>
        public List<Lawyers> GetLawyersInfo()
        {
            System.Text.StringBuilder sb = new StringBuilder();
            sb.Append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            sb.Append("<method name=\"getLawyersInfo\" groupid=\"" + groupid + "\" cpuid=\"" + cpuid + "\"/>");
            byte[] byte1 = System.Text.Encoding.UTF8.GetBytes(sb.ToString());
            string response = this.SendRequest(byte1);
            System.Xml.XmlDocument doc = new System.Xml.XmlDocument();
            doc.LoadXml(response);

            string respcode = doc.GetElementsByTagName("respcode").Item(0).InnerText;
            string respmsg = doc.GetElementsByTagName("respmsg").Item(0).InnerText;
            if (System.Int32.Parse(respcode) < 0)
            {
                System.Windows.Forms.MessageBox.Show(respmsg); 
                return null;
            }

           List<Lawyers> lawyers=new List<Lawyers>();

           XmlNodeList nodelist= doc.GetElementsByTagName("lawyer");
           int length = nodelist == null ? 0 : nodelist.Count;
           for (int i = 0; i < length; i++)
           {
               XmlElement element = (XmlElement)nodelist.Item(i);
               Lawyers lawyer = new Lawyers();

               lawyer.Cardno = element.GetElementsByTagName("cardno").Item(0).InnerText;
               lawyer.Lawyerno = element.GetElementsByTagName("lawyerno").Item(0).InnerText;
               lawyer.Systemno = element.GetElementsByTagName("systemno").Item(0).InnerText;
               lawyer.Officename = element.GetElementsByTagName("officename").Item(0).InnerText;
               lawyer.Photo = element.GetElementsByTagName("photo").Item(0).InnerText;
               lawyer.Userid = Int32.Parse(element.GetElementsByTagName("userid").Item(0).InnerText);
               lawyer.Username = element.GetElementsByTagName("username").Item(0).InnerText;
            //   lawyer.Systemno = element.GetElementsByTagName("systemno").Item(0).InnerText;
               lawyers.Add(lawyer);
           }

            return lawyers;
        }
        /// <summary>
        /// 得到课程信息
        /// </summary>
        /// <returns></returns>
        public List<Lessons> GetLessonsInfo()
        {
            System.Text.StringBuilder sb = new StringBuilder();
            sb.Append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            sb.Append("<method name=\"getLessonsInfo\" groupid=\"" + groupid + "\" cpuid=\"" + cpuid + "\"/>");
            byte[] byte1 = System.Text.Encoding.UTF8.GetBytes(sb.ToString());
            string response = this.SendRequest(byte1);
            System.Xml.XmlDocument doc = new System.Xml.XmlDocument();
            doc.LoadXml(response);
            string respcode = doc.GetElementsByTagName("respcode").Item(0).InnerText;
            string respmsg = doc.GetElementsByTagName("respmsg").Item(0).InnerText;
            if (System.Int32.Parse(respcode) < 0)
            {
                System.Windows.Forms.MessageBox.Show(respmsg); 
                return null;
            }  


            List<Lessons> lessons = new List<Lessons>();

            XmlNodeList nodelist = doc.GetElementsByTagName("lesson");
            int length = nodelist == null ? 0 : nodelist.Count;
            for (int i = 0; i < length; i++)
            {
                XmlElement element = (XmlElement)nodelist.Item(i);
                Lessons lesson = new Lessons();

                lesson.Lessonid = Int32.Parse(element.GetElementsByTagName("lessonid").Item(0).InnerText);
                lesson.Title = element.GetElementsByTagName("title").Item(0).InnerText;
                lesson.Lessontype = element.GetElementsByTagName("lessontype").Item(0).InnerText;
                lesson.Lessondate = element.GetElementsByTagName("lessondate").Item(0).InnerText;
                lesson.Reqminute = element.GetElementsByTagName("reqminute").Item(0).InnerText;
                lessons.Add(lesson);
            }

            return lessons;
        }
        /// <summary>
        /// 得到培训课程中已经有人参加了培训的
        /// 记住：：：在关闭窗口的时候，提示是否结束本课程。如果是，通过接口，将本课程结束
        /// 
        /// select * from lxnetrecs where lessonid in() //就是不是往期课程或者结束课程
        /// 
        /// 
        /// </summary>
        /// <returns></returns>
        public List<ClassReg> GetClassRegInfo()
        {
            System.Text.StringBuilder sb = new StringBuilder();
            sb.Append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            sb.Append("<method name=\"getClassRegInfo\" groupid=\"" + groupid + "\" cpuid=\"" + cpuid + "\"/>");
            byte[] byte1 = System.Text.Encoding.UTF8.GetBytes(sb.ToString());
            string response = this.SendRequest(byte1);
            System.Xml.XmlDocument doc = new System.Xml.XmlDocument();
            doc.LoadXml(response);

            string respcode = doc.GetElementsByTagName("respcode").Item(0).InnerText;
            string respmsg = doc.GetElementsByTagName("respmsg").Item(0).InnerText;
            if (System.Int32.Parse(respcode) < 0)
            {
                System.Windows.Forms.MessageBox.Show(respmsg); 
                return null;
            }

            List<ClassReg> classregs = new List<ClassReg>();

            XmlNodeList nodelist = doc.GetElementsByTagName("classreg");
            int length = nodelist == null ? 0 : nodelist.Count;
            for (int i = 0; i < length; i++)
            {
                XmlElement element = (XmlElement)nodelist.Item(i);
                ClassReg classreg = new ClassReg();

                classreg.Dotime = element.GetElementsByTagName("dotime").Item(0).InnerText;
                classreg.Lawyerno = element.GetElementsByTagName("lawyerno").Item(0).InnerText;
                classreg.Lessonid = Int32.Parse(element.GetElementsByTagName("lessonid").Item(0).InnerText);
                classreg.Regtype = element.GetElementsByTagName("regtype").Item(0).InnerText;

                classregs.Add(classreg);
            }

            return classregs;
        }
        public bool SetSKRecs(System.Data.DataSet skrecs)
        {
            System.Text.StringBuilder sb = new StringBuilder();
            sb.Append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            sb.Append("<method name=\"setSKRecs\" groupid=\"" + groupid + "\" cpuid=\"" + cpuid + "\">");
            sb.Append("<skrecs>");
            if (skrecs != null && skrecs.Tables[0].Rows.Count > 0)
            {
                int length = skrecs.Tables[0].Rows.Count;
                for (int i = 0; i < length; i++)
                {
                    System.Data.DataRow row = skrecs.Tables[0].Rows[i];
                 //   string cardno = row["KH"].ToString();
                    string cardno = row["LawyerID"].ToString();
                    string lessonid = row["LessonID"].ToString();
                    string skdate = row["SKDT"].ToString();
                    string skmode = row["SKMode"].ToString();

                    //string cardno = "1234567890";
                    //string lessonid = "1";
                    //string skdate = "2008-5-15 10:55:23";
                    //string skmode = "模拟";
                    //这里的cardno为律师执业证号
                    sb.Append("<skrec>");
                    sb.Append("<cardno>").Append(cardno).Append("</cardno>");
                    sb.Append("<lessonid>").Append(lessonid).Append("</lessonid>");
                    sb.Append("<skdate>").Append(skdate).Append("</skdate>");
                    sb.Append("<skmode>").Append(skmode).Append("</skmode>");
                    sb.Append("</skrec>");
                }
            }

            sb.Append("</skrecs>");
            sb.Append("</method>");


            Logger.LogInfo(sb.ToString());

            byte[] byte1 = System.Text.Encoding.Default.GetBytes(sb.ToString());
            string response = this.SendRequest(byte1);
            System.Xml.XmlDocument doc = new System.Xml.XmlDocument();
            doc.LoadXml(response);
            string respcode = doc.GetElementsByTagName("respcode").Item(0).InnerText;
            string respmsg = doc.GetElementsByTagName("respmsg").Item(0).InnerText;

         
            if (System.Int32.Parse(respcode) < 0)
            {
                System.Windows.Forms.MessageBox.Show(respmsg);
                return false;
            }



            return respcode.Equals("0") ? true : false;
        }
    }
}
