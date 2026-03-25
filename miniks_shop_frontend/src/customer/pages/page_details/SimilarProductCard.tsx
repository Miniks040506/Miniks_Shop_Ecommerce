import React from 'react';

const SimilarProductCard = () => {
    return (
        <div>
            <div className='group px-4 relative'>
                
                <div className='card'>
                    
                    <img className='card-media object-top'
                        src={"https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-md6wyctra5r086.webp"} alt="Product"
                    />
                    
                </div>
                
                <div className='details pt-3 space-y-1 group-hover-effect rounded-md'>
                    
                    <div className='name'>
                        
                        <h1>KAKIFIT</h1>
                        <p>Sky Jeans Jacket - AK83</p>
                    
                    </div>
                    
                    <div className='price flex items-center gap-3'>
                        
                        <span className='font-sans text-gray-800'>
                            500.000 ₫    
                        </span>
                        
                        <span className='thin-line-through text-gray-400'>
                            999.000 ₫
                        </span>   
                        
                        <span className='text-primary font-semibold'>
                            50%
                        </span> 
                        
                    </div>
                    
                </div>
                
            </div>
        </div>
    )
}

export default SimilarProductCard;