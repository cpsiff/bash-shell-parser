package bashShell;

public class Type {
    public Byte kind;

    public final static Byte STRING = 0;
    public final static Byte NUMERIC = 1;
    public final static Byte EXECUTABLE = 2;
    public final static Byte NULLTYPE = 3;

    public static Type string = new Type(STRING);
    public static Type numeric = new Type(NUMERIC);
    public static Type executable = new Type(EXECUTABLE);
    public static Type nulltype = new Type(NULLTYPE);

    public Type(byte kind){
        this.kind = kind;
    }

//    public boolean equals(Object other){
//        Type otherType = (Type) other;
//        if (this.kind == otherType.kind){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
}
