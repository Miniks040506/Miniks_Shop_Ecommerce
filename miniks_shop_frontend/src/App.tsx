import { ThemeProvider } from '@mui/material';
import './App.css';
import Narbar from './customer/components/narbar/Narbar';
import customTheme from './theme/CustomTheme';
import Home from './customer/pages/home/Home';
import Product from './customer/pages/Product/Product';


function App() {
    return (
        <div className="">

            <ThemeProvider theme={customTheme}>
                <Narbar />
                {/* <Home /> */}
                <Product />
            </ThemeProvider>
        
        </div>
    )
}

export default App
