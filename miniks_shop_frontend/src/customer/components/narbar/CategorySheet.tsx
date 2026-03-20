import React from 'react';
import { menLevelTwo } from '../../../data/category/level two/MenLevelTwo';
import { womenLevelTwo } from '../../../data/category/level two/WomenLevelTwo';
import { furnitureLevelTwo } from '../../../data/category/level two/FurnitureLevelTwo';
import { electronicsLevelTwo } from '../../../data/category/level two/ElectronicsLevelTwo';
import { menLevelThree } from '../../../data/category/level three/menLevelThree';
import { womenLevelThree } from '../../../data/category/level three/WomenLevelThree';
import { furnitureLevelThree } from '../../../data/category/level three/FurnitureLevelThree';
import { electronicsLevelThree } from '../../../data/category/level three/ElectronicsLevelThree';
import { Box } from '@mui/material';


/* eslint-disable @typescript-eslint/no-explicit-any */

const categoryLevelTwo: { [key: string]: any[] } = {
    men: menLevelTwo,
    women: womenLevelTwo,
    home_furniture: furnitureLevelTwo,
    electronics: electronicsLevelTwo,
}

const categoryLevelThree: { [key: string]: any[] } = {
    men: menLevelThree,
    women: womenLevelThree,
    home_furniture: furnitureLevelThree,
    electronics: electronicsLevelThree,
}

const CategorySheet = ({selectedCategory, setShowSheet}: any) => {
    
    const childCategory = (category: any, parentCategoryId: any) => {
        return category.filter((child:any) => child.parentCategoryId == parentCategoryId);
    }
    
    return (
        <Box sx={{zIndex:1}} className="bg-white shadow-lg lg:h-125 overflow-y-auto">
            
            <div className='flex text-sm flex-wrap'>
                
                {
                    categoryLevelTwo[selectedCategory]?.map((item: any, index: number) => 
                        <div className={`p-8 lg:w-[20%] ${index % 2 === 0 ? "bg-slate-50" : "bg-white"}`}>
                            <p className='text-primary mb-5 font-semibold'>{item.name}</p>
                            
                            <ul className='space-y-3'>      
                                {
                                    childCategory(categoryLevelThree[selectedCategory], item.categoryId).map(
                                        (item: any) => <div>
                                            <li className='hover:text-primary cursor-pointer'>
                                                {item.name}
                                            </li>
                                        </div>
                                    )
                                }
                            </ul>
                            
                        </div>
                    )
                }
                
            </div>
        </Box>
    )
}

export default CategorySheet;