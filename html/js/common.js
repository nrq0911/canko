var basePath = getRootPath();
function getRootPath(){
    if(window.document.location.href.split("//")[1].split("/").length == 4){
        var curWwwPath=window.document.location.href;
        var pathName=window.document.location.pathname;
        var pos=curWwwPath.indexOf(pathName);
        var localhostPaht=curWwwPath.substring(0,pos);
        var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
        return (localhostPaht+projectName);
    }else{
        return window.document.location.href.replace(window.document.location.pathname, "");
    }
}

toastr.options = {
    /* 定义操作结果弹出框的属性 */
    "closeButton": true,
    "debug": false,
    "progressBar": false,
    "positionClass": "toast-bottom-full-width",
    "showDuration": "400",
    "hideDuration": "1000",
    "timeOut": "3000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
};

/**
 * 判断对象是否为空
 */
function isBlank(obj){
    if (obj==null || obj ==undefined || obj=='') {
        return true;
    }
    return false;
}

//判断obj是否为json对象
function isJson(obj){
    var isJson = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;
    return isJson;
}

$(function () {
    var esearch = {};
    window.esearch = esearch;

    esearch.tableListHandle = function (res) {

        if(res.success == false){
            esearch.error(res.msg);
            if(res.msg == "login timeout"){
                window.top.location.href = basePath;
            }
            // return {"rows":[],"total":0};
        }else{
            if(res.rows.length == 0){
                return {"rows":[],"total":0};
            }else{
                return {"rows": res.rows, "total": res.total};
            }
        }
    };

    esearch.post = function(url, data, back ){
        $.post(url, data, back, 'json');
    }

    esearch.postform = function(url, $form, back ){
        esearch.post(url, $form.serialize(), back);
    };

    esearch.error = function (msg) {
        toastr["error"](msg);
    };

    esearch.success = function (msg) {
        toastr["info"](msg);
    };

    esearch.warn = function (msg) {
        toastr["warning"](msg);
    };

    esearch.timeout = function (fun, option) {
        setTimeout(function () {
            if(option){
                fun(option);
            }else{
                fun();
            }
        }, 800);
    }

    esearch.doResult = function (result, success, failure) {
        if(result.success){
            if(success){
                success(result);
            }else{
                if(result.msg){
                    esearch.success(result.msg);
                }else{
                    esearch.success("操作成功");
                }
            }
        }else{
            if(failure){
                failure(result);
            }else{
                esearch.error(result.msg);
            }
        }
    };

    esearch.getUrlParam = function(name, url) {
        if (!url) {
            url = window.location.href;
        }
        name = name.replace(/[\[\]]/g, "\\$&");
        var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, " "));
    };

    esearch.setStorage = function (key, value) {
        window.localStorage.setItem(key, value);
    };

    esearch.getStorage = function (key) {
        return window.localStorage.getItem(key);
    };

    esearch.clearStorage = function () {
        window.localStorage.clear();
    };

    esearch.appkey = "appName";

    esearch.pageState = {
        edit : 'edit',
        addnew : 'addnew'
    };

    esearch.table = function ($table, url, columns, responseHandler, option) {

        var default_option = {
            showRefresh: true,
            method: 'post',
            url: url,
            dataType: "json",
            pagination: true,
            "queryParamsType": "limit",
            contentType: "application/x-www-form-urlencoded",
            singleSelect: false,
            pageSize: 10,
            pageNumber:1,
            clickToSelect:true,
            search: true, //不显示 搜索框
            columns: columns,
            showColumns: false, //不显示下拉框（选择显示的列）
            sidePagination: "server", //服务端请求
            responseHandler: responseHandler
        };

        var final_option = $.extend(default_option, option);

        $table.bootstrapTable(final_option);
    };

    esearch.confirmDelete = function (back, msg) {
        var defaultMessage = "删除的数据将无法恢复";
        if(msg){
            defaultMessage = msg;
        }
        swal({
                title: "确认删除?",
                text: defaultMessage,
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认删除",
                closeOnConfirm: true
            },
            function(){
                back();
            });
    };

    esearch.confirmAction = function (back, msg, action) {
        var defaultMessage = "操作的数据将无法恢复";
        if(msg){
            defaultMessage = msg;
        }
        swal({
                title: "确认" + action + "?",
                text: defaultMessage,
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认" + action,
                closeOnConfirm: true
            },
            function(){
                back();
            });
    };

    esearch.bindEnterkey = function ($ele, fun) {
        $ele.keydown(function (event) {
            if (event.which == 13)       //13等于回车键(Enter)键值,ctrlKey 等于 Ctrl
                fun();
        });
    };

    esearch.select2 = {

        selectId: "",

        elgSelect2: {},

        ele: {},

        formatGroupRepo: function (repo) {
            repo.text = repo.name;
            if (repo.loading) return repo.text;
            var markup = "<div value=" + repo.id + ">" + repo.name + "</div>";
            return markup;
        },

        formatRepoSelection: function (repo) {
            return repo.full_name || repo.text;
        },

        displayGroup: function (accountArray) {
            for(var i = 0,isize = accountArray.length; i < isize; i ++){
                var ele = accountArray[i];
                this.ele.append(new Option(ele.name,ele.id, true, true));
            }
            this.elgSelect2.trigger('change.select2');
        },

        init: function ($selectId, url, display) {
            this.ele = $selectId;
            this.elgSelect2 = $selectId.select2(
                {
                    allowClear: true,
                    placeholder: "请输入信息查询告警账号",
                    language: "zh-CN",
                    ajax: {
                        url: url,
                        dataType: 'json',
                        delay: 250,
                        type: "POST",
                        data: function (params) {
                            return {
                                groupName: params.term,
                                page: params.page
                            };
                        },
                        processResults: function (data, params) {
                            params.page = params.page || 1;
                            return {
                                results: data.items,
                                pagination: {
                                    more: (params.page * 30) < data.total_count
                                }
                            };
                        },
                        cache: true
                    },
                    escapeMarkup: function (markup) {
                        return markup;
                    }, // let our custom formatter work
                    minimumInputLength: 1,
                    templateResult: this.formatGroupRepo, // omitted for brevity, see the source of this page
                    templateSelection: this.formatRepoSelection // omitted for brevity, see the source of this page
                }
            );

            if(display){
                display(this);
            }
            return this;
        }
    };

    $.fn.serializeObject = function() {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

    Date.prototype.format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
});