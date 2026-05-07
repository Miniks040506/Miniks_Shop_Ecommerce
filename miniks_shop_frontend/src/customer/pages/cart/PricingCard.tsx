import { Divider } from '@mui/material';
import React from 'react';

const PricingCard = () => {
    return (
        <>
            <div className='space-y-3 p-5'>
                
                <div className='flex justify-between items-center'>
                    
                    <span>Subtotal</span>
                    
                    <span>1.800.000 ₫</span>
                    
                </div>
                
                <div className='flex justify-between items-center'>
                    
                    <span>Discount</span>
                    
                    <span>900.000 ₫</span>
                    
                </div>
                
                <div className='flex justify-between items-center'>
                    
                    <span>Shipping</span>
                    
                    <span>50.000 ₫</span>
                    
                </div>
                
                <div className='flex justify-between items-center'>
                    
                    <span>Plateform fee</span>
                    
                    <span className='text-primary'>Free</span>
                    
                </div>
                
            </div>  
            
            <Divider />
                
            <div className='flex justify-between items-center p-5 font-bold'>
                    
                <span>Total</span>
                    
                <span>950.000 ₫</span>
                    
            </div>
                
        </>
    )
}

export default PricingCard;