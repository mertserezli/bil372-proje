<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Search Users and Projects</title>
</head>

<body>
	<div class="form">
		<input type="text" autocomplete="off" name="searchBar"/>
		<div class="top-row">
			<div class="tab-group">      
				<div id="search">
            		<div class="field-wrap">
              		<label>
	               		<span class="req"></span>
             		</label>
        		</div>
        	</div>
        </div>
        <div class="field-wrap">
        	<form action="SearchServlet" method="get">
				<ul class="tab-group">
					<label for="users">Users</label>
        			<input type="radio" id="usersRB" name="searchType" onclick = \"getAnswer('users') value="users" checked="checked"/>
        			<label for="projects">Projects</label>
    		    	<input type="radio" id="projectsRB" name="searchType" onclick = \"getAnswer('projects') value="projects"/>
	    		</ul>
	 	   		<button type="submit" class="button button-block">Search</button>
	    	</form>
    	</div>
	</div>
	
	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script  src="js/index.js"></script>
    
</body>
</html>