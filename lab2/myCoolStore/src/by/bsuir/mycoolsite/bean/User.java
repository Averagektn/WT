package by.bsuir.mycoolsite.bean;

import by.bsuir.mycoolsite.bean.enums.Role;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private long id;
    private String email;
    private final static String salt = "";
    private String password;
    private Role role;
    private boolean isBan;

    public User(long id, String email, String password, Role role, boolean isBan) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isBan = isBan;
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

    public static String getHashSha512Password(String Password) {
        String passwordHash = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(Password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();

            md.update(salt.getBytes(StandardCharsets.UTF_8));

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

    public boolean isBan() {
        return isBan;
    }

    public void ban() {
        this.isBan = true;
    }

    public void unban() {
        this.isBan = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
