//In script: 
// - displaying tasks from database on page; 
// - displaying data of clicked task in form 

document.addEventListener("DOMContentLoaded", function (event) {
    
        function getCookie(name)
        {
          var re = new RegExp(name + "=([^;]+)");
          var value = re.exec(document.cookie);
          return (value != null) ? unescape(value[1]) : null;
        }    

	let urlTaskStatus = $('.float-right input[type=radio]:checked').val();
	let url = `webresources/tasksrest/${urlTaskStatus}/${getCookie("username")}`;


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

	let listTasks = function (tasks) {
		const tasksELement = document.getElementById("tasks-list");
		const imgElement = document.querySelector("img");
		tasksELement.innerHTML = `${tasks.map(taskTemplate).join("")}`;
	};


	fetch(url)
		.then(response => response.json()) //first we get all tasks from database
		.then(json => listTasks(json)) //display response on page
		.then(result => clickedtask()) //waiting for clicked task after task is loaded from database 
		.catch(error => (console.log("Fetch crashed due to " + error)));


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
	//recive JSON depends on radio buttons
	$('.float-right input[type=radio]').on('change', function () {
		console.log(this.value);
		urlTaskStatus = this.value;
                url = `webresources/tasksrest/${urlTaskStatus}/${getCookie("username")}`;
                
                fetch(url)
		.then(response => response.json()) //first we get all tasks from database
		.then(json => listTasks(json)) //display response on page
		.then(result => clickedtask()) //waiting for clicked task after task is loaded from database 
		.catch(error => (console.log("Fetch crashed due to " + error)));
	});
      
});