package cn.gov.scciq.bussiness.samplingStatistics;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 抽批分析导出数据
 * 
 * @author chao.xu
 *
 */
public class SamplingStatisticsExportAction extends ActionSupport {

	private static Log log = LogFactory
			.getLog(SamplingStatisticsExportAction.class);
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

	public String execute() throws Exception {

		String excelName = "抽批分析";
		JSONObject rsData= SamplingStatisticsService.getStaticResultForSampling(data, draw, 0, 0);
 		HSSFWorkbook workbook = generateExcel(excelName, getTitles(), rsData);
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
		String titiles[] = { "出口国家", "企业名称", "产品编号", "产品名称",  "项目代码","项目名称","项目类别", "当前理论抽中率","总批次","抽中批次","实际抽中率","偏差","送检批次","送检率","差异批次" };
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
			Field fields[] = SamplingStatisticsDto.class.getDeclaredFields();
			for (; j < ja.size(); j++) {
				HSSFRow row = sheet.createRow(j+1);
				int column = 0;
				for (int k = 0; k< fields.length; k++) {
					Field f = fields[k];
					Class<?> type = f.getType();
					String fName = f.getName();
					String typeName = type.getSimpleName();
					if ("String".equalsIgnoreCase(typeName)) {
						String value = "";
						if(fName.equals("itemSamplingTheoryRatio_Lower")){
							value = ja.getJSONObject(j).getString(fName)+"%-"+ja.getJSONObject(j).getString("itemSamplingTheoryRatio_Upper")+"%";
							createCell(row, column, HSSFCell.CELL_TYPE_STRING, value);
							column++;
							k++;
						}else{
							value = ja.getJSONObject(j).getString(fName);
							createCell(row, column, HSSFCell.CELL_TYPE_STRING, value);
							column++;
						}
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
