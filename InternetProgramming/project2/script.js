//will test card number, cvc, zip
function testLength(value, length){
    if(value.length == length){
        return true;
    }
    else{
        return false;
    }
}
//will test card number, cvc, zip and the other 5 untested things, cause we dont want numbers
function testNumber(value){
    if(isNaN(value)){
        return false;
    }
    else{
        return true;
    }
}
//will validate zip and cvc
function validateControl(control, name, length){
    if(name.toString() == "Zip"){
        if(testLength(control, length)){ 
            if(testNumber(control)){
                return true;
            }
            else{
            alert("Invalid Zip, not a number");
            return false;
            }
        }
        else{
            alert("Invalid Zip length");
            return false;
        }
    }
    if(name.toString() == "CVC"){
        if(testLength(control, length)){ 
            if(testNumber(control)){
                return true;
            }
            else{
            alert("Invalid CVV2/CVC, not a number");
            return false;
            }
        }
        else{
            alert("Invalid CVV2/CVC length");
            return false;
        }
    }
}
function validateCreditCard(value){
    value = value.replace(/\s/g, '');
    if(testNumber(value) == false){
        alert("Please enter a valid credit card");
        return false;
    }
    var firstDigit = parseInt(value.charAt(0));
    if(firstDigit == 3){
        if(testLength(value, 15)){
            return true;
        }
        else{
            alert("Invalid credit card length");
            return false;
        }
        
    }
    else if(firstDigit == 6 || firstDigit == 5 || firstDigit == 4){
        if(testLength(value, 16)){
            return true;
        }
        else{
            alert("Invalid credit card length");
            return false;
        }
    }
    else{
        alert("Invalid credit card numbers");
        return false;
    }
}
//checking expiration date
function validateDate(value){
    var selected = new Date(value);
    var today = new Date();
    if(selected<today){
        alert("Card is expired")
        return false;
    }
    else{
        return true;
    }

}
function validateEmail(value){
    var re = /\S+@\S+\.\S+/;
    if(re.test(value) == true){
        return true;
    }
    else{
        alert("Please enter a valid email");
        return false;
    }
}
function validateState(value){
    if (value.toString() === "Select State"){
        alert("Please select a state");
        return false;
    }
    else{
        return true;
    }
}
function validateForm(){
    var zip = document.getElementById("Zip").value;
    var email = document.getElementById("Email").value;
    var cardnumber = document.getElementById("Card#").value;
    var CVC = document.getElementById("CVC").value;
    var state = document.getElementById("State").value;
    var date = document.getElementById("ExpirationDate").value;   
    if(validateControl(zip, "Zip", 5)){
        if(validateEmail(email)){
            if(validateCreditCard(cardnumber)){
                if(validateControl(CVC, "CVC", 3)){
                    if(validateState(state)){
                        if(validateDate(date)){
                            alert("Payment Submitted");
                        }else{return false;}
                    }else{return false;}
                }else{return false;}
            }else{return false;}
        }else{return false;}
    }else{return false;}

}