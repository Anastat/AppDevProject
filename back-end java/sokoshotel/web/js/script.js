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
var userAddModal = document.getElementById('userAddModal');
userAddModal.style.display = "none";

// Get the button that opens the modal
var btn = document.getElementById("myBtn");
var addBtn = document.getElementById("addUserBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
     //clear all inner html of task input, if don't clear it displays data of last clicked task
    document.getElementById("taskID").innerHTML = "";
    document.getElementById("dateInput").value = "";
    document.getElementById("timeInput").value = "";
    document.getElementById("departmentSelect").value = "";
    document.getElementById("placeSelect").value = "";
    document.getElementById("titleInput").innerHTML = "";
    document.getElementById("detailsInput").innerHTML = "";
    //set form visible
    modal.style.display = "block";
}

addBtn.onclick = function () {
   userAddModal.style.display = "block";
   console.log(userAddModal);
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
    if (event.target == userAddModal) {
       
        userAddModal.style.display = "none";
    }
}


//Hiding buttons if no rights
var newTaskButton = document.getElementById('myBtn');
const url = 'webresources/users/getRights/'+username.innerHTML;
console.log(url);
  fetch(url)
  .then((resp) => resp.json())
  .then(function(data) {      
      if (data == 2) {
      	newTaskButton.style.display = "none";
        addBtn.style.display = "none";
      }
    })

  .catch(function(error) {
    console.log(error);
  });   



const submitButtonn = document.querySelector("#saveButtonAdduser");
const formInput = document.querySelector(".formUser");
let note = {};
let departmentid = {};
let rightsid = {};
formInput.addEventListener("input", function () {
    const username = formInput.querySelector("#usernameInput").value;
    const password = formInput.querySelector("#passwordInput").value;
    const firstname = formInput.querySelector("#firstnameInput").value;
    const lastname = formInput.querySelector("#lastnameInput").value;
    const department = formInput.querySelector("#departmentSelectt").value;
    const rights = formInput.querySelector("#rightSelectt").value;

    departmentid.departmentID = parseInt(department);
    rightsid.rightsID = parseInt(rights);
    note.username = username;
    note.password = password;
    note.firstname = firstname;
    note.surname = lastname;
    note.department = departmentid;
    note.rights = rightsid;

    console.log("rikki");
    console.log(note);
  });

submitButtonn.addEventListener("click", function () {
  const urll = "http://localhost:8080/sokoshotel/webresources/users/newuser";

    console.log(JSON.stringify(note));
    note = JSON.stringify(note);
    const init = {
      method: "POST",
      body: note,
      headers: {
        "Content-type": "application/json; charset=UTF-8"
      }
    };
    console.log(note);
    fetch(urll, init)
      .then(response => response.json())
      .then(json => console.log("Note saved: " + JSON.stringify(json)))
      .catch(error => console.log("Fetch crashed due to " + error));
});
}