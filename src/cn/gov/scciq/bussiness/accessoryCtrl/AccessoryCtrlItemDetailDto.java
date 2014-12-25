package cn.gov.scciq.bussiness.accessoryCtrl;

import java.util.List;

import cn.gov.scciq.bussiness.convCtrl.ConvCtrlItemLimitDto;

/**
 * 辅料布控详情
 * 
 * @author chao.xu
 *
 */
public class AccessoryCtrlItemDetailDto {
    private AccessoryCtrlItemDetailResDto itemDetail;
    
    private List<ConvCtrlItemLimitDto> itemLimitList;

    public AccessoryCtrlItemDetailResDto getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(AccessoryCtrlItemDetailResDto itemDetail) {
        this.itemDetail = itemDetail;
    }

    public List<ConvCtrlItemLimitDto> getItemLimitList() {
        return itemLimitList;
    }

    public void setItemLimitList(List<ConvCtrlItemLimitDto> itemLimitList) {
        this.itemLimitList = itemLimitList;
    }
    
    
}
