import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.*;

import java.util.*;
import java.io.*;

public class BackupDiaocha {
	public static void writeLog(String log, String str) {
		File parent = new File(log).getParentFile();
		if (!parent.exists())
			parent.mkdirs();
		FileOutputStream out = null;
		try {

			out = new FileOutputStream(log, true);
			out.write(str.getBytes());
			out.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static String substring(String str, int len) {
		if (str.length() >= len)
			return str.substring(0, len);
		else
			return str + "...";
	}

	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws ServiceException {
		String diaochalog = "D:/diaochalog.txt";

		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		BasicService service = (BasicService) context.getBean("basicService");

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Diaochawenti.class);
		detachedCriteria.add(Restrictions.eq("diaocha.diaochaid", 3));
		detachedCriteria.addOrder(Order.asc("wentiid"));
		detachedCriteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

		List<Diaochawenti> list = service.findAllByCriteria(detachedCriteria);

		String str = "回复人\t";
		for (Diaochawenti wenti : list) {

			str += substring(wenti.getTitle(), 10) + "\t";
		}
		str += "\r\n";
		writeLog(diaochalog, str);

		List<String> replys = service.find("select distinct(replyuser) from Diaochareply where replyuser!='admin'");
		for (String reply : replys) {
			str = reply + "\t";
			detachedCriteria = DetachedCriteria.forClass(Diaochareply.class);

			detachedCriteria.add(Restrictions.eq("replyuser", reply));
			detachedCriteria.addOrder(Order.asc("diaochawenti.wentiid"));
//			detachedCriteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
			List<Diaochareply> diaochareplys = service.findAllByCriteria(detachedCriteria);

			for (Diaochawenti wenti : list) {
				String content = "";
				for (Diaochareply diaochareply : diaochareplys) {

					if (diaochareply.getDiaochawenti().equals(wenti)) {

						content = diaochareply.getReplycontent();
						
						
						
						break;
					}
				}
				str += content + "\t";
			}
			str += "\r\n";
			writeLog(diaochalog, str);
		}
	}
}
