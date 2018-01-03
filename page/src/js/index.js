var app;
$(function(){
	 var goodsId = Cjs.url.getParamByName('goodsId');
	 if(goodsId == null || goodsId == ''){
	     goodsId = Cjs.url.getGoodsId();
     }
     $.getJSON(config.index_api+goodsId,function (data) {
         console.log(data.result);
         if(data.code!='200'){
             alert("服务器返回数据失败！");
             return false;
         }

         $("title").html(data.result.title);

         app = new Vue({
             el: '#contentPage',
             data: {
                 result      : data.result,
                 firstLevel  : 0,
                 secondLevel : 0,
                 firstLevelSpec  : '',
                 secondLevelSpec : '',
                 goodImg         : '',
                 number          : 1,
                 goodsId         : 0,
                 money           : 0
             },
             methods: {
                 firstLevelCheck: function (index,val) {
                     this.firstLevel     = index;
                     this.firstLevelSpec = val;
                     this.goodImg        = data.result.firstLevelPicture[val];
                     console.log(this.goodImg);
                 },
                 secondLevelCheck: function (index,val) {
                     this.secondLevel     = index;
                     this.secondLevelSpec = val;
                 },
                 submit : function () {
                     var data = {
                         goodsId : app.goodsId,
                         spec    : app.firstLevelSpec+','+app.secondLevelSpec,
                         num     : app.number,
                         money   : app.number*app.money,
                         goodImg : app.goodImg
                     };
                     
                     $.ajax({
                         url: config.order_post_api,
                         type: 'post',
                         dataType: 'json',
                         data: data,
                         success: function (res_data) {
                             if(res_data.code!=200){
                                 alert(res_data.msg);
                                 return false;
                             }
                             data.orderId = res_data.orderId;
                             data.price   = app.money;
                             var get = new Array();
                             for (key in data){
                                 get.push(key+"="+encodeURI(data[key]));
                             }

                             window.location.href = "./order.html?"+get.join("&");
                         }
                     });
                 }
             }
         });

         app.firstLevelSpec  = data.result.firstLevel[0];
         app.secondLevelSpec = data.result.secondLevel[0];
         app.goodImg         = data.result.firstLevelPicture[app.firstLevelSpec];
         app.goodsId         = data.result.id;
         app.money           = data.result.marketPrice;

         $("#contentPage").show();

         if( $(".edui-upload-video").length > 0 ){
             $(".edui-upload-video").attr("poster", "/public/image/videoPlay.jpg");
             $(".edui-upload-video").click(function(){
                 $(this).get(0).play();
             });
         }

         // 计时器
         var interval = 1000;
         function ShowCountDown(year,month,day,divname) {
             var now = new Date();
             var endDate = new Date(year,month-1, day, now.getHours()+8);
             var leftTime=endDate.getTime()-now.getTime();
             var leftsecond = parseInt(leftTime/1000);
             var day1=Math.floor(leftsecond/(60*60*24));
             var hour=Math.floor((leftsecond-day1*24*60*60)/3600);
             var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60);
             var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60);
             var cc = document.getElementById(divname);
             cc.innerHTML ="<span id='h'>"+0+hour+"</span>"+"<span class='colon'>時</span>"+"<span id='m'>"+minute+"</span>"+"<span class='colon'>分</span>"+"<span id='s'>"+second+"</span>"+"<span class='colon'>秒</span>";
         }
         window.setInterval(function(){ShowCountDown(2018,4,20,'timer');}, interval)

         // 回到顶部
         $(".m-goToTop").click(function(event) {
             $(window).scrollTop(0);
         });

         // 轮播图
         var h = $(window).height();
         var mySwiper1 = new Swiper('.swiper-container', {
             autoplay: 3000,
             loop: false,
             autoHeight:true,
             pagination: '.swiper-pagination',
             paginationType: 'custom',
             paginationCustomRender: function(swiper, current, total) {
                 var text = "";
                 for (var i = 1; i <= total; i++) {
                     if (current == i) {
                         text += "<span class='redicon'></span>";
                     } else {
                         text += "<span class='whiteicon'></span>";
                     }
                 }
                 return text;
             }
         });

         $("img.lazy").lazyload({
             effect: "fadeIn"
         });

         percent();

         // 显示属性层
         $('.btn-addToCart').on('click', function(event) {
             event.preventDefault();
             $('#page-order').show();
             $("#page-index").hide();
             $(window).scrollTop(0);
         });
         $('.detailback').on('click', function(event) {
             event.preventDefault();
             $('#page-order').hide();
             $("#page-index").show();
         });
         $('#val-sel').on('click', function(event) {
             event.preventDefault();
             $('#page-order').show();
             $("#page-index").hide();
             $(window).scrollTop(0);
         });

         // 初始化选择
         //$("#comb .tab").eq(0).addClass('tab-sel').find('input').attr("checked", 'true');
         //$('#comb').attr('data-price', $("#comb .tab-sel").attr('data-price'));
         //$('[currentprice]').html($("#comb .tab-sel").attr('data-price'));
         // 选择产品
         /*$("#comb .tab").click(function(event) {
             /!* Act on the event *!/
             $(this).addClass('tab-sel').siblings().removeClass('tab-sel');
             $(this).find('input').attr("checked", 'true');
             $(this).siblings().find('input').attr("checked", false);
             $('#comb').attr('data-price', $(this).attr('data-price'));
             $('[currentprice]').html($(this).attr('data-price'));
             var index = $(this).attr("data-loopindex");
             $('section[data-loopindex]').eq(index-1).show().siblings().hide();
             refresh_price();
         });*/

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





    //销售百分比
    function percent(){
        //获取时间点
        var curhour= $('.percentBar').attr('data-value');
        var base=0,range=0;
        var percent   = document.getElementById("percentNum");
        var progress  = document.getElementById("progress");
        var soldNum  = document.getElementById("soldNum");
        if(curhour<=1000){
            base=70;range=5;
        }else
        if(curhour<=2000){
            base=70;range=10;
        }else
        if(curhour<=4000){
            base=70;range=15;
        }else
        if(curhour<=8000){
            base=70;range=20;
        }else
        if(curhour<=13000){
            base=70;range=25;
        }else
        if(curhour<20000){
            base=70;range=28;
        }
        var opercent=Math.floor(range+base);
        progress.style.width = percent.innerHTML = opercent+"%";
    }

});
