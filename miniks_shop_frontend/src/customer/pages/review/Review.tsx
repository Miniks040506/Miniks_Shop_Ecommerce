import React from 'react';
import ReviewCard from './ReviewCard';
import { Divider } from '@mui/material';

const Review = () => {
    return (
        <div className='p-5 lg:px-20 flex flex-col lg:flex-row gap-20'>
            
            <section className='w-full md:w-1/2 lg:w-[35%] space-y-2'>
                
                <img 
                    src="https://www.montecarlo.in/cdn/shop/files/224261780-2-38_1.jpg?v=1739291547&width=800" 
                    alt="Product" 
                />
                
                <div>
                    
                    <div>
                        
                        <p className='font-bold text-xl'>MONTE CARLO</p>
                        
                        <p className='text-lg text-gray-600'>Men Olive Solid Mock Neck Full Sleeve Jacket</p>
                        
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
                        
                        {/* <p className='text-sm'>Inclusive of all taxes. Free Shipping above 1.000.000 ₫.</p> */}
                        
                    </div>
                    
                </div>
                
            </section>
            
            <section className='space-y-5'>
                
                {
                    [1, 1, 1, 1, 1].map(() => 
                        <div className='space-y-3'>
                            
                            <ReviewCard />
                            
                            <Divider />
                            
                        </div>
                    )
                }
                
            </section>
            
        </div>
    )
}

export default Review;