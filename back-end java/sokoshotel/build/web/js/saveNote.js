document.addEventListener("DOMContentLoaded", function (event) {
	
    const noteInput = document.querySelector(".noteForm");
    const url = "webresources/tasksrest";
	
    let note = {};

    //Reading input from the note form 
    noteInput.addEventListener("input", function () {
		const taskStatus = noteInput.querySelector(".button-group").value;
        const dueDate = noteInput.querySelector("#dateInput").value;
		const dueTime = noteInput.querySelector("#timeInput").value;
		const department = noteInput.querySelector("#departmentSelect").value;
		const title = noteInput.querySelector("#titleInput").value;
		const place = noteInput.querySelector("#placeSelect").value;
        const details = noteInput.querySelector("#detailsInput").value;
        const attachment = noteInput.querySelector("#fileInput").value;
        
		note.taskStatus = 1;
		note.dueDate = dueDate;
		note.dueTime = dueTime;
		note.department = parseInt(department);
		note.title = title;
		note.place = parceInt(place);
		note.details  = details;
		note.attachment = attachment;
		
       console.log(JSON.stringify(note));

    });

    //post the data to server
    const submitButton = document.querySelector("#saveButton");
    submitButton.addEventListener("click", function () {
        const init = {
            method: "POST",
            body: JSON.stringify(note),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        };

        fetch(url, init)
            .then(response => response.json()) 
            .then(json => console.log("Note saved: " + JSON.stringify(json)))
            .catch(error => console.log("Fetch crashed due to " + error));
    });
});
