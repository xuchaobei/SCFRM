package cn.gov.scciq.bussiness.download;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.gov.scciq.util.RsToDtoUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 导出excel文件
 * 
 * @author chao.xu
 *
 */
public class ExportExcelAction extends ActionSupport {

	 private static Log log=LogFactory.getLog(ExportExcelAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String filename;
	private InputStream inputStream;
	
	private String serviceName;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String execute() throws Exception {

		String serviceClass = "cn.gov.scciq.bussiness."+serviceName;
		String methodName1 = "getExcelName";
		String methodName2 = "getExcelTitles";
		String methodName3 = "getExcelData";
		Class claz = Class.forName(serviceClass);
		Object service = claz.newInstance();
		String excelName = (String)claz.getMethod(methodName1,String.class).invoke(service, filename);
		List titles = (List)claz.getMethod(methodName2,String.class).invoke(service, filename);
		List datas = (List)claz.getMethod(methodName3,String.class).invoke(service, filename);
		HSSFWorkbook workbook = generateExcel(excelName, titles, datas);
		filename = "document.xls";
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		workbook.write(output);
		byte[] ba = output.toByteArray();
		inputStream = new ByteArrayInputStream(ba);
		output.flush();
		output.close();

		return Action.SUCCESS;
	}
	
	public static void main(String[] args) {
		String excelName = "绿色通道";
		String filename = excelName+".xls";
		System.out.println(filename);
	}

	public <T> HSSFWorkbook generateExcel(String excelName, List<String> titles, List<T> list) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建工作表实例
		HSSFSheet sheet = workbook.createSheet(excelName);
		HSSFRow rowTitle = sheet.createRow(0);
		for(int i = 0;  i < titles.size(); i++){
			createCell(rowTitle, i, HSSFCell.CELL_TYPE_STRING, titles.get(i));
		}
		for (int j = 0;  j < list.size(); j++) {
			T t = list.get(j);
			HSSFRow row = sheet.createRow(j+1);
			try {
				Field fields[] = t.getClass().getDeclaredFields();
				for (int k = 0; k < fields.length; k++) {
					Field f = fields[k];
					Class<?> type = f.getType();
					String fName = f.getName();
					String getMethodName = "get"
							+ RsToDtoUtil.initialsToUppercase(fName);
					Method method = t.getClass().getMethod(getMethodName);
					String typeName = type.getSimpleName();
					if ("String".equalsIgnoreCase(typeName)) {
						String value = (String) method.invoke(t);
						createCell(row, k, HSSFCell.CELL_TYPE_STRING, value);
					} else if ("Integer".equalsIgnoreCase(typeName)
							|| "int".equalsIgnoreCase(typeName)) {
						int value = (int) method.invoke(t);
						createCell(row, k, HSSFCell.CELL_TYPE_NUMERIC, value);
					} else {
						throw new Exception(t.getClass() + " 只支持int和String类型的变量");
					}
				}
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				log.error("", e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				log.error("", e);
			} catch (Exception e) {
				log.error("", e);
			} 
		}

		return workbook;
	}

	// 创建Excel单元格

	private void createCell(HSSFRow row, int column, int cellType, Object value) {

		HSSFCell cell = row.createCell(column);
		
		switch (cellType) {

		case HSSFCell.CELL_TYPE_BLANK: {

		}
			break;
		case HSSFCell.CELL_TYPE_STRING: {
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(value.toString());
		}
			break;
		case HSSFCell.CELL_TYPE_NUMERIC: {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(Double.parseDouble(value.toString()));
		}
			break;
		default:
			break;

		}

	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}



	
}
