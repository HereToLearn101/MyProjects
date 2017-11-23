<%-- 
    Document   : createOrg
    Created on : Nov 17, 2017, 11:13:23 AM
    Author     : tedis
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create New Organization Page</title>
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

            #create-org-div {
                position: relative;
                top: 150px;
            }

            #list-of-hv {
                position: relative;
                top: 150px;
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
            <div id="create-org-div" class="col-md-offset-2 col-md-3">
                <div class="styleFontOne">
                    Create New Organization</h2>
                </div>
                <hr>
                <form class="form-horizontal" role="form" method="POST" action="createHeroVillain">
                    <div class="form-group styleFontThree">
                        <label for="name" class="col-md-3 control-label">Name: </label>
                        <div class="col-md-9">
                            <c:choose>
                                <c:when test="${hvName == 'error'}">
                                    <input type="text" class="form-control" id="name" name="name" placeholder="Don't leave this blank!"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="text" class="form-control" id="name" name="name" placeholder="HeroVillain Name" value="${hvName}"/>
                                </c:otherwise>
                            </c:choose>                           
                        </div>
                    </div>
                    <div class="form-group styleFontThree">
                        <label for="description" class="col-md-3 control-label">Description: </label>
                        <div class="col-md-9">
                            <c:choose>
                                <c:when test="${hvDescription == 'error'}">
                                    <textarea id="description" name="description" class="form-control" placeholder="Don't leave this blank!"></textarea>
                                </c:when>
                                <c:otherwise>
                                    <textarea id="description" name="description" class="form-control" placeholder="Description of HeroVillain">${hvDescription}</textarea>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="form-group styleFontThree">
                        <label for="phone" class="col-md-3 control-label">Phone: </label>
                        <div class="col-md-9">
                            <input type="tel" class="form-control" id="phone" name="phone" placeholder="Phone Number"/>
                        </div>
                    </div>
                    <div class="form-group styleFontThree">
                        <label for="email" class="col-md-3 control-label">Email: </label>
                        <div class="col-md-9">
                            <input type="email" class="form-control" id="email" name="email" placeholder="Email"/>
                        </div>
                    </div>
                    <div class="form-group styleFontThree">
                        <label for="location-select" class="col-md-3 control-label">Location: </label>
                        <div class="col-md-9">
                            <select id="location-select" name="location" class="form-control">
                                <c:forEach var="currentLoc" items="${locList}">
                                    <option value="${currentLoc.id}">${currentLoc.locationName}</option>
                                </c:forEach>
<!--                                <option>Something</option>
                                <option>Something2</option>-->
                            </select>
                        </div>
                    </div>
                    <div id="hv-placement">
                        <!--<input type="hidden" id="chosen-power${hvPower.id}" name="power${hvPower.id}" value="${hvPower.id}"/>-->
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
            <div id="list-of-hv" class="col-md-5">
                <div>
                    <div class="styleFontOne">
                        Choose From Existing List Of HeroesVillains
                    </div>
                    <hr>
                    <div>
                        <c:forEach var="currentHv" items="${hvList}">
                            <div class="styleFontThree">
                                <div class="col-md-6">
                                    <c:out value="${currentHv.hVName}"/>
                                </div>
                                <div id="check-mark-spot${currentHv.id}" class="col-md-1">
                                    <c:forEach var="hv" items="${hvs}">
                                        <c:choose>
                                            <c:when test="${hv.id eq currentHv.id}">
                                                <span id="check-mark${currentHv.id}" class="glyphicon glyphicon-ok" style="color: green;"></span>
                                            </c:when>
                                            <c:otherwise></c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </div>
                                <div class="col-md-offset-2 col-md-1">
                                    <c:set var="skip" value="false"/>
                                    <c:forEach var="hv" items="${hvs}">
                                        <c:choose>
                                            <c:when test="${hv.id eq currentHv.id}">
                                                <button id="add-hv${currentHv.id}" class="addEditDeleteButton" onclick="addHvToOrg(${currentHv.id})" style="display: none;">
                                                    <img src="${pageContext.request.contextPath}/img/icons8-Plus-26.png">                           
                                                </button>
                                                <button id="remove-hv${currentHv.id}" class="addEditDeleteButton" onclick="removeHvFromOrg(${currentHv.id})">
                                                    <img src="${pageContext.request.contextPath}/img/icons8-Minus-26.png">
                                                </button>
                                                <c:set var="skip" value="true"/>
                                            </c:when>
                                            <c:otherwise></c:otherwise>
                                        </c:choose>
                                    </c:forEach>

                                    <c:if test="${skip == false}">
                                        <button id="add-hv${currentHv.id}" class="addEditDeleteButton" onclick="addHvToOrg(${currentHv.id})">
                                            <img src="${pageContext.request.contextPath}/img/icons8-Plus-26.png">                           
                                        </button>
                                        <button id="remove-hv${currentHv.id}" class="addEditDeleteButton" onclick="removeHvToOrg(${currentHv.id})" style="display: none;">
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
