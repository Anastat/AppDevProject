document.addEventListener("DOMContentLoaded", function (event) {
	
    const noteInput = document.querySelector(".noteForm");
    //const url = "http://localhost:8080/sokoshotel/webresources/tasksrest/addNewNote";
    
    
    let note = {};
    let departmentid = {};
    let placeid = {};
    let taskstatusID = {};

    //Reading input from the note form 
    noteInput.addEventListener("input", function () {
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
		note.details  = details;
		note.attachment = attachment;
		
       console.log(JSON.stringify(note));
    });

    //post the data to server
    var modal = document.getElementById('myModal');
    const submitButton = document.querySelector("#saveButton");
    submitButton.addEventListener("click", function () {
        note = JSON.stringify(note);
        const init = {
            method: "POST",
            body: note,
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        };

        fetch(url, init)
            .then(response => response.json()) 
            .then(json => console.log("Note saved: " + JSON.stringify(json)))
            .catch(error => console.log("Fetch crashed due to " + error));
    //console.log(init);
    });
});
