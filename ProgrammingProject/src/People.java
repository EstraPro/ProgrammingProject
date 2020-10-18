/**
 * Class that represents a person
 * @author ZSJ
 */
public class People {
	
	//Attribute declaration
	private String id, name, lastName, birthDate, gender, birthPlace, home, studiedAt, workPlaces, films, groupCode;

	/**
	 * Empty constructor
	 */
	public People() {}

	/**
	 * Id getter
	 * @return id
	 */
	public String getId() {
		
		return id;
	}

	/**
	 * Id setter
	 * @param id
	 */
	public void setId(String id) {
		
		this.id = id;
	}

	/**
	 * Name getter
	 * @return name
	 */
	public String getName() {
		
		return name;
	}

	/**
	 * Name setter
	 * @param name
	 */
	public void setName(String name) {
		
		this.name = name;
	}

	/**
	 * Last name getter
	 * @return lastName
	 */
	public String getLastName() {
		
		return lastName;
	}

	/**
	 * Last name setter
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		
		this.lastName = lastName;
	}

	/**
	 * Birth date getter
	 * @return birthDate
	 */
	public String getBirthDate() {
		
		return birthDate;
	}

	/**
	 * Birth date setter
	 * @param birthDate
	 */
	public void setBirthDate(String birthDate) {
		
		this.birthDate = birthDate;
	}

	/**
	 * Gender getter
	 * @return gender
	 */
	public String getGender() {
		
		return gender;
	}

	/**
	 * Gender setter
	 * @param gender
	 */
	public void setGender(String gender) {
		
		this.gender = gender;
	}

	/**
	 * Birth place getter
	 * @return birthPlace
	 */
	public String getBirthPlace() {
		
		return birthPlace;
	}

	/**
	 * Birth place setter
	 * @param birthPlace
	 */
	public void setBirthPlace(String birthPlace) {
		
		this.birthPlace = birthPlace;
	}

	/**
	 * Home getter
	 * @return
	 */
	public String getHome() {
		
		return home;
	}

	/**
	 * Home setter
	 * @param home
	 */
	public void setHome(String home) {
		
		this.home = home;
	}

	/**
	 * Place of study getter
	 * @return studiedAt
	 */
	public String getStudiedAt() {
		
		return studiedAt;
	}

	/**
	 * Place of study setter
	 * @param studiedAt
	 */
	public void setStudiedAt(String studiedAt) {
		
		this.studiedAt = studiedAt;
	}

	/**
	 * Work places getter
	 * @return workPlaces
	 */
	public String getWorkPlaces() {
		
		return workPlaces;
	}

	/**
	 * Work places setter
	 * @param workPlaces
	 */
	public void setWorkPlaces(String workPlaces) {
		
		this.workPlaces = workPlaces;
	}

	/**
	 * Films getter
	 * @return films
	 */
	public String getFilms() {
		
		return films;
	}

	/**
	 * Films setter
	 * @param films
	 */
	public void setFilms(String films) {
		
		this.films = films;
	}

	/**
	 * Group code getter
	 * @return groupCode
	 */
	public String getGroupCode() {
		
		return groupCode;
	}

	/**
	 * Group code setter
	 * @param groupCode
	 */
	public void setGroupCode(String groupCode) {
		
		this.groupCode = groupCode;
	}

	/**
	 * Overriding toString method
	 * @return all in a string
	 */
	public String toString() {

		String s = "";

		s += id + ",";
		s += name + ",";
		s += lastName + ",";
		s += birthDate + ",";
		s += gender + ",";
		s += birthPlace + ",";
		s += home + ",";
		s += studiedAt + ",";
		s += workPlaces + ",";
		s += films + ",";
		s += groupCode + ",";

		return s;
	}

}