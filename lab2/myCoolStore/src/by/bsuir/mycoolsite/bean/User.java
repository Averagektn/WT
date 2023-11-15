package by.bsuir.mycoolsite.bean;

import by.bsuir.mycoolsite.bean.enums.Role;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class representing a user in the system.
 */
public class User {
    private long id;
    private String email;
    private String password;
    private Role role;
    private long bannedBy;

    /** Constant indicating that the user is not banned. */
    public static final int NOT_BANNED = -1;

    /**
     * Constructs a new User with the specified ID.
     *
     * @param id the ID of the user
     */
    public User(long id) {
        this(id, "", "", Role.CUSTOMER, 0);
    }

    /**
     * Constructs a new User with the specified ID, email, password, role, and bannedBy.
     *
     * @param id       the ID of the user
     * @param email    the email of the user
     * @param password the password of the user
     * @param role     the Role object representing the role of the user
     * @param bannedBy the ID of the user who banned this user
     */
    public User(long id, String email, String password, Role role, long bannedBy) {
        this.id = id;
        this.email = email;
        this.password = getHashSha512Password(password);
        this.role = role;
        this.bannedBy = bannedBy;
    }

    /**
     * Constructs a new User with an undefined ID and the specified email, password, role, and bannedBy.
     *
     * @param email    the email of the user
     * @param password the password of the user
     * @param role     the Role object representing the role of the user
     * @param bannedBy the ID of the user who banned this user
     */
    public User(String email, String password, Role role, long bannedBy) {
        this(-1, email, password, role, bannedBy);
    }

    /**
     * Constructs a new User with an undefined ID and the specified email and password.
     *
     * @param email    the email of the user
     * @param password the password of the user
     */
    public User(String email, String password) {
        this(-1, email, password, Role.CUSTOMER, NOT_BANNED);
    }

    /**
     * Checks if the user has the role of an administrator.
     *
     * @return true if the user is an administrator, false otherwise
     */
    public boolean isAdmin() {
        return role == Role.ADMIN;
    }

    /**
     * Gets the email of the user.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email the new email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password hash of the user.
     *
     * @return the password hash of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password hash of the user.
     *
     * @param password the new password hash of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role of the user.
     *
     * @return the Role object representing the role of the user
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the new Role object representing the role of the user
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets whether the user is banned or not.
     *
     * @return true if the user is banned, false otherwise
     */
    public boolean isBanned() {
        return bannedBy != NOT_BANNED;
    }

    /**
     * Gets the ID of the user who banned this user.
     *
     * @return the ID of the user who banned this user
     */
    public long getBannedBy() {
        return bannedBy;
    }

    /**
     * Bans the user with the specified banner ID.
     *
     * @param bannerId the ID of the user who banned this user
     */
    public void ban(long bannerId) {
        this.bannedBy = bannerId;
    }

    /**
     * Unbans the user.
     */
    public void unban() {
        this.bannedBy = NOT_BANNED;
    }

    /**
     * Gets the ID of the user.
     *
     * @return the ID of the user
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id the new ID of the user
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Generates a SHA-512 hash of the provided password.
     *
     * @param password the password to hash
     * @return the hashed password
     */
    private static String getHashSha512Password(String password) {
        String passwordHash = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
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
}
