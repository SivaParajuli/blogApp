package com.sipra.blogapplication.config;

import java.nio.charset.StandardCharsets;

public class AppConstants {
    public static final String PAGE_NUMBER="0";
    public static final String PAGE_SIZE="8";
    public static final String SORT_BY="postId";
    public static final String SORT_DIR="asc";
    public static final String FILE_PATH=System.getProperty("user.home").concat("blogPost/img");
    //jwt token validity
    public static final long JWT_TOKEN_VALIDITY = 5*60*60;
    public static final byte[] JWT_SECRET_KEY = "secretKeyBlogSipraBlogSreGlobYeacretKeyBlogSipraBlogSecretTreGlobYea".getBytes(StandardCharsets.UTF_8);
    //role
    public static final Long NORMAL_USER= 502L;
    public static final Long ADMIN_USER= 502L;

}
