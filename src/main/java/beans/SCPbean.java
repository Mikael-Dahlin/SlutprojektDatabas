package beans;

/*
 * Bean to handle response from scp database.
 */
public class SCPbean {

	int scp_id;
	String SCP, title, rating, classification, type, related_GOI_s, location_notes, author, leaked_info, humanoid_or_structure, animal_computer_or_extradimensional, autonomous_or_cognitohazard, artifact_location_or_sentient, summary, gender, none, ethnicity_origins;
	
	public int getKey() {
		return scp_id;
	}
	public void setKey(int scp_id) {
		this.scp_id = scp_id;
	}
	public String getSCP() {
		return SCP;
	}
	public void setSCP(String scp) {
		this.SCP = scp;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRelated_GOI_s() {
		return related_GOI_s;
	}
	public void setRelated_GOI_s(String related_GOI_s) {
		this.related_GOI_s = related_GOI_s;
	}
	public String getLocation_notes() {
		return location_notes;
	}
	public void setLocation_notes(String location_notes) {
		this.location_notes = location_notes;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getLeaked_info() {
		return leaked_info;
	}
	public void setLeaked_info(String leaked_info) {
		this.leaked_info = leaked_info;
	}
	public String getHumanoid_or_structure() {
		return humanoid_or_structure;
	}
	public void setHumanoid_or_structure(String humanoid_or_structure) {
		this.humanoid_or_structure = humanoid_or_structure;
	}
	public String getAnimal_computer_or_extradimensional() {
		return animal_computer_or_extradimensional;
	}
	public void setAnimal_computer_or_extradimensional(String animal_computer_or_extradimensional) {
		this.animal_computer_or_extradimensional = animal_computer_or_extradimensional;
	}
	public String getAutonomous_or_cognitohazard() {
		return autonomous_or_cognitohazard;
	}
	public void setAutonomous_or_cognitohazard(String autonomous_or_cognitohazard) {
		this.autonomous_or_cognitohazard = autonomous_or_cognitohazard;
	}
	public String getArtifact_location_or_sentient() {
		return artifact_location_or_sentient;
	}
	public void setArtifact_location_or_sentient(String artifact_location_or_sentient) {
		this.artifact_location_or_sentient = artifact_location_or_sentient;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNone() {
		return none;
	}
	public void setNone(String none) {
		this.none = none;
	}
	public String getEthnicity_origins() {
		return ethnicity_origins;
	}
	public void setEthnicity_origins(String ethnicity_origins) {
		this.ethnicity_origins = ethnicity_origins;
	}
	
}
