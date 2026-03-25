import React, { useState } from 'react';
import CartItem from './CartItem';
import { Close, LocalOffer } from '@mui/icons-material';
import { orange } from '@mui/material/colors';
import { Button, IconButton, TextField } from '@mui/material';
import PricingCard from './PricingCard';

/* eslint-disable @typescript-eslint/no-explicit-any */
const Cart = () => {
    
    const [couponCode, setCouponCode] = useState("");
    
    const handleCodeChange = (code: any) => {
        setCouponCode(code.target.value);
    };
    
    return (
        <div className='pt-10 px-5 sm:px-10 md:px-60 min-h-screen'>
            
            <div className='grid grid-cols-1 lg:grid-cols-3 gap-5'>
                
                <div className='cart-item-section lg:col-span-2 space-y-3'>
                    
                    {
                        [1, 1, 1, 1, 1].map(() => 
                            <CartItem />
                        )
                    }
                    
                </div>
                
                <div className='col-span-1 text-sm space-y-3'>
                    
                    <div className='border-2 border-gray-200 rounded-md px-5 py-3 space-y-5'>
                            
                        <div className='flex gap-3 text-sm items-center'>
                                
                            <div className='flex gap-3 text-sm items-center'>
                            
                                <LocalOffer sx={{color: orange[800], fontSize: "18px"}} />            
                                
                            </div>
                                
                            <span>
                                Apply Coupons
                            </span>
                                
                        </div>
                            
                        { true ?
                            <div className='flex justify-between items-center gap-3'>
                            
                                <TextField 
                                    onChange={handleCodeChange}
                                    id='outlined-basic' 
                                    placeholder='coupon code'
                                    size='small' 
                                    variant='outlined'
                                />
                                
                                <Button size='medium'>
                                    Apply
                                </Button>
                            
                            </div> : <div className='flex'>
                                
                                <div className='p-1 pl-5 pr-3 border rounded-md flex gap-2 items-center'>
                                    
                                    <span className=''><strong className='text-orange-600'>MC317Z7815</strong> Applied</span>
                                    
                                    <IconButton size='small'>
                                        <Close className='text-red-600' />
                                    </IconButton>
                                    
                                </div>
                                
                            </div>
                        }
                        
                    </div>
                    
                    <div className='border-2 border-gray-200 rounded-md'>
                        
                        <PricingCard />
                    
                    </div>
                    
                </div>
                
            </div>
            
        </div>
    )
}

export default Cart;