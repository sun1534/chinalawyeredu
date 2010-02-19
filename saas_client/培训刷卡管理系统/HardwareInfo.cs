using System;
using System.Collections.Generic;
using System.Text;
using System.Management;
namespace 培训刷卡管理系统
{
    public class HardwareInfo
    {

        public static string getCpuId()
        {
            ManagementClass mc = new ManagementClass("Win32_Processor");
            ManagementObjectCollection moc = mc.GetInstances();
            String strCpuID = null;
            foreach (ManagementObject mo in moc)
            {
                strCpuID = mo.Properties["ProcessorId"].Value.ToString();
                break;
            }
            if (strCpuID == null || strCpuID.Equals(""))
            {
                strCpuID = "LXPXXT20100218";
            }
            return strCpuID;

        }
    }
}
