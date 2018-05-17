<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%@ page import="javax.portlet.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CREATE POLL</title>
<!-- <style type="text/css">
input[type=text] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type=submit]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style> -->
<style>
   .wptheme1Col .wpthemeCol {
    width: 100% !important;
}
</style>
</head>
<body>
<%-- 	<portlet:actionURL var="actionOneMethodURL">
		<portlet:param name="action" value="create-Poll" />
	</portlet:actionURL>
	<div class="col-md-12">
		<!-- 		<h3 class="box-title">Poll</h3>
 -->
		<form action="${actionOneMethodURL}" method="post">
			<label>Question* :</label> <input name="question" type="text"
				id="question" /> <label>Option1* :</label> <input name="options"
				type="text" /> <label>Option2* :</label> <input name="options"
				type="text" />
			<div align="center">
				<input type="submit" value="Submit" />
			</div>
		</form>
		<span style="font-family: inherit;"></span>



	</div> --%>
	
	
	
	
	
	<%-- 
    Document   : createpoll
    Created on : Apr 6, 2016, 12:49:10 PM
    Author     : tempuser
--%>





<section class="content-header">
    <h1 class="createpolls_h1">Polls
        <button data-target="#myModal" data-toggle="modal" class="btn btn-danger pull-right">Create Polls</button>
    </h1>

</section>

<!-- Main content -->
<section class="content">
    <div class = "modal-body">
        <div class="polls">
            <c:forEach items="${allQuestions}" var="allQuestions">
                <div class="form-group row">
                    <div class="col-md-12">

                        <p><c:out value="${allQuestions.question}" /></p>  

                        <c:choose> 
                            <c:when test="${(allQuestions.status eq 'active')}" > 
                                <a href="<portlet:actionURL><portlet:param name='action' value='questionStatusChange' />
                                   <portlet:param name='currentStatus' value='${allQuestions.status}'/>
                                   <portlet:param name='questionID' value='${allQuestions.questionID}'/>
                                   </portlet:actionURL>" class="btn btn-success pull-right polls_active">
                                    Active
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="<portlet:actionURL><portlet:param name='action' value='questionStatusChange' />
                                   <portlet:param name='currentStatus' value='${allQuestions.status}'/>
                                   <portlet:param name='questionID' value='${allQuestions.questionID}'/>
                                   </portlet:actionURL>" class="btn btn-success pull-right polls_active">
                                    InActive
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>


            <!--<input type="submit" data-target="#myModal" value="Create Polls"/>-->

        </div>      

    </div>
    <!-- Your Page Content Here --> 
</section>


<div class = "modal fade" id = "myModal" tabindex = "-1" role = "dialog" 
     aria-labelledby = "myModalLabel" aria-hidden = "true">

    <div class = "modal-dialog">
        <div class = "modal-content">

            <div class = "modal-header header" >
                <button type = "button" class = "close" data-dismiss = "modal" aria-hidden = "true">
                    ×
                </button>
                <h4 class = "modal-title" id = "myModalLabel">
                    Create Polls
                </h4>
            </div>
            
            
            <div class="col-md-12">
                <div class="col-md-12">
                    <b style="float:left;">Options type : 
                        <select class="form-control">
                            <option value="text">Text</option>
                            <option value="image">Image</option>
                        </select>
                </div>
            </div>
            
        


            <form action="<portlet:actionURL><portlet:param name='action' value='create-Poll' /></portlet:actionURL>" method="post" onsubmit="return validate()" name="myform"> 	
                <div class = "modal-footer"> 
                    <div class="col-md-12">
                        <div class="poll_table">

                            <b style="float:left;" >Question:</b>
                            <textarea rows="1" class="form-control" name="pollQuestion" id="pollQuestionID"></textarea>
                            <b style="float:left;"> Options</b>
                            <table class="poll_options" id="myTable">
                                <tbody>
                                    <tr>
                                        <td><b><input type="text" name="rowNo" class="rowNo" value="1"/></b></td>
                                        <td><input type="text" class="form-control fname" id="inputEmail" name="option" /></td>
                                        <td> <button class="delete"><img class="pull-right" src="<%=request.getContextPath()%>/images/delete_icon.png" ></button></td>
                                    </tr>

                                    <tr>
                                        <td><b><input type="text" name="rowNo" class="rowNo" value="2"></b></td>
                                        <td><input type="text" class="form-control fname" id="inputEmail" name="option" ></td>
                                        <td> <button class="delete"><img class="pull-right" src="<%=request.getContextPath()%>/images/delete_icon.png" ></button></td>
                                    </tr>

                                    <!--  <tr>
                                      <td><b><input type="text" name="rowNo" class="rowNo" value="1"></b></td>
                                      <td><input type="text" class="form-control" id="inputEmail" name="option" ></td>
                                      <td> <img class="pull-right" src="images/delete_icon.png"></td>
                                      </tr> -->
                                </tbody>
                            </table>
                                    
                        </div>
                        <!--<label> <button class="btn btn-success pull-right add" id="add">Add Choice</button></label>-->
                        <hr>
                    </div>

                    <!--<button class="btn btn-danger pull-right polls_submit" >Submit</button>-->
                    <input  class="btn btn-success pull-right " type="submit" value="Submit"/>
                </div>
                <label> <button type="button" class="btn btn-success pull-right add" id="add">Add Choice</button></label>   
            </form> 
                        
                                    
                                    
            <form action="<portlet:actionURL><portlet:param name='javax.portlet.action' value='CreatePollWithImages' /></portlet:actionURL>" 
                  method="post" name="myform1" onsubmit="return validate1()" 
                  style="display: none;" enctype="multipart/form-data"> 
                
                <div class = "modal-footer"> 
                    <div class="col-md-12">
                        <div class="poll_table">

                            <b style="float:left;" >Question:</b>
                            <textarea rows="1" class="form-control" name="pollQuestion" id="pollQuestionID1"></textarea>
                            <b style="float:left;"> Options</b>
                            <table class="poll_options" id="myTable1">
                                <tbody>
                                    <tr>
                                        <td><b><input type="text" name="rowNo" class="rowNo" value="1"/></b></td>
                                        <td><input type="file" class="form-control fname" id="inputEmail" name="option" /></td>
                                        <td> <button class="delete1"><img class="pull-right" src="<%=request.getContextPath()%>/images/delete_icon.png" ></button></td>
                                    </tr>

                                    <tr>
                                        <td><b><input type="text" name="rowNo" class="rowNo" value="2"></b></td>
                                        <td><input type="file" class="form-control fname" id="inputEmail" name="option" ></td>
                                        <td> <button class="delete1"><img class="pull-right" src="<%=request.getContextPath()%>/images/delete_icon.png" ></button></td>
                                    </tr>
                                </tbody>
                            </table>
                                    
                        </div>
                        <hr>
                    </div>

                    <input  class="btn btn-success pull-right " type="submit" value="Submit"/>
                </div>
                                    
            <label> <button type="button" class="btn btn-success pull-right add" id="add1">Add Choice</button></label>   
            </form>
                    
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->

