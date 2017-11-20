$(document).ready(function() {
    
});

// added an onclick attribute for each of my items display on my vending.jsp
// so when it is clicked this function is executed and display item id on the
// input tag where the id="item-input"
function displayToItemMessage(itemId) {
    $('#item-input').val(itemId);
}

function addDollar() {
    var currentMoney, sum;
    if ($('#money-in').val() == '') {
        currentMoney = 0;
        sum = parseFloat(currentMoney + 1.00).toFixed(2);
    } else {
        currentMoney = +$('#money-in').val();
        sum = parseFloat(currentMoney + 1.00).toFixed(2);
    }
    $('#money-in').val(sum);
}

function addQuarter() {
    var currentMoney, sum;
    if ($('#money-in').val() == '') {
        currentMoney = 0;
        sum = parseFloat(currentMoney + 0.25).toFixed(2);
    } else {
        currentMoney = +$('#money-in').val();
        sum = parseFloat(currentMoney + 0.25).toFixed(2);
    }
    $('#money-in').val(sum);
}

function addDime() {
    var currentMoney, sum;
    if ($('#money-in').val() == '') {
        currentMoney = 0;
        sum = parseFloat(currentMoney + 0.10).toFixed(2);
    } else {
        currentMoney = +$('#money-in').val();
        sum = parseFloat(currentMoney + 0.10).toFixed(2);
    }
    $('#money-in').val(sum);
}

function addNickel() {
    var currentMoney, sum;
    if ($('#money-in').val() == '') {
        currentMoney = 0;
        sum = parseFloat(currentMoney + 0.05).toFixed(2);
    } else {
        currentMoney = +$('#money-in').val();
        sum = parseFloat(currentMoney + 0.05).toFixed(2);
    }
    $('#money-in').val(sum);
}

////// For change return button ///////////////////////////////////////////////
function displayChange(quarters, dimes, nickels, pennies) {
    var changeMessage = '';
    if (quarters != 0) {
        changeMessage += quarters + 'Quarters ';
    }

    if (dimes != 0) {
        changeMessage += dimes + 'Dimes ';
    }

    if (nickels != 0) {
        changeMessage += nickels + 'Nickels ';
    }

    if (pennies != 0) {
        changeMessage += pennies + 'Pennies ';
    }

    $('#change-message').val(changeMessage);
}

function returnChange() {
    var change = ($('#money-in').val() * 100);
    var quarters = 0;
    var dimes = 0;
    var nickels = 0;
    var pennies = 0;

    if (($('#money-in').val() == '') || ($('#money-in').val() == null)) {
        $('#change-message').val('No change...');
    } else {
        if (change > 25 || change == 25) {
            quarters = Math.floor(change / 25);
            change = change % 25;
        }

        if (change > 10 || change == 10) {
            dimes = Math.floor(change / 10);
            change = change % 10;
        }

        if (change > 5 || change == 5) {
            nickels = Math.floor(change / 5);
            change = change % 5;
        }

        pennies = change;

        displayChange(quarters, dimes, nickels, pennies);
        $('#money-in').val('');
        $('#item-input').val('');
    }
}
///////////////////////////////////////////////////////////////////////////////