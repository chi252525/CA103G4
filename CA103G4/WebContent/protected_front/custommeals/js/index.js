/* Custom Dragula JS */
dragula([
  document.getElementById("ingdt_1"),
  document.getElementById("ingdt_2"),
  document.getElementById("ingdt_3"),
  document.getElementById("ingdt_4"),
  document.getElementById("ingdt_5"),
  document.getElementById("ingdt_6"),
  document.getElementById("combination")
]);
removeOnSpill: false
  .on("drag", function(el) {
    el.className.replace("ex-moved", "");
  })
  .on("drop", function(el) {
    el.className += "ex-moved";
  })
  .on("over", function(el, container) {
    container.className += "ex-over";
  })
  .on("out", function(el, container) {
    container.className.replace("ex-over", "");
  });

/* Vanilla JS to add a new task */
function addTask() {
  /* Get task text from input */
  var inputTask = document.getElementById("taskText").value;
  /* Add task to the 'To Do' column */
  document.getElementById("to-do").innerHTML +=
    "<li class='task'><p>" + inputTask + "</p></li>";
  /* Clear task text from input after adding task */
  document.getElementById("taskText").value = "";
}

/* Vanilla JS to delete tasks in 'Trash' column */
function emptyTrash() {
  /* Clear tasks from 'Trash' column */
  document.getElementById("combination").innerHTML = "";
}

function conFirmCombination() {
  document.getElementById("confirm").innerHTML = "";
}