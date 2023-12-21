const switchBtn = document.getElementById('change');
const passwordText = document.getElementById('exampleInputPassword');

const changeElement = (el)=> {
  if(el.style.display==''){
    el.style.display='none';
  }else{
    el.style.display='';
  }
}

switchBtn.addEventListener('click', ()=> {
  changeElement(passwordText);  
}, false);
