<%-- 
    Document   : createHero
    Created on : Oct 28, 2017, 9:14:08 PM
    Author     : tedis
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create New Hero Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/myNavigation.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Merriweather|Muli:300" rel="stylesheet">
        <style>
            .addEditDeleteButton {
                background-color: Transparent;
                background-repeat:no-repeat;
                border: none;
                cursor:pointer;
                overflow: hidden;
                outline:none;
            }

            #main-div {
                height: 800px;
            }

            #create-hv-div {
                position: relative;
                top: 200px;
            }

            #list-of-powers {
                position: relative;
                top: 200px;
            }

            .styleFontOne {
                font-family: 'Muli', sans-serif;
                font-weight: bold;
                font-size: 40px;
            }

            .styleFontTwo {
                font-family: 'Muli', sans-serif;
                font-weight: bold;
                font-size: 25px;
            }

            .styleFontThree {
                font-family: 'Muli', sans-serif;
                font-size: 20px;
            }

            #error-div {
                text-align: center;
                color: red;
                font-size: 50px;
            }
        </style>
    </head>
    <body>
        <div id="main-div" class="container-fluid">
            <div id="create-hv-div" class="col-md-offset-2 col-md-3">
                <div class="styleFontOne">
                    Create New HeroVillain</h2>
                </div>
                <hr>
                <form class="form-horizontal" role="form" method="POST" action="createHeroVillain">
                    <div class="form-group styleFontThree">
                        <label for="add-hv-name" class="col-md-3 control-label">Name: </label>
                        <div class="col-md-9">
                            <c:choose>
                                <c:when test="${hvName == 'error'}">
                                    <input type="text" class="form-control" id="add-hv-name" name="hvName" placeholder="Don't leave this blank!"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="text" class="form-control" id="add-hv-name" name="hvName" placeholder="HeroVillain Name" value="${hvName}"/>
                                </c:otherwise>
                            </c:choose>                           
                        </div>
                    </div>
                    <div class="form-group styleFontThree">
                        <label for="add-hv-description" class="col-md-3 control-label">Description: </label>
                        <div class="col-md-9">
                            <c:choose>
                                <c:when test="${hvDescription == 'error'}">
                                    <textarea id="add-hv-description" name="hvDescription" class="form-control" placeholder="Don't leave this blank!"></textarea>
                                </c:when>
                                <c:otherwise>
                                    <textarea id="add-hv-description" name="hvDescription" class="form-control" placeholder="Description of HeroVillain">${hvDescription}</textarea>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="form-group styleFontThree">
                        <label for="status-select" class="col-md-3 control-label">Status: </label>
                        <div class="col-md-9">
                            <select id="status-select" name="heroOrVillain" class="form-control">
                                <c:choose>
                                    <c:when test="${heroOrVillain == 'Hero'}">
                                        <option value="Hero" selected="">Hero</option>
                                        <option value="Villain">Villain</option>
                                    </c:when>
                                    <c:when test="${heroOrVillain == 'Villain'}">
                                        <option value="Hero">Hero</option>
                                        <option value="Villain" selected="">Villain</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="Hero">Hero</option>
                                        <option value="Villain">Villain</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                    </div>
                    <div id="powers-placement">
                        <c:forEach var="hvPower" items="${hvPowers}">
                            <input type="hidden" id="chosen-power${hvPower.id}" name="power${hvPower.id}" value="${hvPower.id}"/>
                        </c:forEach>
                    </div>
                    <div class="form-group">
                        <div id="createHVButton" class="col-md-offset-6 col-md-4" style="margin-left: 225px">
                            <input type="submit" class="btn" value="Create HeroVillain" />
                        </div>
                        <div class="col-md-2">
                            <a href="${pageContext.request.contextPath}/displayHeroesVillainsPage" style="color: black;">
                                <button type="button" class="btn">Back</button>
                            </a>
                        </div>
                    </div>
                </form>
            </div>
            <div id="list-of-powers" class="col-md-5">
                <div>
                    <div class="styleFontOne">
                        Choose From Existing List Of Powers
                    </div>
                    <hr>
                    <div>
                        <c:forEach var="currentPower" items="${powersList}">
                            <div class="styleFontThree">
                                <div class="col-md-6">
                                    <c:out value="${currentPower.powerName}"/>
                                </div>
                                <div id="check-mark-spot${currentPower.id}" class="col-md-1">
                                    <c:forEach var="hvPower" items="${hvPowers}">
                                        <c:choose>
                                            <c:when test="${hvPower.id eq currentPower.id}">
                                                <span id="check-mark${currentPower.id}" class="glyphicon glyphicon-ok" style="color: green;"></span>
                                            </c:when>
                                            <c:otherwise></c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </div>
                                <div class="col-md-offset-2 col-md-1">
                                    <c:set var="skip" value="false"/>
                                    <c:forEach var="hvPower" items="${hvPowers}">
                                        <c:choose>
                                            <c:when test="${hvPower.id eq currentPower.id}">
                                                <button id="add-power${currentPower.id}" class="addEditDeleteButton" onclick="addPowerToHero(${currentPower.id})" style="display: none;">
                                                    <img src="${pageContext.request.contextPath}/img/icons8-Plus-26.png">                           
                                                </button>
                                                <button id="remove-power${currentPower.id}" class="addEditDeleteButton" onclick="removePowerFromHero(${currentPower.id})">
                                                    <img src="${pageContext.request.contextPath}/img/icons8-Minus-26.png">
                                                </button>
                                                <c:set var="skip" value="true"/>
                                            </c:when>
                                            <c:otherwise></c:otherwise>
                                        </c:choose>
                                    </c:forEach>

                                    <c:if test="${skip == false}">
                                        <button id="add-power${currentPower.id}" class="addEditDeleteButton" onclick="addPowerToHero(${currentPower.id})">
                                            <img src="${pageContext.request.contextPath}/img/icons8-Plus-26.png">                           
                                        </button>
                                        <button id="remove-power${currentPower.id}" class="addEditDeleteButton" onclick="removePowerFromHero(${currentPower.id})" style="display: none;">
                                            <img src="${pageContext.request.contextPath}/img/icons8-Plus-26.png">
                                        </button>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="col-md-2 offset"></div>
        </div>
        <div id="error-div" class="container-fluid">${error}</div>
        <div class="footer">
            <div class="col-md-12 nav-div" style="padding: 0;">
                <div class="col-md-offset-4 col-md-5">
                    <nav class="navbar navbar-default mynavbar" style="margin: 0;">
                        <ul class="nav navbar-nav">
                            <li>
                                <a href="${pageContext.request.contextPath}/displayHeroesVillainsPage" class="navFont" style="color: white;">HeroesVillains</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/displayLocationsPage" class="navFont" style="color: white;">Locations</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/home"><span size="10" class="glyphicon glyphicon-home" style="color: white;"></a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/displayOrganizationsPage" class="navFont" style="color: white;">Organizations</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/displaySightingsPage" class="navFont" style="color: white;">Sightings</a>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/heroesVillains.js"></script>
    </body>
</html>