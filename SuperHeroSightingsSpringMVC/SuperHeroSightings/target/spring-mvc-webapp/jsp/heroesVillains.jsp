<%-- 
    Document   : createHeroVillain
    Created on : Oct 25, 2017, 12:55:30 PM
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
        <link href="${pageContext.request.contextPath}/css/myNavigation.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Merriweather|Muli:300" rel="stylesheet">
        <style>
            #title-div {
                font-family: 'Merriweather', serif;
                font-size: 110px;
                font-weight: bold;
                text-align: center;
                height: 800px;
                vertical-align: middle;
                position: relative;
                top: 120px;
            }

            #list-of-heroesvillains {
                position: relative;
                top: 100px;
            }

            .addEditDeleteButton {
                background-color: Transparent;
                /*background-repeat:no-repeat;*/
                border: none;
                cursor:pointer;
                overflow: hidden;
                outline:none;
            }

            .styleFontOne {
                font-family: 'Muli', sans-serif;
                font-weight: bold;
                font-size: 50px;
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

            .body-div {
                height: 800px;
            }

            #hv-details {
                position: relative;
                top: 150px;
            }
            
            .scrollable-div {
                height:250px;
                /*width:920px;*/
                /*border:1px solid #ccc;*/
                /*font:16px/26px Georgia, Garamond, Serif;*/
                overflow:auto;
            }

        </style>
    </head>
    <body>
        <div class="body-div">
            <div id="title-div" class="col-md-5">
                <div>
                    Heroes and Villains
                    <div>
                        <a href="${pageContext.request.contextPath}/displayCreateHeroVillainPage">
                            <button type="button" class="btn">New HeroVillain</button>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div id="list-of-heroesvillains">
                    <div class="styleFontOne">
                        Current List of Heroes and Villains
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="styleFontTwo">Name</div>
                            <hr>
                        </div>
                        <div class="col-md-4">
                            <div class="styleFontTwo">Status</div>
                            <hr>
                        </div>
                        <div class="col-md-offset-4"></div>
                    </div>
                    <div class="scrollable-div col-md-12">
                        <c:forEach var="currentHV" items="${hvList}">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="styleFontThree" style="cursor: pointer;" onclick="displayHVDetails(${currentHV.id})">
                                        <c:out value="${currentHV.hVName}"/>
                                    </div>
                                </div>
                                <div class="col-md-4 styleFontThree">
                                    <c:choose>
                                        <c:when test="${currentHV.isVillain == false}">
                                            Hero
                                        </c:when>
                                        <c:otherwise>
                                            Villain
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="col-md-1">
                                    <a href="${pageContext.request.contextPath}/displayEditHVPage?hvId=${currentHV.id}">
                                        <button class="addEditDeleteButton glyphicon glyphicon-edit"></button>
                                    </a>
                                </div>
                                <div class="col-md-1">
                                    <a href="${pageContext.request.contextPath}/deleteHV?hvId=${currentHV.id}">
                                        <button class="addEditDeleteButton glyphicon glyphicon-remove"></button>
                                    </a>
                                </div>
                                <div class="col-md-offset-2"></div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div id="hv-details" style="display: none;">
                    <div class="col-md-offset-1 col-md-6">
                        <div class="styleFontOne">
                            <span id="reputation-with-details"></span> Details
                        </div>
                        <hr>
                        <div>
                            <span class="styleFontTwo">Name: </span> <span id="name-div" class="styleFontThree"></span>
                        </div>
                        <div>
                            <span class="styleFontTwo">Description: </span> <span id="descrip-div" class="styleFontThree"></span>
                        </div>
<!--                        <div id="descrip-div" class="styleFontThree"></div>-->
                        <div>
                            <span class="styleFontTwo">Status: </span> <span id="status-div" class="styleFontThree"></span>
                        </div>
                        <div class="styleFontTwo">
                            Powers:
                        </div>
                        <div class="styleFontThree">
                            <ul id="power-list"></ul>
                        </div>
                    </div>
                    <div class="col-md-offset-5"></div>
                    <div>
                        <div class="col-md-offset-5 col-md-1">
                            <button type="button" class="btn" onclick="backToHvList()">Back</button>
                        </div>
                        <div class="col-md-offset-6"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-offset-1"></div>
        </div>

        <div class="footer">
            <div class="col-md-12 nav-div" style="padding: 0;">
                <div class="col-md-offset-4 col-md-5">
                    <nav class="navbar navbar-default mynavbar" style="margin: 0;">
                        <ul class="nav navbar-nav">
                            <li>
                                <a href="${pageContext.request.contextPath}/displayHeroesVillainsPage" style="color: white;">HeroesVillains</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/displayLocationsPage" style="color: white;">Locations</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/home"><span size="10" class="glyphicon glyphicon-home" style="color: white;"></a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/displayOrganizationsPage" style="color: white;">Organizations</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/displaySightingsPage" style="color: white;">Sightings</a>
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
