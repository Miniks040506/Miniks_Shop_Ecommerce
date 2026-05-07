import { Divider } from '@mui/material';
import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import Orders from './Orders';
import OrderDetails from './OrderDetails';

/* eslint-disable @typescript-eslint/no-explicit-any */

const menu = [
    {name: "Orders", path: "/account/orders"},
    {name: "Profile", path: "/account/profile"},
    {name: "Saved Cards", path: "/account/saved-cards"},
    {name: "Addresses", path: "/account/addressed"},
    {name: "Logout", path: "/"}
];

const AccountProfile = () => {
    
    const navigate = useNavigate();
    const location =useLocation();
    
    const handleClick = (item: any) => navigate(item.path);
    
    return (
        <div className='px-5 lg:px-52 min-h-screen mt-10'>
            
            <div>
                
                <h1 className='text-xl font-bold pb-5'>Miniks</h1>
                
            </div>
            
            <Divider />
            
            <div className='grid grid-cols-1 lg:grid-cols-3 lg:min-h-[78vh]'>
                
                <section className='col-span-1 lg:border-r-2 border-gray-200 lg:pr-5 py-5 h-full '>
                    
                    {
                        menu.map((item) => (
                            <div 
                                onClick={() => handleClick(item)}
                                key={item.name} 
                                className={`${item.path === location.pathname ? "bg-primary text-white" : ""}
                                    py-3 cursor-pointer hover:text-white hover:bg-primary px-5 rounded-md border-b-2 border-gray-200`}
                            >
                                {/* <a href={item.path}>{item.name}</a> */}
                                <p>{item.name}</p>
                            </div>
                        ))
                    }
                    
                </section>
                
                <section className='right lg:col-span-2 lg:pl-5 py-5'>
                    
                    {/* <Orders /> */}
                    <OrderDetails />
                    
                </section>
                
            </div>
            
        </div>
    )
}

export default AccountProfile;