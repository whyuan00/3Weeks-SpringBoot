package todo.todo.common;

public interface ResponseStatus {
    // 인터페이스는 무조건 public static final로 인식함
//       public static final String SUCCESS = "SU";

    // http status 200
    String SUCCESS = "OK";
    String CREATED = "CREATED";

    //400
    String VALIDATION_FAILED = "VF";

    //401
    String SIGN_IN_FAIL = "SF";
    String AUTHORIZATION_FAIL = "AF";

    //403
    String NO_PERMISSION = "NP";

    // 404
    String NOT_EXIST_USER = "NU";
    String NOT_EXIST_TODO = "NB";
    String NOT_EXIST_PROFILEIMAGE = "NP";

    //409
    String DUPLICATE_USERNAME = "DU";
    String DUPLICATE_NICKNAME = "DN";
//    String DUPLICATE_TEL_NUMBER = "DT";

    //500
    String DATABASE_ERROR = "DRE";
}
