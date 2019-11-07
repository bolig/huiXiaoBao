
function toast(msg){
    Android.showToast(msg);
}

function onPageLoad(){
    toast('页面加血中...');
}


function loadALineChart(){
    // 必须加JOSN.parse 转换数据类型
    var option = JSON.parse(Android.getLineChartOptions());
    var chart2Doc = document.getElementById('main');
    chart2Doc.style.height =Android.getHeight()*0.95+ 'px';
    var myChart2 = require('echarts').init(chart2Doc);
    myChart2.setOption(option);

}

