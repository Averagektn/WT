package by.bsuir.mycoolsite.bean;

import by.bsuir.mycoolsite.bean.enums.Role;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private long id;
    private String email;
    private String password;
    private Role role;
    private long bannedBy;

    public static final int NOT_BANNED = -1;

    public User(long id) {
        this(id, "", "", Role.CUSTOMER, 0);
    }

    public User(long id, String email, String password, Role role, long bannedBy) {
        this.id = id;
        this.email = email;
        this.password = getHashSha512Password(password);
        this.role = role;
        this.bannedBy = bannedBy;
    }

    public User(String email, String password, Role role, long bannedBy) {
        this(-1, email, password, role, bannedBy);
    }

    public User(String email, String password) {
        this(-1, email, password, Role.CUSTOMER, NOT_BANNED);
    }

    public boolean isAdmin() {
        return role == Role.ADMIN;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private static String getHashSha512Password(String Password) {
        String passwordHash = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(Password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();

            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            passwordHash = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Algorithm wasn't found: " + e.toString());
        }

        return passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBanned() {
        return bannedBy != NOT_BANNED;
    }

    public long getBannedBy() {
        return bannedBy;
    }

    public void ban(long bannerId) {
        this.bannedBy = bannerId;
    }

    public void unban() {
        this.bannedBy = NOT_BANNED;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
