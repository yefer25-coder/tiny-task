const API_URL = "http://localhost:8080/api/tasks";
const taskList = document.getElementById("taskList");
const taskForm = document.getElementById("taskForm");
const titleInput = document.getElementById("titleInput");
const descInput = document.getElementById("descInput");


async function loadTasks() {
  taskList.innerHTML = "";
  const res = await fetch(API_URL);
  const tasks = await res.json();

  tasks.forEach(task => renderTask(task));
}


taskForm.addEventListener("submit", async (e) => {
  e.preventDefault();
  const title = titleInput.value.trim();
  const description = descInput.value.trim();

  if (title.length < 3) {
    alert("Title must be at least 3 characters long.");
    return;
  }

  const res = await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ title, description })
  });

  if (res.ok) {
    const newTask = await res.json();
    renderTask(newTask);
    taskForm.reset();
  } else {
    const err = await res.json();
    alert(err.error || "Error creating task");
  }
});


async function toggleTask(id) {
  const res = await fetch(`${API_URL}/${id}/toggle`, { method: "PUT" });

  if (res.ok) {
    loadTasks();
  } else {
    const err = await res.json();
    alert(err.error || "Error toggling task");
  }
}


async function deleteTask(id) {
  const res = await fetch(`${API_URL}/${id}`, { method: "DELETE" });

  if (res.ok) {
    document.getElementById(`task-${id}`).remove();
  } else {
    const err = await res.json();
    alert(err.error || "Error deleting task");
  }
}


function renderTask(task) {
  const li = document.createElement("li");
  li.className = "list-group-item";
  li.id = `task-${task.id}`;

  li.innerHTML = `
    <div class="task-info ${task.done ? "completed" : ""}">
      <strong>${task.title}</strong><br>
      <small>${task.description || ""}</small>
    </div>
    <div class="task-actions">
      <button class="btn btn-sm btn-warning" onclick="toggleTask(${task.id})">Toggle</button>
      <button class="btn btn-sm btn-danger" onclick="deleteTask(${task.id})">Delete</button>
    </div>
  `;

  taskList.appendChild(li);
}


loadTasks();
