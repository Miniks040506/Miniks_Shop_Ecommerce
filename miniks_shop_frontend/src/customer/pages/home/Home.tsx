import React from 'react';
import ElectricCategory from './electricCategory/ElectricCategory';
import CategoryGrid from './categoryGrid/CategoryGrid';
import Deal from './deal/Deal';
import ShopByCategory from './shopByCategory/ShopByCategory';
import { Button } from '@mui/material';
import { Storefront } from '@mui/icons-material';

const Home = () => {
  return (
    <>
        <div className='space-y-5 lg:space-y-10 relative pb-20'>
            <ElectricCategory />
            <CategoryGrid />
            
            <section className='pt-20'>
                <h1 className='text-lg lg:text-4xl font-bold text-primary-green pb-5 lg:pb-10 text-center'>
                    TODAY'S DEALS
                </h1>
                <Deal />
            </section>
            
            <section className='pt-20'>
                <h1 className='text-lg lg:text-4xl font-bold text-primary-green pb-5 lg:pb-10 text-center'>
                    SHOP BY CATEGORY
                </h1>
                <ShopByCategory />
            </section>
            
            <section className='mt-20 lg:px-20 relative h-50 lg:h-112.5 object-cover'>
                <img className='w-full h-full rounded-sm'
                    src="src/assets/seller_advise_logo.png"
                    alt="Advertise Logo" />
                    
                <div className='absolute top-1/2 left-4 lg:left-60 transform -translate-y-1/2 font-semibold lg:text-4xl space-y-3'>
                
                    <h1 className=''>Sell Your Product</h1>
                    <p 
                        className='text-lg md:text-2xl'>With <span className='logo text-4xl'>Miniks Shop</span>
                    </p>
                    
                    <div className='pt-6 flex justify-center'>
                        <Button startIcon={<Storefront />} variant='contained' size='large'>
                            Become Seller
                        </Button>
                    </div>
                    
                </div>
            </section>
            
        </div>  
    </>
  )
}

export default Home;