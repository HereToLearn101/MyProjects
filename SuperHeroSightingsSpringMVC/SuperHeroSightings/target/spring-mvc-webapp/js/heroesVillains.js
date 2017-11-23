/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    $('#hvName').on

});

//****************************HEROES AND VILLAINS PAGE**********************************************************
function addPowerToHero(powerId) {
    $('#add-power' + powerId).hide();
    $('#remove-power' + powerId).show();

    var checkMark = '<span id="check-mark' + powerId + '" ';
    checkMark += 'class="glyphicon glyphicon-ok" style="color: green;">';
    checkMark += '</span>';

    var inputPower = '<input type="hidden" id="chosen-power' + powerId + '" name="power' + powerId + '" ';
    inputPower += 'value="' + powerId + '">';

    $('#check-mark-spot' + powerId).append(checkMark);
    $('#powers-placement').append(inputPower);
}

function removePowerFromHero(powerId) {
    $('#remove-power' + powerId).hide();
    $('#add-power' + powerId).show();

    $('#check-mark' + powerId).remove();
    $('#chosen-power' + powerId).remove();
}

function displayHVDetails(hvId) {
    clearHvDetails();
    $('#list-of-heroesvillains').hide();
    $('#hv-details').show();

    $.ajax({
        type: 'GET',
        url: 'HeroVillain/' + hvId,
        success: function (hv) {
            $('#name-div').append(hv.hVName);
            $('#descrip-div').append(hv.hVDescription);

            if (hv.isVillain == false) {
                $('#status-div').append('Hero');
                $('#reputation-with-details').append('Hero');
            } else {
                $('#status-div').append('Villain');
                $('#reputation-with-details').append('Villain');
            }

            for (i = 0; i < hv.powers.length; i++) {
                var powerList = '<li>' + hv.powers[i].powerName + '</li>';
                $('#power-list').append(powerList);
            }
        },
        error: function () {
            alert('Failed!');
        }
    });
}

function backToHvList() {
    $('#hv-details').hide();
    $('#list-of-heroesvillains').show();
}

function clearHvDetails() {
    $('#name-div').empty();
    $('#descrip-div').empty();
    $('#status-div').empty();
    $('#power-list').empty();
    $('#reputation-with-details').empty();
}
//*****************************************************************************************************

//**************************************FOR LOCATIONS PAGE**********************************************
function displayLocationDetails(locId) {
    clearLocationDetails();
    $('#list-of-locations').hide();
    $('#location-details').show();

    $.ajax({
        type: 'GET',
        url: 'Location/' + locId,
        success: function (location) {
            $('#name-div').append(location.locationName);
            $('#descrip-div').append(location.locationDescription);
            $('#address-div').append(location.address);
            $('#latitude-div').append(location.lat);
            $('#longitude-div').append(location.lng);
        },
        error: function () {
            alert('Failed!');
        }
    });
}

function clearLocationDetails() {
    $('#name-div').empty();
    $('#descrip-div').empty();
    $('#address-div').empty();
    $('#latitude-div').empty();
    $('#longitude-div').empty();
}

function backToLocationList() {
    $('#location-details').hide();
    $('#list-of-locations').show();
}
//**************************************************************************************************

//*****************************FOR ORGANIZATIONS PAGE***********************************************
function displayOrganizationDetails(orgId) {
    clearOrgDetails();
    $('#list-of-organizations').hide();
    $('#org-details').show();

    $.ajax({
        type: 'GET',
        url: 'Organization/' + orgId,
        success: function (org) {
            $('#name-div').append(org.orgName);
            $('#descrip-div').append(org.orgDescription);
            $('#phone-div').append(org.phone);
            $('#email-div').append(org.email);
            $('#location-div').append(org.location.locationName);

            for (i = 0; i < org.metaHumans.length; i++) {
                var hvList = '<li>' + org.metaHumans[i].hVName + '</li>';
                $('#hv-list').append(hvList);
//                alert(org.metaHumans[i].hVName);
            }
        },
        error: function () {
            alert('Failed!');
        }
    });
}

function addHvToOrg(hvId) {
    $('#add-hv' + hvId).hide();
    $('#remove-hv' + hvId).show();

    var checkMark = '<span id="check-mark' + hvId + '" ';
    checkMark += 'class="glyphicon glyphicon-ok" style="color: green;">';
    checkMark += '</span>';

    var inputHv = '<input type="hidden" id="chosen-hv' + hvId + '" name="hv' + hvId + '" ';
    inputHv += 'value="' + hvId + '">';

    $('#check-mark-spot' + hvId).append(checkMark);
    $('#hv-placement').append(inputHv);
}

function removeHvToOrg(hvId) {
    $('#remove-hv' + hvId).hide();
    $('#add-hv' + hvId).show();

    $('#check-mark' + hvId).remove();
    $('#chosen-hv' + hvId).remove();
}

function clearOrgDetails() {
    $('#name-div').empty();
    $('#descrip-div').empty();
    $('#phone-div').empty();
    $('#email-div').empty();
    $('#location-div').empty();
    $('#hv-list').empty();
}

function backToOrgList() {
    $('#org-details').hide();
    $('#list-of-organizations').show();
}
//************************************************************************************************

//********************************FOR SIGHTINGS PAGE**********************************************
function displaySightingDetails(sightId) {
    clearSightingDetails();
    $('#list-of-sightings').hide();
    $('#sighting-details').show();

    $.ajax({
        type: 'GET',
        url: 'Sighting/' + sightId,
        success: function (sighting) {
            $('#name-div').append(sighting.location.locationName);
            var date = sighting.date.month + ' ' + sighting.date.dayOfMonth + ', ' + sighting.date.year;
            $('#date-div').append(date); // PROBLEM HERE!

            for (var i = 0; i < sighting.hVList.length; i++) {
                var hvList = '<li>' + sighting.hVList[i].hVName + '</li>';
                $('#hv-list').append(hvList);
            }
        },
        error: function () {
            alert('Failed to grab Sighting Details!');
        }
    });
}

function clearSightingDetails() {
    $('#name-div').empty();
    $('#date-div').empty();
    $('#hv-list').empty();
}

function backToSightingList() {
    $('#sighting-details').hide();
    $('#list-of-sightings').show();
}