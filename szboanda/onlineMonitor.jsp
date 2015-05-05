<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="net.sf.json.JSONArray"
%><%@page import="net.sf.json.JSONObject"
%><%@page import="java.io.ByteArrayOutputStream"
%><%@page import="java.io.InputStream"
%><%@page import="java.io.InputStreamReader"
%><%@page import="java.io.BufferedReader"
%><%@page import="java.net.URL"
%><%@page import="java.util.*"
%><%@page import="java.util.ArrayList"
%><%@page import="java.net.HttpURLConnection"
%><%@page import="org.apache.commons.httpclient.HttpClient"
%><%
	
	String typeStr = null;
	request.getAttribute("type");
	String urlPath =  "";
	if("1".equals(typeStr)){
		urlPath ="http://222.243.169.212:8000/ydzfxt/pages/android/zxjc/queryWrySite.jsp?queryArea=430302000";
	}
	urlPath ="http://222.243.169.212:8000/ydzfxt/pages/android/zxjc/queryWrySite.jsp?queryArea=430302000";
	URL url = new URL(urlPath);
	HttpURLConnection  connection = (HttpURLConnection)url.openConnection();
	connection.setDoOutput(true);
	connection.setRequestMethod("POST");
	connection.setRequestProperty("Content-type", "text/html");
	connection.setRequestProperty("Accept-Charset", "UTF-8");
	//connection.setRequestProperty("contentType", "UTF-8");
    connection.connect();
    //BufferedReader reader = new BufferedReader(new InputStreamReader());
    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
    InputStream inputstream  = connection.getInputStream();
    byte buffer [] = new byte [1024];
    int len = 0;
    while((len = inputstream.read(buffer))!=-1){
    	byteArray.write(buffer,0,len);
    }
    
    String str = new String(byteArray.toByteArray(),"UTF-8");
  
   
    response.setCharacterEncoding("UTF-8");
  //  out.print(str);
    
    JSONArray  jsonArray =  JSONArray.fromObject(str);
    JSONArray mJsonArray =  new JSONArray();
      for(int i=0;i<jsonArray.size();i++){
     	JSONObject mjson = jsonArray.getJSONObject(i);
		JSONObject tempjson = new JSONObject();
		tempjson.put("设备编号", mjson.get("设备编号"));
		tempjson.put("SBLX", mjson.get("SBLX"));
		tempjson.put("TITLE", mjson.get("测点名称"));
		tempjson.put("PRIMARY_KEY", mjson.get("PSCODE"));
		tempjson.put("LINK", "wry/online/zxjcDetailCategory.jsp");
		tempjson.put("设备名称", mjson.get("设备名称"));
		String remark = "设备名称:"+ mjson.get("设备名称");
		tempjson.put("REMARK", remark); 
		tempjson.put("TIME", "排污类型:"+ ("FQ".equals(mjson.get("设备编号"))? "废气":"废水")); 
		tempjson.put("DESCRIPTION", "地址:"+ mjson.get("测站地址"));
		mJsonArray.add(tempjson);
 	} 
      StringBuffer json = new StringBuffer();
      json.append("{\"totalCount\":" + mJsonArray.size() + ",");
      json.append("\"data\":"+mJsonArray.toString()+"}");
  	out.print(json.toString());
  	System.out.println("totalCount：" + jsonArray.size());
  	System.out.println("在线监测查询结果：" + json.toString());
    connection.disconnect();
%>













