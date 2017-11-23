<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home Page</title>
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
                vertical-align: middle;
                position: relative;
            }

            #body-div {
                height: 800px;
            }

            .addEditDeleteButton {
                background-color: Transparent;
                background-repeat:no-repeat;
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
                height: 180px;
                /*width:920px;*/
                /*border: 1px solid #ccc;*/
                overflow: auto;
            }

        </style>
    </head>
    <body>
        <div id="body-div">
            <div id="title-div" style="padding-bottom: 1cm;">
                Super Hero Sightings
            </div>
            <div class="row" style="padding-bottom: 1cm;">
                <div class="col-md-offset-4 col-md-4 styleFontTwo">
                    <p class="styleFontTwo">This application displays sightings of Super Heroes and Villains. In addition:</p>
                    <ul>
                        <li>Create, View, Edit, Delete Heroes or Villains</li>
                        <li>Create, View, Edit, Delete Locations</li>
                        <li>Create, View, Edit, Delete Organizations</li>
                        <li>Create, Edit, Delete Sightings</li>
                    </ul>
                </div>
                <div class="col-md-4"></div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <div class="col-md-4">
                        <div class="styleFontTwo">Location Name</div>
                        <hr>
                    </div>
                    <div class="col-md-4">
                        <div class="styleFontTwo">Date</div>
                        <hr>
                    </div>
                    <div class="col-md-4"></div>
                </div>
                <div class="col-md-4"></div> 
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4 scrollable-div">
                    <c:forEach var="currentSighting" items="${listOfSightings}">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="styleFontThree">
                                    <c:out value="${currentSighting.location.locationName}"/>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <c:out value="${currentSighting.date}"/>
                            </div>
                            <div class="col-md-4"></div>
                        </div>
                    </c:forEach>
                </div>
            </div>
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
    </body>
</html>
