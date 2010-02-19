//-------------------------------------------------------------------
//版权所有：版权所有(C) 2006，Microsoft(China) Co.,LTD
//系统名称：GMCC-ADC
//文件名称：
//模块名称：
//模块编号：
//作　　者：
//完成日期：
//功能说明：
//-----------------------------------------------------------------

using System;
using System.Collections.Generic;
using System.Text;

using log4net;

[assembly: log4net.Config.XmlConfigurator(Watch = true)]
namespace 培训刷卡管理系统
{
    public enum AppError
    {
        WARN = 0,
        EROR = 1,
        FATL = 2
    }
    /// <summary>
    /// 类名称：Logger
    /// 类说明：按照日志的五个级别写日志。日志目录通过配置文件设定
    /// 作者：  
    /// 完成日期：
    /// </summary>
    public class Logger
    {
        private static readonly log4net.ILog loginfo  = log4net.LogManager.GetLogger("Logging.Info");
       
        public static void LogInfo(string msg)
        {
            StringBuilder logMessage = new StringBuilder();
            logMessage.Append(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss")).Append("=>").Append(msg);
          
            if (loginfo.IsInfoEnabled) 
                loginfo.Info( logMessage.ToString() );

        }

     
      
        public static void Log(string logmsg)
        {
           // log4net.LogManager.GetLogger(loggerName).Info(logmsg);
            loginfo.Info(logmsg);
        }

        public static ILog GetLogger(string loggerName)
        {
            return log4net.LogManager.GetLogger(loggerName);
        }
    }
}