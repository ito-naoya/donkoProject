function chebg(chkID){
    optionId = document.getElementById(chkID);
    if(optionId.checked == true){
        optionId.parentNode.style.color = '#9933FF';
    }else{
        optionId.parentNode.style.color = '#000000';
    }
}