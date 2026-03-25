import React, { useState } from 'react';
import StarIcon from '@mui/icons-material/Star';
import { orange } from '@mui/material/colors';
import { Button, Divider } from '@mui/material';
import { Add, AddShoppingCart, FavoriteBorder, LocalShipping, Remove, Shield, Wallet, WorkspacePremium } from '@mui/icons-material';
import SimilarProduct from './SimilarProduct';
import ReviewCard from '../review/ReviewCard';

const ProductDetails = () => {
    
    const [quantity, setQuantity] = useState(1);
    
    return (
        <div className='px-5 lg:px-20 pt-10'>
            
            <div className='grid grid-cols-1 lg:grid-cols-2 gap-10'>
                
                <section className='flex flex-col lg:flex-row gap-5'>
                    
                    <div className='w-full lg:w-[15%] flex flex-wrap lg:flex-col gap-3'>
                        {
                            [1, 1, 1, 1, 1].map(() => 
                                <img className='lg:w-full w-[50px] cursor-pointer rounded-md'
                                    src="https://www.montecarlo.in/cdn/shop/files/224261780-2-38_1.jpg?v=1739291547&width=800" 
                                    alt="Product Details" 
                                />
                            )
                        }
                    </div>
                    
                    <div className='w-full lg:w-[85%]'>
                        <img className='w-full rounded-md'
                            src="https://www.montecarlo.in/cdn/shop/files/224261780-2-38_1.jpg?v=1739291547&width=800" 
                            alt="Product Details" 
                        />
                    </div>
                    
                </section>
                
                <section>
                    
                    <h1 className='text-primary font-bold text-lg'>
                        MONTE CARLO
                    </h1>
                    
                    <p className='text-gray-500 font-semibold'>
                        Men Olive Solid Mock Neck Full Sleeve Jacket
                    </p>
                    
                    <div className='flex justify-between items-center py-2 border w-45 px-3 mt-5'>
                        
                        <div className='flex gap-1 items-center'>
                            <span>4.3</span>
                            <StarIcon sx={{color: orange[800], fontSize: "17px"}} />
                        </div>
                        
                        <Divider orientation='vertical' flexItem />
                        
                        <span>324 Ratings</span>
                        
                    </div>
                    
                    <div>
                        
                        <div className='price flex items-center gap-3 mt-5 text-2xl'>
                            
                            <span className='font-sans text-gray-800'>
                                900.000 ₫
                            </span>
                            
                            <span className='line-through text-gray-400'>
                                1.800.000 ₫
                            </span>   
                            
                            <span className='text-primary font-semibold'>
                                50% off
                            </span> 
                            
                        </div>
                        
                        <p className='text-sm'>Inclusive of all taxes. Free Shipping above 1.000.000 ₫.</p>
                        
                    </div>
                    
                    <div className='mt-7 space-y-3'>
                        
                        <div className='flex items-center gap-4'>
                            
                            <Shield sx={{color: orange[900]}} />
                            <p>Authentic & Quality Assured</p>
                            
                        </div>
                        
                        <div className='flex items-center gap-4'>
                            
                            <WorkspacePremium sx={{color: orange[900]}} />
                            <p>100% money back guarantee</p>
                            
                        </div>
                        
                        <div className='flex items-center gap-4'>
                            
                            <LocalShipping sx={{color: orange[900]}} />
                            <p>Free Shipping & Returns</p>
                            
                        </div>
                        
                        <div className='flex items-center gap-4'>
                            
                            <Wallet sx={{color: orange[900]}} />
                            <p>Pay on delivery might be available</p>
                            
                        </div>
                    
                    </div>
                    
                    <div className='mt-7 space-y-2'>
                            
                        <h1>QUANTITY</h1>
                            
                        <div className='flex items-center gap-2 w-35 justify-between'>
                                
                            <Button disabled={quantity == 1} onClick={() => setQuantity(quantity - 1)}>
                                <Remove />
                            </Button>
                                
                            <span>
                                {quantity}
                            </span>
                                
                            <Button onClick={() => setQuantity(quantity + 1)}>
                                <Add />
                            </Button>
                                
                        </div>
                            
                    </div>
                    
                    <div className='mt-12 flex items-center gap-5'>
                        
                        <Button
                            fullWidth
                            variant='contained' 
                            startIcon={<AddShoppingCart />} 
                            sx={{py: "1rem"}}
                        >
                            Add To Bag
                        </Button>
                        
                        <Button
                            fullWidth
                            variant='outlined' 
                            startIcon={<FavoriteBorder />} 
                            sx={{py: "1rem"}}
                        >
                            WishList
                        </Button>
                        
                    </div>
                    
                    <div className='mt-5'>
                        <p>
                            The jacket is a stylish, light-wash denim jacket featuring a classic button-down front and modern distressed detailing. It includes dual chest pockets and a tailored fit, designed for a trendy, casual look. This high-quality piece is currently offered at a 50% discount for 500.000 ₫.
                        </p>
                    </div>
                    
                    <div className='mt-12 space-y-5'>
                        
                        <ReviewCard />
                        
                        <Divider />
                        
                    </div>
                    
                </section>
                
            </div>
            
            <div className='mt-20'>
                <h1 className='text-lg font-bold'>
                    Similar Product
                </h1>
                <div className='pt-5'>
                    <SimilarProduct />
                </div>
            </div>
            
        </div>
    )
}

export default ProductDetails;