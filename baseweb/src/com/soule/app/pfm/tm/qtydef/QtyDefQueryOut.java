package com.soule.app.pfm.tm.qtydef;

import java.io.Serializable;
import java.util.List;

//import com.soule.app.pub.prd.ProductPo;
import com.soule.base.service.IServiceResult;
import com.soule.base.service.ServiceResult;

public class QtyDefQueryOut implements Serializable,IServiceResult {
	private static final long serialVersionUID = 8443413781268075892L;
		private ServiceResult head = new ServiceResult();
	    private List<QtyDefPo> qtyDef;

	    private QtyDefPo oneQtyDef;
	    
	    //private ProductPo oneProduct;
	    private List<QtyDefPo> qtyDayScopeList;
	    private QtyExpDefPo qtyExp;
	    public ServiceResult getResultHead() {
	        return head;
	    }

	    public void setResultHead(ServiceResult head) {
	        this.head = head;
	    }

		public List<QtyDefPo> getQtyDef() {
			return qtyDef;
		}
		public void setQtyDef(List<QtyDefPo> qtyDef) {
			this.qtyDef = qtyDef;
		}
		public QtyDefPo getOneQtyDef() {
			return oneQtyDef;
		}
		public void setOneQtyDef(QtyDefPo oneQtyDef) {
			this.oneQtyDef = oneQtyDef;
		}
		/*public ProductPo getOneProduct() {
			return oneProduct;
		}
		public void setOneProduct(ProductPo oneProduct) {
			this.oneProduct = oneProduct;
		}*/
		public List<QtyDefPo> getQtyDayScopeList() {
            return qtyDayScopeList;
        }
		public void setQtyDayScopeList(List<QtyDefPo> qtyDayScopeList) {
            this.qtyDayScopeList = qtyDayScopeList;
        }

		public QtyExpDefPo getQtyExp() {
			return qtyExp;
		}

		public void setQtyExp(QtyExpDefPo qtyExp) {
			this.qtyExp = qtyExp;
		}
}
