package com.caronapp.BackEndUserModule.autenticacao.security;

public class ConstantesSeguranca  {
    public static final String SECRET = "deTodosUmSomente";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 dias
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/usuario/cadastrar";
}
