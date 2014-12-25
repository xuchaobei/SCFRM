package cn.gov.scciq.bussiness.convCtrl;

import java.util.List;

/**
 * 布控项目详情及限量表
 * @author chao.xu
 *
 */
public class ConvCtrlItemDetailDto {

    private ConventionCtrlItemDetailResDto itemDetail;
    
    private List<ConvCtrlItemLimitDto> itemLimitList;

    public ConventionCtrlItemDetailResDto getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(ConventionCtrlItemDetailResDto itemDetail) {
        this.itemDetail = itemDetail;
    }

    public List<ConvCtrlItemLimitDto> getItemLimitList() {
        return itemLimitList;
    }

    public void setItemLimitList(List<ConvCtrlItemLimitDto> itemLimitList) {
        this.itemLimitList = itemLimitList;
    }
    
    
}
