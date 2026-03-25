import { Button, Divider, FormControl, FormControlLabel, FormLabel, Radio, RadioGroup } from '@mui/material';
import React, { useState } from 'react';
import { colors } from '../../../data/filter/Color';
import { useSearchParams } from 'react-router-dom';
import { discount } from '../../../data/filter/Discount';
import { price } from '../../../data/filter/Price';

/* eslint-disable @typescript-eslint/no-explicit-any */
const FilterSection = () => {
    
    const [expendColor, setExpendColor] = useState(false);
    const [searchParams, setSearchParams] = useSearchParams();
    
    const handlerExpendColor = () => {
        setExpendColor(!expendColor);
    };
    
    const updateFilterParams = (e: any) => {
        const { value, name } = e.target;

        if (value) {
            searchParams.set(name, value);
        } else {
            searchParams.delete(name);
        }

        setSearchParams(searchParams);  
    };
    
    const clearAllFilters = () => {
        console.log("clearAllFilters", searchParams);
        
        searchParams.forEach((value: any, key: any) => {
            searchParams.delete(key); 
        });
        
        setSearchParams(searchParams);
    }
    
    return (
        <div className='-z-50 space-y-5 bg-white'>
            
            <div className='flex items-center justify-between h-10 px-9 lg:border-r-2 border-gray-100'>
                
                <p className='text-lg font-semibold'>
                    Filters
                </p>
                
                <Button onClick={clearAllFilters} size='small' className='text-primary cursor-pointer font-semibold!' >
                    Clear all
                </Button>
                
            </div>
            
            <Divider />
            
            <div className='px-9 space-y-6'>
                
                <section className='mt-4'>
                    <FormControl>
                        <FormLabel 
                            sx={{
                                fontSize: "16px",
                                fontWeight: "bold",
                                color: "#d35400",
                                pb: "14px"
                            }}
                            id="color" 
                            className='text-2xl font-semibold'
                        >
                            Color
                        </FormLabel>
                        <RadioGroup
                            onChange={updateFilterParams}
                            aria-labelledby="color"
                            defaultValue=""
                            name="color"
                        >
                            {
                                colors.slice(0, expendColor ? colors.length : 5).map((item) => 
                                    <FormControlLabel 
                                        value={item.name} 
                                        control={<Radio size='small' />} 
                                        label={
                                            <div className='flex items-center gap-3'>
                                                
                                                <p>{item.name}</p>
                                                
                                                <p className={`h-5 w-5 rounded-full ${item.name === "White" ? "border border-gray-200" : ""}`} style={{backgroundColor: item.hex}}></p>
                                                
                                            </div>
                                        } 
                                    />
                                )
                            }
                        </RadioGroup>
                    </FormControl>   
                    
                    <div>
                        <button 
                            className='text-primary cursor-pointer hover:text-orange-900 flex items-center'
                            onClick={handlerExpendColor}
                        >
                            {expendColor ? "hide" : `+${colors.length - 5} colors more`}
                        </button>
                    </div>
                </section>
                
                <Divider />
                
                <section className='mt-4'>
                    <FormControl>
                        <FormLabel 
                            sx={{
                                fontSize: "16px",
                                fontWeight: "bold",
                                color: "#d35400",
                                pb: "14px"
                            }}
                            id="price" 
                            className='text-2xl font-semibold'
                        >
                            Price
                        </FormLabel>
                        <RadioGroup
                            aria-labelledby="price"
                            onChange={updateFilterParams}
                            defaultValue=""
                            name="price"
                        >
                            {
                                price.map((item) => 
                                    <FormControlLabel 
                                        key={item.name}
                                        value={item.value}
                                        control={<Radio size='small'/>}
                                        label={item.name}
                                    />
                                )
                            }
                        </RadioGroup>
                    </FormControl>   
                </section>
                
                <Divider />
            
                <section className='mt-4'>
                    <FormControl>
                        <FormLabel 
                            sx={{
                                fontSize: "16px",
                                fontWeight: "bold",
                                color: "#d35400",
                                pb: "14px"
                            }}
                            id="brand" 
                            className='text-2xl font-semibold'
                        >
                            Discount
                        </FormLabel>
                        <RadioGroup
                            aria-labelledby="brand"
                            onChange={updateFilterParams}
                            defaultValue=""
                            name="discount"
                        >
                            {
                                discount.map((item) => 
                                    <FormControlLabel 
                                        key={item.name}
                                        value={item.value}
                                        control={<Radio size='small'/>}
                                        label={item.name}
                                    />
                                )
                            }
                        </RadioGroup>
                    </FormControl>   
                </section>
            
            </div>
            
        </div>
    )
}

export default FilterSection;