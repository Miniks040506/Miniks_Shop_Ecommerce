import React from 'react';

const DealCard = () => {
    return (
        <div className='w-54 cursor-pointer rounded-sm overflow-hidden'>
            <img className='border-x-[7px] border-t-[7px] border-pink-700 w-full h-55 object-cover object-center'
                src="https://i.pinimg.com/736x/d9/ae/a0/d9aea084c8e9c4fabcfd92ee10163b11.jpg" 
                alt="Smart Watch" />
            
            <div className='border-4 border-black bg-black text-white p-2 text-center'>
                <p className='text-lg font-semibold'>Smart Watch</p>
                <p className='text-2xl font-bold'>25% OFF</p> 
                <p className='text-balance text-lg'>Shop Now</p>           
            </div>    
            
        </div>
    )
}

export default DealCard;