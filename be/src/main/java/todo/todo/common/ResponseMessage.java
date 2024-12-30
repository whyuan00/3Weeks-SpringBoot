package todo.todo.common;

public interface ResponseMessage {

    // http status 200
    String SUCCESS = "status 200 Success";
    String CREATED = "status 200 Created";

    //400 Bad request
    String VALIDATION_FAILED = "status 400 Validation failed";

    // 409 Confilct 서버 정보와 충돌할때
    String DUPLICATE_USERNAME = "status 409 Duplicate username";
    String DUPLICATE_NICKNAME = "status 409 Duplicate nickname";
//    String DUPLICATE_TEL_NUMBER = "status 400 Duplicate tel number";
    String NOT_EXISTED_USER = "status 400 This user does not exist.";
    String NOT_EXISTED_BOARD = "status 400 This todo does not exist";

    //401 unauthorized 클라이언트가 요청을 인증할 수 없을 때
    String USERNAME_ERROR = "status 401 Username error";
    String PASSWORD_MISMATCH = "status 401 Password mismatch";
    String AUTHORIZATION_FAIL = "status 401 Authorization failed";

    //403
    String NO_PERMISSION = "status 403 Do not have permission";

    //500
    String DATABASE_ERROR = "status 500 Database error";
}
