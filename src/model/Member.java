package model;

import java.util.Objects;

public class Member extends Person {
    private String Role;

    Member(){
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Member member = (Member) o;
        return Objects.equals(Role, member.Role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Role);
    }
}
