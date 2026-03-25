import { Add, Close, Remove } from '@mui/icons-material';
import { Button, Divider, IconButton } from '@mui/material';
import React from 'react';

const CartItem = () => {
    
    const handleUpdateQuantity= () => {
        
    }
    
    return (
        <div className='border-2 border-gray-200 rounded-md relative'>
            
            <div className='p-5 flex gap-3'>
                
                <div>
                    
                    <img 
                        className='w-27 rounded-md'
                        src="https://www.montecarlo.in/cdn/shop/files/224261780-2-38_1.jpg?v=1739291547&width=800" 
                        alt="Product" 
                    />
                    
                </div>
                
                <div className='space-y-2'>
                    
                    <h1 className='font-semibold text-lg'>MONTE CARLO</h1>
                    
                    <p className='text-gray-600 font-medium text-sm'>Men Olive Solid Mock Neck Full Sleeve Jacket</p>
                    
                    <p className='text-gray-400 text-xs'><strong>Sold by: </strong>Natural Lifestyle Products Private Limited</p>
                    
                    <p className='text-sm'><strong>7 days replacement</strong> available</p>
                    
                    <p className='text-sm text-gray-500'><strong>quantity: </strong>5</p>
                    
                </div>
                
            </div>
            
            <Divider />
            
            <div className='flex justify-between items-center'>
                
                <div className='px-5 py-2 flex justify-between items-center'>
                        
                    <div className='flex items-center gap-2 w-35 justify-between'>
                        
                        <Button disabled={true} onClick={handleUpdateQuantity}>
                            <Remove />
                        </Button>
                            
                        <span>
                            {5}
                        </span>
                            
                        <Button onClick={handleUpdateQuantity}>
                            <Add />
                        </Button>
                            
                    </div>
                        
                </div>
                
                <div className='pr-5'>
                    <p className='text-gray-700 font-medium'>900.000 ₫</p>
                </div>
            
            </div>
            
            <div className='absolute top-1 right-1'>
                
                <IconButton>
                    <Close color='primary' />
                </IconButton>
                
            </div>
            
        </div>
    )
}

export default CartItem;