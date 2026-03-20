import React from 'react';

const CategoryGrid = () => {
  return (
    <div className='grid gap-4 grid-rows-12 grid-cols-12 lg:h-150 px-5 lg:px-20'>
        
        <div className='col-span-3 row-span-12 text-white'>
            <img className='w-full h-full object-cover object-center rounded-md'
                src="https://photo.znews.vn/w660/Uploaded/ryksdreyxq/2021_02_26/3.jpeg" 
                alt="Female clothes" />
        </div>
        
        <div className='col-span-2 row-span-6 text-white'>
            <img className='w-full h-full object-cover object-center rounded-md'
                src="https://file.hstatic.net/200000410665/file/giay-oxford-chinh-la-kieu-giay-dress-shoes-co-dien_f7eacb42b8a94d3ca45702445923f029.jpg" 
                alt="Dress Shoes" />
        </div>
        
        <div className='col-span-4 row-span-6 text-white'>
            <img className='w-full h-full object-cover object-center rounded-md'
                src="https://daphongthuyvn.com/files/sanpham/1660/1/jpg/nhan-vang-trang-nu-da-quy-sapphire-xanh-hero-thien-nhien-cao-cap-nvdq1002.jpg" 
                alt="Jewelry" />
        </div>
        
        <div className='col-span-3 row-span-12 text-white'>
            <img className='w-full h-full object-cover object-center rounded-md'
                src="https://i.pinimg.com/1200x/c7/01/6e/c7016e544ac85e826f3b5b2eb4858ab9.jpg" 
                alt="Man shirt" />
        </div>
        
        <div className='col-span-4 row-span-6 text-white'>
            <img className='w-full h-full object-cover object-center rounded-md'
                src="https://www.dangquangwatch.vn/upload/product/dong-ho-co-thuy-sy11-624006410.jpg" 
                alt="Watch" />
        </div>
        
        <div className='col-span-2 row-span-6 text-white'>
            <img className='w-full h-full object-cover object-center rounded-md'
                src="https://down-vn.img.susercontent.com/file/28b6e83e04e4e831985aa4ce9e7ce459@resize_w900_nl.webp" 
                alt="Women's Shoes" />
        </div>
        
    </div>
  )
}

export default CategoryGrid;