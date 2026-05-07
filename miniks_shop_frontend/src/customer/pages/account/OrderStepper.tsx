import { Box } from '@mui/material';
import React, { useEffect, useState } from 'react';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import FiberManualRecordIcon from '@mui/icons-material/FiberManualRecord';


/* eslint-disable @typescript-eslint/no-explicit-any */

const steps = [
    { name: 'Order Placed', description: 'on Thu, 11 Jul', value: 'PLACED' },
    { name: 'Packed', description: 'Item Packed in Dispatch Warehouse', value: 'CONFIRMED' },
    { name: 'Shipped', description: 'on Mon, 15 Jul', value: 'SHIPPED' },
    { name: 'Arriving', description: 'by 16 Jul - 19 Jul', value: 'ARRIVING' },
    { name: 'Arrived', description: 'by 17 Jul - 19 Jul', value: 'DELIVERED' },
];

const canceledSteps = [
    { name: 'Order Placed', description: 'On Thu, 11 Jul', value: 'PLACED' },
    { name: 'Order Canceled', description: 'On Thu, 11 Jul', value: 'CANCELED' }
];

const currentStep = 2;

const OrderStepper = ({ orderStatus }: any) => {
    
    const [statusStep, setStatusStep] = useState(steps);
    
    useEffect(() => {
        if (orderStatus === 'CANCELED') {
            setStatusStep(canceledSteps);
        } else {
            setStatusStep(steps);
        }
    }, [orderStatus]);

    return (
        <Box className="my-10">
            
            {
                statusStep.map((step, index) => (
                    <>
                        <div key={index} className='flex px-4'>
                            
                            <div className='flex flex-col items-center'>
                                
                                <Box 
                                    sx={{ zIndex: -1 }}
                                    className={`w-8 h-8 rounded-full flex items-center justify-center z-10 
                                        ${index <= currentStep ? 
                                        "bg-gray-200 text-orange-600" 
                                        : "bg-gray-300 text-gray-600"}`}    
                                >
                                    {step.value === orderStatus ? (
                                        <CheckCircleIcon />
                                    ) : (
                                        <FiberManualRecordIcon sx={{ zIndex: -1 }} />
                                    )}
                                </Box>
                                
                                {
                                    statusStep.length - 1 && (
                                        <div
                                            className={`border-0 border-gray-200 h-20 w-[2px] 
                                                ${index < currentStep 
                                                    ? "bg-orange-600" 
                                                    : "bg-gray-300 text-gray-600"}
                                                `}
                                        >
                                            
                                        </div>  
                                    )
                                }
                            </div>
                            
                            <div className={` ml-2 w-full `}>
                                
                            </div>
                            
                        </div>
                    </>
                ))
            }
            
        </Box>
    )
}

export default OrderStepper;