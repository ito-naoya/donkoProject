let switchBtn = document.getElementById('change');
let passwordText = document.getElementById('exampleInputPassword');

let changeElement = (el)=> {
  if(el.style.display==''){
    el.style.display='none';
  }else{
    el.style.display='';
  }
}

switchBtn.addEventListener('click', ()=> {
  changeElement(passwordText);  
}, false);