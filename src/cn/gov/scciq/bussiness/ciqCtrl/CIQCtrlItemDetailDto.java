package cn.gov.scciq.bussiness.ciqCtrl;

import java.util.List;

import cn.gov.scciq.bussiness.riskCtrl.ConvCtrlItemLimitDto;

/**
 * 布控项目详情及限量表
 * @author chao.xu
 *
 */
public class CIQCtrlItemDetailDto {

    private CIQCtrlItemDetailResDto itemDetail;
    
    private List<ConvCtrlItemLimitDto> itemLimitList;

    public CIQCtrlItemDetailResDto getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(CIQCtrlItemDetailResDto itemDetail) {
        this.itemDetail = itemDetail;
    }

    public List<ConvCtrlItemLimitDto> getItemLimitList() {
        return itemLimitList;
    }

    public void setItemLimitList(List<ConvCtrlItemLimitDto> itemLimitList) {
        this.itemLimitList = itemLimitList;
    }

    
    
}
