<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Glassfish Subsystem Administration</title>
</head>
<body BGCOLOR="#FDF5E6">
    <H2 ALIGN="CENTER">Glassfish Subsystem Administration</H2>
    <br>
    <form method="post" action="DeploySubsystemServlet" enctype="multipart/form-data">
    <div align="center">
       Please Select a subsystem definition file to deploy: <br>
       <input type="file" name="subsystemFile" />
    </div> 
    <div align="center">
       <input type="submit" value="deploy" />
    </div> 
    </form>
    <br>
    <div align="center">
       <a href="index.html" >Backing to Subsystem Administration Page</a>
    </div> 
</body>
</html>