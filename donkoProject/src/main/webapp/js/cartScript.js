
const itemImages = document.querySelectorAll(".itemImage");

itemImages.forEach(itemImage => {

	itemImage.addEventListener("mouseover", () => {
		itemImage.classList.toggle("itemImageHover");
	})

	itemImage.addEventListener("mouseout", () => {
		itemImage.classList.toggle("itemImageHover");
	})
})

const quantityIncrementBtns = document.querySelectorAll(".quantityIncrementBtn");
const quantityDecrementBtns = document.querySelectorAll(".quantityDecrementBtn");

quantityDecrementBtns.forEach(decrementBtn => {

	decrementBtn.addEventListener("click", () => {

		const enext = decrementBtn.nextElementSibling;
		const maxQuantity = enext.getAttribute("max");
		const minQuantity = enext.getAttribute("min");

		const quantityInput = decrementBtn.nextElementSibling;
		const incrementBtn = quantityInput.nextElementSibling;
		const updateBtn = incrementBtn.nextElementSibling;
		updateBtn.style.visibility = "";

		if (parseInt(enext.value, 10) > parseInt(maxQuantity, 10)) {
			enext.value = maxQuantity;
		}else if(parseInt(enext.value, 10) < parseInt(minQuantity, 10)){
			enext.value = 1;
		}else if(parseInt(enext.value, 10) > parseInt(minQuantity, 10)){
			enext.value--;
		}
		
	});
});

quantityIncrementBtns.forEach(incrementBtn => {

	incrementBtn.addEventListener("click", () => {

		const eprev = incrementBtn.previousElementSibling;
		const maxQuantity = eprev.getAttribute("max");
		const minQuantity = eprev.getAttribute("min");	
		
		const updateBtn = incrementBtn.nextElementSibling;
		updateBtn.style.visibility = "";

		if (parseInt(eprev.value, 10) < parseInt(minQuantity, 10)) {
			eprev.value = 1;
		}else if(parseInt(eprev.value, 10) < parseInt(maxQuantity, 10)){
			eprev.value++;
		}else if(parseInt(eprev.value, 10) > parseInt(maxQuantity, 10)){
			eprev.value = maxQuantity;
		}
	});
});


const quantityInputArray = document.querySelectorAll(".quantity");

quantityInputArray.forEach(quantityInput => {
	
	quantityInput.addEventListener("focus", () => {
		const incrementBtn = quantityInput.nextElementSibling
		const updateBtn = incrementBtn.nextElementSibling;
		updateBtn.style.visibility = "";
	})
	
})

const quantityUpdateBtnArray = document.querySelectorAll(".quantityUpdateBtn");

quantityUpdateBtnArray.forEach(btn => {
	btn.addEventListener("submit", ()=>{
		btn.style.visibility = "hidden";
	})
})











