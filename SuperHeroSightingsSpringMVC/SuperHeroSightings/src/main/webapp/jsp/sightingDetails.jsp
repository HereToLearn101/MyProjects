<%-- 
    Document   : sightingDetails
    Created on : Nov 7, 2017, 8:37:19 AM
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
            
            #list-of-sightings {
                position: relative;
                top: 100px;
            }
            
            #sighting-details {
                position: relative;
                top: 150px;
            }

            .addEditDeleteButton {
                background-color: Transparent;
                background-repeat:no-repeat;
                border: none;
                cursor: pointer;
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
        <div>
            <div id="title-div" class="col-md-5">
                <div>
                    Sightings
                    <div>
                        <a href="">
                            <button type="button" class="btn">New Sightings</button>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div id="sighting-details">
                    <div class="col-md-offset-1 col-md-6">
                        <div class="styleFontOne">
                            Sighting Details
                        </div>
                        <hr>
                        <div>
                            <span class="styleFontTwo">Location Name: </span> <span id="name-div" class="styleFontThree"></span>
                        </div>
                        <div>
                            <span class="styleFontTwo">Date: </span> <span id="date-div" class="styleFontThree"></span>
                        </div>
                        <div class="styleFontTwo">
                            HeroesVillains Sighted:
                        </div>
                        <div class="styleFontThree scrollable-div-hv-list">
                            <ul id="hv-list"></ul>
                        </div>
                    </div>
                    <div class="col-md-offset-5"></div>
                    <div>
                        <div class="col-md-offset-5 col-md-1">
                            <button type="button" class="btn" onclick="backToSightingList()">Back</button>
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
