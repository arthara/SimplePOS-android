package com.simple.pos.modul.login;

import com.simple.pos.base.util.UtilProvider;
import com.simple.pos.shared.apiresponse.APIResponseCollection;
import com.simple.pos.shared.callback.RequestCallback;
import com.simple.pos.shared.callback.RetrofitCallback;
import com.simple.pos.shared.model.Token;
import com.simple.pos.shared.model.User;
import com.simple.pos.shared.retrofit.ServiceGenerator;
import com.simple.pos.shared.util.TokenUtil;
import com.simple.pos.shared.util.UserUtil;

import retrofit2.Call;

public class LoginInteractor implements LoginContract.Interactor {
    private static final String TAG = LoginInteractor.class.getSimpleName();
    private final LoginService service;

    public LoginInteractor() {
        service = ServiceGenerator.createService(LoginService.class);
    }

    @Override
    public void requestLogin(String email, String pass, RequestCallback<Token> callback) {
        Call<Token> call = service.login(new User(email, pass));
        call.enqueue(new RetrofitCallback<>(callback, TAG, "requestLogin"));
    }

    @Override
    public void requestSaveToken(Token token) {
        TokenUtil tokenUtil = (TokenUtil) UtilProvider.getUtil(TokenUtil.class);
        tokenUtil.initialize(token);
    }

    @Override
    public void requestUser(Token token, RequestCallback<User> callback) {
        LoginService service = ServiceGenerator.createService(LoginService.class, token.getToken());
        Call<APIResponseCollection<User>> call = service.me();
        call.enqueue(new RetrofitCallback<>(callback, TAG, "requestUser"));
    }

    @Override
    public void requestSaveUser(User user) {
        UserUtil userUtil = (UserUtil) UtilProvider.getUtil(UserUtil.class);
        userUtil.initialize(user);
    }
}
