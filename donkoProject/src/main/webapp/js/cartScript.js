
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

		const eClosest = decrementBtn.closest(".quantityForm");

		if (parseInt(enext.value, 10) > parseInt(maxQuantity, 10)) {
			enext.value = maxQuantity;
			eClosest.submit();
		}else if(parseInt(enext.value, 10) < parseInt(minQuantity, 10)){
			enext.value = 1;
			eClosest.submit();
		}else if(parseInt(enext.value, 10) > parseInt(minQuantity, 10)){
			enext.value--;
			eClosest.submit();
		}
		
	});
});

quantityIncrementBtns.forEach(incrementBtn => {

	incrementBtn.addEventListener("click", () => {

		const eprev = incrementBtn.previousElementSibling;
		const maxQuantity = eprev.getAttribute("max");
		const minQuantity = eprev.getAttribute("min");	
		
		const eClosest = incrementBtn.closest(".quantityForm");
		console.log(eClosest);

		if (parseInt(eprev.value, 10) < parseInt(minQuantity, 10)) {
			eprev.value = 1;
			eClosest.submit();
		}else if(parseInt(eprev.value, 10) < parseInt(maxQuantity, 10)){
			eprev.value++;
			eClosest.submit();
		}else if(parseInt(eprev.value, 10) > parseInt(maxQuantity, 10)){
			eprev.value = maxQuantity;
			eClosest.submit();
		}
	});
});


const quantityInputArray = document.querySelectorAll(".quantity");

quantityInputArray.forEach(quantityInput => {
	
	quantityInput.addEventListener("focus", () => {
		const incrementBtn = quantityInput.nextElementSibling
		const updateBtn = incrementBtn.nextElementSibling;
		updateBtn.style.display = "";
	})
	
})

const quantityUpdateBtnArray = document.querySelectorAll(".quantityUpdateBtn");

quantityUpdateBtnArray.forEach(btn => {
	btn.addEventListener("submit", ()=>{
		btn.style.display = "none";
	})
})











