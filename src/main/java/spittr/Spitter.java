package spittr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;

@Table(name="Spitter")
@Entity
public class Spitter {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="username")
	@NotNull
	@Size(min=5, max=16, message="{username.size}")
	private String username;
	@Column(name="password")
	@NotNull
	@Size(min=5, max=25, message="{password.size}")
	private String password;
	@Column(name="firstName")
	@NotNull
	@Size(min=2, max=30, message="{firstName.size}")
	private String firstName;
	@Column(name="lastName")
	@NotNull
	@Size(min=2, max=30, message="{lastName.size}")
	private String lastName;
	@Column(name="email")
	@NotNull
	@Email(message="{email.valid}")
	private String email;

	public Spitter() {
	}

	public Spitter(String username, String password, String firstName, String lastName, String email) {
		this(null, username, password, firstName, lastName, email);
	}
	
	public Spitter(Long id, String username, String password, String firstName, String lastName) {
		this(id, username, password, firstName, lastName, null);
	}
	public Spitter(Long id, String username, String password, String firstName, String lastName, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "firstName", "lastName", "username", "password", "email");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "firstName", "lastName", "username", "password", "email");
	}

}