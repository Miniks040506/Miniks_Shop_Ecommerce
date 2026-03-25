import { Delete } from '@mui/icons-material';
import { Avatar, Box, Grid, IconButton, Rating } from '@mui/material';
import { red } from '@mui/material/colors';
import React from 'react';

const ReviewCard = () => {
    return (
        <div className='flex justify-between relative'>
            
            <Grid container spacing={9}>
                
                <Grid size={{xs: 1}}>
                    
                    <Box>
                        
                        <Avatar className='text-white' sx={{width: 56, height: 56, bgcolor: "#9155fd"}}>
                            M
                        </Avatar>
                        
                    </Box>
                    
                </Grid>
                
                <Grid size={{xs: 9}}>
                    
                    <div className='space-y-2'> 
                        
                        <div>
                            
                            <p className='font-semibold text-lg'>Miniks</p>
                        
                            <p className='opacity-70'>2026-01-23T20:17:13.463138</p>
                        
                        </div>
                        
                    </div>
                    
                    <Rating 
                        readOnly
                        value={4.5}
                        precision={0.5}    
                    />
                    
                    <p>Value for money product, great!</p>
                    
                    <div>
                        <img 
                            className='w-24 h-24 object-cover'
                            src="https://www.montecarlo.in/cdn/shop/files/224261780-2-38_5.jpg?v=1758281090&width=800" 
                            alt="Product" 
                        />
                    </div>
                    
                </Grid>
                
            </Grid>
            
            <div>
                
                <IconButton>
                    <Delete sx={{color: red[700]}} />
                </IconButton>
            
            </div>
            
        </div>
    )
}

export default ReviewCard;