<%@ include file="header.jsp"%>

<body>
	<form:form action="save" modelAttribute="employee">
		<form:hidden path="id" />
		<table>
			<tr>
				<td>Resume Name :</td>
				<td><form:input path="resumeName" /></td>
			</tr>
			<tr>
				<td>Full Name :</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>Address :
				<td><form:input path="address" /></td>
			</tr>
			<tr>
				<td>Email :</td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td>Mobile :</td>
				<td><form:input path="mobile" /></td>
			</tr>
			<tr>
				<td>DOB :</td>
				<td><form:input path="dob" /></td>
			</tr>
			<tr>
				<td>Languages Known :</td>
				<td><form:input path="languagesKnown" /></td>
			</tr>
			<tr>
				<td>Summary :</td>
				<td><form:textarea
						cssStyle="margin: 0px; height: 133px; width: 753px;"
						path="summary" /></td>
			</tr>
			<tr>
				<td>Hobbies :</td>
				<td><form:textarea
						cssStyle="margin: 0px; height: 133px; width: 753px;"
						path="hobbies" /></td>
			</tr>
		</table>


		<%-- skill sets --%>

		<div>
			Skills : <input type="button" value="Add More"
				onclick="modifySkills('add')" />
		</div>
		<div class="skills">
			<c:if test="${not empty employee.skills}">
				<table>
					<tr>
						<td>Sr.No.</td>
						<td>Name</td>
						<td>Value</td>
						<td><input type="button" value="Delete"
							onclick="modifySkills('delete')" /><br>
						<input onclick="checkAll('skills_del')" type="checkbox" /></td>
					</tr>
					<c:forEach items="${employee.skills}" var="s" varStatus="counter">
						<tr>
							<td>${counter.count}</td>
							<td><form:hidden path="skills[${counter.index}].id" /> <form:input
									path="skills[${counter.index}].name" /></td>
							<td><form:input path="skills[${counter.index}].value" /></td>
							<td><input sid="${counter.index}" class="skills_del"
								type="checkbox" /></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>

		<%-- skill sets --%>


		<div>
			Companies : <input type="button" value="Add More"
				onclick="addMore(null,'companies')" />
		</div>
		<div class="companies">
			<c:if test="${not empty employee.companies}">
				<table>
					<tr>
						<td>Sr. No.</td>
						<td colspan="2"></td>
						<td><input type="button" value="Delete"
							onclick="deleteField('companies')" />&nbsp;&nbsp;/&nbsp;&nbsp; <input
							type="button" value="Duplicate"
							onclick="duplicateField('companies')" /><br /> <input
							onclick="checkAll('comp_del')" type="checkbox" /></td>
					</tr>
					<c:forEach items="${employee.companies}" var="c"
						varStatus="counter">
						<tr>
							<td rowspan="5">${counter.count }<form:hidden
									path="companies[${counter.index}].id" /></td>
							<td>Company Name:</td>
							<td><form:input path="companies[${counter.index}].name" /></td>
							<td rowspan="5"><input type="checkbox" class="comp_del"
								cid="${counter.index}" /></td>
						</tr>
						<tr>
							<td>Job Profile :</td>
							<td><form:input
									path="companies[${counter.index}].jobProfile" /></td>
						</tr>
						<tr>
							<td>Joining Date :</td>
							<td><form:input
									path="companies[${counter.index}].startDateString" /></td>
						</tr>
						<tr>
							<td>Last Date :</td>
							<td><form:input
									path="companies[${counter.index}].endDateString" /></td>
						</tr>
						<tr>
							<td>Address :</td>
							<td><form:input path="companies[${counter.index}].address" /></td>
						</tr>
						<tr>
							<td colspan="4" style="text-align: left;">Projects : <input
								type="button" value="Add More"
								onclick="addMore(${counter.index},'projects')" /></td>
						</tr>
						<%-- projects --%>
						<c:if test="${not empty c.projects}">
							<c:forEach items="${c.projects}" var="p" varStatus="pcount">
								<tr>
									<td rowspan="8">${counter.count}.${pcount.count }<form:hidden
											path="companies[${counter.index}].projects[${pcount.index}].id" /></td>
									<td>Project Name :</td>
									<td><form:input
											path="companies[${counter.index}].projects[${pcount.index}].name" /></td>
									<td rowspan="8"><input type="button" value="Delete"
										onclick="modifyProject(${counter.index},${pcount.index},'delete')" />&nbsp;&nbsp;/&nbsp;&nbsp;
										<input type="button" value="Duplicate"
										onclick="modifyProject(${counter.index},${pcount.index},'duplicate')" /><br /></td>
								</tr>
								<tr>
									<td>Client Details :</td>
									<td><form:input
											path="companies[${counter.index}].projects[${pcount.index}].clientDetails" /></td>
								</tr>
								<tr>
									<td>Team Size :</td>
									<td><form:input
											path="companies[${counter.index}].projects[${pcount.index}].teamSize" /></td>
								</tr>
								<tr>
									<td>Project Start Date :</td>
									<td><form:input
											path="companies[${counter.index}].projects[${pcount.index}].startDateString" /></td>
								</tr>
								<tr>
									<td>Project End Date :</td>
									<td><form:input
											path="companies[${counter.index}].projects[${pcount.index}].endDateString" /></td>
								</tr>
								<tr>
									<td>Tools :</td>
									<td><form:textarea
									cssStyle="margin: 0px; height: 133px; width: 753px;"
											path="companies[${counter.index}].projects[${pcount.index}].tools" /></td>
								</tr>
								<tr>
									<td>Summary :</td>
									<td><form:textarea
											cssStyle="margin: 0px; height: 133px; width: 753px;"
											path="companies[${counter.index}].projects[${pcount.index}].summary" /></td>
								</tr>
								<tr>
									<td>Responsiblities :</td>
									<td><form:textarea
											cssStyle="margin: 0px; height: 133px; width: 753px;"
											path="companies[${counter.index}].projects[${pcount.index}].rolesAndResponsibilities" /></td>
								</tr>
							</c:forEach>
						</c:if>
						<%--projects --%>
					</c:forEach>
				</table>
			</c:if>
		</div>


		<input type="submit" value="Save" style="width: 21%" />
	</form:form>

	<div>
		<input type="button" value="Load Default Data" onclick="loadDefaultData()" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="Backup All Records" onclick="backup()" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="Restore Records" onclick="restore()" />
	</div>

	<c:if test="${not empty employees}">
		<table>
			<tr>
				<td>Sr.No.</td>
				<td>Name</td>
				<td>Edit</td>
				<td>Other Details</td>
				<td>CV</td>
				<td><input type="button" onclick="deleteEmployee()" value="Delete" />&nbsp;&nbsp;/&nbsp;&nbsp;
					<input type="button" onclick="duplicateEmployee()" value="Duplicate" />&nbsp;&nbsp;<br/><input
					onclick="checkAll('dlp')" type="checkbox" /></td>
			</tr>
			<c:forEach items="${employees}" var="e" varStatus="counter">
				<tr>
					<td>${counter.count}</td>
					<td>${e.name}</td>
					<td><a href="${pageContext.request.contextPath}/edit/${e.id }">${e.resumeName }</a></td>
					<td><a
						href="${pageContext.request.contextPath}/otherDetails/${e.id }"
						target="_blank">Other Details</a></td>
						<td><a target="_blank" href="${pageContext.request.contextPath}/cv/${e.id }">CV</a></td>
					<td><input type="checkbox" class="dlp" eid="${e.id }" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>