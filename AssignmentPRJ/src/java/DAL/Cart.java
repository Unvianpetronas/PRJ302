package DAL;

import Entity.ProductDTO;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    
    private Map<Integer, ProductDTO> cart;

    public Cart() {
        this.cart = new HashMap<>();
    }

    public Cart(Map<Integer, ProductDTO> cart) {
        this.cart = cart;
    }

    public Map<Integer, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<Integer, ProductDTO> cart) {
        this.cart = cart;
    }

    public boolean add(ProductDTO productDTO, int quantity) {
        boolean check = false;
        try {
            if (this.cart == null) {
                this.cart = new HashMap<>();
            }

            if (this.cart.containsKey(productDTO.getProductID())) {
                ProductDTO existingProduct = this.cart.get(productDTO.getProductID());
                existingProduct.setQuantity(existingProduct.getQuantity() + quantity);
            } else {
                
                ProductDTO cartItem = new ProductDTO(
                    productDTO.getProductID(),
                    productDTO.getName(),
                    productDTO.getDescription(),
                    productDTO.getPrice(),
                    quantity
                );
                this.cart.put(productDTO.getProductID(), cartItem);
            }
            check = true;
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return check;
    }

    public Map<Integer, ProductDTO> getItems() {
        return this.cart;
    }

    public boolean remove(int id) {
        boolean check = false;
        try {
            if (this.cart != null && this.cart.containsKey(id)) {
                this.cart.remove(id);
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean update(int id, int quantity) {
        boolean check = false;
        try {
            if (this.cart != null && this.cart.containsKey(id)) {
                this.cart.get(id).setQuantity(quantity);
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}
