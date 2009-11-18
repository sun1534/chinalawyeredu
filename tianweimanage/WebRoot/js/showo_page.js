
var url,re,CurrentPage,FirstPageUrl,PrevPageUrl,NextPageUrl,LastPageUrl,CurrPage,PageCount,prevpage,nextpage,PageStart,PageEnd,i,ipage;
   url=""+document.location;
   if (url.indexOf("Page=")==-1) {
     CurrPage=1;
   } 
   else {
     re=/(\S.*)(Page=\d*)(\S.*|\S*)/g;   
     CurrentPage=url.replace(re,"$2"); 
     url=url.replace("&"+CurrentPage,""); 
     url=url.replace(CurrentPage+"&",""); 
     url=url.replace(CurrentPage,"");      
     CurrentPage=CurrentPage.replace("Page=","")
     CurrPage=CheckNum(CurrentPage,1)	 
   }      
   if (url.indexOf("?")==-1) {
     url=url+"?";
   }  
   else {
     url=url+"&"; 
   }
   url=url.replace("?&","?");  
   url=url.replace("&&","&");
function CheckNum(str,num) {
  str=""+str;
  if (str.length>=1) {
    mynum=parseInt(str);
    if (isNaN(mynum)) {
      mynum=num;
    }
  }  
  else {
	mynum=num;
  } 	
  return (mynum);
}
function CheckPage(iPageCount) {
  url=url+'&Page='	
  url=url.replace("?&","?");
  url=url.replace("&&","&");
  ipage=document.iform.Page.value;	 
  ipage=CheckNum(ipage,1)	
  if (ipage>iPageCount)	 {
	ipage=iPageCount;
  }
  else if (ipage<1)	 {
	ipage=1;
  }
  else {
    ipage=ipage;
  }	 
  document.iform.action=url+ipage;
  document.iform.submit();
}     
function ShowoPage(Tabstart,Tabend,CurrPageFont1,CurrPageFont2,PageCountFont1,PageCountFont2,PrePageFont1,PrePageFont2,RecCountFont1,RecCountFont2,FirstFont,PrevFont,NextFont,LastFont,Jump,PageNumFont1,PageNumFont2,PageNumFont3,PageNumFont4,LinkFont1,LinkFont2,LinkFont3,LinkFont4,RecCount,RecPerPage,PageNum) {
  if (RecCount%RecPerPage==0) {
    PageCount=RecCount/RecPerPage;
  }
  else {
    PageCount=(parseInt(RecCount/RecPerPage)+1);
  }
  if (PageCount<=1) {
	PageCount=1
  }
  prevpage=parseInt(CurrPage-1);
  if (prevpage<1) {
	prevpage=1;
  }
  nextpage=parseInt(CurrPage+1)
  if (nextpage>PageCount) {
	nextpage=PageCount;
  }
  if (CurrPage<=1&&PageCount==1) {
    CurrPage=1;
	FirstPageUrl="&nbsp;"+LinkFont3+FirstFont+LinkFont4+"&nbsp;";
	PrevPageUrl="&nbsp;"+LinkFont3+PrevFont+LinkFont4+"&nbsp;";
	NextPageUrl="&nbsp;"+LinkFont3+NextFont+LinkFont4+"&nbsp;";
    LastPageUrl="&nbsp;"+LinkFont3+LastFont+LinkFont4+"&nbsp;";
  }
  else if (CurrPage<=1) {
    CurrPage=1;
	FirstPageUrl="&nbsp;"+LinkFont3+FirstFont+LinkFont4+"&nbsp;";
	PrevPageUrl="&nbsp;"+LinkFont3+PrevFont+LinkFont4+"&nbsp;";
	NextPageUrl="&nbsp;<A href=\""+url+"Page="+nextpage+"\">"+LinkFont1+NextFont+LinkFont2+"</A>&nbsp;";
    LastPageUrl="&nbsp;<A href=\""+url+"Page="+PageCount+"\">"+LinkFont1+LastFont+LinkFont2+"</A>&nbsp;";
  }
  else if (CurrPage>=PageCount) {
    CurrPage=PageCount;
	FirstPageUrl="&nbsp;<A href=\""+url+"Page=1\">"+LinkFont1+FirstFont+LinkFont2+"</A>&nbsp;";
	PrevPageUrl="&nbsp;<A href=\""+url+"Page="+prevpage+"\">"+LinkFont1+PrevFont+LinkFont2+"</A>&nbsp;";
	NextPageUrl="&nbsp;"+LinkFont3+NextFont+LinkFont4+"&nbsp;";
    LastPageUrl="&nbsp;"+LinkFont3+LastFont+LinkFont4+"&nbsp;";
  }	  
  else {
    CurrPage=CurrPage;	   
	FirstPageUrl="&nbsp;<A href=\""+url+"Page=1\">"+LinkFont1+FirstFont+LinkFont2+"</A>&nbsp;";
	PrevPageUrl="&nbsp;<A href=\""+url+"Page="+prevpage+"\">"+LinkFont1+PrevFont+LinkFont2+"</A>&nbsp;";
    NextPageUrl="&nbsp;<A href=\""+url+"Page="+nextpage+"\">"+LinkFont1+NextFont+LinkFont2+"</A>&nbsp;";
    LastPageUrl="&nbsp;<A href=\""+url+"Page="+PageCount+"\">"+LinkFont1+LastFont+LinkFont2+"</A>&nbsp;";
  }
  if (CurrPage-PageNum<=1) {
    PageStart=1;
  }
  else {
    PageStart=CurrPage-PageNum;
  }
  if (CurrPage+PageNum>=PageCount) {
    PageEnd=PageCount;
  }
  else {
    PageEnd=CurrPage+PageNum;
  }
  document.write ("<FORM method=\"post\" name=\"iform\" action=\""+url+"\" onSubmit=\"return CheckPage("+PageCount+")\">"+Tabstart);
  document.write (CurrPageFont1+CurrPage+CurrPageFont2+PageCountFont1+PageCount+PageCountFont2+PrePageFont1);
  document.write (RecPerPage+PrePageFont2+RecCountFont1+RecCount+RecCountFont2+FirstPageUrl+PrevPageUrl);
  for (i=PageStart;i<=PageEnd;i++) {
    if (i==CurrPage) {
      document.write ("&nbsp;"+PageNumFont3+i+PageNumFont4+"&nbsp;");
    } 
    else {
	  document.write ("&nbsp;<A href=\""+url+"Page="+i+"\">"+PageNumFont1+i+PageNumFont2+"</A>&nbsp;");
	}
  }
  document.write (NextPageUrl+LastPageUrl+Jump);
  document.write ("<INPUT type=\"text\" name=\"Page\" size=\"5\" maxlength=\"10\"><INPUT type=\"submit\" value=\"Go\" name=\"submit\">"+Tabend+"</FORM>");  
}