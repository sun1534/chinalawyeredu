/*
 * NAME: com.sxit.cmpp.CommandID.java Company:SXIT
 */
package com.sxit.cmpp;

/**
 * cmppÐ­Òé¸÷ÃüÁî¶ÔÓ¦id
 * @author HuaFeng
 * @version 1.0 (2005-3-28 23:06:50)
 */
public interface CommandID {
    /**
     * connectÃüÁî×Ö,sp->ismg
     */
    int CMPP_CONNECT = 1;

    /**
     * connectrespÃüÁî×Öid,ismg->sp
     */

    int CMPP_CONNECT_REP = 0x80000001;

    /**
     * terminateÃüÁî,sp->ismg»òismg->sp
     */

    int CMPP_TERMINATE = 2;

    /**
     * terminaterespÃüÁî,sp->ismg»òismg->sp
     */
    int CMPP_TERMINATE_REP = 0x80000002;

    /**
     * submitÃüÁî,sp->ismg
     */
    int CMPP_SUBMIT = 4;

    /**
     * submitrespÃüÁî,ismg->sp
     */
    int CMPP_SUBMIT_REP = 0x80000004;

    /**
     * deliverÃüÁî,ismg->sp
     */
    int CMPP_DELIVER = 5;

    /**
     * deliverrespÃüÁî,sp->ismg
     */
    int CMPP_DELIVER_REP = 0x80000005;

    /**
     * queryÃüÁî,sp->ismg
     */
//    int CMPP_QUERY = 6;

    /**
     * queryrespÃüÁî,ismg->sp
     */
//    int CMPP_QUERY_REP = 0x80000006;

    /**
     * cancelÃüÁî,sp->ismg
     */
//    int CMPP_CANCEL = 7;

    /**
     * cancelrespÃüÁî,sp->ismg
     */
//    int CMPP_CANCEL_REP = 0x80000007;

    /**
     * activeÃüÁî,sp->ismg»òismg->sp
     */
    int CMPP_ACTIVE_TEST = 8;

    /**
     * activerespÃüÁî,sp->ismg»òismg->sp
     */
    int CMPP_ACTIVE_TEST_REP = 0x80000008;
}