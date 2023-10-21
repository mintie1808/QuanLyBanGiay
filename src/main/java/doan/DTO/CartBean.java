package doan.DTO;

import java.util.HashMap;

public class CartBean extends HashMap<String, ProductDTO> {
	public void addSanPham(ProductDTO item) {
		String key = String.valueOf(item.getSanpham().getProductID());
		if (this.containsKey(key)) {
			int oldQuantity = ((ProductDTO) this.get(key)).getQuantity();
			((ProductDTO) this.get(key)).setQuantity(oldQuantity + 1);
		} else {
			this.put(key, item);
		}
	}

	public boolean removeSanPham(String id) {
	        if (this.containsKey(String.valueOf(id))) {
	            this.remove(id);
	            return true;
	        }
	    return false;
	}

	public CartBean() {
		super();

	}
}
