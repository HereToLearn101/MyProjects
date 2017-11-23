<%-- 
    Document   : createLocation
    Created on : Nov 7, 2017, 7:15:19 PM
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

            #create-location-div {
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
            <div id="create-location-div" class="col-md-offset-4 col-md-4">
                <div class="styleFontOne">
                    Create New Location</h2>
                </div>
                <hr>
                <form class="form-horizontal" role="form" method="POST" action="createLocation">
                    <div class="form-group styleFontThree">
                        <label for="add-location-name" class="col-md-3 control-label">Name: </label>
                        <div class="col-md-9">
                            <c:choose>
                                <c:when test="${name == 'error'}">
                                    <input type="text" class="form-control" id="add-location-name" name="locationName" placeholder="Don't leave this blank!"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="text" class="form-control" id="add-location-name" name="locationName" placeholder="Location Name" value="${name}"/>
                                </c:otherwise>
                            </c:choose>                           
                        </div>
                    </div>
                    <div class="form-group styleFontThree">
                        <label for="add-location-description" class="col-md-3 control-label">Description: </label>
                        <div class="col-md-9">
                            <c:choose>
                                <c:when test="${descript == 'error'}">
                                    <textarea id="add-location-description" name="locationDescription" class="form-control" placeholder="Don't leave this blank!"></textarea>
                                </c:when>
                                <c:otherwise>
                                    <textarea id="add-location-description" name="locationDescription" class="form-control" placeholder="Location Description">${descript}</textarea>
                                </c:otherwise>
                            </c:choose>                  
                        </div>
                    </div>
                    <div class="form-group styleFontThree">
                        <label for="address-input" class="col-md-3 control-label">Address: </label>
                        <div class="col-md-9">
                            <c:choose>
                                <c:when test="${address == 'error'}">
                                    <input type="text" class="form-control" id="address-input" name="address" placeholder="Don't leave this blank!"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="text" class="form-control" id="address-input" name="address" placeholder="Location Address" value="${address}"/>
                                </c:otherwise>
                            </c:choose>
                            
                        </div>
                    </div>
                    <div class="form-group styleFontThree">
                        <label for="latitude-input" class="col-md-3 control-label">Latitude: </label>
                        <div class="col-md-9">
                            <c:choose>
                                <c:when test="${lat == 'error'}">
                                    <input type="text" class="form-control" id="latitude-input" name="latitude" placeholder="Don't leave this blank!"/>
                                </c:when>
                                <c:when test="${lat == 'notNum'}">
                                    <input type="text" class="form-control" id="latitude-input" name="latitude" placeholder="Put a real number!"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="text" class="form-control" id="latitude-input" name="latitude" placeholder="Location Latitude" value="${lat}"/>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="form-group styleFontThree">
                        <label for="longitude-input" class="col-md-3 control-label">Longitude: </label>
                        <div class="col-md-9">
                            <c:choose>
                                <c:when test="${lng == 'error'}">
                                    <input type="text" class="form-control" id="longitude-input" name="longitude" placeholder="Don't leave this blank!"/>
                                </c:when>
                                <c:when test="${lng == 'notNum'}">
                                    <input type="text" class="form-control" id="longitude-input" name="longitude" placeholder="Put a real number!"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="text" class="form-control" id="longitude-input" name="longitude" placeholder="Location Longitude" value="${lng}"/>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn col-md-offset-8" value="Create Location"/>
                        <a href="${pageContext.request.contextPath}/displayLocationsPage" style="color: black;">
                            <button type="button" class="btn">Back</button>
                        </a>
                    </div>
                </form>
            </div>
            <div class="col-md-4"></div>
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
