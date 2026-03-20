import { Avatar, Box, Button, IconButton, useMediaQuery, useTheme } from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import SearchIcon from "@mui/icons-material/Search";
// import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import { AddShoppingCart, FavoriteBorder, Storefront } from "@mui/icons-material";
import CategorySheet from "./CategorySheet";
import { mainCategory } from "../../../data/category/MainCategory";
import { useState } from "react";

const Narbar = () => {
	
	const theme = useTheme();
	const isLarge = useMediaQuery(theme.breakpoints.up("lg"));
	const [selectedCategory, setSelectedCategory] = useState("men");
	const [showCategorySheet, setShowCategorySheet] = useState(false);
	
  	return (
		<>
			<Box sx={{zIndex: 2}} className="sticky top-0 left-0 right-0 bg-white">
				<div className="flex items-center justify-between px-5 lg:px-20 h-17.5 border-b-2 border-gray-100">
					
					<div className="flex items-center gap-9">
						<div className="flex items-center gap-2">
							{
								!isLarge &&
									<IconButton>
										<MenuIcon />
									</IconButton>
							}

							<h1 className="logo cursor-pointer text-lg md:text-2xl text-primary">
								Miniks Shop
							</h1>
						
						</div>
						
						<ul className="flex items-center font-medium text-gray-800">
							{
								mainCategory.map((item) => 
									<li className="mainCategory hover:text-primary hover:border-b-[3px] h-17.5 px-4 border-primary flex items-center" 
									onMouseEnter={() => {
										setSelectedCategory(item.categoryId);
										setShowCategorySheet(true);
									}}
									onMouseLeave={() => setShowCategorySheet(false)}>
										{item.name}
									</li>
								)
							}
						</ul>
						
					</div>

					<div className="flex gap-1 lg:gap-6 items-center">
						<IconButton>
							<SearchIcon />
						</IconButton>

						{
							false ? 
								<Button className="flex items-center gap-2">
									<Avatar 
									sx={{ width: 30, height: 30 }}
									src="https://i.pinimg.com/736x/aa/0a/d6/aa0ad613962eaa185dc1f90ee8b2fedd.jpg" />

									<h1 className="font-semibold hidden lg:block">
										Miniks
									</h1>
								</Button> : <Button variant="contained">Login</Button>
						}
						
						<IconButton>
							<FavoriteBorder sx={{fontSize:30}} />	
						</IconButton>
						
						<IconButton>
							<AddShoppingCart className="text-gray-700" sx={{fontSize:30}} />
						</IconButton>
						
						{
							isLarge &&
								<Button startIcon={<Storefront />} variant="outlined">
									Become Seller
								</Button>	
						}
						
					</div>
				
					{showCategorySheet && (
						<div className="categorySheet absolute top-[4.41rem] left-20 right-20 border-2 border-gray-100" 
							onMouseLeave={() => setShowCategorySheet(false)} 
							onMouseEnter={() => setShowCategorySheet(true)}>
							<CategorySheet selectedCategory={selectedCategory} />
						</div>
					)}
				
				</div>
			</Box>
		</>
  	);
};

export default Narbar;
