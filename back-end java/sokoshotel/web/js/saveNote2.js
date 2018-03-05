//In script:
//- functionality for saving task in database
//- eventListener for task status buttons in form
//- functions for disable/notDisable task status buttons

document.addEventListener("DOMContentLoaded", function () {
	const noteInput = document.querySelector(".noteForm");
	const url = "http://localhost:8080/sokoshotel/webresources/tasksrest/addNewNote";
        //const url = "http://localhost:46419/sokoshotel/webresources/tasksrest/addNewNote";
	let note = {};
	let departmentid = {};
	let placeid = {};
	let taskstatusID = {};

        noteInput.addEventListener("input",updatejson);
        noteInput.addEventListener("click",updatejson);
	//Reading input from the note form 
	function updatejson() {
		const taskStatus = noteInput.querySelector("button.notDisabled").value;
		const dueDate = noteInput.querySelector("#dateInput").value;
		const dueTime = noteInput.querySelector("#timeInput").value;
		const department = noteInput.querySelector("#departmentSelect").value;
		const title = noteInput.querySelector("#titleInput").value;
		const place = noteInput.querySelector("#placeSelect").value;
		const details = noteInput.querySelector("#detailsInput").value;
		const attachment = noteInput.querySelector("#fileInput").value;

		departmentid.departmentID = parseInt(department);
		placeid.placeID = parseInt(place);
		taskstatusID.taskStatusID = taskStatus;
		note.taskStatus = taskstatusID;
		note.dueDate = dueDate;
		note.dueTime = dueTime;
		note.department = departmentid;
		note.title = title;
		note.place = placeid;
		note.details = details;
		note.attachment = attachment;

	};



	//post the data to server
	var modal = document.getElementById('myModal');
	const submitButton = document.querySelector("#saveButton");
	submitButton.addEventListener("click", function () {
                console.log(JSON.stringify(note));
		note = JSON.stringify(note);
		const init = {
			method: "POST",
			body: note,
			headers: {
				"Content-type": "application/json; charset=UTF-8"
			}
		};
                if(document.getElementById("taskID").value !== ""){
                    const urll = "http://localhost:8080/sokoshotel/webresources/tasksrest/editNote/" + document.getElementById("taskID").value;
                    fetch(urll, init)
			.then(response => response.json())
			.then(json => console.log("Note saved: " + JSON.stringify(json)))
			.catch(error => console.log("Fetch crashed due to " + error));
                }else{
		fetch(url, init)
			.then(response => response.json())
			.then(json => console.log("Note saved: " + JSON.stringify(json)))
			.catch(error => console.log("Fetch crashed due to " + error));
		
	}});

	//Task status buttons
	let a = document.getElementById("newButton");
	let b = document.getElementById("inProcess");
	let c = document.getElementById("cancelled");
	let d = document.getElementById("done");

	//Add eventListener for task status buttons
	a.addEventListener("click", function () {
		hideNew(); //call from inside the function because, hideNew func are needed later
	});
	b.addEventListener("click", function () {
		hideInProcess(); //hideInProcess func are needed in another script
	});
	c.addEventListener("click", function () {
		hideCancelled();
	});
	d.addEventListener("click", function () {
		hideDone();
	});
	//functions for disable or not disable task status buttons
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
	//export { hideNew, hideInProcess, hideCancelled, hideDone }

});
