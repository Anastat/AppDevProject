document.addEventListener("DOMContentLoaded", function (event) {
	
  function getCookie(name)
  {
    var re = new RegExp(name + "=([^;]+)");
    var value = re.exec(document.cookie);
    return (value != null) ? unescape(value[1]) : null;
  }        
        
	$('.float-right input[type=radio]').click(function(){
            console.log(this.value);
            urlTaskStatus = this.value;
            const url = `webresources/tasksrest/${urlTaskStatus}/${getCookie("username")}`;
			//Fetch data from server
                        console.log(url);
            fetch(url)
                .then(response => response.json())
                .then(json => listTasks(json))
                .catch(error => (console.log("Fetch crashed due to " + error)));
        });
    
    
	//source of the image depends of taskStatus
	function imgSrc(statusId) {
		let scr;
		if (statusId == 1) {
			scr = "./Icons/Add.png";
		} else if (statusId == 2) {
			scr="./Icons/inProcess.png";
		} else if (statusId==3) {
			scr="./Icons/done.png";
		} else {
			scr = "./Icons/cancelled.png"
		}
		return scr;
	}
	
	//create div with task in it
	function taskTemplate(task) {
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
        console.log(tasks);
    }
    

});
