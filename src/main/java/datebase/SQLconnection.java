package datebase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.IKEAbean;
import beans.SCPbean;
import beans.bearBean;

/*
 * Class that handles the SQL queries.
 */
public class SQLconnection {

	static Connection connBear = null;
	static Connection connIKEA = null;
	static Connection connSCP = null;
	static PreparedStatement stmt = null;
	static ResultSet rs = null;

	/*
	 * Method for setting up a connection to the databases.
	 */
	public static boolean connectSQL() {

		try {

			// Driver setup.
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (Exception ex) {
			System.out.println("Exception Driver " + ex.getMessage());
			return false;
		}

		try {
			// Connection to the databases.
			connBear = DriverManager.getConnection("jdbc:mysql://localhost:3306/dumble_bearbase", DatabaseLogin.getuName(),
					DatabaseLogin.getuPass());
			connIKEA = DriverManager.getConnection("jdbc:mysql://localhost:3306/dumble_ikeabase", DatabaseLogin.getuName(),
					DatabaseLogin.getuPass());
			connSCP = DriverManager.getConnection("jdbc:mysql://localhost:3306/dumble_scpbase", DatabaseLogin.getuName(),
					DatabaseLogin.getuPass());

			return true;

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}

	}

	/*
	 * Main method for handling the search string and calling SQL queries.
	 */
	public static ArrayList<Object> stateSQL(String search, ArrayList<Object> searchResult) {

		try {
			// Checks if the search string is empty.
			if (search.isBlank()) {
				return null;
			}
			// Trims excess characters from the beginning and end of the string.
			search = search.trim();
			
			// Puts the search string in an array.
			ArrayList<String> searchArray = new ArrayList<String>();
			searchArray.add(search);
			
			// Adds secondary search strings to the array.
			String[] secondSearch = search.split(" ");
			if( secondSearch.length > 1 ) {
				for (int i = 0; i < secondSearch.length; i++) {
					if (secondSearch[i].length() > 2 && !searchArray.contains(secondSearch[i])) {
						searchArray.add(secondSearch[i]);
					}
				}
			}
			
			// Iterate through the secondary search strings (primary if there is no secondary).
			int length = searchArray.size();
			for (int i = 0; i < length; i++) {
				if (!searchArray.get(i).contains(" ")) {
					int wordLength = searchArray.get(i).length();
					for (int j = 1; j < wordLength-2; j++) {
						for (int k = 0; k <= j; k++) {
							String substring = searchArray.get(i).substring(k, wordLength-j+k);
							if (!searchArray.contains(substring)){
								searchArray.add(substring);
							}
						}
					}
				}
			}

			// Limits the search to the first 150 words found. (Should be plenty for most normal search strings)
			int searchLimit = 150;
			if (searchArray.size() < searchLimit) {
				searchLimit = searchArray.size();
			}
			
			// Runs the search queries for the strings.
			for(int i = 0; i < searchLimit; i++) {
				String searchString = "%" + searchArray.get(i) + "%";
				
				searchResult = bearDatabaseSQL(searchString, searchResult);
				searchResult = ikeaDatabaseSQL(searchString, searchResult);
				searchResult = scpDatabaseSQL(searchString, searchResult);
			}

			// Close connections.
			connBear.close();
			connIKEA.close();
			connSCP.close();

			return searchResult;

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return null;

	}

	/*
	 * Method for sending query to the scp database.
	 */
	private static ArrayList<Object> scpDatabaseSQL(String searchString, ArrayList<Object> searchResult) {

		try {
			ArrayList<Integer> keys = new ArrayList<Integer>();
			String requestQuery = "SELECT `scp_id`, `SCP`, `Title`, `rating`, `Classification`, `Type`, `Related_GOI_s`, `Location_Notes`, `Author`, `Leaked_info`, `Humanoid_or_Structure`, `Animal_Computer_or_Extradimensional`, `Autonomous_or_Cognitohazard`, `Artifact_Location_or_Sentient`, `Summary`, `Gender`, `None`, `Ethnicity_Origins` FROM `masterscplist` WHERE `SCP` LIKE ? OR `Title` LIKE ? OR `rating` LIKE ? OR `Classification` LIKE ? OR `Type` LIKE ? OR `Related_GOI_s` LIKE ? OR `Location_Notes` LIKE ? OR `Author` LIKE ? OR `Leaked_info` LIKE ? OR `Humanoid_or_Structure` LIKE ? OR `Animal_Computer_or_Extradimensional` LIKE ? OR `Autonomous_or_Cognitohazard` LIKE ? OR `Artifact_Location_or_Sentient` LIKE ? OR `Summary` LIKE ? OR `Gender` LIKE ? OR `None` LIKE ? OR `Ethnicity_Origins`";
			
			stmt = connSCP.prepareStatement(requestQuery);
			stmt.setString(1, searchString);
			stmt.setString(2, searchString);
			stmt.setString(3, searchString);
			stmt.setString(4, searchString);
			stmt.setString(5, searchString);
			stmt.setString(6, searchString);
			stmt.setString(7, searchString);
			stmt.setString(8, searchString);
			stmt.setString(9, searchString);
			stmt.setString(10, searchString);
			stmt.setString(11, searchString);
			stmt.setString(12, searchString);
			stmt.setString(13, searchString);
			stmt.setString(14, searchString);
			stmt.setString(15, searchString);
			stmt.setString(16, searchString);

			rs = stmt.executeQuery();
			
			searchResult.forEach(bean -> {
				if(bean.getClass().equals(beans.SCPbean.class)) { 
				keys.add(((beans.SCPbean) bean).getKey());
				}
			});
			
			while (rs.next()) {
				
				if(checkNew(keys, rs.getInt("scp_id"))) {	
					SCPbean scp = new SCPbean();
				
					scp.setKey(rs.getInt("scp_id"));
					scp.setSCP(rs.getString("SCP"));
					scp.setTitle(rs.getString("Title"));
					scp.setRating(rs.getString("rating"));
					scp.setClassification(rs.getString("Classification"));
					scp.setType(rs.getString("Type"));
					scp.setRelated_GOI_s(rs.getString("Related_GOI_s"));
					scp.setLocation_notes(rs.getString("Location_Notes"));
					scp.setLeaked_info(rs.getString("Leaked_info"));
					scp.setHumanoid_or_structure(rs.getString("Humanoid_or_Structure"));
					scp.setAnimal_computer_or_extradimensional(rs.getString("Animal_Computer_or_Extradimensional"));
					scp.setAutonomous_or_cognitohazard(rs.getString("Autonomous_or_Cognitohazard"));
					scp.setArtifact_location_or_sentient(rs.getString("Artifact_Location_or_Sentient"));
					scp.setSummary(rs.getString("Summary"));
					scp.setGender(rs.getString("Gender"));
					scp.setNone(rs.getString("None"));
					scp.setEthnicity_origins(rs.getString("Ethnicity_Origins"));
					
					searchResult.add(scp);
				}
			}
			rs.close();
			connSCP.endRequest();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return searchResult;
	}
	
	/*
	 * Method for sending query to the ikea database.
	 */
	private static ArrayList<Object> ikeaDatabaseSQL(String searchString, ArrayList<Object> searchResult) {

		try {
			ArrayList<Integer> keys = new ArrayList<Integer>();
			String requestQuery = "SELECT `item_id`, `name`, `description`, `Column_3`, `Column_4`, `Column_5` FROM `ikea_names` WHERE `name` LIKE ? OR `description` LIKE ? OR `Column_3` LIKE ? OR `Column_4` LIKE ? OR `Column_5` LIKE ?";

			stmt = connIKEA.prepareStatement(requestQuery);

			stmt.setString(1, searchString);
			stmt.setString(2, searchString);
			stmt.setString(3, searchString);
			stmt.setString(4, searchString);
			stmt.setString(5, searchString);

			rs = stmt.executeQuery();
			
			searchResult.forEach(bean -> {
				if(bean.getClass().equals(beans.IKEAbean.class)) { 
				keys.add(((beans.IKEAbean) bean).getKey());
				}
			});
			
			while (rs.next()) {
				
				if(checkNew(keys, rs.getInt("item_id"))) {	
					IKEAbean ikea = new IKEAbean();
				
					ikea.setKey(rs.getInt("item_id"));
					ikea.setItemName(rs.getString("name"));
					ikea.setItemDescription(rs.getString("description"));
					ikea.setExtraOne(rs.getString("Column_3"));
					ikea.setExtraTwo(rs.getString("Column_4"));
					ikea.setExtraThree(rs.getString("Column_5"));

					searchResult.add(ikea);
				}
			}
			rs.close();
			connIKEA.endRequest();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return searchResult;
	}

	/*
	 * Method for sending query to the bear database.
	 */
	private static ArrayList<Object> bearDatabaseSQL(String searchString, ArrayList<Object> searchResult) {
		
		try {
			ArrayList<Integer> keys = new ArrayList<Integer>();
			String requestQuery = "SELECT `bear_id`, `Character`, `Origin`, `Creator`, `Notes` FROM `bearbase` WHERE `Character` LIKE ? OR `Origin` LIKE ? OR `Creator` LIKE ? OR `Notes` LIKE ?";

			stmt = connBear.prepareStatement(requestQuery);
			stmt.setString(1, searchString);
			stmt.setString(2, searchString);
			stmt.setString(3, searchString);
			stmt.setString(4, searchString);

			rs = stmt.executeQuery();
			
			searchResult.forEach(bean -> {
				if(bean.getClass().equals(beans.bearBean.class)) { 
				keys.add(((beans.bearBean) bean).getKey());
				}
			});
			
			while (rs.next()) {
				
				if(checkNew(keys, rs.getInt("bear_id"))) {	
					bearBean bear = new bearBean();
				
					bear.setKey(rs.getInt("bear_id"));
					bear.setCharacter(rs.getString("Character"));
					bear.setOrigin(rs.getString("Origin"));
					bear.setCreator(rs.getString("Creator"));
					bear.setNotes(rs.getString("Notes"));

					searchResult.add(bear);
				}
			}
			rs.close();
			connBear.endRequest();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return searchResult;
	}

	/*
	 * Method to check if a search result already exists.
	 */
	private static boolean checkNew(ArrayList<Integer> keys, int key) {
		
		for(int i = 0; i < keys.size(); i++) {
			if (keys.get(i) == key) {
				return false;
			}
		}
		return true;
	}

}
