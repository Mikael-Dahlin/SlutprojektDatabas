<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@page import="beans.bearBean, beans.IKEAbean, beans.SCPbean"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css"/>
<title>Dumble Search</title>
</head>
<body>

	<h1>DUMBLE</h1>

	<form action="<%=request.getContextPath()%>/SearchServlet"
		method="post">
		Get from the database: <input type="text" name="searchString">

		<input type="submit" value="Search">
	</form>

	<%
	// Get the results
	ArrayList<Object> result = (ArrayList<Object>) request.getAttribute("searchResult");
	bearBean bear = new bearBean();
	IKEAbean ikea = new IKEAbean();
	SCPbean scp = new SCPbean();
	
	// Check if we have a result
	if (!(result == null)) {
		out.print("<p>You wanted: " + request.getAttribute("searchString") + "</p>");
		out.print("<p>This is what you got:</p>");

		out.print("<ul>");
		
		// Loop through the results and print them out in the list, omits null values.
		for (int i = 0; i < result.size(); i++) {
			out.print("<li>");
			if (result.get(i).getClass().equals(bear.getClass())){
				bear = ((beans.bearBean) result.get(i));
				
				out.print("Found in bear database | ");
				out.print("Character: ");
				out.println(bear.getCharacter());
				out.print("Origin: ");
				out.println(bear.getOrigin());
				
				if ( bear.getCreator() != null ) {
					out.print("Created by: ");
					out.println(bear.getCreator());
				}
				if ( bear.getNotes() != null ) {
					out.print("Note: ");
					out.println(bear.getNotes());					
				}
				
			} else if (result.get(i).getClass().equals(ikea.getClass())){
				ikea = ((beans.IKEAbean) result.get(i));
				
				out.print("Found in IKEA database | ");
				out.print("Name: ");
				out.println(ikea.getItemName());
				out.print("Description: ");
				out.print(ikea.getItemDescription());
				
				if ( ikea.getExtraOne() != null ) {
					out.print(", " + ikea.getExtraOne());
				}
				if ( ikea.getExtraTwo() != null ) {
					out.print(", " + ikea.getExtraTwo());
				}
				if ( ikea.getExtraThree() != null ) {
					out.println(", " + ikea.getExtraThree());
				}
				
			} else if (result.get(i).getClass().equals(scp.getClass())){
				scp = ((beans.SCPbean) result.get(i));
				
				out.print("Found in SCP database | ");
				out.print("SCP: ");
				out.println(scp.getSCP());
				out.print("Title: ");
				out.println(scp.getTitle());
				
				if ( scp.getRating() != null ) {
					out.print("rating: ");
					out.println(scp.getRating());
				}
				if ( scp.getClassification() != null ) {
					out.print("Classification: ");
					out.println(scp.getClassification());
				}
				if ( scp.getType() != null ) {
					out.print("Type: ");
					out.println(scp.getType());
				}
				if ( scp.getRelated_GOI_s() != null ) {
					out.print("Related_GOI_s: ");
					out.println(scp.getRelated_GOI_s());
				}
				if ( scp.getLocation_notes() != null ) {
					out.print("Location_Notes: ");
					out.println(scp.getLocation_notes());
				}
				if ( scp.getAuthor() != null ) {
					out.print("Author: ");
					out.println(scp.getAuthor());
				}
				if ( scp.getLeaked_info() != null ) {
					out.print("Leaked_info: ");
					out.println(scp.getLeaked_info());
				}
				if ( scp.getHumanoid_or_structure() != null ) {
					out.print("Humanoid_or_Structure: ");
					out.println(scp.getHumanoid_or_structure());
				}
				if ( scp.getAnimal_computer_or_extradimensional() != null ) {
					out.print("Animal_Computer_or_Extradimensional: ");
					out.println(scp.getAnimal_computer_or_extradimensional());
				}
				if ( scp.getAutonomous_or_cognitohazard() != null ) {
					out.print("Autonomous_or_Cognitohazard: ");
					out.println(scp.getAutonomous_or_cognitohazard());
				}
				if ( scp.getArtifact_location_or_sentient() != null ) {
					out.print("Artifact_Location_or_Sentient: ");
					out.println(scp.getArtifact_location_or_sentient());
				}
				if ( scp.getSummary() != null ) {
					out.print("Summary: ");
					out.println(scp.getSummary());
				}
				if ( scp.getGender() != null ) {
					out.print("Gender: ");
					out.println(scp.getGender());
				}
				if ( scp.getNone() != null ) {
					out.print("None: ");
					out.println(scp.getNone());
				}
				if ( scp.getEthnicity_origins() != null ) {
					out.print("Ethnicity_Origins: ");
					out.println(scp.getEthnicity_origins());
				}
				
			} else {
				out.print("error, no class found!");
			}
			out.print("</li>");
		}
		if (result.size() == 0) {
			out.print("No search results found");
		}

		out.print("</ul>");
	}
	%>

</body>
</html>