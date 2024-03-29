
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


const quantityIncrementBtn = document.querySelector("#quantityIncrementBtn");
const quantityDecrementBtn = document.querySelector("#quantityDecrementBtn");
const quantity = document.querySelector("#quantity");
const maxQuantity = quantity.getAttribute("max");
const minQuantity = quantity.getAttribute("min");

quantityIncrementBtn.addEventListener("click", () => {
	
	if (parseInt(quantity.value , 10) < parseInt(minQuantity, 10)) {
		quantity.value = 1;
	}else if(parseInt(quantity.value, 10) < parseInt(maxQuantity, 10)){
		quantity.value++;
	}else if(parseInt(quantity.value, 10) > parseInt(maxQuantity, 10)){
		quantity.value = maxQuantity;
	}
	
})

quantityDecrementBtn.addEventListener("click", () => {

	if (parseInt(quantity.value , 10) > parseInt(maxQuantity, 10)) {
		quantity.value = maxQuantity;
	}else if(parseInt(quantity.value, 10) < parseInt(minQuantity, 10)){
		quantity.value = 1;
	}else if(parseInt(quantity.value, 10) > parseInt(minQuantity, 10)){
		quantity.value--;
	}
	
})


const itemOptionName = document.querySelector("#itemOptionName");
const options = document.querySelectorAll(".option");


options.forEach(option => {
	
	if(itemOptionName.innerText.includes(option.innerText)){
		option.style.backgroundColor = "#e5ccff";
	};
});

