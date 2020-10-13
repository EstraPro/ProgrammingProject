
public class people {
	private String id;
	private String name;
	private String lastName;
	private String birthDate;
	private String gender;
	private String birthPlace;
	private String home;
	private String studiedAt;
	private String workPlaces;
	private String films;
	private String groupCode;
	
	
	
	public people(String id, String name, String lastName, String birthDate, String gender, String birthPlace,
			String home, String studiedAt, String workPlaces, String films, String groupCode) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.birthPlace = birthPlace;
		this.home = home;
		this.studiedAt = studiedAt;
		this.workPlaces = workPlaces;
		this.films = films;
		this.groupCode = groupCode;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getStudiedAt() {
		return studiedAt;
	}
	public void setStudiedAt(String studiedAt) {
		this.studiedAt = studiedAt;
	}
	public String getWorkPlaces() {
		return workPlaces;
	}
	public void setWorkPlaces(String workPlaces) {
		this.workPlaces = workPlaces;
	}
	public String getFilms() {
		return films;
	}
	public void setFilms(String films) {
		this.films = films;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	
	
	
}