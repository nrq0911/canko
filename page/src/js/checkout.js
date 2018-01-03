var app;
var quotes= '';
var marquee = '';
var wfgdaa,wfgdbb;

$(document).ready(function(){

    app = new Vue({
        el: '#order',
        data: {
            result      : {},
            firstLevel  : 0,
            secondLevel : 0,
            firstLevelSpec  : '',
            secondLevelSpec : '',
            goodImg         : '',
            number          : 1,
            goodsId         : 0,
            money           : 0
        }
    });

    app.result = {
        goodsId : Cjs.url.getParamByName('goodsId'),
        spec    : Cjs.url.getParamByName('spec'),
        num     : Cjs.url.getParamByName('num'),
        money   : Cjs.url.getParamByName('money'),
        goodImg : Cjs.url.getParamByName('goodImg'),
        orderId : Cjs.url.getParamByName('orderId'),
        price   : Cjs.url.getParamByName('price'),
        name    : '',
        tel     : '',
        email   : '',
        address : '',
        postcode: '',
        remark  : ''
    };
    $("#order").show();

    quotes = new Array(Cjs.url.getParamByName('spec'));

    marquee = new Array(
        "<p><span>[最新購買]：</span>張**（095***6831）在1分鐘前訂購了"+quotes[Math.floor((Math.random()*quotes.length))]+" <font color='#FF0000'>√</font></p>",
        "<p><span>[最新購買]：</span>李**（093***1685）在3分鐘前訂購了"+quotes[Math.floor((Math.random()*quotes.length))]+" <font color='#FF0000'>√</font></p>",
        "<p><span>[最新購買]：</span>趙**（091***8603）在5分鐘前訂購了"+quotes[Math.floor((Math.random()*quotes.length))]+" <font color='#FF0000'>√</font></p>",
        "<p><span>[最新購買]：</span>劉**（093***3943）在2分鐘前訂購了"+quotes[Math.floor((Math.random()*quotes.length))]+" <font color='#FF0000'>√</font></p>",
        "<p><span>[最新購買]：</span>張**（098***5500）在4分鐘前訂購了"+quotes[Math.floor((Math.random()*quotes.length))]+" <font color='#FF0000'>√</font></p>",
        "<p><span>[最新購買]：</span>王**（092***0214）在6分鐘前訂購了"+quotes[Math.floor((Math.random()*quotes.length))]+" <font color='#FF0000'>√</font></p>"
    );
    wfgdaa = 0;
    wfgdbb = 1;
    window.setInterval(function () {
        marqueeL();
    }, 3000);

    $("title").html(app.result.spec);

    console.log(Cjs.url.getParamByName('spec'));
    // 赋值数量
    var count = Cjs.url.getParamByName('count');
    $("#mun").val(count);
    // 产品
    var combo_id = Cjs.url.getParamByName('comb_id');
    $("input[name=combo_id]").val(combo_id);
    // 获取属性
    var shuxing = Cjs.url.getParamByName('proto') || "";
    var shuxingArr = shuxing.split('|');
    shuxingArr.map(function(elem, index) {
        var obj = elem.split('-')
        var key = obj[0];
        var value = obj[1];
        $("input[name='attr["+key.replace('*','-')+"]']").val(value);
        var domgroup = $('[optionsGroup]').filter("[data-id='"+key+"']")
        domgroup.find('[data-id='+value+']').show();
        // 如果有套餐产品则显示下面
        $('#selectedProducts').find("[data-optionsGroup='"+key+"'][data-id='"+value+"']").show();
    });
}) ;

$('.combo').click(function () {
    refresh_price();
}) ;

function addnumber(){
    $('#mun').val(parseInt($('#mun').val())+1);
    refresh_price() ;
}
function minnumber(){
    if($('#mun').val()>1){
        $('#mun').val(parseInt($('#mun').val())-1);
        refresh_price() ;
    }
}

function marqueeL(){
    if(wfgdaa>(marquee.length-1))
        wfgdaa = 0;
    if(wfgdbb>(marquee.length-1))
        wfgdaa = 0;
    wfgdbb = wfgdaa +1;
    var marHTML = marquee[wfgdaa]+marquee[wfgdbb];
    document.getElementById("fahuo").innerHTML = marHTML;
    wfgdaa +=1;
    wfgdbb +=1;
}
    
function refresh_price() {
    var type = $("input[name='type']").val();
    app.result.num =  $('#mun').val();
}

var _region = $("#_region").val();
function postcheck(){

    try{
        if (document.form.name.value==""){
            alert('請填寫姓名！');
            document.form.name.focus();
            return false;
        }
        var name = /^[a-zA-Z\u4e00-\u9fa5\s]{2,}$/;
        if (!name.test(document.form.name.value)){
            alert('姓名格式不正確，請重新填寫！');
            document.form.name.focus();
            return false;
        }
    }
    catch(ex){
    }
    try{
        // if (document.form.email.value==""){
        //     alert('請填寫郵箱地址！');
        //     document.form.email.focus();
        //     return false;
        // }
        // if (!/^([a-zA-Z0-9_-]|.)+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(document.form.email.value)) {
        //     alert('郵箱格式不正確，請重新填寫！');
        //     document.form.email.focus();
        //     return false;
        // }
    }
    catch(ex){
    }
    try{
        if (document.form.mob.value==""){
            alert('請填寫手機號碼！');
            document.form.mob.focus();
            return false;
        }
        switch(_region){
            case "台灣":
                if (/^09/.test(document.form.mob.value) && !/^\d{10}$/.test(document.form.mob.value)) {
                    alert('手機號碼格式不正確，請重新填寫！');
                    document.form.mob.focus();
                    return false;
                }
                if (!/^0\d{6,10}/.test(document.form.mob.value)) {
                    alert('手機號碼格式不正確，請重新填寫！');
                    document.form.mob.focus();
                    return false;
                }
                break;
            case "香港":

                break;
        }
    }
    catch(ex){
    }
    try{
        if (document.form.province.value==""){
            alert('请选择所在地区！');
            document.form.province.focus();
            return false;
        }
    }
    catch(ex){
    }

    try{
        if (document.form.address.value==""){
            alert('请填写详细地址！');
            document.form.address.focus();
            return false;
        }
    }catch(ex){
    }
    app.result.money = app.result.num*app.result.price;
    $.ajax({
        url: config.api_url,
        type: 'post',
        data: app.result,
        dataType: 'json',
        success: function (data) {
            if(data.code!=200){
                alert(data.msg);
                return false;
            }
            data.info.goodImg = app.result.goodImg;
            var get = new Array();
            for (key in data.info){
                get.push(key+"="+encodeURI(data.info[key]));
            }

            window.location.href = "./pay_success.htm?"+get.join("&");
        }
    });
    return false;
}