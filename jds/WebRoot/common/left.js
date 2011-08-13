Ext.onReady(function(){
  //添加进程任务
	var task = {
		run:function getnum(){
				var con = new Ext.data.Connection();
				con.request({
					url: '../wait/listNum.action',
					success:function(response,options){
							 var num = Ext.get('num');
							 num.dom.innerHTML=response.responseText;							 
						    }
				});
		    },
		interval: 60000*5 //5 minute
	}
	var runner = new Ext.util.TaskRunner();
	runner.start(task); 
  
});