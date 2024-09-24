public class Main {
    public static void main(String[] args) {
        Builder director = new Builder();
        String[] directorArg = {args[0], args[1], args[2]};
        director.main(directorArg);
    }
}