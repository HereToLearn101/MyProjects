<%-- 
    Document   : vending
    Created on : Oct 8, 2017, 12:49:28 PM
    Author     : tedis
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <div class="text-center">
                <h1>Vending Machine</h1>
            </div>
            <hr/>
            <div class="col-md-8">
                <c:forEach var="currentItem" items="${itemList}" varStatus="myIndex">
                    <div class="col-md-offset-1 col-md-3" style="padding-bottom: 1cm;">
                        <div  onclick="displayToItemMessage(${currentItem.itemId})" style="border: 1px solid black; cursor: pointer;">
                            <p><c:out value="${currentItem.itemId}"/></p>
                            <div class="text-center">
                                <p><c:out value="${currentItem.itemName}"/></p>
                                <p>$<c:out value="${currentItem.price}"/></p>
                                <p>Quantity Left:<c:out value="${currentItem.quantity}"/></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="col-md-offset-1 col-md-3">
                <form id="add-purchase-form" class="text-center" role="form" method="GET" action="processItem">
                    <div>
                        <!--<form id="add-money-form" class="text-center" role="form">-->
                        <div class="form-group">
                            <label><h3>Total $ in</h3></label><br>
                            <input type="text" id="money-in" name="money" class="form-control text-center" value="${money}" readonly /><br>

                            <div class="form-group">
                                <button type="button" id="add-dollar" class="btn" onclick="addDollar()">Add Dollar</button>
                                <button type="button" id="add-quarter" class="btn" onclick="addQuarter()">Add Quarter</button>
                            </div>

                            <div class="form-group">
                                <button type="button" id="add-dime" class="btn" onclick="addDime()">Add Dime</button>
                                <button type="button" id="add-nickel" class="btn" onclick="addNickel()">Add Nickel</button>
                            </div>
                        </div>
                        <!--</form>-->
                    </div>
                    <hr style="border: 1px solid black">
                    <div>
                        <!--<form id="purchase-form" class="text-center" role="form">-->
                        <div class="form-group">
                            <label><h3>Messages</h3></label><br>
                            <input id="general-message" class="text-center" value="${message}" style="font-size: 20px;" readonly/><br>
                            <label for="item-input"><h3>Item:</h3></label>
                            <input type="text" id="item-input" name="item" class="text-center" value="${itemId}" size="5" readonly/><br>

                            <div class="form-group">
                                <button type="submit" id="make-purchase" class="btn">Make Purchase</button>
                            </div>
                        </div>
                        <!--</form>-->
                    </div>
                </form>
                <hr style="border: 1px solid black">
                <div>
                    <form id="change-form" class="text-center" role="form">
                        <div class="form-group">
                            <label><h3>Change</h3></label><br>
                            <input type="text" id="change-message" class="text-center" value="${change}" readonly/><br>
                            <br>
                            <div class="form-group">
                                <button type="button" id="change-return" class="btn" onclick="returnChange()">Change Return</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/vending.js"></script>
    </body>
</html>