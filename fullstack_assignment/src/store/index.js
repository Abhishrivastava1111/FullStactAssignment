import authSlice from "./auth-slice";
import { configureStore,  } from "@reduxjs/toolkit";
import cartSlice from "./cart-slice";

const store = configureStore({
    reducer:{
        auth: authSlice.reducer,
        cart: cartSlice.reducer,
    },
    
}) 

export default store