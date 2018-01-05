$(document).ready(function(){
    var spec = "";
    $("#spec").val("");
    $("#spec2").val("");
    $("#spec3").val("");
    $("#first-li a").bind("click",function(){
        var o = $(this);
        $("#first-li a").removeClass("sku_cur");
        o.addClass("sku_cur");
        spec = $("#first-level").val() + " " + o.html();
        $("#spec").val(spec);
    });

    $("#second-li a").bind("click",function(){
        var o = $(this);
        $("#second-li a").removeClass("sku_cur");
        o.addClass("sku_cur");
        spec = $("#second-level").val() + " " + o.html();

        $("#spec2").val(spec);
    });

    $("#third-li a").bind("click",function(){
        var o = $(this);
        $("#third-li a").removeClass("sku_cur");
        o.addClass("sku_cur");
        spec = $("#third-level").val() + " " + o.html();

        $("#spec3").val(spec);
    });
});
function checkSearch(){
    var search = $("#order_search").val();
    if('' == search){
        alert("請輸入訂單號或者訂單姓名或手機號！");
        return false;
    }
    return true;
}
function checkBuy(){
    var num = $("#item_num").val();
    if('' == num){
        alert("請輸入購買商品數量！");
        return false;
    }

    var spec = $("#spec").val();
    if('' == spec){
        alert("請選擇規格！！");
        return false;
    }

    var secondLevel = $("#second-level").val();
    if(typeof(secondLevel) != "undefined" && '' != secondLevel){
        var spec2 = $("#spec2").val();
        if('' == spec2){
            alert("請選擇規格！！");
            return false;
        }
        spec += "," + spec2;
    }
    var thirdLevel = $("#third-level").val();
    if(typeof(thirdLevel) != "undefined" && '' != thirdLevel){
        var spec3 = $("#spec3").val();
        if('' == spec3){
            alert("請選擇規格！！");
            return false;
        }
        spec += "," + spec3;
    }
    $("#spec").val(spec);
    return true;
}