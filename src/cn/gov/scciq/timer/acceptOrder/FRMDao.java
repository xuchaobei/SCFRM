package cn.gov.scciq.timer.acceptOrder;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.dbpool.DBPool;

/**
 * FRM数据库Dao
 * 
 * @author chao.xu
 *
 */
public class FRMDao {
    private static Log log=LogFactory.getLog(FRMDao.class);
    
    
    
    /**
     * 保存CIQ报检数据
     * --返回码，0成功；非0：失败。
     */
    public static int saveCIQDeclInfo(CEMSDeclDataDto declDto){
        int retCode = -1;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveCIQDeclInfo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, declDto.getDecl_No());
            proc.setString(2, declDto.getDecl_Get_No());
            proc.setString(3, declDto.getDecl_Reg_No());
            proc.setString(4, declDto.getDecl_Person_Code());
            proc.setString(5, declDto.getDecl_Date());
            proc.setString(6, declDto.getEnt_Property());
            proc.setString(7, declDto.getConsignor_Code());
            proc.setString(8, declDto.getConsignor_CName());
            proc.setString(9, declDto.getConsignor_EName());
            proc.setString(10, declDto.getConsignee_Code());
            proc.setString(11, declDto.getConsignee_CName());
            proc.setString(12, declDto.getConsignee_EName());
            proc.setString(13, declDto.getTrans_Type_Code());
            proc.setString(14, declDto.getTrans_Means_Code());
            proc.setString(15, declDto.getTonnage());
            proc.setString(16, declDto.getTrade_Mode_Code());
            proc.setString(17, declDto.getGoods_Place_Code());
            proc.setString(18, declDto.getGoods_Place());
            proc.setString(19, declDto.getPurpose_Code());
            proc.setString(20, declDto.getDecl_Date());
            proc.setString(21, declDto.getArri_Date());
            proc.setString(22, declDto.getUnload_Date());
            proc.setString(23, declDto.getDesp_Port_Code());
            proc.setString(24, declDto.getArri_Port_Code());
            proc.setString(25, declDto.getEntry_Port_Code());
            proc.setString(26, declDto.getVia_Port_Code());
            proc.setString(27, declDto.getDest_Code());
            proc.setString(28, declDto.getTrade_Country_Code());
            proc.setString(29, declDto.getDesp_Country_Code());
            proc.setString(30, declDto.getSituation_Code());
            proc.setString(31, declDto.getSituation_Level());
            proc.setString(32, declDto.getCounter_Claim());
            proc.setString(33, declDto.getContract_No());
            proc.setString(34, declDto.getCarrier_Note_No());
            proc.setString(35, declDto.getLicense_Code());
            proc.setString(36, declDto.getApprove_Code());
            proc.setString(37, declDto.getProd_Reg_No());
            proc.setString(38, declDto.getExchange_Note_Codes());
            proc.setString(39, declDto.getExchange_Note_Num());
            proc.setString(40, declDto.getPack_Cap_Resu_Codes());
            proc.setString(41, declDto.getPack_Use_Resu_Codes());
            proc.setString(42, declDto.getSheet_Type_Codes());
            proc.setString(43, declDto.getCert_Type_Codes());
            proc.setString(44, declDto.getCert_Originals());
            proc.setString(45, declDto.getCert_Copies());
            proc.setString(46, declDto.getSpecial_Require());
            proc.setString(47, declDto.getMark_No());
            proc.setString(48, declDto.getValue_Checkup_Flag());
            proc.setString(49, declDto.getInsp_Mode_Code());
            proc.setString(50, declDto.getInsp_Org_Code());
            proc.setString(51, declDto.getInsp_Dept_1());
            proc.setString(52, declDto.getInsp_Dept_2());
            proc.setString(53, declDto.getInsp_Dept_3());
            proc.setString(54, declDto.getInsp_Dept_4());
            proc.setString(55, declDto.getInsp_Dept_5());
            proc.setString(56, declDto.getDecl_Type_Code());
            proc.setString(57, declDto.getProcess_Status());
            proc.setString(58, declDto.getFee_Status());
            proc.setString(59, declDto.getValues_USD());
            proc.setString(60, declDto.getValues_RMB());
            proc.setString(61, declDto.getOperator_Code());
            proc.setString(62, declDto.getOperate_Date());
            proc.setString(63, declDto.getStat_Flag());
            proc.setString(64, declDto.getWaste_Flag());
            proc.setString(65, declDto.getRelease_Status());
            proc.setString(66, declDto.getCheckup_Type_Code());
            proc.setString(67, declDto.getCheckup_Work_Code());
            proc.setString(68, declDto.getOrg_Code());
            proc.setString(69, declDto.getDept_Code());
            proc.setString(70, declDto.getDest_Org_Code());
            proc.setString(71, declDto.getFlow_Flag());
            proc.setString(72, declDto.getTrans_Flag());
            proc.setString(73, declDto.getInputer_Code());
            proc.setString(74, declDto.getInput_Date());
            proc.setString(75, declDto.getEnt_Decl_No());
            proc.setString(76, declDto.getO_I_Flag());
            proc.setString(77, declDto.getChg_Org_Code());
            proc.setString(78, declDto.getChg_Person_Code());
            proc.setString(79, declDto.getChg_Date());
            proc.setString(80, declDto.getCheck_Flag());
            proc.setString(81, declDto.getInput_Org_Code());
            proc.setString(82, declDto.getDECL_FLAG());
            proc.setString(83, declDto.getCUSTOM_CODE());
            proc.setString(84, declDto.getAUTO_PASS_FLAG());
            proc.setString(85, declDto.getAUTO_CHECK_FLAG());
            proc.setString(86, declDto.getBACK_TRANSPORT_FLAG());
            proc.setString(87, declDto.getCONTACTOR());
            proc.setString(88, declDto.getTELEPHONE());
            proc.setString(89, declDto.getPROCESS_FLAG());
            proc.setString(90, declDto.getMONITOR_PASS_FLAG());
            proc.setString(91, declDto.getCOMPUTE_PASS_FLAG());
            proc.setString(92, declDto.getTRANS_MEANS_NAME());
            proc.setString(93, declDto.getMONITOR_FLAG());
            proc.setString(94, declDto.getSPOTTEST_FLAG());
            proc.setString(95, declDto.getCOMB_BATCH_NO());
            proc.setString(96, declDto.getRESEND_NUM());
            proc.setString(97, declDto.getCHANGE_INSP_DEPT_FLAG());
            proc.setString(98, declDto.getCIQ2000_ASSIGN_FLAG());
            proc.setString(99, declDto.getMonitor_Decl_Flag());
            proc.setString(100, declDto.getSEQ_NUM());
            proc.registerOutParameter(101, Types.INTEGER);
            proc.execute();
            retCode = proc.getInt(101);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("num9", e);
        } catch (Exception e) {
            log.error("num10", e);
        } finally{
            try {
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                log.error("num11", e);
            }
        }
        return retCode;
    }
    
    /**
     * 保存CIQ货物数据
     * @return
     */
    public static int saveCIQGoodsInfo(CEMSGoodsDataDto goodsDto){
        int retCode = -1;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveCIQGoodsInfo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, goodsDto.getDecl_No());
            proc.setString(2, goodsDto.getGoods_No());
            proc.setString(3, goodsDto.getHS_Code());
            proc.setString(4, goodsDto.getGoods_CName());
            proc.setString(5, goodsDto.getGoods_EName());
            proc.setString(6, goodsDto.getGoods_Model());
            proc.setString(7, goodsDto.getOrigin_Place_Code());
            proc.setString(8, goodsDto.getOrigin_Country_Code());
            proc.setString(9, goodsDto.getWeight());
            proc.setString(10, goodsDto.getWeight_Unit_Code());
            proc.setString(11, goodsDto.getSTD_Weight());
            proc.setString(12, goodsDto.getSTD_Weight_Unit_Code() );
            proc.setString(13, goodsDto.getQTY());
            proc.setString(14, goodsDto.getQTY_Unit_Code());
            proc.setString(15, goodsDto.getSTD_QTY());
            proc.setString(16, goodsDto.getSTD_QTY_Unit_Code());
            proc.setString(17, goodsDto.getPack_Number());
            proc.setString(18, goodsDto.getPack_Type_Code());
            proc.setString(19, goodsDto.getCCY());
            proc.setString(20, goodsDto.getRate());
            proc.setString(21, goodsDto.getPrice());
            proc.setString(22, goodsDto.getGoods_Values());
            proc.setString(23, goodsDto.getValues_USD());
            proc.setString(24, goodsDto.getValues_RMB());
            proc.setString(25, goodsDto.getCIQ_CODE());
            proc.setString(26, goodsDto.getINSP_MODE_CODE());
            proc.setString(27, goodsDto.getCONDITION_FLAG());
            proc.setString(28, goodsDto.getWASTE_FLAG());
            proc.setString(29, goodsDto.getCHECKUP_TYPE_CODE());
            proc.setString(30, goodsDto.getCHECKUP_WORK_CODE());
            proc.setString(31, goodsDto.getSITUATION_CODE());
            proc.setString(32, goodsDto.getSITUATION_LEVEL());
            proc.setString(33, goodsDto.getPURPOSE_CODE());
            proc.setString(34, goodsDto.getNOTIFY_NOS());
            proc.setString(35, goodsDto.getPROD_NO());
            proc.setString(36, goodsDto.getCOMBBATCH_NO());
            proc.registerOutParameter(37, Types.INTEGER);
            proc.execute();
            retCode = proc.getInt(37);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("N30", e);
        } catch (Exception e){
            log.error("N31", e);
        } finally{
            try {
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                log.error("N32", e);
            }
        }
        return retCode;
    }
    
    /**
     * 根据监管标识处理相关事务
     */
    public static int declFlagAction(String declNo){
        int retCode = -1;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DeclFlagAction(?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, declNo);
            proc.registerOutParameter(2, Types.INTEGER);
            proc.execute();
            retCode = proc.getInt(2);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("N33", e);
        } catch (Exception e){
            log.error("N34", e);
        } finally{
            try {
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                log.error("N35", e);
            }
        }
        return retCode;
    }
    
    /**
     * 计算所有出口报检产品及项目的抽检比，以及判定是否抽检
     * @param declNo
     */
    public static void calculateAllDeclProductSampling(String declNo){
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_CalculateAllDeclProductSampling(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, declNo);
            proc.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("N36", e);
        } catch (Exception e){
            log.error("N37", e);
        } finally{
            try {
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                log.error("N38", e);
            }
        }
    }
    
    
    /**
     * 检查企业是否存在
     */
    public static int checkEntExists(String entCode){
        int retCode = -1;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_CheckEntExists(?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            System.out.println("entcode"+entCode);
            proc.setString(1, entCode);
            proc.registerOutParameter(2, Types.INTEGER);
            proc.execute();
            retCode = proc.getInt(2);
            System.out.println("retcode"+retCode);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("N39", e);
        } catch (Exception e){
            log.error("N40", e);
        } finally{
            try {
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                log.error("N41", e);
            }
        }
        return retCode;
    }
    
    /**
     * 企业不存在后的处理：报检为异常
     * @param declNo
     */
    public static void saveDeclInfoAbnormal(String declNo,int flg){
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveDeclInfoAbnormal(?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, declNo);
            proc.setInt(2, flg);
            proc.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("N42", e);
        } catch (Exception e){
            log.error("N43", e);
        } finally{
            try {
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                log.error("N44", e);
            }
        }
    }
    
    /**
     * 保存企业端输入的厂检单数据
     * @param exchangeDto
     */
    public static void saveDeclProductFromEnt(CEMSDeclExchangeDto exchangeDto){
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveDeclProductFromEnt(?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, exchangeDto.getSEQ_NUM());
            proc.setString(2, exchangeDto.getDECL_NO_TYPE());
            proc.setString(3, exchangeDto.getDECL_NO());
            proc.setString(4, exchangeDto.getRESULT_CONTENT());
            proc.setString(5, exchangeDto.getUPDATE_COUNT());
            proc.setString(6, exchangeDto.getLAST_OPER_DATE());
            proc.setString(7, exchangeDto.getGEN_FLAG());
            proc.setString(8, exchangeDto.getTRUE_DECL_NO());
            proc.setString(9, exchangeDto.getPROD_NO());
            proc.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("N45", e);
        } catch (Exception e){
            log.error("N46", e);
        } finally{
            try {
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                log.error("N47", e);
            }
        }
    }
    
    /**
     * 保存风险管理系统中的报检数据（实际上从CIQ报检数据提取有用信息，因此要先保存CIQ报检数据）
     */
    public static int saveDeclInfo(String declNo){
        int retCode = -1;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveDeclInfo(?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, declNo);
            proc.registerOutParameter(2, Types.INTEGER);
            proc.execute();
            retCode = proc.getInt(2);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("N48", e);
        } catch (Exception e){
            log.error("N49", e);
        } finally{
            try {
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                log.error("N50", e);
            }
        }
        return retCode;
    }
    
    /**
     * 取得厂检单数据
     * @param declNo
     * @return
     */
    public static String getDeclProductFromEnt(String declNo){
        String declProductFromEnt = null;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetDeclProductFromEnt(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, declNo);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                declProductFromEnt = rs.getString("RESULT_CONTENT");
                break;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("N51", e);
        } catch (Exception e){
            log.error("N52", e);
        } finally{
            try {
                if(rs != null){
                    rs.close();
                }
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                log.error("N53", e);
            }
        }
        return declProductFromEnt;
    }
    
    /**
     * 保存从厂检单解析出来的出口产品数据
     */
    public static int saveDeclProduct(String declNo, String entProductCode, String baseCode, String goodsNo){
        int retCode = -1;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_SaveDeclProduct(?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, declNo);
            proc.setString(2, entProductCode);
            proc.setString(3, baseCode);
            proc.setString(4, goodsNo);
            proc.registerOutParameter(5, Types.INTEGER);
            proc.execute();
            retCode = proc.getInt(5);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("N54", e);
        } catch (Exception e){
            log.error("N55", e);
        } finally{
            try {
                if(rs != null){
                    rs.close();
                }
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                log.error("N56", e);
            }
        }
        return retCode;
    }
    
    
    /**
     * 检查企业填写厂检单的出口产品是否完整
     * @param declNo
     * @return
     */
    public static int checkDeclProductPerfect(String declNo){
        int retCode = -1;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_CheckDeclProductPerfect(?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, declNo);
            proc.registerOutParameter(2, Types.INTEGER);
            proc.execute();
            retCode = proc.getInt(2);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error(e);
        } catch (Exception e){
            log.error(e);
        } finally{
            try {
                if(rs != null){
                    rs.close();
                }
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                log.error(e);
            }
        }
        return retCode;
    }
    
    public static int checkDeclProductSampling(String declNo){
        int retCode = -1;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_CheckDeclProductSampling(?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, declNo);
            proc.registerOutParameter(2, Types.INTEGER);
            proc.execute();
            retCode = proc.getInt(2);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("N57", e);
        } catch (Exception e){
            log.error("N58", e);
        } finally{
            try {
                if(rs != null){
                    rs.close();
                }
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                log.error("N59", e);
            }
        }
        return retCode;
    }
    
    /**
     * 检查是否满足快速核放:系统自动接单时调用
     * @return
     */
    public static int checkRapidRelease(String declNo){
        int retCode = -1;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_CheckRapidRelease(?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, declNo);
            proc.registerOutParameter(2, Types.INTEGER);
            proc.execute();
            retCode = proc.getInt(2);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("N60", e);
        } catch (Exception e){
            log.error("N61", e);
        } finally{
            try {
                if(rs != null){
                    rs.close();
                }
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                log.error("N62", e);
            }
        }
        return retCode;
    }
    
    
    /**
     * 快速核放
     * @param declNo
     */
    public static void rapidRelease(String declNo){
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_RapidRelease(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, declNo);
            proc.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("N63", e);
        } catch (Exception e){
            log.error("N64", e);
        } finally{
            try {
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                log.error("N65", e);
            }
        }
    }
}
