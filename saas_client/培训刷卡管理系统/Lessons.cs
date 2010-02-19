using System;
using System.Collections.Generic;
using System.Text;

namespace 培训刷卡管理系统
{
    public class Lessons
    {
        private int lessonid;

        public int Lessonid
        {
            get { return lessonid; }
            set { lessonid = value; }
        }
        private string title;

        public string Title
        {
            get { return title; }
            set { title = value; }
        }
        private string lessontype;

        public string Lessontype
        {
            get { return lessontype; }
            set { lessontype = value; }
        }
        private string lessondate;

        public string Lessondate
        {
            get { return lessondate; }
            set { lessondate = value; }
        }
        private string reqminute;

        public string Reqminute
        {
            get { return reqminute; }
            set { reqminute = value; }
        }

    }
}
