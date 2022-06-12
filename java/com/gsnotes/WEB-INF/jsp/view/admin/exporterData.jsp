<%--
  Created by IntelliJ IDEA.
  User: SuperElectro
  Date: 05/05/2022
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>



<%@page import="com.gsnotes.web.models.ModuleData" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">





    
       

        <f:form action="/controller/exporter" method="POST" modelAttribute="exportModule">
           
                <label >Choose your  Session: </label>
                
                       
     <br>
                        <f:radiobutton  path="session" value="Normale"/>
                       
        
                        <label  >Normale</label>
                         <br>
                    

                        <f:radiobutton  path="session" value="Rattrapage"/>
                        <label >Rattrapage</label>
                    
              
<br>
                <br>
                    <label >Choose one Module : </label>
                    <f:select path="nomModule">
                        <f:options items="${modulesName}"  />
                    </f:select>
                

<br>
                <br>
                    <button type="submit" >Submit</button>
                

          

        </f:form>

        


        


</body>

</html>
