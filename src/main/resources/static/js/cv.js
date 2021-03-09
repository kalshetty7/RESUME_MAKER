window.onload = function(event){
	let inputs = document.querySelectorAll('input[type="text"]');
	inputs.forEach(i=>{i.size = 55;i.className="form-control";})
}

function sendToServer(e,url,targetClass){
	e.preventDefault();
	  $.ajax({
	  async: true,
	  type: 'POST',
	  url: url,
	  data: $('form').serialize(),
	  success: function(response) {
	    $('.'+targetClass).html(response);
	  },
	  error: function() {
	    alert('error');
	  }
	});
}

function deleteEmployee(){
	let ids = new Array();
	let al = document.querySelectorAll('.dlp');
	al.forEach((i)=>{
		if(i.checked)
			ids.push(i.getAttribute('eid'));
	})
	let form = document.createElement('form');
	form.action="delete"
	form.method="POST"
	form.hidden = true;
	let input = document.createElement('input')
	input.setAttribute("type", "text");
	input.setAttribute("name", "ids"); 
	input.setAttribute("value", ids); 
	form.appendChild(input)
	document.querySelector('body').appendChild(form)
	form.submit();
}

function deleteField(f){
	let ids = new Array();
	let al = document.querySelectorAll('.comp_del');
	al.forEach((i)=>{
		if(i.checked)
			ids.push(i.getAttribute('cid'));
	})
	let ids_input = getInputField("ids",ids);
	let field_input = getInputField("field",f);
	let form = document.querySelector('form')
	form.action="deleteField"
		form.appendChild(field_input)
		form.appendChild(ids_input)
		form.submit();
}

function duplicateField(f){
	let ids = new Array();
	let al = document.querySelectorAll('.comp_del');
	al.forEach((i)=>{
		if(i.checked)
			ids.push(i.getAttribute('cid'));
	})
	let ids_input = getInputField("ids",ids);
	let field_input = getInputField("field",f);
	let form = document.querySelector('form')
	form.action="duplicateField"
		form.appendChild(field_input)
		form.appendChild(ids_input)
		form.submit();
}


function loadDefaultData(){
	let form = document.querySelector('form');
	form.action="loadDefaultData"
		form.submit();
}

function restore(){
	let form = document.querySelector('form');
	form.action="restore"
		form.submit();
}

function backup(){
	let form = document.querySelector('form');
	form.action="backup"
		form.submit();
}

function modifySkills(action){
	let actionField = getInputField("action",action);
	let form = document.querySelector('form')
	form.appendChild(actionField)
	form.action="modifySkills"
		if(action=='delete'){
			let ids = new Array();
			let al = document.querySelectorAll('.skills_del');
			al.forEach((i)=>{
				if(i.checked)
					ids.push(i.getAttribute('sid'));
			})
			let indices = getInputField("indices",ids);
			form.appendChild(indices)
		}
		form.submit();
}

function modifyProject(cid,pid,action){
	let companyIndex = getInputField("companyIndex",cid);
	let projectIndex = getInputField("projectIndex",pid);
	let actionField = getInputField("action",action);
	let form = document.querySelector('form')
	form.appendChild(companyIndex)
	form.appendChild(projectIndex)
	form.appendChild(actionField)
	form.action="modifyProject"
	form.submit();
}

function getInputField(name,value){
	let input = document.createElement('input')
	input.setAttribute("type", "text");
	input.setAttribute("name", name); 
	input.setAttribute("value", value); 
	return input
}

function duplicateEmployee(){
	let ids = new Array();
	let al = document.querySelectorAll('.dlp');
	al.forEach((i)=>{
		if(i.checked)
			ids.push(i.getAttribute('eid'));
	})
	let form = document.createElement('form');
	form.action="duplicate"
	form.method="POST"
	form.hidden = true;
	let input = document.createElement('input')
	input.setAttribute("type", "text");
	input.setAttribute("name", "ids"); 
	input.setAttribute("value", ids); 
	form.appendChild(input)
	document.querySelector('body').appendChild(form)
	form.submit();
}

function addMore(ci,f){
	let field_input = getInputField("field",f);
	let form = document.querySelector('form')
	form.action="addMore"
		form.appendChild(field_input)
		if(ci!=null){
			let companyIndex = getInputField("companyIndex",ci);
			form.appendChild(companyIndex)
		}
		form.submit();
}

function checkAll(targetClass){
	let al = document.querySelectorAll('.'+targetClass);
	al.forEach((i)=>{
		i.click();
	})
}

function orderBy(e,field,sort,targetClass){
	let form = document.createElement('form');
	form.name="sorter";
	form.id='sorter'
	let field_input = document.createElement('input');
	field_input.setAttribute("type", "text");
	field_input.setAttribute("value", field);
	field_input.setAttribute("name", "field");
	
	let order_input = document.createElement('input');
	order_input.setAttribute("type", "text");
	order_input.setAttribute("value", sort);
	order_input.setAttribute("name", "sort");
	
	form.appendChild(field_input)
	form.appendChild(order_input)
	
	let url = 'sorter';
	$.ajax({
		  type: 'POST',
		  url: url,
		  data: $(form).serialize(),
		  success: function(response) {
		    $('.'+targetClass).html(response);
		  },
		  error: function() {
		    alert('error');
		  }
		});
}