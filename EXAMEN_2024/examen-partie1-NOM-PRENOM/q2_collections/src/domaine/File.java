package domaine;

import java.time.LocalDate;
import java.util.*;

public class File {
    private String name;
    private int size;
    private User owner;

    private SortedMap<User , List<Permission>> permissions = new TreeMap<>(Comparator.comparing(User::getId));

    public File(String name, int size, User owner) {
        this.name = name;
        this.size = size;
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    /**
     *  Vérifie que l'utilisateur a toutes les permissions en paramètre
     * @param user
     * @param read
     * @param write
     * @param execute
     * @return true si l'utilisateur a toutes les permissions, false sinon.
     */
    public boolean hasPermission(User user, boolean read, boolean write, boolean execute) {

        if (user.equals(owner)) {
            return true;
        }

        List<Permission> permissio1 = permissions.get(user);

        if(permissio1 == null || permissio1.isEmpty()){
            return false;
        }

        boolean hasRead = false;
        boolean hasWrite = false;
        boolean hasExecute = false;
        for (Permission permission : permissio1) {
            if (!permission.isExpired()) {
                hasRead = hasRead || permission.isRead();
                hasWrite = hasWrite || permission.isWrite();
                hasExecute = hasExecute || permission.isExecute();
            }
        }
        return ( hasRead || !read ) && ( hasWrite || !write ) && ( hasExecute || !execute );

    }



    /**
     *  Annule toutes les permissions pour l'utilisateur
     * @param user
     * @return true si l'utilisateur avait des permissions, false sinon.
     */
    public List<Permission> revokeAllPermissions(User user) {
        return permissions.remove(user);
    }


    /**
     * Ajoute une permission pour l'utilisateur
     * @param user1
     * @param r
     * @param w
     * @param x
     */
    public void addPermission(User user1, boolean r, boolean w, boolean x) {
        List<Permission> permissions1 = permissions.computeIfAbsent(user1, k -> new ArrayList<>());
        permissions1.add(new Permission(user1, this, r, w, x));
    }

    public void addPermission(User user1, boolean r, boolean w, boolean x, LocalDate expirationDate) {
        List<Permission> permissions1 = permissions.computeIfAbsent(user1, k -> new ArrayList<>());

        permissions1.add(new Permission(user1, this, r, w, x, expirationDate));
    }
    public void print() {
        System.out.println("File: " + name + " (" + size + " octets)");
    }

    public void printPermissions() {
        System.out.println("Permissions for file: " + name);
        for (Map.Entry<User, List<Permission>> userListEntry : permissions.entrySet()) {
            User user = userListEntry.getKey();
            List<Permission> permission = userListEntry.getValue();

            for (Permission permission1 : permission) {
                System.out.println("User : " + permission1.getUser().getName() + " File : " + permission1.getFile().name + " Permision : " + permission);
            }
        }
    }


}
