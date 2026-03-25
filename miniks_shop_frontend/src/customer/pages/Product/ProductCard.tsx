import React, { useEffect, useState } from 'react';
import "./ProductCard_style.css";
import { Button } from '@mui/material';
import { Favorite, ModeComment } from '@mui/icons-material';
import { orange} from '@mui/material/colors';

/* eslint-disable @typescript-eslint/no-explicit-any */
const images = [
    "https://down-vn.img.susercontent.com/file/vn-11134207-820l4-mennzv2wjj7r14.webp",
    "https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-md6wycvf7q1o2d.webp",
    "https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-md9osrgvzwz14a.webp",
    "https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-md6wycvp8pzwdc.webp",
    "https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-md6wycvp7ayn42.webp",
    "https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-md6wyctra5r086.webp"
]

const ProductCard = () => {
    
    const [currentImage, setCurrentImage] = useState(0);
    const [isHovered, setIsHovered] = useState(false);
    
    useEffect(() => {
        
        let interval: any;
        if (isHovered) {
            interval = setInterval(() => {
                setCurrentImage((prevImage) => (prevImage + 1) % images.length);
            }, 1000);
        } else if (interval) {
            clearInterval(interval);
            interval = null;
        }
        
        return () => clearInterval(interval);
        
    }, [isHovered]);
    
    return (
        <>
            <div className='group px-4 relative'>
                
                <div className='card'
                    onMouseEnter={() => setIsHovered(true)} 
                    onMouseLeave={() => setIsHovered(false)}
                >
                    
                    {
                        images.map((image, index) => 
                            <img className='card-media object-top'
                                src={image} alt="Product"
                                style={{transform:`translateX(${(index - currentImage) * 100}%)`}} 
                            />
                        )
                    }
                    
                    {
                        isHovered && (
                            <div className='indicator flex flex-col items-center space-x-2'>
                                <div className='flex gap-3'>
                                    <Button variant='contained' color='secondary'>
                                        <Favorite sx={{color: orange[800]}}/>
                                    </Button>
                                    <Button variant='contained' color='secondary'>
                                        <ModeComment sx={{color: orange[800]}}/>
                                    </Button>
                                </div>
                            </div>
                        )
                    }
                    
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
        </>
    )
}

export default ProductCard;