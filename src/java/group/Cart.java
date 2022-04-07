/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import user.UserDTO;

/**
 *
 * @author mac
 */
public class Cart {
    private Map<Integer, List<UserDTO>> cart;

    public Cart(Map<Integer, List<UserDTO>> cart) {
        this.cart = cart;
    }

    public Map<Integer, List<UserDTO>> getCart() {
        return cart;
    }

    public void setCart(Map<Integer, List<UserDTO>> cart) {
        this.cart = cart;
    }

    public Cart() {
    }
    public void add(int key, List<UserDTO> list){
        if(this.cart == null){
            this.cart = new HashMap<>();
        }

        cart.put(key, list);
    }
    public UserDTO getUser(int key){
        UserDTO user = null;
        List<UserDTO> list = cart.get(key);
        user = list.get(0);
        list.remove(0);
        return user;
    }

}
