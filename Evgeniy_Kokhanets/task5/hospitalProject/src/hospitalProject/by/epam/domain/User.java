package hospitalProject.by.epam.domain;

public class User extends Entity{
    private String login;
    private String password;
    private String name;
    private Role role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }


    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "User [id=" + super.getId() + ", login=" + login + ", password=" + password + ", name=" + name +  ", role=" + role + "]";
    }
    
}
