import { ThemeProvider } from '@mui/material';
import './App.css';
import Narbar from './customer/components/narbar/Narbar';
import customTheme from './theme/CustomTheme';
import Home from './customer/pages/home/Home';
import Product from './customer/pages/product/Product';
import ProductDetails from './customer/pages/page_details/ProductDetails';
import Review from './customer/pages/review/Review';
import Cart from './customer/pages/cart/Cart';


function App() {
    return (
        <div className="">

            <ThemeProvider theme={customTheme}>
                <Narbar />
                {/* <Home /> */}
                {/* <Product /> */}
                {/* <ProductDetails /> */}
                {/* <Review /> */}
                <Cart />
            </ThemeProvider>
        
        </div>
    )
}

export default App
