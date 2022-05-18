alert("Magical!");
console.log("my first log!");
console.log(document.getElementById("myFirstForm"));
//console.log(document.getElementByTagName("label"));
console.log(document.getElementsByClassName(".special"));
console.log(document.querySelector("#myFirstForm"));

var form = document.getElementById("myFirstForm");
var goButton = document.getElementById("go");

goButton.addEventListener("click",doWhatIWant);

function doWhatIWant(event){
    console.log("this is my this: ");
    console.log(this);
    console.log("this is my event: ");
    console.log(event);
    console.log("this is my form: ");
    console.log(form);
    console.log("these are my form elements");
    console.log(form.elements[1].value);
    var myValue = form.elements[1].value;
    for(var i = 0; i <= form.elements.length; i++){
        alert(form.elements.name);

    }
}
