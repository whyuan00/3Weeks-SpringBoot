package todo.todo.common;

public interface ResponseStatus {
    // 인터페이스는 무조건 public static final로 인식함
//       public static final String SUCCESS = "SU";

    // http status 200
    String SUCCESS = "OK";
    String CREATED = "CREATED";

    //400
    String VALIDATION_FAILED = "VF";
    //409
    String DUPLICATE_USERNAME = "DU";
    String DUPLICATE_NICKNAME = "DN";
//    String DUPLICATE_TEL_NUMBER = "DT";
    String NOT_EXISTED_USER = "NU";
    String NOT_EXISTED_TODO = "NB";

    //401
    String SIGN_IN_FAIL = "SF";
    String AUTHORIZATION_FAIL = "AF";

    //403
    String NO_PERMISSION = "NP";

    //500
    String DATABASE_ERROR = "DRE";
}
