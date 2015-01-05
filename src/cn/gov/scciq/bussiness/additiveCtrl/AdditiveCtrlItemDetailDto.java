package cn.gov.scciq.bussiness.additiveCtrl;

import java.util.List;

import cn.gov.scciq.bussiness.convCtrl.ConvCtrlItemLimitDto;

/**
 * 添加剂布控详情
 * 
 * @author chao.xu
 *
 */
public class AdditiveCtrlItemDetailDto {
    private AdditiveCtrlItemDetailResDto itemDetail;
    
    private List<ConvCtrlItemLimitDto> itemLimitList;

    public AdditiveCtrlItemDetailResDto getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(AdditiveCtrlItemDetailResDto itemDetail) {
        this.itemDetail = itemDetail;
    }

    public List<ConvCtrlItemLimitDto> getItemLimitList() {
        return itemLimitList;
    }

    public void setItemLimitList(List<ConvCtrlItemLimitDto> itemLimitList) {
        this.itemLimitList = itemLimitList;
    }
    
    
}
