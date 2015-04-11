package cn.gov.scciq.bussiness.declStatistics;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

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
 * 导出报检批数据
 * 
 * @author chao.xu
 *
 */
public class DeclStatisticsExportAction extends ActionSupport {

	private static Log log = LogFactory
			.getLog(DeclStatisticsExportAction.class);
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


	private List<String> sumFields = new ArrayList<String>();

	public String execute() throws Exception {

		String excelName = "报检批统计";
		Map<Integer, Object> map = DeclStatisticsService.getMapStaticResultForDec(data, draw, 0, 0);
 		HSSFWorkbook workbook = generateExcel(excelName, getTitles(), map);
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
		DeclStatisticsReqDto dto = (DeclStatisticsReqDto) JSONObject.toBean(
				JSONObject.fromObject(data), DeclStatisticsReqDto.class);
		List<String> titles = new ArrayList<String>();
		if (dto.getGroup_Org()) {
			titles.add("检验机构");
			sumFields.add("orgName");
		}
		if (dto.getGroup_Dept()) {
			titles.add("检验部门");
			sumFields.add("deptName");
		}
		if (dto.getGroup_Ent()) {
			titles.add("企业");
			sumFields.add("entName");
		}
		if (dto.getGroup_Country()) {
			titles.add("出口国家");
			sumFields.add("countryName");
		}
	
		String otherTitiles[] = { "出口批次", "金额（美元）", "重量（千克）", "不合格批次", "不合格金额",
				"不合格重量", "批次不合格率", "抽中批次", "抽中率", };
		titles.addAll(Arrays.asList(otherTitiles));
		return titles;
	}

	public <T> HSSFWorkbook generateExcel(String excelName,
			List<String> titles, Map<Integer, Object> dataMap) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建工作表实例
		HSSFSheet sheet = workbook.createSheet(excelName);
		HSSFRow rowTitle = sheet.createRow(0);
		for (int i = 0; i < titles.size(); i++) {
			createCell(rowTitle, i, HSSFCell.CELL_TYPE_STRING, titles.get(i));
		}
		List<DeclStatisticsDto> list = (List<DeclStatisticsDto>) dataMap.get(2);
		DeclStatisticsSingleDto singleDto = (DeclStatisticsSingleDto) dataMap
				.get(3);
		int sumFieldsSize = sumFields.size();
		try {
			int j = 0;
			for (; j < list.size(); j++) {
				DeclStatisticsDto t = list.get(j);
				HSSFRow row = sheet.createRow(j+1);
				Field fields[] = t.getClass().getDeclaredFields();
				int k = 0;
				for (; k < sumFieldsSize; k++) {
					String getMethodName = "get"
							+ RsToDtoUtil.initialsToUppercase(sumFields.get(k));
					Method method = t.getClass().getMethod(getMethodName);
					String value = (String) method.invoke(t);
					createCell(row, k, HSSFCell.CELL_TYPE_STRING, value);
				}
				for (int n=4; n< fields.length; n++,k++) {
					Field f = fields[n];
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
						throw new Exception(t.getClass()
								+ " 只支持int和String类型的变量");
					}
				}

			}

			HSSFRow row = sheet.createRow(j + 2);
			createCell(row, 0, HSSFCell.CELL_TYPE_STRING, "汇总");
			Field fields[] = singleDto.getClass().getDeclaredFields();
			for (int m = 0; m < fields.length; m++) {
				Field f = fields[m];
				Class<?> type = f.getType();
				String fName = f.getName();
				String getMethodName = "get"
						+ RsToDtoUtil.initialsToUppercase(fName);
				Method method = singleDto.getClass().getMethod(getMethodName);
				String typeName = type.getSimpleName();
				if ("String".equalsIgnoreCase(typeName)) {
					String value = (String) method.invoke(singleDto);
					createCell(row, m + sumFieldsSize,
							HSSFCell.CELL_TYPE_STRING, value);
				} else if ("Integer".equalsIgnoreCase(typeName)
						|| "int".equalsIgnoreCase(typeName)) {
					int value = (int) method.invoke(singleDto);
					createCell(row, m + sumFieldsSize,
							HSSFCell.CELL_TYPE_NUMERIC, value);
				} else {
					throw new Exception(singleDto.getClass()
							+ " 只支持int和String类型的变量");
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
