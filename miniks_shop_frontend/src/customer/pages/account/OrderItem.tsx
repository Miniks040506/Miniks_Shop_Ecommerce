import { ElectricBolt } from '@mui/icons-material';
import { Avatar } from '@mui/material';
import { orange } from '@mui/material/colors';
import React from 'react';

const OrderItem = () => {
    return (
        <div className='text-sm bg-white p-5 space-y-4 border-2 border-gray-200 rounded-md cursor-pointer'>
            
            <div className='flex items-center gap-5'>
                
                <div>
                    
                    <Avatar sizes='small' sx={{bgcolor: orange[800]}}>
                        <ElectricBolt />
                    </Avatar>
                    
                </div>
                
                <div>
                    
                    <h1 className='font-bold text-primary'>PENDING</h1>
                    
                    <p>Arrived By Mon, 12 Mar</p>
                    
                </div>
                
            </div>
            
            <div className='p-5 bg-orange-50 flex gap-3'>
                
                <div>
                    <img className='w-25'    
                        src="https://www.dangquangwatch.vn/upload/product/dong-ho-co-thuy-sy11-624006410.jpg"
                        alt="Product Image" />
                </div>
                
                <div className='w-full space-y-2'>
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
                
            </div>
            
        </div>
    )
}

export default OrderItem;