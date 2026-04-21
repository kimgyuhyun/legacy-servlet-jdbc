package step1.user.dto;
import java.sql.Timestamp;
import java.sql.Date;


public class UserDto {
	private Long id;
	private String name;
	private Integer age;
	private Date birthDate;
	private String address;
	private Timestamp createAt;
	private Timestamp updateAt;
	
	public UserDto() {}
	
	public UserDto(Long id, String name, Integer age, Date birthDate, String address,
			Timestamp createAt, Timestamp updateAt) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.birthDate = birthDate;
		this.address = address;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}
	
	
}