<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Users and Projects</title>
<link rel="stylesheet" href="css/search.css">
</head>

<body>
	<form action="SearchServlet" method="get">
		<input type="text" autocomplete="off" name="searchBar" />
		<label>
			<span class="req"></span>
		</label>
		<input type="radio" id="usersRB" name="searchType" onclick=\ "getAnswer('users') value="users" checked="checked" />
		Users
		<input type="radio" id="projectsRB" name="searchType" onclick=\ "getAnswer('projects') value="projects" />
		Projects
		<input type="radio" id="companiesRB" name="searchType" onclick=\ "getAnswer('companies') value="companies" />
		Companies
		<button type="submit" class="button button-block">Search</button>
	</form>
</body>
</html>