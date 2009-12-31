$(document).ready(function(){
//if(field&&ascdesc){
$("thead tr th a").each(function (){
  if($(this).attr("id")==field){
    if(ascdesc=="desc")
      $(this).addClass("toggleDate_");
    else
      $(this).addClass("toggleDate");
  }else if(orderArray&&$.inArray($(this).attr("id"),orderArray)!=-1){
      $(this).addClass("toggleDate__");
  }
});
//}
});
function orderByThis(form,obj){
if(ascdesc=="asc")
  $("#ascdescid").attr("value","desc");
else
  $("#ascdescid").attr("value","asc");
  $("#orderfieldid").attr("value",obj.id);
  
  //alert( $("#ascdescid").attr("value"));
  //alert( $("#orderfieldid").attr("value"));
  
  form.submit();
}