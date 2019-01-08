package rechard.learn.birt;

import java.util.HashMap;
import java.util.Map;

public class ReportParameter {
	private String reportName;
	private Map<String,Object> params=null;
	private String format;
	public ReportParameter(String reportName,String format) {
		this.reportName = reportName;
		this.format = format;
		params=new HashMap<String,Object>();
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
	public void setParameter(String key,Object val){
		this.params.put(key, val);
	}
	
	public Object getParameter(String key){
		return this.params.get(key);
	}
	
	public Map<String,Object> getParameter(){
		return this.params;
	}
	
}
