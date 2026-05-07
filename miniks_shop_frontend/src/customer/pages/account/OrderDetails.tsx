import { Box, Button } from '@mui/material';
import React from 'react';
import { useNavigate } from 'react-router';
import OrderStepper from './OrderStepper';

const OrderDetails = () => {
    
    const navigate = useNavigate();
    
    return (
        <Box className="space-y-5">
            
            <section className='flex flex-col gap-5 justify-center items-center'>
                
                <img className='w-30'
                    src="https://www.dangquangwatch.vn/upload/product/dong-ho-co-thuy-sy11-624006410.jpg" 
                    alt="Product Image" />
                    
                <div className='text-sm space-y-1 text-center'>
                    <h1 className='font-bold'>Swiss Mechanical Watches</h1>
                    <p>
                        Premium Automatic Movement | Sapphire Crystal | 5ATM Water Resistant | 
                        Genuine Leather Strap | Exhibition Caseback (Silver Edition, 42mm)
                    </p>
                    <p>
                        <strong>Size: </strong>
                        42mm
                    </p>
                </div>
                
                <div>
                    <Button onClick={() => navigate(`/reviews/${5}/create`)}>Write Review</Button>
                </div>
                
            </section>
            
            <section className='border-2 border-gray-200 p-5'>
                <OrderStepper orderStatus={"PENDING"} />
            </section>
            
            <div className='border-2 border-gray-200 p-5'>
                
            </div>
            
        </Box>
    )
}

export default OrderDetails;