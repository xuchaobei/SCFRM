package cn.gov.scciq.basicSettings.countryArea;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.gov.scciq.basicSettings.productClass.ProductClassBeanForExport;
import cn.gov.scciq.basicSettings.productClass.ProductClassForExportAction;
import cn.gov.scciq.basicSettings.productClass.ProductClassServiceImpl;
import cn.gov.scciq.util.DefaultResultUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CountryAreaExportAction  extends ActionSupport{

	private static Log log = LogFactory
			.getLog(ProductClassForExportAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int draw;
	private int start;
	private int length;
	private String data;
	private String filename;
	private InputStream inputStream;
	CountryAreaService service=new CountryAreaService();
	
	public String execute() throws Exception {

		String excelName = "国家地区数据";
		System.out.println("ac se "+data);
		//JSONObject rsData= ProductStatisticsService.getStaticResultForProduct(data, draw, 0, 0);
		//JSONObject object = JSONObject.fromObject(data);
		CountryAreaBean bean=new CountryAreaBean();
		bean.setCountryName("");
		Map<Integer, Object> rsMap=  service.GetCountry(bean, 1, 0, 0);
	       int recordsTotal = (Integer)rsMap.get(1);
	        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
	      JSONObject   result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
		 
		HSSFWorkbook workbook = generateExcel(excelName, getTitles(), result);
		filename = "document.xls";
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		workbook.write(output);
		byte[] ba = output.toByteArray();
		inputStream = new ByteArrayInputStream(ba);
		output.flush();
		output.close();
		return Action.SUCCESS;
	}


	private List<String> getTitles() {
		List<String> titles = new ArrayList<String>();
		String titiles[] = { "国家地区代码", "国家地区名称"};
		titles.addAll(Arrays.asList(titiles));
		return titles;
	}

	public <T> HSSFWorkbook generateExcel(String excelName,
			List<String> titles, JSONObject data) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建工作表实例
		HSSFSheet sheet = workbook.createSheet(excelName);
		HSSFRow rowTitle = sheet.createRow(0);
		for (int i = 0; i < titles.size(); i++) {
			createCell(rowTitle, i, HSSFCell.CELL_TYPE_STRING, titles.get(i));
		}
		JSONArray ja = data.getJSONArray("data");
		try {
			int j = 0;
			Field fields[] = CountryAreaForExportBean.class.getDeclaredFields();
			for (; j < ja.size(); j++) {
				HSSFRow row = sheet.createRow(j+1);
				for (int k = 0; k< fields.length; k++) {
					Field f = fields[k];
					Class<?> type = f.getType();
					String fName = f.getName();
					String typeName = type.getSimpleName();
					if ("String".equalsIgnoreCase(typeName)) {
						String value = ja.getJSONObject(j).getString(fName);
						createCell(row, k, HSSFCell.CELL_TYPE_STRING, value);
					} else if ("Integer".equalsIgnoreCase(typeName)
							|| "int".equalsIgnoreCase(typeName)) {
						int value = ja.getJSONObject(j).getInt(fName);
						createCell(row, k, HSSFCell.CELL_TYPE_NUMERIC, value);
					} else {
						throw new Exception(" 只支持int和String类型的变量");
					}
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

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}