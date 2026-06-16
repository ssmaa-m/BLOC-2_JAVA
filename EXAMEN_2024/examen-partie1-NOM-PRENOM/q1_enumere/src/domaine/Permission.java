package domaine;


public class Permission {

    public enum PermissionType {
        RWX(true , true , true) , RW_ (true , true , false), R__ (true , false ,false),
        R_X (true , false , true), _WX (false , true , true), _W_ (false , true , false), __X (false , false , true);

        private boolean read;
        private boolean write;
        private boolean execute;


        PermissionType(boolean b, boolean b1, boolean b2) {
            read = b;
            write = b1;
            execute = b2;
        }

        public boolean isRead() {
            return read;
        }

        public boolean isWrite() {
            return write;
        }

        public boolean isExecute() {
            return execute;
        }

    }
    private User user;
    private File file;
    private PermissionType permissionType;

    public Permission(User user, File file, PermissionType permissionType) {
        this.user = user;
        this.file = file;
        this.permissionType = permissionType;
    }

    public User getUser() {
        return user;
    }

    public File getFile() {
        return file;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void print() {
        System.out.println("Permission: " + user.getName() + " " + file.getName() + " " + permissionType);
    }

}
