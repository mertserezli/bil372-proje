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
			<input type="radio" name="searchType" onclick=\ "getAnswer('users') value="users" checked="checked" /> Users
		</label>
		<label>
			<input type="radio" name="searchType" onclick=\ "getAnswer('projects') value="projects" /> Projects
		</label>
		<label>
			<input type="radio" name="searchType" onclick=\ "getAnswer('companies') value="companies" /> Companies
		</label>
		<br>
		<br>
		<label>User Search Options</label>
		<br>
		<label>
		<input type="radio" name="userCategory" onclick=\ "getAnswer('username')" value="username" checked="checked" /> Username
		</label>
		<br>
		<label>
		<input type="radio" name="userCategory" onclick=\ "getAnswer('jobtitle')" value="jobtitle" /> Job Title
		</label>
		<br>
		<label>
		<input type="radio" name="userCategory" onclick=\ "getAnswer('qualifications')" value="qualifications" /> Qualifications
		</label>
		<br>
		<br>
		<label>Project Search Options</label>
		<br>
		<label>
		<input type="radio" name="projectCategory" onclick=\ "getAnswer('title')" value="title" checked="checked"/> Title
		</label>
		<br>
		<label>
		<input type="radio" name="projectCategory" onclick=\ "getAnswer('description')" value="description" /> Description
		</label>
		<br>
		<label>
		<input type="radio" name="projectCategory" onclick=\ "getAnswer('tags')" value="tags" /> Tags
		</label>
		<br>
		<br>
		<label>Company Search Options</label>
		<br>
		<label>
		<input type="radio" name="companyCategory" onclick=\ "getAnswer('name')" value="name" checked="checked"/> Name
		</label>
		<br>
		<label>
		<input type="radio" name="companyCategory" onclick=\ "getAnswer('description')" value="description" /> Description
		</label>
		<br>
		<br>
		<button type="submit" class="button button-block">Search</button>
	</form>
</body>
</html>