
 const mainImage = document.querySelector("#mainImage");
 const mainSrc = mainImage.getAttribute("src");
 const subImages = document.querySelectorAll(".subImage");

subImages.forEach(img => {
	
	img.addEventListener("mouseover", () => {
		const subSrc = img.getAttribute("src");
		mainImage.setAttribute("src", subSrc);
		img.classList.toggle("subImageOpacity");
	})
	
	img.addEventListener("mouseout", () => {
		mainImage.setAttribute("src", mainSrc);
		img.classList.toggle("subImageOpacity");
	})
	
})

const sizes = document.querySelectorAll(".size");

sizes.forEach(size => {
	
	size.addEventListener("mouseover", () => {
		size.classList.toggle("sizeHover");
	})
	
	size.addEventListener("mouseout", () => {
		size.classList.toggle("sizeHover");
	})
	
})

 
 
 
