window.onload = function() {
//Loading JS before HTML
    
var username = document.getElementById('username');


 
    $(document).ready(function() {
        username.innerHTML =  getCookie("username");
    });    
        
  function getCookie(name)
  {
    var re = new RegExp(name + "=([^;]+)");
    var value = re.exec(document.cookie);
    return (value != null) ? unescape(value[1]) : null;
  }

// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}


//Hiding button if no rights
var newTaskButton = document.getElementById('myBtn');
const url = 'webresources/users/getRights/'+username.innerHTML;
console.log(url);
  fetch(url)
  .then((resp) => resp.json())
  .then(function(data) {      
      if (data == 2) {
      	newTaskButton.style.display = "none";
      }
    })

  .catch(function(error) {
    console.log(error);
  });   
}