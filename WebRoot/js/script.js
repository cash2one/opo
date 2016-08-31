

window.onload=function(){
  // 标签的索引
  var index=0;
  var timer=null;
  var tit=document.getElementById('notice-tit');
  var con=document.getElementById('notice-con');
  var lis=tit.getElementsByTagName('li'),
      divs=$('.mod');
	 

  if(lis.length!=divs.length) return;

  // 遍历所有的页签
  for(var i=0;i<lis.length;i++){
    lis[i].id=i;
    lis[i].onclick=function(){
      // 用that这个变量来引用当前滑过的li
      var that=this;
      // 如果存在准备执行的定时器，立刻清除，只有当前停留时间大于500ms时才开始执行
      if(timer){
        clearTimeout(timer);
        timer=null;
      }
      // 延迟半秒执行
      timer=window.setTimeout(function(){
        for(var j=0;j<lis.length;j++){
          lis[j].className='';
          divs[j].style.display='none';
        }
        lis[that.id].className='select';
        divs[that.id].style.display='block';
      },100);//500--0.5秒
    };
  }
};