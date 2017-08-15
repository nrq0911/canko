function checkPost(){
	try{
		if (document.form.name.value==""){
			alert('請填寫姓名！');
			document.form.name.focus();
			return false;
		}
		var name = /^[\u4e00-\u9fa5]{2,6}$/;
		if (!name.test(document.form.name.value)){
			alert('姓名格式不正確，姓名必須為中文,請重新填寫！');
			document.form.name.focus();
			return false;
		}
    }
    catch(ex){
    }

    try{
		if (document.form.tel.value==""){
			alert('請填寫手機號碼！');
			document.form.tel.focus();
			return false;
		}
		var form = /^1[3,4,5,6,7,8]\d{9}$/;
		if (isNaN(document.form.tel.value)){
			alert('手機號碼格式不正確，請重新填寫！');
			document.form.tel.focus();
			return false;
		}
    }
    catch(ex){
    } 	
    try{
		if (document.form.province.value==""){
			alert('請選擇所在地區！');
			document.form.province.focus();
			return false;
		}
    }
    catch(ex){
    } 	
    try{
		if (document.form.address.value==""){
			alert('請填寫詳細地址！');
			document.form.address.focus();
			return false;
		}
    }
    catch(ex){
    }
	try{
		/*if (document.form.email.value==""){
			alert('請輸入E-MAIL號碼！');
			document.form.email.focus();
			return false;
		}*/
		var form = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		if (!form.test(document.form.email.value)){
			alert('E-mail號碼格式不正確，請重新填寫！');
			document.form.email.focus();
			return false;
		}
    }
    catch(ex){
    } 		

    return true;
}

try{	
new PCAS("province","city","area");
}
catch(ex){
}