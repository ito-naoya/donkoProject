function chebg(chkID){
    optionId = document.getElementById(chkID);
    if(optionId.checked == true){
        optionId.parentNode.style.color = '#9933FF';
    }else{
        optionId.parentNode.style.color = '#000000';
    }
}

function nullCheck(){
  var test = document.getElementById("test").value;
  if(test.length < 6){
    var validate = "6文字以上入力してください。";
    document.getElementById("validate_msg").innerHTML = validate; 
    return false; 
  }
}