</div>

<script>
    function validate() {
        var result = true;
        var options;

        var questionValue = $.trim($("#pollQuestionID").val());
        if (questionValue.length === 0) {
            alert("Please enter all fields");
            result = false;
        }
        
        if (result){
            $('#myTable .fname').each(function (index, value) {
                if ($.trim($(this).val()).length === 0) {
                    //$(this).css('border','1px solid red');
                    options = "Options Empty";
                }
            });
            if (options === "Options Empty"){
                alert("Please enter all fields");
                result = false;
            }
        }
        return result;
    }
    
    
    function validate1() {
        var result = true;
        var options;
        var questionValue = $.trim($("#pollQuestionID1").val());
        if (questionValue.length === 0) {
            alert("Please enter all fields");
            result = false;
        }
        
        if (result){
            $('#myTable1 .fname').each(function (index, value) {
                if ($.trim($(this).val()).length === 0) {
                    options = "Options Empty";
                }
            });
            if (options === "Options Empty"){
                alert("Please enter all fields");
                result = false;
            }
        }
        return result;
    }

    $(document).ready(function () {
        $('.delete').css('display', 'none');
        $('.delete1').css('display', 'none');

        function enableDeleteBtn() {
            var rowCount = $('#myTable tr').length;
            if (rowCount > 2) {
                $('.delete').css('display', 'block');
            }
            else {
                $('.delete').css('display', 'none');
            }
        }
        
        function enableDeleteBtn1() {
            var rowCount = $('#myTable1 tr').length;
            if (rowCount > 2) {
                $('.delete1').css('display', 'block');
            }
            else {
                $('.delete1').css('display', 'none');
            }
        }
        
        function sortRows() {
            $('#myTable tr .rowNo').each(function (index, value) {
                $(this).val(index + 1);
            });
        }
        
        function sortRows1() {
            $('#myTable1 tr .rowNo').each(function (index, value) {
                $(this).val(index + 1);
            });
        }
        
        
        
        $("#add").click(function () {
            var length = $('#myTable tr').length + 1;
            var row = '<tr><td><input type="text" name="rowNo" class="rowNo" value="' + length + '"></td>' +
                    '<td><input type="text" class="form-control fname" id="inputEmail" name="option"></td>' +
                    '<td><button class="delete"><img class="pull-right" src="<%=request.getContextPath()%>/images/delete_icon.png" ></button></td></tr>';

            $('#myTable').append(row);
            enableDeleteBtn();
        });
        
        
        $("#add1").click(function () {
            var length = $('#myTable1 tr').length + 1;
            var row = '<tr><td><input type="text" name="rowNo" class="rowNo" value="' + length + '"></td>' +
                    '<td><input type="file" class="form-control fname" id="inputEmail" name="option"></td>' +
                    '<td><button class="delete1"><img class="pull-right" src="<%=request.getContextPath()%>/images/delete_icon.png" ></button></td></tr>';

            $('#myTable1').append(row);
            enableDeleteBtn1();
        });

        $("#myTable").on("click", ".delete", function () {
            //$('#myTable').append(row);
            $(this).closest("tr").remove();
            enableDeleteBtn();
            sortRows();
        });
        
        $("#myTable1").on("click", ".delete1", function () {
            $(this).closest("tr").remove();
            enableDeleteBtn1();
            sortRows1();
        });
        
        
        
        
        $("select").change(function(){
            $(this).find("option:selected").each(function(){
                if($(this).attr("value")==="text"){
                    $("form[name=myform]").show();
                    $("form[name=myform1]").hide();
                }
                else if($(this).attr("value")==="image"){
                    $("form[name=myform]").hide();
                    $("form[name=myform1]").show();
                }
            });
        });
        

    });
</script>
</body>
</html>