
const itemImages = document.querySelectorAll(".itemImage");

itemImages.forEach(itemImage => {
	
	itemImage.addEventListener("mouseover", () => {
		itemImage.classList.toggle("itemImageHover");
	})
	
	itemImage.addEventListener("mouseout", () => {
		itemImage.classList.toggle("itemImageHover");
	})
})