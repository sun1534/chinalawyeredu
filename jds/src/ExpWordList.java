import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.changpeng.common.Globals;
import com.changpeng.operation.model.ToprCreditcard;
import com.sxit.common.BasicDaoNew;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * 
 * 
 * @author 刘哈哈 Mar 24, 201110:20:05 AM
 * 
 */
public class ExpWordList {
	// 2011年4月18日、4月19日、5月9日、5月18日、6月20日、6月28日、7月28日
	static int count=500;
	public static void main(String[] args) {

		BasicDaoNew bd = (BasicDaoNew) Globals.getBeanWithoutWeb("basicDaoNew");

//		String[] consigndates = "2011-04-18,2011-04-19,2011-05-09,2011-05-18,2011-06-20,2011-06-28,2011-07-28"
//				.split(",");
		String[] consigndates =new String[]{"2011-04-08"};
		for (String consigndate : consigndates) {

			List list = bd.find("from ToprCreditcard c where c.bankid=1 and c.consigndate='" + consigndate + "'");

			int len = (list == null ? 0 : list.size());

			List resultlist = new ArrayList();
			for (int i = 0; i < len; i++) {
				ToprCreditcard creditcard = (ToprCreditcard) list.get(i);
				LoopCredits s = new LoopCredits();
				s.setCreditcard(creditcard);
				String hsql = "from ToprCreditlog where toprCredittask.toprCreditcard.creditcardid="
						+ creditcard.getCreditcardid();
				List logList = bd.find(hsql);
				s.setLogList(logList);

				resultlist.add(s);

			}

			if (len == 0) {
				System.out.println(consigndate + "没有数据");
				continue;
			}

			String templatePath = "E:\\personal\\jds\\src";
			String templateName = "wordlog.html";
			int s = len / count + 1;
			for (int i = 0; i < s; i++) {

				
				List list1=new ArrayList();
				
				for(int j=i*count;j<(i+1)*count&&j<len;j++){
					list1.add(resultlist.get(j));
					
				}
				
				Map map = new HashMap();
				map.put("resultlist", list1);

				
				String fileName = "E:\\" + consigndate + "_"+(i+1)+".doc";
				analysisTemplate(templatePath, templateName, fileName, map);
			}
		}
	}

	// templatePath模板文件存放路径
	// templateName 模板文件名称
	// filename 生成的文件名称
	public static void analysisTemplate(String templatePath, String templateName, String fileName, Map<?, ?> root) {
		try {
			Configuration config = new Configuration();
			// 设置要解析的模板所在的目录，并加载模板文件
			config.setDirectoryForTemplateLoading(new File(templatePath));
			// 设置包装器，并将对象包装为数据模型
			config.setObjectWrapper(new DefaultObjectWrapper());

			// 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
			// 否则会出现乱码
			Template template = config.getTemplate(templateName, "utf-8");
			// 合并数据模型与模板
			FileOutputStream fos = new FileOutputStream(fileName);
			Writer out = new OutputStreamWriter(fos, "utf-8");
			template.process(root, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}
