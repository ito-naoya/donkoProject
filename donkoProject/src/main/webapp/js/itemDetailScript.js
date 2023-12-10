
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
 
 
 
