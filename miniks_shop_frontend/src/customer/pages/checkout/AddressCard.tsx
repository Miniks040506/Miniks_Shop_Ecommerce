import React from 'react';
import { Radio } from '@mui/material';

/* eslint-disable @typescript-eslint/no-explicit-any */
const AddressCard = () => {
    
    const handleChange = (event: any) => {
        console.log(event.target.checked)  
    };
    
    return (
        <div className='p-5 border-2 border-gray-200 rounded-md flex'>
            
            <div>
                
                <Radio 
                    checked={true}
                    onChange={handleChange}
                    value=''
                    name='radio-button'
                />
                
            </div>
            
            <div className='space-y-3 pt-3'>
                
                <h1>Miniks</h1>
                
                <p className='w-80'>
                    55 Le Loi, Ngai Giao, Ho Chi Minh - 78000
                </p>
                
                <p>
                    <strong>Mobile: </strong>0983717453
                </p>
                
            </div>
            
        </div>
    )
}

export default AddressCard;