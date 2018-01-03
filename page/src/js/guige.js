
 $(function(){
    // 初始化选择
    $("#comb .tab").eq(0).addClass('tab-sel').find('input').attr("checked", 'true');
    $('#comb').attr('data-price', $("#comb .tab-sel").attr('data-price'));
    $('[currentprice]').html($("#comb .tab-sel").attr('data-price'));
    // 选择产品
    $("#comb .tab").click(function(event) {
        /* Act on the event */
        $(this).addClass('tab-sel').siblings().removeClass('tab-sel');
        $(this).find('input').attr("checked", 'true');
        $(this).siblings().find('input').attr("checked", false);
        $('#comb').attr('data-price', $(this).attr('data-price'));
        $('[currentprice]').html($(this).attr('data-price'));
        var index = $(this).attr("data-loopindex");
        $('section[data-loopindex]').eq(index-1).show().siblings().hide();
        refresh_price();
    });

    $('.u-format.count_atrr').each(function(){
        var obj = $(this).find('.tab');
            obj.eq(0).addClass('tab-sel');
        var id = obj.attr('data-id');
        $(this).attr('data-select', id);
    });

    // 选择事件
    $('.u-format.count_atrr').on('click', '.tab', function(){
        var self = $(this);
        var id = self.attr('data-id');
        self.addClass('tab-sel').siblings().removeClass('tab-sel');
        self.parents('.u-format.count_atrr').attr('data-select', id);
        var src = self.attr('data-img');
        if( src ){ $('#attrimg').attr('src', src); }
    });
});


function addnumber(){
    $('#num').val(parseInt($('#num').val())+1);
    refresh_price(); 
}

function minnumber(){
    if($('#num').val() > 1){
        $('#num').val(parseInt($('#num').val())-1);
        refresh_price();
    }
}

function inputnumber(){
    var number=parseInt($('#num').val());
    if(isNaN(number)||number < 1){
        $('#num').val('1');
        refresh_price();
    }else if(number > 1){
        $('#num').val(number);
    }
    refresh_price();
}

// 刷新价格
function refresh_price() {

    var type = $("input[name='type']").val();
    app.number = parseInt($("#num").val());
}

// 提交表单
function postcheckGuige() {
    var type = $("input[name='type']").val();
    var url = "/checkout/"+type;

    // 数量
    var count = parseInt($('input#num').val()) || 1;
    url = url + "?count="+count;

    // 产品ID
    var comb_id = $("#comb .tab-sel").find('input').val();
    url = url + "&comb_id="+comb_id;
    
    /* Act on the event */
    if( $('.u-format.count_atrr').length > 0 ){
        var prototype = [];
        $('.u-format.count_atrr').each(function(){
            var groupId = $(this).attr('data-group');
            var prototypeId = $(this).attr('data-select');
            if( parseInt(prototypeId)>0 ){
                prototype.push(groupId+"-"+prototypeId);
            }
        });
        if( prototype.length == 0 ){
            alert('请选择属性');
            return false;
        }
        url = url + "&proto="+prototype.join('|');
    }
                
    // 跳转
    window.location.href = url;
    return false;
}