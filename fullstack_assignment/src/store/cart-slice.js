import { createSlice } from "@reduxjs/toolkit";

const cartSlice = createSlice({
    name: "cart",
    initialState: 
    {
        itemList : [],
        totalQuantity: 0,
        showCart: false,
    },

    reducers: {
        addToCart(state, action){
            const newItem = action.payload;
            //to check if the item is already available
            const existingItem = state.itemList.find(item => item.id ===newItem.id)
            if(existingItem){
                existingItem.quantity++;
                existingItem.totalPrice += newItem.price;
               
            }
            else{
                state.itemList.push({
                    id : newItem.id,
                    price: newItem.price,
                    quantity: 1,
                    totalPrice : newItem.price,
                    name: newItem.name
                })
            }
            state.totalQuantity++;
        },
        removeFromCart(state, action){
        const id =  action.payload;
        const targetedItem = state.itemList.find(item=> item.id === id)
        if(targetedItem.quantity === 1){
        var updatedItemList = state.itemList.filter(i=> i.id !==targetedItem.id);
        state.itemList = updatedItemList;
        }
        else{
            targetedItem.quantity--;
        }
       state.totalQuantity--;
        },
        setShowCart(state){ state.showCart = !state.showCart;}
    }
})

export const CartActions = cartSlice.actions;
export default cartSlice;