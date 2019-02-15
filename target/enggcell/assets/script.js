
function checkForm(form)
{

    if(!form.terms.checked) {
        alert("Please indicate that you accept the Terms and Conditions");
        form.terms.focus();
        return false;
    }
    return true;
}

$().ready(function () {

    $(".submit").click(function (e){
        e.preventDefault();
        var d = {
            fullname : $('#fullname').val(),
            address : $('#address').val(),
            college : $('#college').val(),
            email : $('#email').val(),
            terms1: $('#terms').val(),
            companies : $('#companies').val(),
            mobile : $('#mobile').val(),
            dobday : $('#dobday').val(),
            dobmonth : $('#dobmonth').val(),
            dobyear : $('#dobyear').val(),
            aadhar : $('#aadhar').val(),
            it : $('#it').val(),
            passingYear : $('#year').val(),
            gender : $('#gender').val(),
            location : $('#location').val(),
            city : $('#location').val(),
            resume1 : $('#fileUpload'),
            resume : $('#resume').val()
        };
        localStorage.setItem("data", JSON.stringify(d));
        $("#signupForm").submit();

    });

    $("#signupForm").validate({

        rules: {
            fullname: {
                required: true,
                maxlength: 65
            },
            passingYear: {
            	required: true
            },
            resume1: {
            	required: true
            },
            city: {
            	required: true
            },
            terms1: {
            	required: true
            },
            companies: {
            	required: true
            },
            address: {
            	required: true
            },
            college: {
            	required: true
            },
            email: {
            	required: true
            },
            mobile: {
                required: true,
                integer: true,
                minlength: 10,
                maxlength: 10

            },
            dobday : {
                required: true,
                integer: true,
                minlength: 2,
                maxlength: 2
            },
            dobmonth : {
                required: true,
                integer: true,
                minlength: 2,
                maxlength: 2
            },
            dobyear : {
                required: true,
                integer: true,
                minlength: 4,
                maxlength: 4
            },
            aadhar:{
                required:true,
                alphanumeric:true,
                maxlength: 12
            },
            terms:"required"
        },
        messages: {
            fullname: {
                required: "Please enter your full name",
                lettersonly: "Please enter alphabets only",
                maxlength: "Please enter 65 characters or less"
            },
            passingYear: {
            	required: "Select passing year"
            },
            resume1: {
            	required: "Select your Resume"
            },
            city: {
            	required: "Select your city"
            },
            terms1: {
            	required: "Please accept terms and conditions"
            },
            companies: {
            	required: "Enter atleast one company"
            },
            address: {
            	required: "Please enter your address"
            },
            college: {
            	required: "Please enter your college name"
            },
            mobile: {
                required: "Please enter your contact number",
                integer: "Please enter numbers only",
                minlength: "Please enter a 10 digit mobile number",
                maxlength: "Please enter a 10 digit mobile number"
            },
            dobday:{
                required: "Please enter your dob",
                integer: "Please enter numbers only"
            },
            dobmonth:{
                required: "Please enter your dob",
                integer: "Please enter numbers only"
            },
            dobyear:{
                required: "Please enter your dob",
                integer: "Please enter numbers only"
            },
            aadhar:{
                required: "Please enter your aadhar/pan number",
                maxlength: "Please enter 12 characters or less"
            },
            terms: "Please read and accept our policy"
        }

    });


});


