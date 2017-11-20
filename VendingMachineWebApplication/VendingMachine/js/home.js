$(document).ready(function () {

    loadAllItems();

});

function loadAllItems() {
    var numberForRow = 1;
    var count = 3;
    var contentRow;

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/items',
        success: function (itemArray) {
            $.each(itemArray, function (index, item) {
                var id = item.id;
                var name = item.name;
                var price = item.price;
                var quantity = item.quantity;

                var itemToDisplay = '<td></td>';
                itemToDisplay += '<td height="150">';
                itemToDisplay += '<div id="item' + id + '" ' + 'onclick="displayToItem(' + id + ')" ' + 'style="border: 1px solid black; cursor: pointer;">';
                itemToDisplay += '<p>' + id + '</p>';
                itemToDisplay += '<p class="text-center">' + name + '</p>';
                itemToDisplay += '<p class="text-center">$' + price + '</p>';
                itemToDisplay += '<p class="text-center">Quantity Left:' + quantity + '</p>';
                itemToDisplay += '</div>';
                itemToDisplay += '</td>';

                if (count == 3) {
                    addRow(numberForRow);
                    contentRow = $('#row' + numberForRow);
                    count = 1;
                    numberForRow++;
                } else {
                    count++;
                }

                contentRow.append(itemToDisplay);
            });
        },
        error: function () {
            alert('Fail to load items!');
        }
    });
}

function purchase() {
    $('#content').empty();
    var itemToPurchase = $('#item-input').val();
    var amount = $('#money-in').val();

    if (itemToPurchase == '') {
        $('#general-message').val('Choose an item first!');
    } else if (amount == '') {
        $('#general-message').val('Add some money first!');
    } else {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/money/' + amount + '/item/' + itemToPurchase,
            success: function (change, status) {
                var quarters = change.quarters;
                var dimes = change.dimes;
                var nickels = change.nickels;
                var pennies = change.pennies;

                displayChange(quarters, dimes, nickels, pennies);
                $('#general-message').val('Thank You!');
                $('#money-in').val('');
                $('#item-input').val('');
            },
            error: function (error) {
                if (error.responseJSON.message == 'SOLD OUT!!!') {
                    $('#money-in').val('');
                    $('#item-input').val('');
                }
                $("#general-message").val(error.responseJSON.message);
            }
        });
    }
    loadAllItems();
}

// Created this function to add rows to help me display Items dynamically.
function addRow(rowNumber) {
    var contentBody = $("#content");
    var newRow = '<tr id="row' + rowNumber + '"></tr>';

    contentBody.append(newRow);
}

function displayToItem(id) {
    $('#change-message').val('');
    $('#general-message').val('');
    $('#item-input').val(id);
}

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

    if ($('#money-in').val() == '') {
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