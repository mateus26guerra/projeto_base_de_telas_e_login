package projeto_base_de_telas_e_login.domain.model.user;

public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
