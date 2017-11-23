<%-- 
    Document   : editHVPage
    Created on : Oct 30, 2017, 10:19:14 AM
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

            #edit-hv-div {
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
        </style>
    </head>
    <body>
        <div id="edit-hv-div" class="col-md-offset-2 col-md-3">
            <div class="styleFontOne">
                Edit Your Hero Or Villain</h2>
            </div>
            <hr>
            <form class="form-horizontal" role="form" method="POST" action="editHeroVillain">
                <div class="form-group styleFontThree">
                    <label for="edit-hv-name" class="col-md-3 control-label">Name: </label>
                    <div class="col-md-9">
                        <input type="hidden" name="hvId" value="${hv.id}"/>
                        <input type="text" class="form-control" id="edit-hv-name" name="hvName" value="${hv.hVName}" />
                    </div>
                </div>
                <div class="form-group styleFontThree">
                    <label for="edit-hv-description" class="col-md-3 control-label">Description: </label>
                    <div class="col-md-9">
                        <textarea id="edit-hv-description" name="hvDescription" class="form-control">${hv.hVDescription}</textarea>
                    </div>
                </div>
                <div class="form-group styleFontThree">
                    <label for="status-select" class="col-md-3 control-label">Status: </label>
                    <div class="col-md-9">
                        <select id="status-select" name="heroOrVillain" class="form-control">
                            <c:choose>
                                <c:when test="${hv.isVillain == true}">
                                    <option value="Hero">Hero</option>
                                    <option value="Villain" selected="selected">Villain</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="Hero" selected="selected">Hero</option>
                                    <option value="Villain">Villain</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>
                </div>
                <div id="powers-placement">
                    <c:forEach var="hvPower" items="${hvPowers}">
                        <input type="hidden" id="chosen-power${hvPower.id}" name="power${hvPower.id}" value="${hvPower.id}">
                    </c:forEach>
                </div>
                <div class="form-group">
                    <div id="createHVButton" class="col-md-offset-6 col-md-6" style="margin-left: 315px;">
                        <input type="submit" class="btn" value="Edit HeroVillain" />
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
                        <div class="col-md-offset-2"></div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="col-md-offset-2"></div>
        <div class="footer">
            <div class="col-md-12 nav-div" style="padding: 0;">
                <div class="col-md-offset-4 col-md-5">
                    <nav class="navbar navbar-default mynavbar" style="margin: 0;">
                        <ul class="nav navbar-nav">
                            <li>
                                <a href="${pageContext.request.contextPath}/displayHeroesVillainsPage" class="navFont" style="color: white">HeroesVillains</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/displayLocationsPage" class="navFont" style="color: white">Locations</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/home"><span size="10" class="glyphicon glyphicon-home" style="color: white"></a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/displayOrganizationsPage" class="navFont" style="color: white">Organizations</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/displaySightingsPage" class="navFont" style="color: white">Sightings</a>
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
