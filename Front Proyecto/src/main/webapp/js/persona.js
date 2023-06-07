//post nueva persona
formularioagente.addEventListener('input', () => {
	if (arminconombre.value.length > 0
		&& armincocedula.value.length > 0
		&& armincoemail.value.length > 0) {
		armincoenviar.removeAttribute('disabled');
	} else {
		armincoenviar.setAttribute('disabled', 'disabled');
	}
});

// validar campos nueva persona
function validarCampos() {
	// Post a user
	var url = "http://localhost:8081/Juanpi/persona";
	var nombre = '?nombre=' + document.getElementById("arminconombre").value;
	var cedula = '&cedula=' + document.getElementById("armincocedula").value;;
	var email = '&email=' + document.getElementById("armincoemail").value;
	if (armincosexo.checked == true) {
		sexo = sexo + document.getElementById("armincosexo").value;

	} else if (armincosexo2.checked == true) {
		sexo = sexo + document.getElementById("armincosexo2").value;

	}
	
	var comparendo = '&comparendo=' + document.getElementById("armincocomparendo").value;

	var data = {};
	data.nombre = document.getElementById("arminconombre").value;
	data.cedula = document.getElementById("armincocedula").value;
	data.fnacimiento = document.getElementById("armincocampofnacimiento").value;
	if (armincosexo.checked == true) {
		data.sexo = document.getElementById("armincosexo").value;

	} else if (armincosexo2.checked == true) {
		data.sexo = document.getElementById("armincosexo2").value;
	}
	data.fentrinstit = document.getElementById("armincofentradainstit").value;
	data.camporangoactual = document.getElementById("armincocamporangoactual").value;
	data.camponcasosatendidos = document.getElementById("armincocamponcasosatendidos").value;
	var json = JSON.stringify(data);

	var xhr = new XMLHttpRequest();
	xhr.open("POST", url + nombre + cedula + email + comparendo + fnacimiento + sexo + fentrinstit + rangoactual + ncasosatendidos, true);
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	xhr.onload = function() {
		var users = JSON.parse(xhr.responseText);
		if (xhr.readyState == 4 && xhr.status == "201") {
			console.table(users);
		} else {
			console.error(users);
		}
	}
	xhr.send(json);
}

//get 1 persona por id
formulariopersona1.addEventListener('input', () => {
	if (campoid.value.length > 0) {
		enviar1.removeAttribute('disabled');
	} else {
		enviar1.setAttribute('disabled', 'disabled');
	}
});
//valida los campos para 1 persona por id
function validarCampos1() {
	// Get a user
	var url = "http://localhost:8081/Juanapi/persona";
	var xhr = new XMLHttpRequest()
	var id = '/' + document.getElementById("campoid").value;
	xhr.open('GET', url + id, true)
	xhr.onload = function() {
		var user = JSON.parse(xhr.responseText);
		var res = document.querySelector('#res2');
		res.innerHTML = '';
		if (xhr.readyState == 4 && xhr.status == "202") {
			//console.table(user);
			res.innerHTML += `
					<tr>
						<th>${user.id}</th>
						<th>${user.nombre}</th>
						<th>${user.cedula}</th>
						<th>${user.email}</th>
						<th>${user.sexo}</th>	
					</tr>
				`
		} else {
			console.error("Error getting");
		}
	}
	xhr.send(null);
}

