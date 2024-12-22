package todo.todo.common;

public interface ResponseCode {
    // 인터페이스는 무조건 public static final로 인식함
//       public static final String SUCCESS = "SU";

    // http status 200
    String SUCCESS = "SU";
    //400
    String VALIDATION_FAILED = "VF";
    String DUPLICATE_USERNAME = "DU";
    String DUPLICATE_NICKNAME = "DN";
    String DUPLICATE_TEL_NUMBER = "DT";
    String NOT_EXISTED_USER = "NU";
    String NOT_EXISTED_BOARD = "NB";

    //401
    String SIGN_IN_FAIL = "SF";
    String AUTHORIZATION_FAIL = "AF";

    //403
    String NO_PERMISSION = "NP";

    //500
    String DATABASE_ERROR = "DRE";
}
