import React from 'react'
import './ShopByCategory_style.css';

const ShopByCategoryCard = () => {
  return (
    <div className='flex gap-3 flex-col justify-center items-center group cursor-pointer'>

        <div className='custom-border w-37.5 h-37.5 lg:w-62.25 lg:h-62.25 rounded-full bg-primary-green'>
            <img className='group-hover:scale-95 transition-transform transform-duration-700 object-cover object-center w-full h-full rounded-full' 
                src="https://down-vn.img.susercontent.com/file/vn-11134207-7qukw-lhsyi44kxvi96d_tn" 
                alt="Book Shelf" />
        </div>
        
        <h1>Home Decor</h1>

    </div>
  )
}

export default ShopByCategoryCard;