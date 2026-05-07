import { Box, Button, Grid, TextField } from '@mui/material';
import { useFormik } from "formik";
import React from 'react';
import * as Yup from 'yup';

const addressFormSchema = Yup.object().shape({
    name: Yup.string().required("Name is required"),
    mobile: Yup.string().required("Mobile number is required").matches(/^[0]\d{9}$/, "Invalid mobile number"),
    postalCode: Yup.string().required("Pin code is required").matches(/^[0-9]{5}$/, "Invalid pin code"),
    address: Yup.string().required("Address is required"),
    province: Yup.string().required("City is required"),
    district: Yup.string().required("State is required"),
    locality: Yup.string().required("Locality is required"), 
});

const AddressForm = () => {
    
    const formik = useFormik({
        initialValues: {
            name: '',
            mobile: '',
            postalCode: '',
            address: '',
            province: '',
            district: '',
            locality: ''  
        },
        validationSchema: addressFormSchema,
        onSubmit: (values) => {
            //Submit form
            console.log(values);
        }
    });
    
    return (
        <Box sx={{max: "auto"}}>
            
            <p className='text-xl font-bold text-center pb-5'>Contact Details</p>
            
            <form onSubmit={formik.handleSubmit}>
                
                <Grid container spacing={4}>
                    
                    <Grid size={{xs: 12}}>
                        
                        <TextField 
                            fullWidth
                            name='txtName'
                            label='Name'
                            value={formik.values.name}
                            onChange={formik.handleChange}
                            error={formik.touched.name && Boolean(formik.errors.name)}
                            helperText={formik.touched.name && formik.errors.name}
                        />
                        
                    </Grid>
                    
                    <Grid size={{xs: 6}}>
                        
                        <TextField 
                            fullWidth
                            name='txtMobile'
                            label='Mobile'
                            value={formik.values.mobile}
                            onChange={formik.handleChange}
                            error={formik.touched.mobile && Boolean(formik.errors.mobile)}
                            helperText={formik.touched.mobile && formik.errors.mobile}
                        />
                        
                    </Grid>
                    
                    <Grid size={{xs: 6}}>
                        
                        <TextField 
                            fullWidth
                            name='txtPostalCode'
                            label='Postal Code'
                            value={formik.values.postalCode}
                            onChange={formik.handleChange}
                            error={formik.touched.postalCode && Boolean(formik.errors.postalCode)}
                            helperText={formik.touched.postalCode && formik.errors.postalCode}
                        />
                        
                    </Grid>
                    
                    <Grid size={{xs: 12}}>
                        
                        <TextField 
                            fullWidth
                            name='txtAddress'
                            label='Address (House No, Building, Street)'
                            value={formik.values.address}
                            onChange={formik.handleChange}
                            error={formik.touched.address && Boolean(formik.errors.address)}
                            helperText={formik.touched.address && formik.errors.address}
                        />
                        
                    </Grid>
                    
                    <Grid size={{xs: 12}}>
                        
                        <TextField 
                            fullWidth
                            name='txtLocality'
                            label='Locality/Town'
                            value={formik.values.locality}
                            onChange={formik.handleChange}
                            error={formik.touched.locality && Boolean(formik.errors.locality)}
                            helperText={formik.touched.locality && formik.errors.locality}
                        />
                        
                    </Grid>
                    
                    <Grid size={{xs: 6}}>
                        
                        <TextField 
                            fullWidth
                            name='txtDistrict'
                            label='District'
                            value={formik.values.district}
                            onChange={formik.handleChange}
                            error={formik.touched.district && Boolean(formik.errors.district)}
                            helperText={formik.touched.district && formik.errors.district}
                        />
                        
                    </Grid>
                    
                    <Grid size={{xs: 6}}>
                        
                        <TextField 
                            fullWidth
                            name='txtProvince'
                            label='Province/City'
                            value={formik.values.province}
                            onChange={formik.handleChange}
                            error={formik.touched.province && Boolean(formik.errors.province)}
                            helperText={formik.touched.province && formik.errors.province}
                        />
                        
                    </Grid>
                    
                    <Grid size={{xs: 12}}>
                        
                        <Button 
                            type='submit'
                            fullWidth 
                            variant='contained' 
                            sx={{py: "14px"}}
                        >
                            Add Address
                        </Button>
                        
                    </Grid>
                    
                </Grid>
                
            </form>
            
        </Box>
    )
}

export default AddressForm;