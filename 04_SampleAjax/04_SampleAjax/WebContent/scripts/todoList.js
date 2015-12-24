$(document).ready(function() {
	"use strict";
	var ENDPOINT = "http://localhost:3000/tasks";
	function taskEndpoint(taskId) {
		return ENDPOINT + "/" + taskId;
	}

	function showPanel(panelName) {
		var ALL_PANELS = ["emptyPanel", "readPanel", "updatePanel", "createPanel"];
		_.forEach(ALL_PANELS, function(nextValue) {
			$("#"+nextValue).hide();
		});
		$("#"+panelName).show();
	}

	function listTasks() {
		return $.ajax(ENDPOINT, {
			method: "GET",
			dataType: "json",
		});
	}
	function readTask(taskId) {
		return $.ajax(taskEndpoint(taskId), {
			method: "GET",
			dataType: "json"
		});
	}
	
	function showTaskView(task) {
		$("#readPanel .task-title").text(task.title);
		$("#readPanel .task-description").text(task.description);
		showPanel("readPanel");
	}
	
	function reloadTasks() {
		listTasks().then(function(response) {
			function addTaskToList(task) {
				var newItem = $("<li />");
				newItem.text(task.title);
				newItem.addClass("list-group-item");
				newItem.attr("data-task-id", task.id);
				$("#tasksList").append(newItem);
			}
			$("#tasksList").html("");
			_.forEach(response, addTaskToList);
		});
	}

	
	function deleteTask(taskId){
		
		$.ajax(taskEndpoint(taskId), {
			method: "DELETE"
		}).done(function( data ) {
			reloadTasks();
		});
	}
	
	function createTask(title,description){
		 
		 var task = {
					title: title,
					description: description
				};
		$.ajax(ENDPOINT, {
			method: "POST",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(task),
			dataType: "json"
		}).done(function( data ) {
			reloadTasks();
		});
	 }
	
	function attachHandlers() {
		var taskId = "";
		$(document).on("click", "#tasksList [data-task-id]", function() {
			taskId = $(this).attr("data-task-id");
			readTask(taskId).then(showTaskView);
		});
		
		$(".task-action-cancel").click(function() {
			showPanel("emptyPanel");
		});
		
		
		$(document).on('click', '.task-action-remove', function(){
			deleteTask(taskId);
			$('#readPanel').hide();
		});
		
		$(document).on('click', '#addTaskButton', function(){
			showPanel("createPanel");
		});	
		
		$(document).on('click', '.task-action-ok', function(){
			
			if($(this).text() == "OK"){
				var title = $($('.form-control[name=title]')[1]).val();
				var description = $($('.form-control[name=description]')[1]).val();
				createTask(title,description);
				$("#createPanel").hide();
			}
		});
		
	}
	attachHandlers();
	reloadTasks();

});
