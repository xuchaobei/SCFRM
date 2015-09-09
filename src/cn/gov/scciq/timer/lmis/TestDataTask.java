package cn.gov.scciq.timer.lmis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import cn.gov.scciq.bussiness.sampleRegister.SampleRegisterService;
import cn.gov.scciq.util.ConstantStr;

//读取实验室检测结果
public class TestDataTask extends TimerTask {
	private static boolean isRunning = false;
	private static Log log=LogFactory.getLog(TestDataTask.class);
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			if (!isRunning) {
				isRunning = true;
				getLabApplyNoForTestData();
				getLabApplyNoForDelSample();
				isRunning = false;
			} else {
				log.info("TestDataTask上一次认为还未结束！");
			}
		}catch(Exception e){
			log.info("TestDataTask失败！");
			isRunning = false;
		}	
	}

	public void getLabApplyNoForTestData() {
		try {
			Date now = new Date();
	        DateFormat d=DateFormat.getDateTimeInstance();	        
			log.info("读取结果----"+d.format(now));
			List<String> labApplyNoList = TestDataDao.getLabApplyNoForTestData();	
			updateItemLabData(labApplyNoList);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("",e);
		} 
	}
	
	public void getLabApplyNoForDelSample() {
		try {
			Date now = new Date();
	        DateFormat d=DateFormat.getDateTimeInstance();	        
			log.info("读是否可删除标识----"+d.format(now));
			List<String> labApplyNoList = TestDataDao.getLabApplyNoForDelSample();	
			updateDelSampleFlg(labApplyNoList);   
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("",e);
		} 
	}

	public void updateItemLabData(List<String> labApplyNoList) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost;
		HttpResponse response;
		HttpEntity entity;
	    Connection conn=null;
        CallableStatement proc = null;
		try {
		
			for (int j = 0; j < labApplyNoList.size(); j++) {			
				StringEntity stringEntity = new StringEntity(this.getHeader(
						"TestResult", labApplyNoList.get(j)), "text/xml",
						"utf-8");
				stringEntity.setChunked(true);
				httpPost = new HttpPost(
						ConstantStr.LRP_URL+"/ResultQuery/GetMultiple?apikey=bda11d91-7ade-4da1-855d-24adfe39d174&uid=1&uname=admin&utype=2");
				httpPost.setEntity(stringEntity);
				response = httpclient.execute(httpPost);

				// Examine the response status
				//System.out.println(response.getStatusLine());
				log.info(response.getStatusLine());

				// Get hold of the response entity
				entity = response.getEntity();

				// If the response does not enclose an entity, there is no need
				// to worry about connection release
				if (entity != null) {
					InputStream instream = entity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(instream, "utf-8"));
					StringBuffer temp = new StringBuffer();
					String line = reader.readLine();
					while (line != null) {
						temp.append(line).append("\r\n");
						line = reader.readLine();
					}
					reader.close();
					instream.close();
					String result = temp.toString();
					log.info(result);
					SampleRegisterService.generateXmlFile(result, "AcceptLMISItem");
					Reader rr = new StringReader(result);
					DocumentBuilderFactory builderFactory = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder domBuilder = builderFactory
							.newDocumentBuilder();
					Document document = domBuilder.parse(new InputSource(rr));
					Element gsp = document.getDocumentElement();
					Node sampleItemList = gsp.getElementsByTagName(
							"biz_SampleItemList").item(0);
					//if(testFlag==1){
						if (sampleItemList.getChildNodes().getLength() != 0	) {		
							NodeList listOfItem = gsp
									.getElementsByTagName("biz_SampleItem");
							
							boolean ifAllSuccess = true;
							for (int i = 0; i < listOfItem.getLength(); i++) {
								
								Node ItemOfgsp = listOfItem.item(i);
								String DpTestItemID = null; 
								String ResultData = null;
								String ResultUnit = null;
								String SampleID = null; 
								for (Node node = ItemOfgsp.getFirstChild(); node != null; node = node
										.getNextSibling()) {
									if (node.getNodeType() == Node.ELEMENT_NODE) {
										if (node.getNodeName().equals(
												"TestItemID")) {
											DpTestItemID = node.getFirstChild()
													.getNodeValue();
											//System.out.println(DpTestItemID);
											log.info(DpTestItemID);
										} else if (node.getNodeName().equals(
												"DeliveryNo")) {
											if (node.getChildNodes().getLength() != 0) {
												SampleID = node.getFirstChild()
														.getNodeValue();
												//System.out.println(SampleID);
												log.info(SampleID);
											} else
												SampleID = null;
										} else if (node.getNodeName().equals(
												"ResultData")) {
											if (node.getChildNodes().getLength() != 0) {
												ResultData = node.getFirstChild()
														.getNodeValue();
												//System.out.println(ResultData);
												log.info(ResultData);
											} else
												ResultData = "";
										} else if (node.getNodeName()
												.equals("Unit")) {
											if (node.getChildNodes().getLength() != 0) {
												ResultUnit = node.getFirstChild()
														.getNodeValue();
												//System.out.println(ResultUnit);
												log.info(ResultUnit);
											} else
												ResultUnit = "";

										}
									}
								}
								if (SampleID != null) { 
								    log.info("更新检测结果-- 报验号:"+labApplyNoList.get(j)+ "--样品号："+SampleID);
									int ReturnCode = 0;
									ReturnCode = TestDataDao.updateItemLabData(SampleID, DpTestItemID, ResultData, ResultUnit);
									
									if (ReturnCode == 0){
										log.info("报验号："+labApplyNoList.get(j)+"--样品号："+SampleID+" 读取结果失败");
										ifAllSuccess = false;
									}
								}else{
								    log.info(labApplyNoList.get(j)+"--sampleId为空，未保存结果");
								    ifAllSuccess = false;
								}							
							}
							if(ifAllSuccess){
								TestDataDao.updateReadTestDataFlg(labApplyNoList.get(j));
							}							
						}else
							log.info(labApplyNoList.get(j)+" 没有检测结果");
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			log.error(e);
			//e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			log.error(e);
			//e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			log.error(e);
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e);
			//e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			log.error(e);
			//e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			log.error(e);
			//e.printStackTrace();
		} catch (SQLException e) {
			log.error(e);
			//e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			//e.printStackTrace();
		} finally {
		    try{
                if(proc!=null)
                    proc.close();
                if(conn!=null)
                    conn.close();                       
            } catch(SQLException e){
                log.error(e);
            }   
			httpclient.getConnectionManager().shutdown();
		}
	}

	public void updateDelSampleFlg(List<String> labApplyIDList){
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet;
		HttpResponse response;
		HttpEntity entity;
		try {
			for (int i = 0; i < labApplyIDList.size(); i++) {
				httpGet = new HttpGet(ConstantStr.LRP_URL
						+ "/BizModify/CheckIsCanDelete?apikey=bda11d91-7ade-4da1-855d-24adfe39d174&uid=1&uname=admin&utype=2&BizID="
						+ labApplyIDList.get(i));

				response = httpclient.execute(httpGet);
				log.info(response.getStatusLine());
				entity = response.getEntity();

				if (entity != null) {
					InputStream instream = entity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(instream, "utf-8"));
					StringBuffer temp = new StringBuffer();
					String line = reader.readLine();
					while (line != null) {
						temp.append(line).append("\r\n");
						line = reader.readLine();
					}
					reader.close();
					instream.close();
					String result = temp.toString();
					log.info(result);
					Reader rr = new StringReader(result);
					DocumentBuilderFactory builderFactory = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder domBuilder = builderFactory
							.newDocumentBuilder();
					Document document = domBuilder.parse(new InputSource(rr));
					Element root = document.getDocumentElement();
					Node isCanDel = root.getElementsByTagName("IsCanDelete")
							.item(0);
					if (isCanDel.getFirstChild().getNodeValue() != null) {
						String delFlag = isCanDel.getFirstChild()
								.getNodeValue();
						if (!delFlag.equalsIgnoreCase("True"))
							TestDataDao.updateDelSampleFlg(labApplyIDList.get(i));
					}
				}
			}
		}catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error(e);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error(e);
		} catch (Exception e) {
			log.error(e);
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}
	
	

	public String getHeader(String judge, String labApplyNo) {
		String xmlStr;
		org.jdom.Element root = new org.jdom.Element("DataRoot");
		org.jdom.Document doc = new org.jdom.Document(root);
		if (judge.equals("TestResult")) {
			org.jdom.Element sql = new org.jdom.Element("Sql");
			sql.setText(" AND m.BizNo='" + labApplyNo+"' AND BizStatusOfItem=40490");
			root.addContent(sql);
		} else if (judge.equals("ifReport")) {
			org.jdom.Element sql = new org.jdom.Element("Sql");
			// sql.setText( "AND m.BizID=301");
			sql.setText(" And BizNo='" + labApplyNo+"' AND (BizStatus = 10190 or ReportStatus = 50560)");
		/*	org.jdom.Element optionCode = new org.jdom.Element("OptionCode");
			optionCode.setText("2");
			org.jdom.Element formCode = new org.jdom.Element("FormCode");
			formCode.setText("65");*/
			org.jdom.Element pageSize = new org.jdom.Element("PageSize");
			pageSize.setText("1");
			org.jdom.Element pageIndex = new org.jdom.Element("PageIndex");
			pageIndex.setText("0");
			root.addContent(sql);
			//root.addContent(optionCode);
			//root.addContent(formCode);
			root.addContent(pageSize);
			root.addContent(pageIndex);
		}
		org.jdom.output.Format format = org.jdom.output.Format
				.getPrettyFormat();
		format.setEncoding("utf-8");
		XMLOutputter xmlout = new XMLOutputter(format);
		// xmlout.output(Doc, new PrintWriter(System.out));
		xmlStr = xmlout.outputString(doc);
		//System.out.println(xmlStr);
		return xmlStr;
	}
}
