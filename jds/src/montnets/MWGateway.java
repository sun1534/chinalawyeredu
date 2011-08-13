
package montnets;


public class MWGateway
{

    public MWGateway()
    {
    }

    public static native int Connect(String s, int i, String s1, String s2);

    public static native int SendSms(int i, String s, String s1);

    public static native int TestConn(int i);

    public static native int QueryBalance(int i);

    public static native int QueryUsed(int i);

    public static native int Recharge(int i, String s, String s1);

    public static native int ChangePwd(int i, String s, String s1, String s2);

    public static native void Disconnect(int i);

    public static native String[] VasGetSms(int i);

    public static native int VasSendSms(int i, String s, String s1, String s2, String s3, String s4, String s5, int j);

    public static native String[] CsGetSms(int i);

    public static native String CsGetSmsEx(int i);

    public static native String CsSendSms(int i, String s, String s1, int j);

    public static native String[] CsGetStatusReport(int i);

    public static native String CsGetStatusReportEx(int i);

    public static native String CsSPSendSms(int i, String s, String s1, int j, String s2);

    public static native String MGhexChrTosrcStr(String strMsg);

    static 
    {
        System.loadLibrary("MWGateway");
    }
}