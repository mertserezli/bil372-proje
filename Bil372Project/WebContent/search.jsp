<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Search Users and Projects</title>
</head>

<body>
	<form action="SearchServlet" method="get">
		<input type="text" autocomplete="off" name="searchBar"/>
       	<label><span class="req"></span></label>
       		<ul class="tab-group">
				<label for="users">Users</label>
       			<input type="radio" id="usersRB" name="searchType" onclick = \"getAnswer('users') value="users" checked="checked"/>
       			<label for="projects">Projects</label>
    	    	<input type="radio" id="projectsRB" name="searchType" onclick = \"getAnswer('projects') value="projects"/>
	    	</ul>
	 	   	<button type="submit" class="button button-block">Search</button>
	</form>
</body>
</html>