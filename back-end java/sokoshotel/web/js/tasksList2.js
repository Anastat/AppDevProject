//In script: 
// - displaying tasks from database on page; 
// - displaying data of clicked task in form 

document.addEventListener("DOMContentLoaded", function (event) {
        
        function getCookie(name) {
		var re = new RegExp(name + "=([^;]+)");
		var value = re.exec(document.cookie);
		return (value != null) ? unescape(value[1]) : null;
	}
        
        //get users rigths
        const urlRights = `webresources/users/getRights/${getCookie("username")}`;
        fetch(urlRights)
            .then((resp) => resp.json())
            .then(json => setUserRights(json)) 
            .catch(function(error) {
            console.log(error);
            }); 
      
      //users rights
      let userRights;
      function setUserRights (data) {
          userRights = parseInt(data);
          console.log(userRights);
      }
	let urlTaskStatus = $('.float-right input[type=radio]:checked').val(); //get the value of checked radio button
	let url = `webresources/tasksrest/${urlTaskStatus}/${getCookie("username")}`;
	let urlDepartment = 1;

	fetch(`webresources/users/getDepartment/${getCookie("username")}`) //aside buttons depend of user's department 
		.then((resp) => resp.json())
		.then(json => department(json)) //we get from server user's department
                .then(result => departmentButtons())//wait for click department buttons
		.catch(error => (console.log("Fetch crashed due to " + error)));

	//create div with task in it
	function taskTemplate(task) {
		//console.log(task);
		return `
		<div class="taskInList" id="taskID${task.taskID}">
		<img src=${imgSrc(task.taskstatus)}>
		<h3>${task.title}</h3>
		<p class="placeList"><strong>Place: </strong>${task.place}</p>
		<p class="dateList"><strong>Due date: </strong>${task.duedate}</p>
		<p class="timeList"><strong>Due time: </strong>${task.duetime}</p>
		</div>
		`;
	}
	//source of the image depends of taskStatus
	function imgSrc(statusId) {
		let scr;
		if (statusId == 1) {
			scr = "./Icons/Add.png";
		} else if (statusId == 2) {
			scr = "./Icons/inProcess.png";
		} else if (statusId == 3) {
			scr = "./Icons/done.png";
		} else {
			scr = "./Icons/cancelled.png";
		}
		return scr;
	}


	let listTasks = function (tasks) {
		const tasksELement = document.getElementById("tasks-list");
		const imgElement = document.querySelector("img");
		tasksELement.innerHTML = `${tasks.map(taskTemplate).join("")}`;
	};


	//added eventListener to all tasks and handle it
	let clickedtask = () => {
		let urlClickedTask;

		let divTask = document.getElementsByClassName("taskInList"); //HTMLCollection
		//add eventListener to all elements of HTMLCollection
		for (let task of divTask) {
			task.addEventListener('click', function () {
				let urlForTaskId = task.getAttribute("id");
				urlClickedTask = `webresources/tasksrest/${urlForTaskId}`;
				fetch(urlClickedTask)
					.then(response => response.json())
					.then(json => taskResponse(json))
					.catch(error => (console.log("Fetch crashed due to " + error)));
			});
		};
	};

	//added eventListener to aside department buttons
	let departmentButtons = () => {
              
		console.log(userDepartmentId);
                console.log("rights = " + userRights);
		if (userRights == 1 || userDepartmentId == 1 || userDepartmentId == 2) {
			depAll.addEventListener("click", function () {
				activeAll();
                                urlDepartment = 1;
				fetch(`webresources/tasksrest/${urlTaskStatus}`)
					.then(response => response.json()) //first we get all tasks from database
					.then(json => listTasks(json))
                                        .then(result => clickedtask()) //waiting for clicked task after task is loaded from database 
					.catch(error => (console.log("Fetch crashed due to " + error)));

			});
			frontOffice.addEventListener("click", function () {
				activeFrontOffice();
				urlDepartment = 2;
				fetch(`webresources/tasksrest/${urlTaskStatus}/department${urlDepartment}`)
					.then(response => response.json()) //first we get all tasks from database
					.then(json => listTasks(json))
                                        .then(result => clickedtask()) //waiting for clicked task after task is loaded from database 
					.catch(error => (console.log("Fetch crashed due to " + error)));
			});
			housekeeping.addEventListener("click", function () {
				activeHousekeeping();
				urlDepartment = 3;
				fetch(`webresources/tasksrest/${urlTaskStatus}/department${urlDepartment}`)
					.then(response => response.json()) //first we get all tasks from database
					.then(json => listTasks(json))
                                        .then(result => clickedtask()) //waiting for clicked task after task is loaded from database 
					.catch(error => (console.log("Fetch crashed due to " + error)));
			});
			guestservices.addEventListener("click", function () {
				activeGuestService();
				urlDepartment = 4;
				fetch(`webresources/tasksrest/${urlTaskStatus}/department${urlDepartment}`)
					.then(response => response.json()) //first we get all tasks from database
					.then(json => listTasks(json))
                                        .then(result => clickedtask()) //waiting for clicked task after task is loaded from database 
					.catch(error => (console.log("Fetch crashed due to " + error)));
			});
			foodDep.addEventListener("click", function () {
				activeFoodDep();
				urlDepartment = 5;
				fetch(`webresources/tasksrest/${urlTaskStatus}/department${urlDepartment}`)
					.then(response => response.json()) //first we get all tasks from database
					.then(json => listTasks(json))
                                        .then(result => clickedtask()) //waiting for clicked task after task is loaded from database 
					.catch(error => (console.log("Fetch crashed due to " + error)));
			});
			engineering.addEventListener("click", function () {
				activeEngineering();
				urlDepartment = 6;
				fetch(`webresources/tasksrest/${urlTaskStatus}/department${urlDepartment}`)
					.then(response => response.json()) //first we get all tasks from database
					.then(json => listTasks(json))
                                        .then(result => clickedtask()) //waiting for clicked task after task is loaded from database 
					.catch(error => (console.log("Fetch crashed due to " + error)));
			});
			otherDep.addEventListener("click", function () {
				activeOther();
				urlDepartment = 7;
				fetch(`webresources/tasksrest/${urlTaskStatus}/department${urlDepartment}`)
					.then(response => response.json()) //first we get all tasks from database
					.then(json => listTasks(json))
                                        .then(result => clickedtask()) //waiting for clicked task after task is loaded from database 
					.catch(error => (console.log("Fetch crashed due to " + error)));
			});
		}
	}

	//display task's data in form
	let taskResponse = (task) => {
		console.log(task);
		let taskStatus = task.taskStatus.taskStatusID;
		switch (taskStatus) {
			case 1:
				hideNew();
				break;
			case 2:
				hideInProcess();
				break;
			case 3:
				hideDone();
				break;
			case 4:
				hideCancelled();
				break;
		}

		document.querySelector(".modal").style.display = "block";
		document.getElementById("taskID").innerHTML = task.taskID;
		document.getElementById("dateInput").value = task.dueDate;
		document.getElementById("timeInput").value = task.dueTime;
		document.getElementById("departmentSelect").value = task.department.departmentID;
		document.getElementById("placeSelect").value = task.place.placeID;
		document.getElementById("titleInput").innerHTML = task.title;
		document.getElementById("detailsInput").innerHTML = task.details;
	}


	//recive JSON depends on radio buttons
	$('.float-right input[type=radio]').on('change', function () {
		console.log(this.value);
		urlTaskStatus = this.value;
		if (urlDepartment > 1) {
			url = `webresources/tasksrest/${urlTaskStatus}/department${urlDepartment}`;
		} else {
			url = `webresources/tasksrest/${urlTaskStatus}`;

		}

		fetch(url)
			.then(response => response.json()) //first we get all tasks from database
			.then(json => listTasks(json)) //display response on page
			.then(result => clickedtask()) //waiting for clicked task after task is loaded from database 
			.catch(error => (console.log("Fetch crashed due to " + error)));
	});

	let userDepartmentId;
	let department = function (json) {
		userDepartmentId = parseInt(json);
		urlDepartment = userDepartmentId;
		switch (userDepartmentId) {
			case 1:
				activeAll();
				break;
			case 2:
				activeAll();
				break;
			case 3:
				activeHousekeeping();
				break;
			case 4:
				activeGuestService();
				break;
			case 5:
				activeFoodDep();
				break;
			case 6:
				activeEngineering();
				break;
			case 7:
				activeOther();
				break;
			default:
				console.log("I don't find this department");
				break;

		}
	};

	fetch(url)
		.then(response => response.json()) //first we get all tasks from database
		.then(json => listTasks(json)) //display response on page
		.then(result => clickedtask()) //waiting for clicked task after task is loaded from database 
		.catch(error => (console.log("Fetch crashed due to " + error)));


	/*Set functionality for department aside buttons*/
	let depAll = document.getElementById("AllDepartments");
	let frontOffice = document.getElementById("frontOffice");
	let housekeeping = document.getElementById("housekeeping");
	let guestservices = document.getElementById("guestservice");
	let foodDep = document.getElementById("food");
	let engineering = document.getElementById("engineering");
	let otherDep = document.getElementById("other");

	function activeAll() {
		depAll.classList.remove("disabledDep");
		depAll.className = "button activeDepartment";
		frontOffice.className = "button disabledDep";
		housekeeping.className = "button disabledDep";
		guestservices.className = "button disabledDep";
		foodDep.className = "button disabledDep";
		engineering.className = "button disabledDep";
		otherDep.className = "button disabledDep";
	};

	function activeFrontOffice() {
		depAll.className = "button disabledDep";
		frontOffice.classList.remove("disabledDep");
		frontOffice.className = "button activeDepartment";
		housekeeping.className = "button disabledDep";
		guestservices.className = "button disabledDep";
		foodDep.className = "button disabledDep";
		engineering.className = "button disabledDep";
		otherDep.className = "button disabledDep";
	};

	function activeHousekeeping() {
		depAll.className = "button disabledDep";
		frontOffice.className = "button disabledDep";
		housekeeping.classList.remove("disabledDep");
		housekeeping.className = "button activeDepartment";
		guestservices.className = "button disabledDep";
		foodDep.className = "button disabledDep";
		engineering.className = "button disabledDep";
		otherDep.className = "button disabledDep";
	};

	function activeGuestService() {
		depAll.className = "button disabledDep";
		frontOffice.className = "button disabledDep";
		housekeeping.className = "button disabledDep";
		guestservices.classList.remove("disabledDep");
		guestservices.className = "button activeDepartment";
		foodDep.className = "button disabledDep";
		engineering.className = "button disabledDep";
		otherDep.className = "button disabledDep";
	};

	function activeFoodDep() {
		depAll.className = "button disabledDep";
		frontOffice.className = "button disabledDep";
		housekeeping.className = "button disabledDep";
		guestservices.className = "button disabledDep";
		foodDep.classList.remove("disabledDep");
		foodDep.className = "button activeDepartment";
		engineering.className = "button disabledDep";
		otherDep.className = "button disabledDep";
	};

	function activeEngineering() {
		depAll.className = "button disabledDep";
		frontOffice.className = "button disabledDep";
		housekeeping.className = "button disabledDep";
		guestservices.className = "button disabledDep";
		foodDep.className = "button disabledDep";
		engineering.classList.remove("disabledDep");
		engineering.className = "button activeDepartment";
		otherDep.className = "button disabledDep";
	};

	function activeOther() {
		depAll.className = "button disabledDep";
		frontOffice.className = "button disabledDep";
		housekeeping.className = "button disabledDep";
		guestservices.className = "button disabledDep";
		foodDep.className = "button disabledDep";
		engineering.className = "button disabledDep";
		otherDep.classList.remove("disabledDep");
		otherDep.className = "button activeDepartment";
	}

	//functions for disable or not disable task status buttons
	let a = document.getElementById("newButton");
	let b = document.getElementById("inProcess");
	let c = document.getElementById("cancelled");
	let d = document.getElementById("done");


	function hideNew() {
		a.className = "button notDisabled";
		b.className = "button disabled";
		c.className = "button disabled";
		d.className = "button disabled";
	}

	function hideInProcess() {
		a.className = "button disabled";
		b.className = "button notDisabled";
		c.className = "button disabled";
		d.className = "button disabled";
	}

	function hideCancelled() {
		a.className = "button disabled";
		b.className = "button disabled";
		c.className = "button notDisabled";
		d.className = "button disabled";
	}

	function hideDone() {
		a.className = "button disabled";
		b.className = "button disabled";
		c.className = "button disabled";
		d.className = "button notDisabled";
	}
});